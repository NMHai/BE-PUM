/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: RegOpenKey.java
 * Created date: May 30, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.advapi32.functions;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.system.registry.ERegKeySecuritynAccessRights;
import v2.org.analysis.system.registry.RegistryHandle;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.LONG;
import com.sun.jna.platform.win32.WinReg.HKEY;
import com.sun.jna.platform.win32.WinReg.HKEYByReference;

/**
 * Opens the specified registry key.
 * 
 * @param hKey
 *            A handle to an open registry key. This handle is returned by the
 *            RegCreateKeyEx or RegOpenKeyEx function, or it can be one of the
 *            following predefined keys: HKEY_CLASSES_ROOT HKEY_CURRENT_CONFIG
 *            HKEY_CURRENT_USER HKEY_LOCAL_MACHINE HKEY_USERS
 * 
 * @param lpSubKey
 *            The name of the registry key to be opened. This key must be a
 *            subkey of the key identified by the hKey parameter. Key names are
 *            not case sensitive. If this parameter is NULL or a pointer to an
 *            empty string, the function returns the same handle that was passed
 *            in.
 * 
 * @param phkResult
 *            A pointer to a variable that receives a handle to the opened key.
 *            If the key is not one of the predefined registry keys, call the
 *            RegCloseKey function after you have finished using the handle.
 * 
 * @return If the function succeeds, the return value is ERROR_SUCCESS. If the
 *         function fails, the return value is a nonzero error code defined in
 *         Winerror.h. You can use the FormatMessage function with the
 *         FORMAT_MESSAGE_FROM_SYSTEM flag to get a generic description of the
 *         error.
 * 
 * @author Yen Nguyen
 *
 */
public class RegOpenKey extends Advapi32API {

	public RegOpenKey() {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		long hKeyValue = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);

		HKEY hKey = new HKEY(new Pointer(hKeyValue));
		String lpSubKey = (t2 == 0L) ? null : memory.getText(this, t2);
		HKEYByReference phkResult = new HKEYByReference();

		System.out.println("lpSubKey: " + lpSubKey);

		LONG ret = Advapi32DLL.INSTANCE.RegOpenKey(hKey, lpSubKey, phkResult);

		long keyResult = Pointer.nativeValue(phkResult.getValue().getPointer());
		
		if (keyResult == 2L) {
			// Key not found, try to find in virtual environment
			if (RegistryHandle.isKeyExist(hKeyValue, lpSubKey)) {
				ret.setValue(0L); // ERROR_SUCCESS 0L
				keyResult = RegistryHandle.generateNewHandle();
			}
		}

		register.mov("eax", new LongValue(ret.longValue()));
		memory.setDoubleWordMemoryValue(t3, new LongValue(keyResult));

		RegistryHandle.addHandle(keyResult, hKeyValue, lpSubKey, ERegKeySecuritynAccessRights.KEY_ALL_ACCESS);
	}

}
