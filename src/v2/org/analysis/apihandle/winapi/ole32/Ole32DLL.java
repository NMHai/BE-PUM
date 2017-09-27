/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.ole32
 * File name: Ole32DLL.java
 * Created date: Aug 5, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.ole32;

import v2.org.analysis.apihandle.winapi.structures.Guid.GUID;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinBase.FILETIME;
import com.sun.jna.platform.win32.WinDef.LPVOID;
import com.sun.jna.platform.win32.WinNT.HRESULT;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

/**
 * @author Yen Nguyen
 *
 */
public interface Ole32DLL extends StdCallLibrary {
	/** The instance. */
	Ole32DLL INSTANCE = (Ole32DLL) Native.loadLibrary("Ole32", Ole32DLL.class, W32APIOptions.DEFAULT_OPTIONS);
	Ole32DLL SYNC_INSTANCE = (Ole32DLL) Native.synchronizedLibrary(INSTANCE);

	/**
	 * Initializes the COM library on the current apartment, identifies the
	 * concurrency model as single-thread apartment (STA), and enables
	 * additional functionality described in the Remarks section below.
	 * Applications must initialize the COM library before they can call COM
	 * library functions other than CoGetMalloc and memory allocation functions.
	 * 
	 * @param pvReserved
	 *            This parameter is reserved and must be NULL.
	 * 
	 * @return This function returns S_OK on success. Other possible values
	 *         include the following.
	 */
	HRESULT OleInitialize(/* _In_ */LPVOID pvReserved);

	/**
	 * Closes the COM library on the apartment, releases any class factories,
	 * other COM objects, or servers held by the apartment, disables RPC on the
	 * apartment, and frees any resources the apartment maintains.
	 */
	void OleUninitialize();

	/**
	 * Creates a GUID, a unique 128-bit integer used for CLSIDs and interface
	 * identifiers.
	 * 
	 * @param pguid
	 *            A pointer to the requested GUID.
	 * 
	 * @return S_OK if the GUID was successfully created.
	 */
	HRESULT CoCreateGuid(GUID pguid);

	/**
	 * Returns the current time as a FILETIME structure.
	 * 
	 * @param lpFileTime
	 *            A pointer to the FILETIME structure that receives the current
	 *            time.
	 * 
	 * @return This function returns S_OK to indicate success.
	 */
	HRESULT CoFileTimeNow(FILETIME lpFileTime);

	/**
	 * Initializes the COM library on the current thread and identifies the
	 * concurrency model as single-thread apartment (STA). New applications
	 * should call CoInitializeEx instead of CoInitialize. If you want to use
	 * the Windows Runtime, you must call Windows::Foundation::Initialize
	 * instead.
	 * 
	 * @param pvReserved
	 *            This parameter is reserved and must be NULL.
	 * 
	 * @return This function can return the standard return values E_INVALIDARG,
	 *         E_OUTOFMEMORY, and E_UNEXPECTED, as well as the following values.
	 */
	HRESULT CoInitialize(
	/* _In_opt_ */LPVOID pvReserved);

	/**
	 * Closes the COM library on the current thread, unloads all DLLs loaded by
	 * the thread, frees any other resources that the thread maintains, and
	 * forces all RPC connections on the thread to close.
	 */
	void CoUninitialize();

	// HRESULT CoCreateInstance(
	// /*_In_*/ REFCLSID rclsid,
	// /*_In_*/ LPUNKNOWN pUnkOuter,
	// /*_In_*/ DWORD dwClsContext,
	// /*_In_*/ REFIID riid,
	// /*_Out_*/ PointerByReference ppv
	// );
}
