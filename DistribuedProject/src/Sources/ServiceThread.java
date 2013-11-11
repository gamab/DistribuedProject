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
		super("ServiceThread");
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
		
		//we split the request with "{{" to have access to the different pieces of data contained by the message
		String[] datas = request.split("{{");
		//get the clock
		String clockStrg = datas[datas.length-1];
		clockStrg = clockStrg.split("}}")[0];
		//set the clock logically (max(x,y+1))
		try {
			int messageClock = Integer.valueOf(clockStrg);
			this.clock.setClockLogically(messageClock);
		} catch (NumberFormatException e) {
			answer = "COULD_NOT_GET_CLOCK{{" + this.clock.getClock() + "}}";
		}
		
		if (request.startsWith("GET_RESOURCE")) {
			String resourceName = datas[1].split("}}")[0];
			boolean resourceFound = false;
			for (int i=0; i < resources.length && !resourceFound; i++) {
				if (resourceName.equals(resources[i])) {
					answer = "OK_IT_IS_YOURS{{"+ this.clock.getClock() + "}}";
					resourceFound = true;
				}
			}
			if (!resourceFound) {
				answer = "I_DO_NOT_HAVE_IT{{"+ this.clock.getClock() + "}}";
			}
		}
		else if (request.startsWith("FREE_RESOURCE")) {
			String resourceName = datas[1].split("}}")[0];
			boolean resourceFound = false;
			for (int i=0; i < resources.length && !resourceFound; i++) {
				if (resourceName.equals(resources[i])) {
					answer = "OK_IT_IS_NOT_YOURS_ANYMORE{{"+ this.clock.getClock() + "}}";
					resourceFound = true;
				}
			}
			if (!resourceFound) {
				answer = "I_DO_NOT_HAVE_IT{{"+ this.clock.getClock() + "}}";
			}
		}
		else if (request.startsWith("JOIN")) {
			String partStrg = datas[1].split("}}")[0];
			Participant p = new Participant();
			boolean partOK = p.fromString(partStrg);
			if (partOK) {
				participants.add(p);
				answer = "OK_I_ADD_YOU{{" + this.clock.getClock() + "}}";
			} else {
				answer = "BAD_FORMAT{{" + this.clock.getClock() + "}}";
			}
		}
		else if (request.startsWith("GET_PARTICIPANTS")) {
			answer = "PARTICIPANTS{{" + participants.toString() + "}}";
		}
		else if (request.startsWith("GET_CRITICAL_REGION")) {
			String cellStrg = datas[1].split("}}")[0];
			CRWaitingListCell cell = new CRWaitingListCell();
			boolean cellOK = cell.fromString(cellStrg);
			if (cellOK) {
				// we add the participant to the list
				this.crWaitingList.add(cell);
				answer = "OK{{" + this.clock.getClock() + "}}";
			} else {
				answer = "BAD_FORMAT{{" + this.clock.getClock() + "}}";
			}
		}
		else if (request.startsWith("FREE_CRITICAL_REGION")) {
			String cellStrg = datas[1].split("}}")[0];
			CRWaitingListCell cell = new CRWaitingListCell();
			boolean cellOK = cell.fromString(cellStrg);
			if (cellOK) {
				// we remove the participant from the list
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
