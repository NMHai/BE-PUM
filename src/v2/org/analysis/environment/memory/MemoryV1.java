/**
 * 
 */
package v2.org.analysis.environment.memory;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeSet;

import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;
import org.jakstab.disasm.x86.X86Disassembler;
import org.jakstab.loader.ExecutableImage;
import org.jakstab.loader.pe.PEModule;
import org.jakstab.util.Pair;

import com.twmacinta.util.MD5;

import v2.org.analysis.apihandle.winapi.API;
import v2.org.analysis.apihandle.winapi.APIHandle;
import v2.org.analysis.apihandle.winapi.kernel32.functions.GetProcAddress;
import v2.org.analysis.apihandle.winapi.kernel32.functions.LoadLibrary;
import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.memory.ExternalMemory.ExternalMemoryReturnData;
import v2.org.analysis.environment.stack.Stack;
import v2.org.analysis.statistics.FileProcess;
import v2.org.analysis.structure.BitVector;
import v2.org.analysis.structure.Convert;
import v2.org.analysis.util.PairEntry;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.SymbolValue;
import v2.org.analysis.value.Value;

/**
 * @author NMHai
 *
 */
public class MemoryV1 {
	private static final int UNKNOWN = Integer.MAX_VALUE;

	private Environment env;
	private Map<Long, Value> memory;
	private Program program;
	private Map<Long, Value> reset;
	private String hashValue = ""; 
	private boolean isChanged = false;

	public MemoryV1() {
		memory = new NewHashMap<>();
		reset = new NewHashMap<>();
		program = Program.getProgram();
		initSEHMemory();
	}

	public MemoryV1(Environment env) {
		memory = new NewHashMap<>();
		program = Program.getProgram();
		this.env = env;
		initSEHMemory();
	}

	// ///////////// PHONG: 20150526/////////////////////////////////////
	public void initSEHMemory() {
		long addrSEH = 0x12FFE0;
		long nextSEH = 0xFFFFFFFF;
		long sehHandler = 0x7C839AA8;
		setDoubleWordMemoryValue(addrSEH, new LongValue(nextSEH));
		setDoubleWordMemoryValue(addrSEH + 4, new LongValue(sehHandler));
	}

	// //////////////////////////////////////////////////////////////////

	private void outputMemory(String ret) {
		FileProcess fp = new FileProcess("/data/data/memory.txt"); // YenNguyen
		fp.clearContentFile();
		// fp.clearContentFile();
		fp.appendFile(ret);
	}

	@Override
	public String toString() {
//		FileProcess fp = new FileProcess("/data/data/memory.txt");
		String ret = "";
		for (Map.Entry<Long, Value> entry : memory.entrySet()) {
			ret += "0x" + Long.toHexString(entry.getKey()) + "\t" + entry.getValue() + "\n";
		}

//		fp.clearContentFile();
//		fp.appendFile(ret);
		outputMemory(ret);
		return ret;
	}

	/**
	 * Sorting the memory pair values of this instance by address (key value of
	 * {@link HashMap} attribute). Then, appending each pair value to
	 * {@link StringBuilder} and get {@link String} content of it.
	 * 
	 * @return The string holding the ordered content of this memory.
	 * 
	 * @author Yen Nguyen
	 */
	private String getOrderedStringContent() {
		SortedSet<Long> keys = new TreeSet<>(this.memory.keySet());

		StringBuilder stringBuilder = new StringBuilder();
		for (Long key : keys) {
			stringBuilder.append(key);
			stringBuilder.append(this.memory.get(key).toString());
		}
		
		return stringBuilder.toString();
	}

	public void addMemoryValue(long address, Value v, Instruction inst) {
		Value o = getMemoryValue(address, inst);
		o = o.addFunction(v);
		setMemoryValue(address, o, inst);
	}

	public void addMemoryValue(X86MemoryOperand dest, Value v, Instruction inst) {
		/*
		 * if (dest.getBase() != null &&
		 * dest.getBase().toString().contains("esp")) {
		 * env.getStack().add(dest.getDisplacement(), v, inst,
		 * ((X86MemoryOperand)dest).getDataType()); return; }
		 */

		long d = evaluateAddress(dest);

		if (d == UNKNOWN) {
			return;
		}

		this.addMemoryValue(d, v, inst);
	}

	public void andMemoryValue(long address, Value v, Instruction inst) {
		Value o = getMemoryValue(address, inst);
		o = o.andFunction(v);
		setMemoryValue(address, o, inst);
	}

	public void andMemoryValue(X86MemoryOperand dest, Value v, Instruction inst) {
		/*
		 * if (dest.getBase() != null &&
		 * dest.getBase().toString().contains("esp")) {
		 * env.getStack().and(dest.getDisplacement(), v, inst); return; }
		 */

		long d = evaluateAddress(dest);

		if (d == UNKNOWN) {
			return;
		}

		this.andMemoryValue(d, v, inst);
	}

	private long calculateQWordValue(long r1, long r2, long r3, long r4,
			long r5, long r6, long r7, long r8) {
		/*
		 * int ret = 0; ret = (int) r1; ret |= r2 << 8; ret |= r3 << 16; ret |=
		 * r4 << 24; return ret;
		 */
		return BitVector.bytesToLong((int) r1, (int) r2, (int) r3, (int) r4,
				(int) r5, (int) r6, (int) r7, (int) r8);
	}
	
	private long calculateDoubleWordValue(long r1, long r2, long r3, long r4) {
		/*
		 * int ret = 0; ret = (int) r1; ret |= r2 << 8; ret |= r3 << 16; ret |=
		 * r4 << 24; return ret;
		 */
		return BitVector.bytesToLong((int) r1, (int) r2, (int) r3, (int) r4);
	}

	private long calculateWordValue(long r1, long r2) {
		/*
		 * int ret = 0; ret = (int) r1; ret |= r2 << 8; return ret;
		 */
		return BitVector.bytesToLong((int) r1, (int) r2);
	}

