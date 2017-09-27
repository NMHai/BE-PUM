/**
 * 
 */
package v2.org.analysis.system;

/**
 * This class manages Heap
 * 
 * @author MinhHai
 * 
 */
public class Heap {
	private long intialSize, maximumSize, handle, allocationFlag;

	public Heap(long intialSize, long maximumSize, long allocationFlag) {
		super();
		this.intialSize = intialSize;
		this.maximumSize = maximumSize;
		this.allocationFlag = allocationFlag;
	}

	/**
	 * @return the intialSize
	 */
	public long getIntialSize() {
		return intialSize;
	}

	/**
	 * @param intialSize
	 *            the intialSize to set
	 */
	public void setIntialSize(long intialSize) {
		this.intialSize = intialSize;
	}

	/**
	 * @return the maximumSize
	 */
	public long getMaximumSize() {
		return maximumSize;
	}

	/**
	 * @param maximumSize
	 *            the maximumSize to set
	 */
	public void setMaximumSize(long maximumSize) {
		this.maximumSize = maximumSize;
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

	/**
	 * @return the allocationFlag
	 */
	public long getAllocationFlag() {
		return allocationFlag;
	}

	/**
	 * @param allocationFlag
	 *            the allocationFlag to set
	 */
	public void setAllocationFlag(long allocationFlag) {
		this.allocationFlag = allocationFlag;
	}

	public long free(long freeFlag, long pointer) {
		// TODO Auto-generated method stub
		// Hien thuc sau
		return 1;
	}
}
