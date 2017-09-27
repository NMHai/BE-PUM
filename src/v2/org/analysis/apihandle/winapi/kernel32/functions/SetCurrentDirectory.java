/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: SetCurrentDirectory.java
 * Created date: Feb 10, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinDef.BOOL;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.system.Storage;
import v2.org.analysis.value.LongValue;

/**
 * Changes the current directory for the current process.
 * 
 * @param lpPathName
 *            : The path to the new current directory. This parameter may
 *            specify a relative path or a full path. In either case, the full
 *            path of the specified directory is calculated and stored as the
 *            current directory. For more information, see File Names, Paths,
 *            and Namespaces.
 * 
 * @return If the function succeeds, the return value is nonzero.
 * 
 * @author Hai Nguyen
 * 
 * Still keep the same code
 *
 */
public class SetCurrentDirectory extends Kernel32API {

	/**
	 * 
	 */
	public SetCurrentDirectory() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		String path = memory.getText(this, t1);
		System.out.println("Original Path File:" + path);
		path = path.replace('/', '\\');
		
		Storage.CurrentDirectory = path;	
		GetCurrentDirectory.IS_SET = true;
		
		path = Storage.getMappingPath(path);
		System.out.println("Path File:" + path);
		
//		Create wrapper for this function
//		memory.createMemory(0x0, path.length());
//		memory.setText(this, 0x0, path);
//		System.out.println("Path File:" + memory.getText(this, 0x0));
//		BOOL ret = Kernel32DLL.INSTANCE.SetCurrentDirectory(memory.getPointer(0x0));	
//		memory.removeMemory(0x0);
		
		BOOL ret = Kernel32DLL.INSTANCE.SetCurrentDirectory(new WString(path));
		
		register.mov("eax", new LongValue(ret.longValue()));
	}
}
