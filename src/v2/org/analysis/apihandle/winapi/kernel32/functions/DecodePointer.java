/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: DecodePointer.java
 * Created date: Aug 27, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.PVOID;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

/**
 * Decodes a pointer that was previously encoded with EncodePointer.
 * 
 * @param Ptr
 *            The pointer to be decoded.
 * 
 * @return The function returns the decoded pointer.
 * 
 * @author Yen Nguyen
 *
 */
public class DecodePointer extends Kernel32API {

	public DecodePointer() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		PVOID Ptr = new PVOID(new Pointer(t1));
		PVOID ret = Kernel32DLL.INSTANCE.DecodePointer(Ptr);

		long value = ret == null ? 0 : Pointer.nativeValue(ret.getPointer());
		register.mov("eax", new LongValue(value));
	}

}
