package Test;

import CriticalResources.CRWaitingList;
import CriticalResources.CRWaitingListCell;


public class TestCRWaitingList {
	public static void runTest() {
		System.out.println("########################");
		System.out.println("## TEST CRWaitingList ##");
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
	}

	public static void testCRWaitingList_1() {
		System.out.println("Test n°1");
		System.out.println("Description : Test toString");
		System.out.println("Description : checking if the function applied to a CRWaitingList returns what we expect");
		
		
		CRWaitingListCell cell1 = new CRWaitingListCell(1900,10);
		CRWaitingListCell cell2 = new CRWaitingListCell(1901,20);
		CRWaitingList list = new CRWaitingList();
		list.add(cell1);
		list.add(cell2);
		
		String attendu = "1900//10:::1901//20";
		String obtenu = list.toString();
		System.out.println("Attendu : " + attendu);
		System.out.println("Obtenu  : " + obtenu);
		
		if (attendu.equals(obtenu)) {
			System.out.println("Test OK");
		}
		else {
			System.out.println("Test failed");
		}

	}
	
	public static void testCRWaitingList_2() {
		System.out.println("Test n°2");
		System.out.println("Description : Test toString");
		System.out.println("Description : checking if the function applied to a CRWaitingList returns what we expect inserting two cells the other way around");
		
		
		CRWaitingListCell cell1 = new CRWaitingListCell(1900,10);
		CRWaitingListCell cell2 = new CRWaitingListCell(1901,20);
		CRWaitingList list = new CRWaitingList();
		list.add(cell2);
		list.add(cell1);
		
		String attendu = "1900//10:::1901//20";
		String obtenu = list.toString();
		System.out.println("Attendu : " + attendu);
		System.out.println("Obtenu  : " + obtenu);
		
		if (attendu.equals(obtenu)) {
			System.out.println("Test OK");
		}
		else {
			System.out.println("Test failed");
		}

	}
	
	public static void testCRWaitingList_3() {
		System.out.println("Test n°3");
		System.out.println("Description : Test toString");
		System.out.println("Description : checking if the function applied to a CRWaitingList returns what we expect inserting two cells with the same clock");
		
		
		CRWaitingListCell cell1 = new CRWaitingListCell(1900,10);
		CRWaitingListCell cell2 = new CRWaitingListCell(1901,10);
		CRWaitingList list = new CRWaitingList();
		list.add(cell2);
		list.add(cell1);
		
		String attendu = "1900//10:::1901//10";
		String obtenu = list.toString();
		System.out.println("Attendu : " + attendu);
		System.out.println("Obtenu  : " + obtenu);
		
		if (attendu.equals(obtenu)) {
			System.out.println("Test OK");
		}
		else {
			System.out.println("Test failed");
		}

	}
	
	public static void testCRWaitingList_4() {
		System.out.println("Test n°4");
		System.out.println("Description : Test toString");
		System.out.println("Description : checking if the function applied to a CRWaitingList returns what we expect inserting two cells with the same clock the other way around");
		
		
		CRWaitingListCell cell1 = new CRWaitingListCell(1900,10);
		CRWaitingListCell cell2 = new CRWaitingListCell(1901,10);
		CRWaitingList list = new CRWaitingList();
		list.add(cell1);
		list.add(cell2);
		
		String attendu = "1900//10:::1901//10";
		String obtenu = list.toString();
		System.out.println("Attendu : " + attendu);
		System.out.println("Obtenu  : " + obtenu);
		
		if (attendu.equals(obtenu)) {
			System.out.println("Test OK");
		}
		else {
			System.out.println("Test failed");
		}

	}

