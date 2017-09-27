/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.shell32.functions
 * File name: StrChrI.java
 * Created date: Aug 30, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.shell32.functions;

import v2.org.analysis.apihandle.winapi.shell32.Shell32API;
import v2.org.analysis.value.LongValue;

/**
 * @author Yen Nguyen
 *
 */
public class StrChrI extends Shell32API {

	public StrChrI() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		String pszStart = memory.getText(this, t1);
		int index = pszStart.indexOf((char) t2);

		// NULL pointer
		long ret = 0;
		// Or pointer to the first occurrence
		if (index > -1) {
			ret = t1 + index;
		}

		register.mov("eax", new LongValue(ret));
	}

}
