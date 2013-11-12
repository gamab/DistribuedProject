package CriticalResources;

import Sources.Processus;

public class CriticalRegion {

	private Processus proc;
	private int crid;
	private CRWaitingList crWaitingList;
	
	
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
	
	public void enter(){
		
	}
	
	public void execute(){
		
	}
	
	public void release(){
		
	}
}