	public static void testCRWaitingList_5() {
		System.out.println("Test n°5");
		System.out.println("Description : Test fromString");
		System.out.println("Description : checking if retrieving with function fromString applied to the list retrieves everything well");
		
		
		CRWaitingList list = new CRWaitingList();
		String listString = "1900//10:::1901//20";
		String attendu = "0 : pid=1900 & clock=10 // 1 : pid=1901 & clock=20";
		list.fromString(listString);
		
		String obtenu = "0 : pid=" + list.get(0).getPid() + " & clock=" + list.get(0).getClock();
		obtenu += " // 1 : pid=" + list.get(1).getPid() + " & clock=" + list.get(1).getClock();
		System.out.println("Attendu : " + attendu);
		System.out.println("Obtenu  : " + obtenu);
		
		if (attendu.equals(obtenu)) {
			System.out.println("Test OK");
		}
		else {
			System.out.println("Test failed");
		}
	}	
	
	public static void testCRWaitingList_6() {
		System.out.println("Test n°6");
		System.out.println("Description : Test fromString");
		System.out.println("Description : checking if retrieving with function fromString in a list that is not empty works well");
		
		
		CRWaitingList list = new CRWaitingList();
		CRWaitingListCell cell = new CRWaitingListCell(1900,30);
		list.add(cell);
		
		String listString = "1900//10:::1901//20";
		String attendu = "0 : pid=1900 & clock=10 // 1 : pid=1901 & clock=20";
		list.fromString(listString);
		
		String obtenu = "0 : pid=" + list.get(0).getPid() + " & clock=" + list.get(0).getClock();
		obtenu += " // 1 : pid=" + list.get(1).getPid() + " & clock=" + list.get(1).getClock();
		System.out.println("Attendu : " + attendu);
		System.out.println("Obtenu  : " + obtenu);
		
		if (attendu.equals(obtenu)) {
			System.out.println("Test OK");
		}
		else {
			System.out.println("Test failed");
		}
	}	
	
