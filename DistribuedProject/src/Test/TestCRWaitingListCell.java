package Test;

import CriticalResources.CRWaitingListCell;
import Sources.Participant;


public class TestCRWaitingListCell {
	public static void runTest() {
		System.out.println("############################");
		System.out.println("## TEST CRWaitingListCell ##");
		System.out.println("############################");
		testCRWaitingListCell_1();
		System.out.println("############################");
		testCRWaitingListCell_2();
		System.out.println("############################");
		testCRWaitingListCell_3();
		System.out.println("############################");
		testCRWaitingListCell_4();
		System.out.println("############################");
		testCRWaitingListCell_5();
		System.out.println("############################");
		testCRWaitingListCell_6();
		System.out.println("############################");
		testCRWaitingListCell_7();
		System.out.println("############################");
		testCRWaitingListCell_8();
		System.out.println("############################");
		testCRWaitingListCell_9();
		System.out.println("############################");
	}

	public static void testCRWaitingListCell_1() {
		System.out.println("Test n°1");
		System.out.println("Description : Test toString");
		System.out.println("Description : checking if the function applied to a cell with the pid to 110 and the clock to 220 returns what we expect");
		
		
		CRWaitingListCell cell = new CRWaitingListCell(110,220);
		String attendu = "110//220";
		String obtenu = cell.toString();
		System.out.println("Attendu : " + attendu);
		System.out.println("Obtenu  : " + obtenu);
		
		if (attendu.equals(obtenu)) {
			System.out.println("Test OK");
		}
		else {
			System.out.println("Test failed");
		}

	}

	public static void testCRWaitingListCell_2() {
		System.out.println("Test n°2");
		System.out.println("Description : Test fromString");
		System.out.println("Description : checking if retrieving if function fromString applied to \"110//220\" sets the pid to 110 and the clock to 220");
		
		
		CRWaitingListCell cell = new CRWaitingListCell();
		String cellString = "110//220";
		cell.fromString(cellString);
		String attendu = "pid=110 & clock=220";
		String obtenu = "pid=" + cell.getPid() + " & clock=" + cell.getClock();
		System.out.println("Attendu : " + attendu);
		System.out.println("Obtenu  : " + obtenu);
		
		if (attendu.equals(obtenu)) {
			System.out.println("Test OK");
		}
		else {
			System.out.println("Test failed");
		}
	}	

	public static void testCRWaitingListCell_3() {
		System.out.println("Test n°3");
		System.out.println("Description : Test fromString");
		System.out.println("Description : checking if retrieving if function fromString applied to \"110//szd\" returns False");
		
		
		CRWaitingListCell cell = new CRWaitingListCell();
		String cellString = "110//szd";
		boolean resConvertion = cell.fromString(cellString);
		String attendu = "Convertion worked : false";
		String obtenu = "Convertion worked : " + resConvertion;
		System.out.println("Attendu : " + attendu);
		System.out.println("Obtenu  : " + obtenu);
		
		if (attendu.equals(obtenu)) {
			System.out.println("Test OK");
		}
		else {
			System.out.println("Test failed");
		}
	}	
	
	public static void testCRWaitingListCell_4() {
		System.out.println("Test n°4");
		System.out.println("Description : Test fromString");
		System.out.println("Description : checking if retrieving if function fromString applied to \"110//220\" returns True");
		
		
		CRWaitingListCell cell = new CRWaitingListCell();
		String cellString = "110//220";
		boolean resConvertion = cell.fromString(cellString);
		String attendu = "Convertion worked : true";
		String obtenu = "Convertion worked : " + resConvertion;
		System.out.println("Attendu : " + attendu);
		System.out.println("Obtenu  : " + obtenu);
		
		if (attendu.equals(obtenu)) {
			System.out.println("Test OK");
		}
		else {
			System.out.println("Test failed");
		}
	}	

	public static void testCRWaitingListCell_5() {
		System.out.println("Test n°5");
		System.out.println("Description : Test compare");
		System.out.println("Description : compare two cells");
		
		CRWaitingListCell a = new CRWaitingListCell(10,20);
		CRWaitingListCell b = new CRWaitingListCell(12,21);
		
		String attendu = "Compare (10//20, 12//21) : -1";
		String obtenu = "Compare ("+a.toString()+", "+ b.toString()+") : "+a.compare(a, b);
		System.out.println("Attendu : " + attendu);
		System.out.println("Obtenu  : " + obtenu);
		
		if (attendu.equals(obtenu)) {
			System.out.println("Test OK");
		}
		else {
			System.out.println("Test failed");
		}
		
	}
	
	public static void testCRWaitingListCell_6() {
		System.out.println("Test n°6");
		System.out.println("Description : Test compare");
		System.out.println("Description : compare two cells");
		
		CRWaitingListCell a = new CRWaitingListCell(10,20);
		CRWaitingListCell b = new CRWaitingListCell(12,21);
		
		String attendu = "Compare (12//21, 10//20) : 1";
		String obtenu = "Compare ("+b.toString()+", "+ a.toString()+") : "+a.compare(b, a);
		System.out.println("Attendu : " + attendu);
		System.out.println("Obtenu  : " + obtenu);
		
		if (attendu.equals(obtenu)) {
			System.out.println("Test OK");
		}
		else {
			System.out.println("Test failed");
		}
		
	}
	
	public static void testCRWaitingListCell_7() {
		System.out.println("Test n°7");
		System.out.println("Description : Test compare");
		System.out.println("Description : compare two cells");
		
		CRWaitingListCell a = new CRWaitingListCell(10,20);
		CRWaitingListCell b = new CRWaitingListCell(12,20);
		
		String attendu = "Compare (10//20, 12//20) : -1";
		String obtenu = "Compare ("+a.toString()+", "+ b.toString()+") : "+a.compare(a, b);
		System.out.println("Attendu : " + attendu);
		System.out.println("Obtenu  : " + obtenu);
		
		if (attendu.equals(obtenu)) {
			System.out.println("Test OK");
		}
		else {
			System.out.println("Test failed");
		}
		
	}
	
	public static void testCRWaitingListCell_8() {
		System.out.println("Test n°8");
		System.out.println("Description : Test compare");
		System.out.println("Description : compare two cells");
		
		CRWaitingListCell a = new CRWaitingListCell(10,20);
		CRWaitingListCell b = new CRWaitingListCell(12,20);
		
		String attendu = "Compare (12//20, 10//20) : 1";
		String obtenu = "Compare ("+b.toString()+", "+ a.toString()+") : "+a.compare(b, a);
		System.out.println("Attendu : " + attendu);
		System.out.println("Obtenu  : " + obtenu);
		
		if (attendu.equals(obtenu)) {
			System.out.println("Test OK");
		}
		else {
			System.out.println("Test failed");
		}
		
	}
	
	public static void testCRWaitingListCell_9() {
		System.out.println("Test n°9");
		System.out.println("Description : Test compare");
		System.out.println("Description : compare two cells");
		
		CRWaitingListCell a = new CRWaitingListCell(10,20);
		CRWaitingListCell b = new CRWaitingListCell(10,20);
		
		String attendu = "Compare (10//20, 10//20) : 0";
		String obtenu = "Compare ("+a.toString()+", "+ b.toString()+") : "+a.compare(a, b);
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
