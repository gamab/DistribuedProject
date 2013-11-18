package Sources;
import java.util.Scanner;

import Test.TestCRWaitingList;
import Test.TestCRWaitingListCell;
import Test.TestDatagramCommunication;
import Test.TestParticipant;
import Test.TestProcessus;
import Test.TestServiceThread;


// Allows user to create 3 process with the pid of his choice
public class Main {

	public static void main(String[] args) {
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
				System.out.print("What is the pid of the programm ? (if 0 : Random) => pid = ");
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
		ProcessusThread[] proc = new ProcessusThread[3];
		try {
			for (int i = 0; i < 3; i++) {
				if (pid[i]!=-1) {
					System.out.println("Launching processus " + pid[i]);
					proc[i] = new ProcessusThread(new Processus(pid[i]));
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
	
	public static void runTests() {
		TestParticipant.runTest();
		TestCRWaitingListCell.runTest();
		TestCRWaitingList.runTest();
		TestDatagramCommunication.runTest();
		try {
			TestServiceThread.runTest();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TestProcessus.runTest();
	}
}
