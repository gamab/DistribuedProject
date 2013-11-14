package CriticalResources;

import Sources.Processus;

public class CR2 extends CriticalRegion {

	public CR2(Processus proc, Integer crid) {
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
		getResource("A2");	
		//ask for the second resource
		getResource("B2");

	}



	@Override
	public void release() {
		// TODO Auto-generated method stub
		
	}

}
