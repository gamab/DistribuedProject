package Test;

import Participant.Participant;
import Participant.ParticipantList;


public class TestParticipant {
	public static void runTest() {
		testParticipantToString();
		testParticipantFromString();
		testParticipantToStringvide();
		testParticipantFromStringvide();
		testParticipantListToString();
		testParticipantListToStringVide();
		testParticipantListFromString();
		testParticipantHasTheResource();
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
	
	public static void testParticipantListToString(){
		System.out.println("###################################");
		System.out.println("## TEST ParticipantListToString  ##");
		System.out.println("###################################");
		System.out.println("What we espect : 1//2//cacahuete;;pikatchou:::3//4//patate;;cornichon");
		Participant participant1 = new Participant(1,2);
		participant1.addToResourcesList("cacahuete");
		participant1.addToResourcesList("pikatchou");
		Participant participant2 = new Participant(3,4);
		participant2.addToResourcesList("patate");
		participant2.addToResourcesList("cornichon");
		ParticipantList participants = new ParticipantList();
		participants.add(participant1);
		participants.add(participant2);		
		System.out.print("result : ");
		System.out.println(participants.toString());
	}
	
	public static void testParticipantListToStringVide(){
		System.out.println("#######################################");
		System.out.println("## TEST ParticipantListToStringVide  ##");
		System.out.println("#######################################");
		System.out.println("What we espect : 1//2//");
		Participant participant1 = new Participant(1,2);
		ParticipantList participants = new ParticipantList();
		participants.add(participant1);	
		System.out.print("result : ");
		System.out.println(participants.toString());
	}
	
	public static void testParticipantListFromString(){
		System.out.println("#####################################");
		System.out.println("## TEST ParticipantListFromString  ##");
		System.out.println("#####################################");
		System.out.println("What we espect : 1//2//cacahuete;;pikatchou:::3//4//patate;;cornichon");
		ParticipantList participants = new ParticipantList();
		participants.fromString("1//2//cacahuete;;pikatchou:::3//4//patate;;cornichon");
		System.out.print("result : ");
		System.out.println(participants.toString());
	}
	
	public static void testParticipantHasTheResource(){
		System.out.println("######################################");
		System.out.println("## TEST ParticipantHasThe Resource  ##");
		System.out.println("######################################");
		System.out.println("Test 1 : ");
		System.out.println("What we espect : Participant has the resource");
		Participant participant ;
		participant = new Participant(1,2);
		participant.addToResourcesList("cacahuete");
		participant.addToResourcesList("pikatchou");
		participant.addToResourcesList("patate");
		participant.addToResourcesList("cornichon");
		if (participant.hasTheResource("patate")){
			System.out.print("result : ");
			System.out.println(" Participant has the resource \n");
		} else {
			System.out.print("result : ");
			System.out.println(" Participant doesn't have the resource \n");
		}
		System.out.println("Test 2  : ");
		System.out.println("What we espect : Participant doesn't have the resource");
		if (participant.hasTheResource("Mayo")){
			System.out.print("result : ");
			System.out.println(" Participant has the resource /n");
		} else {
			System.out.print("result : ");
			System.out.println(" Participant doesn't have the resource /n");
		}
	}
	
}

