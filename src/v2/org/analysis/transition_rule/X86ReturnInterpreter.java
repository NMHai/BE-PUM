package v2.org.analysis.transition_rule;

import java.util.List;

import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Immediate;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.Operand;
import org.jakstab.asm.x86.X86RetInstruction;

import v2.org.analysis.apihandle.winapi.APIHandle;
import v2.org.analysis.cfg.BPCFG;
import v2.org.analysis.cfg.BPEdge;
import v2.org.analysis.cfg.BPVertex;
import v2.org.analysis.environment.Environment;
import v2.org.analysis.packer.PackerManager;
import v2.org.analysis.path.BPPath;
import v2.org.analysis.path.BPState;
import v2.org.analysis.structure.Convert;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class X86ReturnInterpreter {

	public BPState execute(X86RetInstruction inst, BPPath path, List<BPPath> pathList, X86TransitionRule rule) {
		// TODO Auto-generated method stub
		BPState curState = path.getCurrentState();
		// Formulas l = path.getPathCondition();
		Environment env = curState.getEnvironement();

		/*
		 * BPState s = specialCase(inst, curState, l); if (s != null) return s;
		 */

		if (inst.getName().startsWith("ret")) {

			// PHONG: CHECK FOR RETURN AT ADDRESS: 004097f0
			// if (curState.getLocation().toString().contains("4097f0")){
			// System.out.println();
			// }

			Value ret = env.getStack().pop();
			// env.getRegister().add("esp", new LongValue(4));

			if (ret != null && ret instanceof LongValue) {

				long r = ((LongValue) ret).getValue();
				if (r == 0x7c9032A8) {
					// if
					// (Program.getProgram().getFileName().equals("api_test_yc1.2.exe"))
					// ret = new LongValue(0x401000) ;
					// else
					return rule.specialProcessSEH(curState);
				}
				
				String api = rule.checkAPICall(ret, curState);
				if (api != null/* !api.equals("") */) {
					APIHandle.executeAPI(new AbsoluteAddress(r), api, inst, path, pathList);
					rule.setCFG(true);
					return curState;
				}

				AbsoluteAddress nextAddr = new AbsoluteAddress(((LongValue) ret).getValue());
				Instruction nextIns = Program.getProgram().getInstruction(nextAddr, env);

//				Program.getProgram().setTechnique("Ret-Indirect Jump");
//				Program.getProgram().setDetailTechnique("Ret-Indirect Jump:" + curState.getLocation() + " ");
//				PackerManager.getInstance().setIndirectJumpTechnique(curState.getLocation().getValue());

				if (nextIns == null) {
					BPCFG cfg = Program.getProgram().getBPCFG();
					BPVertex s1 = cfg.getVertex(curState.getLocation(), curState.getInstruction());
					BPVertex s2 = new BPVertex();

					if (env.getSystem().getKernel().isInside(nextAddr)) {
						s2.setType(BPVertex.ExitNode);
					} else {
						s2.setType(BPVertex.UnknownNode);
					}

					s2.setProperty(nextAddr.toString());
					cfg.insertVertex(s2);
					cfg.insertEdge(new BPEdge(s1, s2));

					nextAddr = null;
					rule.setCFG(true);
				}
				
				/*
				 * Hai: Packer Detection
				 * To detect the overlapping function techniques
				*/
				if (nextAddr != null && curState.getLocation() != null) {
					PackerManager.getInstance().setOverlappingFuction("ret", curState.getLocation().getValue(), 
							nextAddr.getValue());
				}

				curState.setInstruction(nextIns);
				curState.setLocation(nextAddr);

				if (inst.getOperandCount() == 1) {
					Operand op = inst.getOperand(0);
					long t = Convert.convetUnsignedValue(((Immediate) op).getNumber().intValue(),
							rule.getBitCount(inst));

					env.getStack().pop(t);
				}
			}
		} else if (inst.getName().startsWith("iretd")) {
			;
		} else if (inst.getName().startsWith("iret") || inst.getName().startsWith("lret")) {

			// PHONG: CHECK FOR RETURN AT ADDRESS: 004097f0
			// if (curState.getLocation().toString().contains("4097f0")){
			// System.out.println();
			// }

			Value ret = env.getStack().pop();
			// env.getRegister().add("esp", new LongValue(4));

			if (ret != null && ret instanceof LongValue) {

				long r = ((LongValue) ret).getValue();
				if (r == 0x7c9032A8) {
					// if
					// (Program.getProgram().getFileName().equals("api_test_yc1.2.exe"))
					// ret = new LongValue(0x401000) ;
					// else
					return rule.specialProcessSEH(curState);
				}
				
				String api = rule.checkAPICall(ret, curState);
				if (api != null/* !api.equals("") */) {
					APIHandle.executeAPI(new AbsoluteAddress(r), api, inst, path, pathList);
					rule.setCFG(true);
					return curState;
				}

				AbsoluteAddress nextAddr = new AbsoluteAddress(((LongValue) ret).getValue());
				Instruction nextIns = Program.getProgram().getInstruction(nextAddr, env);

//				Program.getProgram().setTechnique("Ret-Indirect Jump");
//				Program.getProgram().setDetailTechnique("Ret-Indirect Jump:" + curState.getLocation() + " ");
//				PackerManager.getInstance().setIndirectJumpTechnique(curState.getLocation().getValue());

				if (nextIns == null) {
					BPCFG cfg = Program.getProgram().getBPCFG();
					BPVertex s1 = cfg.getVertex(curState.getLocation(), curState.getInstruction());
					BPVertex s2 = new BPVertex();

					if (env.getSystem().getKernel().isInside(nextAddr)) {
						s2.setType(BPVertex.ExitNode);
					} else {
						s2.setType(BPVertex.UnknownNode);
					}

					s2.setProperty(nextAddr.toString());
					cfg.insertVertex(s2);
					cfg.insertEdge(new BPEdge(s1, s2));

					nextAddr = null;
					rule.setCFG(true);
				}
				/*
				 * Hai: Packer Detection
				 * To detect the overlapping function techniques
				*/
				PackerManager.getInstance().setOverlappingFuction("ret", 
						curState.getLocation().getValue(), nextAddr.getValue());
				curState.setInstruction(nextIns);
				curState.setLocation(nextAddr);			

				if (inst.getOperandCount() == 1) {
					Operand op = inst.getOperand(0);
					long t = Convert.convetUnsignedValue(((Immediate) op).getNumber().intValue(),
							rule.getBitCount(inst));

					env.getStack().pop(t);
				}
				
				Value cs = env.getStack().pop(); //32-bit pop, high-order 16 bits discarded
				env.getRegister().setRegisterValue("cs", cs);
				Value temporaryEFLAGS = env.getStack().pop();
				Value EFLAGS = env.getFlag().getEFLAGS();
				
				if (temporaryEFLAGS != null && temporaryEFLAGS instanceof LongValue
						&& EFLAGS != null && EFLAGS instanceof LongValue) {
					long t = ((LongValue)temporaryEFLAGS).getValue() & 0x257FD5 | ((LongValue)EFLAGS).getValue() & 0x1A0000;
					env.getFlag().setEFLAGS(t);
				}
			}
		} else {
			Program.getProgram().getLog().error("Instruction not supported" + inst + " at " + curState.getLocation());
		}

		return curState;
	}

}
