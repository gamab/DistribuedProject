package Test;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.ListIterator;

import DatagramCommunication.CommunicationMessage;
import DatagramCommunication.DatagramCommunication;
import Participant.Participant;
import Participant.ParticipantList;


public class TestDatagramCommunication {

	public static void runTest() {
		try {
			System.out.println("#################################");
			System.out.println("## TEST DATAGRAM COMMUNICATION ##");
			System.out.println("#################################");
			testDatagramCommunication_1();
			System.out.println("############################");
			testDatagramCommunication_2();
			System.out.println("############################");
			testDatagramCommunication_3();
			System.out.println("############################");
			testDatagramCommunication_4();
			System.out.println("############################");
			testDatagramCommunication_5();
			System.out.println("############################");
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	public static void testDatagramCommunication_4() throws UnknownHostException {
		System.out.println("Test n°4");
		System.out.println("Description : Test send and retreive message to multiple pids");
		System.out.println("Description : Sending a small message");
		try {
			DatagramSocket s1 = new DatagramSocket(0);
			int port1 = 50000;
			int port2 = 54385;
			ArrayList<Integer> listPids = new ArrayList();
			ParticipantList participants = new ParticipantList();
			Participant participant1 = new Participant(2,port1,s1.getLocalAddress());
			Participant participant2 = new Participant(3,port2,s1.getLocalAddress());
			listPids.add(participant1.getPid());
			listPids.add(participant2.getPid());
			participants.add(participant1);
			participants.add(participant2);
			ArrayList<CommunicationMessage> messageRead = new ArrayList();

			String messageToSend = "PONG";
			TestDatagramCommunicationThread2 t1 = new TestDatagramCommunicationThread2(port1, messageToSend + "1");
			TestDatagramCommunicationThread2 t2 = new TestDatagramCommunicationThread2(port2, messageToSend + "2");
			t1.start();
			t2.start();
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			messageRead = DatagramCommunication.sendAndRetreivedMessagesToMultipleProc("PING", s1, listPids, participants);

			String attendu = messageToSend + "1-" + messageToSend + "2-"; 
			String obtenu = new String();
			ListIterator<CommunicationMessage> it = messageRead.listIterator();
			while (it.hasNext()) {
				obtenu += it.next().getMessage() + "-";
			}

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

	public static void testDatagramCommunication_5() throws UnknownHostException {
		System.out.println("Test n°5");
		System.out.println("Description : Test send and retreive message to one pid");
		System.out.println("Description : Sending a small message");
		try {
			DatagramSocket s1 = new DatagramSocket(0);
			int port1 = 50000;
			ArrayList<Integer> listPids = new ArrayList();
			ParticipantList participants = new ParticipantList();
			Participant participant1 = new Participant(2,port1,s1.getLocalAddress());
			listPids.add(participant1.getPid());
			participants.add(participant1);
			ArrayList<CommunicationMessage> messageRead = new ArrayList();

			String messageToSend = "PONG";
			TestDatagramCommunicationThread2 t1 = new TestDatagramCommunicationThread2(port1, messageToSend + "1");
			t1.start();
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			messageRead = DatagramCommunication.sendAndRetreivedMessagesToMultipleProc("PING", s1, listPids, participants);

			String attendu = messageToSend + "1-"; 
			String obtenu = new String();
			ListIterator<CommunicationMessage> it = messageRead.listIterator();
			while (it.hasNext()) {
				obtenu += it.next().getMessage() + "-";
			}

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