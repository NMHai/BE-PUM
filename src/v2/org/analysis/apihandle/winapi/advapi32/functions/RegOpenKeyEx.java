/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: RegOpenKeyEx.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.advapi32.functions;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.system.registry.ERegKeySecuritynAccessRights;
import v2.org.analysis.system.registry.RegistryHandle;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Advapi32;
import com.sun.jna.platform.win32.WinReg.HKEY;
import com.sun.jna.platform.win32.WinReg.HKEYByReference;

/**
 * The RegOpenKeyEx function opens the specified registry key. Note that key
 * names are not case sensitive.
 * 
 * @param hKey
 *            Handle to an open key.
 * 
 * @param lpSubKey
 *            Pointer to a null-terminated string containing the name of the
 *            subkey to open.
 * 
 * @param ulOptions
 *            Reserved; must be zero.
 * 
 * @param samDesired
 *            Access mask that specifies the desired access rights to the key.
 *            The function fails if the security descriptor of the key does not
 *            permit the requested access for the calling process.
 * 
 * @param phkResult
 *            Pointer to a variable that receives a handle to the opened key. If
 *            the key is not one of the predefined registry keys, call the
 *            RegCloseKey function after you have finished using the handle.
 * 
 * @return If the function succeeds, the return value is ERROR_SUCCESS. If the
 *         function fails, the return value is a nonzero error code defined in
 *         Winerror.h.
 * 
 * @author Yen Nguyen
 * 
 */
public class RegOpenKeyEx extends Advapi32API {

	public RegOpenKeyEx() {
		super();
		NUM_OF_PARMS = 5;
	}


	@Override
	public void execute() {
		long hKeyValue = this.params.get(0);
		long t2 = this.params.get(1);
//		long t3 = this.params.get(2);
		long t4 = this.params.get(3);
		long t5 = this.params.get(4);

		String lpSubKey = (t2 == 0) ? null : memory.getText(this, t2);
		HKEYByReference phkResult = new HKEYByReference();
		
		System.out.println("Subke:" + lpSubKey);

		int ret = Advapi32.INSTANCE.RegOpenKeyEx(new HKEY(new Pointer(hKeyValue)), lpSubKey, 0 /*NULL - reversed*/, (int) t4, phkResult);
		register.mov("eax", new LongValue(ret));

		long keyResult = Pointer.nativeValue(phkResult.getValue().getPointer());
		
		if (keyResult == 2L) {
			// Key not found, try to find in virtual environment
			if (RegistryHandle.isKeyExist(hKeyValue, lpSubKey)) {
				ret = 0; // ERROR_SUCCESS 0L
				keyResult = RegistryHandle.generateNewHandle();
			}
		}
		
		memory.setDoubleWordMemoryValue(t5, new LongValue(keyResult));
		
		// Add handle to map
		RegistryHandle.addHandle(keyResult, hKeyValue, lpSubKey, ERegKeySecuritynAccessRights.fromLong(t4));
	}

}
