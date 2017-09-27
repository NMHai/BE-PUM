package v2.org.analysis.environment.processthread;

import java.io.File;
import java.util.ArrayList;
import java.util.Map.Entry;

import org.jakstab.Program;

import v2.org.analysis.apihandle.winapi.APIHandle;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.apihandle.winapi.kernel32.functions.LoadLibrary;
import v2.org.analysis.environment.memory.ExternalMemory;
import v2.org.analysis.environment.memory.ExternalMemory.ExternalMemoryReturnData;
import v2.org.analysis.path.BPState;
import v2.org.analysis.util.PairEntry;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HMODULE;

public class LDRData {

	private static final int LOAD_ORDER_TYPE		= 0;
	private static final int MEM_ORDER_TYPE			= LOAD_ORDER_TYPE + 1;
	private static final int INIT_ORDER_TYPE		= MEM_ORDER_TYPE + 1;
	
	private static final int HEAD_MODULE			= 0;
	private static final int PROC_MODULE			= HEAD_MODULE  + 1;
	private static final int NTDLL_MODULE			= PROC_MODULE  + 1;
	private static final int KERNEL_MODULE			= NTDLL_MODULE + 1;
	
	private static final long LDR_Base_Address 		= 0x241EA0;
	private static final long HEAD_LENGTH 			= 0x24;
	private static final long MODULE_LENGTH			= 0x34;	
	private static long Initialized;
	private static long SsHandle;
	
	private static ArrayList<LoaderModule> moduleList;

	static 
	{
		moduleList = CreateModuleList();
	}
	
	static void updateLDR(BPState curState) {
		// Update LDR_0
		curState.getEnvironement().getMemory().createMemory(LDR_Base_Address, 0x100, "Initialization of LDR");
		curState.getEnvironement().getMemory().setDoubleWordMemoryValue(LDR_Base_Address, new LongValue(HEAD_LENGTH));
		// Update LDR_4
		Initialized = 0x1;
		curState.getEnvironement().getMemory()
				.setDoubleWordMemoryValue(LDR_Base_Address + 4, new LongValue(Initialized));
		// Update LDR_8
		SsHandle = 0x0;
		curState.getEnvironement().getMemory().setDoubleWordMemoryValue(LDR_Base_Address + 8, new LongValue(SsHandle));
		// Update LDR_C
		for (LoaderModule lModule: moduleList)
		{
			lModule.Update(curState);
		}
	}

	public static long getLDR_Base_Address() 
	{
		return LDR_Base_Address;
	}
	
	public static ArrayList<ListEntry> createOrderList (long base, int type)
	{
		ArrayList<ListEntry> orderList = new ArrayList<>();
		long head_base  	= base;
		long proc_base  	= head_base 			+ MODULE_LENGTH;
		long ntdll_base 	= proc_base 			+ MODULE_LENGTH;
		long kernel_base 	= ntdll_base 			+ MODULE_LENGTH;
	
		boolean initType = (type == INIT_ORDER_TYPE);
		ListEntry head 		= new ListEntry(initType ? ntdll_base: proc_base, kernel_base);
		ListEntry proc 		= new ListEntry(initType ? 0x0: ntdll_base, initType ? 0x0: head_base);
		ListEntry ntdll		= new ListEntry(kernel_base, initType ? head_base: proc_base);
		ListEntry kernel	= new ListEntry(head_base, ntdll_base);
		orderList.add(head);
		orderList.add(proc);
		orderList.add(ntdll);
		orderList.add(kernel);
		
		return orderList;
	}
	
