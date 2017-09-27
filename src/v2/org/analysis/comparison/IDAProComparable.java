package v2.org.analysis.comparison;

import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Immediate;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.Operand;
import org.jakstab.asm.x86.X86MemoryOperand;
import v2.org.analysis.cfg.BPCFG;
import v2.org.analysis.cfg.BPVertex;
import v2.org.analysis.statistics.FileProcess;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class IDAProComparable extends Comparison {
	public class Vertex {
		private AbsoluteAddress addr = null;
		private String inst = "";

		public Vertex() {

		}

		public Vertex(AbsoluteAddress a, String inst) {
			this.setAddress(a);
			this.setInstructionString(inst);
		}

		/**
		 * @return the addr
		 */
		public AbsoluteAddress getAddress() {
			return addr;
		}

		/**
		 * @param addr
		 *            the addr to set
		 */
		public void setAddress(AbsoluteAddress addr) {
			this.addr = addr;
		}

		/**
		 * @return the inst
		 */
		public String getInstructionString() {
			return inst;
		}

		/**
		 * @param inst
		 *            the inst to set
		 */
		public void setInstructionString(String inst) {
			this.inst = inst;
		}

		@Override
		public String toString() {
			return addr.getValue() + " " + inst;
		}
	}

	private class CFG {

		List<Vertex> vertices = new ArrayList<Vertex>();

		public void insertVertex(Vertex t) {
			// TODO Auto-generated method stub
			if (!contain(t))
				vertices.add(t);
		}

		public boolean contain(Vertex t) {
			for (Vertex temp : vertices)
				if (temp.getAddress().getValue() == t.getAddress().getValue()
						&& temp.getInstructionString().equals(t.getInstructionString()))
					return true;
			return false;
		}

		@Override
		public String toString() {
			String r = "";
			for (Vertex t : vertices)
				r += t.toString() + ", ";

			return r;
		}

		public List<Vertex> getVertecesList() {
			// TODO Auto-generated method stub
			return vertices;
		}
	}

	private BPCFG cfg;
	private String outputFileName = "data/data/comparison.txt"; //YenNguyen

	public IDAProComparable(BPCFG cfg) {
		this.cfg = cfg;
	}

	private String compare(BPVertex p, CFG cfg) {
		// TODO Auto-generated method stub
		if (p != null && p.getAddress() != null && p.getAddress().toString().contains("0x0040100b"))
			Program.getProgram().getLog().debugString("Debug");

		if (p == null || p.getAddress() == null)
			return "";

		List<Vertex> verteces = cfg.getVertecesList();
		for (Vertex t : verteces) {
			if (p != null && p.getAddress() != null && t.getAddress().getValue() == p.getAddress().getValue()) {
				if (compare(t.getInstructionString(), p.getInstruction()))
					return "";
				else
					return p.getAddress() + ": " + t.getInstructionString() + "vs " + getInstString(p.getInstruction());
			}
		}

		return "Not contain " + p.getAddress() + ":" + getInstString(p.getInstruction());
	}

	private boolean compare(String instString, Instruction inst) {
		// TODO Auto-generated method stub
		String[] v = instString.split(" ");
		if (!v[0].contains(inst.getName().substring(0, inst.getName().length() - 1)))
			return false;

		for (int i = 0; i < inst.getOperandCount(); i++) {
			Operand dest = inst.getOperand(i);
			String op = "";

			if (dest.getClass().getSimpleName().equals("X86Register")
					|| dest.getClass().getSimpleName().equals("X86RegisterPart")
					|| dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
				op = normalizeRegister(dest.toString());
				if (!instString.contains(op))
					return false;

			} else if (dest.getClass().getSimpleName().equals("Immediate")) {
				op = normalizeImmediate(((Immediate) dest).toString());
				if (!instString.contains(op))
					return false;

			} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
				op = normalizeMemoryOperand((X86MemoryOperand) dest);

				if (!contains((X86MemoryOperand) dest, instString))
					return false;
			}
		}

		return true;
	}

	private boolean contains(X86MemoryOperand op, String str) {
		// TODO Auto-generated method stub
		if (op.getBase() != null) {
			if (!str.contains(normalizeRegister(op.getBase().toString())))
				return false;
		}

		if (op.getDisplacement() != 0) {
			long t = op.getDisplacement();

			if (t > 0) {
				if (!str.contains(Long.toHexString(t).toUpperCase()))
					return false;
			} else {
				String x = Long.toHexString(Math.abs(t)).toUpperCase();
				if (!str.contains(x) || !str.contains("-"))
					return false;
			}
		}

		if (op.getIndex() != null) {
			if (!str.contains(normalizeRegister(op.getIndex().toString())))
				return false;
		}

		return true;
	}

	private String normalizeMemoryOperand(X86MemoryOperand op) {
		// TODO Auto-generated method stub
		String ret = "";
		if (op.getBase() != null) {
			if (op.getDisplacement() == 0)
				ret = op.getBase().toString();
			else if (op.getDisplacement() < 0) {
				long t = op.getDisplacement();
				ret = op.getBase() + "-" + Long.toHexString(Math.abs(t));
			} else {
				long t = op.getDisplacement();
				ret = op.getBase() + "+" + Long.toHexString(t);
				;
			}
		} else
			ret = op.toString();

		ret = ret.replace("%", "");
		ret = ret.replace("0x", "");
		ret = ret.toUpperCase();
		return ret;
	}

	private String normalizeImmediate(String str) {
		// TODO Auto-generated method stub
		String ret = str;
		ret = ret.replace("$0x", "");
		ret = ret.replace("<UINT32>", "");
		ret = ret.replace("<UINT8>", "");
		ret = ret.replace("<UINT16>", "");
		ret = ret.replace("f", "F");
		ret = ret.toUpperCase();
		return ret;
	}

	private String normalizeRegister(String register) {
		return register.replace("%", "");
	}

	private String getInstString(Instruction inst) {
		// TODO Auto-generated method stub
		String ret = "";
		ret += inst.getName();

		if (inst.getOperandCount() > 0)
			ret += " ";

		for (int i = 0; i < inst.getOperandCount(); i++)
			ret += inst.getOperand(i) + ", ";

		return ret;
	}

	@Override
	public void compare(String fileName) {
		// TODO Auto-generated method stub
		CFG op = extractCFG(fileName);
		System.out.println("===============================================================================");
		System.out.println("Comparison between IDA Pro and BE-PUM on " + fileName + ":");
		System.out.println("===============================================================================");
		FileProcess fp = new FileProcess(outputFileName);
		fp.clearContentFile();
		fp.appendFile(compare(op));
	}

	private String compare(CFG op) {
		// TODO Auto-generated method stub
		List<BPVertex> vers = cfg.getVertecesList();

		if (op.getVertecesList() == null || op.getVertecesList().size() == 0)
			return "IDA Pro fails to generate CFG!";

		String result = "";
		for (BPVertex p : vers) {
			String t = compare(p, op);
			if (t != "")
				result += t + "\n";
		}

		if (result == "")
			return "Equal!";

		return result;

	}

	private CFG extractCFG(String fileName) {
		// TODO Auto-generated method stub
		getLTS(fileName);
		CFG op = new CFG();

		try {
			// clearContentFile(unprocessedFile);
			BufferedReader br = new BufferedReader(new FileReader(fileName + ".lst"));
			// StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				Vertex t = getVertex(line);
				if (t != null)
					op.insertVertex(t);
				line = br.readLine();
				// if (numFile > 0) break;
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return op;
	}

	private Vertex getVertex(String line) {
		// TODO Auto-generated method stub
		// Program.getProgram().getLog().infoString("Process " + line + "\n");
		Vertex t = null;
		if (line.startsWith(".data") || line.startsWith(".rdata"))
			return null;

		if (line.startsWith(".text:") || line.startsWith("CODE:")) {
			String[] v = line.split("\\s+");

			if (v.length <= 1)
				return null;

			String addr = v[0].subSequence(6, v[0].length()).toString();

			if (v[1].startsWith(";") || v[1].equals("db") || v[1].equals("dd") || v[1].startsWith("loc_")
					|| v[1].startsWith(".") || v[1].startsWith("_text") || v[1].equals("assume")
					|| v[1].equals("public") || v[1].startsWith("start") || v[1].startsWith("unk")
					|| v[1].startsWith("_"))
				return null;

			// if (addr.equals("00401007"))
			// System.out.println("Debug");

			// Program.getProgram().getLog().error(addr + " ");
			AbsoluteAddress address = new AbsoluteAddress(Integer.parseInt(addr, 16));
			String property = "";
			for (int i = 1; i < v.length; i++) {
				if (v[i].contains(";"))
					break;
				// Program.getProgram().getLog().error(v[i] + " ");
				property += v[i] + " ";
			}
			// Program.getProgram().getLog().infoString("\n");
			t = new Vertex();
			t.setAddress(address);
			t.setInstructionString(property);
		}

		return t;
	}

	private void getLTS(String fileName) {
		try {
			Process p = Runtime.getRuntime().exec("cmd /c start /wait ExportLST.bat" + " " + fileName);
			Program.getProgram().getLog().info("Running script ExportLTS.bat...");
			int exitCode = p.waitFor();

			Program.getProgram().getLog().info("Done script. ExitCode:" + exitCode);
		} catch (Throwable e) {
			System.out.flush();
			e.printStackTrace();
			// Runtime.getRuntime().removeShutdownHook(shutdownThread);
			// Kills eclipse shutdown thread
			System.exit(1);
		}
	}

}
