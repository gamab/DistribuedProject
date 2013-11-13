package Test;

import Sources.Processus;

public class TestProcessusThread extends Thread {

	Processus proc;
	
	public TestProcessusThread(Processus proc) {
		this.proc = proc;		
	}
	
	public void run() {
		proc.run();
	}
}