	public boolean changeValue(AbsoluteAddress address) {
		// TODO Auto-generated method stub
		ExecutableImage module = Program.getProgram().getModule(address);

		for (Map.Entry<Long, Value> entry : memory.entrySet()) {
			long addr = entry.getKey();
			if (Math.abs(addr - address.getValue()) < 20) {
				Value v = entry.getValue();
				if (v instanceof LongValue) {
					long fp1 = module.getFilePointer(new AbsoluteAddress(addr));

					if (fp1 < 0 || (int) fp1 < 0) {
						Program.getProgram().getLog()
								.debugString("Requested instruction outside of file area: " + address);
						return false;
					}

					// PHONG: debug here
					// Value old = new LongValue(Program.getProgram()
					// .getByteValueMemory(new AbsoluteAddress(addr)));
					// long old_v =
					// module.getDisassembler().getMemoryByteValue((int) fp1);
					if (module.getDisassembler() instanceof X86Disassembler) {
						X86Disassembler x86Dis = (X86Disassembler) module.getDisassembler();
						long oldValue = x86Dis.getMemoryByteValue((int) fp1);

						if (oldValue != ((LongValue) v).getValue()) {
							// Program.getProgram().setTechnique("SMC");
							// Program.getProgram().setDetailTechnique("SMC:" +
							// new AbsoluteAddress(addr) + " ");
							module.getDisassembler().setMemoryByteValue((int) fp1, ((LongValue) v).getValue());
							reset.put(addr, new LongValue(oldValue));
						}
					}
				}
			}
		}

		return true;
	}

	@Override
	public MemoryV1 clone() {
		MemoryV1 ret = new MemoryV1();

		for (Entry<Long, Value> entry : memory.entrySet()) {
			if (entry.getValue() != null) {
				ret.setByteMemoryValue(entry.getKey(), entry.getValue().clone());
			}
		}

		return ret;
	}

	public boolean contains(AbsoluteAddress addr) {
		// TODO Auto-generated method stub
		for (Map.Entry<Long, Value> entry : memory.entrySet()) {

			if (entry.getKey().longValue() == addr.getValue()) {
				return true;
			}
		}

		return false;
	}

	public void divMemoryValue(long address, Value v, Instruction inst) {
		Value o = getMemoryValue(address, inst);
		o = o.unsignedDivFunction(v);
		setMemoryValue(address, o, inst);
	}

	public void divMemoryValue(X86MemoryOperand dest, Value v, Instruction inst) {
		/*
		 * if (dest.getBase() != null &&
		 * dest.getBase().toString().contains("esp")) {
		 * env.getStack().div(dest.getDisplacement(), v, inst); return; }
		 */

		long d = evaluateAddress(dest);

		if (d == UNKNOWN) {
			return;
		}

		this.divMemoryValue(d, v, inst);
	}

	public boolean equals(MemoryV1 m) {
		for (Entry<Long, Value> entry : memory.entrySet()) {
			Value t = m.getByteMemoryValue(entry.getKey());
			if (t == null || t.equal(entry.getValue())) {
				return false;
			}
		}

		return true;
	}

	private long evaluateAddress(X86MemoryOperand m) {
		// TODO Auto-generated method stub
		long val = (int) m.getDisplacement();
		if (m.getBase() != null) {
			Value r = env.getRegister().getRegisterValue(m.getBase().toString());

			// if (r == null || !(r instanceof LongValue))
			// return UNKNOWN;

			if (r != null && r instanceof LongValue) {
				// PHONG: change long address here to int address
				val += (int) ((LongValue) r).getValue();
				// return ret + m.getDisplacement() + ((LongValue)
				// r).getValueOperand();
			} else {
				return UNKNOWN;
			}
		}

		if (m.getSegmentRegister() != null) {
			Value s = env.getRegister().getRegisterValue(m.getSegmentRegister().toString());
			if (s != null && s instanceof LongValue) {
				val += ((LongValue) s).getValue();
			} else {
				return UNKNOWN;
			}
		}

		if (m.getIndex() != null) {
			Value index = env.getRegister().getRegisterValue(m.getIndex().toString());

			if (index != null && index instanceof LongValue) {
				if (m.getScale() != 0) {
					val += (int) (((LongValue) index).getValue() * m.getScale());
				} else {
					val += (int) (((LongValue) index).getValue());
				}
			} else {
				return UNKNOWN;
			}
		}

		return val;
	}

	public Value calculateAddress(X86MemoryOperand m) {
		// TODO Auto-generated method stub
		/*
		 * if (m.getBase() != null) { Value r =
		 * env.getRegister().getRegisterValue(m.getBase().toString()); Value
		 * index = null; if (m.getIndex() != null) index =
		 * env.getRegister().getRegisterValue(m.getIndex().toString()); // long
		 * ret = 0;
		 * 
		 * if (index != null) { if (m.getScale() != 0) r =
		 * r.addFunction(index.unsignedMulFunction(new
		 * LongValue(m.getScale()))); }
		 * 
		 * r = r.addFunction(new LongValue(m.getDisplacement()));
		 * 
		 * return r; }
		 */

		long val = evaluateAddress(m);
		if (val == UNKNOWN) {
			return new SymbolValue(SymbolValue.generateString(m));
		}

		return new LongValue(val);
	}

	public X86MemoryOperand evaluateAddress(X86MemoryOperand m, Environment env) {
		// TODO Auto-generated method stub
		long val = (int) m.getDisplacement();
		if (m.getBase() != null) {
			Value r = env.getRegister().getRegisterValue(m.getBase().toString());
			if (r != null && r instanceof LongValue) {
				// PHONG: change long address here to int address
				val += (int) ((LongValue) r).getValue();
				// return ret + m.getDisplacement() + ((LongValue)
				// r).getValueOperand();
			} else {
				return m;
			}
		}

		if (m.getSegmentRegister() != null) {
			Value s = env.getRegister().getRegisterValue(m.getSegmentRegister().toString());
			if (s != null && s instanceof LongValue) {
				val += ((LongValue) s).getValue();
			} else {
				return m;
			}
		}

		if (m.getIndex() != null) {
			Value index = env.getRegister().getRegisterValue(m.getIndex().toString());

			if (index != null && index instanceof LongValue) {
				if (m.getScale() != 0) {
					val += (int) (((LongValue) index).getValue() * m.getScale());
				} else {
					val += (int) (((LongValue) index).getValue());
				}
			} else {
				return m;
			}
		}

		return new X86MemoryOperand(m.getDataType(), val);
	}

