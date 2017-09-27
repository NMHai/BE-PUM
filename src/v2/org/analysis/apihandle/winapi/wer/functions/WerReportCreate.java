/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.wer.functions
 * File name: WerReportCreate.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.wer.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.CHAR;
import com.sun.jna.platform.win32.WinDef.CHARByReference;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.platform.win32.WinNT.HANDLEByReference;

import v2.org.analysis.apihandle.structures.WER_REPORT_INFORMATION;
import v2.org.analysis.apihandle.winapi.wer.WerAPI;
import v2.org.analysis.apihandle.winapi.wer.WerDLL;
import v2.org.analysis.value.LongValue;
 
public class WerReportCreate extends WerAPI {
	public WerReportCreate () {
		super();
		NUM_OF_PARMS = 4;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		long t3 = this.params.get(3);
		
		// Step 2: type conversion from C++ to Java
		CHARByReference pwzEventType = new CHARByReference (new CHAR(t0));
		int repType = (int) t1;
		WER_REPORT_INFORMATION pReportInformation = null;
		if ( t2 != 0L) {
			pReportInformation = new WER_REPORT_INFORMATION ();
			pReportInformation.dwSize = new DWORD (((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue());
			t2 += 4;
			pReportInformation.hProcess = new HANDLE (new Pointer(((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue()));
			t2 += 4;
			for (int i = 0; i < pReportInformation.wzConsentKey.length; i++) {
				pReportInformation.wzConsentKey [i] = (char) ((LongValue) memory.getWordMemoryValue (t2)).getValue();
				t2 += 2;
			}
			for (int i = 0; i < pReportInformation.wzFriendlyEventName.length; i++) {
				pReportInformation.wzFriendlyEventName [i] = (char) ((LongValue) memory.getWordMemoryValue (t2)).getValue();
				t2 += 2;
			}
			for (int i = 0; i < pReportInformation.wzApplicationName.length; i++) {
				pReportInformation.wzApplicationName [i] = (char) ((LongValue) memory.getWordMemoryValue (t2)).getValue();
				t2 += 2;
			}
			for (int i = 0; i < pReportInformation.wzApplicationPath.length; i++) {
				pReportInformation.wzApplicationPath [i] = (char) ((LongValue) memory.getWordMemoryValue (t2)).getValue();
				t2 += 2;
			}
			for (int i = 0; i < pReportInformation.wzDescription.length; i++) {
				pReportInformation.wzDescription [i] = (char) ((LongValue) memory.getWordMemoryValue (t2)).getValue();
				t2 += 2;
			}
			pReportInformation.hwndParent = new HWND (new Pointer(((LongValue)memory.getDoubleWordMemoryValue (t2)).getValue()));
			t2 += 4;
		}
		HANDLEByReference phReportHandle = new HANDLEByReference (new HANDLE(new Pointer(t3)));

		// Step 3: call API function
		int ret = WerDLL.INSTANCE.WerReportCreate (pwzEventType, repType, pReportInformation, phReportHandle);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		memory.setDoubleWordMemoryValue(t3, new LongValue(Pointer.nativeValue(phReportHandle.getValue().getPointer())));

		

	}
}