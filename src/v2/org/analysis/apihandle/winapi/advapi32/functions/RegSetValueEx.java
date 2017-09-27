/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: RegSetValueEx.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.advapi32.functions;

import org.jakstab.asm.AbsoluteAddress;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.system.registry.RegistryHandle;
import v2.org.analysis.value.LongValue;

/**
 * The RegSetValueEx function sets the data and type of a specified value under
 * a registry key.
 *
 * @param hKey
 *            Handle to an open key. The key must have been opened with the
 *            KEY_SET_VALUE access right.
 * 
 * @param lpValueName
 *            Pointer to a string containing the name of the value to set. If a
 *            value with this name is not already present in the key, the
 *            function adds it to the key. If lpValueName is NULL or an empty
 *            string, "", the function sets the type and data for the key's
 *            unnamed or default value.
 * 
 * @param Reserved
 *            Reserved; must be zero.
 * 
 * @param dwType
 *            Type of data pointed to by the lpData parameter.
 * 
 * @param lpData
 *            Pointer to a buffer containing the data to be stored with the
 *            specified value name.
 * 
 * @param cbData
 *            Size of the information pointed to by the lpData parameter, in
 *            bytes. If the data is of type REG_SZ, REG_EXPAND_SZ, or
 *            REG_MULTI_SZ, cbData must include the size of the terminating null
 *            character or characters.
 * 
 * @return If the function succeeds, the return value is ERROR_SUCCESS. If the
 *         function fails, the return value is a nonzero error code defined in
 *         Winerror.h.
 * 
 * @author Yen Nguyen
 *
 */
public class RegSetValueEx extends Advapi32API {

	public RegSetValueEx() {
		super();
		NUM_OF_PARMS = 6;
	}


	@Override
	public void execute() {
		long hKey = this.params.get(0);
		long lpValueName = this.params.get(1);
//		long t3 = this.params.get(2);
		long dwType = this.params.get(3);
		long lpData = this.params.get(4);
		long cbData = this.params.get(5);

		String valueName = memory.getText(this, lpValueName);
		byte[] data = memory.getBytesArray(new AbsoluteAddress(lpData), (int) cbData);
		
		char[] convertedData = new char[data.length];
		for (int i = 0; i < convertedData.length; i++) {
			convertedData[i] = (char) data[i];
		}
		
		System.out.println("Handle Key:" + hKey + ", lpValueName:" + valueName + ", Reserved:" + this.params.get(2));		
		
		RegistryHandle.setRegValue(hKey, valueName, dwType, convertedData);

		int ret = 0;
		// Advapi32.INSTANCE.RegSetValueEx(new HKEY((int) t1), lpValueName,
		// (int) t3, (int) t4,
		// lpData.getBytes(), (int) t6);
		register.mov("eax", new LongValue(ret));
	}

}