	public Value getByteMemoryValue(long address) {
		return this.getByteMemoryValue(address, true);
	}

	public Value getByteMemoryValue(long address, boolean isTryInExternalMemory) {
		// First, let find in the internal memory
		Value memValue = memory.get(address);
		if (memValue != null) {
			return memValue;
		}

		// If not exist, try to find it in the dynamic-link library
		String libName = env.getSystem().getLibraryHandle().insideDLL(new AbsoluteAddress(address));
		if (libName != null && libName != ""){
			return new LongValue(env.getSystem().getLibraryHandle().readByte(libName, (int) address));
		}

		if (env.getSystem().getFileHandle().isInsideFile(new AbsoluteAddress(address))) {
			return new LongValue(env.getSystem().getFileHandle().readByte((int) address));
		}

		try {
			AbsoluteAddress absoluteAddr = new AbsoluteAddress(address);
			if (program.isInside(absoluteAddr)) {
				return new LongValue(program.getByteValueMemoryPhong(absoluteAddr));
			} else if (program.isBetweenSection(absoluteAddr)) {
				return new LongValue(0);
			} else {
				// YenNguyen: Access jna's memory here
				if (address != 0 && isTryInExternalMemory) {
					ExternalMemoryReturnData ret = ExternalMemory.getByte(address);
					if (ret != null && ret.isValidAddress) {
						return ret.value;
					}
				}
				// return new SymbolValue(SymbolValue.generateString(
				// new X86MemoryOperand(DataType.INT8, address))
				// .toString());
				return new LongValue(0);
			}
		} catch (Exception e) {
			return new SymbolValue(SymbolValue.generateString(new X86MemoryOperand(DataType.INT8, address)).toString());
			// return new LongValue(0);
		}
	}

	public Value getByteMemoryValue(X86MemoryOperand dest) {
		long d = evaluateAddress(dest);

		// PHONG: 20150605
		// -----------------------------------------------------------------------
		/*
		 * if (dest.getSegmentRegister() != null &&
		 * dest.getSegmentRegister().toString() == "%fs") { d =
		 * TIB.getTIB_Base_Address() + d; }
		 */
		// ---------------------------------------------------------------------------------------

		if (d == UNKNOWN) {
			return new SymbolValue(SymbolValue.generateString(dest));
		}

		return this.getByteMemoryValue(d);
	}

	public Value getWordMemoryValue(long address) {
//		if (env.getSystem().getKernel().isInside(new AbsoluteAddress(address))) {
//			return new LongValue(env.getSystem().getKernel().readWord((int) address));
//		}
//
		String libName = env.getSystem().getLibraryHandle().insideDLL(new AbsoluteAddress(address));
		if (libName != null && libName != ""){
			return new LongValue(env.getSystem().getLibraryHandle().readWord(libName, (int) address));
		}

		if (env.getSystem().getFileHandle().isInsideFile(new AbsoluteAddress(address))) {
			return new LongValue(env.getSystem().getFileHandle().readWord((int) address));
		}

		Value v1 = getByteMemoryValue(address);
		Value v2 = getByteMemoryValue(address + 1);

		if (v1 != null && v2 != null && v1 instanceof LongValue && v2 instanceof LongValue) {
			long value = calculateWordValue(((LongValue) v1).getValue(), ((LongValue) v2).getValue());
			// BPLogger.debugLogger.info(address + ":" + value);
			return new LongValue(value);
		}

		return new SymbolValue(SymbolValue.generateString(new X86MemoryOperand(DataType.INT16, address)));
	}

	public Value getWordMemoryValue(X86MemoryOperand dest) {
		long d = evaluateAddress(dest);

		// PHONG: 20150605
		// -----------------------------------------------------------------------
		/*
		 * if (dest.getSegmentRegister() != null &&
		 * dest.getSegmentRegister().toString() == "%fs") { d =
		 * TIB.getTIB_Base_Address() + d; }
		 */
		// ---------------------------------------------------------------------------------------

		if (d == UNKNOWN) {
			return new SymbolValue(SymbolValue.generateString(dest));
		}

		return this.getWordMemoryValue(d);
	}

	public Value getDoubleWordMemoryValue(long address) {

		String libName = env.getSystem().getLibraryHandle().insideDLL(new AbsoluteAddress(address));
		if (libName != null && libName != ""){
			return new LongValue(env.getSystem().getLibraryHandle().readDoubleWord(libName, (int) address));
		}

		if (env.getSystem().getFileHandle().isInsideFile(new AbsoluteAddress(address))) {
			return new LongValue(env.getSystem().getFileHandle().readDoubleWord((int) address));
		}

		Value v1 = getByteMemoryValue(address);
		Value v2 = getByteMemoryValue(address + 1);
		Value v3 = getByteMemoryValue(address + 2);
		Value v4 = getByteMemoryValue(address + 3);

		if (v1 != null && v1 instanceof LongValue && v2 != null && v2 instanceof LongValue && v3 != null
				&& v3 instanceof LongValue && v4 != null && v4 instanceof LongValue) {
			long value = calculateDoubleWordValue(((LongValue) v1).getValue(), ((LongValue) v2).getValue(),
					((LongValue) v3).getValue(), ((LongValue) v4).getValue());
			// BPLogger.debugLogger.info(address + ":" + value);
			return new LongValue(value);
		}

		return new SymbolValue(SymbolValue.generateString(new X86MemoryOperand(DataType.INT32, address)));
		// return new LongValue(0);
	}

