package v2.org.analysis.packer;

import java.util.ArrayList;

import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Immediate;
import org.jakstab.asm.Operand;
import org.jakstab.asm.x86.X86AbsoluteAddress;
import org.jakstab.asm.x86.X86MemoryOperand;
import org.jakstab.asm.x86.X86PCRelativeAddress;
import org.jakstab.asm.x86.X86Register;

import v2.org.analysis.apihandle.winapi.APIHandle;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class PackerHelper {

	public static boolean IsInCodeSection (Program prog, AbsoluteAddress address)
	{
		return prog.getMainModule().isCodeArea(address);
	}
	
	public static String GetAPIName (BPState curState, Value apiAddr)
	{
		String api = APIHandle.getAPINameFromAddress(((LongValue) apiAddr).getValue());
		if (api == null || api == "") {
			api = curState.getEnvironement().getSystem().getLibraryHandle().getAPIName(((LongValue) apiAddr).getValue());
		}
		
		if (api == null || api == "") {
			api = Program.getProgram().checkAPI(((LongValue) apiAddr).getValue(), curState.getEnvironement());
			if (api != null && api.equals("")) {
				api = null;
			}
		}	
		return api;
	}
	
	public static Value GetOperandValue (BPState curState, Operand op)
	{
		if (op instanceof X86Register)
		{
			return curState.getEnvironement().getRegister().getRegisterValue(op.toString());
		}
		else if (op instanceof X86MemoryOperand)
		{
//			return curState.getEnvironement().getMemory().getDoubleWordMemoryValue((X86MemoryOperand)op);
			return curState.getEnvironement().getMemory().getExDoubleWordMemoryValue((X86MemoryOperand)op);
		}
		else if (op instanceof X86AbsoluteAddress) 
		{
			return new LongValue(((AbsoluteAddress)op).getValue());
		} 
		else if (op instanceof X86PCRelativeAddress) 
		{
			return new LongValue(((X86PCRelativeAddress) op).getEffectiveValue(curState.getLocation().getValue()));
		}
		else if (op instanceof Immediate)
		{
			return new LongValue(((Immediate) op).getNumber().longValue());
		}
		return null;
	}

	public static boolean CheckOverlapping (PackerSavedBlock blockA, PackerSavedBlock blockB)
	{
		long startBlockA;
		long endBlockA;
		if (blockA.getBeginBlock() < blockA.getEndBlock())
		{
			startBlockA = blockA.getBeginBlock();
			endBlockA = blockA.getEndBlock();
		}
		else 
		{
			startBlockA = blockA.getEndBlock();
			endBlockA = blockA.getBeginBlock();
		}
		long startBlockB;
		long endBlockB;
		if (blockB.getBeginBlock() < blockB.getEndBlock())
		{
			startBlockB = blockB.getBeginBlock();
			endBlockB = blockB.getEndBlock();
		}
		else
		{
			startBlockB = blockB.getEndBlock();
			endBlockB = blockB.getBeginBlock();
		}
		// Check block overlap block
		if (!((endBlockB < startBlockA) ||(startBlockB > endBlockA)))
		{
			if (startBlockA != startBlockB && endBlockA != endBlockB)
			{
				return true;
			}
		}
		return false;
	}
	
	public static <T> ArrayList<T> ClearStates (ArrayList<T> savedStates)
	{
		int size = savedStates.size();
		for (int i = 0; i < size; i++)
		{
			savedStates.remove(0);
		}
		return savedStates;
	}
	
	public static boolean IsExistedState (ArrayList<PackerSavedState> savedStates, PackerSavedState states)
	{
		for (PackerSavedState s: savedStates)
		{
			if (s.getInsLoc() == states.getInsLoc())
			{
				return true;
			}
		}
		return false;
	}
	
	public static boolean IsExisted (ArrayList<Long> list, Long elem)
	{
		for (Long e: list)
		{
			if (elem.longValue() == e.longValue())
			{
				return true;
			}
		}
		return false;
	}
}
