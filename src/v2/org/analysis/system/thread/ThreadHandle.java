/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.system
 * File name: ThreadTiming.java
 * Created date: Sep 27, 2015
 * Description:
 */
package v2.org.analysis.system.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.memory.MemoryV2;
import v2.org.analysis.path.BPPath;
import v2.org.analysis.path.BPState;
import v2.org.analysis.path.PathList;
import v2.org.analysis.value.Formulas;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.ReturnMainThreadValue;

/**
 * @author Yen Nguyen
 *
 */
public class ThreadHandle {
	private static final long MAX_WAITING_TIME = 300000;
	// 300 seconds = 5 minutes
	private static final long BASE_HANDLE_VALUE = 0x44448888;
	private static final int BASE_THREAD_ID_VALUE = 0x00008888;

	public static final ReturnMainThreadValue RETURN_TO_BASE_THREAD_VALUE = new ReturnMainThreadValue(); // 0x75C8336A;
	public static final int STACK_BASE_ADDRESS = 200;
	public static final int STACK_SIZE = 200;

	private int createdThreadCount = 0;

	private Map<Long, Thread> threadMap = new HashMap<>();
	private List<Thread> threadList = new ArrayList<>();
	private int currentIndex = 0;

	public ThreadHandle() {
		// TODO Auto-generated constructor stub
	}

	public Thread getNextThread() throws Exception {
		int count = 0;
		long time = System.currentTimeMillis();

		while (true) {
			this.currentIndex++;
			count++;

			if (this.currentIndex >= this.threadList.size()) {
				this.currentIndex = 0;
			}

			if (this.threadList.get(this.currentIndex).isActive()) {
				break;
			}

			if (count >= this.threadList.size() && (System.currentTimeMillis() - time) >= MAX_WAITING_TIME) {
				// All of threads are sleeping so long or waiting for each other
				throw new Exception("DEADLOCK!!!");
			}
		}
		return this.threadList.get(this.currentIndex);
	}

	/**
	 * Create a new thread and add it into manager to timming
	 * 
	 * @param environment
	 *            The environment of parent thread.
	 * 
	 * @param funcPointer
	 *            A pointer to the thread function
	 * 
	 * @param arglistAddr
	 *            A pointer to a variable to be passed to the thread.
	 * 
	 * @param creationFlags
	 *            The flags that control the creation of the thread.
	 */
	public Thread createThread(Environment environment, long funcPointer, long arglistAddr,
			EThreadCreationFlags creationFlags) {
		createdThreadCount++;

		// Generate identifies
		long handle = BASE_HANDLE_VALUE + createdThreadCount;
		int threadID = BASE_THREAD_ID_VALUE + createdThreadCount;

		// Prepare new environment for this thread
		MemoryV2 memory = environment.getMemory();
		environment = new Environment();
		environment.setMemory(memory);

		/********************************************
		 * Initialize some available value on stack *
		 ********************************************/
		environment.getRegister().setRegisterValue("ebp",
				new LongValue(STACK_BASE_ADDRESS + STACK_SIZE * createdThreadCount));
		// Add 1 block
		environment.getRegister().setRegisterValue("esp",
				new LongValue(STACK_BASE_ADDRESS + STACK_SIZE * createdThreadCount + 4));
		/*************
		 * HARD CODE *
		 *************/
		// ntdll.77179882
		environment.getStack().push(new LongValue(0x77179882));
		// RANDOM -- I don't know
		environment.getStack().push(new LongValue(0x00044444));
		// arglist
		environment.getStack().push(new LongValue(arglistAddr));
		// RETURN TO BASE THREAD VALUE
		environment.getStack().push(RETURN_TO_BASE_THREAD_VALUE);

		/***************
		 * INSTRUCTION *
		 ***************/
		AbsoluteAddress location = new AbsoluteAddress(funcPointer);
		Instruction inst = Program.getProgram().getInstruction(location, environment);

		BPState curState = new BPState(environment, location, inst);
		BPPath path = new BPPath(curState, new PathList(), new Formulas());

		Thread thread = new Thread(path, handle, funcPointer, arglistAddr, creationFlags, threadID);
		this.addNewThread(handle, thread);

		return thread;
	}

	public void addNewThread(long handle, Thread thread) {
		if (threadList.size() == 0) {
			thread.setMainThread();
		}
		this.threadList.add(thread);
		this.threadMap.put(handle, thread);
	}
	
	public ThreadHandle clone(BPPath path) {
		ThreadHandle tmp = new ThreadHandle();
		tmp.createdThreadCount = this.createdThreadCount;
		tmp.currentIndex = this.currentIndex;
		
		// Clone all of collections
		for (Map.Entry<Long, Thread> entry : this.threadMap.entrySet()) {
			Thread tmpThread = entry.getValue().clone(path);
			
			tmp.threadList.add(tmpThread);
			tmp.threadMap.put(entry.getKey(), tmpThread);
		}
		
		return tmp;
	}
}
