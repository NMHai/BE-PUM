/**
 * Project: BE_PUM_V2
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.user32.functions
 * File name: SetCursor.java
 * Created date: Mar 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HCURSOR;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.stack.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Sets the cursor shape.
 * 
 * @param hCursor
 *            A handle to the cursor. The cursor must have been created by the
 *            CreateCursor function or loaded by the LoadCursor or LoadImage
 *            function. If this parameter is NULL, the cursor is removed from
 *            the screen.
 * 
 * @return The return value is the handle to the previous cursor, if there was
 *         one. If there was no previous cursor, the return value is NULL.
 * 
 * @author Yen Nguyen
 *
 */
public class SetCursor extends User32API {

	public SetCursor() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		HCURSOR hCursor = new HCURSOR(new Pointer(t1));
		HCURSOR ret = User32DLL.INSTANCE.SetCursor(hCursor);

		long value = (ret == null) ? 0 : Pointer.nativeValue(ret.getPointer());
		register.mov("eax", new LongValue(value));
		System.out.println("Return Value: " + value);
	}

}
