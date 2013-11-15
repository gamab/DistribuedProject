package Sources;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.ListIterator;

import CriticalResources.CR1;
import CriticalResources.CR2;
import CriticalResources.CR3;
import CriticalResources.CRWaitingList;
import CriticalResources.CriticalRegion;
import DatagramCommunication.CommunicationMessage;
import DatagramCommunication.DatagramCommunication;
import Participant.Participant;
import Participant.ParticipantList;

public class Processus {

	public static int portDefault = 54321;
	public static int ANY = -1;
	public static int NONE = -2;
	
	private Integer pid;
	private ParticipantList participants;
	private CriticalRegion[] criticalRegion;
	private DatagramSocket socket;
	private ServiceThread serviceThread;
	private Integer servicePort;
	private InetAddress serviceAddress;
	private LogicalClock clock;
	private String[] resources;


	public Processus(Integer pid) throws Exception{
		super();
		this.pid = pid;
		System.out.println("In Processus "+ pid +": Creating participants list.");
		this.participants = new ParticipantList();
		System.out.println("In Processus "+ pid +": Creating critical regions.");
		this.criticalRegion = new CriticalRegion[3];
		this.criticalRegion[0] = new CR1(this,0); 
		this.criticalRegion[1] = new CR2(this,1); 
		this.criticalRegion[2] = new CR3(this,2);
		System.out.println("In Processus "+ pid +": Creating clock.");
		this.clock = new LogicalClock();
		DatagramSocket serviceThreadSocket;
		System.out.println("In Processus "+ pid +": Creating resources array.");
		this.resources = new String[2];
		System.out.println("In Processus "+ pid +": Creating the processus.");		
		this.socket = new DatagramSocket(0);

		try{
			// if it's the first processus we listen on port 54321 and we give him a1, b2
			System.out.println("In Processus "+ pid +": Creating serviceThreadSocket on port :" + portDefault);
			serviceThreadSocket = new DatagramSocket(portDefault);
			System.out.println("In Processus "+ pid +": Creating processus ressources.");
			this.resources[0] = "a1";
			this.resources[1] = "b2";
		}catch (SocketException e){
			//if it's already used we listen to another random port
			serviceThreadSocket = new DatagramSocket(0);
			System.out.println("In Processus "+ pid +": Rejected => Creating serviceThreadSocket on port :" + serviceThreadSocket.getLocalPort());
			// now we ask to the processus listening on the port 54321 the actual list of participants 
			InetAddress broadcastAddress = DatagramCommunication.getOurBroadcastAddressOnWlan();
			System.out.println("In Processus "+ pid +": Asking participants to the first processus on " + broadcastAddress + ":" + this.portDefault);
			CommunicationMessage message = sendAndRetrieveOneMessage("GET_PARTICIPANTS<<" + this.clock.getClock() + "<<",broadcastAddress,portDefault);
			retrieveClockFromMessage(message);
			// we actualize our own list of participants
			System.out.print("In Processus "+ pid +": Updating our list :");
			this.participants.fromString(message.getMessage().split("<<")[1]);	
			System.out.println(participants);
			// if we are the second processus we take the following resources
			System.out.println("In Processus "+ pid +": Creating processus ressources.");
			if (this.participants.size() == 1) {
				this.resources[0] = "a2";
				this.resources[1] = "b3";
			}
			// if we are the third processus we takes the following resources
			if (this.participants.size() == 2) {
				this.resources[0] = "a3";
				this.resources[1] = "b1";
			}
		}	
		//we save our serviceThread port
		this.servicePort = serviceThreadSocket.getLocalPort();
		this.serviceAddress = DatagramCommunication.getOurAddressOnWlan();
		System.out.println("In Processus "+ pid +": Saving our service IP/PORT : " + serviceAddress  + "/" + this.servicePort);
		//we create ourself
		ArrayList<String> resourcesList = new ArrayList<String>();
		for (int i = 0; i<this.resources.length;i++) {
			resourcesList.add(this.resources[i]);
		}
		Participant me = new Participant(this.pid, this.servicePort, this.serviceAddress, resourcesList);
		System.out.println("In Processus "+ pid +": Creating ourself :" + me);

		//we ask everyone to add us with a JOIN request
		System.out.println("In Processus "+ pid +": Make a join request.");
		String joinRequest = "JOIN<<"+me.toString()+"<<"+this.clock.getClock()+"<<";
		ArrayList<CommunicationMessage> messages = sendAndRetrieveMessage(joinRequest,ANY);
		retrieveClockFromMessageList(messages);

		//we add ourselves to the list
		System.out.println("In Processus "+ pid +": Adding ourself to the list of participants.");
		this.participants.add(me);

		//we only get the waiting lists for the ServiceThread
		System.out.println("In Processus "+ pid +": Creating the waiting list.");
		CRWaitingList[] crWaitingLists = new CRWaitingList[3];
		for (int i=0; i<criticalRegion.length; i++) {
			crWaitingLists[i] = criticalRegion[i].getCrWaitingList();
		}

		//we create the serviceThread
		System.out.println("In Processus "+ pid +": Creating our serviceThread.");
		this.serviceThread = new ServiceThread(serviceThreadSocket, crWaitingLists, this.resources,this.participants, this.pid,this.clock);

	}

