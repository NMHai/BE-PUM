package v2.org.analysis.apihandle.winapi.structures;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public interface Stdio {
	public static class FILE extends Structure {
		public Pointer _ptr;
		public int _cnt;
		public Pointer _base;
		public int _flag;
		public int _file;
		public int _charbuf;
		public int _bufsiz;
		public Pointer _tmpfname;

		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { "_ptr", "_cnt", "_base", "_flag", "_file", "_charbuf", "_bufsiz",
					"_tmpfname" });
		}

		public FILE() {
		}

		public static class ByReference extends FILE implements Structure.ByReference {
			public ByReference() {
			}

			public ByReference(Pointer memory) {
				super(memory);
			}
		}

		public FILE(Pointer memory) {
			super(memory);
			read();
		}
	}
	

	public static class FILE2 extends Structure {
		public String _ptr;
		public int _cnt;
		public String _base;
		public int _flag;
		public int _file;
		public int _charbuf;
		public int _bufsiz;
		public String _tmpfname;

		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { "_ptr", "_cnt", "_base", "_flag", "_file", "_charbuf", "_bufsiz",
					"_tmpfname" });
		}

		public FILE2() {
		}

		public static class ByReference extends FILE2 implements Structure.ByReference {
			public ByReference() {
			}

			public ByReference(Pointer memory) {
				super(memory);
			}
		}

		public FILE2(Pointer memory) {
			super(memory);
			read();
		}
	}
}
