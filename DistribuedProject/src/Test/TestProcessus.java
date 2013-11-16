package Test;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;

import CriticalResources.CRWaitingList;
import CriticalResources.CRWaitingListCell;
import Participant.Participant;
import Participant.ParticipantList;
import Sources.LogicalClock;
import Sources.Processus;
import Sources.ProcessusThread;
import Sources.ServiceThread;

public class TestProcessus {
	public static void runTest() {
		System.out.println("####################");
		System.out.println("## TEST PROCESSUS ##");
		System.out.println("####################");
		try {
			//		testProcessus_1();
			//		System.out.println("############################");
			//		testProcessus_2();
			//		System.out.println("############################");
			//		testProcessus_3();
			//		System.out.println("############################");
						testProcessus_4();
						System.out.println("############################");
			//		testProcessus_5();
			//		System.out.println("############################");
			//		testProcessus_6();
			//		System.out.println("############################");
			//		testProcessus_7();
			//		System.out.println("############################");
			//		testProcessus_8();
			//		System.out.println("############################");
			testProcessus_9();
			System.out.println("############################");
			testProcessus_10();
			System.out.println("############################");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testProcessus_1() throws Exception {
		System.out.println("Test n°1");
		System.out.println("Description : Test that make does not crash");
		System.out.println("Description : ");

		try {
			Processus proc = new Processus(1900);

			String attendu = "";
			String obtenu = "";
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
	public static void testProcessus_2() throws Exception {
		System.out.println("Test n°2");
		System.out.println("Description : Test run does not crash");
		System.out.println("Description : ");
		try {
			Processus proc = new Processus(1900);
			proc.run();

			String attendu = "";
			String obtenu = "";
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
	public static void testProcessus_3() throws Exception {
		System.out.println("Test n°3");
		System.out.println("Description : Test if launching two processus works");
		System.out.println("Description : ");
		try {
			Processus proc = new Processus(1900);
			ProcessusThread tProc = new ProcessusThread(proc);
			tProc.start();
			Processus proc2 = new Processus(1902);
			proc2.run();

			String attendu = "";
			String obtenu = "";
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
	public static void testProcessus_4() throws Exception {
		System.out.println("Test n°4");
		System.out.println("Description : Test if launching three processus works");
		System.out.println("Description : ");
		try {
			Processus proc = new Processus(1900);
			ProcessusThread tProc = new ProcessusThread(proc);
			tProc.start();
			Processus proc2 = new Processus(1904);
			ProcessusThread tProc2 = new ProcessusThread(proc2);
			tProc2.start();
			Processus proc3 = new Processus(1902);
			proc3.run();

			String attendu = "";
			String obtenu = "";
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


	public static void testProcessus_5() throws Exception {
		System.out.println("Test n°5");
		System.out.println("Description : Test whoHasResource");
		System.out.println("Description : ");
		try {
			Processus proc = new Processus(1900);
			int pid = proc.whoHasResource("a1");
			String result = String.valueOf(pid);

			String attendu = "1900";
			String obtenu = result;

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

	public static void testProcessus_6() throws Exception {
		System.out.println("Test n°6");
		System.out.println("Description : Test whoHasResource with a ressource not inside the resource list");
		System.out.println("Description : ");
		try {
			Processus proc = new Processus(1900);
			int pid = proc.whoHasResource("c1");
			String result = String.valueOf(pid);

			String attendu = "-2";
			String obtenu = result;

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

	public static void testProcessus_7() throws Exception {
		System.out.println("Test n°7");
		System.out.println("Description : Test whoIsHeadOfCRWaitingList with no one in the CRWaitingList");
		System.out.println("Description : ");
		try {
			DatagramSocket s = new DatagramSocket(Processus.portDefault);
			CRWaitingList[] crList = new CRWaitingList[3];
			crList[0] = new CRWaitingList();
			crList[1] = new CRWaitingList();
			crList[2] = new CRWaitingList();
			String[] resources = {"a1","b2"};
			ArrayList<String> resourcesList = new ArrayList<String>();
			for (int i=0; i<resources.length; i++) {
				resourcesList.add(resources[i]);
			}
			ParticipantList participants = new ParticipantList();
			int pid = 1900;
			LogicalClock clock = new LogicalClock();
			Participant first = new Participant(pid,Processus.portDefault,s.getLocalAddress(),resourcesList);
			participants.add(first);
			ServiceThread serviceT = new ServiceThread(s, crList, resources , participants, pid, clock);
			serviceT.start();

			Processus proc = new Processus(1920);
			int pid_r = proc.whoIsHeadOfCRWaitingList(1);
			String result = String.valueOf(pid_r);

			serviceT.stop();
			String attendu = "-2";
			String obtenu = result;

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

//	public static void testProcessus_8() throws Exception {
//		System.out.println("Test n°8");
//		System.out.println("Description : Test whoIsHeadOfCRWaitingList with someone in the CRWaitingList");
//		System.out.println("Description : ATTENTION COPYING WHAT IS INSIDE THE FUNCTION FOR THE TEST. NOT REALLY CALLING THE FUNCTION");
//		try {
//			DatagramSocket s = new DatagramSocket(Processus.portDefault);
//			CRWaitingList[] crList = new CRWaitingList[3];
//			crList[0] = new CRWaitingList();
//			crList[1] = new CRWaitingList();
//			crList[2] = new CRWaitingList();
//			String[] resources = {"a1","b2"};
//			ArrayList<String> resourcesList = new ArrayList<String>();
//			for (int i=0; i<resources.length; i++) {
//				resourcesList.add(resources[i]);
//			}
//			ParticipantList participants = new ParticipantList();
//			int pid = 1900;
//			LogicalClock clock = new LogicalClock();
//			Participant first = new Participant(pid,Processus.portDefault,s.getLocalAddress(),resourcesList);
//			participants.add(first);
//			ServiceThread serviceT = new ServiceThread(s, crList, resources , participants, pid, clock);
//			serviceT.start();
//
//			Processus proc = new Processus(1920);
//			CRWaitingListCell cell = new CRWaitingListCell(1920,proc.getClock().getClock());
//			proc.sendAndRetrieveOneMessage("GET_CRITICAL_REGION<<1<<"+cell+"<<"+ proc.getClock().getClock(),s.getLocalAddress(), Processus.portDefault);
//
//
//			int pid_r = crList[1].get(0).getPid();
//			String result = String.valueOf(pid_r);
//
//			String attendu = "1920";
//			String obtenu = result;
//			serviceT.stop();
//
//			System.out.println("Attendu : " + attendu);
//			System.out.println("Obtenu  : " + obtenu);
//
//			if (attendu.equals(obtenu)) {
//				System.out.println("Test OK");
//			}
//			else {
//				System.out.println("Test failed");
//			}		
//		} catch (SocketException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}		
//	}
	
	public static void testProcessus_9() {
		System.out.println("Test n°9");
		System.out.println("Description : Test of random");
		System.out.println("Description : ");
		
		int random = Processus.randomInRange(0, 10);
		if (random <= 10 && random >= 0) {
			System.out.println("Test OK");
		} else {
			System.out.println("Test failes");
		}
	}
	
	public static void testProcessus_10() {
		System.out.println("Test n°10");
		System.out.println("Description : Test of random");
		System.out.println("Description : ");
		int random = Processus.randomInRange(0, 2);
		int zeros = 0;
		int ones = 0;
		int twos = 0;
		for (int i=0; i<9999999; i++) {
			 random = Processus.randomInRange(0, 2);
			 if (random == 0) {
				 zeros++;
			 }
			 else if (random == 1) {
				 ones++;
			 }
			 else if (random == 2) {
				 twos++;
			 }
		}
		System.out.println("Zeros : " + zeros/(double)(zeros+ones+twos));
		System.out.println("Ones  : " + ones/(double)(zeros+ones+twos));
		System.out.println("Twos  : " + twos/(double)(zeros+ones+twos));
		if (random <= 10 && random >= 0) {
			System.out.println("Test OK");
		} else {
			System.out.println("Test failes");
		}
	}
}