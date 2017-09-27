package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTDLL;
import v2.org.analysis.system.Storage;
import v2.org.analysis.value.LongValue;

/**
 * Remove file
 * 
 * Deletes the file whose name is specified in filename.
 * 
 * This is an operation performed directly on a file identified by its filename;
 * No streams are involved in the operation.
 * 
 * Proper file access shall be available.
 * 
 * @param filename
 *            C string containing the name of the file to be deleted. Its value
 *            shall follow the file name specifications of the running
 *            environment and can include a path (if supported by the system).
 * 
 * @return If the file is successfully deleted, a zero value is returned. On
 *         failure, a nonzero value is returned. On most library
 *         implementations, the errno variable is also set to a system-specific
 *         error code on failure.
 * 
 * @author Yen Nguyen
 *
 */
public class remove extends MSVCRTAPI {

	public remove() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		String filename = memory.getText(this, this.params.get(0));
		filename = Storage.getMappingPath(filename);

		System.out.println("Delete file: " + filename);
		int ret = MSVCRTDLL.INSTANCE.remove(filename);

		register.mov("eax", new LongValue(ret));
	}
}
