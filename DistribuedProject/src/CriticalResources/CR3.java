package CriticalResources;

import Sources.Processus;

public class CR3 extends CriticalRegion{

	public CR3(Processus proc, Integer crid) {
		super(proc, crid);
		// TODO Auto-generated constructor stub

	}

	@Override
	public void enter() throws Exception {
		// TODO Auto-generated method stub
		//we ask to be add in the waiting list of CR 
		//then we  wait until we can get the resource
		getCriticalRegion(crid);
		// ask for the first resource
		if (getResource("A3")) {
			//ask for the second resource
			if (!getResource("B3")){
				throw new Exception();
			}
		}
		
	}


	@Override
	public void release() throws Exception {
		// TODO Auto-generated method stub
		freeResources("A3");
		freeResources("B3");
		freeCriticalRegion(crid);
		
	}

}
