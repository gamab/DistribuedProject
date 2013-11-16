package Sources;


public class ProcessusThread extends Thread {

	Processus proc;
	
	public ProcessusThread(Processus proc) {
		this.proc = proc;		
	}
	
	public void run() {
		try {
			proc.run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
