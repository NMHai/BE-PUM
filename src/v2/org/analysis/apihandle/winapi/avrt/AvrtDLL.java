package v2.org.analysis.apihandle.winapi.avrt;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.Guid.GUID;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.platform.win32.WinNT.HANDLEByReference;
import com.sun.jna.platform.win32.WinNT.LARGE_INTEGER;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;


public interface AvrtDLL extends StdCallLibrary {
	AvrtDLL INSTANCE = (AvrtDLL) Native.loadLibrary("avrt", AvrtDLL.class, W32APIOptions.DEFAULT_OPTIONS);
	
	// API's Interfaces
	int AvQuerySystemResponsiveness ( HANDLE AvrtHandle, IntByReference SystemResponsivenessValue );

	int AvRevertMmThreadCharacteristics ( HANDLE AvrtHandle );

	int AvRtCreateThreadOrderingGroup ( HANDLEByReference Context, LARGE_INTEGER Period, GUID ThreadOrderingGuid, LARGE_INTEGER Timeout );

	int AvRtCreateThreadOrderingGroupEx ( HANDLEByReference Context, LARGE_INTEGER Period, GUID ThreadOrderingGuid, LARGE_INTEGER Timeout, String TaskName );

	int AvRtDeleteThreadOrderingGroup ( HANDLE Context );

	int AvRtJoinThreadOrderingGroup ( HANDLEByReference Context, GUID ThreadOrderingGuid, BOOL Before );

	int AvRtLeaveThreadOrderingGroup ( HANDLE Context );

	int AvRtWaitOnThreadOrderingGroup ( HANDLE Context );

	int AvSetMmMaxThreadCharacteristics ( String FirstTask, String SecondTask, IntByReference TaskIndex );

	int AvSetMmThreadCharacteristics ( String TaskName, IntByReference TaskIndex );

	int AvSetMmThreadPriority ( HANDLE AvrtHandle, int Priority );

}