package CriticalResources;

import java.util.ArrayList;

import DatagramCommunication.CommunicationMessage;
import Sources.Processus;

public class CR1 extends CriticalRegion{

	public CR1(Processus proc, Integer crid) {
		super(proc, crid);
		// TODO Auto-generated constructor stub
	}

	
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!ATTENTION!!!!!!!!!!!!!!!!
	@Override
	public void enter() throws Exception {
		// TODO Auto-generated method stub
		int pidResource1 = proc.whoHasResource("A1");
		ArrayList<CommunicationMessage> message1 = proc.sendAndRetrieveMessage("GET_RESOURCE" + "<<A1<<"+ proc.getClock()+"<<", pidResource1);
		// set the clock
			proc.retrieveClockFromMessageList(message1);
			// find the message and look if it's what we are expeting for
			if (message1.get(0).getMessage().equals("OK_IT_IS_YOURS")){
				proc.wait();
			} else {
				throw new Exception();
			}
		
		int pidResource2 = proc.whoHasResource("B1");
		ArrayList<CommunicationMessage> message2 = proc.sendAndRetrieveMessage("GET_RESOURCE" + "<<A2<<"+ proc.getClock()+"<<", pidResource2);
	
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub
		
	}

	
}
