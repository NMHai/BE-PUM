package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTDLL;
import v2.org.analysis.apihandle.winapi.structures.Stdio.FILE;
import v2.org.analysis.value.LongValue;

/**
 * Read block of data from stream
 * 
 * Reads an array of count elements, each one with a size of size bytes, from
 * the stream and stores them in the block of memory specified by ptr.
 * 
 * The position indicator of the stream is advanced by the total amount of bytes
 * read.
 * 
 * The total amount of bytes read if successful is (size*count).
 * 
 * @param ptr
 *            Pointer to a block of memory with a size of at least (size*count)
 *            bytes, converted to a void*.
 * 
 * @param size
 *            Size, in bytes, of each element to be read. size_t is an unsigned
 *            integral type.
 * 
 * @param count
 *            Number of elements, each one with a size of size bytes. size_t is
 *            an unsigned integral type.
 * 
 * @param stream
 *            Pointer to a FILE object that specifies an input stream.
 * 
 * @return The total number of elements successfully read is returned. If this
 *         number differs from the count parameter, either a reading error
 *         occurred or the end-of-file was reached while reading. In both cases,
 *         the proper indicator is set, which can be checked with ferror and
 *         feof, respectively. If either size or count is zero, the function
 *         returns zero and both the stream state and the content pointed by ptr
 *         remain unchanged. size_t is an unsigned integral type.
 * 
 * @author Yen Nguyen
 *
 */
public class fread extends MSVCRTAPI {

	public fread() {
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

		ByteBuffer ptr = ByteBuffer.allocate((int) (t2 * t3));
		UINT size = new UINT(t2);
		UINT count = new UINT(t3);
		FILE stream = (t4 == 0L) ? null : new FILE(new Pointer(t4));

		if (stream != null) {
//			// char *_ptr;
//			// int _cnt;
//			// char *_base;
//			// int _flag;
//			// int _file;
//			// int _charbuf;
//			// int _bufsiz;
//			// char *_tmpfname;
//			stream._ptr = memory.getText(this, ((LongValue) memory.getDoubleWordMemoryValue(t4)).getValue());
//			if (stream._ptr.length() == 0) {
//				stream._ptr = null;
//			}
//			stream._cnt = ((int) ((LongValue) memory.getDoubleWordMemoryValue(t4 += 4)).getValue());
//			stream._base = memory.getText(this, ((LongValue) memory.getDoubleWordMemoryValue(t4 += 4)).getValue());
//			if (stream._base.length() == 0) {
//				stream._base = null;
//			}
//			stream._flag = ((int) ((LongValue) memory.getDoubleWordMemoryValue(t4 += 4)).getValue());
//			stream._file = ((int) ((LongValue) memory.getDoubleWordMemoryValue(t4 += 4)).getValue());
//			stream._charbuf = ((int) ((LongValue) memory.getDoubleWordMemoryValue(t4 += 4)).getValue());
//			stream._bufsiz = ((int) ((LongValue) memory.getDoubleWordMemoryValue(t4 += 4)).getValue());
//			stream._tmpfname = memory.getText(this, ((LongValue) memory.getDoubleWordMemoryValue(t4 += 4)).getValue());
//			if (stream._tmpfname.length() == 0) {
//				stream._tmpfname = null;
//			}
			
			System.out.println("FILE stream:");
			System.out.println(stream);
		}

		UINT ret = MSVCRTDLL.INSTANCE.fread(ptr, size, count, stream);

		register.mov("eax", new LongValue(ret.longValue()));

		try {
			System.out.println("Read file is done!");
			System.out.println(new String(ptr.array(), "ASCII"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

//		if (stream != null) {
//
//			if (stream._ptr != null) {
//				memory.setText(this, ((LongValue) memory.getDoubleWordMemoryValue(t4)).getValue(), stream._ptr);
//			}
//			t4 += 4;
//
//			memory.setDoubleWordMemoryValue(t4, new LongValue(stream._cnt));
//			t4 += 4;
//
//			if (stream._base != null) {
//				memory.setText(this, ((LongValue) memory.getDoubleWordMemoryValue(t4)).getValue(), stream._base);
//			}
//			t4 += 4;
//
//			memory.setDoubleWordMemoryValue(t4, new LongValue(stream._flag));
//			t4 += 4;
//
//			memory.setDoubleWordMemoryValue(t4, new LongValue(stream._file));
//			t4 += 4;
//
//			memory.setDoubleWordMemoryValue(t4, new LongValue(stream._charbuf));
//			t4 += 4;
//
//			memory.setDoubleWordMemoryValue(t4, new LongValue(stream._bufsiz));
//			t4 += 4;
//
//			if (stream._tmpfname != null) {
//				memory.setText(this, ((LongValue) memory.getDoubleWordMemoryValue(t4)).getValue(), stream._tmpfname);
//			}
//		}

		if (ptr != null && ptr.capacity() > 0) {
			int i = 0;
			for (byte b : ptr.array()) {
				memory.setByteMemoryValue(t1 + i, new LongValue(b));
				i++;
			}
		}
	}
}
