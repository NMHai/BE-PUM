/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.shell32
 * File name: Shel32DLL.java
 * Created date: Aug 5, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.shell32;

import v2.org.analysis.apihandle.structures.OPENASINFO;
import v2.org.analysis.apihandle.structures.SHELLEXECUTEINFO;
import v2.org.analysis.apihandle.structures.SHELLFLAGSTATE;
import v2.org.analysis.apihandle.structures.SHQUERYRBINFO;
import v2.org.analysis.apihandle.structures.ULARGE_INTEGER;
import v2.org.analysis.apihandle.winapi.structures.ShellApi.SHFILEINFO;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.BaseTSD.DWORD_PTR;
import com.sun.jna.platform.win32.ShellAPI;
import com.sun.jna.platform.win32.WinBase.FILETIME;
import com.sun.jna.platform.win32.WinBase.SECURITY_ATTRIBUTES;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.CHARByReference;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HICON;
import com.sun.jna.platform.win32.WinDef.HINSTANCE;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.LONG;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.platform.win32.WinDef.ULONG;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.platform.win32.WinNT.HANDLEByReference;
import com.sun.jna.platform.win32.WinReg.HKEY;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.ShortByReference;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

/**
 * @author Yen Nguyen
 *
 */
public interface Shell32DLL extends ShellAPI, StdCallLibrary {
	Shell32DLL INSTANCE = (Shell32DLL) Native.loadLibrary("shell32", Shell32DLL.class, W32APIOptions.DEFAULT_OPTIONS);

	/**
	 * 
	 * @param pszPath
	 * @param dwFileAttributes
	 * @param psfi
	 * @param cbFileInfo
	 * @param uFlags
	 * @return
	 */
	DWORD_PTR SHGetFileInfo(
	/* _In_ */String pszPath, DWORD dwFileAttributes,
	/* _Inout_ */SHFILEINFO psfi, UINT cbFileInfo, UINT uFlags);

	/**
	 * Displays a ShellAbout dialog box.
	 * 
	 * @param hWnd
	 *            A window handle to a parent window. This parameter can be
	 *            NULL.
	 * 
	 * @param szApp
	 *            A pointer to a null-terminated string that contains text to be
	 *            displayed in the title bar of the ShellAbout dialog box and on
	 *            the first line of the dialog box after the text "Microsoft".
	 *            If the text contains a separator (#) that divides it into two
	 *            parts, the function displays the first part in the title bar
	 *            and the second part on the first line after the text
	 *            "Microsoft".
	 * 
	 * @param szOtherStuff
	 *            A pointer to a null-terminated string that contains text to be
	 *            displayed in the dialog box after the version and copyright
	 *            information. This parameter can be NULL.
	 * 
	 * @param hIcon
	 *            The handle of an icon that the function displays in the dialog
	 *            box. This parameter can be NULL, in which case the function
	 *            displays the Windows icon.
	 * 
	 * @return TRUE if successful; otherwise, FALSE.
	 */
	int ShellAbout(
	/* _In_opt_ */HWND hWnd,
	/* _In_ */String szApp,
	/* _In_opt_ */String szOtherStuff,
	/* _In_opt_ */HICON hIcon);
	
	// API's Interfaces
		int CommandLineToArgvW ( CHARByReference lpCmdLine, IntByReference pNumArgs );

		int DuplicateIcon ( HINSTANCE hInst, HICON hIcon );

		int ExtractAssociatedIcon ( HINSTANCE hInst, String lpIconPath, ShortByReference lpiIcon );

		int ExtractIcon ( HINSTANCE hInst, String lpszExeFileName, UINT nIconIndex );

		int ExtractIconEx ( String lpszFile, int nIconIndex, HANDLEByReference phiconLarge, HANDLEByReference phiconSmall, UINT nIcons );

		void SHAddToRecentDocs ( UINT uFlags, Pointer pv );

		int SHAppBarMessage ( DWORD dwMessage, APPBARDATA pData );

		void SHChangeNotify ( LONG wEventId, UINT uFlags, Pointer dwItem1, Pointer dwItem2 );

		int SHCreateDirectory ( HWND hwnd, CHARByReference pszPath );

		int SHCreateDirectoryEx ( HWND hwnd, String pszPath, SECURITY_ATTRIBUTES psa );

//		int ShellAbout ( HWND hWnd, String szApp, String szOtherStuff, HICON hIcon );

		int ShellExecute ( HWND hwnd, String lpOperation, String lpFile, String lpParameters, String lpDirectory, int nShowCmd );

		int ShellExecuteEx ( SHELLEXECUTEINFO pExecInfo );

		int SHEmptyRecycleBin ( HWND hwnd, String pszRootPath, DWORD dwFlags );

		int SHEnumerateUnreadMailAccounts ( HKEY hKeyUser, DWORD dwIndex, String pszMailAddress, int cchMailAddress );

		int SHFileOperation ( SHFILEOPSTRUCT lpFileOp );

		int SHFormatDrive ( HWND hwnd, UINT drive, UINT fmtID, UINT options );

		void SHFreeNameMappings ( HANDLE hNameMappings );

		int SHGetDiskFreeSpace ( String pszVolume, ULARGE_INTEGER pqwFreeCaller, ULARGE_INTEGER pqwTot, ULARGE_INTEGER pqwFree );

		int SHGetDiskFreeSpaceEx ( String pszDirectoryName, ULARGE_INTEGER pulFreeBytesAvailableToCaller, ULARGE_INTEGER pulTotalNumberOfBytes, ULARGE_INTEGER pulTotalNumberOfFreeBytes );

//		int SHGetFileInfo ( String pszPath, DWORD dwFileAttributes, SHFILEINFO psfi, UINT cbFileInfo, UINT uFlags );

		int SHGetFolderPath ( HWND hwndOwner, int nFolder, HANDLE hToken, DWORD dwFlags, String pszPath );

		int SHGetFolderPathAndSubDir ( HWND hwnd, int csidl, HANDLE hToken, DWORD dwFlags, String pszSubDir, String pszPath );

		int SHGetIconOverlayIndex ( String pszIconPath, int iIconIndex );

		int SHGetNewLinkInfo ( String pszLinkTo, String pszDir, String pszName, IntByReference pfMustCopy, UINT uFlags );

		void SHGetSettings ( SHELLFLAGSTATE lpsfs, DWORD dwMask );

		int SHGetSpecialFolderPath ( HWND hwndOwner, String lpszPath, int csidl, BOOL fCreate );

		int SHGetUnreadMailCount ( HKEY hKeyUser, String pszMailAddress, IntByReference pdwCount, FILETIME pFileTime, String pszShellExecuteCommand, int cchShellExecuteCommand );

		int SHInvokePrinterCommand ( HWND hwnd, UINT uAction, String lpBuf1, String lpBuf2, BOOL fModal );

		int SHIsFileAvailableOffline ( CHARByReference pszPath, IntByReference pdwStatus );

		int SHLoadNonloadedIconOverlayIdentifiers ( );

		int SHOpenWithDialog ( HWND hwndParent, OPENASINFO poainfo );

		int SHQueryRecycleBin ( String pszRootPath, SHQUERYRBINFO pSHQueryRBInfo );

		int SHSetFolderPath ( int csidl, HANDLE hToken, DWORD dwFlags, String pszPath );

		int SHSetUnreadMailCount ( String pszMailAddress, DWORD dwCount, String pszShellExecuteCommand );

		int SHTestTokenMembership ( HANDLE hToken, ULONG ulRID );

		void SHUpdateImage ( String pszHashItem, int iIndex, UINT uFlags, int iImageIndex );
}
