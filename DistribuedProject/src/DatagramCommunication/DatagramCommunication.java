package DatagramCommunication;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class DatagramCommunication {

	final static int bufferSendLength = 1024;
	final static int bufferReceiveLength = 1024;
	
	public static void sendMessage(String message, DatagramSocket s, InetAddress ip, int port) {
		//if first byte is 1 then it is the last packet we send
		//if first byte is 0 then we have more than one packet to send
		if (message.length() <= bufferReceiveLength - 1) {
			message = '1' + message;
			sendPacket(message,s,ip,port);
		}
		else {
			//we send the beginning of the sentence
			String messagePart = new String('0' + message.substring(0, bufferReceiveLength - 1));
			sendPacket(messagePart,s,ip,port);
			//we call back this function to send the following
			sendMessage(message.substring(bufferReceiveLength - 1),s,ip,port);
		}
	}
	
	public static void sendPacket(String message, DatagramSocket s, InetAddress ip, int port) {
		DatagramPacket sendPac = new DatagramPacket(message.getBytes(),message.length());
		
		sendPac.setData(message.getBytes());
		sendPac.setPort(port);
		sendPac.setAddress(ip);
		
		try {
			s.send(sendPac);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static CommunicationMessage retrieveMessage(DatagramSocket s) {
		CommunicationMessage message = new CommunicationMessage();
		
		String bufferReceive_inString = new String();
		Boolean receivedEverything = false;

		while (!receivedEverything) {
			byte[] bufferReceive = new byte[bufferReceiveLength];
			DatagramPacket receivedPack = new DatagramPacket(bufferReceive,bufferReceive.length);
			
			//reception of a packet from the server
			try {
				s.receive(receivedPack);
				bufferReceive = receivedPack.getData();
				bufferReceive_inString = new String(bufferReceive);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//adding what is in this packet to the message string without the end_detection_byte
			message.addToMessage(bufferReceive_inString.substring(1));
			
			//reading the end_detection_byte
			if (bufferReceive_inString.startsWith("1")) {
				message.setIpOrigin(receivedPack.getAddress());
				message.setPortOrigin(receivedPack.getPort());
				receivedEverything = true;
			}
		}
		
		return message;
	}
	

}
