package Sources;

import java.net.DatagramSocket;
import java.net.InetAddress;

import CriticalResources.CRWaitingList;
import CriticalResources.CRWaitingListCell;
import Participant.Participant;
import Participant.ParticipantList;

public class ServiceThread extends Thread {

	private DatagramSocket serviceSocket;
	private CRWaitingList crWaitingList;
	private String[] resources;
	private ParticipantList participants;
	private int pid;
	private LogicalClock clock;
	
	public ServiceThread(DatagramSocket serviceSocket, CRWaitingList crWaitingList,
			String[] resources,ParticipantList participants, int pid,
			LogicalClock clock) {
		super();
		this.serviceSocket = serviceSocket;
		this.crWaitingList = crWaitingList;
		this.resources = resources;
		this.participants = participants;
		this.pid = pid;
		this.clock = clock;
	}
	
	public void run() {
		
	}
	
	// In function of the request we received we are returning an answer thanks to the protocol we made
	private String treatRequest(String request) {
		String answer = null;
		boolean answerMade = false;
		if (request.startsWith("GET_RESOURCE")) {
			String resourceName = request.split("{{")[1];
			resourceName = resourceName.split("}}")[0];
			for (int i=0; i < resources.length && !answerMade; i++) {
				if (resourceName.equals(resources[i])) {
					answer = "OK_IT_IS_YOURS";
					answerMade = true;
				}
			}
			if (!answerMade) {
				answer = "I_DO_NOT_HAVE_IT";
			}
		}
		else if (request.startsWith("FREE_RESOURCE")) {
			String resourceName = request.split("{{")[1];
			resourceName = resourceName.split("}}")[0];
			for (int i=0; i < resources.length && !answerMade; i++) {
				if (resourceName.equals(resources[i])) {
					answer = "OK_IT_IS_NOT_YOURS_ANYMORE";
					answerMade = true;
				}
			}
			if (!answerMade) {
				answer = "I_DO_NOT_HAVE_IT";
			}
		}
		else if (request.startsWith("JOIN")) {
			String partStrg = request.split("{{")[1];
			partStrg = partStrg.split("}}")[0];
			Participant p = new Participant();
			boolean added = p.fromString(partStrg);
			if (added) {
				participants.add(p);
				answer = "OK_I_ADD_YOU";
			} else {
				answer = "NO_WE_DO_NOT_LIKE_YOU";
			}
		}
		else if (request.startsWith("GET_PARTICIPANTS")) {
			answer = "PARTICIPANTS{{" + participants.toString() + "}}";
		}
		else if (request.startsWith("GET_CRITICAL_REGION")) {
			String cellStrg = request.split("{{")[1];
			String clockStrg = request.split("{{")[2];
			cellStrg = cellStrg.split("}}")[0];
			clockStrg = clockStrg.split("}}")[0];
			CRWaitingListCell cell = new CRWaitingListCell();
			boolean cellOK = cell.fromString(clockStrg);
			boolean clockOK = true;
			int messageClock = 0;
			try {
				messageClock = Integer.valueOf(clockStrg);
			} catch (NumberFormatException e) {
				clockOK = false;
			}
			if (cellOK && clockOK) {
				// we add the participant to the list and set our clock logically
				this.clock.setClockLogically(messageClock);
				this.crWaitingList.add(cell);
				answer = "OK{{" + this.clock.getClock() + "}}";
			} else {
				answer = "BAD_FORMAT{{" + this.clock.getClock() + "}}";
			}
		}
		else if (request.startsWith("FREE_CRITICAL_REGION")) {
			String cellStrg = request.split("{{")[1];
			String clockStrg = request.split("{{")[2];
			cellStrg = cellStrg.split("}}")[0];
			clockStrg = clockStrg.split("}}")[0];
			CRWaitingListCell cell = new CRWaitingListCell();
			boolean cellOK = cell.fromString(clockStrg);
			boolean clockOK = true;
			int messageClock = 0;
			try {
				messageClock = Integer.valueOf(clockStrg);
			} catch (NumberFormatException e) {
				clockOK = false;
			}
			if (cellOK && clockOK) {
				// we remove the participant from the list and set our clock logically
				this.clock.setClockLogically(messageClock);
				this.crWaitingList.remove(cell);
				answer = "OK{{" + this.clock.getClock() + "}}";
			} else {
				answer = "BAD_FORMAT{{" + this.clock.getClock() + "}}";
			}
		}
		return answer;
	}
	
	private void sendAnswer(String answer, InetAddress ip, Integer port) {
		
	}
	
}
