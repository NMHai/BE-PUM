/**
 *
 */
package v2.org.analysis.transition_rule;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

//import v2.org.analysis.apihandle.APIHandle;
import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Immediate;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.Operand;
import org.jakstab.asm.x86.X86ArithmeticInstruction;
import org.jakstab.asm.x86.X86CallInstruction;
import org.jakstab.asm.x86.X86CondJmpInstruction;
import org.jakstab.asm.x86.X86Instruction;
import org.jakstab.asm.x86.X86JmpInstruction;
import org.jakstab.asm.x86.X86MemoryOperand;
import org.jakstab.asm.x86.X86MoveInstruction;
import org.jakstab.asm.x86.X86PCRelativeAddress;
import org.jakstab.asm.x86.X86Register;
import org.jakstab.asm.x86.X86RegisterPart;
import org.jakstab.asm.x86.X86RetInstruction;
import org.jakstab.asm.x86.X86SegmentRegister;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import v2.org.analysis.apihandle.winapi.APIHandle;
import v2.org.analysis.cfg.BPCFG;
import v2.org.analysis.cfg.BPEdge;
import v2.org.analysis.cfg.BPVertex;
import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.memory.ContextRecord;
import v2.org.analysis.environment.memory.ExceptionRecord;
import v2.org.analysis.environment.processthread.TIB;
import v2.org.analysis.interactive.AnalysisModel;
import v2.org.analysis.interactive.IInteractive;
import v2.org.analysis.loop.LoopAlgorithm;
import v2.org.analysis.packer.PackerManager;
import v2.org.analysis.path.BPPath;
import v2.org.analysis.path.BPState;
import v2.org.analysis.structure.Convert;
import v2.org.analysis.system.VirtualMem;
import v2.org.analysis.transition_rule.stub.AssemblyInstructionStub;
import v2.org.analysis.value.Formula;
import v2.org.analysis.value.Formulas;
import v2.org.analysis.value.HybridBooleanValue;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.SolverResult;
import v2.org.analysis.value.SymbolValue;
import v2.org.analysis.value.Value;

/**
 * @author NMHai
 *
 */
public class X86TransitionRule extends TransitionRule {

	static long MAX_LOOP_MULTI = 20;
	private static HashMap<String, String> instructionMapping = new HashMap<>();
	private static HashMap<String, String> moveInstructionMapping = new HashMap<>();

