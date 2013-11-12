package Sources;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import CriticalResources.CRWaitingList;
import CriticalResources.CriticalRegion;
import DatagramCommunication.CommunicationMessage;
import DatagramCommunication.DatagramCommunication;
import Participant.ParticipantList;

public class Processus {
	Integer pid;
	ParticipantList participants;
	CriticalRegion[] criticalRegion;
	CRWaitingList crWaitingLists;
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
		this.crWaitingLists = new CRWaitingList();
		this.clock = new LogicalClock();
		DatagramSocket serviceThreadSocket;
		try{
			// if it's the first processus we listen on port 5000
			serviceThreadSocket = new DatagramSocket(54321);
		}catch (SocketException e){
			//if it's already used we listen to another random port
			serviceThreadSocket = new DatagramSocket(0);
			this.resources[0] = "a1";
			this.resources[1] = "b2";
			// now we ask to the processus listening on the port 54321 the actual list of participants 
			DatagramCommunication.sendMessage("GET_PARTICIPANTS",this.socket, this.socket.getLocalAddress(), 5000);
			String message = DatagramCommunication.retrieveMessage(this.socket).getMessage();
			// we actualize our own list of participants
			this.participants.fromString(message);	
			// if it is the second processus it takes the following resources
			if (this.participants.size() == 1) {
				this.resources[0] = "a2 ";
				this.resources[1] = "b3";
			}
			// if it is the third processus it takes the following resources
			if (this.participants.size() == 2) {
				this.resources[0] = "a3";
				this.resources[1] = "b1";
			}
			
		}	
		this.resources = new String[2];
		this.servicePort = serviceThreadSocket.getPort();
		this.serviceThread = new ServiceThread(serviceThreadSocket, this.crWaitingLists, this.resources,this.participants, this.pid,this.clock);
		
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
		return crWaitingLists.get(crid).getPid();
	}
	
	public Integer my_servicePort(){
		return servicePort;
	}
	
	public void setServicePort(Integer servicePort){
		this.servicePort = servicePort;
	}
	
}
