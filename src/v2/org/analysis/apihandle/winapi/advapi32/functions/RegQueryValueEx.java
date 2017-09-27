/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: RegQueryValueEx.java
 * Created date: Aug 2, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.advapi32.functions;

import java.nio.ByteBuffer;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.system.registry.EKeyValueType;
import v2.org.analysis.system.registry.RegistryHandle;
import v2.org.analysis.util.PairEntry;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.DWORDByReference;
import com.sun.jna.platform.win32.WinDef.LONG;
import com.sun.jna.platform.win32.WinReg.HKEY;

/**
 * Retrieves the type and data for the specified value name associated with an
 * open registry key.
 * 
 * @param hKey
 *            A handle to an open registry key. The key must have been opened
 *            with the KEY_QUERY_VALUE access right. For more information, see
 *            Registry Key Security and Access Rights.
 * 
 * @param lpValueName
 *            The name of the registry value.
 * 
 * @param lpReserved
 *            This parameter is reserved and must be NULL.
 * 
 * @param lpType
 *            A pointer to a variable that receives a code indicating the type
 *            of data stored in the specified value. For a list of the possible
 *            type codes, see Registry Value Types. The lpType parameter can be
 *            NULL if the type code is not required.
 * 
 * @param lpData
 *            A pointer to a buffer that receives the value's data. This
 *            parameter can be NULL if the data is not required.
 * 
 * @param lpcbData
 *            A pointer to a variable that specifies the size of the buffer
 *            pointed to by the lpData parameter, in bytes. When the function
 *            returns, this variable contains the size of the data copied to
 *            lpData. The lpcbData parameter can be NULL only if lpData is NULL.
 * 
 * @return If the function succeeds, the return value is ERROR_SUCCESS. If the
 *         function fails, the return value is a system error code. If the
 *         lpData buffer is too small to receive the data, the function returns
 *         ERROR_MORE_DATA. If the lpValueName registry value does not exist,
 *         the function returns ERROR_FILE_NOT_FOUND.
 * 
 * @author Yen Nguyen
 *
 */
public class RegQueryValueEx extends Kernel32API {

	public RegQueryValueEx() {
		super();
		NUM_OF_PARMS = 6;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		// long t3 = this.params.get(2);
		long t4 = this.params.get(3);
		long t5 = this.params.get(4);
		long t6 = this.params.get(5);

		HKEY hKey = new HKEY(new Pointer(t1));
		String lpValueName = (t2 == 0L) ? null : memory.getText(this, t2);
		System.out.println("lpValueName:" + lpValueName);
		DWORDByReference lpReserved = null;// reversed!!
		// (t3 == 0L) ? null : new DWORDByReference();
		DWORDByReference lpType = (t4 == 0L) ? null : new DWORDByReference();
		DWORDByReference lpcbData = (t6 == 0L) ? null : new DWORDByReference(new DWORD(
				((LongValue) memory.getDoubleWordMemoryValue(t6)).getValue()));
		ByteBuffer lpData = (t5 == 0L) ? null : ByteBuffer.allocate(lpcbData.getValue().intValue() + 1);

		LONG ret = Advapi32DLL.INSTANCE.RegQueryValueEx(hKey, lpValueName, lpReserved, lpType, lpData, lpcbData);

		register.mov("eax", new LongValue(ret.longValue()));

		if (t4 != 0L) {
			memory.setDoubleWordMemoryValue(t4, new LongValue(lpType.getValue().longValue()));
		}

		if (t5 != 0L) {
			byte[] bufferArray = lpData.array();
			for (int i = 0; i < bufferArray.length; i++) {
				memory.setByteMemoryValue(t5 + i, new LongValue(bufferArray[i]));
			}
			// memory.setByteMemoryValue(new X86MemoryOperand(DataType.INT8,
			// t5), new LongValue(lpData.getValue()));
		}

		if (t6 != 0L) {
			memory.setDoubleWordMemoryValue(t6, new LongValue(lpcbData.getValue().longValue()));
		}

		// In case of not existing, try to find it in virtual registry
		// #define ERROR_FILE_NOT_FOUND 2L
		if (ret.longValue() == 2L) {
			PairEntry<EKeyValueType, char[]> regEntry = RegistryHandle.queryRegValue(hKey, lpValueName);

			if (regEntry == null) {
				return;
			}

			// Set data
			char[] bufferArray = regEntry.getValue();
			for (int i = 0; i < bufferArray.length; i++) {
				memory.setByteMemoryValue(t5 + i, new LongValue(bufferArray[i]));
			}
			// Set type
			memory.setDoubleWordMemoryValue(t4, new LongValue(regEntry.getKey().getValue()));
			// Set size
			memory.setDoubleWordMemoryValue(t6, new LongValue(bufferArray.length));

			// Return ERORR_SUCCESS 0L
			register.mov("eax", new LongValue(0));
		}
	}
}
