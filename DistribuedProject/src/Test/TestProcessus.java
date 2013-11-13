package Test;

import java.net.SocketException;

import Sources.Processus;

public class TestProcessus {
	public static void runTest() {
		System.out.println("####################");
		System.out.println("## TEST PROCESSUS ##");
		System.out.println("####################");
		testProcessus_3();
		System.out.println("############################");
	}

	public static void testProcessus_1() {
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
	public static void testProcessus_2() {
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
	public static void testProcessus_3() {
		System.out.println("Test n°3");
		System.out.println("Description : Test ");
		System.out.println("Description : ");
		try {
			Processus proc = new Processus(1900);
			TestProcessusThread tProc = new TestProcessusThread(proc);
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
	public static void testProcessus_4() {
		System.out.println("Test n°4");
		System.out.println("Description : Test ");
		System.out.println("Description : ");

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
	}
}
