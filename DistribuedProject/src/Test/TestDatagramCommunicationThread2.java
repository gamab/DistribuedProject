package Test;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;

import DatagramCommunication.CommunicationMessage;
import DatagramCommunication.DatagramCommunication;
import Participant.ParticipantList;

public class TestDatagramCommunicationThread2 extends Thread {

	private int port1;
	private String messageToSend;

	
	public TestDatagramCommunicationThread2(Integer port, String messageToSend) {
		this.messageToSend = messageToSend;
		this.port1 = port;
	}
	

	public void run() {
		DatagramSocket s2;
		//wait so that we send the message when s1 is listening
		try {
			this.sleep(100);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			s2 = new DatagramSocket(port1);
			System.out.println("TestDatagramCommunicationThread2 : Retrieving messages...");
			CommunicationMessage messageRead = DatagramCommunication.retrieveMessage(s2);
			System.out.println("TestDatagramCommunicationThread2 : messageRead = " + messageRead.getMessage());
			System.out.println("TestDatagramCommunicationThread2 : messageToSend = " + messageToSend);
			DatagramCommunication.sendMessage(messageToSend, s2, messageRead.getIpOrigin(),messageRead.getPortOrigin() );
			s2.close();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