	public Value getQWordMemoryValue(long address) {

		String libName = env.getSystem().getLibraryHandle().insideDLL(new AbsoluteAddress(address));
		if (libName != null && libName != "") {
			return new LongValue(env.getSystem().getLibraryHandle().readDoubleWord(libName, (int) address));
		}

		if (env.getSystem().getFileHandle().isInsideFile(new AbsoluteAddress(address))) {
			return new LongValue(env.getSystem().getFileHandle().readDoubleWord((int) address));
		}

		Value v1 = getByteMemoryValue(address);
		Value v2 = getByteMemoryValue(address + 1);
		Value v3 = getByteMemoryValue(address + 2);
		Value v4 = getByteMemoryValue(address + 3);
		Value v5 = getByteMemoryValue(address + 4);
		Value v6 = getByteMemoryValue(address + 5);
		Value v7 = getByteMemoryValue(address + 6);
		Value v8 = getByteMemoryValue(address + 7);

		if (v1 != null && v1 instanceof LongValue && v2 != null && v2 instanceof LongValue && v3 != null
				&& v3 instanceof LongValue && v4 != null && v4 instanceof LongValue) {
			long value = calculateQWordValue(((LongValue) v1).getValue(), ((LongValue) v2).getValue(),
					((LongValue) v3).getValue(), ((LongValue) v4).getValue(),
					((LongValue) v5).getValue(), ((LongValue) v6).getValue(),
					((LongValue) v7).getValue(), ((LongValue) v8).getValue());
			// BPLogger.debugLogger.info(address + ":" + value);
			return new LongValue(value);
		}

		return new SymbolValue(SymbolValue.generateString(new X86MemoryOperand(DataType.INT32, address)));
		// return new LongValue(0);
	}
	
	public Value getDoubleWordMemoryValue(X86MemoryOperand dest) {
		long d = evaluateAddress(dest);

		// PHONG: 20150506 If segment is FS
		// ------------------------------------------------------
		/*
		 * if (dest.getSegmentRegister() != null &&
		 * dest.getSegmentRegister().toString() == "%fs") { d =
		 * TIB.getTIB_Base_Address() + d; }
		 */
		// ---------------------------------------------------------------------------------------
		if (d == UNKNOWN) {
			return new SymbolValue(SymbolValue.generateString(dest));
		}

		return this.getDoubleWordMemoryValue(d);
	}

	public Value getQWordMemoryValue(X86MemoryOperand dest) {
		long d = evaluateAddress(dest);

		// PHONG: 20150506 If segment is FS
		// ------------------------------------------------------
		/*
		 * if (dest.getSegmentRegister() != null &&
		 * dest.getSegmentRegister().toString() == "%fs") { d =
		 * TIB.getTIB_Base_Address() + d; }
		 */
		// ---------------------------------------------------------------------------------------
		if (d == UNKNOWN) {
			return new SymbolValue(SymbolValue.generateString(dest));
		}

		return this.getQWordMemoryValue(d);
	}
	
	public String getText(API api, int addr, long l) {
		// YenNguyen: Using StringBuilder will help to improve executing time
		StringBuilder ret = new StringBuilder();

		for (int i = 0; i < l; i++) {
			Value t = getByteMemoryValue(addr + i, false);

			if (t != null && t instanceof LongValue) {
				byte t1 = (byte) (((LongValue) t).getValue() & 0xFF);
				ret.append((char) t1);
			}
		}

		return ret.toString();
	}

	private String getText(long addr, int charSize) {
		// YenNguyen: Using StringBuilder will help to improve executing time
		StringBuilder ret = new StringBuilder();

		while (true) {
			Value t = (charSize == 1) ? getByteMemoryValue(addr, false) : getWordMemoryValue(addr);
			if (t != null && t instanceof LongValue) {
				
				char t1 = (charSize == 1) //
				? (char) (byte) (((LongValue) t).getValue() & 0xFF) //
						: (char) (((LongValue) t).getValue() & 0xFFFF);
				if (t1 == 0 || t1 < 0) { // Over-size
					break;
				}

				ret.append(t1);
				addr += charSize;
			} else if (t instanceof SymbolValue) {
				return ret.toString();
			}
		}

		return ret.toString();
	}

	public String getTextLimit(long addr, int charSize, int size) {
		// YenNguyen: Using StringBuilder will help to improve executing time
		StringBuilder ret = new StringBuilder();

		int cnt = 0;
		while (true) {
			if (cnt++ == size) {
				return ret.toString();
			}
			Value t = (charSize == 1) ? getByteMemoryValue(addr, false) : getWordMemoryValue(addr);

			if (t != null && t instanceof LongValue) {
				char t1 = (charSize == 1) //
						? (char) (byte) (((LongValue) t).getValue() & 0xFF) //
						: (char) (((LongValue) t).getValue() & 0xFFFF);
				ret.append(t1);
				addr += charSize;
			} else if (t instanceof SymbolValue) {
				return ret.toString();
			}
		}
		//return ret.toString();
	}
	
	public String getPrintableLimit(long addr, int charSize, int size) {
		// YenNguyen: Using StringBuilder will help to improve executing time
		StringBuilder ret = new StringBuilder();

		int cnt = 0;
		while (true) {
			if (cnt++ == size) {
				return ret.toString();
			}
			Value t = (charSize == 1) ? getByteMemoryValue(addr, false) : getWordMemoryValue(addr);

			if (t != null && t instanceof LongValue) {
				char t1 = (charSize == 1) //
						? (char) (byte) (((LongValue) t).getValue() & 0xFF) //
						: (char) (((LongValue) t).getValue() & 0xFFFF);
				if (t1 == 0 || t1 < 0) { // Over-size
					break;
				}
				ret.append(t1);
				addr += charSize;
			} else if (t instanceof SymbolValue) {
				return ret.toString();
			}
		}
		return ret.toString();
	}
	