	static {
		String directory = X86InstructionInterpreter.class.getPackage().getName().replace(".", "/");
		InputStream fXmlFile = null;
		try {
			fXmlFile = X86InstructionInterpreter.class.getResourceAsStream("/" + directory + "/X86AssemblyMap.xml");

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			if (doc.hasChildNodes()) {
				NodeList groupList = doc.getElementsByTagName("GROUP");

				for (int count = 0; count < groupList.getLength(); count++) {
					Node groupNode = groupList.item(count);
					String groupName = null;
					if (groupNode.getNodeType() == Node.ELEMENT_NODE && groupNode.hasAttributes()) {
						NamedNodeMap namedNodeMap = groupNode.getAttributes();
						groupName = namedNodeMap.getNamedItem("name").getNodeValue();
					}

					// make sure it's element node.
					if (groupNode.getNodeType() == Node.ELEMENT_NODE) {
						// get attributes names and values
						NodeList asmList = groupNode.getChildNodes();

						for (int i = 0; i < asmList.getLength(); i++) {
							Node apiNode = asmList.item(i);

							// make sure it's element node.
							if (apiNode.getNodeType() == Node.ELEMENT_NODE && apiNode.hasAttributes()) {
								// get attributes names and values
								NamedNodeMap apiMap = apiNode.getAttributes();
								if (groupName.equals("x86instruction")) {
									instructionMapping.put(apiMap.getNamedItem("assemblyName").getNodeValue(), apiMap
											.getNamedItem("className").getNodeValue());
								} else {
									moveInstructionMapping.put(apiMap.getNamedItem("assemblyName").getNodeValue(),
											apiMap.getNamedItem("className").getNodeValue());
								}
							}

						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fXmlFile != null) {
					fXmlFile.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static BPState currentState = null;
	private List<Pair<String, String>> pathCond = new ArrayList<>();
	
	class Pair<T, U> {         
	    private final T t;
	    private final U u;

	    public Pair(T t, U u) {         
	        this.t= t;
	        this.u= u;
	    }
	    
	    public T getT() {
	    	return t;
	    }
	    
	    public U getU() {
	    	return u;
	    }
	    
	    public boolean equal(Pair<T, U> p) {
	    	return t.equals(p.getT()) && u.equals(p.getU());
	    }
	 }
	
	private static String findClassName(Instruction asm) {
		if (asm == null) {
			return null;
		}

		String asmName = asm.getName().trim().toLowerCase();
		Map<String, String> map = null;

		if (asm instanceof X86MoveInstruction) {
			map = moveInstructionMapping;
		} else {
			map = instructionMapping;
		}

		String fullClassName = null;
		fullClassName = map.get(asmName);

		if (fullClassName == null) {
			char lastChar = asmName.charAt(asmName.length() - 1);

			if (lastChar == 'l' || lastChar == 's' || lastChar == 'w' || lastChar == 'b') {
				asmName = asmName.substring(0, asmName.length() - 1);
				fullClassName = map.get(asmName);
			}
		}

		return fullClassName;
	}

	private long loop_mullti = 0;
	private boolean setCFG = false;

	private List<String> checkedFormulasTrue = new ArrayList<>();

	private List<String> checkedFormulasFalse = new ArrayList<>();

	private SEHHandle sehHandle = new SEHHandle();

	// Check Address valid for modification
	public boolean checkAddressValid(Environment env, AbsoluteAddress addr) {
		if (!env.getSystem().getSEHHandler().isSet()) {
			return true;
		}

		if (addr.getValue() == 0) {
			return false;
		}

//		if (Program.getProgram().checkAddress(addr)) {
//			return true;
//		}
		if (env.getStack().isInsideStack(addr)) {
			return true;
		}

		if (env.getMemory().checkAddressValid(addr)) {
			return true;
		}

//		if (env.getSystem().getFileHandle().isInsideFile(addr)) {
//			return true;
//		}
//		
//		if (env.getSystem().getLibraryHandle().isInside(addr)) {
//			return true;
//		}
		return false;
	}

	public boolean checkAddressValid(Environment env, X86MemoryOperand d) {
		// TODO Auto-generated method stub

		if (d != null && d.getBase() != null && d.getBase() instanceof X86Register
				&& d.getBase().toString().contains("esp")) {
			return true;
		}

		X86MemoryOperand t = env.getMemory().evaluateAddress(d, env);

		if (t == null || t.getBase() != null || t.getSegmentRegister() != null || t.getIndex() != null) {
			return true;
		}

		AbsoluteAddress addr = new AbsoluteAddress(t.getDisplacement());
		return checkAddressValid(env, addr);
	}

	// PHONG: 20150502 --------------------------------------------------------
	boolean checkAddressValidJump(Environment env, long t) {
		// TODO Auto-generated method stub
		if (!env.getSystem().getSEHHandler().isSet()) {
			return true;
		}

		if (env.getSystem().getLibraryHandle().getAPIName(t) != "") {
			return true;
		}

		String temp = APIHandle.getAPINameFromAddress(t);
		if (temp != null && temp != "") {
			return true;
		}

		AbsoluteAddress addr = new AbsoluteAddress(t);

		return checkAddressValid(env, addr);
	}

	public String checkAPICall(Value r, BPState curState) {
		// YenNguyen: Check address in JNA memory
		String api = APIHandle.getAPINameFromAddress(((LongValue) r).getValue());
		// System.out.println();
		if (api == null || api == "") {
			api = curState.getEnvironement().getSystem().getLibraryHandle().getAPIName(((LongValue) r).getValue());
		}

		if (api == null || api == "") {
			api = Program.getProgram().checkAPI(((LongValue) r).getValue(), curState.getEnvironement());
			if (api != null && api.equals("")) {
				api = null;
			}
		}

		return api;
	}

	private void printFomula(Formulas condition) {
		System.err.println(" --- Condition");
		for (Formula cond : condition.getListFormula()) {
			cond.evaluate();
			System.err.println(" - " + cond.toString());
		}
	}

	SolverResult checkZ3(Formulas formulas, BPState curState) {
		//printFomula(formulas);
		// TODO Auto-generated method stub
		// System.out.println("Check feasibility of path with Z3");
		if (formulas.isEmpty()) {
			// Program.getProgram().getLog().info("Check Path Condition: SAT");
			return new SolverResult(true);
		}

		if (formulas.isBooleanValue()) {
			return new SolverResult(formulas.evaluate());
		}

		// Reuse the result of other running
		String prefix = formulas.toStringPrefix();
		if (checkedFormulasTrue.contains(prefix)) {
			return new SolverResult(true, true);
		}

		if (checkedFormulasFalse.contains(prefix)) {
			return new SolverResult(false, true);
		}

		// if ()
		try {
			// System.out.println();
			Program.getProgram().getLog().info("Running Z3 at " + curState.getLocation());
			this.writeZ3Input(System.getProperty("user.dir") + "/data/z3Input.smt", formulas);
			// Process p = Runtime.getRuntime().exec(
			// "cmd /c start /wait run_z3.bat");
			Process p = Runtime.getRuntime().exec(
					"cmd /c start /wait " + System.getProperty("user.dir") + "/data/run_z3.bat");
			/*
			 * Process p = Runtime.getRuntime().exec( "cmd /c start /wait " +
			 * System.getProperty("user.dir") + "/z3-4.3.0-x86/bin/z3.exe /smt "
			 * + System.getProperty("user.dir") + "/z3Input.smt > " +
			 * System.getProperty("user.dir") + "/z3Output.txt");
			 */
			int exitCode = p.waitFor();

			Program.getProgram().getLog().info("ExitCode:" + exitCode);
		} catch (Throwable e) {
			System.out.flush();
			e.printStackTrace();
			// Runtime.getRuntime().removeShutdownHook(shutdownThread);
			// Kills eclipse shutdown thread
			System.exit(1);
		}

		InputStream fis;
		BufferedReader br;
		boolean isSAT = false;
		String strPrefix = formulas.toStringPrefix();
		Map<String, Long> result = new TreeMap<>();
		try {
			String line = "";
			fis = new FileInputStream(System.getProperty("user.dir") + "/data/z3Output.txt");
			// fis = new FileInputStream("z3Output.txt");
			br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
			do {
				line = br.readLine();
				if (line == null || line.isEmpty()) {
					break;
				}
				if (line.equals("sat")) {
					isSAT = true;
				} else {
					String[] split = line.split("->");
					if (split.length == 2) {
						String var = split[0].trim();
						String value = split[1].trim().replace("#x", "");
						Long lngValue = Long.parseLong(value, 16);
						result.put(var, lngValue);
					}
				}
			} while (line != null && !line.isEmpty());
			br.close();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		// Done with the file
		// System.out.println(result.substring(8));
		if (isSAT) {
			Program.getProgram().getLog().info("Check Path Condition: SAT");
			checkedFormulasTrue.add(strPrefix);
		} else {
			checkedFormulasFalse.add(strPrefix);
			Program.getProgram().getLog().info("Check Path Condition: UNSAT");
		}
		br = null;
		fis = null;
		return new SolverResult(isSAT, result);
	}

	Map<String, Long> executeZ3(Formulas l) {
		// TODO Auto-generated method stub
		this.writeZ3Input(System.getProperty("user.dir") + "/data/z3Input.smt", l);
		try {
			// Process p = Runtime.getRuntime().exec(
			// "cmd /c start /wait run_z3.bat");
			Process p = Runtime.getRuntime().exec(
					"cmd /c start /wait " + System.getProperty("user.dir") + "/data/run_z3.bat");
			/*
			 * Process p = Runtime.getRuntime().exec( "cmd /c start /wait " +
			 * System.getProperty("user.dir") +
			 * "/z3-4.3.2-x86-win/bin/z3.exe /smt " +
			 * System.getProperty("user.dir") + "/z3Input.smt > " +
			 * System.getProperty("user.dir") + "/z3Output.txt");
			 */
			Program.getProgram().getLog().info("Running script Z3...");
			int exitCode = p.waitFor();

			Program.getProgram().getLog().info("Done script. ExitCode:" + exitCode);
		} catch (Throwable e) {
			System.out.flush();
			e.printStackTrace();
			// Runtime.getRuntime().removeShutdownHook(shutdownThread);
			// Kills eclipse shutdown thread
			System.exit(1);
		}
		// return this.readZ3Output(System.getProperty("user.dir") +
		// "/z3Output.txt");
		return this.readZ3Output(System.getProperty("user.dir") + "/data/z3Output.txt");
	}

	// Change the location and instruction for new state
	public void generateNextInstruction(Instruction ins, BPPath path, List<BPPath> pathList, boolean cond) {
		// TODO Auto-generated method stub
		BPState curState = path.getCurrentState();
		BPCFG cfg = Program.getProgram().getBPCFG();
		BPVertex src = cfg.getVertex(curState.getLocation(), ins);

		if (ins instanceof X86ArithmeticInstruction) {
			AbsoluteAddress newLocation = new AbsoluteAddress(curState.getLocation().getValue() + ins.getSize());

			Instruction newIns;
			// FOR ARITHMETIC HERE
			if (curState.getEnvironement().getSystem().isInVirtualMemory() == true) {
				byte[] opcodes = this.getOpcodesArray(curState, newLocation.getValue());
				newIns = Program.getProgram().getInstruction(opcodes, curState.getEnvironement());
			} else {
				newIns = Program.getProgram().getInstruction(newLocation, curState.getEnvironement());
			}

			curState.setInstruction(newIns);
			curState.setLocation(newLocation);
		} else if (ins instanceof X86CallInstruction) {
			;
		} else if (ins instanceof X86CondJmpInstruction) {
			// Dieu kien dung
			if (cond) {
				Environment env = curState.getEnvironement();
				Operand dest = ((X86CondJmpInstruction) ins).getOperand1();
				Value r = null;
				AbsoluteAddress targetTemp = curState.getLocation();

				if (dest.getClass().getSimpleName().equals("X86AbsoluteAddress")) {
					r = new LongValue(((AbsoluteAddress) dest).getValue());
				} else if (dest.getClass().getSimpleName().equals("X86PCRelativeAddress")) {
					r = new LongValue(((X86PCRelativeAddress) dest).getEffectiveValue(targetTemp.getValue()));
				} else if (dest.getClass().getSimpleName().equals("X86Register")) {
					r = env.getRegister().getRegisterValue(dest.toString());
				} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
					r = env.getMemory().getMemoryValue((X86MemoryOperand) dest, (X86Instruction) ins);
				}

				if (r != null && r instanceof LongValue) {
					AbsoluteAddress r1 = new AbsoluteAddress(((LongValue) r).getValue());
					Program.getProgram().getBPCFG().getVertex(curState.getLocation(), curState.getInstruction())
							.setProperty(r1.toString());

					String api = checkAPICall(r, curState);
					if (api != null/* !api.equals("") */) {
						APIHandle.executeAPI(new AbsoluteAddress(((LongValue) r).getValue()), api, ins, path, pathList);
						this.setCFG = true;
					} else {
						AbsoluteAddress nextAddr = new AbsoluteAddress(((LongValue) r).getValue());
						Instruction nextInst;
						// FOR CONDITIONAL JUMP HERE
						if (curState.getEnvironement().getSystem().isInVirtualMemory() == true) {
							byte[] opcodes = this.getOpcodesArray(curState, nextAddr.getValue());
							nextInst = Program.getProgram().getInstruction(opcodes, curState.getEnvironement());
						} else {
							nextInst = Program.getProgram().getInstruction(nextAddr, env);
						}

						curState.setInstruction(nextInst);
						curState.setLocation(nextAddr);
					}
				}
			} else {
				// Dieu kien sai
				AbsoluteAddress newLocation = new AbsoluteAddress(curState.getLocation().getValue() + ins.getSize());
				Instruction newIns;
				if (curState.getEnvironement().getSystem().isInVirtualMemory() == true) {
					byte[] opcodes = this.getOpcodesArray(curState, newLocation.getValue());
					newIns = Program.getProgram().getInstruction(opcodes, curState.getEnvironement());
				} else {
					newIns = Program.getProgram().getInstruction(newLocation, curState.getEnvironement());
				}

				curState.setInstruction(newIns);
				curState.setLocation(newLocation);
			}
		} else if (ins instanceof X86JmpInstruction) {
			;
		} else if (ins instanceof X86MoveInstruction) {
			AbsoluteAddress newLocation = new AbsoluteAddress(curState.getLocation().getValue() + ins.getSize());
			// FOR MOVE HERE
			Instruction newIns;
			if (curState.getEnvironement().getSystem().isInVirtualMemory() == true) {
				byte[] opcodes = this.getOpcodesArray(curState, newLocation.getValue());
				newIns = Program.getProgram().getInstruction(opcodes, curState.getEnvironement());
			} else {
				newIns = Program.getProgram().getInstruction(newLocation, curState.getEnvironement());
			}

			curState.setInstruction(newIns);
			curState.setLocation(newLocation);
		} else if (ins instanceof X86RetInstruction) {
			;
		} else if (ins instanceof X86Instruction) {
			AbsoluteAddress newLocation = new AbsoluteAddress(curState.getLocation().getValue() + ins.getSize());
			Instruction newIns;
			if (curState.getEnvironement().getSystem().isInVirtualMemory() == true) {
				byte[] opcodes = this.getOpcodesArray(curState, newLocation.getValue());
				newIns = Program.getProgram().getInstruction(opcodes, curState.getEnvironement());
			} else {
				newIns = Program.getProgram().getInstruction(newLocation, curState.getEnvironement());
			}

			curState.setInstruction(newIns);
			curState.setLocation(newLocation);
		}

		BPVertex dest = new BPVertex(curState.getLocation(), curState.getInstruction());
		dest = cfg.insertVertex(dest);
		BPEdge edge = new BPEdge(src, dest);
		cfg.insertEdge(edge);
	}

	public void generateNextInstructionForce(Instruction ins, BPPath path) {
		// TODO Auto-generated method stub
		BPState curState = path.getCurrentState();
		BPCFG cfg = Program.getProgram().getBPCFG();
		BPVertex src = cfg.getVertex(curState.getLocation(), ins);

		AbsoluteAddress newLocation = new AbsoluteAddress(curState.getLocation().getValue() + ins.getSize());
		Instruction newIns;
		if (curState.getEnvironement().getSystem().isInVirtualMemory() == true) {
			byte[] opcodes = this.getOpcodesArray(curState, newLocation.getValue());
			newIns = Program.getProgram().getInstruction(opcodes, curState.getEnvironement());
		} else {
			newIns = Program.getProgram().getInstruction(newLocation, curState.getEnvironement());
		}

		curState.setInstruction(newIns);
		curState.setLocation(newLocation);

		BPVertex dest = new BPVertex(curState.getLocation(), curState.getInstruction());
		dest = cfg.insertVertex(dest);
		BPEdge edge = new BPEdge(src, dest);
		cfg.insertEdge(edge);
	}
	
	public void generateNextInstructionForce(Instruction insSrc, long addr, BPPath path) {
		BPState curState = path.getCurrentState();
		BPCFG cfg = Program.getProgram().getBPCFG();
		AbsoluteAddress absAddr = new AbsoluteAddress(addr);
		Instruction instruction = Program.getProgram().getInstruction(absAddr);
		BPVertex src = cfg.getVertex(curState.getLocation(), insSrc);
		curState.setLocation(absAddr);
		curState.setInstruction(instruction);
		BPVertex dest = new BPVertex(curState.getLocation(), curState.getInstruction());
		dest = cfg.insertVertex(dest);
		if (curState.getLocation() == null || curState.getInstruction() == null) {
			dest.setType(BPVertex.UnknownNode);
			dest.setProperty("Unknown Node");
		}
		BPEdge edge = new BPEdge(src, dest);
		cfg.insertEdge(edge);
	}

	public int geSizeOprand(Operand op) {
		// TODO Auto-generated method stub
		if (op instanceof X86MemoryOperand) {
			return ((X86MemoryOperand) op).getDataType().bits();
		} else if (op instanceof X86RegisterPart) {
			return ((X86RegisterPart) op).getLength();
		} else if (op instanceof X86SegmentRegister) {
			return 32;
		} else if (op instanceof X86Register) {
			return 32;
		}

		return 0;
	}

	public static int getBitCount(Instruction ins) {
		// Yen Nguyen: Change from compare "endWith" to switch case the last
		// char
		char lastChar = ins.getName().charAt(ins.getName().length() - 1);

		switch (lastChar) {
			case 'b':
				return 8;
			case 's':
			case 'w':
				return 16;
			case 'l':
				return 32;
			default:
				break;
		}

		return 0;
	}

	private String getNameZ3Result(String line) {
		// TODO Auto-generated method stub
		String result[] = line.split(" ");
		return result[0];
	}

	@Override
	public void getNewState(BPPath path, List<BPPath> pathList, boolean cond) {
		// TODO Auto-generated method stub
		// Formulas l = path.getPathCondition();
		BPState curState = path.getCurrentState();
		BPCFG cfg = Program.getProgram().getBPCFG();
		Instruction ins = curState.getInstruction();
		BPVertex src = cfg.getVertex(curState.getLocation(), ins);

		String className = findClassName(ins);
		currentState = curState;

//		if (curState.getLocation().getValue() >= 0x401af4L && curState.getLocation().getValue() <= 0x401b0dL) {
//			System.out.println("BUSTED!!!");
//		}
		if (className != null) {
			try {
				Class<?> clazz = Class.forName(className);
				Constructor<?> ctor = clazz.getConstructor();
				AssemblyInstructionStub asmObject = (AssemblyInstructionStub) ctor.newInstance();

				asmObject.run((X86Instruction) ins, path, pathList, this);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (ins instanceof X86ArithmeticInstruction) {
			new X86ArithmeticInterpreter().execute((X86ArithmeticInstruction) ins, path, pathList, this);
		} else if (ins instanceof X86CallInstruction) {
			new X86CallInterpreter().execute((X86CallInstruction) ins, path, pathList, this);
		} else if (ins instanceof X86CondJmpInstruction) {
			new X86ConditionalJumpInterpreter().execute((X86CondJmpInstruction) ins, path, pathList, this);
		} else if (ins instanceof X86JmpInstruction) {
			new X86JumpInterpreter().execute((X86JmpInstruction) ins, path, pathList, this);
		} else if (ins instanceof X86MoveInstruction) {
			new X86MoveInterpreter().execute((X86MoveInstruction) ins, path, pathList, this);
		} else if (ins instanceof X86RetInstruction) {
			new X86ReturnInterpreter().execute((X86RetInstruction) ins, path, pathList, this);
		} else if (ins instanceof X86Instruction) {
			new X86InstructionInterpreter().execute((X86Instruction) ins, path, pathList, this);
		}

		if (!setCFG) {
			BPVertex dest = new BPVertex(curState.getLocation(), curState.getInstruction());
			dest = cfg.insertVertex(dest);
			if (curState.getLocation() == null || curState.getInstruction() == null) {
				dest.setType(BPVertex.UnknownNode);
				dest.setProperty("Unknown Node");
			}

			BPEdge edge = new BPEdge(src, dest);
			cfg.insertEdge(edge);
		} else {
			setCFG = false;
		}
		// return curState;
		if (path.getCurrentState().checkFeasiblePath()) {
			LoopAlgorithm.getInstance().halt(path, this);
		}
	}

	static int k = 0;
	public void getNewState(X86CondJmpInstruction inst, BPPath path, List<BPPath> pathList) {
		// TODO Auto-generated method stub
		Formulas p1 = path.getPathCondition().clone();
		Formulas p2 = path.getPathCondition().clone();

		// Special Case
		if (inst.getName().startsWith("loop")) {
			path.getCurrentState().getEnvironement().getRegister().sub("%ecx", new LongValue(1));
		}

		X86ConditionalJumpInterpreter condTrue = new X86ConditionalJumpInterpreter();
		X86ConditionalJumpInterpreter condFalse = new X86ConditionalJumpInterpreter();
		boolean condTrueRet = condTrue.execute(inst, p1, path, true, this);
		// TRUE branch
		if (!p1.isEmpty() && !p1.getListVariable().isEmpty()) {
			IInteractive analysis = AnalysisModel.getInstance().getAnalysis();
			if (analysis != null) {
				analysis.onSymbolicBranch(inst, p1, condTrue.getSolverResult());
			}
		}

		if (condTrueRet) {
			k++;
//			System.err.println("k=" + k);
//			if (k == 24) {
//				System.out.println("DEBUG");
//			}
			boolean condFalseRet = condFalse.execute(inst, p2, path, false, this);
			// FALSE branch
			if (!p2.isEmpty() && !p2.getListVariable().isEmpty()) {
				IInteractive analysis = AnalysisModel.getInstance().getAnalysis();
				if (analysis != null) {
					analysis.onSymbolicBranch(inst, p2, condFalse.getSolverResult());
				}
			}
			if (condFalseRet) {
				BPPath newP = path.clone();
				newP.setPathCondition(p2);

//				if (path.getLoopHandle().isCheck()
//				// && path.getLoopHandle().isStop()
//				) {
//					path.getLoopHandle().setStop(
//							newP.getLoopHandle().checkFormulas(inst.getName(), newP.getCurrentState()));
//				}
				this.generateNextInstruction(inst, newP, pathList, false);
				pathList.add(newP);
				newP.getCurrentState().setFeasiblePath(true);
				
				// Check later
//				if (!checkDuplicateCondition(newP)) {
//					System.out.println("Insert new Path: " + newP.getCurrentState().getLocation());
//					pathList.add(newP);
//					newP.getCurrentState().setFeasiblePath(true);
//					pathCond.add(new Pair(newP.getCurrentState().getLocation().toString(), newP.getPathCondition().toString()));
//				} else {
//					System.out.println("Does not insert new Path: " + newP.getCurrentState().getLocation());
//				}
//				LoopAlgorithm.getInstance().halt(newP, this);
			}

//			if (path.getLoopHandle().isCheck()) {
//				path.getLoopHandle()
//						.setStop(path.getLoopHandle().checkFormulas(inst.getName(), path.getCurrentState()));
//			}
			this.generateNextInstruction(inst, path, pathList, true);
			path.setPathCondition(p1);
			path.getCurrentState().setFeasiblePath(true);
//			LoopAlgorithm.getInstance().halt(path, this);
		} else if (new X86ConditionalJumpInterpreter().execute(inst, p2, path, false, this)) {
			path.setPathCondition(p2);
//				if (path.getLoopHandle().isCheck()
//				// && path.getLoopHandle().isStop()
//				) {
//					path.getLoopHandle().setStop(
//							path.getLoopHandle().checkFormulas(inst.getName(), path.getCurrentState()));
//				}

			this.generateNextInstruction(inst, path, pathList, false);
			path.getCurrentState().setFeasiblePath(true);
			
//				LoopAlgorithm.getInstance().halt(path, this);
		} else {
			path.getCurrentState().setFeasiblePath(false);
		}
		
		// Check later
//		if (checkDuplicateCondition(path)) {
//			System.out.println("Stop path " + path.getCurrentState().getLocation());
//			path.getCurrentState().setFeasiblePath(false);
//		} else {
//			pathCond.add(new Pair(path.getCurrentState().getLocation().toString(), path.getPathCondition().toString()));
//		}
	}
	
	// Remove the case when path have the same path condition repeating many times. It results to the count-to-infinity loop
	private boolean checkDuplicateCondition(BPPath path) {
		// TODO Auto-generated method stub
		for (Pair<String, String> p: pathCond) {
			if (p.getT().equals(path.getCurrentState().getLocation().toString()) && 
					p.getU().equals(path.getPathCondition().toString())) {				
				return true;
			}
		}
		
		return false;
	}

	// Check if newP is already in pathList
	private boolean contain(List<BPPath> pathList, BPPath newP) {
		// TODO Auto-generated method stub
		for (BPPath p : pathList) {
			if (p.getCurrentState().getLocation().getValue() == newP.getCurrentState().getLocation().getValue()) {
				return true;
			}
		}
		
		return false;
	}

	// Generate new Path Condition and check for feasibility with Z3
	public void getNewState(X86CondJmpInstruction inst, BPPath path, List<BPPath> pathList, boolean condition) {
		// TODO Auto-generated method stub
		new X86ConditionalJumpInterpreter().execute(inst, path, pathList, condition, this);
		if (path.getCurrentState().checkFeasiblePath()) {
			LoopAlgorithm.getInstance().halt(path, this);
		}
	}

	// PHONG: insert here
	// Need to improve here, process the opcodes[] for best performance
	public byte[] getOpcodesArray(BPState curState, long address) {
		VirtualMem vM = curState.getEnvironement().getSystem().getVirtualHandle().getCurrentVirtualMemory();
		vM.setAddress(address);
		// long offset = address - vM.getBaseAddress();
		byte[] opcodes = new byte[(int) vM.getSize()];
		// can modify here for best result, i < 10, because one asm statement
		// needs 10 bytes or less
		for (int i = 0; i < /* vM.getSize() - offset */ 10; i++) {
			long virtualAdrr = vM.getAddress() + i;
			opcodes[i] = (byte) ((LongValue) curState.getEnvironement().getMemory().getByteMemoryValue(virtualAdrr))
					.getValue();
		}
		return opcodes;
	}

	public SEHHandle getSEHHandle() {
		return this.sehHandle;
	}

	public boolean getSetCFG() {
		return this.setCFG;
	}

	public Value getValueOperand(Operand src, Environment env, Instruction ins) {
		Value s = null;
		if (src == null) {
			s = new LongValue(1);
		} else if (src.getClass().getSimpleName().equals("Immediate")) {
			long y = Convert.convetUnsignedValue(((Immediate) src).getNumber().intValue(), getBitCount(ins));
			s = new LongValue(y);
		} else if (src.getClass().getSimpleName().equals("X86Register")
				|| src.getClass().getSimpleName().equals("X86RegisterPart")
				|| src.getClass().getSimpleName().equals("X86SegmentRegister")
				|| src.getClass().getSimpleName().equals("X86MMXRegister")) {
			s = env.getRegister().getRegisterValue(src.toString());
		} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
			s = env.getMemory().getMemoryValue((X86MemoryOperand) src, ins);
		}

		return s;
	}

	private long getValueZ3(String line) {
		// TODO Auto-generated method stub
		long val = 0;
		String result[] = line.split(" ");
		String temp = "";

		if (result[0].contains("Flag")) {
			if (result[2].contains("false")) {
				val = 0;
			} else if (result[2].contains("true")) {
				val = 1;
			}
			return val;
		}

		if (result[2].startsWith("(")) {
			temp = result[3].substring(0, result[3].length() - 1);
			val = Convert.parseLongFromString(temp);
			// if (val > 100000) val = 0;
			if (result[2].substring(1).equals("-")) {
				val = -val;
			}
		} else if (result[2].contains("[")) {
			temp = result[2].substring(2, result[2].indexOf("["));
			val = Convert.parseLongFromString(temp);
			if (val >= Math.pow(2, 31)) {
				val = (long) (val - Math.pow(2, 32));
			}
		} else {
			temp = result[2].substring(2, result[2].length());
			val = Convert.parseLongFromString(temp);
			if (val >= Math.pow(2, 31)) {
				val = (long) (val - Math.pow(2, 32));
			}
		}
		return val;
	}

	// ---------------------------------------------------------------------------
	private long getValueZ3Result(String line) {
		// TODO Auto-generated method stub
		long val = 0;
		String result[] = line.split(" ");
		String temp = "";
		if (result[0].contains("Flag")) {
			if (result[2].contains("false")) {
				val = 0;
			} else if (result[2].contains("true")) {
				val = 1;
			}
			return val;
		}

		if (result[2].startsWith("(")) {
			temp = result[3].substring(0, result[3].length() - 1);
			val = Convert.parseLongFromString(temp);
			// if (val > 100000) val = 0;
			if (result[2].substring(1).equals("-")) {
				val = -val;
			}
		} else if (result[2].contains("[")) {
			temp = result[2].substring(2, result[2].indexOf("["));
			val = Convert.parseLongFromString(temp);
		} else {
			temp = result[2].substring(2, result[2].length());
			val = Convert.parseLongFromString(temp);
			// if (val >= Math.pow(2, 31))
			// val = (long) (val - Math.pow(2, 32));
		}
		return val;
	}

	/*
	 * private String reverseName(String name) { // TODO Auto-generated method
	 * stub String var = name; // %eax if (name.startsWith("op_addr_base_") &&
	 * !name.contains("disp")) var = "(%" + var + ")"; // 0x441023 else if
	 * (var.startsWith("op_addr_disp_")) var = var.substring(13); else if
	 * (var.startsWith("op_addr_base_disp")) { String temp[] = var.split("_");
	 * var = temp[4] + "(%" + temp[5] + ")"; } else if
	 * (var.startsWith("op_addr_base2_disp")) { String temp[] = var.split("_");
	 * var = temp[4] + "(%" + temp[5] + "," + temp[6] + ")"; } else if
	 * (var.startsWith("op_addr_base_index")) { String temp[] = var.split("_");
	 * var = "(%" + temp[4] + ",%" + temp[5] + ")"; } else if
	 * (var.startsWith("op_addr_base_index_disp_")) { String temp[] =
	 * var.split("_"); var = "%" + temp[5] + ":(%" + temp[6] + ")"; } else if
	 * (var.startsWith("op_addr_base_index_base_")) { String temp[] =
	 * var.split("_"); var = temp[5] + "(%" + temp[6] + "," + temp[7] + ")"; }
	 * else var = "%" + var;
	 * 
	 * return var; }
	 */
	private Map<String, Long> insertRelatedValue(Map<String, Long> list) {
		// TODO Auto-generated method stub
		Map<String, Long> clone = new HashMap<>();
		for (Map.Entry<String, Long> entry : list.entrySet()) {
			String t = entry.getKey();
			long v = entry.getValue();
			clone.put(t, v);
			if (t.equals("eax")) {
				long x = v % 65536;
				clone.put("ax", x);
				clone.put("ah", x / 256);
				clone.put("al", x % 256);
			} else if (t.equals("ebx")) {
				long x = v % 65536;
				clone.put("bx", x);
				clone.put("bh", x / 256);
				clone.put("bl", x % 256);
			} else if (t.equals("ecx")) {
				long x = v % 65536;
				clone.put("cx", x);
				clone.put("ch", x / 256);
				clone.put("cl", x % 256);
			} else if (t.equals("edx")) {
				long x = v % 65536;
				clone.put("dx", x);
				clone.put("dh", x / 256);
				clone.put("dl", x % 256);
			} else if (t.equals("esi")) {
				long x = v % 65536;
				clone.put("si", x);
			} else if (t.equals("edi")) {
				long x = v % 65536;
				clone.put("di", x);
			} else if (t.equals("esp")) {
				long x = v % 65536;
				clone.put("sp", x);
			} else if (t.equals("ebp")) {
				long x = v % 65536;
				clone.put("bp", x);
			}
		}
		return clone;
	}

	// New version: Add (not dest1) in new Formula
	void multiDestination(Value r1, long value, Instruction inst, BPPath path, List<BPPath> pathList) {
		// TODO Auto-generated method stub
		if (loop_mullti > MAX_LOOP_MULTI) {
			return;
		} else {
			loop_mullti++;
		}

		BPPath p = path.clone();
		Formulas l = p.getPathCondition();

		Value l1 = new HybridBooleanValue(r1, new LongValue(value), "==");

		l.add(new Formula(l1, "not"));
		if (!this.checkZ3(l, path.getCurrentState()).isSAT()) {
			return;
		}

		pathList.add(p);
		// Program.getProgram().generageCFG(Program.getProgram().getAbsolutePathFile()
		// + "_test");
	}

	// Hai: Process the problem of multi destination of SAT Solver
	// Old implementation
	private void multiDestination_Old(Map<String, Long> z3Value, X86JmpInstruction inst, BPPath path,
			List<BPPath> pathList) {
		// TODO Auto-generated method stub

		// Program.getProgram().generageCFG(Program.getProgram().getAbsolutePathFile()
		// + "_test");
		if (loop_mullti > MAX_LOOP_MULTI) {
			return;
		} else {
			loop_mullti++;
		}

		BPPath p = path.clone();
		Formulas l = p.getPathCondition();
		HybridBooleanValue l2 = null;

		for (Entry<String, Long> i : z3Value.entrySet()) {
			String var = i.getKey();
			long value = i.getValue();

			Value l1 = new HybridBooleanValue(new SymbolValue(var), new LongValue(value), "==");
			if (l2 != null) {
				l2 = new HybridBooleanValue(l1, l2, "and");
			} else {
				l2 = (HybridBooleanValue) l1;
				// p.getPathCondition().add(new Formula());
			}
		}

		l.add(new Formula(l2, "not"));
		if (!this.checkZ3(l, path.getCurrentState()).isSAT()) {
			return;
		}

		pathList.add(p);

		Program.getProgram().generageCFG(Program.getProgram().getAbsolutePathFile() + "_test");
	}

	// -----------------------------------------------------------------------------------------
	// PHONG - 20150422
	public BPState processSEH(BPState curState) {
//		Program.getProgram().setTechnique("SEH");
//		Program.getProgram().setDetailTechnique("SEH:" + curState.getLocation() + " ");
		System.out.println("Process SEH at:" + curState.getLocation());
		PackerManager.getInstance().setSEHTechnique(curState.getLocation().getValue());
		AbsoluteAddress addr = new AbsoluteAddress(curState.getEnvironement().getSystem().getSEHHandler().getStart()
				.getSehHandler());
		Instruction inst = Program.getProgram().getInstruction(addr, curState.getEnvironement());

		// Context changing
		// ---------------------------------------------------------------
		long err_ptr = curState.getEnvironement().getSystem().getSEHHandler().getStart().getAddrSEHRecord();

		// Step 1: Set up Context Record for SEH and Push it first to stack
		ContextRecord context_record = new ContextRecord();
		context_record.setContextRecord(curState);
		context_record.toStack(curState);
		long context_record_ptr = context_record.getContext_record_ptr();

		// Step 2: Set up Exception Record for SEH and push it to stack
		ExceptionRecord exception_record = new ExceptionRecord();
		exception_record.setExceptionRecord(curState);
		exception_record.toStack(curState);
		long exception_record_ptr = exception_record.getException_record_ptr();

		// Push the return value for system
		curState.getEnvironement().getStack().push(new LongValue(err_ptr));
		curState.getEnvironement().getStack().push(new LongValue(0x7C));
		curState.getEnvironement().getStack().push(new LongValue(err_ptr));
		this.setSystemSEH(curState);
		curState.getEnvironement().getStack().push(new LongValue(context_record_ptr));
		curState.getEnvironement().getStack().push(new LongValue(err_ptr));
		curState.getEnvironement().getStack().push(new LongValue(exception_record_ptr));
		curState.getEnvironement().getStack().push(new LongValue(0x7C9032A8));

		// Set up value for register
		// EAX = EBX = 0
		// ECX = address of next address
		curState.getEnvironement().getRegister().setRegisterValue("eax", new LongValue(0));
		curState.getEnvironement().getRegister().setRegisterValue("ebx", new LongValue(0));
		curState.getEnvironement().getRegister().setRegisterValue("ecx", new LongValue(addr.getValue()));
		// Set False for SEH
		curState.getEnvironement().getSystem().getSEHHandler().setSEHReady(false);
		TIB.setBeUpdated(true);
		// ---------------------------------------------------------------------------------

		BPCFG cfg = Program.getProgram().getBPCFG();
		BPVertex v1 = cfg.getVertex(curState.getLocation(), curState.getInstruction());
		BPVertex v2 = new BPVertex(addr, inst);
		v2 = cfg.insertVertex(v2);
		cfg.insertEdge(new BPEdge(v1, v2));
		curState.setLocation(addr);
		curState.setInstruction(inst);
		this.setCFG = true;

		return curState;
	}

	private Map<String, Long> readZ3Output(String filePath) {
		// TODO Auto-generated method stub
		InputStream fis;
		BufferedReader br;
		String line;
		try {
			fis = new FileInputStream(filePath);
			br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
			line = br.readLine();

			if (line != null && line.equals("sat")) {
				Program.getProgram().getLog().info("SAT Result:");
				Map<String, Long> result = new HashMap<>();
				while ((line = br.readLine()) != null) {
					String name = getNameZ3Result(line);
					long val = getValueZ3Result(line);
					// name = reverseName(name);
					result.put(name, val);
					Program.getProgram().getLog().info(name + " --> " + val + " = " + getValueZ3(line));
				}
				br.close();
				/*
				 * if (sr != null) { //EnvWeight ew = new EnvWeight();
				 * //ew.setAddress(targetIndirect);
				 * //ew.setInstruction(assemblyMap.get(targetIndirect));
				 * 
				 * Environment e = new Environment(symbolValueRegister.clone(),
				 * symbolValueRegisterPart.clone(),
				 * symbolValueMemoryOperand.clone(), symbolValueSegment.clone(),
				 * symbolFlag.clone(), symbolStack.clone(), program, system);
				 * 
				 * Environment e = new Environment(symbolValueRegister,
				 * symbolValueRegisterPart, symbolValueMemoryOperand,
				 * symbolValueSegment, symbolFlag, symbolStack, program,
				 * system);
				 * 
				 * //sr.getEnvWeight().addWeight(e); }
				 */
				return insertRelatedValue(result);
			} else {
				System.out.println("UNSAT");
			}
			br.close();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		// Done with the file
		// System.out.println(result.substring(8));

		br = null;
		fis = null;
		return null;
	}

	public void setCFG(boolean b) {
		this.setCFG = b;
	}

	public void setSEH(BPState curState) {
		// TODO Auto-generated method stub
		Program.getProgram().setTechnique("SetUpException");
		Program.getProgram().setDetailTechnique("SetUpException:" + curState.getLocation() + " ");
		System.out.println("Set Up Exception: " + curState.getLocation());
		Environment env = curState.getEnvironement();
		Value stack0 = env.getStack().getValueStackFromIndex(0);
		Value esp = env.getRegister().getRegisterValue("esp");
		Value stack4 = env.getStack().getValueStackFromIndex(4);

		if (stack0 != null && stack0 instanceof LongValue
				&& stack4 != null && stack4 instanceof LongValue
				&& esp != null && esp instanceof LongValue) {
			env.getSystem()
					.getSEHHandler()
					.getStart()
					.setNextSEHRecord(((LongValue) env.getStack().getValueStackFromIndex(0)).getValue(),
							((LongValue) env.getRegister().getRegisterValue("esp")).getValue());
			env.getSystem()
					.getSEHHandler()
					.getStart()
					.setSEHHandler(((LongValue) env.getStack().getValueStackFromIndex(4)).getValue(),
							((LongValue) env.getRegister().getRegisterValue("esp")).getValue() + 4);
			env.getSystem().getSEHHandler().setSEHReady(true);
		}
		TIB.setBeUpdated(true);
	}

	// PHONG: 20150501
	// ------------------------------------------------------------------
	public void setSEHOther(BPState curState, String register) {
		// TODO Auto-generated method stub
		Program.getProgram().setTechnique("SetUpException");
		Program.getProgram().setDetailTechnique("SetUpException:" + curState.getLocation() + " ");
		System.out.println("Set Up Other Exception: " + curState.getLocation());
		Environment env = curState.getEnvironement();
		Value reg = env.getRegister().getRegisterValue(register);
		long register_value = 0;
		if (reg != null && reg instanceof LongValue) {
			register_value = ((LongValue) env.getRegister().getRegisterValue(register)).getValue();
		}

		env.getSystem()
				.getSEHHandler()
				.getStart()
				.setNextSEHRecord(((LongValue) env.getMemory().getDoubleWordMemoryValue(register_value)).getValue(),
						register_value);
		env.getSystem()
				.getSEHHandler()
				.getStart()
				.setSEHHandler(((LongValue) env.getMemory().getDoubleWordMemoryValue(register_value + 4)).getValue(),
						register_value + 4);
		env.getSystem().getSEHHandler().setSEHReady(true);
		TIB.setBeUpdated(true);
	}

	/*
	 * public BPState processSEH(BPState curState) { // TODO Auto-generated
	 * method stub Program.getProgram().setTechnique("SEH");
	 * Program.getProgram().setDetailTechnique( "SEH:" + curState.getLocation()
	 * + " "); AbsoluteAddress addr = new
	 * AbsoluteAddress(curState.getEnvironement()
	 * .getSystem().getSEHHandler().getStart().getSehHandler()); Instruction
	 * inst = Program.getProgram().getInstruction(addr,
	 * curState.getEnvironement());
	 * 
	 * //ICFEM: Set SEH false
	 * curState.getEnvironement().getSystem().getSEHHandler
	 * ().setSEHReady(false);
	 * 
	 * // Set up Stack Configuration for SEH Value e = new
	 * LongValue(curState.getEnvironement().getSystem()
	 * .getSEHHandler().getStart().getAddrSEHRecord());
	 * curState.getEnvironement().getStack() .push(new LongValue((long)
	 * (Math.random() * Math.pow(10, 7))));
	 * curState.getEnvironement().getStack() .push(new LongValue((long)
	 * (Math.random() * Math.pow(10, 7))));
	 * 
	 * curState.getEnvironement().getStack() .push(new LongValue((long)
	 * (Math.random() * Math.pow(10, 7)))); // Address Code
	 * curState.getEnvironement().getStack() .push(new
	 * LongValue(curState.getLocation().getValue()));
	 * 
	 * curState.getEnvironement().getStack().push(new LongValue(0));
	 * curState.getEnvironement().getStack().push(new LongValue(0));
	 * curState.getEnvironement().getStack() .push(new LongValue((long)
	 * (Math.random() * Math.pow(10, 7)))); Value esp =
	 * curState.getEnvironement().getRegister() .getRegisterValue("esp");
	 * 
	 * curState.getEnvironement().getStack().push(e);
	 * curState.getEnvironement().getStack().push(esp);
	 * curState.getEnvironement().getStack().push(new LongValue(2089824936));
	 * 
	 * // Set up Registers for SEH curState.getEnvironement().getRegister()
	 * .setRegisterValue("eax", new LongValue(0));
	 * curState.getEnvironement().getRegister() .setRegisterValue("ebx", new
	 * LongValue(0)); curState.getEnvironement().getRegister()
	 * .setRegisterValue("ecx", new LongValue(addr.getValue()));
	 * 
	 * BPCFG cfg = Program.getProgram().getBPCFG(); BPVertex v1 =
	 * cfg.getVertex(curState.getLocation(), curState.getInstruction()); //
	 * v1.setProperty(getFullName(funcName)); BPVertex v2 = new BPVertex(addr,
	 * inst); // v2.setAddress(address); v2 = cfg.insertVertex(v2);
	 * cfg.insertEdge(new BPEdge(v1, v2)); curState.setLocation(addr);
	 * curState.setInstruction(inst); this.setCFG = true;
	 * 
	 * return curState; }
	 */
	// PHONG: 20150105 -------------------------------------------------------
	void setSystemSEH(BPState curState) {
		// TODO Auto-generated method stub
		// Program.getProgram().setTechnique("SetUpException");
		// Program.getProgram().setDetailTechnique(
		// "SetUpException:" + curState.getLocation() + " ");
		System.out.println("Set Up System Exception: " + curState.getLocation());
		Environment env = curState.getEnvironement();

		env.getSystem()
				.getSEHHandler()
				.getStart()
				.setNextSEHRecord(((LongValue) env.getStack().getValueStackFromIndex(0)).getValue(),
						((LongValue) env.getRegister().getRegisterValue("esp")).getValue());
		env.getSystem()
				.getSEHHandler()
				.getStart()
				.setSEHHandler(((LongValue) env.getStack().getValueStackFromIndex(4)).getValue(),
						((LongValue) env.getRegister().getRegisterValue("esp")).getValue() + 4);
		// env.getSystem().getSEHHandler().setSEHReady(true);
		TIB.setBeUpdated(true);
	}

	public void setValueOperand(Operand src, Value val, Environment env, Instruction inst) {
		if (src == null) {
			return;
		} else if (src.getClass().getSimpleName().equals("X86Register")
				|| src.getClass().getSimpleName().equals("X86RegisterPart")
				|| src.getClass().getSimpleName().equals("X86SegmentRegister")
				|| src.getClass().getSimpleName().equals("X86MMXRegister")) {
			env.getRegister().setRegisterValue(src.toString(), val);
		} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
			env.getMemory().setMemoryValue((X86MemoryOperand) src, val, inst);
		}

	}

	/*
	 * private BPState specialCase(Instruction inst, BPState curState, Formulas
	 * l) { // TODO Auto-generated method stub if (inst instanceof
	 * X86RetInstruction) { if
	 * (Program.getProgram().getFileName().equals("hostname.exe") &&
	 * curState.getLocation().toString().contains("010013ac")) { AbsoluteAddress
	 * addr = new AbsoluteAddress(16781795); Instruction ins =
	 * Program.getProgram().getInstruction(addr, curState.getEnvironement());
	 * 
	 * curState.setInstruction(ins); curState.setLocation(addr);
	 * 
	 * return curState; } else if (Program.getProgram().getFileName()
	 * .equals("hostname.exe") &&
	 * curState.getLocation().toString().contains("01001472")) { AbsoluteAddress
	 * addr = new AbsoluteAddress(16781691); Instruction ins =
	 * Program.getProgram().getInstruction(addr, curState.getEnvironement());
	 * 
	 * curState.setInstruction(ins); curState.setLocation(addr);
	 * 
	 * return curState; } }
	 * 
	 * return null; }
	 */
 /*
	 * BPState specialProcessSEH(BPState curState) { // TODO Auto-generated
	 * method stub // PHONG Environment env = curState.getEnvironement();
	 * env.getRegister().mov("ebp", "esp"); env.getRegister().add("ebp", new
	 * LongValue(20)); long ebp_addr = ((LongValue)
	 * env.getRegister().getRegisterValue("ebp")) .getValue(); long
	 * exception_took_place = ((LongValue) env.getMemory()
	 * .getDoubleWordMemoryValue(ebp_addr)).getValue();
	 * 
	 * AbsoluteAddress nextAddr = new AbsoluteAddress(exception_took_place);
	 * Instruction nextIns = Program.getProgram() .getInstruction(nextAddr,
	 * env);
	 * 
	 * // Restore stack long seh_address =
	 * curState.getEnvironement().getSystem()
	 * .getSEHHandler().getStart().getSehHandler();
	 * 
	 * // Stack pointer while (true) { long top_stack_address = ((LongValue)
	 * env.getRegister() .getRegisterValue("esp")).getValue(); Value t =
	 * env.getMemory().getDoubleWordMemoryValue( top_stack_address);
	 * 
	 * if (t instanceof LongValue) { long top_stack_value = ((LongValue)
	 * t).getValue(); env.getStack().pop(); if (seh_address == top_stack_value
	 * || env.getStack().isEmpty()) break; }
	 * 
	 * if (env.getStack().isEmpty()) break; env.getRegister().add("esp", new
	 * LongValue(4)); }
	 * 
	 * curState.setLocation(nextAddr); curState.setInstruction(nextIns);
	 * 
	 * return curState; }
	 */
	// PHONG - 20150422
	public BPState specialProcessSEH(BPState curState) {
		// Restore
		PackerManager.getInstance().setHardwareBreakpointTechnique(curState.getLocation().getValue());

		long context_record_ptr = ((LongValue) curState.getEnvironement().getStack().getValueStackFromIndex(0x8))
				.getValue();
		curState.getEnvironement().getRegister().mov("esp", context_record_ptr);
		Value dr0_value = curState.getEnvironement().getStack().getValueStackFromIndex(0x4);
		Value dr1_value = curState.getEnvironement().getStack().getValueStackFromIndex(0x8);
		Value dr2_value = curState.getEnvironement().getStack().getValueStackFromIndex(0xc);
		Value dr3_value = curState.getEnvironement().getStack().getValueStackFromIndex(0x10);
		Value dr4_value = curState.getEnvironement().getStack().getValueStackFromIndex(0x14);
		Value dr5_value = curState.getEnvironement().getStack().getValueStackFromIndex(0x18);
		Value dr6_value = curState.getEnvironement().getStack().getValueStackFromIndex(0x1c);
		Value dr7_value = curState.getEnvironement().getStack().getValueStackFromIndex(0x20);

		Value edi_value = curState.getEnvironement().getStack().getValueStackFromIndex(0x9c);
		Value esi_value = curState.getEnvironement().getStack().getValueStackFromIndex(0xa0);
		Value ebx_value = curState.getEnvironement().getStack().getValueStackFromIndex(0xa4);
		Value edx_value = curState.getEnvironement().getStack().getValueStackFromIndex(0xa8);
		Value ecx_value = curState.getEnvironement().getStack().getValueStackFromIndex(0xac);
		Value eax_value = curState.getEnvironement().getStack().getValueStackFromIndex(0xb0);
		Value ebp_value = curState.getEnvironement().getStack().getValueStackFromIndex(0xb4);
		// New location
		Value eip_value = curState.getEnvironement().getStack().getValueStackFromIndex(0xb8);
		Value efl_value = curState.getEnvironement().getStack().getValueStackFromIndex(0xc0);

		if (efl_value != null && efl_value instanceof LongValue) {
			curState.getEnvironement().getFlag().setflags(((LongValue) efl_value).getValue());
		}

		Value esp_value = curState.getEnvironement().getStack().getValueStackFromIndex(0xc4);

		curState.getEnvironement().getRegister().mov("edi", edi_value);
		curState.getEnvironement().getRegister().mov("esi", esi_value);
		curState.getEnvironement().getRegister().mov("ebx", ebx_value);
		curState.getEnvironement().getRegister().mov("edx", edx_value);
		curState.getEnvironement().getRegister().mov("ecx", ecx_value);
		curState.getEnvironement().getRegister().mov("eax", eax_value);
		curState.getEnvironement().getRegister().mov("ebp", ebp_value);
		curState.getEnvironement().getRegister().mov("esp", esp_value);

		curState.getEnvironement().getRegister().mov("dr0", dr0_value);
		curState.getEnvironement().getRegister().mov("dr1", dr1_value);
		curState.getEnvironement().getRegister().mov("dr2", dr2_value);
		curState.getEnvironement().getRegister().mov("dr3", dr3_value);
		curState.getEnvironement().getRegister().mov("dr4", dr4_value);
		curState.getEnvironement().getRegister().mov("dr5", dr5_value);
		curState.getEnvironement().getRegister().mov("dr6", dr6_value);
		curState.getEnvironement().getRegister().mov("dr7", dr7_value);

		// Restore System SEH
		this.setSEH(curState);
		curState.getEnvironement().getSystem().getSEHHandler().setSEHReady(false);
		this.sehHandle.setExceptionAddr(curState.getEnvironement().getRegister().getRegisterValue("dr0"));
		this.sehHandle.setExceptionAddr(curState.getEnvironement().getRegister().getRegisterValue("dr1"));
		this.sehHandle.setExceptionAddr(curState.getEnvironement().getRegister().getRegisterValue("dr2"));
		this.sehHandle.setExceptionAddr(curState.getEnvironement().getRegister().getRegisterValue("dr3"));
		TIB.setBeUpdated(true);

		AbsoluteAddress nextAddr = new AbsoluteAddress(0x00000000);
		if (eip_value != null && eip_value instanceof LongValue) {
			nextAddr = new AbsoluteAddress(((LongValue) eip_value).getValue());
		}
		Instruction nextIns = Program.getProgram().getInstruction(nextAddr, curState.getEnvironement());
		curState.setLocation(nextAddr);
		curState.setInstruction(nextIns);

		return curState;
	}

	private void writeZ3Input(String filePath, Formulas formulaList) {
		FileWriter writer;
		// System.out.println("Z3 Input File: ");
		try {
			// writer = new FileWriter(fileName, "UTF-8");
			/*
			 * File f = new File(fileName); if (!f.exists()) return;
			 */
			writer = new FileWriter(new File(filePath));

			try {
				writer.write("(benchmark program\n");
				writer.write(" :status sat\n");
				writer.write(" :logic QF_LIA\n");
				writer.write(":extrafuns (" + formulaList.getVariableZ3() + ")\n");
				Program.getProgram().getLog().info(":extrafuns (" + formulaList.getVariableZ3() + ")");
				// Program.getProgram().getLog().info(":formula" +
				// formulaList.toStringPrefix());
				if (formulaList.isEmpty()) {
					writer.write(":formula (true)\n");
					Program.getProgram().getLog().info(":formula (true)");
				} else {
					writer.write(":formula" + formulaList.toStringPrefix() + "\n");
					Program.getProgram().getLog().info(":formula" + formulaList.toStringPrefix());
				}
				writer.write(")\n");
				Program.getProgram().getLog().info(")");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// System.out.println("Close Stream");
				writer.close();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
