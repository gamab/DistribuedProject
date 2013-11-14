package CriticalResources;

import java.util.ArrayList;

import DatagramCommunication.CommunicationMessage;
import Sources.Processus;

public class CR1 extends CriticalRegion{

	public CR1(Processus proc, Integer crid) {
		super(proc, crid);
		// TODO Auto-generated constructor stub
	}


	@Override
	// find resources and send an exception if the resource is not free
	public void enter() throws Exception {
		// TODO Auto-generated method stub
		//we ask to be add in the waiting list of CR 
		//then we  wait until we can get the resource
		getCriticalRegion(crid);
		// ask for the first resource
		if (getResource("a1")) {
			//ask for the second resource
			if (getResource("b1")){
			}else{
				throw new Exception();
			}
		}

	}


	@Override
	public void release() throws Exception {
		// TODO Auto-generated method stub
		//free resource
		freeResources("a1");
		freeResources("b1");
		freeCriticalRegion(crid);


	}

	//Free critical region

}



