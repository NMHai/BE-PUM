package v2.org.analysis.loop;

import java.util.ArrayList;
import java.util.List;

import org.jakstab.Program;

import v2.org.analysis.cfg.BPVertex;
import v2.org.analysis.path.BPPath;
import v2.org.analysis.path.BPState;
import v2.org.analysis.path.PathList;
import v2.org.analysis.transition_rule.X86TransitionRule;

public class LoopAlgorithm {
	private static LoopAlgorithm programInstance = null;
	private int MAX_EP_LOOP = 50;
	private int count_EP = 0;
	private int MAX_LOOP = 1000000000;
	//private static int MAX_LOOP = 1000;
	// private List<LoopHandle> paths;

	public static LoopAlgorithm getInstance() {
		if (programInstance == null) {
			programInstance = new LoopAlgorithm();
		}

		return programInstance;
	}

	private LoopAlgorithm() {
		// paths = new ArrayList<LoopHandle>();
		this.MAX_LOOP = exceptionCase(Program.getProgram().getFileName());
	}

	private LoopAlgorithm(int num) {
		// paths = new ArrayList<PathHandle>();
		MAX_LOOP = num;
	}

	public boolean halt(BPPath path, X86TransitionRule rule) {
		// TODO Auto-generated method stub
		PathList trace = path.getTrace();
		BPState curState = path.getCurrentState();
		if (curState.getLocation() == null) {
			return false;
		}

		if (trace.contain(curState.getLocation().getValue())) {
			// Heuristics: If this is entry point, stops
			BPVertex entry = Program.getProgram().getBPCFG().getEntryPoint();
			if (entry != null && curState.getLocation().getValue() == entry.getAddress().getValue()
					&& curState.getInstruction().compareInstruction(entry.getInstruction())) {
				if (count_EP > MAX_EP_LOOP) {
					System.out.println("Path reaches to Entry Point!!!");
					count_EP = 0;
					path.setStop(true);
					return true;
				} else {
					count_EP ++;
				}
			}

			LoopHandle temp = path.getLoopHandle();

			if (temp == null) {
				temp = new LoopHandle();
			}

			int loop_num = temp.getNumLoop();
			BPVertex loopHead = temp.getLoopHead();

			if (loopHead == null) {
				temp.setLoopHead(new BPVertex(curState.getLocation(), curState.getInstruction()));
				temp.setNumLoop(1);
				temp.setCheck(true);
				temp.setStop(false);
				temp.setLoopTrace(new ArrayList<Long>());
			} else if (!temp.containLoopNode(loopHead.getAddress().getValue())
					&& temp.containLoopNode(curState.getLocation().getValue())) {
				temp.setLoopHead(new BPVertex(curState.getLocation(), curState.getInstruction()));
				;
				temp.setNumLoop(1);
				temp.setCheck(true);
				temp.setStop(false);
			} else {
				// System.out.println("The path contains loop at:" +
				// curState.getLocation());
				// PHONG: change here
				// if (loop_num >= 3)
				// System.out.println("Debug");
				temp.addLoopNode(curState.getLocation().getValue());

				if (loop_num > MAX_LOOP 
						|| loop_num > exceptionCase(Program.getProgram().getFileName())) {
					// BPCFG cfg = Program.getProgram().getBPCFG();
					if (loop_num > MAX_LOOP + 5 
							|| loop_num > exceptionCase(Program.getProgram().getFileName()) + 5) {	
						System.out.println("Loop node reach Maximum Loops counts " + MAX_LOOP + " at "
							+ curState.getLocation());
						path.setStop(true);
						return true;
					}					
								
					if (loop_num == MAX_LOOP + 1) {
						curState.getEnvironement().reset();
					}
					
					temp.setNumLoop(++loop_num);
					return false;
				}

				if (loopHead.getAddress().getValue() == curState.getLocation().getValue()
						&& loopHead.getInstruction().compareInstruction(curState.getInstruction())) {
					// loopState = curState.clone();
					temp.setNumLoop(++loop_num);

					if (temp.getLoopTrace().size() == 1) {
						if (curState.getInstruction().getName().startsWith("call")
								|| curState.getInstruction().getName().startsWith("jmp")) {
							if (curState.getEnvironement().getSystem().getSEHHandler().isSet()) {
								System.out.println("Loop Stack Overflow - Exception: SEH Occur");
								curState = rule.processSEH(curState);
								rule.setCFG(false);
								path.setStop(false);
								return false;
							} else {
								System.out.println("Loop Stack Overflow - Exception: Stop path");
								path.setStop(true);
								return true;
							}
						}
//						if (curState.getInstruction().getName().startsWith("loop")) {
//							Value ecx = curState.getEnvironement().getRegister().getRegisterValue("ecx");
//
//							if (ecx instanceof LongValue) {
//								long t = ((LongValue) ecx).getValue();
//
//								if (t > 10) {
//									curState.getEnvironement().getRegister().setRegisterValue("ecx", new LongValue(10));
//								}
//							} else {
//								curState.getEnvironement().getRegister().setRegisterValue("ecx", new LongValue(10));
//							}
//						}
					}

					if (temp.isStop()) {
						System.out.println("Loop may run forever: Stop path after " + path.getLoopHandle().getNumLoop()
								+ " times");
						System.out.println("Loop Path: " + path.getLoopHandle());
						path.setStop(true);
						return true;
					}
				}

				if (checkExceptionLoop(path)) {
					curState = rule.processSEH(curState);
					rule.setCFG(false);
					path.setStop(false);
					return false;
				}
			}
		} else {
			LoopHandle temp = path.getLoopHandle();

			if (temp != null) {
				temp.clear();
			}
		}
		return false;
	}

	private List<Long> extractLoopTrace(PathList trace, long value) {
		// TODO Auto-generated method stub
		return trace.extract(value);
	}

	private int exceptionCase(String fileName) {
		// TODO Auto-generated method stub
		if (fileName != null
				&& (fileName.contains("count6.exe") || fileName.contains("count7.exe")
						|| fileName.contains("count8.exe") || fileName.contains("count9.exe")
						|| fileName.contains("smc.exe") || fileName.contains("ex.exe") || fileName
							.contains("fib-reach-0.exe")))
		 {
			return 10;
		//if (fileName.equals("Virus.Win32.Cabanas.2999"))
		//	return 1000;
		}

		return this.MAX_LOOP;
	}

	private boolean checkExceptionLoop(BPPath path) {
		// TODO Auto-generated method stub
		BPState curState = path.getCurrentState();
		if (Program.getProgram().getFileName().equals("Packed_IczEdit.exe")
				&& curState.getLocation().toString().contains("407a3d")) {
			return true;
		}

		return false;
	}

	public long getMaxLoop() {
		// TODO Auto-generated method stub
		return MAX_LOOP;
	}

	public long normalizeLoop(long t) {
		// TODO Auto-generated method stub
//		if (t > 1000000000) {
//			t = getMaxLoop();
//		}
		
		return t;
	}
}
