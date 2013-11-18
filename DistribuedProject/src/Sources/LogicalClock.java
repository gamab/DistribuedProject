//DESCRIPTION : LogicalClock represents the Lamport Clock

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
	
	public void setClockLogically(int messageClock) {
		this.clock = Math.max(clock,messageClock+1);
	}
	
	public void incClock() {
		this.clock ++;
	}
}
