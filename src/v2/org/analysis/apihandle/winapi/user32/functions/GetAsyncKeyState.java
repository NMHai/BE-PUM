/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.user32.functions
 * File name: GetAsyncKeyState.java
 * Created date: Mar 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.platform.win32.User32;

import v2.org.analysis.apihandle.winapi.user32.User32API;

import v2.org.analysis.value.LongValue;

/**
 * This function determines whether a key is up or down at the time the function
 * is called, and whether the key was pressed after a previous call to
 * GetAsyncKeyState.
 * 
 * @param vKey
 *            Specifies one of 256 possible virtual-key codes.
 * 
 * @return If the function succeeds, the return value specifies whether the key
 *         was pressed since the last call to GetAsyncKeyState, and whether the
 *         key is currently up or down. If the most significant bit is set, the
 *         key is down.
 * 
 * @author Yen Nguyen
 *
 */
public class GetAsyncKeyState extends User32API {

	public GetAsyncKeyState() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		short ret = User32.INSTANCE.GetAsyncKeyState((int) t1);

		register.mov("eax", new LongValue(ret));
	}

}
