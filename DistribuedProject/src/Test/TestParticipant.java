package Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

import Participant.Participant;
import Participant.ParticipantList;


public class TestParticipant {
	public static void runTest() {
		try {
			testParticipantToString();
			testParticipantFromString();
			testParticipantToStringvide();
			testParticipantFromStringvide();
			testParticipantListToString();
			testParticipantListToStringVide();
			testParticipantListFromString();
			testParticipantHasTheResource();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testParticipantToString() throws Exception{
		System.out.println("###############################");
		System.out.println("## TEST ParticipantToString  ##");
		System.out.println("###############################");
		System.out.println("What we espect : 1//2//192.168.0.112//cacahuete;;pikatchou;;patate;;cornichon");
		Participant participant ;
		InetAddress ip = InetAddress.getByName("192.168.0.112");
		participant = new Participant(1,2,ip);
		participant.addToResourcesList("cacahuete");
		participant.addToResourcesList("pikatchou");
		participant.addToResourcesList("patate");
		participant.addToResourcesList("cornichon");
		System.out.print("result : ");
		System.out.println(participant.toString());
	}

	public static void testParticipantToStringvide() throws Exception{
		System.out.println("###############################");
		System.out.println("## TEST ParticipantToString  ##");
		System.out.println("###############################");
		System.out.println("What we espect : 1//2//192.168.0.112//");
		Participant participant ;
		InetAddress ip = InetAddress.getByName("192.168.0.112");
		participant = new Participant(1,2,ip);
		System.out.print("result : ");
		System.out.println(participant.toString());
	}

	public static void testParticipantFromString(){
		System.out.println("################################");
		System.out.println("## TEST ParticipantFromString ##");
		System.out.println("################################");
		System.out.println("What we espect : 1//2//192.168.0.112//cacahuete;;pikatchou;;patate;;cornichon");
		Participant participant;
		participant = new Participant();
		participant.fromString( "1//2//192.168.0.112//cacahuete;;pikatchou;;patate;;cornichon");
		System.out.print("result : ");
		System.out.println(participant.toString());
	}

	public static void testParticipantFromStringvide(){
		System.out.println("################################");
		System.out.println("## TEST ParticipantFromString ##");
		System.out.println("################################");
		System.out.println("What we espect : 1//2//192.168.0.112//");
		Participant participant = new Participant();
		participant.fromString( "1//2//192.168.0.112//");
		System.out.print("result : ");
		System.out.println(participant.toString());
	}

	public static void testParticipantListToString() throws Exception{
		System.out.println("###################################");
		System.out.println("## TEST ParticipantListToString  ##");
		System.out.println("###################################");
		System.out.println("What we espect : 1//2//192.168.0.112//cacahuete;;pikatchou:::3//4//192.168.0.111//patate;;cornichon");
		InetAddress ip1 = InetAddress.getByName("192.168.0.112");
		Participant participant1 = new Participant(1,2,ip1);
		participant1.addToResourcesList("cacahuete");
		participant1.addToResourcesList("pikatchou");
		InetAddress ip2 = InetAddress.getByName("192.168.0.111");
		Participant participant2 = new Participant(3,4,ip2);
		participant2.addToResourcesList("patate");
		participant2.addToResourcesList("cornichon");
		ParticipantList participants = new ParticipantList();
		participants.add(participant1);
		participants.add(participant2);		
		System.out.print("result : ");
		System.out.println(participants.toString());
	}

	public static void testParticipantListToStringVide() throws Exception{
		System.out.println("#######################################");
		System.out.println("## TEST ParticipantListToStringVide  ##");
		System.out.println("#######################################");
		System.out.println("What we espect : 1//2//192.168.0.112//");
		InetAddress ip1 = InetAddress.getByName("192.168.0.112");
		Participant participant1 = new Participant(1,2,ip1);
		ParticipantList participants = new ParticipantList();
		participants.add(participant1);	
		System.out.print("result : ");
		System.out.println(participants.toString());
	}

	public static void testParticipantListFromString(){
		System.out.println("#####################################");
		System.out.println("## TEST ParticipantListFromString  ##");
		System.out.println("#####################################");
		System.out.println("What we espect : 1//2//192.168.0.112//cacahuete;;pikatchou:::3//4//192.168.0.111//patate;;cornichon");
		ParticipantList participants = new ParticipantList();
		participants.fromString("1//2//192.168.0.112//cacahuete;;pikatchou:::3//4//192.168.0.111//patate;;cornichon");
		System.out.print("result : ");
		System.out.println(participants.toString());
	}

	public static void testParticipantHasTheResource() throws Exception{
		System.out.println("######################################");
		System.out.println("## TEST ParticipantHasThe Resource  ##");
		System.out.println("######################################");
		System.out.println("Test 1 : ");
		System.out.println("What we espect : Participant has the resource");
		InetAddress ip1 = InetAddress.getByName("192.168.0.112");
		Participant participant = new Participant(1,2,ip1);
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
			System.out.println(" Participant has the resource \n");
		} else {
			System.out.print("result : ");
			System.out.println(" Participant doesn't have the resource \n");
		}
	}

}

