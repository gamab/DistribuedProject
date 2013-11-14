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
		System.out.println("CriticalRegion of proc : " + proc.myPid() + ", getCriticalRegion : create new CRWaitingListCell ");
		// we create a new cell of crWaitingList for our newbie
		CRWaitingListCell cell = new CRWaitingListCell(proc.myPid(),proc.getClock().getClock());
		System.out.println("CriticalRegion of proc : " + proc.myPid() + ", getCriticalRegion  : send message GET_CRITICAL_REGION");
		// we ask to everyone to add us in there Watinglist of the CR asked for
		ArrayList<CommunicationMessage> messages = proc.sendAndRetrieveMessage("GET_CRITICAL_REGION"+"<<"+ crid + "<<" + cell.toString() + "<<" + proc.getClock().getClock() + "<<", proc.ANY);
		System.out.println("CriticalRegion of proc : " + proc.myPid() + ", getCriticalRegion : Clock before setting" + proc.getClock().getClock());
		proc.retrieveClockFromMessageList(messages);
		System.out.println("CriticalRegion of proc : " + proc.myPid() + ", getCriticalRegion : Clock after setting" + proc.getClock().getClock());

		// if all the message retrieved say OK then you can add the proc to the waiting list of the CR
		// else we ask again
		for(int i = 0; i < messages.size(); i++){
			if (proc.retrieveMessageWithoutClockFromMessage(messages.get(i)).equals("OK")){
				System.out.println("CriticalRegion of proc : " + proc.myPid() + ", getCriticalRegion : resonse := "+ i);

			} else if (proc.retrieveMessageWithoutClockFromMessage(messages.get(i)).equals("BAD_FORMAT") ){
				throw new Exception("CriticalRegion of proc : " + proc.myPid() + ", getCriticalRegion :  all procs didn't send OK to GET_CRITICAL_REGION");
			} 
		}
		System.out.println("CriticalRegion of proc : " + proc.myPid() + ", getCriticalRegion : we add the cell to the CRWaitingList of the CR");
		crWaitingList.add(cell);
		//we look if we are first in the list of the critcal region
		while (!(proc.whoIsHeadOfCRWaitingList(crid) == proc.myPid())){
			System.out.println("CriticalRegion of proc : " + proc.myPid() + ", getCriticalRegion : Waiting to be head of CR.");
			Thread.currentThread().sleep(proc.randomInRange(1000, 5000));
		}
	}

	//ask the resource
	protected boolean getResource(String resource) throws Exception{
		boolean gotResource = false;
		System.out.println("CriticalRegion of proc : " + proc.myPid() + ", getResource : Asking WhoHasResource" + resource);
		int pidResource2 = proc.whoHasResource(resource);
		System.out.println("CriticalRegion : getResource :"+ pidResource2 + " hasResource " + resource);
		//now that we know who has it, we send a request to have it
		System.out.println("CriticalRegion of proc : " + proc.myPid() + ", getResource : send and retrieve message GET_RESSOURCE ");
		ArrayList<CommunicationMessage> message = proc.sendAndRetrieveMessage("GET_RESOURCE" + "<<" + resource + "<<"+ proc.getClock().getClock() +"<<", pidResource2);
		System.out.println("CriticalRegion of proc : " + proc.myPid() + ", getResource : message received " );
		System.out.println("CriticalRegion of proc : " + proc.myPid() + ", getResource : Clock before setting" + proc.getClock().getClock());
		// we set the clock
		proc.retrieveClockFromMessageList(message);
		System.out.println("CriticalRegion of proc : " + proc.myPid() + ", getResource : Clock after setting" + proc.getClock().getClock());
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
		System.out.println("CriticalRegion of proc : " + proc.myPid() + ", freeResource : Asking WhoHasResource" + resource);
		int pidResource2 = proc.whoHasResource(resource);
		System.out.println("CriticalRegion of proc : " + proc.myPid() + ", freeResource :"+ pidResource2+ " hasResource " + resource);
		//now that we know who has it, we can tell him we don't need it anymore
		System.out.println("CriticalRegion of proc : " + proc.myPid() + ", freeResources : send and retrieve message FREE_RESSOURCE ");
		ArrayList<CommunicationMessage> message = proc.sendAndRetrieveMessage("FREE_RESOURCE" + "<<"+resource+"<<"+ proc.getClock().getClock() +"<<", pidResource2);
		System.out.println("CriticalRegion of proc : " + proc.myPid() + ", freeResources : message received " );
		System.out.println("CriticalRegion of proc : " + proc.myPid() + ", freeResources : Clock before setting" + proc.getClock().getClock());
		// we set the clock
		proc.retrieveClockFromMessageList(message);
		System.out.println("CriticalRegion of proc : " + proc.myPid() + ", freeResources : Clock after setting" + proc.getClock().getClock());
		// find the message and look if it's what we are expecting for
		String[] mess = proc.retrieveMessageWithoutClockFromMessage(message.get(0));
		if (mess[0].equals("OK_IT_IS_NOT_YOURS_ANYMORE")){
			letResourceGo = true;
		} else {
			throw new Exception();
		}
		return letResourceGo;
	}

	protected void freeCriticalRegion(int crid) throws Exception{
		System.out.println("CriticalRegion of proc : " + proc.myPid() + ", freeCriticalRegion : create new CRWaitingListCell ");
		// we create a new cell and get our cell from the 
		CRWaitingListCell cell = crWaitingList.get(0);
		if (cell.getPid() != this.proc.myPid()){
			throw new Exception("Was not meant to have the CR, why am I releasing it");
		}
		System.out.println("CriticalRegion of proc : " + proc.myPid() + ", freeCriticalRegion  : send message FREE_CRITICAL_REGION");
		// we ask to everyone to add us in there Watinglist of the CR asked for
		ArrayList<CommunicationMessage> messages = proc.sendAndRetrieveMessage("FREE_CRITICAL_REGION"+"<<"+ crid + "<<" + cell.toString() + "<<" + proc.getClock().getClock() + "<<", -1);
		System.out.println("CriticalRegion of proc : " + proc.myPid() + ", freeCriticalRegion : Clock before setting" + proc.getClock().getClock());
		proc.retrieveClockFromMessageList(messages);
		System.out.println("CriticalRegion of proc : " + proc.myPid() + ", freeCriticalRegion : Clock after setting" + proc.getClock().getClock());

		// if all the message retrieved say OK then you delete the proc of the waiting list of the CR
		// else we ask again
		for(int i = 0; i < messages.size(); i++){
			if (proc.retrieveMessageWithoutClockFromMessage(messages.get(i)).equals("OK")){
				System.out.println("CriticalRegion of proc : " + proc.myPid() + ", freeCriticalRegion : resonse := "+ i);

			} else if (proc.retrieveMessageWithoutClockFromMessage(messages.get(i)).equals("BAD_FORMAT") ){
				throw new Exception("CriticalRegion of proc : " + proc.myPid() + ", freeCriticalRegion : all procs didn't send OK to FREE_CRITICAL_REGION");
			}
		}

		System.out.println("CriticalRegion of proc : " + proc.myPid() + ", freeCriticalRegion : we delete the cell " + cell + " from the CRWaitingList " + this.crWaitingList + " of the CR");
		crWaitingList.remove(0);
	}

	public abstract void enter() throws Exception;



	public void execute() throws InterruptedException{
		int sleeping_time = proc.randomInRange(8000, 10000);
		System.out.println("CriticalRegion of proc : " + proc.myPid() + ", executing CR " + this.crid + " for " + sleeping_time + "ms");
		Thread.currentThread().sleep(sleeping_time);
		System.out.println("CriticalRegion of proc : " + proc.myPid() + ", Stopping execution of CR " + this.crid);
	}



	public abstract void release() throws Exception;


}
