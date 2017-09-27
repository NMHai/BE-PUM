package v2.org.analysis.comparison;

import org.jakstab.Program;
import v2.org.analysis.statistics.FileProcess;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DOTComparison {
	static String resultFile = "data/data/comparison.txt";
	static String resultFileTest = "data/data/comparison_test.txt";
	private String instSrc = "", instDst = "";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String in = "";
		// file = "asm/virus/seh.exe";
		in = "Virus.Win32.Adson.1559";
		// in = "Virus.Win32.Aztec.01";
		// in = "seh.exe";
		// in = "ws2_32.exe";
		// in = "Virus.Win32.Seppuku.1606"; // 41 40 0.3s
		// in = "cmd.exe";
		// in = "calc.exe";
		// in = "diskcopy.com";
		// in = "seh.exe";
		// in = "hostname.exe";
		in = "Virus.Win32.HLLW.Rolog.f";

		String dest = "asm/cfg/" + in + ".dot";
		String src = "asm/cfg/" + in + "_model.dot";
		// System.out.println(new DOTComparison().compare(src, dest));
		new DOTComparison().exportComparison(src, dest);
	}

	private String compare(CFG s, CFG d) {
		// TODO Auto-generated method stub
		List<Edge> edges = s.getEdgesList();
		List<Vertex> verteces = s.getVertecesList();
		String result = "";
		String temp = "";
		for (Vertex vertex : verteces) {
			String t = contain(d, vertex);
			if (t != "")
				temp += t + "\n";
		}
		if (temp == "")
			result += "Equal Vertex Set\n";
		else
			result += temp;
		result += "---------------------------------------------------------------\n";

		temp = "";
		for (Edge edge : edges) {
			// if (!contain(d, edge))
			String t = contain(edge, d);
			if (t != "")
				temp += t + "\n";
			// temp += !contain(d, edge) + "\n";
		}

		if (temp == "")
			result += "Equal Edge Set\n";
		else
			result += temp;

		return result;
	}

	private boolean compare(Vertex s, Vertex d) {
		// TODO Auto-generated method stub
		if (s.getAddress() == null || d.getAddress() == null) {
			// System.out.println("Debug");
			return false;
		}

		// if (s.getAddress().contains("dll") || d.getAddress().contains("dll"))
		// return true;
		if (s.isAPI()) {
			String dx = d.getInstructionString().toLowerCase();
			String sx = "ds:__imp_" + s.getInstructionString().toLowerCase();
			// return
			// d.getInstructionString().toLowerCase().contains("ds:__imp_" +
			// s.getInstructionString().toLowerCase());
			return dx.contains(sx);
		} else if (compareStringAddr(s.getAddress(), d.getAddress())) {
			return compareStringInst(s.getInstructionString(), d.getInstructionString());
		}

		return false;
	}

	private boolean compareInstMnemonic(String i1, String i2) {
		// TODO Auto-generated method stub
		String s[] = i1.split(" ");
		String d[] = i2.split(" ");

		String s1 = s[0];
		String d1 = normalizeMnemonic(d[0], this.instDst);

		if (s1.equals(d1))
			return true;

		if (specialCase(s[0], d[0]))
			return true;

		return false;
	}

	private boolean compareInstOperands(String i1, String i2) {
		// TODO Auto-generated method stub
		String s[] = i1.split(" ");
		String d[] = i2.split(" ");

		if (d[0].contains("rep") || (d[0].contains("shl") && s.length != d.length)) {
			return compareOperand(s[1], d[1]);
		}

		if (s[0].equals("call")) {
			Pattern p = Pattern.compile("[^A-Za-z]");
			Matcher m = p.matcher(s[1]);
			// boolean b = m.matches();
			boolean b = m.find();
			if (!b) {
				// System.out.println("Consider Equal:" + s[1] + " vs " + d[1]);
				return true;
			}
		} else if (d[0].equals("jmp") && d[1].contains("@") && s[1].contains("ds:imp")) {
			// System.out.println("Consider Equal:" + s[1] + " vs " + d[1]);
			String s2 = s[1].replaceAll("ds:imp", "");
			String d2[] = d[1].split("@");

			return s2.equals(d2[0]);
		} else if (d[0].contains("xchg") && s[0].contains("xchg")) {
			return compareOperand(s[1], d[1]) && compareOperand(s[2], d[2]);
		} else if (d[0].contains("unsignedDiv") && s[0].contains("unsignedDiv")) {
			return compareOperand(s[1], d[1]);
		} else if (d[0].contains("imul") && s[0].contains("imul")) {
			return compareOperand(s[2], d[1]);
		}

		if (s.length != d.length)
			return false;

		if (s.length == 2) {
			return compareOperand(s[1], d[1]);
		} else if (s.length == 3) {
			return compareOperand(s[1], d[2]) && compareOperand(s[2], d[1]);
		} else if (s.length == 4) {
			return compareOperand(s[1], d[1]) && compareOperand(s[2], d[3]) && compareOperand(s[3], d[2]);
		}

		return true;
	}

	private boolean compareOperand(String s, String d) {
		// TODO Auto-generated method stub
		// System.out.println("");
		String s1 = normalizeOperand(s, this.instSrc);
		String d1 = normalizeOperand(d, this.instDst);

		String s2 = roundOperandValue(s1, this.instSrc);
		String d2 = roundOperandValue(d1, this.instDst);

		if (s1.contains("+") || s1.contains("-") || s1.contains("*") || s1.contains("/") || s1.startsWith("a")) {
			// System.out.println("Consider Equal:" + s1 + " vs " + d1);
		} else if (!s1.equals(d1) && !s2.equals(d2) && checkSpecialCase(s1) && checkSpecialCase(d1))
			return false;

		return true;
		// return normalizeOperand(s).equals(normalizeOperand(d));
	}

	private boolean checkSpecialCase(String str) {
		Pattern p1 = Pattern.compile("[^A-Za-z]");
		Matcher m1 = p1.matcher(str);
		// boolean b = m.matches();
		boolean r1 = m1.find();

		// boolean b = m.matches();
		boolean r2 = !str.startsWith("f");

		// Pattern p2 = Pattern.compile("[*G-Zg-z*]");
		// Matcher m2 = p1.matcher(str);
		// boolean b = m.matches();
		// boolean r3 = m1.find();

		// return r1 && r2 && r3;
		return r1 && r2;
	}

	private boolean compareStringAddr(String addr1, String addr2) {
		// TODO Auto-generated method stub
		String s1 = normalizeAddr(addr1);
		String s2 = normalizeAddr(addr2);

		if (s1 == null && s2 == null)
			return true;
		else if (s1 != null && s2 != null) {
			return s1.equals(s2);
		}

		return false;
	}

	private boolean compareStringInst(String inst1, String inst2) {
		// TODO Auto-generated method stub
		this.instSrc = inst1;
		this.instDst = inst2;

		String i1 = normalizeInst(inst1);
		String i2 = normalizeInst(inst2);

		// return compareInstMnemonic(i1, i2) && checkInstOperand(i1, i2);
		if (specialInst(i1, i2))
			return true;

		if (!compareInstMnemonic(i1, i2))
			return false;

		if (!compareInstOperands(i1, i2))
			return false;

		return true;
	}

	private String contain(CFG d, Vertex vertex) {
		// TODO Auto-generated method stub
		List<Vertex> verteces = d.getVertecesList();
		// if (vertex.getAddress().contains("402159"))
		// System.out.println("Debug");

		if (vertex.isAPI()) {
			for (Vertex t : verteces) {
				String dx = t.getInstructionString().toLowerCase();
				String sx = "ds:__imp_" + vertex.getInstructionString().toLowerCase();
				// return
				// d.getInstructionString().toLowerCase().contains("ds:__imp_" +
				// s.getInstructionString().toLowerCase());
				if (dx.contains(sx))
					return "";
			}
		} else {
			for (Vertex v : verteces) {
				if (compareStringAddr(v.getAddress(), vertex.getAddress())) {
					if (compareStringInst(v.getInstructionString(), vertex.getInstructionString()))
						return "";

					return "Vertex Change: " + vertex.getAddress() + ": " + vertex.getInstructionString() + " vs "
							+ v.getAddress() + ": " + v.getInstructionString();
				}
			}
		}

		return "Not contain Vertex: " + vertex.getAddress() + ": " + vertex.getInstructionString();
	}

	private String contain(Edge edge1, CFG d) {
		// TODO Auto-generated method stub
		List<Edge> edges = d.getEdgesList();
		for (Edge edge2 : edges) {
			if (compare(edge2.getDest(), edge1.getDest()) && compare(edge2.getSource(), edge1.getSource()))
				return "";

		}
		return "Not contain Edge: " + edge1.toString();
	}

	public void exportComparison(String fileName) {
		// TODO Auto-generated method stub
		generateCFGForIDA(fileName);
		String file = Program.getProgram().generatePathFileName(fileName);
		String src = file + "_model.dot";
		String dest = file + ".dot";
		FileProcess fp = new FileProcess(resultFileTest);
		fp.clearContentFile();
		System.out.println("===============================================================================");
		fp.appendFile("===============================================================================");
		System.out.println("Comparison between BE-PUM and IDA Pro on DOT file: " + src + " vs " + dest + ":");
		fp.appendFile("Comparison between BE-PUM and IDA Pro on DOT file: " + src + " vs " + dest + ":");
		System.out.println("===============================================================================");
		fp.appendFile("---------------------------------------------------------------");
		CFG s = extractCFGFromDOT(src);
		CFG d = extractCFGFromDOT(dest);
		// fp.clearContentFile();
		String ret = compare(s, d);
		fp.appendFile(ret);
		System.out.println(ret);
		fp.appendFile("===============================================================================");
	}

	public void exportComparison(String src, String dest) {
		// TODO Auto-generated method stub
		// generateCFGForIDA(fileName);
		// String file = Program.getProgram().generatePathFileName(fileName);
		// String src = file + "_model.dot";
		// String dest = fileName + ".dot";
		FileProcess fp = new FileProcess(resultFileTest);
		fp.clearContentFile();
		System.out.println("===============================================================================");
		fp.appendFile("===============================================================================");
		System.out.println("Comparison between BE-PUM and IDA Pro on DOT file: " + src + " vs " + dest + ":");
		fp.appendFile("Comparison between BE-PUM and IDA Pro on DOT file: " + src + " vs " + dest + ":");
		System.out.println("===============================================================================");
		fp.appendFile("---------------------------------------------------------------");
		CFG s = extractCFGFromDOT(src);
		CFG d = extractCFGFromDOT(dest);
		String r = compare(s, d);
		// System.out.println(r);
		fp.appendFile(r);
		fp.appendFile("===============================================================================");
	}

	private CFG extractCFGFromDOT(String fileName) {
		// TODO Auto-generated method stub
		CFG c = new CFG();

		try {
			// clearContentFile(unprocessedFile);
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			// StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				if (line.contains("[label=")) {
					Vertex t = extractVertex(line);
					c.insertVertex(t);

					if (line.toLowerCase().contains("start"))
						c.setStartPoint(t);
				} else if (line.contains("->"))
					c.insertEdge(extractEdge(line, c));
				line = br.readLine();
				// if (numFile > 0) break;
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return c;
	}

	private Edge extractEdge(String line, CFG c) {
		// TODO Auto-generated method stub
		line = line.replace(" ", "");
		String t[] = line.split("->");
		String id1 = t[0];
		Vertex s = c.getVertex(id1);

		byte[] b = t[1].getBytes();
		String id2 = "";
		for (int i = 0; i < b.length; i++) {
			if (b[i] == '[' || b[i] == ';')
				break;
			id2 += (char) b[i];
		}

		Vertex d = c.getVertex(id2);

		return new Edge(s, d);
	}

	private Vertex extractVertex(String line) {
		// TODO Auto-generated method stub
		/*
		 * if (line.contains("dll")) System.out.println("Debug");
		 */

		Vertex v = new Vertex();

		byte[] b = line.getBytes();
		boolean id = true, label = true, addr = true, ins = true;
		String temp = "";
		for (int i = 0; i < b.length; i++) {
			if (id) {
				if (b[i] == (byte) '[') {
					id = false;
					v.setID(temp);

					// Process API node
					if (temp.contains("dll") && !temp.contains("a0x")) {
						String t[] = temp.split("_");
						v.setAddress(t[0]);
						v.setInstructionString(t[0]);
						v.setAPI(true);

						return v;
					}
					temp = "";
				} else if (b[i] != ' ')
					temp += (char) b[i];
			} else if (label) {
				if (b[i] == (byte) '\"')
					label = false;
			} else if (addr) {
				if ((b[i] == (byte) '\\' && b[i + 1] == (byte) 'n') || b[i] == (byte) '\"') {
					i++;
					addr = false;
					v.setAddress(temp);
					temp = "";
				} else if (b[i] != ' ')
					temp += (char) b[i];
			} else if (ins) {
				if (b[i] == (byte) '\"') {
					ins = false;
					v.setInstructionString(temp);
					temp = "";
				}

				temp += (char) b[i];
			}
		}

		if (v.getAddress().toLowerCase().contains("start")) {
			String t = v.getInstructionString();
			if (t.startsWith("0x") || t.contains("\\\\n")) {
				String t2[] = t.split("\\\\n");
				v.setInstructionString(t2[1]);
			}
		}

		return v;
	}

	private void generateCFGForIDA(String fileName) {
		try {
			//YenNguyen
			Process p = Runtime.getRuntime().exec(
					"cmd /c start /wait " + System.getProperty("user.dir")
							+ "/data/ExportCFG.bat" + " " + fileName);
			Program.getProgram().getLog().info("Running script ExportCFG.bat...");
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

	private String normalizeAddr(String addr) {
		// TODO Auto-generated method stub
		String ret = addr;
		if (ret != null) {
			ret = ret.toLowerCase();
			ret = ret.replaceAll("0x", "");
			while (true) {
				if (ret.startsWith("0"))
					ret = ret.substring(1);
				else
					break;
			}
		}

		return ret;
	}

	private String normalizeInst(String inst) {
		// TODO Auto-generated method stub
		String ret = inst;
		ret = ret.replaceAll("stru_", "");
		ret = ret.replaceAll(",", "");
		ret = ret.replaceAll("_", "");
		ret = ret.replaceAll("loc", "");
		ret = ret.replaceAll("%", "");
		ret = ret.replaceAll("<UINT32>", "");
		ret = ret.replaceAll("<UINT16>", "");
		ret = ret.replaceAll("<UINT8>", "");
		ret = ret.replaceAll("0x", "");
		ret = ret.replaceAll("dword", "");
		ret = ret.replaceAll("ptr", "");
		ret = ret.replaceAll("offset", "");
		ret = ret.replaceAll("byte", "");
		ret = ret.replaceAll("short", "");
		ret = ret.replaceAll("word", "");
		ret = ret.replaceAll("large", "");
		ret = ret.replaceAll(" +", " ");
		ret = ret.toLowerCase();

		return ret;
	}

	private String normalizeMnemonic(String inst, String fullInst) {
		// TODO Auto-generated method stub
		if (inst.equals("call") || inst.equals("jl"))
			return inst;

		if (inst.equals("ret"))
			return inst + "n";

		if (inst.endsWith("s") || inst.endsWith("w") || inst.endsWith("b") || inst.endsWith("l"))
			return inst.substring(0, inst.length() - 1);

		return inst;
	}

	private String normalizeOperand(String s, String fullInst) {
		// TODO Auto-generated method stub
		String ret = s;
		while (true) {
			if (ret.startsWith("0") && ret.length() >= 2)
				ret = ret.substring(1);
			else
				break;
		}

		ret = ret.replaceAll("\\[", "(");
		ret = ret.replaceAll("\\]", ")");
		// ret = ret.replaceAll("\\(", "_");
		// ret = ret.replaceAll("\\)", "");
		ret = ret.replaceAll("off", "");
		ret = ret.replaceAll("ss:", "");
		ret = ret.replaceAll("ds:", "");
		ret = ret.replaceAll("fs:", "");
		ret = ret.replaceAll("cs:", "");
		ret = ret.replaceAll("es:", "");
		ret = ret.replaceAll("gs:", "");
		ret = ret.replaceAll(":", "");
		ret = ret.replaceAll("unk", "");
		ret = ret.replaceAll("sub", "");
		ret = ret.replaceAll("ret", "");
		ret = ret.replaceAll("\\$", "");

		if (ret.endsWith("h"))
			ret = ret.substring(0, ret.length() - 1);

		if (ret.contains("@"))
			ret = ret.substring(0, ret.indexOf("@"));

		return ret;
	}

	private String roundOperandValue(String s, String fullInst) {
		String ret = s;

		if (ret.startsWith("f")) {
			if ((fullInst.contains("byte") || fullInst.contains("UINT8")) && ret.length() >= 2)
				ret = ret.substring(ret.length() - 2, ret.length());
			else if (fullInst.contains("word") && ret.length() >= 4)
				ret = ret.substring(ret.length() - 4, ret.length());
			else if ((fullInst.contains("dword") || fullInst.contains("<UINT32>")) && ret.length() >= 8)
				ret = ret.substring(ret.length() - 8, ret.length());
		}

		return ret;
	}

	private boolean specialCase(String s, String d) {
		// TODO Auto-generated method stub
		return (s.equals("jne") || s.equals("jnz")) && (d.equals("jne") || d.equals("jnz"))
				|| (s.equals("je") || s.equals("jz")) && (d.equals("je") || d.equals("jz"))
				|| (s.equals("jae") || s.equals("jnb")) && (d.equals("jae") || d.equals("jnb"))
				|| (s.equals("repe") || s.equals("repz")) && (d.equals("repe") || d.equals("repz"))
				|| (s.contains("movzx") || s.contains("movzwl")) && (d.contains("movzwl") || d.equals("movzx"))
				|| (s.contains("movsx") || s.contains("movsbl")) && (d.contains("movsbl") || d.equals("movsx"));
	}

	private boolean specialInst(String i1, String i2) {
		// TODO Auto-generated method stub
		if (i1.startsWith("scasb") && i2.startsWith("scasb"))
			return true;

		if (i1.startsWith("lods") && i2.startsWith("lods"))
			return true;

		if (i1.startsWith("stos") && i2.startsWith("stos"))
			return true;

		if (i1.startsWith("cmps") && i2.startsWith("cmps"))
			return true;

		if (i1.startsWith("rep") && i2.startsWith("rep")) {
			String i3 = i1.substring(i1.indexOf(" ") + 1);
			String i4 = i2.substring(i2.indexOf(" ") + 1);

			return compareStringInst(i3, i4);
		}

		if (i1.startsWith("setn") && i2.startsWith("setn")) {
			String i3 = i1.substring(i1.indexOf(" ") + 1);
			String i4 = i2.substring(i2.indexOf(" ") + 1);

			return compareInstOperands(i3, i4);
		}

		return false;
	}

}
