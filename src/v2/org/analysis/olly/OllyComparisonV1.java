package v2.org.analysis.olly;

import org.jakstab.asm.AbsoluteAddress;

import v2.org.analysis.environment.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class OllyComparisonV1 {
	private long nextCheck = 0;
	private String ollyFileName;
	private OllyLoop previous, current;
	private long firstLoopCount = 0;

	public long getMemoryStartAddr() {
		return memoryStartAddr;
	}

	public void setMemoryStartAddr(long memoryStartAddr) {
		this.memoryStartAddr = memoryStartAddr;
	}

	public long getStackIndex() {
		return stackIndex;
	}

	public void setStackIndex(long stackIndex) {
		this.stackIndex = stackIndex;
	}

	private long memoryStartAddr, memoryEndAddr;
	private long stackIndex;

	public OllyComparisonV1(String olly_output, long start_buffer, long end_buffer, long top_stack) {
		this.ollyFileName = olly_output;
		this.memoryStartAddr = start_buffer;
		this.memoryEndAddr = end_buffer;
		this.stackIndex = top_stack;
		// previous = new OllyLoop();
	}

	public void read_olly() {
		File olly = new File(this.ollyFileName);
		try {
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(olly);
			// int i = 0;
			while (sc.hasNextLine()) {
				String loop = sc.nextLine();
				if (loop.contains("loop")) {
					String register_line = sc.nextLine();
					String flag_line = sc.nextLine();
					String memory_line = sc.nextLine();
					String stack_line = sc.nextLine();
					// Get value
					long id = getLoopIdentifer(loop);
					firstLoopCount = id;
					long addr = getAddress(loop);
					OllyRegister olly_register = new OllyRegister(getRegister(register_line));
					long esp = olly_register.getRegisterValue("esp");
					OllyFlag olly_flag = new OllyFlag(getFlag(flag_line));
					OllyMemory olly_memory = new OllyMemory(this.getMemory(memory_line));
					OllyMemory olly_stack = new OllyMemory(this.getStack(stack_line, esp));
					OllyLoop olly_loop = new OllyLoop(id, addr, olly_register, olly_flag, olly_memory, olly_stack);
					previous = olly_loop;
					current = previous;
					return;
					// i++;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private long getAddress(String loop) {
		int identifer_index = loop.indexOf("eip") + 6;
		return Long.parseLong(loop.substring(identifer_index, loop.indexOf(',')), 16);
	}

	private static long getLoopIdentifer(String loop) {
		int identifer_index = loop.indexOf("loop") + 7;
		return Long.parseLong(loop.substring(identifer_index, loop.indexOf('>')), 16);
	}

	private static long[] getRegister(String register_line) {
		int register_index = register_line.indexOf("register") + 11;
		String[] all_register = register_line.substring(register_index).split(", ");
		long[] regs = new long[9];
		for (int i = 0; i < 9; i++) {
			regs[i] = Long.parseLong(all_register[i].substring(6), 16);
		}
		return regs;
	}

	private static long[] getFlag(String flag_line) {
		int flag_index = flag_line.indexOf("flag") + 7;
		String[] all_flag = flag_line.substring(flag_index).split(", ");
		long[] flags = new long[7];
		for (int i = 0; i < 7; i++) {
			flags[i] = Long.parseLong(all_flag[i].substring(5), 16);
		}
		return flags;
	}

	private Map<String, String> getMemory(String memory_line) {
		long start_memory = this.memoryStartAddr;
		int memory_index = memory_line.indexOf("memory") + 9;
		String[] all_memory = memory_line.substring(memory_index).split(" ");
		Map<String, String> memory_value = new HashMap<String, String>();
		for (String value : all_memory) {
			memory_value.put(Long.toHexString(start_memory), value);
			start_memory += 4;
		}
		return memory_value;
	}

	private Map<String, String> getStack(String stack_line, long esp) {
		long start_stack = 0;
		int stack_index = stack_line.indexOf("stack") + 8;
		String[] all_stack = stack_line.substring(stack_index).split(" ");
		Map<String, String> stack_value = new HashMap<String, String>();
		for (String value : all_stack) {
			stack_value.put(Long.toHexString(start_stack + esp), value);
			start_stack += 4;
		}
		return stack_value;
	}

	/*
	 * public boolean compare_bepum(Environment env, int id){ boolean
	 * reg_compare = get(id).compareRegister(env.getRegister()); boolean
	 * flag_compare = get(id).compareFlag(env.getFlag()); boolean mem_compare =
	 * get(id).compareMemory(env.getMemory()); boolean stack_compare =
	 * get(id).compareStack(env.getMemory()); return reg_compare && flag_compare
	 * && mem_compare && stack_compare; }
	 */

	public String compareBEPUM(Environment env, long count, AbsoluteAddress addr) {
		String ret = "";
		OllyLoop l = get(count);
		nextCheck = count + 1;
		if (l == null) {
			l = getAddr(addr.getValue(), count);

			if (l != null) {
				nextCheck = l.getLoopID() + 1;
			} else
				return "Olly does not contain loop " + count;
		}

		if (l.getAddress().getValue() != addr.getValue()) {
			ret += "Address is not right " + addr + " vs " + l.getAddress() + " Bug \n";
			l = getAddr(addr.getValue(), count);

			if (l != null)
				nextCheck = (int) (l.getLoopID() + 1);
			else {
				return "Olly does not contain address " + addr + " Bug";
			}
		}

		if (count == firstLoopCount)
			ret += l.compareBEPUM(env, l, memoryStartAddr, memoryEndAddr, stackIndex);
		else {
			OllyLoop l1 = get(count - 1);

			if (l1 == null || l == null)
				System.out.println("debug");

			ret += l.compareBEPUM(env, l1, memoryStartAddr, memoryEndAddr, stackIndex);
		}
		return ret;
	}

	private OllyLoop getAddr(long value, long count) {
		// TODO Auto-generated method stub
		/*
		 * for (OllyLoop l : ollyLoop) if (l.getAddress().getValue() == value &&
		 * l.getLoopID() >= count) return l;
		 */
		return null;
	}

	private OllyLoop get(long count) {
		/*
		 * for (OllyLoop l : ollyLoop) if (l.getLoopID() == count) return l;
		 */
		if (count == current.getLoopID())
			return current;
		
		if (previous.getLoopID() == count)
			return previous;
		
		//if (count != current.getLoopID()) {
		previous = current;
		current = readOllyWithIndex(count);
		//}

		return current;
	}

	private OllyLoop readOllyWithIndex(long count) {
		// TODO Auto-generated method stub
		File olly = new File(this.ollyFileName);
		try {
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(olly);
			// int i = 0;
			while (sc.hasNextLine()) {
				String loop = sc.nextLine();
				if (loop.contains("loop")) {
					String register_line = sc.nextLine();
					String flag_line = sc.nextLine();
					String memory_line = sc.nextLine();
					String stack_line = sc.nextLine();
					// Get value
					long id = getLoopIdentifer(loop);
					if (id == count) {
						long addr = getAddress(loop);
						OllyRegister olly_register = new OllyRegister(getRegister(register_line));
						long esp = olly_register.getRegisterValue("esp");
						OllyFlag olly_flag = new OllyFlag(getFlag(flag_line));
						OllyMemory olly_memory = new OllyMemory(this.getMemory(memory_line));
						OllyMemory olly_stack = new OllyMemory(this.getStack(stack_line, esp));
						OllyLoop olly_loop = new OllyLoop(id, addr, olly_register, olly_flag, olly_memory, olly_stack);
						// this.ollyLoop.add(olly_loop);
						// previous = olly_loop;
						// current = olly_loop;
						// i++;
						return olly_loop;
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}

	public long getNextCheck() {
		// TODO Auto-generated method stub
		return nextCheck;
	}

	public void importOllyData(AbsoluteAddress checkedAddr, AbsoluteAddress endAddr) {
		// TODO Auto-generated method stub
		File olly = new File(this.ollyFileName);
		try {
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(olly);
			// int i = 0;
			while (sc.hasNextLine()) {
				String loop = sc.nextLine();
				if (loop.contains("loop")) {
					String register_line = sc.nextLine();
					String flag_line = sc.nextLine();
					String memory_line = sc.nextLine();
					String stack_line = sc.nextLine();
					// Get value
					long id = getLoopIdentifer(loop);
					firstLoopCount = id;
					long addr = getAddress(loop);
					OllyRegister olly_register = new OllyRegister(getRegister(register_line));
					long esp = olly_register.getRegisterValue("esp");
					OllyFlag olly_flag = new OllyFlag(getFlag(flag_line));
					OllyMemory olly_memory = new OllyMemory(this.getMemory(memory_line));
					OllyMemory olly_stack = new OllyMemory(this.getStack(stack_line, esp));
					OllyLoop olly_loop = new OllyLoop(id, addr, olly_register, olly_flag, olly_memory, olly_stack);
					// this.ollyLoop.add(olly_loop);
					previous = olly_loop;
					current = olly_loop;
					checkedAddr.setValue(current.getAddress().getValue());
					// i++;
					return;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
		// endAddr.setValue(this.ollyLoop.get(this.ollyLoop.size() -
		// 1).getAddress().getValue());
	}

	public int getLoopCount() {
		// TODO Auto-generated method stub
		return (int) current.getLoopID();
	}
	
	public boolean isFinished() {
		return current == null;
	}
	
}
