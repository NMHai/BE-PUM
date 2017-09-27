/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: CreateThread.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import v2.org.analysis.algorithm.OTFModelGeneration;
import v2.org.analysis.algorithm.OTFThreadManager;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.cfg.BPCFG;
import v2.org.analysis.cfg.BPEdge;
import v2.org.analysis.cfg.BPVertex;
import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.stack.Stack;
import v2.org.analysis.path.BPPath;
import v2.org.analysis.path.BPState;
import v2.org.analysis.path.PathList;
import v2.org.analysis.system.VirtualMem;
import v2.org.analysis.value.LongValue;

/**
 * NOT WORKING
 *
 * @author Yen Nguyen
 *
 */
public class CreateThread extends Kernel32API {

	public CreateThread() {
		super();
		NUM_OF_PARMS = 6;
	}

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

	@Override
	public void execute() {
		/*
		 * LPSECURITY_ATTRIBUTES lpThreadAttributes, // pointer to thread
		 * security attributes DWORD dwStackSize, // initial thread stack size,
		 * in bytes LPTHREAD_START_ROUTINE lpStartAddress, // pointer to thread
		 * function LPVOID lpParameter, // argument for new thread DWORD
		 * dwCreationFlags, // creation flags LPDWORD lpThreadId // pointer to
		 * returned thread identifier
		 */

		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);
		long t5 = this.params.get(4);
		long t6 = this.params.get(5);

		LongValue threadAddress = (LongValue) memory.getDoubleWordMemoryValue(t3);

		System.out.println("ThreadFunctionAddress: 0x" + Long.toHexString(t3));

		Environment env = curState.getEnvironement().clone();
		Instruction ins = curState.getInstruction();
		BPCFG cfg = Program.getProgram().getBPCFG();

		//--- dirty code to get right location
		AbsoluteAddress curLocation = curState.getLocation();

		AbsoluteAddress prevLocation = new AbsoluteAddress(curLocation.getValue() - ins.getSize() + 1);
		Instruction prevIns = Program.getProgram().getInstruction(prevLocation, env);
		BPVertex src = cfg.getVertex(prevLocation, prevIns);
		//BPVertex src = cfg.getVertex(curLocation, ins);

		//AbsoluteAddress newLocation = new AbsoluteAddress(curState.getLocation().getValue() + ins.getSize());
		AbsoluteAddress newLocation = new AbsoluteAddress(t3);
		Instruction newIns;
		if (curState.getEnvironement().getSystem().isInVirtualMemory() == true) {
			byte[] opcodes = this.getOpcodesArray(curState, newLocation.getValue());
			newIns = Program.getProgram().getInstruction(opcodes, env);
		} else {
			newIns = Program.getProgram().getInstruction(newLocation, env);
		}
		Stack stk = env.getStack();
		stk.push(new LongValue(0x756633aa)); // hardcode return location

		BPState threadState = new BPState();
		//threadState.setInstruction(ins);
		threadState.setEnvironment(env);
		threadState.setInstruction(newIns);
		threadState.setLocation(newLocation);

		BPPath threadPath = new BPPath(threadState, new PathList(), curState.getPathCondition().clone());
		threadPath.setCurrentState(threadState);

		//---
		BPVertex dest = new BPVertex(threadState.getLocation(), threadState.getInstruction());
		dest = cfg.insertVertex(dest);
		BPEdge edge = new BPEdge(src, dest);
		cfg.insertEdge(edge);

		OTFThreadManager thrManager = OTFThreadManager.getInstance(); // .afterLoop();
		OTFModelGeneration otfModelGeneration = thrManager.getOtfModelGeneration();

		//--- create thread
		if (otfModelGeneration._curThread != null) {
			otfModelGeneration._curThread.addPath(threadPath);
		}

	}

}
