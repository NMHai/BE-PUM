/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: CloseHandle.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;

import v2.org.analysis.value.LongValue;

/**
 * Waits for ownership of the specified critical section object. The function
 * returns when the calling thread is granted ownership.
 * 
 * @param hObject
 *            A pointer to the critical section object.
 * 
 * @author Yen Nguyen
 *
 */
public class EnterCriticalSection extends Kernel32API {

	public EnterCriticalSection() {
		super();
		NUM_OF_PARMS = 1;
	}


	@Override
	public void execute() {
		// long x = this.params.get(0);
		// System.out.println("Object Handle:" + x);
		//
		// boolean ret = Kernel32.INSTANCE.CloseHandle(new HANDLE(x != 0 ?
		// new Pointer(x) : Pointer.NULL));
		System.out.println("\t\t NOTICE: SPECIAL WINDOWS API");
		
		register.mov("eax", new LongValue(0));
	}

}
