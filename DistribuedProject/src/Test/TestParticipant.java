package Test;

import CriticalResources.CRWaitingListCell;
import Sources.Participant;


public class TestParticipant {
	public static void runTest() {
		testParticipantToString();
	}

	public static void testParticipantToString(){
		Participant participant ;
		participant = new Participant(1,1);
		participant.addToResourcesList("cacahuete");
		participant.addToResourcesList("pikatchou");
		participant.addToResourcesList("patate");
		participant.addToResourcesList("cornichon");
		System.out.println(participant.toString());
	}
}