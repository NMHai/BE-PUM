package v2.org.analysis.olly;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.jakstab.asm.AbsoluteAddress;

import v2.org.analysis.environment.Environment;

public class OllyComparisonV2 {
	private long nextCheck = 0;
	private String ollyFileName;
	private int MAXINDEX = 10000;
	private long firstCount = 0, lastCount = 0;
	private List<OllyLoop> ollyLoop;

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

	public OllyComparisonV2(String olly_output, long start_buffer, long end_buffer, long top_stack) {
		this.ollyFileName = olly_output;
		this.memoryStartAddr = start_buffer;
		this.memoryEndAddr = end_buffer;
		this.stackIndex = top_stack;
		this.ollyLoop = new ArrayList<OllyLoop>();
	}

	public void read_olly() {
		File olly = new File(this.ollyFileName);
		try {
			Scanner sc = new Scanner(olly);
			int i = 1;
			while (sc.hasNextLine() && i < MAXINDEX) {
				String loop = sc.nextLine();
				if (loop.contains("loop")) {
					String register_line = sc.nextLine();
					String flag_line = sc.nextLine();
					String memory_line = sc.nextLine();
					String stack_line = sc.nextLine();
					// Get value
					long id = getLoopIdentifer(loop);
					long addr = getAddress(loop);
					OllyRegister olly_register = new OllyRegister(getRegister(register_line));
					long esp = olly_register.getRegisterValue("esp");
					OllyFlag olly_flag = new OllyFlag(getFlag(flag_line));
					OllyMemory olly_memory = new OllyMemory(this.getMemory(memory_line));
					OllyMemory olly_stack = new OllyMemory(this.getStack(stack_line, esp));
					OllyLoop olly_loop = new OllyLoop(id, addr, olly_register, olly_flag, olly_memory, olly_stack);
					this.ollyLoop.add(olly_loop);
					i++;
				}
			}

			firstCount = this.ollyLoop.get(0).getLoopID();
			lastCount = this.ollyLoop.get(ollyLoop.size() - 1).getLoopID();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private long getAddress(String loop) {
		int identifer_index = loop.indexOf("eip") + 6;
		String temp = loop.substring(identifer_index, loop.indexOf(','));
		temp = temp.replace("0x", "");
		return Long.parseLong(temp, 16);
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
			String temp = all_flag[i].substring(5);
			if (temp.equals("false") || temp.equals("0")) {
				flags[i] = 0;
			} else if (temp.equals("true") || temp.equals("1")) {
				flags[i] = 1;
			}
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
			} else {
				System.out.println();
				return "Olly does not contain loop " + count;
			}
		}

		if (l.getAddress().getValue() != addr.getValue()) {
			ret += "Address is not right: Expect " + addr + " but Olly contains " + l.getAddress() + " Bug \n";
			l = getAddr(addr.getValue(), count);

			if (l != null) {
				nextCheck = (int) (l.getLoopID() + 1);
			} else {
				return "Olly does not contain address " + addr + " Bug";
			}
		}

		if (count == 1 || count == firstCount) {
			ret += l.compareBEPUM(env, l, memoryStartAddr, memoryEndAddr, stackIndex);
		} else {
			OllyLoop l1 = get(count - 1);

			if (l1 == null || l == null) {
				System.out.println("debug");
			}

			ret += l.compareBEPUM(env, l1, memoryStartAddr, memoryEndAddr, stackIndex);
		}
		return ret;
	}

