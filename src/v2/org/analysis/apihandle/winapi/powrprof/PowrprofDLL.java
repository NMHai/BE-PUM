package v2.org.analysis.apihandle.winapi.powrprof;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.CHARByReference;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

import v2.org.analysis.apihandle.structures.GLOBAL_POWER_POLICY;
import v2.org.analysis.apihandle.structures.MACHINE_PROCESSOR_POWER_POLICY;
import v2.org.analysis.apihandle.structures.POWER_POLICY;
import v2.org.analysis.apihandle.structures.SYSTEM_POWER_CAPABILITIES;


public interface PowrprofDLL extends StdCallLibrary {
	PowrprofDLL INSTANCE = (PowrprofDLL) Native.loadLibrary("powrprof", PowrprofDLL.class, W32APIOptions.DEFAULT_OPTIONS);
	
	// API's Interfaces
	int CanUserWritePwrScheme ( );

	int DeletePwrScheme ( UINT uiIndex );

	int GetActivePwrScheme ( IntByReference puiID );

	int GetCurrentPowerPolicies ( GLOBAL_POWER_POLICY pGlobalPowerPolicy, POWER_POLICY pPowerPolicy );

	int GetPwrCapabilities ( SYSTEM_POWER_CAPABILITIES lpSystemPowerCapabilities );

	int GetPwrDiskSpindownRange ( IntByReference RangeMax, IntByReference RangeMin );

	int IsPwrHibernateAllowed ( );

	int IsPwrShutdownAllowed ( );

	int IsPwrSuspendAllowed ( );

	int ReadGlobalPwrPolicy ( GLOBAL_POWER_POLICY pGlobalPowerPolicy );

	int ReadProcessorPwrScheme ( UINT uiID, MACHINE_PROCESSOR_POWER_POLICY pMachineProcessorPowerPolicy );

	int ReadPwrScheme ( UINT uiID, POWER_POLICY pPowerPolicy );

	int SetActivePwrScheme ( UINT uiID, GLOBAL_POWER_POLICY lpGlobalPowerPolicy, POWER_POLICY lpPowerPolicy );

	int SetSuspendState ( byte Hibernate, byte ForceCritical, byte DisableWakeEvent );

	int WriteGlobalPwrPolicy ( GLOBAL_POWER_POLICY pGlobalPowerPolicy );

	int WriteProcessorPwrScheme ( UINT ID, MACHINE_PROCESSOR_POWER_POLICY pMachineProcessorPowerPolicy );

	int WritePwrScheme ( IntByReference puiID, CHARByReference lpszName, CHARByReference lpszDescription, POWER_POLICY pPowerPolicy );

}