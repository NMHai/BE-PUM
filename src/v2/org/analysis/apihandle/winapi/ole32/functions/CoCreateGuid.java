/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.ole32.functions
 * File name: CoCreateGuid.java
 * Created date: Aug 30, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.ole32.functions;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.platform.win32.WinNT.HRESULT;

import v2.org.analysis.apihandle.winapi.ole32.Ole32API;
import v2.org.analysis.apihandle.winapi.ole32.Ole32DLL;
import v2.org.analysis.apihandle.winapi.structures.Guid.GUID;
import v2.org.analysis.value.LongValue;

/**
 * Creates a GUID, a unique 128-bit integer used for CLSIDs and interface
 * identifiers.
 * 
 * @param pguid
 *            A pointer to the requested GUID.
 * 
 * @return S_OK The GUID was successfully created.
 * 
 *         Errors returned by UuidCreate are wrapped as an HRESULT.
 * 
 * @author Yen Nguyen
 *
 */
public class CoCreateGuid extends Ole32API {

	public CoCreateGuid() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		GUID pguid = new GUID();

		HRESULT ret = Ole32DLL.INSTANCE.CoCreateGuid(pguid);

		register.mov("eax", new LongValue(ret.longValue()));

		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1),
				new LongValue(pguid.Data1.longValue()));
		memory.setWordMemoryValue(new X86MemoryOperand(DataType.INT16, t1 += 4), new LongValue(pguid.Data2.longValue()));
		memory.setWordMemoryValue(new X86MemoryOperand(DataType.INT16, t1 += 2), new LongValue(pguid.Data3.longValue()));
		t1 += 2;
		for (int i = 0; i < pguid.Data4.length; i++) {
			memory.setByteMemoryValue(new X86MemoryOperand(DataType.INT8, t1 + i),
					new LongValue(pguid.Data4[i].longValue()));
		}
	}

}
