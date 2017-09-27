package v2.org.analysis.apihandle.winapi.sechost;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.Guid.GUID;
import com.sun.jna.platform.win32.WinDef.ULONG;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;


public interface SechostDLL extends StdCallLibrary {
	SechostDLL INSTANCE = (SechostDLL) Native.loadLibrary("sechost", SechostDLL.class, W32APIOptions.DEFAULT_OPTIONS);
	
	// API's Interfaces
	int QueryAllTraces ( PointerByReference PropertyArray, ULONG PropertyArrayCount, IntByReference SessionCount );

	int RemoveTraceCallback ( GUID pGuid );

}