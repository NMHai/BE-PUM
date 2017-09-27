package v2.org.analysis.apihandle.winapi.ntmsapi;

import com.sun.jna.Native;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinDef.CHARByReference;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

import v2.org.analysis.apihandle.structures.NTMS_NOTIFICATIONINFORMATION;


public interface NtmsapiDLL extends StdCallLibrary {
	NtmsapiDLL INSTANCE = (NtmsapiDLL) Native.loadLibrary("ntmsapi", NtmsapiDLL.class, W32APIOptions.DEFAULT_OPTIONS);
	
	// API's Interfaces
	int BeginNtmsDeviceChangeDetection ( HANDLE hSession, HANDLE lpDetectHandle );

	int CloseNtmsNotification ( HANDLE hNotification );

	int CloseNtmsSession ( HANDLE hSession );

	int EjectDiskFromSADrive ( CHARByReference lpComputerName, CHARByReference lpAppName, CHARByReference lpDeviceName, HWND hWnd, CHARByReference lpTitle, CHARByReference lpMessage, DWORD dwOptions );

	int EndNtmsDeviceChangeDetection ( HANDLE hSession, HANDLE hDetectHandle );

	int ExportNtmsDatabase ( HANDLE hSession );

	int GetVolumesFromDrive ( CHARByReference pszDriveName, WString[] VolumeNameBufferPtr, WString[] DriveLetterBufferPtr );

	int ImportNtmsDatabase ( HANDLE hSession );

	int OpenNtmsNotification ( HANDLE hSession, DWORD dwType );

	int OpenNtmsSession ( String lpServer, String lpApplication, DWORD dwOptions );

	int WaitForNtmsNotification ( HANDLE hNotification, NTMS_NOTIFICATIONINFORMATION lpNotificationInformation, DWORD dwTimeout );

}