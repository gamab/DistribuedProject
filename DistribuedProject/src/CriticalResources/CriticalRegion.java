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
	protected void getCriticalRegion(int crid) throws Exception {
		System.out.println("CriticalRegion : getCriticalRegion : create new CRWaitingListCell ");
		// we create a new cell of crWaitingList for our newbie
		CRWaitingListCell cell = new CRWaitingListCell(proc.myPid(),proc.getClock().getClock());
		System.out.println("CriticalRegion : getCriticalRegion  : send message GET_CRITICAL_REGION");
		// we ask to everyone to add us in there Watinglist of the CR asked for
		ArrayList<CommunicationMessage> messages = proc.sendAndRetrieveMessage("GET_CRITICAL_REGION"+"<<"+ crid + "<<" + cell.toString() + "<<" + proc.getClock().getClock() + "<<", -1);
		System.out.println("CriticalRegion : getCriticalRegion : Clock before setting" + proc.getClock().getClock());
		proc.retrieveClockFromMessageList(messages);
		System.out.println("CriticalRegion : getCriticalRegion : Clock after setting" + proc.getClock().getClock());

		boolean allAgree = true;
		
		// if all the message retrieved say OK then you can add the proc to the waiting list of the CR
		// else we ask again
		for(int i = 0; i < messages.size() && allAgree; i++){
			if (proc.retrieveMessageWithoutClockFromMessage(messages.get(i)).equals("OK")){
				allAgree = true;
				System.out.println("CriticalRegion : getCriticalRegion : resonse := "+ i + allAgree );

			} else if (proc.retrieveMessageWithoutClockFromMessage(messages.get(i)).equals("BAD_FORMAT") ){
				throw new Exception();
			} 
			if (allAgree){
				System.out.println("CriticalRegion : getCriticalRegion : we add the cell to the CRWaitingList of the CR");
				crWaitingList.add(cell);
			} else {
				throw new Exception("CriticalRegion : getCriticalRegion :  all procs didn't send OK to GET_CRITICAL_REGION");
			}
			//we look if we are first in the list of the critcal region
			while (!(proc.whoIsHeadOfCRWaitingList(crid) == proc.myPid())){
				Thread.currentThread().sleep(proc.randomInRange(1000, 5000));
			}
		}
	}

	//ask the resource
	protected boolean getResource(String resource) throws Exception{
		boolean gotResource = false;
		System.out.println("CriticalRegion : getResource : Asking WhoHasResource" + resource);
		int pidResource2 = proc.whoHasResource(resource);
		System.out.println("CriticalRegion : getResource :"+ pidResource2+ "HasResource" + resource);
		//now that we know who has it, we send a request to have it
		System.out.println("CriticalRegion : getResource : send and retrieve message GET_RESSOURCE ");
		ArrayList<CommunicationMessage> message = proc.sendAndRetrieveMessage("GET_RESOURCE" + "<<" + resource + "<<"+ proc.getClock().getClock() +"<<", pidResource2);
		System.out.println("CriticalRegion : getResource : message received " );
		System.out.println("CriticalRegion : getResource : Clock before setting" + proc.getClock().getClock());
		// we set the clock
		proc.retrieveClockFromMessageList(message);
		System.out.println("CriticalRegion : getResource : Clock after setting" + proc.getClock().getClock());
		// find the message and look if it's what we are expecting for
		String[] mess = proc.retrieveMessageWithoutClockFromMessage(message.get(0));
		if (mess[0].equals("OK_IT_IS_YOURS")){
			gotResource = true;
		} else {
			throw new Exception();
		}
		return gotResource;
	}

	
	protected boolean freeResources(String resource) throws Exception{
		boolean letResourceGo = false;
		System.out.println("CriticalRegion : getResource : Asking WhoHasResource" + resource);
		int pidResource2 = proc.whoHasResource(resource);
		System.out.println("CriticalRegion : getResource :"+ pidResource2+ "HasResource" + resource);
		//now that we know who has it, we can tell him we don't need it anymore
		System.out.println("CriticalRegion : freeResources : send and retrieve message FREE_RESSOURCE ");
		ArrayList<CommunicationMessage> message = proc.sendAndRetrieveMessage("FREE_RESOURCE" + "<<"+resource+"<<"+ proc.getClock().getClock() +"<<", pidResource2);
		System.out.println("CriticalRegion : freeResources : message received " );
		System.out.println("CriticalRegion : freeResources : Clock before setting" + proc.getClock().getClock());
		// we set the clock
		proc.retrieveClockFromMessageList(message);
		System.out.println("CriticalRegion : freeResources : Clock after setting" + proc.getClock().getClock());
		// find the message and look if it's what we are expecting for
		String[] mess = proc.retrieveMessageWithoutClockFromMessage(message.get(0));
		if (mess[0].equals("OK_IT_IS_NOT_YOURS_ANYMORE ")){
			letResourceGo = true;
		} else {
			throw new Exception();
		}
		return letResourceGo;
	}
	
	protected void freeCriticalRegion(int crid) throws Exception{
		System.out.println("CriticalRegion : freeCriticalRegion : create new CRWaitingListCell ");
		// we create a new cell and get our cell from the 
		CRWaitingListCell cell = crWaitingList.get(0);
		if (cell.getPid() != this.proc.myPid()){
			throw new Exception("Was not meant to have the CR, why am I releasing it");
		}
		System.out.println("CriticalRegion : freeCriticalRegion  : send message FREE_CRITICAL_REGION");
		// we ask to everyone to add us in there Watinglist of the CR asked for
		ArrayList<CommunicationMessage> messages = proc.sendAndRetrieveMessage("FREE_CRITICAL_REGION"+"<<"+ crid + "<<" + cell.toString() + "<<" + proc.getClock().getClock() + "<<", -1);
		System.out.println("CriticalRegion : freeCriticalRegion : Clock before setting" + proc.getClock().getClock());
		proc.retrieveClockFromMessageList(messages);
		System.out.println("CriticalRegion : freeCriticalRegion : Clock after setting" + proc.getClock().getClock());
		boolean allAgree = true;
		
		// if all the message retrieved say OK then you delete the proc of the waiting list of the CR
		// else we ask again
		for(int i = 0; i < messages.size() && allAgree; i++){
			if (proc.retrieveMessageWithoutClockFromMessage(messages.get(i)).equals("OK")){
				allAgree = true;
				System.out.println("CriticalRegion : freeCriticalRegion : resonse := "+ i + allAgree );

			} else if (proc.retrieveMessageWithoutClockFromMessage(messages.get(i)).equals("BAD_FORMAT") ){
				throw new Exception();
			} 
			if (allAgree){
				System.out.println("CriticalRegion : getCriticalRegion : we delete the cell to the CRWaitingList of the CR");
				crWaitingList.remove(cell);
			} else {
				throw new Exception("CriticalRegion : getCriticalRegion : all procs didn't send OK to FREE_CRITICAL_REGION");
			}
		}
	}
	
	public abstract void enter() throws Exception;

	
	
	public void execute() throws InterruptedException{
		Thread.currentThread().sleep(proc.randomInRange(5000, 9000));
	}
		
	
	
	public abstract void release() throws Exception;
		
	
}
