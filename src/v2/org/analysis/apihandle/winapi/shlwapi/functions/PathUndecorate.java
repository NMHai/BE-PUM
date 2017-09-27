/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.shlwapi.functions
 * File name: PathUndecorate.java
 * Created date: Sep 23, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.shlwapi.functions;

import v2.org.analysis.apihandle.winapi.shlwapi.ShlwapiAPI;
import v2.org.analysis.apihandle.winapi.shlwapi.ShlwapiDLL;
import v2.org.analysis.value.LongValue;

/**
 * Removes the decoration from a path string.
 * 
 * @param pszPath
 *            A null-terminated string of length MAX_PATH that contains the
 *            path. When the function returns, pszPath points to the undecorated
 *            string.
 * 
 * @author Yen Nguyen
 *
 */
public class PathUndecorate extends ShlwapiAPI {

	public PathUndecorate() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		String path = memory.getText(this, t1);
		char[] temp = path.toCharArray();
		char[] pszPath = new char[260];
		
		int size = path.length();
		for (int i = 0; i < pszPath.length; i++) {
			if (i < size) {
				pszPath[i] = temp[i];
			} else {
				pszPath[i] = '\0';
			}
		}
		
		ShlwapiDLL.INSTANCE.PathUndecorate(pszPath);
		
		register.mov("eax", new LongValue(0));
	}

}
