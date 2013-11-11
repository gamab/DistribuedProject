package Sources;

import java.net.DatagramSocket;
import java.util.ArrayList;

import CriticalResources.CRWaitingList;
import CriticalResources.CriticalRegion;
import Participant.ParticipantList;

public class Processus {
	Integer pid;
	ParticipantList participants;
	CriticalRegion[] criticalResource;
	CRWaitingList crWaitingLists;
	DatagramSocket socket;
	ServiceThread serviceThread;
	Integer servicePort;
	LogicalClock clock;
	ArrayList<String> resources;

	public Processus(){
		
	}
	
	public Processus(Integer pid){
		
	}
	
	public Integer myPid() {
		return pid;
	}
	
	public Integer processes(){
		return participants.size();
	}
	
	public Integer whoHasResource(String resource){
		Integer result = null;
		boolean findIt = false;
		// we search into the list of participants
		for (int i = 0 ; i < participants.size() && !findIt ; i++){
			// the participant who has the resource give his pid
			if (participants.get(i).hasTheResource(resource)){
				result = participants.get(i).getPid();
				findIt = true;
			}
		}
		return result;
	}
	
	public Integer whoIsHeadOfCRWaitingList(Integer crid){
		return crWaitingLists.get(crid).getPid();
	}
	
	public Integer my_servicePort(){
		return servicePort;
	}
	
	public void setServicePort(Integer servicePort){
		this.servicePort = servicePort;
	}
	
}
