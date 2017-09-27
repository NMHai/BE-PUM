package v2.org.analysis.apihandle.winapi.winspool;

import v2.org.analysis.apihandle.structures.DEVMODE;
import v2.org.analysis.apihandle.structures.PRINTER_NOTIFY_INFO;
import v2.org.analysis.apihandle.winapi.structures.LPPRINTER_DEFAULTS;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.WORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;


public interface WinspoolDLL extends StdCallLibrary {
	WinspoolDLL INSTANCE = (WinspoolDLL) Native.loadLibrary("winspool", WinspoolDLL.class, W32APIOptions.DEFAULT_OPTIONS);
	
	// API's Interfaces
	int AbortPrinter ( HANDLE hPrinter );

	int AddForm ( HANDLE hPrinter, DWORD Level, byte pForm );

	int AddJob ( HANDLE hPrinter, DWORD Level, byte[] pData, DWORD cbBuf, IntByReference pcbNeeded );

	int AddMonitor ( String pName, DWORD Level, byte pMonitors );

	int AddPort ( String pName, HWND hWnd, String pMonitorName );

	int AddPrinter ( PointerByReference pName, DWORD Level, byte pPrinter );

	int AddPrinterConnection ( String pName );

	int AddPrinterDriver ( String pName, DWORD Level, byte pDriverInfo );

	int AddPrinterDriverEx ( String pName, DWORD Level, byte pDriverInfo, DWORD dwFileCopyFlags );

	int AddPrintProcessor ( String pName, String pEnvironment, String pPathName, String pPrintProcessorName );

	int AddPrintProvidor ( String pName, DWORD Level, byte pProviderInfo );

	int AdvancedDocumentProperties ( HWND hWnd, HANDLE hPrinter, String pDeviceName, DEVMODE pDevModeOutput, DEVMODE pDevModeInput );

	int ClosePrinter ( HANDLE hPrinter );

	int ConfigurePort ( String pName, HWND hWnd, String pPortName );

	int ConnectToPrinterDlg ( HWND hwnd, DWORD Flags );

	int DeleteForm ( HANDLE hPrinter, String pFormName );

	int DeleteMonitor ( String pName, String pEnvironment, String pMonitorName );

	int DeletePort ( String pName, HWND hWnd, String pPortName );

	int DeletePrinter ( HANDLE hPrinter );

	int DeletePrinterConnection ( String pName );

	int DeletePrinterData ( HANDLE hPrinter, String pValueName );

	int DeletePrinterDataEx ( HANDLE hPrinter, String pKeyName, String pValueName );

	int DeletePrinterDriver ( String pName, String pEnvironment, String pDriverName );

	int DeletePrinterDriverEx ( String pName, String pEnvironment, String pDriverName, DWORD dwDeleteFlag, DWORD dwVersionFlag );

	int DeletePrinterKey ( HANDLE hPrinter, String pKeyName );

	int DeletePrintProcessor ( String pName, String pEnvironment, String pPrintProcessorName );

	int DeletePrintProvidor ( String pName, String pEnvironment, String pPrintProviderName );

	int DeviceCapabilities ( String pDevice, String pPort, WORD fwCapability, char[] pOutput, DEVMODE pDevMode );

	int DocumentProperties ( HWND hWnd, HANDLE hPrinter, String pDeviceName, DEVMODE pDevModeOutput, DEVMODE pDevModeInput, DWORD fMode );

	int EndDocPrinter ( HANDLE hPrinter );

	int EndPagePrinter ( HANDLE hPrinter );

	int EnumForms ( HANDLE hPrinter, DWORD Level, byte pForm, DWORD cbBuf, IntByReference pcbNeeded, IntByReference pcReturned );

	int EnumJobs ( HANDLE hPrinter, DWORD FirstJob, DWORD NoJobs, DWORD Level, byte[] pJob, DWORD cbBuf, IntByReference pcbNeeded, IntByReference pcReturned );

	int EnumMonitors ( String pName, DWORD Level, byte[] pMonitors, DWORD cbBuf, IntByReference pcbNeeded, IntByReference pcReturned );

	int EnumPorts ( String pName, DWORD Level, byte[] pPorts, DWORD cbBuf, IntByReference pcbNeeded, IntByReference pcReturned );

