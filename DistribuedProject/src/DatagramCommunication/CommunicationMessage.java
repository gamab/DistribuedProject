package DatagramCommunication;

import java.net.InetAddress;

public class CommunicationMessage {
	private String message;
	private InetAddress ipOrigin;
	private int portOrigin;
	public CommunicationMessage(String message, InetAddress ipOrigin, int portOrigin) {
		super();
		this.message = message;
		this.ipOrigin = ipOrigin;
		this.portOrigin = portOrigin;
	}
	public CommunicationMessage() {
		super();
		this.message = new String();
		this.ipOrigin = null;
		this.portOrigin = 0;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void addToMessage(String messageInc) {
		this.message += messageInc;
	}
	public InetAddress getIpOrigin() {
		return ipOrigin;
	}
	public void setIpOrigin(InetAddress ipOrigin) {
		this.ipOrigin = ipOrigin;
	}
	public int getPortOrigin() {
		return portOrigin;
	}
	public void setPortOrigin(int portOrigin) {
		this.portOrigin = portOrigin;
	}	
}
