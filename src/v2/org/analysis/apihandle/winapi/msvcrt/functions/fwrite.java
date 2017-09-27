/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.msvcrt.functions
 * File name: fwrite.java
 * Created date: Sep 16, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import org.jakstab.asm.AbsoluteAddress;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.BaseTSD.SIZE_T;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTDLL;
import v2.org.analysis.apihandle.winapi.structures.Stdio.FILE;
import v2.org.analysis.value.LongValue;

/**
 * Write block of data to stream
 * 
 * Writes an array of count elements, each one with a size of size bytes, from
 * the block of memory pointed by ptr to the current position in the stream.
 * 
 * The position indicator of the stream is advanced by the total number of bytes
 * written.
 * 
 * Internally, the function interprets the block pointed by ptr as if it was an
 * array of (size*count) elements of type unsigned char, and writes them
 * sequentially to stream as if fputc was called for each byte.
 * 
 * @param ptr
 *            Pointer to the array of elements to be written, converted to a
 *            const void*.
 * 
 * @param size
 *            Size in bytes of each element to be written. size_t is an unsigned
 *            integral type.
 * 
 * @param count
 *            Number of elements, each one with a size of size bytes. size_t is
 *            an unsigned integral type.
 * 
 * @param stream
 *            Pointer to a FILE object that specifies an output stream.
 * 
 * @return The total number of elements successfully written is returned. If
 *         this number differs from the count parameter, a writing error
 *         prevented the function from completing. In this case, the error
 *         indicator (ferror) will be set for the stream. If either size or
 *         count is zero, the function returns zero and the error indicator
 *         remains unchanged. size_t is an unsigned integral type.
 * 
 * @author Yen Nguyen
 *
 */
public class fwrite extends MSVCRTAPI {

	public fwrite() {
		super();
		NUM_OF_PARMS = 4;
		IS_POP_STACK_VALUE = false;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);

		byte[] ptr;
		SIZE_T size = new SIZE_T(t2);
		SIZE_T count = new SIZE_T(t3);
		FILE stream = (t4 == 0L) ? null : new FILE(new Pointer(t4));

		if (stream != null) {
			// char *_ptr;
			// int _cnt;
			// char *_base;
			// int _flag;
			// int _file;
			// int _charbuf;
			// int _bufsiz;
			// char *_tmpfname;
//			stream._ptr = memory.getText(this, t4);
//			if (stream._ptr.length() == 0) {
//				stream._ptr = null;
//			}
//			stream._cnt = ((int) ((LongValue) memory.getDoubleWordMemoryValue(t4 += 4)).getValue());
//			stream._base = memory.getText(this, t4 += 4);
//			if (stream._base.length() == 0) {
//				stream._base = null;
//			}
//			stream._flag = ((int) ((LongValue) memory.getDoubleWordMemoryValue(t4 += 4)).getValue());
//			stream._file = ((int) ((LongValue) memory.getDoubleWordMemoryValue(t4 += 4)).getValue());
//			stream._charbuf = ((int) ((LongValue) memory.getDoubleWordMemoryValue(t4 += 4)).getValue());
//			stream._bufsiz = ((int) ((LongValue) memory.getDoubleWordMemoryValue(t4 += 4)).getValue());
//			stream._tmpfname = memory.getText(this, t4 += 4);
//			if (stream._tmpfname.length() == 0) {
//				stream._tmpfname = null;
//			}
			
			System.out.println("FILE stream:");
			System.out.println(stream);
		}

		int length = ((int) t2) * ((int) t3);
		ptr = new byte[length];

		byte[] buffer = memory.getBytesArray(new AbsoluteAddress(t1), length);
		for (int i = 0; i < buffer.length; i++) {
			ptr[i] = buffer[i];
		}

		SIZE_T ret = MSVCRTDLL.INSTANCE.fwrite(ptr, size, count, stream);

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
