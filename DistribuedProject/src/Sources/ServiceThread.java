package Sources;

import java.net.DatagramSocket;
import java.net.InetAddress;

import CriticalResources.CRWaitingList;
import Participant.ParticipantList;

public class ServiceThread extends Thread {

	private DatagramSocket serviceSocket;
	private CRWaitingList crWaitingList;
	private ParticipantList participants;
	private int pid;
	private LogicalClock clock;
	
	public ServiceThread(CRWaitingList crWaitingList,
			ParticipantList participants, int pid, LogicalClock clock) {
		super();
		this.crWaitingList = crWaitingList;
		this.participants = participants;
		this.pid = pid;
		this.clock = clock;
	}
	
	public void run() {
		
	}
	
	private String treatRequest(String request) {
		return null;
	}
	
	private void sendAnswer(String answer, InetAddress ip, Integer port) {
		
	}
	
}
