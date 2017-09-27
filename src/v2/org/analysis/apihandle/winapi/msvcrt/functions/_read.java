package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import java.nio.ByteBuffer;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTDLL;
import v2.org.analysis.value.LongValue;

/**
 * Reads data from a file.
 * int _read( int handle, void *buffer, unsigned int count );
 * 
 * @author HaiNM
 *
 */
public class _read extends MSVCRTAPI {

	public _read() {
		super();
		NUM_OF_PARMS = 3;
		IS_POP_STACK_VALUE = false;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);

		int handle = (int) t1;
		int count = (int) t3;
		ByteBuffer ptr = ByteBuffer.allocate(count);

		int ret = MSVCRTDLL.INSTANCE._read(handle, ptr, count);

		register.mov("eax", new LongValue(ret));

		System.out.println("Read file is done!");
//		System.out.println(new String(ptr.array(), "ASCII"));
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
				System.out.print(Integer.toHexString(b & 0xFF) + " ");
				memory.setByteMemoryValue(t2 + i, new LongValue(b));
				i++;
			}
			System.out.println();
		}
	}
}
