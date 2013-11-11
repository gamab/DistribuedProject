package Test;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import DatagramCommunication.DatagramCommunication;

public class TestDatagramCommunicationThread extends Thread {
	
	private InetAddress ip1;
	private int port1;
	private String messageToSend;
	
	public TestDatagramCommunicationThread(InetAddress ip1, int port1, String messageToSend) {
		this.ip1 = ip1;
		this.port1 = port1;
		this.messageToSend = messageToSend;
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
			s2 = new DatagramSocket(0);
			DatagramCommunication.sendMessage(messageToSend, s2, ip1, port1);
			s2.close();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
