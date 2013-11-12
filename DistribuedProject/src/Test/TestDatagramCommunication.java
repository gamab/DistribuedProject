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
		testDatagramCommunication_2();
		System.out.println("############################");
		testDatagramCommunication_3();
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
			s1.close();

		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static void testDatagramCommunication_2() {
		System.out.println("Test n°2");	
		System.out.println("Description : Test send and retreive");
		System.out.println("Description : Sending a big message");
		try {
			DatagramSocket s1 = new DatagramSocket(0);
			String messageToSend = "I am the big message... Why am i so big ? Let say gabriel, my creator had so much to say that he forgot to shut up.";
			messageToSend += "If you want him to talk this is great for you... But me? Why does it have to be me? I feel so big right now!";
			messageToSend += "If you were a message you'd understand!";
			messageToSend += "Look he is even putting letters repetidly just to make me even bigger...";
			messageToSend += "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
			messageToSend += "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
			messageToSend += "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
			messageToSend += "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
			messageToSend += "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
			messageToSend += ". You'd think he'd be tired of writting and would stop but no! More letters : ";
			messageToSend += "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb";
			messageToSend += "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb";
			messageToSend += "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb";
			messageToSend += "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb";
			messageToSend += "And when he is done... He is copying the message. Such a \"@&!/de}}\"... ";
			messageToSend += "If you want him to talk this is great for you... But me? Why does it have to be me? I feel so big right now!";
			messageToSend += "If you were a message you'd understand!";
			messageToSend += "Look he is even putting letters repetidly just to make me even bigger...";
			messageToSend += "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
			messageToSend += "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
			messageToSend += "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
			messageToSend += "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
			messageToSend += "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
			messageToSend += ". You'd think he'd be tired of writting and would stop but no! More letters : ";
			messageToSend += "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb";
			messageToSend += "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb";
			messageToSend += "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb";
			messageToSend += "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb";
			System.out.println("Message length = " + messageToSend.length());
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
			s1.close();

		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}	
	public static void testDatagramCommunication_3() {
		System.out.println("Test n°3");

		System.out.println("Description : Test send and retreive");
		System.out.println("Description : Sending a message empty");
		try {
			DatagramSocket s1 = new DatagramSocket(0);
			String messageToSend = new String();
			System.out.println("Message length = " + messageToSend.length());
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
			s1.close();

		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}