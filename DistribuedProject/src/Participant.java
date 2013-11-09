import java.util.ArrayList;


public class Participant {
	Integer pid;
	Integer port;
	ArrayList<String>  resourcesList;
	
	
	/* creation */
	public Participant() {
		
	}
	
	
	public Participant(Integer pid , Integer port){
		
	}
	
	public Participant(Integer pid , Integer port,ArrayList<String>  resourcesList ){
		
	}
	
	/* getter */
	public ArrayList<String>  getRessourcesList() {
		return resourcesList;
	}
	
	
	
	public Integer getPid(){
		return pid;
	}
	
	public Integer getPort(){
		return port;
	}

	/* add */
	public void addToResourcesList(String element){
		
	}

	/* to string */
	public String toString(){
		return null;
	}
	
	/* to Participant*/ 
	/* change a string into a Participant*/
	public Boolean fromString(String obj){
		return null;
	}
}
