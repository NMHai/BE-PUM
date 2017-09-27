/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: EnumWindows.java
 * Created date: May 3, 2016
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import v2.org.analysis.algorithm.OTFModelGeneration.OTFThread;
import v2.org.analysis.algorithm.OTFThreadManager;
import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.cfg.BPVertex;
import v2.org.analysis.environment.Environment;
import v2.org.analysis.path.BPPath;
import v2.org.analysis.path.BPState;
import v2.org.analysis.path.PathList;
import v2.org.analysis.value.Formulas;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser.WNDENUMPROC;

/**
 * This function enumerates all top-level windows on the screen by passing the
 * handle to each window, in turn, to an application-defined callback function.
 * EnumWindows continues until the last top-level window is enumerated or the
 * callback function returns FALSE.
 * 
 * @param lpEnumFunc
 *            Long pointer to an application-defined callback function.
 * @param data
 *            Specifies an application-defined value to be passed to the
 *            callback function.
 * @return Nonzero indicates success. Zero indicates failure. To get extended
 *         error information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class EnumWindows extends User32API {

	public EnumWindows() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		final long t1 = this.params.get(0);
		final long t2 = this.params.get(1);

		final Environment tmpEnv = curState.getEnvironement();

//		WNDENUMPROC lpEnumFunc = new WNDENUMPROC() {
//			private final AbsoluteAddress location = new AbsoluteAddress(t1);
//
//			@Override
//			public boolean callback(HWND hWnd, Pointer data) {
//				// // Log
//				System.out.println("WNDENUMPROC Callback of EnumWindows API");
//				System.out.println(String.format("hWnd: %s, data: %s", Pointer.nativeValue(hWnd.getPointer()),
//						Pointer.nativeValue(data)));
//
//				// // Push parameters to stack
//				Environment environment = new Environment();
//				environment.setMemory(tmpEnv.getMemory());
//				environment.getStack().push(new LongValue(Pointer.nativeValue(data)));
//				environment.getStack().push(new LongValue(Pointer.nativeValue(hWnd.getPointer())));
//				environment.getStack().push(new LongValue(0x74FD6092)); // RETURN to user32.74FD6092
//
//				// // Initialize and start new OTF thread
//				BPState curState = null;
//				BPPath path = null;
//				Instruction inst = Program.getProgram().getInstruction(location, environment);
//				curState = new BPState(tmpEnv, location, inst);
//				path = new BPPath(curState, new PathList(), new Formulas());
//				path.setCurrentState(curState);
//				
//				// Insert start node
//				BPVertex startNode = new BPVertex(location, inst);
//				startNode.setType(0);
//				Program.getProgram().getBPCFG().insertVertex(startNode);
//
//				OTFThread thread = OTFThreadManager.getInstance().getOtfModelGeneration().new OTFThread(path);
//				thread.start();
//
//				return true;
//			}
//		};
//		Pointer data = new Pointer(t2);
//		boolean ret = User32.INSTANCE.EnumWindows(lpEnumFunc, data);

//		register.mov("eax", new LongValue(ret ? 1 : 0));
		register.mov("eax", new LongValue(1));
	}

}