	public String getText(API api, long addr) {
		// ASCII - 1-byte per char
		// Unicode - 2-byte per char
		int numOfBytes = (api.is64bit()) ? 2 : 1;
		String str = getText(addr, numOfBytes);
		return str;
	}
	
	public String getText(int num, long addr) {
		// ASCII - 1-byte per char
		// Unicode - 2-byte per char
		return getText(addr, num);
	}

	public String getPrintable(long addr) {
		int ASCII = 1;
		int UNICODE = 2;
		String strAsc = getPrintableLimit(addr, ASCII, 24);
		String strUni = getPrintableLimit(addr, UNICODE, 24);
		String str = "";
		if (!strAsc.isEmpty()) {
			str += " [A] " + strAsc;
		}
		if (!strUni.isEmpty()) {
			str += " [W] " + strUni;
		}
		return str;
	}
	
	public Value getMemoryValue(long dest, Instruction inst) {
		// YenNguyen: Change from conditions of "endWith(String)" method to
		// switch/case by last character in order to reduce computing
		char lastChar = inst.getName().charAt(inst.getName().length() - 1);

		switch (lastChar) {
		case 'b':
			return getByteMemoryValue(dest);
		case 's':
		case 'w':
			return getWordMemoryValue(dest);
		case 'l':
		default:
			return getDoubleWordMemoryValue(dest);
		}
	}

	public Value getMemoryValue(X86MemoryOperand dest, Instruction inst) {

		// PHONG: 20150507
		// ---------------------------------------------------------------------
		// if (dest.getSegmentRegister() != null &&
		// dest.getSegmentRegister().toString() == "%fs"
		// && dest.getDisplacement() == 0) {
		// return new
		// LongValue(env.getSystem().getSEHHandler().getStart().getAddrSEHRecord());
		// }
		// --------------------------------------------------------------------------------------

		if (dest.getDataType() == DataType.INT32) {
			return getDoubleWordMemoryValue(dest);
		} else if (dest.getDataType() == DataType.INT8) {
			return getByteMemoryValue(dest);
		} else if (dest.getDataType() == DataType.INT16) {
			return getWordMemoryValue(dest);
		}

		return getDoubleWordMemoryValue(dest);
	}

	public Value getMemoryValue(DataType dt, X86MemoryOperand dest, Instruction inst) {

		if (dt == DataType.INT32) {
			return getDoubleWordMemoryValue(dest);
		} else if (dt == DataType.INT8) {
			return getByteMemoryValue(dest);
		} else if (dt == DataType.INT16) {
			return getWordMemoryValue(dest);
		} else if (dt == DataType.INT64) {
			return getQWordMemoryValue(dest);
		}

		return getDoubleWordMemoryValue(dest);
	}
	
	public Register getRegister() {
		return env.getRegister();
	}

	/**
	 * @return the stack
	 */
	public Stack getStack() {
		return env.getStack();
	}

	public void mulMemoryValue(long address, Value v, Instruction inst) {
		Value o = getMemoryValue(address, inst);
		o = o.unsignedMulFunction(v);
		setMemoryValue(address, o, inst);
	}

	public void mulMemoryValue(X86MemoryOperand dest, Value v, Instruction inst) {
		/*
		 * if (dest.getBase() != null &&
		 * dest.getBase().toString().contains("esp")) {
		 * env.getStack().mul(dest.getDisplacement(), v, inst); return; }
		 */

		long d = evaluateAddress(dest);

		if (d == UNKNOWN) {
			return;
		}

		this.mulMemoryValue(d, v, inst);
	}

	private Value normalizeValue(Value v, Instruction inst) {
		if (v instanceof LongValue) {
			long t = ((LongValue) v).getValue();
			return new LongValue(Convert.convetUnsignedValue(t, Convert.getBitCount(inst)));
		}

		return v;
	}

	public void orMemoryValue(long address, Value v, Instruction inst) {
		Value o = getMemoryValue(address, inst);
		o = o.orFunction(v);
		setMemoryValue(address, o, inst);
	}

	public void orMemoryValue(X86MemoryOperand dest, Value v, Instruction inst) {
		/*
		 * if (dest.getBase() != null &&
		 * dest.getBase().toString().contains("esp")) {
		 * env.getStack().or(dest.getDisplacement(), v, inst); return; }
		 */

		long d = evaluateAddress(dest);

		if (d == UNKNOWN) {
			return;
		}

		this.orMemoryValue(d, v, inst);
	}

	public void resetValue(AbsoluteAddress address, Instruction instr) {
		// TODO Auto-generated method stub
		if (instr == null) {
			return;
		}

		ExecutableImage module = Program.getProgram().getModule(address);

		for (Map.Entry<Long, Value> entry : reset.entrySet()) {
			long m = entry.getKey();
			// long addr = evaluateAddress(m);
			long fp1 = module.getFilePointer(new AbsoluteAddress(m));
			module.getDisassembler().setMemoryByteValue((int) fp1, ((LongValue) entry.getValue()).getValue());
			if (m - address.getValue() < instr.getSize() && m - address.getValue() >= 0) {
				Program.getProgram().setTechnique("SMC");
				Program.getProgram().setDetailTechnique("SMC:" + address + " ");
			}
			// reset.put(addr, v);
		}
	}

	public void rlMemoryValue(long address, Value v, Instruction inst) {
		Value o = getMemoryValue(address, inst);
		o = o.rlFunction(v);
		setMemoryValue(address, o, inst);
	}

	public void rlMemoryValue(X86MemoryOperand dest, Value v, Instruction inst) {
		/*
		 * if (dest.getBase() != null &&
		 * dest.getBase().toString().contains("esp")) {
		 * env.getStack().rl(dest.getDisplacement(), v, inst); return; }
		 */

		long d = evaluateAddress(dest);

		if (d == UNKNOWN) {
			return;
		}

		this.rlMemoryValue(d, v, inst);
	}

