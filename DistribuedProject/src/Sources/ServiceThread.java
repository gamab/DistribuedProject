package Sources;

import java.net.DatagramSocket;

import CriticalResources.CRWaitingList;
import Participant.ParticipantList;

public class ServiceThread extends Thread {

	private DatagramSocket serviceSocket;
	private CRWaitingList crWaitingList;
	private ParticipantList participants;
}
