package v2.org.analysis.apihandle.winapi.crtdll;

import com.sun.jna.Native;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

/**
 * @author HaiNM
 */
public interface CrtdllDLL extends StdCallLibrary {
	CrtdllDLL INSTANCE = (CrtdllDLL) Native.loadLibrary("crtdll", CrtdllDLL.class,
			W32APIOptions.DEFAULT_OPTIONS);
	CrtdllDLL SYNC_INSTANCE = (CrtdllDLL) Native.synchronizedLibrary(INSTANCE);
	
//	int __getmainargs(IntByReference _Argc, Pointer _Argv, Memory _Env, int _DoWildCard);
//	int __getmainargs(IntByReference _Argc, Pointer _Argv, Memory _Env, int _DoWildCard, _startupinfo _StartInfo);
}
