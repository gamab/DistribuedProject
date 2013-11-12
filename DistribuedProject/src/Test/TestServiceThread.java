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
		testCRWaitingList_6();
		System.out.println("############################");
		testCRWaitingList_7();
		System.out.println("############################");
		testCRWaitingList_8();
		System.out.println("############################");
		testCRWaitingList_9();
		System.out.println("############################");
		testCRWaitingList_10();
		System.out.println("############################");
		testCRWaitingList_11();
		System.out.println("############################");
		testCRWaitingList_12();
		System.out.println("############################");
		testCRWaitingList_13();
		System.out.println("############################");
		testCRWaitingList_14();
		System.out.println("############################");
		testCRWaitingList_15();
		System.out.println("############################");
		testCRWaitingList_16();
		System.out.println("############################");
		testCRWaitingList_20();
		System.out.println("############################");
		testCRWaitingList_21();
		System.out.println("############################");
	}

	public static ServiceThread buildServiceThread(DatagramSocket s) {
		CRWaitingList crWaitingList1 = new CRWaitingList();
		CRWaitingListCell cell1 = new CRWaitingListCell(1900,20);
		CRWaitingListCell cell2 = new CRWaitingListCell(1910,22);
		crWaitingList1.add(cell1);
		crWaitingList1.add(cell2);
		CRWaitingList crWaitingList2 = new CRWaitingList();
		CRWaitingListCell cell3 = new CRWaitingListCell(1920,30);
		CRWaitingListCell cell4 = new CRWaitingListCell(1930,40);
		crWaitingList2.add(cell3);
		crWaitingList2.add(cell4);
		CRWaitingList[] crWaitingLists = {crWaitingList1,crWaitingList2};

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

		ServiceThread service = new ServiceThread(s, crWaitingLists,resources,participants,pid,clock);
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
			String message1 = "FREE_RESOURCE<<A1<<20<<";
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

	public static void testCRWaitingList_6() {
		System.out.println("Test n°6");
		System.out.println("Description : Test");
		System.out.println("Description : RELEASE_RESOURCE 1 imposible");

		DatagramSocket serviceSocket;
		DatagramSocket s1;
		try {
			serviceSocket = new DatagramSocket(0);
			s1 = new DatagramSocket(0);
			ServiceThread serviceT = buildServiceThread(serviceSocket);
			serviceT.start();
			String message1 = "FREE_RESOURCE<<A9<<20<<";
			DatagramCommunication.sendMessage(message1, s1, serviceSocket.getLocalAddress(), serviceSocket.getLocalPort());
			String attendu = "I_DO_NOT_HAVE_IT<<21<<";
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

	public static void testCRWaitingList_7() {
		System.out.println("Test n°7");
		System.out.println("Description : Test");
		System.out.println("Description : JOIN posible");

		DatagramSocket serviceSocket;
		DatagramSocket s1;
		try {
			serviceSocket = new DatagramSocket(0);
			s1 = new DatagramSocket(0);
			ServiceThread serviceT = buildServiceThread(serviceSocket);
			serviceT.start();
			Participant participant ;
			participant = new Participant(1902,24932);
			participant.addToResourcesList("A8");
			participant.addToResourcesList("B9");
			participant.addToResourcesList("C10");
			participant.addToResourcesList("D11");
			String message1 = "JOIN<<"+participant.toString()+"<<20<<";
			DatagramCommunication.sendMessage(message1, s1, serviceSocket.getLocalAddress(), serviceSocket.getLocalPort());
			String attendu = "OK_I_ADD_YOU<<21<<";
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
	public static void testCRWaitingList_8() {
		System.out.println("Test n°8");
		System.out.println("Description : Test");
		System.out.println("Description : JOIN imposible");

		DatagramSocket serviceSocket;
		DatagramSocket s1;
		try {
			serviceSocket = new DatagramSocket(0);
			s1 = new DatagramSocket(0);
			ServiceThread serviceT = buildServiceThread(serviceSocket);
			serviceT.start();
			String message1 = "JOIN<<"+"WIFI"+"<<20<<";
			DatagramCommunication.sendMessage(message1, s1, serviceSocket.getLocalAddress(), serviceSocket.getLocalPort());
			String attendu = "BAD_FORMAT<<21<<";
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

	public static void testCRWaitingList_9() {
		System.out.println("Test n°9");
		System.out.println("Description : Test");
		System.out.println("Description : GET_PARTICIPANTS posible");

		DatagramSocket serviceSocket;
		DatagramSocket s1;
		try {
			serviceSocket = new DatagramSocket(0);
			s1 = new DatagramSocket(0);
			ServiceThread serviceT = buildServiceThread(serviceSocket);
			serviceT.start();
			String message1 = "GET_PARTICIPANTS"+"<<20<<";
			DatagramCommunication.sendMessage(message1, s1, serviceSocket.getLocalAddress(), serviceSocket.getLocalPort());
			String attendu = "PARTICIPANTS<<1900//"+ serviceSocket.getLocalPort() +"//A1;;B2:::1910//4923//A2;;B3<<21<<";
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

	public static void testCRWaitingList_10() {
		System.out.println("Test n°10");
		System.out.println("Description : Test");
		System.out.println("Description : GET_PARTICIPANTS posible after a JOIN.\n\t\tTo check if the join worked");

		DatagramSocket serviceSocket;
		DatagramSocket s1;
		try {
			serviceSocket = new DatagramSocket(0);
			s1 = new DatagramSocket(0);
			ServiceThread serviceT = buildServiceThread(serviceSocket);
			serviceT.start();
			Participant participant ;
			participant = new Participant(1902,24932);
			participant.addToResourcesList("A8");
			participant.addToResourcesList("B9");
			participant.addToResourcesList("C10");
			participant.addToResourcesList("D11");
			String message1 = "JOIN<<"+participant.toString()+"<<20<<";
			DatagramCommunication.sendMessage(message1, s1, serviceSocket.getLocalAddress(), serviceSocket.getLocalPort());
			DatagramCommunication.retrieveMessage(s1);
			String message2 = "GET_PARTICIPANTS"+"<<22<<";
			DatagramCommunication.sendMessage(message2, s1, serviceSocket.getLocalAddress(), serviceSocket.getLocalPort());
			String attendu = "PARTICIPANTS<<1900//"+ serviceSocket.getLocalPort() +"//A1;;B2:::1910//4923//A2;;B3:::1902//24932//A8;;B9;;C10;;D11<<23<<";
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

	public static void testCRWaitingList_11() {
		System.out.println("Test n°11");
		System.out.println("Description : Test");
		System.out.println("Description : GET_CRITICAL_REGION posible");

		DatagramSocket serviceSocket;
		DatagramSocket s1;
		try {
			serviceSocket = new DatagramSocket(0);
			s1 = new DatagramSocket(0);
			ServiceThread serviceT = buildServiceThread(serviceSocket);
			serviceT.start();

			CRWaitingListCell cell = new CRWaitingListCell(1902, 45);

			String message = "GET_CRITICAL_REGION<<1<<"+cell.toString()+"<<45<<";
			DatagramCommunication.sendMessage(message, s1, serviceSocket.getLocalAddress(), serviceSocket.getLocalPort());
			String attendu = "OK<<46<<";
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

	public static void testCRWaitingList_12() {
		System.out.println("Test n°12");
		System.out.println("Description : Test");
		System.out.println("Description : GET_CRITICAL_REGION imposible because it is the third of two CR");

		DatagramSocket serviceSocket;
		DatagramSocket s1;
		try {
			serviceSocket = new DatagramSocket(0);
			s1 = new DatagramSocket(0);
			ServiceThread serviceT = buildServiceThread(serviceSocket);
			serviceT.start();

			CRWaitingListCell cell = new CRWaitingListCell(1902, 45);

			String message = "GET_CRITICAL_REGION<<2<<"+cell.toString()+"<<45<<";
			DatagramCommunication.sendMessage(message, s1, serviceSocket.getLocalAddress(), serviceSocket.getLocalPort());
			String attendu = "BAD_FORMAT<<46<<";
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
	
	public static void testCRWaitingList_13() {
		System.out.println("Test n°13");
		System.out.println("Description : Test");
		System.out.println("Description : GET_CRITICAL_REGION imposible because we are not giving a proper WaitingListCell");

		DatagramSocket serviceSocket;
		DatagramSocket s1;
		try {
			serviceSocket = new DatagramSocket(0);
			s1 = new DatagramSocket(0);
			ServiceThread serviceT = buildServiceThread(serviceSocket);
			serviceT.start();

			String message = "GET_CRITICAL_REGION<<1<<CELL_POORLY_MADE<<45<<";
			DatagramCommunication.sendMessage(message, s1, serviceSocket.getLocalAddress(), serviceSocket.getLocalPort());
			String attendu = "BAD_FORMAT<<46<<";
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

	public static void testCRWaitingList_14() {
		System.out.println("Test n°14");
		System.out.println("Description : Test");
		System.out.println("Description : FREE_CRITICAL_REGION posible (we add a cell and then delete it)");

		DatagramSocket serviceSocket;
		DatagramSocket s1;
		try {
			serviceSocket = new DatagramSocket(0);
			s1 = new DatagramSocket(0);
			ServiceThread serviceT = buildServiceThread(serviceSocket);
			serviceT.start();

			CRWaitingListCell cell = new CRWaitingListCell(1902, 45);

			String message = "GET_CRITICAL_REGION<<1<<"+cell.toString()+"<<45<<";
			DatagramCommunication.sendMessage(message, s1, serviceSocket.getLocalAddress(), serviceSocket.getLocalPort());
			DatagramCommunication.retrieveMessage(s1);
			String message2 = "FREE_CRITICAL_REGION<<1<<"+cell.toString()+"<<47<<";
			DatagramCommunication.sendMessage(message2, s1, serviceSocket.getLocalAddress(), serviceSocket.getLocalPort());
			
			String attendu = "OK<<48<<";
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

	public static void testCRWaitingList_15() {
		System.out.println("Test n°15");
		System.out.println("Description : Test");
		System.out.println("Description : FREE_CRITICAL_REGION imposible because it is the third of two CR");

		DatagramSocket serviceSocket;
		DatagramSocket s1;
		try {
			serviceSocket = new DatagramSocket(0);
			s1 = new DatagramSocket(0);
			ServiceThread serviceT = buildServiceThread(serviceSocket);
			serviceT.start();

			CRWaitingListCell cell = new CRWaitingListCell(1902, 45);

			String message = "GET_CRITICAL_REGION<<1<<"+cell.toString()+"<<45<<";
			DatagramCommunication.sendMessage(message, s1, serviceSocket.getLocalAddress(), serviceSocket.getLocalPort());
			DatagramCommunication.retrieveMessage(s1);
			String message2 = "FREE_CRITICAL_REGION<<2<<"+cell.toString()+"<<47<<";
			DatagramCommunication.sendMessage(message2, s1, serviceSocket.getLocalAddress(), serviceSocket.getLocalPort());
			
			String attendu = "BAD_FORMAT<<48<<";
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
	
	public static void testCRWaitingList_16() {
		System.out.println("Test n°16");
		System.out.println("Description : Test");
		System.out.println("Description : FREE_CRITICAL_REGION imposible because we are not giving a proper WaitingListCell");

		DatagramSocket serviceSocket;
		DatagramSocket s1;
		try {
			serviceSocket = new DatagramSocket(0);
			s1 = new DatagramSocket(0);
			ServiceThread serviceT = buildServiceThread(serviceSocket);
			serviceT.start();

			String message = "FREE_CRITICAL_REGION<<1<<CELL_POORLY_MADE<<45<<";
			DatagramCommunication.sendMessage(message, s1, serviceSocket.getLocalAddress(), serviceSocket.getLocalPort());
			String attendu = "BAD_FORMAT<<46<<";
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

	public static void testCRWaitingList_20() {
		System.out.println("Test n°20");
		System.out.println("Description : Test");
		System.out.println("Description : Imposible request with good clock");

		DatagramSocket serviceSocket;
		DatagramSocket s1;
		try {
			serviceSocket = new DatagramSocket(0);
			s1 = new DatagramSocket(0);
			ServiceThread serviceT = buildServiceThread(serviceSocket);
			serviceT.start();
			String message1 = "BOOM<<20<<";
			DatagramCommunication.sendMessage(message1, s1, serviceSocket.getLocalAddress(), serviceSocket.getLocalPort());
			String attendu = "BAD_REQUEST";
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

	public static void testCRWaitingList_21() {
		System.out.println("Test n°21");
		System.out.println("Description : Test");
		System.out.println("Description : Request without clock");

		DatagramSocket serviceSocket;
		DatagramSocket s1;
		try {
			serviceSocket = new DatagramSocket(0);
			s1 = new DatagramSocket(0);
			ServiceThread serviceT = buildServiceThread(serviceSocket);
			serviceT.start();
			String message1 = "BOOM";
			DatagramCommunication.sendMessage(message1, s1, serviceSocket.getLocalAddress(), serviceSocket.getLocalPort());
			String attendu = "COULD_NOT_GET_CLOCK<<0<<";
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
