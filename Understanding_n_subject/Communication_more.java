	public static void sendPacket(byte[] bytes, DatagramSocket s, InetAddress ip, int port) {
		DatagramPacket sendPac = new DatagramPacket(bytes,bytes.length);
		
		sendPac.setData(bytes);
		sendPac.setPort(port);
		sendPac.setAddress(ip);
		
		try {
			s.send(sendPac);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public static void sendBytes(byte[] bytes, DatagramSocket s, InetAddress ip, int port) {
		//if first byte is 1 then it is the last packet we send
		//if first byte is 0 then we have more than one packet to send
		byte[] bytesToSend = addOneCellInFront(bytes);
		if (bytesToSend.length <= bufferReceiveLength) {
			bytesToSend[0] = 1;
			sendPacket(bytesToSend,s,ip,port);
		}
		else {
			//we send the beginning of the sentence
			bytesToSend[0] = 0;
			byte[] bytesToSendPart = subCopy(bytesToSend,0,bufferReceiveLength);
			sendPacket(bytesToSendPart,s,ip,port);
			//we call back this function to send the following
			sendBytes(subCopy(bytesToSend,bufferReceiveLength,bytesToSend.length),s,ip,port);
		}
	}
	
	public static CommunicationByteMessage retrieveBytes(DatagramSocket s) {
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
	
	private static byte[] addOneCellInFront(byte[] bytes) {
		byte[] copy = new byte[bytes.length+1];
		copy[0] = 0;
		for (int i=1; i<copy.length; i++) {
			copy[i] = bytes[i-1];
		}
		return copy;
	}
	private static byte[] subCopy(byte[] bytes,int begin, int end) {
		byte[] copy = new byte[end-begin+1];
		for (int i=begin; i<end; i++) {
			copy[begin-i] = bytes[i];
		}
		return copy;
	}
