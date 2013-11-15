package Sources;

import java.net.DatagramSocket;

import CriticalResources.CRWaitingList;
import CriticalResources.CRWaitingListCell;
import DatagramCommunication.DatagramCommunication;
import DatagramCommunication.CommunicationMessage;
import Participant.Participant;
import Participant.ParticipantList;

public class ServiceThread extends Thread {

	private DatagramSocket serviceSocket;
	private CRWaitingList[] crWaitingLists;
	private String[] resources;
	private ParticipantList participants;
	private int pid;
	private LogicalClock clock;

	public ServiceThread(DatagramSocket serviceSocket, CRWaitingList[] crWaitingLists,
			String[] resources,ParticipantList participants, int pid,
			LogicalClock clock) {
		super("ServiceThread");
		this.serviceSocket = serviceSocket;
		this.crWaitingLists = crWaitingLists;
		this.resources = resources;
		this.participants = participants;
		this.pid = pid;
		this.clock = clock;
	}

	public void run() {

		CommunicationMessage request = new CommunicationMessage();
		String answer = new String();
		while(true) {			
			request = DatagramCommunication.retrieveMessage(serviceSocket);
			System.out.println("In ServiceThread of proc : "+ this.pid + ", in run : Received " + request.getMessage());
			answer = treatRequest(request.getMessage());
			DatagramCommunication.sendMessage(answer,serviceSocket,request.getIpOrigin(),request.getPortOrigin());
			System.out.println("In ServiceThread of proc : "+ this.pid + ", in run : Answered " + answer);
			this.clock.incClock();
		}
	}

	// In function of the request we received we are returning an answer thanks to the protocol we made
	private String treatRequest(String request) {
		String answer = null;
		boolean clockOK = true;

		//we split the request with "<<" to have access to the different pieces of data contained by the message
		String[] datas = request.split("<<");
//		for (int i = 0; i<datas.length; i++) {
//			System.out.println("datas[" + i + "] = " + datas[i]	);
//		}
		//get the clock
		String clockStrg = datas[datas.length-1];
		//set the clock logically (max(x,y+1))
		try {
			int messageClock = Integer.valueOf(clockStrg);
			this.clock.setClockLogically(messageClock);
		} catch (NumberFormatException e) {
			answer = "COULD_NOT_GET_CLOCK<<" + this.clock.getClock() + "<<";
			clockOK = false;
		}
		if (clockOK) {
			if (request.startsWith("GET_RESOURCE")) {
				String resourceName = datas[1];
				boolean resourceFound = false;
				for (int i=0; i < resources.length && !resourceFound; i++) {
					if (resourceName.equals(resources[i])) {
						answer = "OK_IT_IS_YOURS<<"+ this.clock.getClock() + "<<";
						resourceFound = true;
					}
				}
				if (!resourceFound) {
					answer = "I_DO_NOT_HAVE_IT<<"+ this.clock.getClock() + "<<";
				}
			}
			else if (request.startsWith("FREE_RESOURCE")) {
				String resourceName = datas[1];
				boolean resourceFound = false;
				for (int i=0; i < resources.length && !resourceFound; i++) {
					if (resourceName.equals(resources[i])) {
						answer = "OK_IT_IS_NOT_YOURS_ANYMORE<<"+ this.clock.getClock() + "<<";
						resourceFound = true;
					}
				}
				if (!resourceFound) {
					answer = "I_DO_NOT_HAVE_IT<<"+ this.clock.getClock() + "<<";
				}
			}
			else if (request.startsWith("JOIN")) {
				String partStrg = datas[1];
				Participant p = new Participant();
				boolean partOK = p.fromString(partStrg);
				if (partOK) {
					participants.add(p);
					answer = "OK_I_ADD_YOU<<" + this.clock.getClock() + "<<";
				} else {
					answer = "BAD_FORMAT<<" + this.clock.getClock() + "<<";
				}
			}
			else if (request.startsWith("GET_PARTICIPANTS")) {
				answer = "PARTICIPANTS<<" + participants.toString() + "<<" + this.clock.getClock() + "<<";
			}
			else if (request.startsWith("GET_CRITICAL_REGION")) {
				boolean nroOK;
				boolean cellOK = true;
				int nroCriticalRegion = -1;
				try {
					nroCriticalRegion = Integer.valueOf(datas[1]);
				} catch (NumberFormatException e) {
					nroCriticalRegion = -1;
				}
				//if the nro of RC is between 0 and the crWaitingLists length then it is ok
				nroOK = nroCriticalRegion >= 0 && nroCriticalRegion < this.crWaitingLists.length;
//				System.out.println("In ServiceThread of proc : "+ this.pid + ", in treatRequest : crWaitingLists length = " + this.crWaitingLists.length);
//				System.out.println("In ServiceThread of proc : "+ this.pid + ", in treatRequest : so nroOK :" + nroOK);
				if (nroOK){
					String cellStrg = datas[2];
					CRWaitingListCell cell = new CRWaitingListCell();
					cellOK = cell.fromString(cellStrg);
					if (cellOK) {
						// we add the participant to the list
						this.crWaitingLists[nroCriticalRegion].add(cell);
						System.out.println("In ServiceThread of proc : "+ this.pid + ", in treatRequest : adding " + cell + " to crWaitingList["+ nroCriticalRegion + "] : " + this.crWaitingLists[nroCriticalRegion].toString());
						answer = "OK<<" + this.clock.getClock() + "<<";
					}
				} 
				if (!nroOK || !cellOK) {
					answer = "BAD_FORMAT<<" + this.clock.getClock() + "<<";
				}
			}
			else if (request.startsWith("FREE_CRITICAL_REGION")) {
				boolean nroOK;
				boolean cellOK = true;
				int nroCriticalRegion = -1;
				try {
					nroCriticalRegion = Integer.valueOf(datas[1]);
				} catch (NumberFormatException e) {
					nroCriticalRegion = -1;
				}
				//if the nro of RC is between 0 and the crWaitingLists length then it is ok
				nroOK = nroCriticalRegion >= 0 && nroCriticalRegion < this.crWaitingLists.length;
//				System.out.println("In ServiceThread of proc : "+ this.pid + ", in treatRequest : crWaitingLists length = " + this.crWaitingLists.length);
//				System.out.println("In ServiceThread of proc : "+ this.pid + ", in treatRequest : so nroOK :" + nroOK);
				if (nroOK){
					String cellStrg = datas[2];
					CRWaitingListCell cell = new CRWaitingListCell();
					cellOK = cell.fromString(cellStrg);
					if (cellOK) {
						// we remove the participant to the list
						Integer position = this.crWaitingLists[nroCriticalRegion].getPosition(cell);
						if (position != null) {
							this.crWaitingLists[nroCriticalRegion].remove((int)position);
							System.out.println("In ServiceThread of proc : "+ this.pid + ", in treatRequest : removing " + cell + " from crWaitingList["+ nroCriticalRegion + "] : " + this.crWaitingLists[nroCriticalRegion].toString());
						}
						answer = "OK<<" + this.clock.getClock() + "<<";
					}
				} 
				if (!nroOK || !cellOK) {
					answer = "BAD_FORMAT<<" + this.clock.getClock() + "<<";
				}
			}
			else {
				answer = "BAD_REQUEST";
			}
		}
		return answer;
	}


}
