package Sources;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.ListIterator;

import CriticalResources.CRWaitingList;
import CriticalResources.CriticalRegion;
import DatagramCommunication.CommunicationMessage;
import DatagramCommunication.DatagramCommunication;
import Participant.Participant;
import Participant.ParticipantList;

public class Processus {
	Integer pid;
	ParticipantList participants;
	CriticalRegion[] criticalRegion;
	CRWaitingList[] crWaitingLists;
	DatagramSocket socket;
	ServiceThread serviceThread;
	Integer servicePort;
	LogicalClock clock;
	String[] resources;


	public Processus(Integer pid) throws SocketException{
		super();
		this.pid = pid;
		this.participants = new ParticipantList();
		this.criticalRegion[0] = new CriticalRegion(this,1); 
		this.criticalRegion[1] = new CriticalRegion(this,2); 
		this.criticalRegion[2] = new CriticalRegion(this,3); 
		this.crWaitingLists = new CRWaitingList[3];
		this.clock = new LogicalClock();
		DatagramSocket serviceThreadSocket;
		this.resources = new String[2];
		try{
			// if it's the first processus we listen on port 54321 and we give him a1, b2
			serviceThreadSocket = new DatagramSocket(54321);
			this.resources[0] = "a1";
			this.resources[1] = "b2";
		}catch (SocketException e){
			//if it's already used we listen to another random port
			serviceThreadSocket = new DatagramSocket(0);
			// now we ask to the processus listening on the port 54321 the actual list of participants 
			ArrayList<CommunicationMessage> messages = sendMessage("GET_PARTICIPANTS<<" + this.clock.getClock() + "<<",54321);
			retrieveClockFromMessageList(messages);
			// we actualize our own list of participants
			this.participants.fromString(messages.get(0).getMessage());	
			// if we are the second processus we take the following resources
			if (this.participants.size() == 1) {
				this.resources[0] = "a2 ";
				this.resources[1] = "b3";
			}
			// if we are the third processus we takes the following resources
			if (this.participants.size() == 2) {
				this.resources[0] = "a3";
				this.resources[1] = "b1";
			}
		}	
		//we save our serviceThread port
		this.servicePort = serviceThreadSocket.getPort();
		//we create ourself
		ArrayList<String> resourcesList = new ArrayList<String>();
		for (int i = 0; i<this.resources.length;i++) {
			resourcesList.add(this.resources[i]);
		}
		Participant me = new Participant(this.pid, this.servicePort, resourcesList);

		//we ask everyone to add us with a JOIN request
		String joinRequest = "JOIN<<"+me.toString()+"<<"+this.clock.getClock()+"<<";
		ArrayList<CommunicationMessage> messages = sendMessage(joinRequest,-1);
		retrieveClockFromMessageList(messages);
		
		//we add ourselves to the list
		this.participants.add(me);
		
		//we create the serviceThread
		this.serviceThread = new ServiceThread(serviceThreadSocket, this.crWaitingLists, this.resources,this.participants, this.pid,this.clock);

	}
	
	//What our program has to do
	public void run() {
		//we start the serviceThread
		this.serviceThread.start();
		//we wait for every program to join
		while (participants.size() != 3) {
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//we start our loop
		while (true) {
			CriticalRegion cr = criticalRegion[randomInRange(0,criticalRegion.length-1)];
			cr.enter();
			cr.execute();
			cr.release();
		}
	}

	public Integer myPid() {
		return pid;
	}

	public Integer processes(){
		return participants.size();
	}

	public Integer whoHasResource(String resource){
		Integer result = null;
		boolean findIt = false;
		// we search into the list of participants
		for (int i = 0 ; i < participants.size() && !findIt ; i++){
			// the participant who has the resource give his pid
			if (participants.get(i).hasTheResource(resource)){
				result = participants.get(i).getPid();
				findIt = true;
			}
		}
		return result;
	}

	public Integer whoIsHeadOfCRWaitingList(Integer crid){
		return crWaitingLists[crid].get(0).getPid();
	}

	public Integer my_servicePort(){
		return servicePort;
	}

	public void setServicePort(Integer servicePort){
		this.servicePort = servicePort;
	}


	//Goes in the Participants list and retrieves all pids.
	private ArrayList<Integer> getAllPids() {
		ListIterator<Participant> partIt = this.participants.listIterator();
		ArrayList<Integer> pids = new ArrayList<Integer>();
		while (partIt.hasNext()) {
			pids.add(partIt.next().getPid());
		}
		return pids;
	}

	//Send a message to ANY if pid = - 1 or just the given pid
	private ArrayList<CommunicationMessage> sendMessage(String message, int pid) {
		ArrayList<CommunicationMessage> messages = new ArrayList<CommunicationMessage>();
		ArrayList<Integer> pids = new ArrayList<Integer>();
		if (pid == -1) {
			pids = getAllPids();
		} else {
			pids.add(pid);
		}
		messages = DatagramCommunication.sendAndRetreivedMessagesToMultipleProc(message, this.socket, pids, this.participants);
		this.clock.incClock();
		return messages;
	}

	//Send a message to ANY if pid = -1 or just the given pid
	private void retrieveClockFromMessageList(ArrayList<CommunicationMessage> messages) {
		ListIterator<CommunicationMessage> it = messages.listIterator();
		String[] data;
		while (it.hasNext()) {
			data = it.next().getMessage().split("<<");
			int messageClock = Integer.valueOf(data[data.length-1]);
			this.clock.setClockLogically(messageClock);
		}
	}
	
	//Return a random number between begin and end
	public static int randomInRange(int begin, int end) {
		return (int) (Math.random()*(end-begin) + begin);
	}

}
