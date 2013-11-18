package Participant;
import java.net.InetAddress;
import java.util.ArrayList;

// a participant all the information that other processes need to know on a given process

public class Participant {
	InetAddress ip;
	Integer pid;
	Integer port;
	ArrayList<String>  resourcesList;
	
	
	/* creation */
	public Participant() {
		this.pid = null;
		this.port = null;
		this.ip = null;
		this.resourcesList = new ArrayList<String>(); 
	}
	
	
	public Participant(Integer pid , Integer port, InetAddress ip){
		this.pid = pid;
		this.port = port;
		this.ip = ip;
		this.resourcesList = new ArrayList<String>();
	}
	
	public Participant(Integer pid , Integer port,InetAddress ip, ArrayList<String>  resourcesList ){
		this.pid = pid;
		this.port = port;
		this.ip = ip;
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
	
	public InetAddress getIp(){
		return ip;
	}

	/* add */
	public void addToResourcesList(String element){
		resourcesList.add(element);
	}

	/* to string */
	public String toString(){
		String result = null;
		/*Integer i = 0;*/
		result = pid.toString() + "//" + port.toString() + "//" + ip.getHostAddress() + "//";
		// on rajoute au string final chaque ressources
		for(int i=0;i < resourcesList.size() ;i++){
			if (i == 0){
				result = result + resourcesList.get(i).toString();
			}
			else {
				result = result + ";;" + resourcesList.get(i).toString();
			}
		}
		
		return result;
	}

	/* to Participant*/ 
	/* change a string into a Participant*/
	public boolean fromString(String obj){
		String[] aux;
		String[] aux1;
		boolean result = true;
		try {
			ArrayList<String>  resourcesList = new ArrayList<String>();	
			aux = obj.split("//");
			// we find the pid
			this.pid =Integer.valueOf(aux[0]);
			// now we find the port
			this.port = Integer.valueOf(aux[1]);
			// now we find the ip
			this.ip = InetAddress.getByName(aux[2]);
			// now we find the list of resources
			aux1 = aux[3].split(";;");
			for (int i = 0; i < aux1.length; i++)  {
				this.resourcesList.add(aux1[i]);
			}
		} catch (Exception e){
			result = false;
			}
			return result;
	}
	
	//return true if the participant has the asking resource 
	public boolean hasTheResource(String resource) {
		boolean result = false;
		for (int i = 0; i < resourcesList.size(); i++){
			if (resource.equals(resourcesList.get(i))){
				result = true;
			}
		}
		return result;
	}
	
}
