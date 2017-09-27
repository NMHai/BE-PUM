/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v2.org.analysis.cfg;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;
import org.jakstab.loader.ExecutableImage;

/**
 *
 * @author zunc
 */
public class CFBlock {

	private TreeMap<Long, Instruction> _inst = new TreeMap<>();
	private long _addrBegin;
	private long _addrEnd;

	private void _refreshRange() {
		_addrBegin = _inst.firstKey();
		_addrEnd = _inst.lastKey();
	}

	public CFBlock() {

	}

	public CFBlock(TreeMap<Long, Instruction> block) {
		if (block == null || block.isEmpty()) {
			return;
		}
		_inst.putAll(block);
		_refreshRange();
	}

	public long getBegin() {
		return _addrBegin;
	}

	public long getEnd() {
		return _addrEnd;
	}
	
	public boolean isEmpty() {
		return _inst.isEmpty();
	}
	
	public void push(long addr, Instruction inst) {
		_inst.put(addr, inst);
		_refreshRange();
	}

	public String getLocName() {
		return String.format("0x00%s", Long.toHexString(_addrBegin));
	}

	public long getLoc() {
		return _addrBegin;
	}

	public boolean isRange(long addr) {
		return addr >= _addrBegin && addr <= _addrEnd;
	}

	public CFBlock splitBlock(long addr) {
		if (addr == _addrBegin || addr == _addrEnd) {
			return null;
		}
		SortedMap<Long, Instruction> subMap = _inst.tailMap(addr);
		TreeMap<Long, Instruction> newBlock = new TreeMap<>(subMap);
		SortedMap<Long, Instruction> curBlock = _inst.headMap(addr);
		_inst = new TreeMap<>(curBlock);
		_refreshRange();
		return new CFBlock(newBlock);
	}

	public long getNextAddr(long addr) {
		if ((addr < _addrBegin) || (addr >= _addrEnd)) {
			return 0;
		}
		return _inst.higherKey(addr);
	}
	
	public boolean getNext(long addr, long addrNext) {
		// get address of next instruction
//		Map.Entry<Long, String> entry = _inst.floorEntry(addr);
//		if (entry == _inst.lastEntry()) {
//			return false;
//		}
		long next = _inst.higherKey(addr);
		return next == addrNext;
	}

	protected String getInst(Instruction inst) {
		String ret = inst.getName() + " ";
		for (int i = 0; i < inst.getOperandCount(); i++) {
			if (i != 0) {
				ret += ", ";
			}
			ret += inst.getOperand(i);
		}
		return ret;
	}
	
	private String getInstructionString(Instruction instr, AbsoluteAddress nodeAddr) {
		String instrString = "";
		if (instr != null) {
			ExecutableImage ext = Program.getProgram().getModule(nodeAddr);

			if (ext != null)
				instrString = instr.toString(nodeAddr.getValue(), ext.getSymbolFinder());
			else {
				// PHONG: change here for Virtual memory
				// instrString = nodeAddr.toString();
				// instrString = instr.getName()+" ";
				// for (int i = 0; i < instr.getOperandCount(); i++){
				// if (i == instr.getOperandCount() - 1)
				// instrString = instrString + instr.getOperand(i);
				// else instrString = instrString + instr.getOperand(i) + ", ";
				// }
				instrString = instr.toString(nodeAddr.getValue(), null);
			}
			instrString = instrString.replace("\t", " ");
		}
		return instrString;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[ ").append("0x").append(Long.toHexString(_addrBegin)).append(" ]").append("\n");
		for (Map.Entry<Long, Instruction> entry : _inst.entrySet()) {
			long addr = entry.getKey();
			Instruction inst = entry.getValue();
			String strInst = getInstructionString(inst, new AbsoluteAddress(addr));
			sb.append(strInst).append("\\l");
		}
		return sb.toString();
	}
}