	int EnumPrinterData ( HANDLE hPrinter, DWORD dwIndex, char[] pValueName, DWORD cbValueName, IntByReference pcbValueName, IntByReference pType, byte[] pData, DWORD cbData, IntByReference pcbData );

	int EnumPrinterDataEx ( HANDLE hPrinter, String pKeyName, byte[] pEnumValues, DWORD cbEnumValues, IntByReference pcbEnumValues, IntByReference pnEnumValues );

	int EnumPrinterDrivers ( String pName, String pEnvironment, DWORD Level, byte[] pDriverInfo, DWORD cbBuf, IntByReference pcbNeeded, IntByReference pcReturned );

	int EnumPrinterKey ( HANDLE hPrinter, String pKeyName, char[] pSubkey, DWORD cbSubkey, IntByReference pcbSubkey );

	int EnumPrinters ( DWORD Flags, String Name, DWORD Level, byte[] pPrinterEnum, DWORD cbBuf, IntByReference pcbNeeded, IntByReference pcReturned );

	int EnumPrintProcessorDatatypes ( String pName, String pPrintProcessorName, DWORD Level, byte[] pDatatypes, DWORD cbBuf, IntByReference pcbNeeded, IntByReference pcReturned );

	int EnumPrintProcessors ( String pName, String pEnvironment, DWORD Level, byte[] pPrintProcessorInfo, DWORD cbBuf, IntByReference pcbNeeded, IntByReference pcReturned );

	int FindClosePrinterChangeNotification ( HANDLE hChange );

	int FreePrinterNotifyInfo ( PRINTER_NOTIFY_INFO pPrinterNotifyInfo );

	int GetDefaultPrinter ( char[] pszBuffer, IntByReference pcchBuffer );

	int GetForm ( HANDLE hPrinter, String pFormName, DWORD Level, byte[] pForm, DWORD cbBuf, IntByReference pcbNeeded );

	int GetJob ( HANDLE hPrinter, DWORD JobId, DWORD Level, byte[] pJob, DWORD cbBuf, IntByReference pcbNeeded );

	int GetPrinter ( HANDLE hPrinter, DWORD Level, byte[] pPrinter, DWORD cbBuf, IntByReference pcbNeeded );

	int GetPrinterData ( HANDLE hPrinter, String pValueName, IntByReference pType, byte[] pData, DWORD nSize, IntByReference pcbNeeded );

	int GetPrinterDataEx ( HANDLE hPrinter, String pKeyName, String pValueName, IntByReference pType, byte[] pData, DWORD nSize, IntByReference pcbNeeded );

	int GetPrinterDriver ( HANDLE hPrinter, String pEnvironment, DWORD Level, byte[] pDriverInfo, DWORD cbBuf, IntByReference pcbNeeded );

	int GetPrinterDriverDirectory ( String pName, String pEnvironment, DWORD Level, byte[] pDriverDirectory, DWORD cbBuf, IntByReference pcbNeeded );

	int GetPrintProcessorDirectory ( String pName, String pEnvironment, DWORD Level, byte[] pPrintProcessorInfo, DWORD cbBuf, IntByReference pcbNeeded );

	int OpenPrinter ( String pPrinterName, HANDLE phPrinter, LPPRINTER_DEFAULTS pDefault );

	int PrinterProperties ( HWND hWnd, HANDLE hPrinter );

	int ResetPrinter ( HANDLE hPrinter, LPPRINTER_DEFAULTS pDefault );

	int ScheduleJob ( HANDLE hPrinter, DWORD dwJobID );

	int SetDefaultPrinter ( String pszPrinter );

	int SetForm ( HANDLE hPrinter, String pFormName, DWORD Level, String pForm );

	int SetJob ( HANDLE hPrinter, DWORD JobId, DWORD Level, byte pJob, DWORD Command );

	int SetPort ( String pName, String pPortName, DWORD dwLevel, byte pPortInfo );

	int SetPrinter ( HANDLE hPrinter, DWORD Level, byte[] pPrinter, DWORD Command );

	int SetPrinterData ( HANDLE hPrinter, String pValueName, DWORD Type, byte[] pData, DWORD cbData );

	int SetPrinterDataEx ( HANDLE hPrinter, String pKeyName, String pValueName, DWORD Type, byte[] pData, DWORD cbData );

	int StartDocPrinter ( HANDLE hPrinter, DWORD Level, byte pDocInfo );

	int StartPagePrinter ( HANDLE hPrinter );

}