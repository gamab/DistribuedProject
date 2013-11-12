package Sources;
import Test.TestCRWaitingList;
import Test.TestCRWaitingListCell;
import Test.TestDatagramCommunication;
import Test.TestParticipant;


public class Main {

	public static void main(String[] args) {
		TestParticipant.runTest();
		TestCRWaitingListCell.runTest();
		TestCRWaitingList.runTest();
		TestDatagramCommunication.runTest();
	}
}
