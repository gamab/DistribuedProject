package Test;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;

import CriticalResources.CRWaitingList;
import CriticalResources.CRWaitingListCell;
import DatagramCommunication.DatagramCommunication;
import Participant.Participant;
import Participant.ParticipantList;
import Sources.LogicalClock;
import Sources.ServiceThread;

public class TestServiceThread {
	public static void runTest() {
		System.out.println("########################");
		System.out.println("## TEST SERVICETHREAD ##");
		System.out.println("########################");
		testCRWaitingList_1();
		System.out.println("############################");
		testCRWaitingList_2();
		System.out.println("############################");
		testCRWaitingList_3();
		System.out.println("############################");
		testCRWaitingList_4();
		System.out.println("############################");
		testCRWaitingList_5();
		System.out.println("############################");
	}

	public static ServiceThread buildServiceThread(DatagramSocket s) {
		CRWaitingList crWaitingList = new CRWaitingList();
		CRWaitingListCell cell1 = new CRWaitingListCell(1900,20);
		CRWaitingListCell cell2 = new CRWaitingListCell(1910,22);
		crWaitingList.add(cell1);
		crWaitingList.add(cell2);
		
		String[] resources = {"A1","B2"};
	
		ArrayList<String> resource1 = new ArrayList<String>();
		resource1.add("A1");
		resource1.add("B2");
		ArrayList<String> resource2 = new ArrayList<String>();
		resource2.add("A2");
		resource2.add("B3");
		
		ParticipantList participants = new ParticipantList();
		Participant p1 = new Participant(1900, s.getLocalPort(),resource1);
		Participant p2 = new Participant(1910, 4923,resource2);
		participants.add(p1);
		participants.add(p2);
		
		int pid = 1900;
		LogicalClock clock = new LogicalClock();

		ServiceThread service = new ServiceThread(s, crWaitingList,resources,participants,pid,clock);
		return service;	
	}
	