	private OllyLoop getAddr(long value, long count) {
		// TODO Auto-generated method stub
		for (OllyLoop l : ollyLoop) {
			if (l.getAddress().getValue() == value && l.getLoopID() >= count) {
				return l;
			}
		}
		// List<OllyLoop> old = ollyLoop;
		// OllyLoop temp = get(count);
		File olly = new File(this.ollyFileName);
		try {
			Scanner sc = new Scanner(olly);
			int i = 0;
			boolean isRead = false;
			this.ollyLoop.clear();
			while (sc.hasNextLine() && i < MAXINDEX) {
				String loop = sc.nextLine();
				if (loop.contains("loop")) {					
					// Get value
					long id = getLoopIdentifer(loop);

					long addr = getAddress(loop);
					if (id >= count && addr == value) {
						isRead = true;
					}

					if (isRead) {
//						String register_line = sc.nextLine();
//						String flag_line = sc.nextLine();
//						String memory_line = sc.nextLine();
//						String stack_line = sc.nextLine();
//						OllyRegister olly_register = new OllyRegister(getRegister(register_line));
//						long esp = olly_register.getRegisterValue("esp");
//						OllyFlag olly_flag = new OllyFlag(getFlag(flag_line));
//						OllyMemory olly_memory = new OllyMemory(this.getMemory(memory_line));
//						OllyMemory olly_stack = new OllyMemory(this.getStack(stack_line, esp));
//						OllyLoop olly_loop = new OllyLoop(id, addr, olly_register, olly_flag, olly_memory, olly_stack);
//						this.ollyLoop.add(olly_loop);
//						i++;
						OllyRegister olly_register = null;
						OllyFlag olly_flag = null;
						OllyMemory olly_memory = null;
						OllyMemory olly_stack = null;

						String line = sc.nextLine(); 
						long esp = 0;
						if (line.contains("register")) {
							olly_register = new OllyRegister(getRegister(line));
							esp = olly_register.getRegisterValue("esp");
							line = sc.nextLine();						
						} 
						
						if (line.contains("flag")) {
							olly_flag = new OllyFlag(getFlag(line));
							line = sc.nextLine();						
						} 
						
						if (line.contains("memory")) {
							olly_memory = new OllyMemory(this.getMemory(line));
							line = sc.nextLine();						
						} 
						
						if (line.contains("stack")) {
							olly_stack = new OllyMemory(this.getStack(line, esp));
						}
						OllyLoop olly_loop = new OllyLoop(id, addr, olly_register, olly_flag, olly_memory, olly_stack);
						this.ollyLoop.add(olly_loop);
						i++;
					}
				}
			}

			if (isRead) {
				firstCount = this.ollyLoop.get(0).getLoopID();
				lastCount = this.ollyLoop.get(ollyLoop.size() - 1).getLoopID();

				return ollyLoop.get(0);
			} else {
				firstCount = 0;
				lastCount = 0;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}

	private OllyLoop get(long count) {
		if (count < firstCount || count > lastCount) {
			refreshOllyData(count);

			if (isFinished()) {
				return null;
			}

			return ollyLoop.get(0);
		}

		for (OllyLoop l : ollyLoop) {
			if (l.getLoopID() == count) {
				return l;
			}
		}

		return null;
	}

	private void refreshOllyData(long count) {
		// TODO Auto-generated method stub
		File olly = new File(this.ollyFileName);
		try {
			Scanner sc = new Scanner(olly);
			int i = 0;
			boolean isRead = false;
			this.ollyLoop.clear();
			while (sc.hasNextLine() && i < MAXINDEX) {
				String loop = sc.nextLine().toLowerCase();
				if (loop.contains("loop")) {					
					// Get value
					long id = getLoopIdentifer(loop);
					if (id == count) {
						isRead = true;
					}

					if (isRead) {
						long addr = getAddress(loop);
						OllyRegister olly_register = null;
						OllyFlag olly_flag = null;
						OllyMemory olly_memory = null;
						OllyMemory olly_stack = null;

						String line = sc.nextLine();
						long esp = 0;
						if (line.contains("register")) {
							olly_register = new OllyRegister(getRegister(line));
							esp = olly_register.getRegisterValue("esp");
							if (sc.hasNextLine()) {
								line = sc.nextLine();
							}						
						} 
						
						if (line.contains("flag")) {
							olly_flag = new OllyFlag(getFlag(line));
							if (sc.hasNextLine()) {
								line = sc.nextLine();
							}						
						} 
						
						if (line.contains("memory")) {
							olly_memory = new OllyMemory(this.getMemory(line));
							if (sc.hasNextLine()) {
								line = sc.nextLine();
							}						
						} 
						
						if (line.contains("stack")) {
							olly_stack = new OllyMemory(this.getStack(line, esp));
						}
						OllyLoop olly_loop = new OllyLoop(id, addr, olly_register, olly_flag, olly_memory, olly_stack);
						this.ollyLoop.add(olly_loop);
						i++;
					}
				}
			}

			if (isRead) {
				firstCount = this.ollyLoop.get(0).getLoopID();
				lastCount = this.ollyLoop.get(ollyLoop.size() - 1).getLoopID();
			} else {
				firstCount = 0;
				lastCount = 0;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public long getNextCheck() {
		// TODO Auto-generated method stub
		return nextCheck;
	}

	public void importOllyData(AbsoluteAddress checkedAddr, AbsoluteAddress endAddr) {
		// TODO Auto-generated method stub
		File olly = new File(this.ollyFileName);
		try {
			Scanner sc = new Scanner(olly);
			int i = 0;
			while (sc.hasNextLine() && i < MAXINDEX) {
				String loop = sc.nextLine().toLowerCase();
				if (loop.contains("loop")) {
					// Get value
					OllyRegister olly_register = null;
					OllyFlag olly_flag = null;
					OllyMemory olly_memory = null;
					OllyMemory olly_stack = null;

					long id = getLoopIdentifer(loop);
					long addr = getAddress(loop);
					long esp = 0;
					String line = sc.nextLine();
					if (line.contains("register")) {
						olly_register = new OllyRegister(getRegister(line));
						esp = olly_register.getRegisterValue("esp");
						line = sc.nextLine();						
					} 
					
					if (line.contains("flag")) {
						olly_flag = new OllyFlag(getFlag(line));
						line = sc.nextLine();						
					} 
					
					if (line.contains("memory")) {
						olly_memory = new OllyMemory(this.getMemory(line));
						line = sc.nextLine();						
					} 
					
					if (line.contains("stack")) {
						olly_stack = new OllyMemory(this.getStack(line, esp));
					}
					
					OllyLoop olly_loop = new OllyLoop(id, addr, olly_register, olly_flag, olly_memory, olly_stack);
					this.ollyLoop.add(olly_loop);
					i++;
				}
			}

			firstCount = this.ollyLoop.get(0).getLoopID();
			lastCount = this.ollyLoop.get(ollyLoop.size() - 1).getLoopID();
			checkedAddr.setValue(this.ollyLoop.get(0).getAddress().getValue());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// endAddr.setValue(this.ollyLoop.get(this.ollyLoop.size() -
		// 1).getAddress().getValue());
	}

	public int getSize() {
		// TODO Auto-generated method stub
		return ollyLoop.size();
	}

	public long getFirstCount() {
		// TODO Auto-generated method stub
		return firstCount;
	}

	public boolean isFinished() {
		// TODO Auto-generated method stub
		return this.ollyLoop.size() == 0;
	}
}
