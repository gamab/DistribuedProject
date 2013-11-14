package CriticalResources;

import java.util.ArrayList;

import DatagramCommunication.CommunicationMessage;
import Sources.Processus;

public abstract class CriticalRegion {

	protected Processus proc;
	protected int crid;
	protected CRWaitingList crWaitingList;
	
	
	public CriticalRegion(Processus proc, Integer crid){
		this.proc = proc;
		this.crid = crid;
		this.crWaitingList = new CRWaitingList();
	}
	
	
	public CRWaitingList getCrWaitingList() {
		return crWaitingList;
	}


	public void setCrWaitingList(CRWaitingList crWaitingList) {
		this.crWaitingList = crWaitingList;
	}



	//we ask to be add in the waiting list of CR 
	//then we  wait until we can get the resource
	public void getCriticalRegion(int crid) throws Exception {
		ArrayList<CommunicationMessage> messages = proc.sendAndRetrieveMessage("GET_CRITICAL_REGION"+"<<" + proc.getClock().toString() + "<<", -1);
		proc.retrieveClockFromMessageList(messages);
		boolean allAgree = true;
		// if all the message retrieved say OK then you can add the proc to the waiting list of the CR
		// else we ask again
		for(int i = 0; i < messages.size() && allAgree; i++){
			if (proc.retrieveMessageWithoutClockFromMessage(messages.get(i)).equals("OK")){
				allAgree = true;
			} else if (proc.retrieveMessageWithoutClockFromMessage(messages.get(i)).equals("BAD_FORMAT") ){
				throw new Exception();
			} 
			if (allAgree){
				CRWaitingListCell cell = new CRWaitingListCell(proc.myPid(),proc.getClock().getClock());
				crWaitingList.add(cell);
			} else {
				getCriticalRegion(crid);
			}
			//we look if we are first in the list of the critcal region
			while (!(proc.whoIsHeadOfCRWaitingList(crid) == proc.myPid())){
				Thread.currentThread().sleep(proc.randomInRange(1000, 5000));
			}
		}
	}

	//ask the resource
	public void getResource(String Resource) throws Exception{
		int pidResource2 = proc.whoHasResource("B1");
		//now that we know who has it, we send a request 
		ArrayList<CommunicationMessage> message2 = proc.sendAndRetrieveMessage("GET_RESOURCE" + "<<A2<<"+ proc.getClock()+"<<", pidResource2);
		// we set the clock
		proc.retrieveClockFromMessageList(message2);
		// find the message and look if it's what we are expecting for
		String[] mess2 = proc.retrieveMessageWithoutClockFromMessage(message2.get(0));
		if (mess2[0].equals("OK_IT_IS_YOURS")){
			proc.wait();
		} else {
			throw new Exception();
		}
	}

	public abstract void enter() throws Exception;

	
	
	public abstract void execute();
		
	
	
	public abstract void release();
		
	
}
