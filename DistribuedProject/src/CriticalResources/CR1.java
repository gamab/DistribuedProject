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
		getResource("A1");	
		//ask for the second resource
		getResource("B1");
	}


	@Override
	public void release() {
		// TODO Auto-generated method stub
		
	}

	
}
