/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.msvcrt.functions
 * File name: fopen.java
 * Created date: Sep 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import com.sun.jna.Pointer;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTDLL;
import v2.org.analysis.apihandle.winapi.structures.Stdio.FILE;
import v2.org.analysis.system.Storage;
import v2.org.analysis.value.LongValue;

/**
 * Open file
 * 
 * Opens the file whose name is specified in the parameter filename and
 * associates it with a stream that can be identified in future operations by
 * the FILE pointer returned.
 * 
 * The operations that are allowed on the stream and how these are performed are
 * defined by the mode parameter.
 * 
 * The returned stream is fully buffered by default if it is known to not refer
 * to an interactive device (see setbuf).
 * 
 * The returned pointer can be disassociated from the file by calling fclose or
 * freopen. All opened files are automatically closed on normal program
 * termination.
 * 
 * The running environment supports at least FOPEN_MAX files open
 * simultaneously.
 * 
 * @param filename
 *            Open file Opens the file whose name is specified in the parameter
 *            filename and associates it with a stream that can be identified in
 *            future operations by the FILE pointer returned.
 * 
 *            The operations that are allowed on the stream and how these are
 *            performed are defined by the mode parameter.
 * 
 *            The returned stream is fully buffered by default if it is known to
 *            not refer to an interactive device (see setbuf).
 * 
 *            The returned pointer can be disassociated from the file by calling
 *            fclose or freopen. All opened files are automatically closed on
 *            normal program termination.
 * 
 *            The running environment supports at least FOPEN_MAX files open
 *            simultaneously.
 * 
 * @param mode
 *            C string containing a file access mode. It can be:
 * 
 *            "r" read: Open file for input operations. The file must exist.
 * 
 *            "w" write: Create an empty file for output operations. If a file
 *            with the same name already exists, its contents are discarded and
 *            the file is treated as a new empty file.
 * 
 *            "a" append: Open file for output at the end of a file. Output
 *            operations always write data at the end of the file, expanding it.
 *            Repositioning operations (fseek, fsetpos, rewind) are ignored. The
 *            file is created if it does not exist.
 * 
 *            "r+" read/update: Open a file for update (both for input and
 *            output). The file must exist.
 * 
 *            "w+" write/update: Create an empty file and open it for update
 *            (both for input and output). If a file with the same name already
 *            exists its contents are discarded and the file is treated as a new
 *            empty file.
 * 
 *            "a+" append/update: Open a file for update (both for input and
 *            output) with all output operations writing data at the end of the
 *            file. Repositioning operations (fseek, fsetpos, rewind) affects
 *            the next input operations, but output operations move the position
 *            back to the end of file. The file is created if it does not exist.
 * 
 * @return If the file is successfully opened, the function returns a pointer to
 *         a FILE object that can be used to identify the stream on future
 *         operations. Otherwise, a null pointer is returned. On most library
 *         implementations, the errno variable is also set to a system-specific
 *         error code on failure.
 * 
 * @author Yen Nguyen
 *
 */
public class fopen extends MSVCRTAPI {

	public fopen() {
		super();
		NUM_OF_PARMS = 2;
		IS_POP_STACK_VALUE = false;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		String filename = memory.getText(this, t1);
		String mode = memory.getText(this, t2);
		
		filename = Storage.getMappingPath(filename);

		System.out.println(String.format("Path: %s, Mode: %s", filename, mode));

		FILE ret = MSVCRTDLL.INSTANCE.fopen(filename, mode);

		if (ret != null) {
			// char *_ptr;
			// int _cnt;
			// char *_base;
			// int _flag;
			// int _file;
			// int _charbuf;
			// int _bufsiz;
			// char *_tmpfname;

			long ptr = Pointer.nativeValue(ret.getPointer());
			
			memory.setDoubleWordMemoryValue(ptr, new LongValue(Pointer.nativeValue(ret._ptr)));
			if (ret._ptr != null && Pointer.nativeValue(ret._ptr) != 0L) {
				memory.setText(this, Pointer.nativeValue(ret._ptr), ret._ptr.getString(0));
			}
			ptr += 4;

			memory.setDoubleWordMemoryValue(ptr, new LongValue(ret._cnt));
			ptr += 4;

			memory.setDoubleWordMemoryValue(ptr, new LongValue(Pointer.nativeValue(ret._base)));
			if (ret._base != null && Pointer.nativeValue(ret._base) != 0L) {
				memory.setText(this, Pointer.nativeValue(ret._base), ret._base.getString(0));
			}
			ptr += 4;

			memory.setDoubleWordMemoryValue(ptr, new LongValue(ret._flag));
			ptr += 4;
			System.out.println("Write Valule _file:" + new LongValue(ret._file));
			memory.setDoubleWordMemoryValue(ptr, new LongValue(ret._file));
			System.out.println("Read Valule _file:" + memory.getDoubleWordMemoryValue(ptr));
			ptr += 4;

			memory.setDoubleWordMemoryValue(ptr, new LongValue(ret._charbuf));
			ptr += 4;

			memory.setDoubleWordMemoryValue(ptr, new LongValue(ret._bufsiz));
			ptr += 4;

			memory.setDoubleWordMemoryValue(ptr, new LongValue(Pointer.nativeValue(ret._tmpfname)));
			if (ret._tmpfname != null && Pointer.nativeValue(ret._tmpfname) != 0L) {
				memory.setText(this, Pointer.nativeValue(ret._tmpfname), ret._tmpfname.getString(0));
			}
			
			System.out.println(ret);
			
			ptr = Pointer.nativeValue(ret.getPointer());
			register.mov("eax", new LongValue(ptr));
			
			System.out.printf("ret._file: %s", memory.getDoubleWordMemoryValue(ptr + 16).toString());
		}
	}
}
