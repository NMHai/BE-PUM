package v2.org.analysis.apihandle.winapi.loadperf;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;


public interface LoadperfDLL extends StdCallLibrary {
	LoadperfDLL INSTANCE = (LoadperfDLL) Native.loadLibrary("loadperf", LoadperfDLL.class, W32APIOptions.DEFAULT_OPTIONS);
	
	// API's Interfaces
	int LoadPerfCounterTextStrings ( String commandLine, BOOL bQuietModeArg );

	int UnloadPerfCounterTextStrings ( String commandLine, BOOL bQuietModeArg );

}