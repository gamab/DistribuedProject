package Test;

import Sources.Processus;

public class TestProcessusThread extends Thread {

	Processus proc;
	
	public TestProcessusThread(Processus proc) {
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
