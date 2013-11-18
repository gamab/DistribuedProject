package Participant;

import java.util.ArrayList;

// contains the list of the participant which are participating to the program 
public class ParticipantList extends ArrayList<Participant> {

	public ParticipantList(){
		super();
	}
	
	public ParticipantList(ArrayList<Participant> listParticipant){
		super(listParticipant);
	}
	
	
	public String toString(){
		String result = null;
		for (int i = 0 ; i < this.size(); i++) {
			if (i == 0) {
				result = this.get(i).toString();
			} else {
				result =result + ":::" +  this.get(i).toString();
				//System.out.println(result);
			}
		}
		return result;
	}
	
	public boolean fromString(String obj){
		String[] aux1;
		boolean result = true;
		Participant particip = new Participant();
		try {
			// we find each participant 
			aux1 = obj.split(":::");
			for (int i = 0; i < aux1.length; i++)  {
				particip.fromString(aux1[i]);
				this.add(particip);
				//System.out.println (this.toString());
				particip = new Participant(); 
			}
		} catch (Exception e){
			result = false;
			}
		return result;
	}
	
}
