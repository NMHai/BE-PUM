/**
 * 
 */
package v2.org.analysis.algorithm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jakstab.Algorithm;
import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;
/**
 * @author NMHai
 * 
 * The main algorithm of On-the-fly Model Generation
 * 
 */
import org.jakstab.asm.x86.X86CondJmpInstruction;

import v2.org.analysis.algorithm.OTFThreadManager.OTFThreadBase;
import v2.org.analysis.cfg.BPCFG;
import v2.org.analysis.cfg.BPVertex;
import v2.org.analysis.deeplearning.AdjMatrixManager;
import v2.org.analysis.deeplearning.DLState;
import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.processthread.TIB;
import v2.org.analysis.interactive.IInteractive;
import v2.org.analysis.json.JSONManager;
import v2.org.analysis.olly.OllyComparisonV2;
import v2.org.analysis.packer.PackerManager;
import v2.org.analysis.path.BPPath;
import v2.org.analysis.path.BPState;
import v2.org.analysis.path.PathList;
import v2.org.analysis.statistics.FileProcess;
import v2.org.analysis.transition_rule.X86TransitionRule;
import v2.org.analysis.value.Formulas;
import v2.org.analysis.value.Value;

public class OTFModelGeneration implements Algorithm {
	private static final int DEFAULT_BK_TIME = 180000;
	private static final int MAX_NUMBER_ADDB = 50;
	private static final int MAX_TIME_OUT = 144000000;
	private final Program program;
	private long overallStartTime;

	// For compare with OllyDbg
	// private static final String OEP_PATH_ADDR = "data/data/OEPAddr.txt";
	// private boolean getOEP = true;
	private long count = 1;
	private boolean isCompareOlly = false, isExport = false, isChecked = false, isContinued = true, isExtractOEP = false;
	private int num = 1, loopCount = 1, memStart = -1, 
			memEnd = -1, numStack = -1, timeOut = 0;
	private long position = -1;
	private FileProcess out = null;
	public OTFThread _curThread = null;

	public OTFModelGeneration(Program program) {
		super();
		this.program = program;
	}