	//What our program has to do
	public void run() throws Exception {

		try {
			//we start the serviceThread
			this.serviceThread.start();
			//we wait for every program to join
			while (participants.size() != 3) {
				System.out.println("In Processus "+ pid +": run : We are : " + participants.size() + ", waiting to be 3...");
				Thread.currentThread().sleep(2000);
			}
			//we start our loop
			while (true) {
				int randomCr = randomInRange(0,criticalRegion.length-1);
				CriticalRegion cr = criticalRegion[randomCr];
				Thread.currentThread().sleep(2000);
				try {
					System.out.println("In Processus "+ pid +": run : asking to enter CR[" + randomCr + "]");
					cr.enter();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("In Processus "+ pid +": run : entered now executing CR[" + randomCr + "]");
				cr.execute();
				System.out.println("In Processus "+ pid +": run : executed now releasing CR[" + randomCr + "]");
				cr.release();
				System.out.println("In Processus "+ pid +": run : released CR[" + randomCr + "]");
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int myPid() {
		return pid;
	}

	public int processes(){
		return participants.size();
	}

	//returns the pid of the processus who has the resource
	//returns NONE if no one has the resource
	public int whoHasResource(String resource){
		int result = NONE;
		boolean findIt = false;
		// we search into the list of participants
		for (int i = 0 ; i < participants.size() && !findIt ; i++){
			//System.out.println("In Processus " + this.pid + ": searching who has the ressource " + resource+ " :" + participants.get(i) + "?");
			// the participant who has the resource give his pid
			if (participants.get(i).hasTheResource(resource)){
				result = participants.get(i).getPid();
				findIt = true;
			}
		}
		return result;
	}
	
	//returns the pid of the processus who is head of the cr
	//returns NONE if no one has the resource
	public int whoIsHeadOfCRWaitingList(int crid){
		int result = NONE;
		if (criticalRegion[crid].getCrWaitingList().size() > 0) {
			result = criticalRegion[crid].getCrWaitingList().get(0).getPid();
		}
		return result;
	}

	public int my_servicePort(){
		return servicePort;
	}

	public LogicalClock getClock(){
		return this.clock;
	}

	public void setServicePort(int servicePort){
		this.servicePort = servicePort;
	}


	//Goes in the Participants list and retrieves all pids.
	public ArrayList<Integer> getAllPidsButMine() {
		ListIterator<Participant> partIt = this.participants.listIterator();
		ArrayList<Integer> pids = new ArrayList<Integer>();
		int current_pid = this.NONE;
		while (partIt.hasNext()) {
			current_pid = partIt.next().getPid();
			if (current_pid != this.pid) {
				pids.add(current_pid);
			}
		}
		return pids;
	}

	//Send a message to ANY if pid = this.ANY or just the given pid
	public ArrayList<CommunicationMessage> sendAndRetrieveMessage(String message, int pid) {
		ArrayList<CommunicationMessage> messages = new ArrayList<CommunicationMessage>();
		ArrayList<Integer> pids = new ArrayList<Integer>();
		if (pid == ANY) {
			pids = getAllPidsButMine();
		} else {
			pids.add(pid);
		}
		//System.out.println("In Processus " + this.pid + ": sendAndRetrieveMessage sending message to pids = " + pids + ". With participants : " + this.participants);
		messages = DatagramCommunication.sendAndRetreivedMessagesToMultipleProc(message, this.socket, pids, this.participants);
		this.clock.incClock();
		return messages;
	}

	//Send a message to a given port
	public CommunicationMessage sendAndRetrieveOneMessage(String message, InetAddress ip, int port) {
		DatagramCommunication.sendMessage(message, this.socket, ip, port);
		this.clock.incClock();
		CommunicationMessage answer = DatagramCommunication.retrieveMessage(this.socket);
		return answer;
	}

	//Retrieve the clock from a list of messages
	public void retrieveClockFromMessageList(ArrayList<CommunicationMessage> messages) {
		ListIterator<CommunicationMessage> it = messages.listIterator();
		while (it.hasNext()) {
			retrieveClockFromMessage(it.next());
		}
	}
	//Retrieve the clock from one message
	public void retrieveClockFromMessage(CommunicationMessage message) {
		String[] data = message.getMessage().split("<<");
		int messageClock = Integer.valueOf(data[data.length-1]);
		this.clock.setClockLogically(messageClock);
	}

	//Retrieve only the message out of the CommunicationMessage
	public String[] retrieveMessageWithoutClockFromMessage(CommunicationMessage message) {
		String[] data = message.getMessage().split("<<");
		String[] result = new String[data.length-1];
		for (int i=0; i<result.length; i++) {
			result[i] = data[i];
		}
		return result;
	}

	//Return a random number between begin and end
	public static int randomInRange(int begin, int end) {
		double random = Math.random();
		int range = end - begin;
		double randRange = random * range + begin;
		double step = range/((double)range+1);
		int result = 0;
		int randUp = (int) Math.ceil(randRange);
		
		if (randRange > (double)randUp*step) {
			result = randUp;
		}
		else {
			result = randUp - 1;
		}

		
		return result;
	}
	
}
