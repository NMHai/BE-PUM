/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.shell32.functions
 * File name: SHGetSpecialFolderPath.java
 * Created date: Aug 30, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.shell32.functions;

import v2.org.analysis.apihandle.winapi.shell32.Shell32API;
import v2.org.analysis.structure.Convert;
import v2.org.analysis.value.LongValue;

import com.sun.jna.platform.win32.Shell32;

/**
 * Retrieves the path of a special folder, identified by its CSIDL.
 *
 * @param owner
 *            Reserved.
 * 
 * @param path
 *            A pointer to a null-terminated string that receives the drive and
 *            path of the specified folder. This buffer must be at least
 *            MAX_PATH characters in size.
 * 
 * @param csidl
 *            A CSIDL that identifies the folder of interest. If a virtual
 *            folder is specified, this function will fail.
 * 
 * @param create
 *            Indicates whether the folder should be created if it does not
 *            already exist. If this value is nonzero, the folder is created. If
 *            this value is zero, the folder is not created.
 * 
 * @return {@code true} if successful; otherwise, {@code false}.
 * 
 * @author Yen Nguyen
 *
 */
public class SHGetSpecialFolderPath extends Shell32API {

	public SHGetSpecialFolderPath() {
		super();
		NUM_OF_PARMS = 4;
	}

	@Override
	public void execute() {
		// long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);

		char[] path = new char[260];
		int csidl = (int) t3;
		boolean create = (t4 == 0L) ? false : true;

		boolean ret = Shell32.INSTANCE.SHGetSpecialFolderPath(null, path, csidl, create);

		register.mov("eax", new LongValue(ret ? 1 : 0));

		String reducedPath = Convert.reduceText(path);

		memory.setText(this, t2, reducedPath);
	}

}
