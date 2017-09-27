/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32.functions
 * File name: GetEnhMetaFileHeader.java
 * Author: Vinh Le
 */

package v2.org.analysis.apihandle.winapi.gdi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.LONG;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.structures.ENHMETAHEADER;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32API;
import v2.org.analysis.apihandle.winapi.gdi32.Gdi32DLL;
import v2.org.analysis.value.LongValue;
 
public class GetEnhMetaFileHeader extends Gdi32API {
	public GetEnhMetaFileHeader () {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		// Step 1: get original parameter values from stack
		long t0 = this.params.get(0);
		long t1 = this.params.get(1);
		long t2 = this.params.get(2);
		
		// Step 2: type conversion from C++ to Java
		HANDLE hemf = null;
		if ( t0 != 0L ) {
			hemf = new HANDLE ();
			hemf.setPointer(new Pointer(t0));
		}
		UINT cbBuffer = new UINT (t1);
		ENHMETAHEADER lpemh = new ENHMETAHEADER ();

		// Step 3: call API function
		int ret = Gdi32DLL.INSTANCE.GetEnhMetaFileHeader (hemf, cbBuffer, lpemh);
		
		// Step 4: update environment (memory & eax register)
		long value = ret;
		register.mov("eax", new LongValue(value));
		t2 = this.params.get(2);
		memory.setDoubleWordMemoryValue (t2, new LongValue(lpemh.iType.longValue()));
		t2 += 4;
		memory.setDoubleWordMemoryValue (t2, new LongValue(lpemh.nSize.longValue()));
		t2 += 4;
		// Nested Structure
			lpemh.rclBounds.left = new LONG (((LongValue)memory.getDoubleWordMemoryValue (t2 += 0)).getValue());
			lpemh.rclBounds.top = new LONG (((LongValue)memory.getDoubleWordMemoryValue (t2 += 4)).getValue());
			lpemh.rclBounds.right = new LONG (((LongValue)memory.getDoubleWordMemoryValue (t2 += 4)).getValue());
			lpemh.rclBounds.bottom = new LONG (((LongValue)memory.getDoubleWordMemoryValue (t2 += 4)).getValue());
		// Nested Structure
			lpemh.rclFrame.left = new LONG (((LongValue)memory.getDoubleWordMemoryValue (t2 += 0)).getValue());
			lpemh.rclFrame.top = new LONG (((LongValue)memory.getDoubleWordMemoryValue (t2 += 4)).getValue());
			lpemh.rclFrame.right = new LONG (((LongValue)memory.getDoubleWordMemoryValue (t2 += 4)).getValue());
			lpemh.rclFrame.bottom = new LONG (((LongValue)memory.getDoubleWordMemoryValue (t2 += 4)).getValue());
		memory.setDoubleWordMemoryValue (t2, new LongValue(lpemh.dSignature.longValue()));
		t2 += 4;
		memory.setDoubleWordMemoryValue (t2, new LongValue(lpemh.nVersion.longValue()));
		t2 += 4;
		memory.setDoubleWordMemoryValue (t2, new LongValue(lpemh.nBytes.longValue()));
		t2 += 4;
		memory.setDoubleWordMemoryValue (t2, new LongValue(lpemh.nRecords.longValue()));
		t2 += 4;
		memory.setWordMemoryValue (t2, new LongValue(lpemh.nHandles.longValue()));
		t2 += 2;
		memory.setWordMemoryValue (t2, new LongValue(lpemh.sReserved.longValue()));
		t2 += 2;
		memory.setDoubleWordMemoryValue (t2, new LongValue(lpemh.nDescription.longValue()));
		t2 += 4;
		memory.setDoubleWordMemoryValue (t2, new LongValue(lpemh.offDescription.longValue()));
		t2 += 4;
		memory.setDoubleWordMemoryValue (t2, new LongValue(lpemh.nPalEntries.longValue()));
		t2 += 4;
		// Nested Structure
			lpemh.szlDevice.cx = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2 += 0)).getValue();
			lpemh.szlDevice.cy = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2 += 4)).getValue();
		// Nested Structure
			lpemh.szlMillimeters.cx = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2 += 0)).getValue();
			lpemh.szlMillimeters.cy = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2 += 4)).getValue();
		memory.setDoubleWordMemoryValue (t2, new LongValue(lpemh.cbPixelFormat.longValue()));
		t2 += 4;
		memory.setDoubleWordMemoryValue (t2, new LongValue(lpemh.offPixelFormat.longValue()));
		t2 += 4;
		memory.setDoubleWordMemoryValue (t2, new LongValue(lpemh.bOpenGL.longValue()));
		t2 += 4;
		// Nested Structure
			lpemh.szlMicrometers.cx = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2 += 0)).getValue();
			lpemh.szlMicrometers.cy = (int) ((LongValue)memory.getDoubleWordMemoryValue (t2 += 4)).getValue();

	}
}