	@Override
	public void run() {
		overallStartTime = System.currentTimeMillis();
		// BE-PUM algorithm
		System.out.println("Starting On-the-fly Model Generation algorithm.");
		program.getResultFileTemp().appendInLine('\n' + program.getFileName() + '\t');

		// Set up initial context
		// X86TransitionRule rule = new X86TransitionRule();
		BPCFG cfg = Program.getProgram().getBPCFG();
		Environment env = new Environment();
		env.getMemory().resetImportTable(program);
		AbsoluteAddress location = Program.getProgram().getEntryPoint();

		Instruction inst = Program.getProgram().getInstruction(location, env);
		List<BPPath> pathList = new ArrayList<>();
		if (isExtractOEP) {
			program.getOEPFile().appendFile(program.getFileName() + " " + location.toString());
			return;
		}		

		// Insert start node
		BPVertex startNode = null;
		startNode = new BPVertex(location, inst);
		startNode.setType(0);
		cfg.insertVertex(startNode);

		BPState curState = null;
		BPPath path = null;
		curState = new BPState(env, location, inst);
		path = new BPPath(curState, new PathList(), new Formulas());
		path.setCurrentState(curState);
		pathList.add(path);

		// Update at first -----------------------------
		TIB.setBeUpdated(true);
		TIB.updateTIB(curState);
		
		// Set current date
		program.setStartdate();
		
		// Update JSON file
		JSONManager.getInstance().importData(program);		
//		JSONManager.getInstance().exportData(program.getFileName()); // testcase
		
		// Deep learning
//		AdjMatrixManager.getInstance().setExportImg(true);
		
		if (PackerManager.getInstance().isDetectBinaryOnly()) {
			PackerManager.getInstance().getHeaderDetection(Program.getProgram().getFileName());
			return;
		}
//		OTFThreadManager.getInstance().setMultiThread(true);
//		PackerManager.getInstance().setDetectSemanticOnly(true);
		synchronized (OTFThreadManager.getInstance()) {
			try {
				OTFThreadManager.getInstance().setOTFModelGeneration(this);
				OTFThreadManager.getInstance().check(this, pathList);
				OTFThreadManager.getInstance().wait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public class OTFThread extends OTFThreadBase {
		X86TransitionRule rule = new X86TransitionRule();
		List<BPPath> pathList = new ArrayList<>();
		BPPath path = null;
		BPState curState = null;
		Instruction inst = null;
		AbsoluteAddress location = null;
		int numAddStop = 0;
		IInteractive analysis = null;

		public OTFThread(BPPath bpPath) {
			this.pathList.add(bpPath);
			this.path = bpPath;
			this.curState = path.getCurrentState();
			this.inst = this.curState.getInstruction();
			this.location = this.curState.getLocation();
		}

		public void setInteractive(IInteractive analysis) {
			this.analysis = analysis;
			// curState.getEnvironement().getMemory().setAnalysis(analysis);
			analysis._init(program, rule, path, curState);
		}

		public void addPath(BPPath path) {
			pathList.add(path);
		}
		
		@Override
		public void execute() {
			long overallStartTemp = overallStartTime;
			PackerManager packerManager = PackerManager.getInstance();			
//			isCompareOlly = true;
			DLState oldState = null;
			
			while (!pathList.isEmpty() && isContinued) {
//				path = pathList.remove(pathList.size() - 1);
				path = pathList.remove(0);
				curState = path.getCurrentState();
				int numADDB = 0;
				// long overallStartTimePath = System.currentTimeMillis();
				while (isContinued) {
					if (!curState.checkFeasiblePath()) {
						path.destroy();
						break;
					}

					long overallCurTimeTemp = System.currentTimeMillis();
					// Output file each 60s
					if (overallCurTimeTemp - overallStartTemp > DEFAULT_BK_TIME) {
						backupState(curState);
						overallStartTemp = overallCurTimeTemp;
					}
					if (overallCurTimeTemp - overallStartTime > timeOut) {
						path.destroy();
						isContinued = false;
						System.out.println("Stop program after " + timeOut/1000 + " seconds");
					}

					if (path.isStop()) {
						path.destroy();
						break;
					}
					// //////////////////////////////// OTF Packer Detection////////////////////////////
					packerManager.updateChecking(curState, program);
					// /////////////////////////////////////////////////////////////////////////////////

					inst = curState.getInstruction();
					location = curState.getLocation();					
																					
					if (inst != null && inst.getName().contains("addb") && inst.getOperand(0) != null
							&& inst.getOperand(0).toString().contains("eax") && inst.getOperand(1) != null
							&& inst.getOperand(1).toString().contains("al")) {
						if (numADDB > MAX_NUMBER_ADDB) {
							program.getStopFile().appendFile(program.getFileName());
							break;
						}

						numADDB++;
					}

//					compareOlly(curState);
					exportLog(curState);
					// --------------------------------------
					TIB.updateTIB(curState);
					// --------------------------------------

					if (inst == null || location == null) {
						path.destroy();
						break;
					}
					
					if (AdjMatrixManager.getInstance().isExportImg()) {
						oldState = new DLState(curState.getLocation(), curState.getInstruction(), curState.getEnvironement().getRegister().clone(),
								curState.getEnvironement().getFlag().clone());
					}
					
					path.addTrace(curState.getLocation());

					if (analysis != null) {
						IInteractive.ACTION_OP op = analysis.onInstruction(path, location.getValue(), inst);
						if (op == IInteractive.ACTION_OP.PASS) {
							continue;
						}
					}

					if (inst instanceof X86CondJmpInstruction) {
						rule.getNewState((X86CondJmpInstruction) inst, path, pathList);
						if (!curState.checkFeasiblePath()) {
							path.destroy();
							break;
						}
					} else {
						rule.getNewState(path, pathList, true);
					}
					
					
					if (packerManager.isExtractFrequency()) {
						if (isOEP(curState.getLocation(), program.getFileName())) {
							packerManager.outputFinalResult(program.getFileName(), curState.getLocation().toString(), "OEP Detected");
							path.destroy();
							return;
						}
					} else if (packerManager.isDetectPacker() && packerManager.isPacked()) {
//					 || isOEP(curState.getLocation(), fileName))
//					 || pManager.isNearOEP(curState.getInstruction()))					
						packerManager.outputFinalResult(program.getFileName(), curState.getLocation().toString(), "Packer Detected");						
						packerManager.setDetectPacker(false);
						
						if (packerManager.isDetectSemanticOnly()) {
							path.destroy();
							return;
						}						
					}

					// /////// AFTER LOOP ///////////
					this.afterLoop(OTFModelGeneration.this, pathList);
					if (this.isStopCurrentPath(curState)) {
						path.destroy();
						break;
					}
					
					// For deep learning processing
					AdjMatrixManager.getInstance().updateMatrix(oldState, curState);
//					curState.getEnvironement().getMemory().emptyChangedList();
					curState.getEnvironement().getMemory().resetDiff();
					///////////////////////////////
				}
			}
		}
		
		private int loop = 0;
		private void exportLog(BPState state) {
			// TODO Auto-generated method stub
			if (isExport) {
				if (out == null) {
					out = new FileProcess("data/data/out_" + program.getFileName() + ".txt");
					out.clearContentFile();
				}
				
				if (state.getLocation() == null || (position != -1 && state.getLocation().getValue() != position)) {
					return;
				}
				
				String ret = "<eip = " + state.getLocation() + ", loop = " + Long.toHexString(++loop) + ">\n";
				ret += "[register]: " + "eip = " + state.getEnvironement().getRegister().getRegisterValue("eip") + 
						", eax = " + state.getEnvironement().getRegister().getRegisterValue("eax") + 
						", ecx = " + state.getEnvironement().getRegister().getRegisterValue("ecx") + 
						", edx = " + state.getEnvironement().getRegister().getRegisterValue("edx") + 
						", ebx = " + state.getEnvironement().getRegister().getRegisterValue("ebx") + 
						", esp = " + state.getEnvironement().getRegister().getRegisterValue("esp") + 
						", ebp = " + state.getEnvironement().getRegister().getRegisterValue("ebp") + 
						", esi = " + state.getEnvironement().getRegister().getRegisterValue("esi") + 
						", edi = " + state.getEnvironement().getRegister().getRegisterValue("edi") + "\n";
				ret += "[flag]: " + "cf = " + state.getEnvironement().getFlag().getCFlag() +
						", pf = " + state.getEnvironement().getFlag().getPFlag() + 
						", af = " + state.getEnvironement().getFlag().getAFlag() +
						", zf = " + state.getEnvironement().getFlag().getZFlag() +
						", sf = " + state.getEnvironement().getFlag().getSFlag() +
						", df = " + state.getEnvironement().getFlag().getDFlag() +
						", of = " + state.getEnvironement().getFlag().getOFlag() + "\n";
				ret += "[memory]: ";
				for (int i = memStart; i <= memEnd; i += 4) {
					ret += state.getEnvironement().getMemory().getDoubleWordMemoryValue(i) + " ";
				}
				ret += "\n" + "[stack]: ";
				for (int i=0; i<numStack; i += 4) {
					ret += state.getEnvironement().getStack().getValueStackFromIndex(i) + " ";
				}
				ret += "\n";
				out.appendFile(ret);
			}
		}
		
		

		private String output(Instruction inst) {
			// TODO Auto-generated method stub
			String ret = "";
			if (inst != null) {
				ret += inst.getName() + " ";
				for (int i = 0; i < inst.getOperandCount(); i++) {
					ret += inst.getOperand(i) + ", ";
				}
			}
			return ret;
		}

		private List<String> readFromFile(String path) {
			// TODO Auto-generated method stub
			List<String> result = new ArrayList<>();
			try {
				// clearContentFile(unprocessedFile);
				BufferedReader br = new BufferedReader(new FileReader(path));
				// StringBuilder sb = new StringBuilder();
				String line = br.readLine();
				while (line != null) {
					result.add(line);
					line = br.readLine();
				}
				br.close();
			} catch (Exception e) {
				System.out.println(e.toString());
			}

			return result;
		}

		private void debugProg(BPState curState2, List<String> locList) {
			// TODO Auto-generated method stub
			AbsoluteAddress loc = curState.getLocation();
			if (locList.contains(loc.toString())) {
				System.out.print("Debug at " + loc.toString() + ": ");
				System.out.println(curState.getEnvironement().getRegister().toString() + ", "
						+ curState.getEnvironement().getFlag().toString());
			}
		}

		private Value oldMem = null;

		private void debugProg(BPState curState, long memAddr) {
			// TODO Auto-generated method stub
			AbsoluteAddress loc = curState.getLocation();
			Value curMem = curState.getEnvironement().getMemory().getDoubleWordMemoryValue(memAddr);
			if (oldMem == null || (oldMem != null && curMem != null && !oldMem.toString().contains(curMem.toString()))) {
				System.out.print("Debug at " + loc.toString() + ": ");
				System.out.println(curMem.toString() + " " + curState.getEnvironement().getRegister().toString() + " "
						+ curState.getEnvironement().getFlag().toString() + " ");
				oldMem = curMem;
			}
		}

		private String getNode(AbsoluteAddress loc, Instruction inst) {
			// TODO Auto-generated method stub
			String ret = loc.toString() + ": " + inst.getName() + " ";
			int num = inst.getOperandCount();
			for (int i = 0; i < num; i++) {
				ret += inst.getOperand(i) + ", ";
			}

			return ret;
		}

		private boolean b = false;
		private static final int MAX_NUM = 1;

		private void debugProg(BPState curState, AbsoluteAddress loc) {
			// TODO Auto-generated method stub
			if (b) {
				System.out.print(loc.toString() + " ");
				System.out.println(curState.getEnvironement().getRegister().toString() + " "
						+ curState.getEnvironement().getFlag().toString() + " ");
			}
		}
	}

	private Map<String, String> oep = null;
//	private static String OEP_PATH = "data/data/OEPAddr.txt";

	private boolean isOEP(AbsoluteAddress location, String fileName) {
		// TODO Auto-generated method stub
		if (oep == null) {
			oep = Program.getProgram().readOEP();
		}

		for (Map.Entry<String, String> entry : oep.entrySet()) {
			if ((fileName.contains(entry.getKey()) || entry.getKey().contains(fileName))
					&& location != null
					&& (location.toString().contains(entry.getValue()) || entry.getValue()
							.contains(location.toString()))) {
				return true;
			}
		}

		return false;		
	}	

	private void backupState(BPState curState) {
		// TODO Auto-generated method stub
		program.generageCFG("/asm/cfg/" + program.getFileName() + "_test");
		program.getResultFileTemp().appendFile(
				program.getFileName() + "\t" + Program.getProgram().getHashFile() + "\t" + String.format("%8dms", (System.currentTimeMillis() - overallStartTime))
						+ "\t" + String.format("%8d", program.getBPCFG().getVertexCount()) + "\t"
						+ String.format("%8d", program.getBPCFG().getEdgeCount())+ "\t" + String.format("%b", PackerManager.getInstance().isDetectPacker())
						+ "\t" + String.format("%b", OTFThreadManager.getInstance().isMultiThread()) 
						+ "\t" + String.format("%8d", OTFThreadManager.getInstance().getSizeThreadBuffer())
						+ "\t" + String.format("%8d", OTFThreadManager.getInstance().getNumConflict())
						+ "\t" + String.format("%8d", OTFThreadManager.getInstance().getTotalWaitingTime()));
		// Export JSON file
		JSONManager.getInstance().exportData(program.getFileName());
		// Export Image File
		AdjMatrixManager.getInstance().exportImage(program.getFileName(), AdjMatrixManager.MAX_SIZE);
//		AdjMatrixManager.getInstance().exportImage(program.getFileName());
		// //////////////////////////////////////////////////
		// Write to packer result file after each 60s
		if (PackerManager.getInstance().isDetectPacker()) {
			PackerManager.getInstance().outputToFile(program.getFileName());
		}		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
	}

	public boolean isCompleted() {
		// TODO Auto-generated method stub
		return program.getBPCFG().isCompleted();
	}

	public boolean isSound() {
		// TODO Auto-generated method stub
		return true;
	}

	private OllyComparisonV2 ollyCompare = null;
	private AbsoluteAddress checkedAddr = new AbsoluteAddress(0);
	private AbsoluteAddress endAddr = new AbsoluteAddress(0);
	private FileProcess compareOllyResult = null;

	private void compareOlly(BPState state) {
		// TODO Auto-generated method stub
		// -------------------------------------------------------------------
		// OLLY DEBUG HERE
		// checkedAddr = new AbsoluteAddress(0x40818C);
		// endAddr = new AbsoluteAddress(0x4085B2);
		if (isCompareOlly) {
			AbsoluteAddress location = state.getLocation();
			Environment env = state.getEnvironement();
			String fileName = "out_" + Program.getProgram().getFileName();
			if (ollyCompare == null) {
				System.out.println("Read file Olly " + "data/data/" + fileName + ".txt");
				ollyCompare = new OllyComparisonV2("data/data/" + fileName + ".txt", memStart, memEnd,
						numStack);
				// ollyCompare = new OllyCompare("asm/olly/" + fileName +
				// ".txt", memoryStartAddr,
				// memoryEndAddr, stackIndex);
				ollyCompare.importOllyData(checkedAddr, endAddr);
				count = ollyCompare.getFirstCount();
				System.out.println("Finish reading!");
			}

			if (compareOllyResult == null) {
				compareOllyResult = new FileProcess("data/data/compareWithOlly_" + fileName + "" + num + ".txt");
				compareOllyResult.clearContentFile();
			}

			if (location != null && location.getValue() == checkedAddr.getValue()) {
				isChecked = true;
			}

			if (isChecked & location != null && !ollyCompare.isFinished()) {
				compareOllyResult.appendFile("Loop = " + Long.toHexString(count) + " , Address = "
						+ location.toString() + ":");
				// COMPARE HERE
				String result = ollyCompare.compareBEPUM(env, count, location);
				count = ollyCompare.getNextCheck();
				// if (result.contains("Unequal")) {
				// System.out.println("Bug");
				// }
				compareOllyResult.appendFile(result);
				// System.out.println("*************************************************************");
				compareOllyResult.appendFile("*************************************************************");
				loopCount++;
			}

			if (isChecked && location != null && ollyCompare.isFinished()) {
				// && location.getValue() == endAddr.getValue()
				// && loopCount == ollyCompare.getLoopCount()) {
				System.out.println("Stop Check: " + fileName + "" + num + ".txt");
				ollyCompare = null;
				loopCount = 1;
				compareOllyResult = null;
				num++;
				isChecked = false;
				count = 1;

				if (num >= 2) {
					isCompareOlly = false;
					System.out.println("Finish Checking");
				}
			}
		}
	}

	public void isCompareOlly(boolean b, int memStart, int memEnd, int numStack) {
		// TODO Auto-generated method stub
		isCompareOlly = b;
		this.numStack = numStack;
		this.memEnd = memEnd;
		this.memStart = memStart;
	}

	public void setTimeOut(int timeOut) {
		// TODO Auto-generated method stub
		this.timeOut = ((timeOut == 0 || timeOut < MAX_TIME_OUT)  ? MAX_TIME_OUT : timeOut);
	}

	public int getTimeOut() {
		return this.timeOut;
	}

	public void isExport(boolean b, long pos, int memStart, int memEnd, int numStack) {
		// TODO Auto-generated method stub
		isExport = b;
		this.position = pos; 
		this.numStack = numStack;
		this.memEnd = memEnd;
		this.memStart = memStart;
	}

	public void setExtractOEP(boolean b) {
		// TODO Auto-generated method stub
		isExtractOEP = b;
	}
}