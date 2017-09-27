package v2.org.analysis.olly;

import java.util.Map;

import v2.org.analysis.environment.memory.MemoryV1;
import v2.org.analysis.environment.memory.MemoryV2;
import v2.org.analysis.structure.Convert;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class OllyMemory {

	private Map<String, String> olly_memory;

	public OllyMemory(Map<String, String> olly_memory) {
		this.olly_memory = olly_memory;
	}

	public boolean compare(MemoryV1 mems) {
		for (Map.Entry<String, String> value : olly_memory.entrySet()) {
			long buffer_value = Long.parseLong(value.getKey(), 16);
			long olly_mem_value = Long.parseLong(value.getValue(), 16);
			long bp_mem = ((LongValue) mems.getDoubleWordMemoryValue(buffer_value)).getValue() & (-1L >>> 32);
			if (olly_mem_value != bp_mem) {
				return false;
			}
		}
		return true;
	}

	public String getValue(long addr) {
		for (Map.Entry<String, String> value : olly_memory.entrySet()) {
			long buffer_value = Long.parseLong(value.getKey(), 16);
			if (buffer_value == addr) {
				return value.getValue();
			}
		}
		return "";
	}

	public String compareMemory(OllyLoop l, MemoryV2 bpMem, long startAddr, long endaddr) {
		return compare(l.getMems(), bpMem, startAddr, endaddr);
	}

	private String compare(OllyMemory l, MemoryV2 bpMem, long startAddr, long endaddr) {
		long offset = startAddr;
		String ret = "";

		if (l == null) {
			return "OllyDbg does not provide this loop \n";
		}
		
		if (offset > endaddr) {
			return "Start Addr is not right \n";
		}

		while (offset < endaddr) {
			/*
			 * if (count == 20 && Long.toHexString(offset).equals("18ff7c"))
			 * System.out.println("Debug");
			 */
			Value v = bpMem.getDoubleWordMemoryValue(offset);
			String oldValue = l.getValue(offset);
			String newValue = getValue(offset);
			if (newValue == "") {
				ret += Long.toHexString(offset) + ": " + v + "\n";
				offset += 4;
				continue;
			}
			boolean change = false;
			boolean unequal = false;
			String temp = "";
			if (!oldValue.equals(newValue)) {
				temp += new LongValue(offset) + ": " + oldValue + " change to " + newValue + "; ";
				change = true;
			} else {
				temp += new LongValue(offset) + ": no change; ";
			}

			long ollyMemValue = Convert.convertSignedValue(Long.parseLong(newValue, 16), 32);
			if (v != null && v instanceof LongValue) {
				long bpMemValue = ((LongValue) v).getValue() & 0xFFFFFFFF;
				if (bpMemValue != ollyMemValue) {
					temp += ": " + v + " vs " + newValue;
					unequal = true;
				} else {
					temp += " equal";
				}
			} else {
				temp += ": " + v + " vs " + newValue;
				unequal = true;
			}
			if (change && unequal) {
				temp += " Bug";
			}

			temp += "\n";

			if (unequal) {
				ret += temp;
			}
			offset += 4;
		}

		return ret;
	}

	public String compareStack(OllyLoop l, MemoryV2 s, long esp, long stackIndex) {
		return compare(l.getStack(), s, esp, esp + stackIndex);
	}
}
