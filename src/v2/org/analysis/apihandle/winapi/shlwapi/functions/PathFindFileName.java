/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.shlwapi.functions
 * File name: PathFindFileName.java
 * Created date: Aug 31, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.shlwapi.functions;

import v2.org.analysis.apihandle.winapi.shlwapi.ShlwapiAPI;
import v2.org.analysis.apihandle.winapi.shlwapi.ShlwapiDLL;
import v2.org.analysis.value.LongValue;

/**
 * @author Yen Nguyen
 *
 */
public class PathFindFileName extends ShlwapiAPI {

	public PathFindFileName() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		long returnValue = 0;

		if (t1 != 0) {
			String pPath = memory.getText(this, t1);
			String ret = ShlwapiDLL.INSTANCE.PathFindFileName(pPath);

			returnValue = t1 + pPath.indexOf(ret);
		}

		register.mov("eax", new LongValue(returnValue));
	}

}
