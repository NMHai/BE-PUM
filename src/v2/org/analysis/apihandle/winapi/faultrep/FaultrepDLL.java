package v2.org.analysis.apihandle.winapi.faultrep;

import com.sun.jna.Native;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;


public interface FaultrepDLL extends StdCallLibrary {
	FaultrepDLL INSTANCE = (FaultrepDLL) Native.loadLibrary("faultrep", FaultrepDLL.class, W32APIOptions.DEFAULT_OPTIONS);
	
	// API's Interfaces
	int AddERExcludedApplication ( String szApplication );

}