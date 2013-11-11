package Sources;

public class LogicalClock {
	private int clock;

	public LogicalClock() {
		super();
		this.clock = 0;
	}

	public LogicalClock(int clock) {
		super();
		this.clock = clock;
	}

	public int getClock() {
		return clock;
	}

	public void setClock(int clock) {
		this.clock = clock;
	}
	
	public void incClock() {
		this.clock ++;
	}
}
