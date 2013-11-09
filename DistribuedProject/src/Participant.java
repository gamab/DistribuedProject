import java.util.ArrayList;


public class Participant {
	Integer pid;
	Integer port;
	ArrayList<String>  resourcesList;
	
	
	/* creation */
	public Participant() {
		this.pid = null;
		this.port = null;
		this.resourcesList = new ArrayList<String>(); 
	}
	
	
	public Participant(Integer pid , Integer port){
		this.pid = pid;
		this.port = port;
		this.resourcesList = new ArrayList<String>();
	}
	
	public Participant(Integer pid , Integer port,ArrayList<String>  resourcesList ){
		this.pid = pid;
		this.port = port;
		this.resourcesList = resourcesList;
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
		resourcesList.add(element);
	}

	/* to string */
	public String toString(){
		String result = null;
		/*Integer i = 0;*/
		result = pid.toString() + " || " + port.toString();
		// on rajoute au string final chaque ressources
		for(int i=0;i < resourcesList.size() ;i++){
			if (i == 0){
				result = result + " || " + resourcesList.get(i).toString();
			}
			else {
				result = result + " ;; " + resourcesList.get(i).toString();
			}
		}
		
		return result;
	}
	
	/* to Participant*/ 
	/* change a string into a Participant*/
	public Boolean fromString(String obj){
		return null;
	}
}
