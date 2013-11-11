package Test;

import java.net.DatagramSocket;
import java.net.SocketException;

import DatagramCommunication.CommunicationMessage;
import DatagramCommunication.DatagramCommunication;


public class TestDatagramCommunication {
	
	public static void runTest() {
		System.out.println("#################################");
		System.out.println("## TEST DATAGRAM COMMUNICATION ##");
		System.out.println("#################################");
		testDatagramCommunication_1();
		System.out.println("############################");
	}

	public static void testDatagramCommunication_1() {
		System.out.println("Test n°1");
		System.out.println("Description : Test send and retreive");
		System.out.println("Description : Sending a small message");
		try {
			DatagramSocket s1 = new DatagramSocket(0);
			String messageToSend = "I am the small message";
			TestDatagramCommunicationThread t = new TestDatagramCommunicationThread(s1.getLocalAddress(),s1.getLocalPort(),messageToSend);
			t.start();
			CommunicationMessage messageRead = DatagramCommunication.retrieveMessage(s1);
			
			String attendu = messageToSend;
			String obtenu = messageRead.getMessage();
			System.out.println("Attendu : " + attendu);
			System.out.println("Obtenu  : " + obtenu);
			
			if (attendu.equals(obtenu)) {
				System.out.println("Test OK");
			}
			else {
				System.out.println("Test failed");
			}

		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static void testDatagramCommunication_2() {
		System.out.println("Test n°2");
		System.out.println("Description : Test ");
		System.out.println("Description : ");

	}	public static void testDatagramCommunication_3() {
		System.out.println("Test n°1");
		System.out.println("Description : Test ");
		System.out.println("Description : ");

	}	public static void testDatagramCommunication_4() {
		System.out.println("Test n°1");
		System.out.println("Description : Test ");
		System.out.println("Description : ");

	}	public static void testDatagramCommunication_5() {
		System.out.println("Test n°1");
		System.out.println("Description : Test ");
		System.out.println("Description : ");

	}	public static void testDatagramCommunication_6() {
		System.out.println("Test n°1");
		System.out.println("Description : Test ");
		System.out.println("Description : ");

	}	public static void testDatagramCommunication_7() {
		System.out.println("Test n°1");
		System.out.println("Description : Test ");
		System.out.println("Description : ");

	}	public static void testDatagramCommunication_8() {
		System.out.println("Test n°1");
		System.out.println("Description : Test ");
		System.out.println("Description : ");
	}
}