/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.system.thread
 * File name: EThreadCreationFlags.java
 * Created date: Sep 27, 2015
 * Description:
 */
package v2.org.analysis.system.thread;

/**
 * @author Yen Nguyen
 *
 */
public enum EThreadCreationFlags {
	RUNS_IMMEDIATELY(0), // The thread runs immediately after creation.
	CREATE_SUSPENDED(0x00000004), // The thread is created in a suspended state,
									// and does not run until the ResumeThread
									// function is called.
	STACK_SIZE_PARAM_IS_A_RESERVATION(0x00010000); // The dwStackSize parameter
													// specifies the initial
													// reserve size of the
													// stack. If this flag is
													// not specified,
													// dwStackSize specifies the
													// commit size.

	private int value;

	EThreadCreationFlags(int numVal) {
		this.value = numVal;
	}

	public int getNumVal() {
		return value;
	}

	public static EThreadCreationFlags getValueFromNum(int num) {
		EThreadCreationFlags[] values = EThreadCreationFlags.values();
		for (int i = 0; i < values.length; i++) {
			if (values[i].getNumVal() == num)
				return values[i];
		}
		return EThreadCreationFlags.RUNS_IMMEDIATELY;
	}
}