	public static ArrayList<LoaderModule> CreateModuleList()
	{
		ArrayList<LoaderModule> mList = new ArrayList<>();
		
		long basic_base		= LDR_Base_Address 	+ 0xC;
		ArrayList<ListEntry> loadOrderList 	= createOrderList(basic_base, LOAD_ORDER_TYPE);
		ArrayList<ListEntry> memOrderList  	= createOrderList(basic_base + 0x8, MEM_ORDER_TYPE);
		ArrayList<ListEntry> initOrderList  = createOrderList(basic_base + 0x10, INIT_ORDER_TYPE);
		
		LibraryModule ntdll32Module 	= getLoaderData("ntdll.dll");
		LibraryModule kernel32Module 	= getLoaderData("kernel32.dll");
		
		LoaderModule headModule = new LoaderModule(
				LDR_Base_Address + 0xC,
				loadOrderList.get(HEAD_MODULE),
				memOrderList.get(HEAD_MODULE),
				initOrderList.get(HEAD_MODULE),
				0x0, 0x0, 0x0,
				new UnicodeString(0x0, 0x0, 0x0),
				new UnicodeString(0x0, 0x0, 0x0)
		);
		
		LoaderModule procModule = new LoaderModule(
				headModule.getBaseAddress() + MODULE_LENGTH,
				loadOrderList.get(PROC_MODULE),
				memOrderList.get(PROC_MODULE),
				initOrderList.get(PROC_MODULE),
				0x0, 0x0, 0x0,
				new UnicodeString(0x0, 0x0, 0x0),
				new UnicodeString(0x0, 0x0, 0x0)
		);
		
		LoaderModule ntdllModule = new LoaderModule(
				procModule.getBaseAddress() + MODULE_LENGTH,
				loadOrderList.get(NTDLL_MODULE),
				memOrderList.get(NTDLL_MODULE),
				initOrderList.get(NTDLL_MODULE),
				(ntdll32Module != null) ? ntdll32Module.getHandle(): 0x0,
				(ntdll32Module != null) ? ntdll32Module.getEntryPoint(): 0x0,
				0x0,
				new UnicodeString(0x0, 0x0, 0x0),
				new UnicodeString(0x0, 0x0, 0x0)
		);
		
		LoaderModule kernelModule = new LoaderModule(
				ntdllModule.getBaseAddress() + MODULE_LENGTH,
				loadOrderList.get(KERNEL_MODULE),
				memOrderList.get(KERNEL_MODULE),
				initOrderList.get(KERNEL_MODULE),
				(kernel32Module != null) ? kernel32Module.getHandle(): 0x0,
				(kernel32Module != null) ? kernel32Module.getHandle(): 0x0,
				0x0,
				new UnicodeString(0x0, 0x0, 0x0),
				new UnicodeString(0x0, 0x0, 0x0)
		);
		
		mList.add(headModule);
		mList.add(procModule);
		mList.add(ntdllModule);
		mList.add(kernelModule);
		
		return mList;
	}
	
	public static LibraryModule getLoaderData(String libraryName) {
		// Get DLL's Base Address /////////////////////
		long libHandle = 0;
		// Try to find the handle of current library
		if (APIHandle.libraryHandleMap.containsValue(libraryName)) {
			for (Entry<Long, PairEntry<String, Integer>> handle : APIHandle.libraryHandleMap.entrySet()) {
				if (handle.getValue().getKey().equals(libraryName)) {
					libHandle = handle.getKey(); // DLL's Base Address
					break;
				}
			}
		} else {
			// If can not find, execute LoadLibrary API
			libHandle = (new LoadLibrary()).execute(libraryName); // DLL's Base
																	// Address
		}
		// ///////////////////////////////////////////////

		// Get DLL's Entry Point/////////////////////////
		long x = getWordExternalMemory(libHandle);
		if (x != 0x5A4D) {
			return null; // fail to reach the MZ in PE File
		}
		
		x = getDoubleWordExternalMemory(libHandle + 0x3c);
		if (x == Long.MIN_VALUE) {
			return null; // fail to reach the MZ in PE File
		}
		
		long entryPoint = getDoubleWordExternalMemory(libHandle + x + 0x28) + libHandle;
		/////////////////////////////////////////////////
		
		// Get Full DLL's name/////////////////////////
		long t3 = 100;
		
		char Filename[] = new char[(int) t3];
		HMODULE module = new HMODULE();
		module.setPointer(new Pointer(libHandle));

		String output = null;
		DWORD ret = null;

		if (libHandle == 0L) {
			output = Program.getProgram().getAbsolutePathFile();
			ret = new DWORD(output.length());
			Kernel32.INSTANCE.SetLastError(0);
		} else {
			ret = Kernel32DLL.INSTANCE.GetModuleFileName(module, Filename, new DWORD(t3));
			output = new String(Filename, 0, ret.intValue());
		}
		
		String jre_location = System.getProperties().getProperty("java.home") + File.separator + "bin" + File.separator
				+ "java";
		String jdk_location = System.getProperties().getProperty("java.home") + File.separator + "java";
		if (jdk_location.contains("jre")) {
			jdk_location = jdk_location.replace("jre", "bin");
		}
		
		if (output.startsWith(jre_location) || output.startsWith(jdk_location)) {
			output = Program.getProgram().getAbsolutePathFile();
			ret = new DWORD(output.length());
			Kernel32.INSTANCE.SetLastError(0);
		}
		
		/*
		System.out.println("DLL's Base Address=0x" +Long.toHexString(libHandle) + ", Module's Entry Point=0x" + Long.toHexString(entryPoint) + 
				", Full DLL's name= " + output+ ", Base DLL's name=" + libraryName);
		*/
		return new LibraryModule(libHandle, entryPoint, output, libraryName);
		/////////////////////////////////////////////////
	}

	private static long getWordExternalMemory(long address) {
		// TODO Auto-generated method stub
		if (address != 0) {
			ExternalMemoryReturnData ret = ExternalMemory.getWord(address);
			if (ret != null && ret.isValidAddress) {
				return ret.value.getValue();
			}
		}
		return Long.MIN_VALUE;
	}

	private static long getDoubleWordExternalMemory(long address) {
		if (address != 0) {
			ExternalMemoryReturnData ret = ExternalMemory.getDoubleWord(address);
			if (ret != null && ret.isValidAddress) {
				return ret.value.getValue();
			}
		}
		return Long.MIN_VALUE;
	}
}
