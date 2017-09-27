/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.system
 * File name: Thread.java
 * Created date: Sep 26, 2015
 * Description:
 */
package v2.org.analysis.system.thread;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.path.BPPath;

/**
 * @author Yen Nguyen
 *
 */
public class Thread {

	private long handle = 0;
	private long funcPointer = 0;
	private long arglistAddress = 0;
	private int threadID = 0;

	private EThreadCreationFlags creationFlags;
	private EThreadState state;

	private boolean isMainThread = false;
	private BPPath path;

	private long timeToWakeUp = System.currentTimeMillis();

	public Thread(BPPath path, long handle, long funcPointer, long arglistAddress, EThreadCreationFlags creationFlags,
			int threadID) {
		this.path = path;

		this.handle = handle;
		this.funcPointer = funcPointer;
		this.arglistAddress = arglistAddress;
		this.creationFlags = creationFlags;
		this.threadID = threadID;

		if (this.creationFlags == EThreadCreationFlags.CREATE_SUSPENDED) {
			this.setState(EThreadState.SUSPENDING);
		} else {
			this.setState(EThreadState.RUNNING);
		}
	}

	public Thread clone(BPPath path) {
		Thread tmp = new Thread(path, this.handle, this.funcPointer, this.arglistAddress, this.creationFlags,
				this.threadID);
		tmp.state = this.state;
		tmp.isMainThread = this.isMainThread;

		// TODO: This can take so many time to run in current path, cause wrong
		// wake up time in another path, so need solve this problem in the
		// future!
		tmp.timeToWakeUp = this.timeToWakeUp;
		
		return tmp;

	}

	public void setMainThread() {
		this.isMainThread = true;
	}

	public boolean isMainThread() {
		return isMainThread;
	}

	/**
	 * Check if this thread can run now
	 * 
	 * @return TRUE if not be blocked, otherwise FALSE
	 */
	public boolean isActive() {
		if (this.timeToWakeUp > System.currentTimeMillis()) {
			return false;
		}

		if (this.getState() != EThreadState.RUNNING) {
			return false;
		}

		return true;
	}

	public Environment getEnvironment() {
		return this.path.getCurrentState().getEnvironement();
	}

	/**
	 * @return the threadID
	 */
	public int getThreadID() {
		return threadID;
	}

	/**
	 * @param threadID
	 *            the threadID to set
	 */
	public void setThreadID(int threadID) {
		this.threadID = threadID;
	}

	/**
	 * @return the creationFlags
	 */
	public EThreadCreationFlags getCreationFlags() {
		return creationFlags;
	}

	/**
	 * @return the funcPointer
	 */
	public long getFuncPointer() {
		return funcPointer;
	}

	/**
	 * @return the arglist
	 */
	public long getArglist() {
		return arglistAddress;
	}

	/**
	 * @return the timeToWakeUp
	 */
	public long getTimeToWakeUp() {
		return timeToWakeUp;
	}

	/**
	 * @param timeToWakeUp
	 *            the timeToWakeUp to set
	 */
	public void setTimeToWakeUp(long timeToWakeUp) {
		this.timeToWakeUp = timeToWakeUp;
	}

	/**
	 * @return the state
	 */
	public EThreadState getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(EThreadState state) {
		this.state = state;
	}

	/**
	 * @return the handle
	 */
	public long getHandle() {
		return handle;
	}

	/**
	 * @param handle
	 *            the handle to set
	 */
	public void setHandle(long handle) {
		this.handle = handle;
	}
}