	public static void testCRWaitingList_7() {
		System.out.println("Test n°7");
		System.out.println("Description : Test fromString");
		System.out.println("Description : checking if retrieving with function fromString applied to an empty string does not work");
		
		
		CRWaitingList list = new CRWaitingList();
		String listString = new String();
		boolean resConvertion = list.fromString(listString);
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
	
	public static void testCRWaitingList_8() {
		System.out.println("Test n°8");
		System.out.println("Description : Test fromString");
		System.out.println("Description : checking if retrieving with function fromString applied to a String that is not meant to be CRWaitingList does not work");
		
		
		CRWaitingList list = new CRWaitingList();
		String listString = "1900//10:::aze//20";
		boolean resConvertion = list.fromString(listString);
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
	
	public static void testCRWaitingList_9() {
		System.out.println("Test n°9");
		System.out.println("Description : Test get_position() first");
		System.out.println("Description : ");
		
		
		CRWaitingList list = new CRWaitingList();
		String listString = "1900//10:::1902//20:::1904//30";
		boolean resConvertion = list.fromString(listString);
		CRWaitingListCell cell = new CRWaitingListCell(1900,10);
		
		String attendu = "1900//10 in position 0";
		String obtenu = cell.toString() + " in position " + list.getPosition(cell);
		System.out.println("Attendu : " + attendu);
		System.out.println("Obtenu  : " + obtenu);
		
		if (attendu.equals(obtenu)) {
			System.out.println("Test OK");
		}
		else {
			System.out.println("Test failed");
		}
	}	
	public static void testCRWaitingList_10() {
		System.out.println("Test n°10");
		System.out.println("Description : Test get_position() second");
		System.out.println("Description : ");
		
		
		CRWaitingList list = new CRWaitingList();
		String listString = "1900//10:::1902//20:::1904//30";
		boolean resConvertion = list.fromString(listString);
		CRWaitingListCell cell = new CRWaitingListCell(1902,20);
		
		String attendu = "1902//20 in position 1";
		String obtenu = cell.toString() + " in position " + list.getPosition(cell);
		System.out.println("Attendu : " + attendu);
		System.out.println("Obtenu  : " + obtenu);
		
		if (attendu.equals(obtenu)) {
			System.out.println("Test OK");
		}
		else {
			System.out.println("Test failed");
		}
	}	
	public static void testCRWaitingList_11() {
		System.out.println("Test n°11");
		System.out.println("Description : Test get_position() last");
		System.out.println("Description : ");
		
		
		CRWaitingList list = new CRWaitingList();
		String listString = "1900//10:::1902//20:::1904//30";
		boolean resConvertion = list.fromString(listString);
		CRWaitingListCell cell = new CRWaitingListCell(1904,30);
		
		String attendu = "1904//30 in position 2";
		String obtenu = cell.toString() + " in position " + list.getPosition(cell);
		System.out.println("Attendu : " + attendu);
		System.out.println("Obtenu  : " + obtenu);
		
		if (attendu.equals(obtenu)) {
			System.out.println("Test OK");
		}
		else {
			System.out.println("Test failed");
		}
	}	
	
	public static void testCRWaitingList_12() {
		System.out.println("Test n°12");
		System.out.println("Description : Test get_position() non present");
		System.out.println("Description : ");
		
		
		CRWaitingList list = new CRWaitingList();
		String listString = "1900//10:::1902//20:::1904//30";
		boolean resConvertion = list.fromString(listString);
		CRWaitingListCell cell = new CRWaitingListCell(1903,30);
		
		String attendu = "1903//30 in position null";
		String obtenu = cell.toString() + " in position " + list.getPosition(cell);
		System.out.println("Attendu : " + attendu);
		System.out.println("Obtenu  : " + obtenu);
		
		if (attendu.equals(obtenu)) {
			System.out.println("Test OK");
		}
		else {
			System.out.println("Test failed");
		}
	}	

	public static void testCRWaitingList_13() {
		System.out.println("Test n°13");
		System.out.println("Description : Test remove on first");
		System.out.println("Description : ");
		
		
		CRWaitingList list = new CRWaitingList();
		String listString = "1900//10:::1902//20:::1904//30";
		boolean resConvertion = list.fromString(listString);
		CRWaitingListCell cell = new CRWaitingListCell(1900,10);
		Integer position = list.getPosition(cell);
		list.remove((int) position);
		String attendu = "1902//20:::1904//30";
		String obtenu = list.toString();
		System.out.println("Attendu : " + attendu);
		System.out.println("Obtenu  : " + obtenu);
		
		if (attendu.equals(obtenu)) {
			System.out.println("Test OK");
		}
		else {
			System.out.println("Test failed");
		}
	}		public static void testCRWaitingList_14() {
		System.out.println("Test n°14");
		System.out.println("Description : Test remove on second");
		System.out.println("Description : ");
		
		
		CRWaitingList list = new CRWaitingList();
		String listString = "1900//10:::1902//20:::1904//30";
		boolean resConvertion = list.fromString(listString);
		CRWaitingListCell cell = new CRWaitingListCell(1902,20);
		Integer position = list.getPosition(cell);
		list.remove((int) position);
		
		String attendu = "1900//10:::1904//30";
		String obtenu = list.toString();
		System.out.println("Attendu : " + attendu);
		System.out.println("Obtenu  : " + obtenu);
		
		if (attendu.equals(obtenu)) {
			System.out.println("Test OK");
		}
		else {
			System.out.println("Test failed");
		}
	}	
	
	public static void testCRWaitingList_15() {
		System.out.println("Test n°15");
		System.out.println("Description : Test remove on last");
		System.out.println("Description : ");
		
		
		CRWaitingList list = new CRWaitingList();
		String listString = "1900//10:::1902//20:::1904//30";
		boolean resConvertion = list.fromString(listString);
		CRWaitingListCell cell = new CRWaitingListCell(1904,30);
		Integer position = list.getPosition(cell);
		list.remove((int) position);
		
		String attendu = "1900//10:::1902//20";
		String obtenu = list.toString();
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
