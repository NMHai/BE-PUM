package v2.org.analysis.apihandle.winapi.kernel32;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HMODULE;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.win32.StdCallLibrary;

/**
 * Use this interface in case some of APIs do not work with Kernel32DLL
 * 
 * @author Yen Nguyen
 *
 */
public interface Kernel32DLLwithoutOption extends StdCallLibrary {

	// int getValue(int value);
	// int GetProcAddressBP(String lpProcName, HMODULE hM);
	// HMODULE GetModuleHandleBP(WString lpModuleName);

	Kernel32DLLwithoutOption INSTANCE = (Kernel32DLLwithoutOption) Native.loadLibrary("kernel32",
			Kernel32DLLwithoutOption.class);
	Kernel32DLLwithoutOption SYNC_INSTANCE = (Kernel32DLLwithoutOption) Native.synchronizedLibrary(INSTANCE);

	int GetProcAddress(HMODULE hM, String lpProcName);
	int GetProcAddress(HMODULE hM, Pointer lpProcName);
	
	HANDLE GetStdHandle(int nStdHandle);
}
