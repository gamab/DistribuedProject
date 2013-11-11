package Sources;

import java.net.DatagramSocket;
import java.net.InetAddress;

import CriticalResources.CRWaitingList;

public class ServiceThread extends Thread {

	private DatagramSocket serviceSocket;
	private CRWaitingList crWaitingList;
	private String[] resources;
	private ParticipantList participants;
	private int pid;
	private LogicalClock clock;
	
	public ServiceThread(DatagramSocket serviceSocket, CRWaitingList crWaitingList,
			ParticipantList participants, int pid, LogicalClock clock) {
		super();
		this.serviceSocket = serviceSocket;
		this.crWaitingList = crWaitingList;
		this.participants = participants;
		this.pid = pid;
		this.clock = clock;
	}
	
	public void run() {
		
	}
	
	// In function of the request we received we are returning an answer thanks to the protocol we made
	private String treatRequest(String request) {
		String answer = null;
		if (request.equals("GET_RESSOURCE")) {
			
		}
		return answer;
	}
	
	private void sendAnswer(String answer, InetAddress ip, Integer port) {
		
	}
	
}
