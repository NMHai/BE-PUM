package v2.org.analysis.transition_rule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.Operand;
import org.jakstab.asm.x86.X86CallInstruction;
import org.jakstab.asm.x86.X86MemoryOperand;
import org.jakstab.asm.x86.X86PCRelativeAddress;

import v2.org.analysis.apihandle.winapi.APIHandle;
import v2.org.analysis.environment.Environment;
import v2.org.analysis.packer.PackerManager;
import v2.org.analysis.path.BPPath;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.Formulas;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.SymbolValue;
import v2.org.analysis.value.Value;

public class X86CallInterpreter {
	public BPState execute(X86CallInstruction inst, BPPath path, List<BPPath> pathList, X86TransitionRule rule) {
		BPState curState = path.getCurrentState();
		Formulas l = path.getPathCondition();
		Environment env = curState.getEnvironement();
		Operand dest = inst.getOperand1();
		Value r = null;
		AbsoluteAddress targetTemp = curState.getLocation();
		AbsoluteAddress returnAddress = new AbsoluteAddress(targetTemp.getValue() + inst.getSize());

		// if (this.targetTemp.getValueOperand() == 0)
		// System.out.println("Debug");
		// System.out.println("Instruction: " + inst.getName());

		// call structure: push call next eip to stack, jump to call address
		// first operand
		if (dest.getClass().getSimpleName().equals("X86AbsoluteAddress")) {
			r = new LongValue(((AbsoluteAddress) dest).getValue());
		} else if (dest.getClass().getSimpleName().equals("X86PCRelativeAddress")) {
			r = new LongValue(((X86PCRelativeAddress) dest).getEffectiveValue(targetTemp.getValue()));
		} else if (dest.getClass().getSimpleName().equals("X86Register")) {
			// Program.getProgram().setTechnique("Indirect Jump");
			// Program.getProgram().setDetailTechnique("Indirect Jump:" +
			// curState.getLocation() + " ");
			/*
			 * Hai: Packer Detection To detect the indirect jump techniques
			 */
			PackerManager.getInstance().setIndirectJumpTechnique(curState.getLocation().getValue());
			r = env.getRegister().getRegisterValue(dest.toString());
		} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
			if (((X86MemoryOperand) dest).getBase() != null) {
				/*
				 * Hai: Packer Detection To detect the indirect jump techniques
				 */
				PackerManager.getInstance().setIndirectJumpTechnique(curState.getLocation().getValue());
			}
			r = env.getMemory().getMemoryValue((X86MemoryOperand) dest, inst);
		}

