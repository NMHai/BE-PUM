package v2.org.analysis.transition_rule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.Operand;
import org.jakstab.asm.x86.X86JmpInstruction;
import org.jakstab.asm.x86.X86MemoryOperand;
import org.jakstab.asm.x86.X86PCRelativeAddress;
//import v2.org.analysis.apihandle.APIHandle;

import v2.org.analysis.apihandle.winapi.APIHandle;
import v2.org.analysis.environment.Environment;
import v2.org.analysis.packer.PackerManager;
import v2.org.analysis.path.BPPath;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.Formulas;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.SymbolValue;
import v2.org.analysis.value.Value;

public class X86JumpInterpreter {
//	private APIHandle apiHandle = null;
	public BPState execute(X86JmpInstruction inst, BPPath path, List<BPPath> pathList, X86TransitionRule rule) {
		// TODO Auto-generated method stub
		Formulas l = path.getPathCondition();
		BPState curState = path.getCurrentState();
		Environment env = curState.getEnvironement();
		Operand dest = inst.getOperand1();
		Value r = null;
		AbsoluteAddress targetTemp = curState.getLocation();

		// call structure: push call next eip to stack, jump to call address
		// first operand
		if (dest.getClass().getSimpleName().equals("X86AbsoluteAddress")) {
			r = new LongValue(((AbsoluteAddress) dest).getValue());
		} else if (dest.getClass().getSimpleName().equals("X86PCRelativeAddress")) {
			r = new LongValue(((X86PCRelativeAddress) dest).getEffectiveValue(targetTemp.getValue()));

		} else if (dest.getClass().getSimpleName().equals("X86Register")) {
//			Program.getProgram().setTechnique("Indirect Jump");
//			Program.getProgram().setDetailTechnique("Indirect Jump:" + curState.getLocation() + "\t");
			PackerManager.getInstance().setIndirectJumpTechnique(curState.getLocation().getValue());
			r = env.getRegister().getRegisterValue(dest.toString());
		} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
			if (((X86MemoryOperand) dest).getBase() != null) {
//				Program.getProgram().setTechnique("Indirect Jump");
//				Program.getProgram().setDetailTechnique("Indirect Jump:" + curState.getLocation() + "\t");
				PackerManager.getInstance().setIndirectJumpTechnique(curState.getLocation().getValue());
			}
			r = env.getMemory().getMemoryValue((X86MemoryOperand) dest, inst);
		}

		// PHONG: 20150502 -----------------------------------------------------
		if (r != null && r instanceof LongValue && !rule.checkAddressValidJump(env, ((LongValue) r).getValue())) {
			return rule.processSEH(curState);
		}
		// -----------------------------------------------------------------------

		if (r != null && r instanceof LongValue) {
			AbsoluteAddress r1 = new AbsoluteAddress(((LongValue) r).getValue());
			Program.getProgram().getBPCFG().getVertex(curState.getLocation(), curState.getInstruction())
					.setProperty(r1.toString());

			String api = rule.checkAPICall(r, curState);
			//System.out.println();
			if (api != null/* !api.equals("") */) {
				APIHandle.executeAPI(new AbsoluteAddress(((LongValue) r).getValue()), api, inst, path, pathList);
				rule.setCFG(true);
			} else {
				AbsoluteAddress nextAddr = new AbsoluteAddress(((LongValue) r).getValue());

				// PHONG: insert here for virtual memory
				Instruction nextInst;
				if (curState.getEnvironement().getSystem().isInVirtualMemory() == true) {
					Program.getProgram().setLog(Program.getProgram().getFileName() + " Is in virtual memory at " + curState.getLocation());
					byte[] opcodes = rule.getOpcodesArray(curState, nextAddr.getValue());
					nextInst = Program.getProgram().getInstruction(opcodes, env);
				} else {
					nextInst = Program.getProgram().getInstruction(nextAddr, env);
				}				
				/*
				 * Hai: Packer Detection
				 * To detect the overlapping block techniques
				*/
//				PackerManager.getInstance().setOverlappingFuction("jmp", curState.getLocation().getValue(), nextAddr.getValue());
//				PackerManager.getInstance().setCodeChunking("jmp", curState.getLocation().getValue(), nextAddr.getValue());
				curState.setInstruction(nextInst);
				curState.setLocation(nextAddr);
			}
		} else {
			// Apply Concolic Testing for solving the Indirect Jump
//			Program.getProgram().setTechnique("Indirect Jump");
//			Program.getProgram().setDetailTechnique("Indirect Jump:" + curState.getLocation() + "\t");
			PackerManager.getInstance().setIndirectJumpTechnique(curState.getLocation().getValue());
			System.out.println("Apply Concolic Testing for solving the Indirect Jump at "
					+ curState.getLocation().toString());
			Map<String, Long> z3Value = new HashMap<String, Long>();

			z3Value = rule.executeZ3(l);

			if (z3Value != null) {
				Value r1 = r.clone();
				r = r.evaluate(z3Value);

				if (r instanceof LongValue) {
					// Hai: Process the problem of multi destination of SAT
					// Solver
					rule.multiDestination(r1, ((LongValue) r).getValue(), inst, path, pathList);
					// ************************************************************

					AbsoluteAddress a = new AbsoluteAddress(((LongValue) r).getValue());
					Program.getProgram().getBPCFG().getVertex(curState.getLocation(), curState.getInstruction())
							.setProperty(a.toString());
					Instruction i = Program.getProgram().getInstruction(a, env);

					if (i != null) {
						System.out.println("The new area of Concolic Testing is:" + a.getValue() + " Hex value:" + a
								+ " Instruction: " + i.getName());
					} else {
						System.out.println("The new area of Concolic Testing is:" + a.getValue() + " Hex value:" + a
								+ " Instruction: null");
					}
					System.out.println("**********************************************");
					
					/*
					 * Hai: Packer Detection
					 * To detect the overlapping block techniques
					*/
//					PackerManager.getInstance().setOverlappingFuction("jmp", curState.getLocation().getValue(), a.getValue());
//					PackerManager.getInstance().setCodeChunking("jmp", curState.getLocation().getValue(), a.getValue());
					curState.setInstruction(i);
					curState.setLocation(a);
					
					// Reset the symbol value based on the result of Z3
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

		return curState;
	}	
}