	public void rrMemoryValue(long address, Value v, Instruction inst) {
		Value o = getMemoryValue(address, inst);
		o = o.rrFunction(v);
		setMemoryValue(address, o, inst);
	}

	public void rrMemoryValue(X86MemoryOperand dest, Value v, Instruction inst) {
		/*
		 * if (dest.getBase() != null &&
		 * dest.getBase().toString().contains("esp")) {
		 * env.getStack().rr(dest.getDisplacement(), v, inst); return; }
		 */

		long d = evaluateAddress(dest);

		if (d == UNKNOWN) {
			return;
		}

		this.rrMemoryValue(d, v, inst);
	}
	
	public String toHashString() {
		if (isChanged) {
			MD5 md5 = new MD5();
			String memoryStr = getOrderedStringContent();
			try {
				md5.Update(memoryStr, null);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			hashValue = md5.asHex();
			isChanged = false;
		}
		
		return this.hashValue;
	}

	public void setByteMemoryValue(long address, Value v) {
		isChanged = true;
		memory.put(address, v);
	}

	public void setByteMemoryValue(X86MemoryOperand dest, Value v) {
		long d = evaluateAddress(dest);

		if (d == UNKNOWN) {
			return;
		}

		this.setByteMemoryValue(d, v);
	}

	public void setDoubleWordMemoryValue(long address, Value v) {
		isChanged = true;
		if (v instanceof LongValue) {
			long x = ((LongValue) v).getValue();
			int[] t = BitVector.longToBytes(x, 4);

			long x4 = t[0];
			long x3 = t[1];
			long x2 = t[2];
			long x1 = t[3];

			memory.put(address, new LongValue(x1));
			memory.put(address + 1, new LongValue(x2));
			memory.put(address + 2, new LongValue(x3));
			memory.put(address + 3, new LongValue(x4));
		} else {
			memory.put(address, v);
		}
	}

	public void setQWordMemoryValue(long address, Value v) {
		isChanged = true;
		if (v instanceof LongValue) {
			long x = ((LongValue) v).getValue();
			int[] t = BitVector.longToBytes(x, 8);

			long x8 = t[0];
			long x7 = t[1];
			long x6 = t[2];
			long x5 = t[3];
			long x4 = t[4];
			long x3 = t[5];
			long x2 = t[6];
			long x1 = t[7];

			memory.put(address, new LongValue(x1));
			memory.put(address + 1, new LongValue(x2));
			memory.put(address + 2, new LongValue(x3));
			memory.put(address + 3, new LongValue(x4));
			memory.put(address + 4, new LongValue(x5));
			memory.put(address + 5, new LongValue(x6));
			memory.put(address + 6, new LongValue(x7));
			memory.put(address + 7, new LongValue(x8));
			
		} else {
			memory.put(address, v);
		}
	}
	
	public void setDoubleWordMemoryValue(X86MemoryOperand dest, Value v) {
		long d = evaluateAddress(dest);

		/*
		 * if (d == 4202596) { System.out.println("Debug"); }
		 */

		if (d == UNKNOWN) {
			return;
		}

		this.setDoubleWordMemoryValue(d, v);
	}

	public void setQWordMemoryValue(X86MemoryOperand dest, Value v) {
		long d = evaluateAddress(dest);

		/*
		 * if (d == 4202596) { System.out.println("Debug"); }
		 */
		if (d == UNKNOWN) {
			return;
		}

		this.setQWordMemoryValue(d, v);
	}
	
	public void setEnvironment(Environment environment) {
		env = environment;
	}

	private void setMemoryValue(long dest, Value x, Instruction inst) {
		if (x == null) {
			return;
		}

		x = this.normalizeValue(x, inst);

		// YenNguyen: Change from conditions of "endWith(String)" method to
		// switch/case by last character in order to reduce computing
		char lastChar = inst.getName().charAt(inst.getName().length() - 1);

		switch (lastChar) {
		case 'b':
			setByteMemoryValue(dest, x);
			break;
		case 's':
		case 'w':
			setWordMemoryValue(dest, x);
			break;
		case 'l':
		default:
			setDoubleWordMemoryValue(dest, x);
			break;
		}
	}

	public void setMemoryValue(X86MemoryOperand dest, Value x, Instruction inst) {
		// TODO Auto-generated method stub
		if (x == null) {
			return;
		}

		x = this.normalizeValue(x, inst);

		if (dest.getDataType() == DataType.INT32) {
			setDoubleWordMemoryValue(dest, x);
		} else if (dest.getDataType() == DataType.INT8) {
			setByteMemoryValue(dest, x);
		} else if (dest.getDataType() == DataType.INT16) {
			setWordMemoryValue(dest, x);
		}
	}

	public void setMemoryValue(DataType dt, X86MemoryOperand dest, Value x, Instruction inst) {
		// TODO Auto-generated method stub
		if (x == null) {
			return;
		}

		x = this.normalizeValue(x, inst);
		
		if (dt == DataType.INT32) {
			setDoubleWordMemoryValue(dest, x);
		} else if (dt == DataType.INT8) {
			setByteMemoryValue(dest, x);
		} else if (dt == DataType.INT16) {
			setWordMemoryValue(dest, x);
		} else if (dt == DataType.INT64) {
			setQWordMemoryValue(dest, x);
		}
	}
	
	public void setText(API api, long address, String str) {
		str = Convert.reduceText(str);
		char[] t = str.toCharArray();
		int numOfBytes = (api.is64bit()) ? 2 : 1;
		for (int i = 0; i < t.length; i++) {
			int x = t[i];
			if (api.is64bit()) {
				this.setWordMemoryValue(address, new LongValue(x));				
			} else {
				this.setByteMemoryValue(address, new LongValue(x));				
			}			
			address += numOfBytes;
		}
	}

	public void setText(int charSize, long address, String str) {
		// ASCII - 1-byte per char
		// Unicode - 2-byte per char
		str = Convert.reduceText(str);
		char[] t = str.toCharArray();
		int len = t.length;
		for (int i = 0; i <= t.length; i++) {
			int x = i < len ? t[i] : 0;
			if (charSize == 2) {
				this.setWordMemoryValue(address, new LongValue(x));
			} else {
				this.setByteMemoryValue(address, new LongValue(x));
			}
			address += charSize;
		}
	}

	public long setText(API api, long address, String str, int length) {
		char[] charArray = str.toCharArray();
		int numOfBytes = (api.is64bit()) ? 2 : 1;
		for (int i = 0; i < length && i < charArray.length; i++) {
			int x = charArray[i];

			if (api.is64bit()) {
				this.setWordMemoryValue(address, new LongValue(x));				
			} else {
				this.setByteMemoryValue(address, new LongValue(x));				
			}	
			address += numOfBytes;
		}
		return charArray.length;
	}

	public void setWordMemoryValue(long address, Value v) {
		isChanged = true;
		if (v instanceof LongValue) {
			long x = ((LongValue) v).getValue();
			int[] t = BitVector.longToBytes(x, 2);

			long x2 = t[0];
			long x1 = t[1];

			memory.put(address, new LongValue(x1));
			memory.put(address + 1, new LongValue(x2));
		} else {
			memory.put(address, v);
		}
	}

	public void setWordMemoryValue(X86MemoryOperand dest, Value v) {
		if (v == null) {
			return;
		}

		long d = evaluateAddress(dest);

		if (d == UNKNOWN) {
			return;
		}

		this.setWordMemoryValue(d, v);
	}

	public void subMemoryValue(long address, Value v, Instruction inst) {
		Value o = getMemoryValue(address, inst);
		o = o.subFunction(v);
		setMemoryValue(address, o, inst);
	}

	public void subMemoryValue(X86MemoryOperand dest, Value v, Instruction inst) {
		// Process with Stack

		long d = evaluateAddress(dest);

		if (d == UNKNOWN) {
			return;
		}

		this.subMemoryValue(d, v, inst);
	}

	public void xorMemoryValue(long address, Value v, Instruction inst) {
		Value o = getMemoryValue(address, inst);
		o = o.xorFunction(v);
		setMemoryValue(address, o, inst);
	}

	public void xorMemoryValue(X86MemoryOperand dest, Value v, Instruction inst) {
		long d = evaluateAddress(dest);

		if (d == UNKNOWN) {
			return;
		}

		this.xorMemoryValue(d, v, inst);
	}

	public void divSignMemoryValue(long address, Value v, Instruction inst) {
		Value o = getMemoryValue(address, inst);
		if (o instanceof LongValue && v instanceof LongValue) {
			long dest = ((LongValue) o).getValue();
			long src = ((LongValue) v).getValue();
			int b = Convert.getBitCount(inst);
			int signBit = Convert.getSignBit(dest, b);

			for (int j = 1; j <= src; j++) {
				dest = dest / 2;
			}

			for (int j = 1; j <= src; j++) {
				dest += signBit * Math.pow(2, b - j);
			}

			setMemoryValue(address, new LongValue(dest), inst);
			return;
		}

		o = o.unsignedDivFunction(v);
		setMemoryValue(address, o, inst);
	}

	public void divSignMemoryValue(X86MemoryOperand dest, Value v, Instruction inst) {

		long d = evaluateAddress(dest);

		if (d == UNKNOWN) {
			return;
		}

		this.divSignMemoryValue(d, v, inst);
	}

	public void getMemoryAddress(X86MemoryOperand t, X86Instruction inst) {
		// TODO Auto-generated method stub

	}

	public long[] getBytesArray(AbsoluteAddress address, int n) {
		// TODO Auto-generated method stub
		if (address == null) {
			return null;
		}
		long addr = address.getValue();

		long[] ret = new long[n];
		for (int i = 0; i < n; i++) {
			// Phong - 20151113 - Fixed get byte memory value without value
			// Value v = memory.get(addr + i);
			Value v = getByteMemoryValue(addr + i);
			if (v != null && v instanceof LongValue) {
				ret[i] = ((LongValue) v).getValue();
			} else {
				ret[i] = Long.MIN_VALUE;
			}
		}

		return ret;
	}

	public void resetImportTable(Program program) {
		program.getLog().info("Reset address of import table");
		ExecutableImage m = program.getMainModule();

		if (m != null && m instanceof PEModule) {
			Map<AbsoluteAddress, Pair<String, String>> importTable = ((PEModule) m).getImportTable();

			for (Map.Entry<AbsoluteAddress, Pair<String, String>> entry : importTable.entrySet()) {
				// ret += entry.getKey() + "\t" + entry.getValue() + "\n";
				// if (entry.getValue().getRight().contains("RegSetValueExA"))
				// System.out.println("Debug");
				long temp = getProcAddress(entry.getValue().getLeft(), entry.getValue().getRight());
				setDoubleWordMemoryValue(entry.getKey().getValue(), new LongValue(temp));
			}
		}
	}

	private long getProcAddress(String libraryName, String procName) {
		long libHandle = 0;

		// Try to find the handle of current library
		if (APIHandle.libraryHandleMap.containsValue(libraryName)) {
			for (Entry<Long, PairEntry<String, Integer>> handle : APIHandle.libraryHandleMap.entrySet()) {
				if (handle.getValue().getKey().equals(libraryName)) {
					libHandle = handle.getKey();
					break;
				}
			}
		} else {
			// If can not find, xecute LoadLibrary API
			libHandle = (new LoadLibrary()).execute(libraryName);
		}

		return (new GetProcAddress()).execute(libHandle, procName);
	}

	public void resetValue(Map<String, Long> z3Value) {
		// TODO Auto-generated method stub
		// System.out.println("Implement the memory later.");
		for (Map.Entry<Long, Value> entry : memory.entrySet()) {
			if (!(entry.getValue() instanceof LongValue)) {
				entry.setValue(entry.getValue().evaluate(z3Value));
			}
		}
	}

	public Value getExDoubleWordMemoryValue(X86MemoryOperand dest) {
		// TODO Auto-generated method stub
		long d = evaluateAddress(dest);

		// PHONG: 20150506 If segment is FS
		// ------------------------------------------------------
		/*
		 * if (dest.getSegmentRegister() != null &&
		 * dest.getSegmentRegister().toString() == "%fs") { d =
		 * TIB.getTIB_Base_Address() + d; }
		 */
		// ---------------------------------------------------------------------------------------
		if (d == UNKNOWN) {
			return new SymbolValue(SymbolValue.generateString(dest));
		}

		return this.getDoubleWordMemoryValueEx(d);
	}

	private Value getDoubleWordMemoryValueEx(long address) {
		// TODO Auto-generated method stub
		String libName = env.getSystem().getLibraryHandle().insideDLL(new AbsoluteAddress(address));
		if (libName != null && libName != ""){
			return new LongValue(env.getSystem().getLibraryHandle().readDoubleWord(libName, (int) address));
		}

		if (env.getSystem().getFileHandle().isInsideFile(new AbsoluteAddress(address))) {
			return new LongValue(env.getSystem().getFileHandle().readDoubleWord((int) address));
		}

		long ret1 = UNKNOWN, ret2 = UNKNOWN, ret3 = UNKNOWN, ret4 = UNKNOWN;
		boolean p = false;

		Value v1 = memory.get(address);
		Value v2 = memory.get(address + 1);
		Value v3 = memory.get(address + 2);
		Value v4 = memory.get(address + 3);

		if (v1 != null) {
			if (v1 instanceof LongValue) {
				ret1 = ((LongValue) v1).getValue();
				p = true;
			} else {
				// return new SymbolValue(SymbolValue.generateString(new
				// X86MemoryOperand(DataType.INT32, address)));
				return v1;
			}
		}

		if (v2 != null) {
			if (v2 instanceof LongValue) {
				ret2 = ((LongValue) v2).getValue();
				p = true;
			} else {
				return new SymbolValue(SymbolValue.generateString(new X86MemoryOperand(DataType.INT32, address)));
			}
		}

		if (v3 != null) {
			if (v3 instanceof LongValue) {
				ret3 = ((LongValue) v3).getValue();
				p = true;
			} else {
				return new SymbolValue(SymbolValue.generateString(new X86MemoryOperand(DataType.INT32, address)));
			}
		}

		if (v4 != null) {
			if (v4 instanceof LongValue) {
				ret4 = ((LongValue) v4).getValue();
				p = true;
			} else {
				return new SymbolValue(SymbolValue.generateString(new X86MemoryOperand(DataType.INT32, address)));
			}
		}

		if (p) {
			if (ret1 == UNKNOWN) {
				ret1 = program.getByteValueMemory(new AbsoluteAddress(address));
			}

			if (ret2 == UNKNOWN) {
				ret2 = program.getByteValueMemory(new AbsoluteAddress(address + 1));
			}

			if (ret3 == UNKNOWN) {
				ret3 = program.getByteValueMemory(new AbsoluteAddress(address + 2));
			}

			if (ret4 == UNKNOWN) {
				ret4 = program.getByteValueMemory(new AbsoluteAddress(address + 3));
			}
		}

		if (ret1 != UNKNOWN && ret2 != UNKNOWN && ret3 != UNKNOWN && ret4 != UNKNOWN) {
			long value = calculateDoubleWordValue(ret1, ret2, ret3, ret4);
			// BPLogger.debugLogger.info(address + ":" + value);
			return new LongValue(value);
		}

		/*
		 * if (ret1 != UNKNOWN) { try { return new
		 * LongValue(calculateWordValue(ret1, program.getWordValueMemory(new
		 * AbsoluteAddress(address + 1)))); } catch (Exception e) { return new
		 * TopValue(); } } else if (ret2 != UNKNOWN) { try { return new
		 * LongValue(calculateWordValue(program.getWordValueMemory(new
		 * AbsoluteAddress(address)), ret2)); } catch (Exception e) { return new
		 * TopValue(); } }
		 */
		// Chinh sua sau van de nay

		try {
			if (program.isInside(new AbsoluteAddress(address))) {
				return new LongValue(program.getDoubleWordValueMemory(new AbsoluteAddress(address)));
			} else {
				// if (address != 0) {
				// ExternalMemoryReturnData ret =
				// ExternalMemory.getDoubleWord(address);
				// if (ret != null && ret.isValidAddress) {
				// return ret.value;
				// }
				// }
				return new LongValue(0);
			}
		} catch (Exception e) {
			// return new SymbolValue(SymbolValue.generateString(new
			// X86MemoryOperand(
			// DataType.INT32, address)));
			return new LongValue(0);
		}
	}

	public String getRawText(API api, long addr, int i) {
		// TODO Auto-generated method stub
		int numOfBytes = (api.is64bit()) ? 2 : 1;
		return getRawText(addr, numOfBytes, i);
	}

	private String getRawText(long addr, int charSize, int num) {
		// TODO Auto-generated method stub
		StringBuilder ret = new StringBuilder();
		
		for (int i=0; i < num; i++) {
			Value t = (charSize == 1) ? getByteMemoryValue(addr, false) : getWordMemoryValue(addr);

			if (t != null && t instanceof LongValue) {
				char t1 = (charSize == 1) //
				? (char) (byte) (((LongValue) t).getValue() & 0xFF) //
						: (char) (((LongValue) t).getValue() & 0xFFFF);

				ret.append(t1);
				addr += charSize;
			} else if (t instanceof SymbolValue) {
				return ret.toString();
			}
		}

		return ret.toString();

	}

	/*
	 * private long normalizeValue(long v, Instruction inst) { // TODO
	 * Auto-generated method stub return Convert.convetUnsignedValue(v,
	 * Convert.getBitCount(inst)); }
	 */
}