	public static void testCRWaitingList_1() {
		System.out.println("Test n°1");
		System.out.println("Description : Test");
		System.out.println("Description : GET_RESOURCE 1 posible");

		DatagramSocket serviceSocket;
		DatagramSocket s1;
		try {
			serviceSocket = new DatagramSocket(0);
			s1 = new DatagramSocket(0);
			ServiceThread serviceT = buildServiceThread(serviceSocket);
			serviceT.start();
			String message = "GET_RESOURCE<<A1<<20<<";
			DatagramCommunication.sendMessage(message, s1, serviceSocket.getLocalAddress(), serviceSocket.getLocalPort());
			String attendu = "OK_IT_IS_YOURS<<21<<";
			String obtenu = DatagramCommunication.retrieveMessage(s1).getMessage();
			System.out.println("Attendu : " + attendu);
			System.out.println("Obtenu  : " + obtenu);

			if (attendu.equals(obtenu)) {
				System.out.println("Test OK");
			}
			else {
				System.out.println("Test failed");
			}
			
			serviceT.stop();
			serviceSocket.close();
			s1.close();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void testCRWaitingList_2() {
		System.out.println("Test n°2");
		System.out.println("Description : Test");
		System.out.println("Description : GET_RESOURCE imposible");

		DatagramSocket serviceSocket;
		DatagramSocket s1;
		try {
			serviceSocket = new DatagramSocket(0);
			s1 = new DatagramSocket(0);
			ServiceThread serviceT = buildServiceThread(serviceSocket);
			serviceT.start();
			String message = "GET_RESOURCE<<B3<<20<<";
			DatagramCommunication.sendMessage(message, s1, serviceSocket.getLocalAddress(), serviceSocket.getLocalPort());
			String attendu = "I_DO_NOT_HAVE_IT<<21<<";
			String obtenu = DatagramCommunication.retrieveMessage(s1).getMessage();
			System.out.println("Attendu : " + attendu);
			System.out.println("Obtenu  : " + obtenu);

			if (attendu.equals(obtenu)) {
				System.out.println("Test OK");
			}
			else {
				System.out.println("Test failed");
			}
			
			serviceT.stop();
			serviceSocket.close();
			s1.close();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void testCRWaitingList_3() {
		System.out.println("Test n°3");
		System.out.println("Description : Test");
		System.out.println("Description : GET_RESOURCE 2 posibles");

		DatagramSocket serviceSocket;
		DatagramSocket s1;
		try {
			serviceSocket = new DatagramSocket(0);
			s1 = new DatagramSocket(0);
			ServiceThread serviceT = buildServiceThread(serviceSocket);
			serviceT.start();
			String message1 = "GET_RESOURCE<<A1<<20<<";
			DatagramCommunication.sendMessage(message1, s1, serviceSocket.getLocalAddress(), serviceSocket.getLocalPort());
			DatagramCommunication.retrieveMessage(s1);
			String message2 = "GET_RESOURCE<<B2<<22<<";
			DatagramCommunication.sendMessage(message2, s1, serviceSocket.getLocalAddress(), serviceSocket.getLocalPort());
			String attendu = "OK_IT_IS_YOURS<<23<<";
			String obtenu =  DatagramCommunication.retrieveMessage(s1).getMessage();

			System.out.println("Attendu : " + attendu);
			System.out.println("Obtenu  : " + obtenu);

			if (attendu.equals(obtenu)) {
				System.out.println("Test OK");
			}
			else {
				System.out.println("Test failed");
			}
			
			serviceT.stop();
			serviceSocket.close();
			s1.close();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void testCRWaitingList_4() {
		System.out.println("Test n°4");
		System.out.println("Description : Test");
		System.out.println("Description : GET_RESOURCE 2 posibles from two different sources.\n\t\tIt allows us to see if the service thread is setting is clock properly");

		DatagramSocket serviceSocket;
		DatagramSocket s1;
		try {
			serviceSocket = new DatagramSocket(0);
			s1 = new DatagramSocket(0);
			ServiceThread serviceT = buildServiceThread(serviceSocket);
			serviceT.start();
			String message1 = "GET_RESOURCE<<A1<<20<<";
			DatagramCommunication.sendMessage(message1, s1, serviceSocket.getLocalAddress(), serviceSocket.getLocalPort());
			DatagramCommunication.retrieveMessage(s1);
			String message2 = "GET_RESOURCE<<B2<<10<<";
			DatagramCommunication.sendMessage(message2, s1, serviceSocket.getLocalAddress(), serviceSocket.getLocalPort());
			String attendu = "OK_IT_IS_YOURS<<22<<";
			String obtenu =  DatagramCommunication.retrieveMessage(s1).getMessage();

			System.out.println("Attendu : " + attendu);
			System.out.println("Obtenu  : " + obtenu);

			if (attendu.equals(obtenu)) {
				System.out.println("Test OK");
			}
			else {
				System.out.println("Test failed");
			}
			
			serviceT.stop();
			serviceSocket.close();
			s1.close();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public static void testCRWaitingList_5() {
		System.out.println("Test n°5");
		System.out.println("Description : Test");
		System.out.println("Description : RELEASE_RESOURCE 1 posible");

		DatagramSocket serviceSocket;
		DatagramSocket s1;
		try {
			serviceSocket = new DatagramSocket(0);
			s1 = new DatagramSocket(0);
			ServiceThread serviceT = buildServiceThread(serviceSocket);
			serviceT.start();
			String message1 = "RELEASE_RESOURCE<<A1<<20<<";
			DatagramCommunication.sendMessage(message1, s1, serviceSocket.getLocalAddress(), serviceSocket.getLocalPort());
			String attendu = "OK_IT_IS_NOT_YOURS_ANYMORE<<21<<";
			String obtenu =  DatagramCommunication.retrieveMessage(s1).getMessage();

			System.out.println("Attendu : " + attendu);
			System.out.println("Obtenu  : " + obtenu);

			if (attendu.equals(obtenu)) {
				System.out.println("Test OK");
			}
			else {
				System.out.println("Test failed");
			}
			
			serviceT.stop();
			serviceSocket.close();
			s1.close();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
