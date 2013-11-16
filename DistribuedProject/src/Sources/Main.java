package Sources;
import java.util.Scanner;

import Test.TestProcessusThread;


public class Main {

	public static void main(String[] args) {
		//		TestParticipant.runTest();
		//		TestCRWaitingListCell.runTest();
		//		TestCRWaitingList.runTest();
		//		TestDatagramCommunication.runTest();
		//		TestServiceThread.runTest();
		//		TestProcessus.runTest();
		Scanner sc = new Scanner(System.in);
		char r = 'N'; 
		int[] pid = new int[3];
		for (int i = 0; i < 3; i++) {
			if (i==0) {
				System.out.print("Do you want to create a new processus [y/N]: ");
			} else {
				System.out.print("Do you want to create an other new processus [y/N]: ");
			}
			r = sc.nextLine().charAt(0);
			if (r == (byte)'y') {
				System.out.print("What is the pid of the programm ? => pid = ");
				pid[i] = sc.nextInt();
				quitEndOfLine(sc);
				pid[i] = Math.abs(pid[i]);
				if (pid[i] == 0) {
					pid[i]=Processus.randomInRange(1000, 9999);
				}
				System.out.println("OK => pid =" + pid[i]);
			} else {
				System.out.println("Ok we will not create processus : " + i);
				pid[i] = -1;
			}
		}
		TestProcessusThread[] proc = new TestProcessusThread[3];
		try {
			for (int i = 0; i < 3; i++) {
				if (pid[i]!=-1) {
					System.out.println("Launching processus " + pid[i]);
					proc[i] = new TestProcessusThread(new Processus(pid[i]));
					proc[i].start();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("In Main : Could not launch your Processus.");
			e.printStackTrace();
		}
	}
	
	public static void quitEndOfLine(Scanner sc) {
		sc.nextLine();
	}
}
