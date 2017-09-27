/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.environment
 * File name: VirtualMemory.java
 * Created date: Sep 1, 2016
 * Description:
 */
package v2.org.analysis.environment.memory;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTDLL;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.BaseTSD.SIZE_T;

/**
 * @author Yen Nguyen
 *
 */
public class VirtualMemory {
	
	private Pointer mBasedPointer = null;
	private long mSize = 0;
	private String description = "";

	private long mStartingAddr = 0;

	/**
	 * This constructor method will accept an integer value as input which
	 * describe size of the memory block, in bytes. Then it will allocate a
	 * block of size bytes of memory. The content of the newly allocated block
	 * of memory is not initialized, remaining with indeterminate values.
	 * 
	 * @param pSize
	 *            Size of the memory block, in bytes.
	 * 
	 * @param pStartingAddr
	 *            The starting address of the memory region in BE-PUM
	 * 
	 */
	public VirtualMemory(long pSize, long pStartingAddr) {
		mSize = pSize;
		mStartingAddr = pStartingAddr;

		Pointer ret = MSVCRTDLL.INSTANCE.malloc(new SIZE_T(pSize));		
		mBasedPointer = ret;
		clearVirtualMemory();
	}
	
	public void clearVirtualMemory() {
		try {
			for (int i = 0; i < mSize; i++) {
				setByte(mStartingAddr + i, (byte) 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public VirtualMemory(long pSize, long pStartingAddr, String des) {
		this(pSize, pStartingAddr);
		this.description = des;
	}

	/**
	 * @author Hai Nguyen
	 *
	 *         Clone the virtual memory
	 * 
	 * @param no
	 *            param
	 * 
	 * @return The cloned virtual memory
	 */
	@Override
	public VirtualMemory clone() {
		VirtualMemory ret = new VirtualMemory(mSize, mStartingAddr);
		try {
			for (int i = 0; i < mSize; i++) {
				ret.setByte(mStartingAddr + i, getByte(mStartingAddr + i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public boolean containAddress(long pAddr) {
		return pAddr >= mStartingAddr && pAddr < (mStartingAddr + mSize);
	}

	public boolean equal(VirtualMemory m) {
		if (mSize != m.getSize() || mStartingAddr != m.getStartingAddr()) {
			return false;
		}
		try {
			for (int i = 0; i < mSize; i++) {
				if (getByte(mStartingAddr + i) != m.getByte(mStartingAddr + i)) {
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	/**
	 * NOTICE: Please call this method once you do not need to use this object
	 * anymore
	 */
	public void free() {
		MSVCRTDLL.INSTANCE.free(mBasedPointer);
	}

	/**
	 * @author Hai Nguyen
	 *
	 *         Get the actual address of the allocated space plus the offset
	 * 
	 * @param t1
	 *            The offset based on physical address
	 * 
	 * @return The physical address of allocated space plus the offset
	 */
	public long getActualAddr(long t1) {
		return Pointer.nativeValue(mBasedPointer) + t1 - mStartingAddr;
	}

	/**
	 * @author Hai Nguyen
	 *
	 *         Get the actual address of the allocated space plus the offset
	 *         based on the starting address (in BE-PUM)
	 * 
	 * @param startAddr
	 *            The starting address of virtual space (in BE-PUM) offset The
	 *            offset based on physical address
	 * 
	 * @return The physical address of allocated space plus the offset or -1 if
	 *         the startAddr is not equal to mStartingAddr
	 */
	public long getActualAddr(long startAddr, int offset) {
		return (mStartingAddr == startAddr) ? Pointer.nativeValue(mBasedPointer) + offset : -1;
	}

	public Pointer getBasePointer() {
		return mBasedPointer;
	}

	/**
	 * Get byte value of cell memory.
	 * 
	 * @param pAddr
	 *            A void pointer to the allocated space
	 * @return The byte value being pointed to
	 * @throws Exception
	 *             This exception will be thrown when the address if out of
	 *             range of allocated space.
	 */
	public synchronized byte getByte(long pAddr) throws Exception {
		return mBasedPointer.getByte(verifyAddress(pAddr));
	}

	/**
	 * Get integer value of cell memory.
	 * 
	 * @param pAddr
	 *            A void pointer to the allocated space
	 * @return The integer value being pointed to
	 * @throws Exception
	 *             This exception will be thrown when the address if out of
	 *             range of allocated space.
	 */
	public synchronized int getDoubleWord(long pAddr) throws Exception {
		return mBasedPointer.getInt(verifyAddress(pAddr));
	}

	public Pointer getPointer(long t1) {
		return new Pointer(getActualAddr(t1));
	}

	public long getSize() {
		return mSize;
	}

	public long getStartingAddr() {
		return mStartingAddr;
	}

	/**
	 * @author Hai Nguyen
	 *
	 *         Get the string content based on size
	 * 
	 * @param size
	 *            Size of the string
	 * 
	 * @return Return the string based on size
	 * @throws Exception
	 */
	public String getString(int size) throws Exception {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < size; i++) {
			result.append((char) getByte(mStartingAddr + i));
		}

		return result.toString();
	}

	/**
	 * Get short value of cell memory.
	 * 
	 * @param pAddr
	 *            A void pointer to the allocated space
	 * @return The short value being pointed to
	 * @throws Exception
	 *             This exception will be thrown when the address if out of
	 *             range of allocated space.
	 */
	public synchronized short getWord(long pAddr) throws Exception {
		return mBasedPointer.getShort(verifyAddress(pAddr));
	}

	/**
	 * Set byte value of cell memory.
	 * 
	 * @param pAddr
	 *            A void pointer to the allocated space
	 * @param pValue
	 *            A byte value which is supposed to be set
	 * @throws Exception
	 *             This exception will be thrown when the address if out of
	 *             range of allocated space.
	 */
	public synchronized void setByte(long pAddr, byte pValue) throws Exception {
		mBasedPointer.setByte(verifyAddress(pAddr), pValue);
	}

	/**
	 * Set integer value of cell memory.
	 * 
	 * @param pAddr
	 *            A void pointer to the allocated space
	 * @param pValue
	 *            A integer value which is supposed to be set
	 * @throws Exception
	 *             This exception will be thrown when the address if out of
	 *             range of allocated space.
	 */
	public synchronized void setDoubleWord(long pAddr, int pValue) throws Exception {
		mBasedPointer.setInt(verifyAddress(pAddr), pValue);
	}

	/**
	 * Set short value of cell memory.
	 * 
	 * @param pAddr
	 *            A void pointer to the allocated space
	 * @param pValue
	 *            A short value which is supposed to be set
	 * @throws Exception
	 *             This exception will be thrown when the address if out of
	 *             range of allocated space.
	 */
	public synchronized void setWord(long pAddr, short pValue) throws Exception {
		mBasedPointer.setShort(verifyAddress(pAddr), pValue);
	}

	@Override
	public String toString() {
		String ret = "Memory Description:" + description + "\n";
		try {
			for (int i = 0; i < mSize; i++) {
				ret += "0x" + Long.toHexString(mStartingAddr + i) + "\t" + Long.toHexString(getByte(mStartingAddr + i)) + "\n";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * Check whether input address is out of range or not.
	 * 
	 * @param pAddr
	 *            A void pointer to the allocated space
	 * @return An offset against the based address
	 * @throws Exception
	 *             This exception will be thrown when the address if out of
	 *             range of allocated space.
	 */
	public long verifyAddress(long pAddr) throws Exception {
		long offset = pAddr - mStartingAddr;

		if (offset < 0 || (pAddr >= (mStartingAddr + mSize))) {
			throw new Exception("The input address is out of range!");
		}

		return offset;
	}

	public void setText(String path) {
		// TODO Auto-generated method stub
		
	}
}
