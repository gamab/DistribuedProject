package CriticalResources;
import java.util.Comparator;


public class CRWaitingListCell implements Comparator<CRWaitingListCell> {

	private int pid;
	private int clock;
	
	public CRWaitingListCell() {
		clock = 0;
		pid = 0;
	}
	
	public CRWaitingListCell(int pid, int clock) {
		this.pid = pid;
		this.clock = clock;
	}
	
	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getClock() {
		return clock;
	}

	public void setClock(int clock) {
		this.clock = clock;
	}

	public String toString() {
		return pid + "//" + clock;
	}
	
	public boolean fromString(String obj) {
		boolean result = true;
		try {
			pid = Integer.valueOf(obj.split("//")[0]);
			clock = Integer.valueOf(obj.split("//")[1]);
		} catch (Exception e){
			result = false;
		}
		return result;
	}
	
	@Override
	// Returns -1 0 1
	public int compare(CRWaitingListCell a, CRWaitingListCell b) {
		// TODO Auto-generated method stub
		int result;
		if (a.clock < b.clock) {
			result = -1;
		}
		else if (a.clock > b.clock) {
			result = 1;
		}
		else {
			if (a.pid < b.pid) {
				result = -1;
			} 
			else if (a.pid > b.pid) {
				result = 1;
			}
			else {
				result = 0;
			}
		}
		return result;
	}

	public boolean equals(CRWaitingListCell a, CRWaitingListCell b) {
		if (a.clock == b.clock && a.pid == b.pid) {
			return true;
		} else {
			return false;
		}
	}
}
