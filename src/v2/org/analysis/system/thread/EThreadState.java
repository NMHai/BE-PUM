package v2.org.analysis.system.thread;

public enum EThreadState {
	RUNNING (0),
	SLEEPING (1),
	SUSPENDING (2);

	private int value;

	EThreadState(int numVal) {
		this.value = numVal;
	}

	public int getNumVal() {
		return value;
	}

	public static EThreadState getValueFromNum(int num) {
		EThreadState[] values = EThreadState.values();
		for (int i = 0; i < values.length; i++) {
			if (values[i].getNumVal() == num)
				return values[i];
		}
		return EThreadState.RUNNING;
	}
}
