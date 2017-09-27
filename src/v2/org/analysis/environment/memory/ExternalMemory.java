package v2.org.analysis.environment.memory;

import v2.org.analysis.environment.memory.ExternalMemory.ExternalMemoryReturnData;
import v2.org.analysis.log.BPLogger;
import v2.org.analysis.structure.BitVector;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;

/**
 * This class is used to get the memory value that is not handled by Memory
 * class. This memory value maybe created by JNA's call or available in
 * dynamic-link library.
 * 
 * @author Yen Nguyen
 *
 */
public class ExternalMemory {
	private static boolean isFirst = true;

	public static class ExternalMemoryReturnData {

		public long address;
		public boolean isValidAddress = false;
		public LongValue value;

		@Override
		public String toString() {
			return "JNA's Address: " + this.address + " is "
					+ ((this.isValidAddress) ? ("valid, value = " + this.value) : "invalid ");
		}
	}

	public ExternalMemory() {
	}

	public synchronized static ExternalMemoryReturnData getByte(long address) {
//		if (isFirst) {
//			isFirst = false;
//			if (Program.getProgram() != null) {
//				BPLogger.reportLogger.info(Program.getProgram().getAbsolutePathFile());
//			}
//		}

		ExternalMemoryReturnData ret = new ExternalMemoryReturnData();
		ret.address = address;

		if (address == 0) {
			ret.isValidAddress = false;
			ret.value = new LongValue(0);
			return ret;
		}

//		// logger.info("ACCESS - Address:" + address, new Exception("Trace"));
//		Thread pointer = new Thread(new AccessPointerThread(ret));
//		pointer.start();
//		try {
//			pointer.join();
//		} catch (InterruptedException e) {
//			BPLogger.reportLogger.error(String.format("JNA Pointer InterruptedException:%d", address), e);
//		}
		
		// zunc: for debug external memory access external pointer on current thread
		AccessPointer acp = new AccessPointer(ret);
		acp.access();
		
//		BPState curState = X86TransitionRule.currentState;
//		if (!ret.isValidAddress) {
//			if (curState != null && curState.getLocation() != null) {
//				BPLogger.reportLogger.warn(String.format("INVALID - Inst:%s - Address:%d", X86TransitionRule.currentState
//						.getLocation().toString(), ret.address), new Exception());
//			}
//		} else {
//			if (curState != null && curState.getLocation() != null) {
//				BPLogger.reportLogger.info(String.format("VALID - Inst:%s - Address:%d, VALUE:%d",
//						X86TransitionRule.currentState.getLocation().toString(), ret.address, ret.value.getValue()));
//			}
//		}

		return ret;

		// return null;
	}

	private static long calculateDoubleWordValue(long r1, long r2, long r3, long r4) {
		/*
		 * int ret = 0; ret = (int) r1; ret |= r2 << 8; ret |= r3 << 16; ret |=
		 * r4 << 24; return ret;
		 */
		return BitVector.bytesToLong((int) r1, (int) r2, (int) r3, (int) r4);
	}

	private static long calculateWordValue(long r1, long r2) {
		/*
		 * int ret = 0; ret = (int) r1; ret |= r2 << 8; return ret;
		 */
		return BitVector.bytesToLong((int) r1, (int) r2);
	}

	public synchronized static ExternalMemoryReturnData getDoubleWord(long address) {
		// TODO Auto-generated method stub
		ExternalMemoryReturnData ret = getByte(address);
		ExternalMemoryReturnData ret1 = getByte(address + 1);
		ExternalMemoryReturnData ret2 = getByte(address + 2);
		ExternalMemoryReturnData ret3 = getByte(address + 3);

		if (ret != null && ret.isValidAddress && ret1 != null && ret1.isValidAddress && ret2 != null
				&& ret2.isValidAddress && ret3 != null && ret3.isValidAddress) {
			ret.value = new LongValue(calculateDoubleWordValue(ret.value.getValue(), ret1.value.getValue(),
					ret2.value.getValue(), ret3.value.getValue()));
		}
		return ret;
		// return null;
	}

	public synchronized static ExternalMemoryReturnData getWord(long address) {
		// TODO Auto-generated method stub
		ExternalMemoryReturnData ret = getByte(address);
		ExternalMemoryReturnData ret1 = getByte(address + 1);

		if (ret != null && ret.isValidAddress && ret1 != null && ret1.isValidAddress) {
			ret.value = new LongValue(calculateWordValue(ret.value.getValue(), ret1.value.getValue()));
		}

		// System.out.println(ret);
		return ret;
		// return null;
	}
}

class AccessPointerThread implements Runnable {

	// private static boolean isFirstTime = true;
	private ExternalMemoryReturnData data;

	public AccessPointerThread(ExternalMemoryReturnData buffer) {
		this.data = buffer;
	}

	@Override
	public void run() {
		Pointer ptr = new Pointer(this.data.address);
		try {
			// if (isFirstTime) {
			// Thread.sleep(1000);
			// }
			byte ret = ptr.getByte(0);
			this.data.value = new LongValue(ret);
			this.data.isValidAddress = true;
		} catch (Exception e) {
			BPLogger.reportLogger.error(e.getMessage(), e);
		}
	}
}

class AccessPointer {

	// private static boolean isFirstTime = true;
	private ExternalMemoryReturnData data;

	public AccessPointer(ExternalMemoryReturnData buffer) {
		this.data = buffer;
	}

	public void access() {
		// System.out.println(String.format("addr: 0x%s", Long.toHexString(this.data.address)));
		Pointer ptr = new Pointer(this.data.address);
		try {
			// if (isFirstTime) {
			// Thread.sleep(1000);
			// }
			byte ret = ptr.getByte(0);
			this.data.value = new LongValue(ret);
//			System.out.println(" -> " + this.data.value);
			this.data.isValidAddress = true;
		} catch (Throwable e) {
			// controlable access memory
			//System.out.println(" - access failed: " + Long.toHexString(this.data.address));
			BPLogger.reportLogger.error(e.getMessage(), e);
			this.data.value = new LongValue(0);
			//System.exit(0);
		}
	}
}