//DESCRIPTION : DatagramCommunication represents an abstraction layer to facilitate communications using UDP

package DatagramCommunication;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;

import Participant.ParticipantList;

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

	public static CommunicationMessage retrieveMessage(DatagramSocket s) throws IOException {
		CommunicationMessage message = new CommunicationMessage();

		String bufferReceive_inString = new String();
		Boolean receivedEverything = false;

		while (!receivedEverything) {
			byte[] bufferReceive = new byte[bufferReceiveLength];
			DatagramPacket receivedPack = new DatagramPacket(bufferReceive,bufferReceive.length);

			//reception of a packet from the server
			s.receive(receivedPack);
			bufferReceive = receivedPack.getData();
			bufferReceive_inString = new String(bufferReceive);
			//adding what is in this packet to the message string without the end_detection_byte
			message.addToMessage(bufferReceive_inString.substring(1));

			//reading the end_detection_byte
			if (bufferReceive_inString.startsWith("1")) {
				message.setIpOrigin(receivedPack.getAddress());
				message.setPortOrigin(receivedPack.getPort());
				receivedEverything = true;
			}
		}
		String messageNormalized = normalize(message.getMessage());
		message.setMessage(messageNormalized);
		return message;
	}

	public static ArrayList<CommunicationMessage> sendAndRetreivedMessagesToMultipleProc(String message, DatagramSocket s, ArrayList<Integer> pids,ParticipantList participants) throws IOException{
		ArrayList<CommunicationMessage> responses = new ArrayList<CommunicationMessage>();
		int port = 0;
		InetAddress ip = null;
		boolean find = false;
		// for all the pid which are in the pid's list
		for (int i = 0 ; i < pids.size() ; i++){
			int current_pid = pids.get(i);
			// we are searching the corresponding port to the actual pid in the Participants list
			for (int j = 0 ; j < participants.size() && !find ; j++){
				if (participants.get(j).getPid()==current_pid){
					find = true;
					port = participants.get(j).getPort();
					ip = participants.get(j).getIp();
				}
			}
			// then we send the message to the first pid of the list
			//System.out.println("DatagramCommunication sendAndRetreived : send message : message = " + message + " to " + ip.getHostAddress() + ":" + port);
			sendMessage(message, s, ip, port);
			// we retrieve the responding message from the processus we send the message to
			// and add it in the responses list
			//System.out.println("DatagramCommunication sendAndRetreived : RetrievingMessages...");
			responses.add(retrieveMessage(s)); 
			//System.out.println("DatagramCommunication sendAndRetreived : Retrieved message = " + responses.get(responses.size()-1).getMessage());
			find = false;
		}

		return responses;
	}

	//	public static ArrayList<CommunicationMessage> sendAndRetreivedMessagesToMultipleProc2(String message, DatagramSocket s, ArrayList<Integer> pids,ParticipantList participants){
	//		ArrayList<CommunicationMessage> responses = new ArrayList<CommunicationMessage>();
	//		int port = 0;
	//		boolean find = false;
	//		// for all the pid which are in the pid's list
	//		ListIterator<Integer> pidIt = pids.listIterator();
	//		while (pidIt.hasNext()) {
	//			ListIterator<Participant> partIt = participants.listIterator();
	//			int current_pid = pidIt.next();
	//			// we are searching the corresponding port to the actual pid in the Participants list
	//			while (partIt.hasNext() && !find) {
	//				Participant current_part = partIt.next();
	//				if (current_part.getPid() == current_pid) {
	//					port = current_part.getPort();
	//					find = true;					
	//				}
	//			}
	//			// then we send the message to the first pid of the list
	//			System.out.println("DatagramCommunication sendAndRetreived : SendMessage : message = " + message + " to : " + port);
	//			sendMessage(message, s, s.getLocalAddress(), port);
	//			// we retrieve the responding message from the processus we send the message to
	//			// and add it in the responses list
	//			System.out.println("DatagramCommunication sendAndRetreived : RetrievingMessages...");
	//			responses.add(retrieveMessage(s)); 
	//			System.out.println("DatagramCommunication sendAndRetreived : Retrieved message = " + responses.get(responses.size()-1).getMessage());
	//			find = false;
	//		}
	//
	//		return responses;
	//	}

	private static String normalize(String message) {
		byte[] messageB = message.getBytes();
		boolean zerosFound = false;
		int size = 0;
		for (int i=0; i<messageB.length && !zerosFound; i++) {
			if (messageB[i]!=0) {
				size++;				
			} else {
				zerosFound = true;
			}
		}
		byte[] result = new byte[size];
		for (int i=0; i<size; i++){
			result[i] = messageB[i];
		}
		return new String(result);
	}

	public static InetAddress getOurAddressOnWlan() throws Exception {
		Enumeration<InetAddress> n = NetworkInterface.getByName("wlan0").getInetAddresses();
		InetAddress result = null;
		while (n.hasMoreElements()) {
			result = n.nextElement();
		}
		return result;
	}

	public static InetAddress getOurBroadcastAddressOnWlan() throws Exception {
		byte[] ourIp = getOurAddressOnWlan().getAddress();
		ourIp[ourIp.length-1] = (byte) 255;
		return InetAddress.getByAddress(ourIp);
	}
}