		if (r != null && r instanceof LongValue) {
			AbsoluteAddress r1 = new AbsoluteAddress(((LongValue) r).getValue());
			Program.getProgram().getBPCFG().getVertex(curState.getLocation(), curState.getInstruction())
					.setProperty(r1.toString());

			String api = rule.checkAPICall(r, curState);
			// String t[] = api.split("@");
			if (api != null/* !api.equals("") */) {
				APIHandle.executeAPI(new AbsoluteAddress(((LongValue) r).getValue()), api, inst, path, pathList);
				rule.setCFG(true);
			} else {
				// env.getRegister().sub("esp", new LongValue(4));
				env.getStack().push(new LongValue(returnAddress.getValue()));

				AbsoluteAddress nextAddr = new AbsoluteAddress(((LongValue) r).getValue());
				Instruction nextInst = null;
				// PHONG: at address 0x00330144, change to getInstruction(byte[]
				// code) because Jackstab can not release instruction for
				// Virtual Section
				// Have to generalize this address, we just only use this for
				// debug at sometime
				if (nextAddr != null && nextAddr.getValue() != 0) {
					if (nextAddr.getValue() == 0x00330144
							|| curState.getEnvironement().getSystem().isInVirtualMemory() == true) {
						// SET IN VIRTUAL MEMORY HERE
						curState.getEnvironement().getSystem().setInVirtualMemory(true);
						byte[] opcodes = rule.getOpcodesArray(curState, nextAddr.getValue());
						// NEXT INSTRUCTION FOR CALL
						nextInst = Program.getProgram().getInstruction(opcodes, env);
					} else {
						nextInst = Program.getProgram().getInstruction(nextAddr, env);
					}
					
					PackerManager.getInstance().setOverlappingFuction("call", curState.getLocation().getValue(),
							nextAddr.getValue());
				}

				/*
				 * if (nextInst == null) { nextInst =
				 * Program.getProgram().getInstruction( returnAddress, env);
				 * curState.setInstruction(nextInst);
				 * curState.setLocation(returnAddress);
				 * 
				 * return curState; }
				 */
				/*
				 * Hai: Packer Detection To detect the overlapping function
				 * techniques
				 */
				curState.setInstruction(nextInst);
				curState.setLocation(nextAddr);
			}
		} else {
			// Apply Concolic Testing for solving the Indirect Jump
			// Program.getProgram().setTechnique("Indirect Jump");
			// Program.getProgram().setDetailTechnique("Indirect Jump:" +
			// curState.getLocation() + " ");
			/*
			 * Hai: Packer Detection To detect Indirect Jump Technique
			 */
			PackerManager.getInstance().setIndirectJumpTechnique(curState.getLocation().getValue());
			System.out.println("Apply Concolic Testing for solving the Indirect Jump at "
					+ curState.getLocation().toString());
			Map<String, Long> z3Value = new HashMap<String, Long>();

			z3Value = rule.executeZ3(l);

			if (z3Value != null) {
				Value r1 = r.clone();
				r = r.evaluate(z3Value);

				if (!(r instanceof LongValue)) {
					Program.getProgram().getLog()
							.debugString("Fail to resolve indirect jump at " + curState.getLocation());
					curState.setInstruction(null);
					curState.setLocation(null);
					return curState;
				}

				AbsoluteAddress r2 = new AbsoluteAddress(((LongValue) r).getValue());
				Program.getProgram().getBPCFG().getVertex(curState.getLocation(), curState.getInstruction())
						.setProperty(r2.toString());

				String api = rule.checkAPICall(r, curState);
				// String t[] = api.split("@");
				if (!api.equals("")) {
					APIHandle.executeAPI(new AbsoluteAddress(((LongValue) r).getValue()), api, inst, path, pathList);
					rule.setCFG(true);
				} else {
					if (r instanceof LongValue) {
						env.getStack().push(new LongValue(returnAddress.getValue()));
						// Hai: Process the problem of multi destination of SAT
						// Solver
						rule.multiDestination(r1, ((LongValue) r).getValue(), inst, path, pathList);
						// ************************************************************

						AbsoluteAddress a = new AbsoluteAddress(((LongValue) r).getValue());
						Program.getProgram().getBPCFG().getVertex(curState.getLocation(), curState.getInstruction())
								.setProperty(a.toString());
						Instruction i = Program.getProgram().getInstruction(a, env);

						if (i != null) {
							System.out.println("The new area of Concolic Testing is:" + a.getValue() + " Hex value:"
									+ a + " Instruction: " + i.getName());
						} else {
							System.out.println("The new area of Concolic Testing is:" + a.getValue() + " Hex value:"
									+ a + " Instruction: null");
						}
						System.out.println("**********************************************");

						/*
						 * Hai: Packer Detection To detect the overlapping
						 * function techniques
						 */
						PackerManager.getInstance().setOverlappingFuction("call", curState.getLocation().getValue(),
								a.getValue());

						curState.setInstruction(i);
						curState.setLocation(a);

						curState.resetValue(z3Value);
						path.clearPathCondition();
					} else {
						if (r instanceof SymbolValue) {
							if (((SymbolValue) r).getVarName().equals("eax")) {
								System.out.println("Jump to Host! Replace by Entry Point");
								curState.setLocation(Program.getProgram().getEntryPoint());
								curState.setInstruction(Program.getProgram().getInstruction(
										Program.getProgram().getEntryPoint(), env));

								return curState;
							}
						}

						System.out.println("Cannot resolve the Indirect Jump with Concolic Testing at:"
								+ curState.getLocation());
						System.out.println("**********************************************");
						curState.setInstruction(null);
						curState.setLocation(null);
					}
				}
			}
		}

		return curState;
	}
}
