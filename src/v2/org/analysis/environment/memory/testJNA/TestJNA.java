package v2.org.analysis.environment.memory.testJNA;

import v2.org.analysis.environment.memory.VirtualMemory;

public class TestJNA {

	/**
	 * @author Hai Nguyen
	 * 
	 *         Test the usage of VirtualMemory function
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int size = 1000;
		long startAddr = 0x401000;
		VirtualMemory va1 = new VirtualMemory(size, startAddr);
		int ret1 = Kernel32DLLTest.INSTANCE.GetCurrentDirectoryA(size, va1.getBasePointer());
		try {
			System.out.println("Size:" + ret1);
			System.out.println(va1.getString(ret1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// UINT ret2 =
		// Kernel32DLL.INSTANCE.GetWindowsDirectory(va.getPointer().getPointer(ret1.intValue()),
		// new UINT(size));
		VirtualMemory va2 = va1.clone();
		try {
			System.out.println(va2.getString(ret1));
			int ret2 = Kernel32DLLTest.INSTANCE.GetWindowsDirectoryA(va2.getBasePointer(), size);
			System.out.println("Size:" + ret2);
			System.out.println(va2.getString(ret2));
//			BOOL ret = Kernel32DLL.INSTANCE.SetCurrentDirectory(va2.getPointer(va2.getStartingAddr() + 0));
//			System.out.println(ret);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		va1.free();
		va2.free();
	}

}
