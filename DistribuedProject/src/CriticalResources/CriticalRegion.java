package CriticalResources;

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
	
	public abstract void enter() throws Exception;
		
	
	
	public abstract void execute();
		
	
	
	public abstract void release();
		
	
}
