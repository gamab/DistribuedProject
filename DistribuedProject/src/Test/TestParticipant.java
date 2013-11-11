package Test;

import Participant.Participant;


public class TestParticipant {
	public static void runTest() {
		testParticipantToString();
		testParticipantFromString();
		testParticipantToStringvide();
		testParticipantFromStringvide();
	}

	public static void testParticipantToString(){
		System.out.println("###############################");
		System.out.println("## TEST ParticipantToString  ##");
		System.out.println("###############################");
		System.out.println("What we espect : 1//2//cacahuete;;pikatchou;;patate;;cornichon");
		Participant participant ;
		participant = new Participant(1,2);
		participant.addToResourcesList("cacahuete");
		participant.addToResourcesList("pikatchou");
		participant.addToResourcesList("patate");
		participant.addToResourcesList("cornichon");
		System.out.print("result : ");
		System.out.println(participant.toString());
	}
	
	public static void testParticipantToStringvide(){
		System.out.println("###############################");
		System.out.println("## TEST ParticipantToString  ##");
		System.out.println("###############################");
		System.out.println("What we espect : 1//2//");
		Participant participant ;
		participant = new Participant(1,2);
		System.out.print("result : ");
		System.out.println(participant.toString());
	}
	
	public static void testParticipantFromString(){
		System.out.println("################################");
		System.out.println("## TEST ParticipantFromString ##");
		System.out.println("################################");
		System.out.println("What we espect : 1//2//cacahuete;;pikatchou;;patate;;cornichon");
		Participant participant;
		participant = new Participant();
		participant.fromString( "1//2//cacahuete;;pikatchou;;patate;;cornichon");
		System.out.print("result : ");
		System.out.println(participant.toString());
	}
	
	public static void testParticipantFromStringvide(){
		System.out.println("################################");
		System.out.println("## TEST ParticipantFromString ##");
		System.out.println("################################");
		System.out.println("What we espect : 1//2//");
		Participant participant = new Participant();
		participant.fromString( "1//2//");
		System.out.print("result : ");
		System.out.println(participant.toString());
	}
	
}