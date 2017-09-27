package org.analysis.api_stub;

import java.util.Map;

import org.analysis.concrete_execution.ConcreteExecution;
import org.analysis.concrete_execution.ConcreteStack;
import org.analysis.concrete_execution.ConcreteValueMemoryOperand;
import org.analysis.concrete_execution.ConcreteValueRegister;
import org.analysis.formula.AnyExp;
import org.analysis.formula.Formula;
import org.analysis.formula.LongValueOld;
import org.analysis.formula.SymbolExp;
import org.analysis.formula.Value;
import org.analysis.symbolic_execution.SymbolStack;
import org.analysis.symbolic_execution.SymbolValueMemoryOperand;
import org.analysis.symbolic_execution.SymbolValueRegister;
import org.analysis.symbolic_execution.SymbolicExecution;
import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86CondJmpInstruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.cfg.AddressList;
import v2.org.analysis.system.SystemHandle;

public class APIStub {

	/*
	 * public static void executeConcrete(String funcName) {
	 * System.out.println(funcName); }
	 */

	public static boolean executeSymbolic(String funcName, SymbolicExecution se, Instruction inst) {
		// TODO Auto-generated method stub
		long returnValue;
		boolean ret = true;

		// FormulaSet formulaList = se.getFormulas();
		// SymbolFlag symbolFlag = se.getSymbolFlag();
		SymbolStack symbolStack = se.getSymbolStack();
		SymbolValueMemoryOperand symbolValueMemoryOperand = se.getSymbolValueMemoryOperand();
		SymbolValueRegister symbolValueRegister = se.getSymbolValueRegister();
		// SymbolValueRegisterPart symbolValueRegisterPart =
		// se.getSymbolValueRegisterPart();
		// SymbolValueSegment symbolValueSegment = se.getSymbolValueSegment();
		Program program = se.getProgram();
		SystemHandle system = se.getSystem();

		// if (funcName.equals("FindFirstFileA"))
		// System.out.println("Debug");
		if (inst.getName().toString().equals("call")) {
			System.out.println("Call Symbolic Execution of API:" + funcName);
			if (funcName.startsWith("WinExec")) {
				// HMODULE hModule, handle to DLL module
				// LPCSTR lpProcName name of function
				Value x1 = symbolStack.pop();
				Value x2 = symbolStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " ");

				if (x1 instanceof LongValueOld && x2 instanceof LongValueOld) {
					/*
					 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
					 * x1).getValueOperand(), ((ValueLongExp)
					 * x2).getValueOperand(), program);
					 */
					String commandLine = symbolValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32,
							((LongValueOld) x1).getValue()));
					System.out.println("Command Line:" + commandLine + ", Window Style:" + ((LongValueOld) x2).getValue());
					symbolValueRegister.mov(
							"%eax",
							new LongValueOld(system.getWindowHandle().createWindow(commandLine,
									((LongValueOld) x2).getValue())));

				}
			} else if (funcName.startsWith("CreateProcess")) {
				/*
				 * LPCTSTR lpApplicationName, // pointer to name of executable
				 * module LPTSTR lpCommandLine, // pointer to command line
				 * string LPSECURITY_ATTRIBUTES lpProcessAttributes, // pointer
				 * to process security attributes LPSECURITY_ATTRIBUTES
				 * lpThreadAttributes, // pointer to thread security attributes
				 * BOOL bInheritHandles, // handle inheritance flag DWORD
				 * dwCreationFlags, // creation flags LPVOID lpEnvironment, //
				 * pointer to new environment block LPCTSTR lpCurrentDirectory,
				 * // pointer to current directory name LPSTARTUPINFO
				 * lpStartupInfo, // pointer to STARTUPINFO
				 * LPPROCESS_INFORMATION lpProcessInformation // pointer to
				 * PROCESS_INFORMATION
				 */
				Value x1 = symbolStack.pop();
				Value x2 = symbolStack.pop();
				Value x3 = symbolStack.pop();
				Value x4 = symbolStack.pop();
				Value x5 = symbolStack.pop();
				Value x6 = symbolStack.pop();
				Value x7 = symbolStack.pop();
				Value x8 = symbolStack.pop();
				Value x9 = symbolStack.pop();
				Value x10 = symbolStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4 + " " + x5 + " " + x6 + " " + x7
						+ " " + x8 + " " + x9 + " " + x10);

				if (x1 instanceof LongValueOld && x2 instanceof LongValueOld && x3 instanceof LongValueOld
						&& x4 instanceof LongValueOld && x5 instanceof LongValueOld && x6 instanceof LongValueOld
						&& x7 instanceof LongValueOld && x8 instanceof LongValueOld && x9 instanceof LongValueOld
						&& x10 instanceof LongValueOld) {
					/*
					 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
					 * x1).getValueOperand(), ((ValueLongExp)
					 * x2).getValueOperand(), program);
					 */
					long t1 = ((LongValueOld) x1).getValue();
					long t2 = ((LongValueOld) x2).getValue();
					long t3 = ((LongValueOld) x3).getValue();
					long t4 = ((LongValueOld) x4).getValue();
					long t5 = ((LongValueOld) x5).getValue();
					long t6 = ((LongValueOld) x1).getValue();
					long t7 = ((LongValueOld) x2).getValue();
					long t8 = ((LongValueOld) x3).getValue();
					long t9 = ((LongValueOld) x4).getValue();
					long t10 = ((LongValueOld) x5).getValue();

					String moduleFileName = symbolValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, t1));
					String commandLine = symbolValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, t2));
					String curDir = symbolValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, t8));
					String pStarupInfo = symbolValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, t9));
					System.out.println("Module File Name:" + moduleFileName + ", Command Line:" + commandLine
							+ ", Process Attribute Security:" + t3 + ", Thread Attribute Security:" + t4
							+ ", Handle Flag:" + t5 + ", Creation Flag:" + t6 + ", Environemnt Block:" + t7
							+ ", Current Directory:" + curDir + ", Process Starup Info:" + pStarupInfo
							+ ", Process Information:" + t10);
					symbolValueRegister.mov(
							"%eax",
							new LongValueOld(system.getProcessHandle().createProcess(moduleFileName, commandLine, t3, t4,
									t5, t6, t7, curDir, pStarupInfo, t10)));
				}
			} else if (funcName.startsWith("GetFileAttributes")) {
				// HANDLE hFindFile // file search handle
				Value x1 = symbolStack.pop();
				System.out.println("Argument:" + x1);

				if (x1 instanceof LongValueOld) {
					String fName = symbolValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32,
							((LongValueOld) x1).getValue()));
					long attr = system.getFileAttribute(fName);
					symbolValueRegister.mov("%eax", new LongValueOld(attr));

					System.out.println("Return Value:" + attr);
				}

			} else if (funcName.startsWith("GetProcAddress")) {
				// HMODULE hModule, handle to DLL module
				// LPCSTR lpProcName name of function
				Value x1 = symbolStack.pop();
				Value x2 = symbolStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " ");

				if (x1 instanceof LongValueOld && x2 instanceof LongValueOld) {
					/*
					 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
					 * x1).getValueOperand(), ((ValueLongExp)
					 * x2).getValueOperand(), program);
					 */
					String functionName = symbolValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32,
							((LongValueOld) x2).getValue()));
					System.out.println("Function Name:" + functionName + ", Library Handle:" + x1);
					symbolValueRegister.mov("%eax",
							new LongValueOld(system.getProcAddress(((LongValueOld) x1).getValue(), functionName)));

				}

			} else if (funcName.startsWith("LoadLibraryA")) {
				// LPCTSTR lpLibFileName // address of filename of executable
				// module
				Value x1 = symbolStack.pop();
				System.out.print("Argument:" + x1);
				if (x1 instanceof LongValueOld) {
					/*
					 * returnValue = APIHandler.loadLibraryA( ((ValueLongExp)
					 * x1).getValueOperand(), program);
					 */
					String libraryName = symbolValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32,
							((LongValueOld) x1).getValue()));
					System.out.println(" Library Name:" + libraryName);

					symbolValueRegister.mov("%eax", new LongValueOld(system.getLibraryHandle(libraryName)));
				}
			} else if (funcName.startsWith("SetFilePointer")) {
				/*
				 * HANDLE hFile, // handle of file LONG lDistanceToMove, //
				 * number of bytes to move file pointer PLONG
				 * lpDistanceToMoveHigh, // address of high-order word of
				 * distance to move DWORD dwMoveMethod // how to move
				 */
				Value x1 = symbolStack.pop();
				Value x2 = symbolStack.pop();
				Value x3 = symbolStack.pop();
				Value x4 = symbolStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4 + " ");

				if (x1 instanceof LongValueOld && x2 instanceof LongValueOld && x3 instanceof LongValueOld
						&& x4 instanceof LongValueOld) {
					/*
					 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
					 * x1).getValueOperand(), ((ValueLongExp)
					 * x2).getValueOperand(), program);
					 */
					long t1 = ((LongValueOld) x1).getValue();
					long t2 = ((LongValueOld) x2).getValue();
					long t3 = ((LongValueOld) x3).getValue();
					long t4 = ((LongValueOld) x4).getValue();

					// String str = symbolValueMemoryOperand.getText(new
					// X86MemoryOperand(DataType.INT32, t2));
					System.out.println("Handle File:" + t1 + ", Number of Bytes:" + t2 + ", Address of High-Order:"
							+ t3 + ", Move Type:" + t4);
					symbolValueRegister.mov("%eax",
							new LongValueOld(system.getFileHandle().setFilePointer(t1, t2, t3, t4)));
				}

			} else if (funcName.startsWith("FindClose")) {
				// HANDLE hFindFile // file search handle
				Value x1 = symbolStack.pop();
				System.out.println("Argument:" + x1);

				if (x1 instanceof LongValueOld) {
					long t = ((LongValueOld) x1).getValue();

					symbolValueRegister.mov("%eax", new LongValueOld(system.getFileHandle().closeFind(t)));
				}

			} else if (funcName.startsWith("DeleteFileA")) {
				// LPCTSTR lpFileName // pointer to name of file to delete
				Value x1 = symbolStack.pop();
				System.out.println("Argument:" + x1);

				if (x1 instanceof LongValueOld) {
					String fName = symbolValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32,
							((LongValueOld) x1).getValue()));
					symbolValueRegister.mov("%eax", new LongValueOld(system.getFileHandle().deleteFile(fName)));
				}

			} else if (funcName.startsWith("GetSystemTime")) {
				// LPSYSTEMTIME lpSystemTime // address of system time structure
				Value x1 = symbolStack.pop();
				System.out.println("Memory Address:" + x1.toString());

				if (x1 instanceof LongValueOld) {
					LongValueOld x = (LongValueOld) x1;

					symbolValueMemoryOperand.setText(new X86MemoryOperand(DataType.INT32, x.getValue()),
							new AnyExp().toString());
					// symbolValueMemoryOperand.setText(new
					// X86MemoryOperand(DataType.INT32, x.getValueOperand()),
					// String.valueOf(System.currentTimeMillis()));
				}

			} else if (funcName.startsWith("GetVersionExA")) {
				// This function has no parameters.

				long verNum = 498139398;
				System.out.println("Version Number:" + verNum);

				symbolValueRegister.mov("%eax", new LongValueOld(verNum));

			} else if (funcName.startsWith("MapViewOfFile")) {
				/*
				 * HANDLE hFileMappingObject, // file-mapping object to map into
				 * address space DWORD dwDesiredAccess, // access mode DWORD
				 * dwFileOffsetHigh, // high-order 32 bits of file offset DWORD
				 * dwFileOffsetLow, // low-order 32 bits of file offset DWORD
				 * dwNumberOfBytesToMap // number of bytes to map
				 */
				Value x1 = symbolStack.pop();
				Value x2 = symbolStack.pop();
				Value x3 = symbolStack.pop();
				Value x4 = symbolStack.pop();
				Value x5 = symbolStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4 + " " + x5);

				// symbolValueRegister.mov("%eax", 1);
				if (x1 instanceof LongValueOld && x2 instanceof LongValueOld && x3 instanceof LongValueOld
						&& x4 instanceof LongValueOld && x5 instanceof LongValueOld) {
					/*
					 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
					 * x1).getValueOperand(), ((ValueLongExp)
					 * x2).getValueOperand(), program);
					 */
					long t1 = ((LongValueOld) x1).getValue();
					long t2 = ((LongValueOld) x2).getValue();
					long t3 = ((LongValueOld) x3).getValue();
					long t4 = ((LongValueOld) x4).getValue();
					long t5 = ((LongValueOld) x5).getValue();

					// String str = symbolValueMemoryOperand.getText(new
					// X86MemoryOperand(DataType.INT32, t2));
					System.out.println("Handle File:" + t1 + ", Access Mode:" + t2
							+ ", High-order 32 bits of file offset:" + t3 + ", Low-order 32 bits of file offset :" + t4
							+ ", Number of bytes to map:" + t5);
					long v = system.getFileHandle().mapViewOfFile(t1, t2, t3, t4, t5);
					System.out.println("Base Address: " + v);
					symbolValueRegister.mov("%eax", new LongValueOld(v));
				}

			} else if (funcName.startsWith("ReadFile")) {
				/*
				 * HANDLE hFile, // handle of file to read LPVOID lpBuffer, //
				 * address of buffer that receives data DWORD
				 * nNumberOfBytesToRead, // number of bytes to read LPDWORD
				 * lpNumberOfBytesRead, // address of number of bytes read
				 * LPOVERLAPPED lpOverlapped // address of structure for data
				 */
				Value x1 = symbolStack.pop();
				Value x2 = symbolStack.pop();
				Value x3 = symbolStack.pop();
				Value x4 = symbolStack.pop();
				Value x5 = symbolStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4 + " " + x5);

				if (x1 instanceof LongValueOld && x2 instanceof LongValueOld && x3 instanceof LongValueOld
						&& x4 instanceof LongValueOld && x5 instanceof LongValueOld) {
					/*
					 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
					 * x1).getValueOperand(), ((ValueLongExp)
					 * x2).getValueOperand(), program);
					 */
					long t1 = ((LongValueOld) x1).getValue();
					long t2 = ((LongValueOld) x2).getValue();
					long t3 = ((LongValueOld) x3).getValue();
					long t4 = ((LongValueOld) x4).getValue();
					long t5 = ((LongValueOld) x5).getValue();

					// String str = symbolValueMemoryOperand.getText(new
					// X86MemoryOperand(DataType.INT32, t2));
					System.out.println("Handle File:" + t1 + ", Address of Buffer:" + t2 + ", Number of Byte:" + t3
							+ ", Number of Actual Read Bytes:" + t4 + ", Overlapped:" + t5);
					String str = system.getFileHandle().readFile(t1, t2, t3, t4, t5);
					if (str != null) {
						symbolValueMemoryOperand.setText(new X86MemoryOperand(DataType.INT32, t2), str);
						symbolValueRegister.mov("%eax", new LongValueOld(1));
					} else {
						symbolValueRegister.mov("%eax", new LongValueOld(0));
					}
				}
			} else if (funcName.startsWith("UnMapViewOfFile")) {
				// LPCVOID lpBaseAddress // address where mapped view begins
				Value x1 = symbolStack.pop();
				System.out.println("Argument:" + x1);

			} else if (funcName.startsWith("WriteFile")) {
				/*
				 * HANDLE hFile, // handle to file to write to LPCVOID lpBuffer,
				 * // pointer to data to write to file DWORD
				 * nNumberOfBytesToWrite, // number of bytes to write LPDWORD
				 * lpNumberOfBytesWritten, // pointer to number of bytes written
				 * LPOVERLAPPED lpOverlapped // pointer to structure needed for
				 * overlapped I/O
				 */
				Value x1 = symbolStack.pop();
				Value x2 = symbolStack.pop();
				Value x3 = symbolStack.pop();
				Value x4 = symbolStack.pop();
				Value x5 = symbolStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4 + " " + x5);

				if (x1 instanceof LongValueOld && x2 instanceof LongValueOld && x3 instanceof LongValueOld
						&& x4 instanceof LongValueOld && x5 instanceof LongValueOld) {
					/*
					 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
					 * x1).getValueOperand(), ((ValueLongExp)
					 * x2).getValueOperand(), program);
					 */
					long t1 = ((LongValueOld) x1).getValue();
					long t2 = ((LongValueOld) x2).getValue();
					long t3 = ((LongValueOld) x3).getValue();
					long t4 = ((LongValueOld) x4).getValue();
					long t5 = ((LongValueOld) x5).getValue();

					String str = symbolValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, t2));
					System.out.println("Handle File:" + t1 + ", String written:" + str + ", Number of Byte:" + t3
							+ ", Pointer:" + t4 + ", Overlapped:" + t5);
					symbolValueRegister.mov("%eax",
							new LongValueOld(system.getFileHandle().writeFile(t1, str, t3, t4, t5)));
				}
			} else if (funcName.startsWith("RegSetValueExA")) {
				/*
				 * HKEY hKey, // handle of key to set value for LPCTSTR
				 * lpValueName, // address of value to set DWORD Reserved, //
				 * reserved DWORD dwType, // flag for value type CONST BYTE
				 * *lpData, // address of value data DWORD cbData // size of
				 * value data
				 */

				Value x1 = symbolStack.pop();
				Value x2 = symbolStack.pop();
				Value x3 = symbolStack.pop();
				Value x4 = symbolStack.pop();
				Value x5 = symbolStack.pop();
				Value x6 = symbolStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4 + " " + x5 + " " + x6);

			} else if (funcName.startsWith("SetEndOfFile")) {
				// HANDLE hFile // handle of file whose EOF is to be set
				Value x1 = symbolStack.pop();

				System.out.println("Argument:" + x1);

				if (x1 instanceof LongValueOld) {
					long t1 = ((LongValueOld) x1).getValue();

					System.out.println("Handle of file:" + t1);
					symbolValueRegister.mov("%eax", new LongValueOld(system.getFileHandle().setEndOfFile(t1)));
				}

			} else if (funcName.startsWith("WaitForSingleObject")) {
				/*
				 * HANDLE hHandle, // handle of object to wait for DWORD
				 * dwMilliseconds // time-out interval in milliseconds
				 */
				Value x1 = symbolStack.pop();
				Value x2 = symbolStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " ");

			} else if (funcName.startsWith("PostMessageA")) {
				/*
				 * HWND hWnd, // handle of destination window UINT Msg, //
				 * message to post WPARAM wParam, // first message parameter
				 * LPARAM lParam // second message parameter
				 */
				Value x1 = symbolStack.pop();
				Value x2 = symbolStack.pop();
				Value x3 = symbolStack.pop();
				Value x4 = symbolStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4);

				if (x1 instanceof LongValueOld && x2 instanceof LongValueOld && x3 instanceof LongValueOld
						&& x4 instanceof LongValueOld) {
					/*
					 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
					 * x1).getValueOperand(), ((ValueLongExp)
					 * x2).getValueOperand(), program);
					 */
					long t1 = ((LongValueOld) x1).getValue();
					long t2 = ((LongValueOld) x2).getValue();
					long t3 = ((LongValueOld) x3).getValue();
					long t4 = ((LongValueOld) x4).getValue();

					String str = symbolValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, t2));
					String str1 = symbolValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, t1));
					System.out.println("Handle Window:" + t1 + " " + str1 + ", Post Message:" + str + ", First Param:"
							+ t3 + ", Second Param:" + t4);
					symbolValueRegister.mov("%eax",
							new LongValueOld(system.getWindowHandle().postMessage(t1, str, t3, t4)));
				}

			} else if (funcName.startsWith("lstrcatA")) {
				// LPTSTR lpString1, // address of buffer for concatenated
				// strings
				// LPCTSTR lpString2 // address of string to add to string1
				Value x1 = symbolStack.pop();
				Value x2 = symbolStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " ");

				if (x1 instanceof LongValueOld && x2 instanceof LongValueOld) {
					/*
					 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
					 * x1).getValueOperand(), ((ValueLongExp)
					 * x2).getValueOperand(), program);
					 */
					long destAddr = ((LongValueOld) x1).getValue();
					String dest = symbolValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32,
							((LongValueOld) x1).getValue()));
					String src = symbolValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, ((LongValueOld) x2)
							.getValue()));
					dest = dest.concat(src);
					System.out.println("Destination Address:" + destAddr + ", Source String:" + src);
					symbolValueMemoryOperand.setText(new X86MemoryOperand(DataType.INT32, destAddr), dest);
					symbolValueRegister.mov("%eax", new LongValueOld(1));
				}

			} else if (funcName.startsWith("lstrcmpA")) {
				// LPCTSTR lpString1, // address of first string
				// LPCTSTR lpString2 // address of second string
				Value x1 = symbolStack.pop();
				Value x2 = symbolStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " ");

				if (x1 instanceof LongValueOld && x2 instanceof LongValueOld) {
					/*
					 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
					 * x1).getValueOperand(), ((ValueLongExp)
					 * x2).getValueOperand(), program);
					 */
					// long destAddr = ((ValueLongExp) x1).getValueOperand();
					String dest = symbolValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32,
							((LongValueOld) x1).getValue()));
					String src = symbolValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, ((LongValueOld) x2)
							.getValue()));
					// dest += src;
					System.out.println("Destination String:" + dest + ", Source String:" + src);
					// symbolValueMemoryOperand.setText(new
					// X86MemoryOperand(DataType.INT32, destAddr), dest);
					symbolValueRegister.mov("%eax", new LongValueOld(dest.compareTo(src)));
				}

			} else if (funcName.startsWith("lstrlenA")) {
				// LPCTSTR lpString // address of string to count
				Value x1 = symbolStack.pop();
				// Exp x2 = symbolStack.pop();
				System.out.println("Argument:" + x1);

				if (x1 instanceof LongValueOld) {
					String dest = symbolValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32,
							((LongValueOld) x1).getValue()));
					System.out.println("Destination String:" + dest);
					symbolValueRegister.mov("%eax", new LongValueOld(dest.length()));
				}

			} else if (funcName.startsWith("MoveFileA")) {
				// LPCTSTR lpExistingFileName, // address of name of the
				// existing file
				// LPCTSTR lpNewFileName // address of new name for the file
				Value x1 = symbolStack.pop();
				Value x2 = symbolStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " ");

				if (x1 instanceof LongValueOld && x2 instanceof LongValueOld) {
					long t1 = ((LongValueOld) x1).getValue();
					long t2 = ((LongValueOld) x2).getValue();

					String fileNameOld = symbolValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, t1));
					String fileNameNew = symbolValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, t2));
					System.out.println("Old File:" + fileNameOld + ", New File:" + fileNameNew);

					symbolValueRegister.mov("%eax",
							new LongValueOld(system.getFileHandle().moveFile(fileNameOld, fileNameNew)));

				}

			} else if (funcName.startsWith("RegCloseKey")) {
				// HKEY hKey // handle of key to close
				Value x1 = symbolStack.pop();
				System.out.println("Argument:" + x1 + " ");

			} else if (funcName.startsWith("RegOpenKeyExA")) {
				/*
				 * HKEY hKey, // handle of open key LPCTSTR lpSubKey, // address
				 * of name of subkey to open DWORD ulOptions, // reserved REGSAM
				 * samDesired, // security access mask PHKEY phkResult //
				 * address of handle of open key
				 */

				Value x1 = symbolStack.pop();
				Value x2 = symbolStack.pop();
				Value x3 = symbolStack.pop();
				Value x4 = symbolStack.pop();
				Value x5 = symbolStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4 + " " + x5);

			} else if (funcName.startsWith("SendMessageA")) {
				/*
				 * HWND hWnd, // handle of destination window UINT Msg, //
				 * message to send WPARAM wParam, // first message parameter
				 * LPARAM lParam // second message parameter
				 */

				Value x1 = symbolStack.pop();
				Value x2 = symbolStack.pop();
				Value x3 = symbolStack.pop();
				Value x4 = symbolStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4);

				if (x1 instanceof LongValueOld && x2 instanceof LongValueOld && x3 instanceof LongValueOld
						&& x4 instanceof LongValueOld) {
					/*
					 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
					 * x1).getValueOperand(), ((ValueLongExp)
					 * x2).getValueOperand(), program);
					 */
					long t1 = ((LongValueOld) x1).getValue();
					long t2 = ((LongValueOld) x2).getValue();
					long t3 = ((LongValueOld) x3).getValue();
					long t4 = ((LongValueOld) x4).getValue();

					// String str = symbolValueMemoryOperand.getText(new
					// X86MemoryOperand(DataType.INT32, t2));
					String msg = symbolValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, t2));

					System.out.println("Window Handle:" + t1 + ", Message Sent:" + msg + ", First Param:" + t3
							+ ", Second Param:" + t4);
					symbolValueRegister.mov("%eax",
							new LongValueOld(system.getWindowHandle().sendMessage(t1, msg, t3, t4)));
				}

			} else
			// insert API them
			if (funcName.startsWith("CopyFileA")) {
				/*
				 * LPCTSTR lpExistingFileName, // pointer to name of an existing
				 * file LPCTSTR lpNewFileName, // pointer to filename to copy to
				 * BOOL bFailIfExists // flag for operation if file exists
				 */
				Value x1 = symbolStack.pop();
				Value x2 = symbolStack.pop();
				Value x3 = symbolStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " " + x3);

				if (x1 instanceof LongValueOld && x2 instanceof LongValueOld && x3 instanceof LongValueOld) {
					long t1 = ((LongValueOld) x1).getValue();
					long t2 = ((LongValueOld) x2).getValue();
					long t3 = ((LongValueOld) x3).getValue();

					String fileNameOld = symbolValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, t1));
					String fileNameNew = symbolValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, t2));
					System.out.println("Old File:" + fileNameOld + ", New File:" + fileNameNew + ", Flag:" + t3);

					symbolValueRegister.mov("%eax",
							new LongValueOld(system.getFileHandle().copyFile(fileNameOld, fileNameNew, t3)));

				}

			} else if (funcName.startsWith("CreateThread")) {
				/*
				 * LPSECURITY_ATTRIBUTES lpThreadAttributes, // pointer to
				 * thread security attributes DWORD dwStackSize, // initial
				 * thread stack size, in bytes LPTHREAD_START_ROUTINE
				 * lpStartAddress, // pointer to thread function LPVOID
				 * lpParameter, // argument for new thread DWORD
				 * dwCreationFlags, // creation flags LPDWORD lpThreadId //
				 * pointer to returned thread identifier
				 */
				Value x1 = symbolStack.pop();
				Value x2 = symbolStack.pop();
				Value x3 = symbolStack.pop();
				Value x4 = symbolStack.pop();
				Value x5 = symbolStack.pop();
				Value x6 = symbolStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4 + " " + x5 + " " + x6);

			} else if (funcName.startsWith("ExitProcess")) {
				// UINT uExitCode // exit code for all threads
				Value x1 = symbolStack.pop();

				System.out.println("Argument:" + x1);

			} else if (funcName.startsWith("FindWindowA")) {
				// LPCTSTR lpClassName, // pointer to class name
				// LPCTSTR lpWindowName // pointer to window name
				Value x1 = symbolStack.pop();
				Value x2 = symbolStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " ");

				if (x1 instanceof LongValueOld && x2 instanceof LongValueOld) {
					/*
					 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
					 * x1).getValueOperand(), ((ValueLongExp)
					 * x2).getValueOperand(), program);
					 */
					String className = symbolValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32,
							((LongValueOld) x1).getValue()));

					String windowName = symbolValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32,
							((LongValueOld) x2).getValue()));
					System.out.println("Class Name:" + className + ", Window Name Address:"
							+ ((LongValueOld) x2).getValue() + ", Window Name:" + windowName);
					symbolValueRegister.mov("%eax",
							new LongValueOld(system.getWindowHandle().findWindow(className, ((LongValueOld) x2).getValue())));

				}

			} else if (funcName.startsWith("FreeEnvironmentStringsA")) {
				// LPTSTR lpszEnvironmentBlock // pointer to a block of
				// environment strings
				Value x1 = symbolStack.pop();
				System.out.println("Argument:" + x1);

			} else if (funcName.startsWith("FreeEnvironmentStringsW")) {
				// LPTSTR lpszEnvironmentBlock // pointer to a block of
				// environment strings
				Value x1 = symbolStack.pop();

				System.out.println("Argument:" + x1);

			} else if (funcName.startsWith("GetCommandLineA")) {
				// This function has no parameters.

				long disp = 4796200;
				String commandLine = "C:/Windows/" + program.getFileName();
				System.out.println("Argument MemoryOperand:" + disp + ", Command Line:" + commandLine);
				symbolValueMemoryOperand.setText(new X86MemoryOperand(DataType.INT32, disp), commandLine);

			} else if (funcName.startsWith("GetEnvironmentStrings")) {
				// This function has no parameters.

				System.out.println("Argument:" + "null");

			} else if (funcName.startsWith("GetEnvironmentStringsW")) {
				// This function has no parameters.

				System.out.println("Argument:" + "null");

			} else if (funcName.startsWith("GetFileType")) {
				// HANDLE hFile // file handle
				Value x1 = symbolStack.pop();
				System.out.println("Argument:" + x1);

			} else if (funcName.startsWith("GetLastError")) {
				// This function has no parameters.
				long verNum = 14007;
				System.out.println("Last Error:" + verNum);

				symbolValueRegister.mov("%eax", new LongValueOld(verNum));

			} else if (funcName.startsWith("GetStartupInfoA")) {
				// LPSTARTUPINFO lpStartupInfo // address of STARTUPINFO
				// structure
				Value x1 = symbolStack.pop();

				System.out.println("Argument:" + x1);

				if (x1 != null && x1 instanceof LongValueOld) {
					// String sInfo = "Info";
					String sInfo = "D...Â¨Â¡^.ÃˆÂ¡^.Ã°Â¡^.l).*.dll.Any file (*.*).*.*.Â�...........Ã¿Ã¿Ã¿Ã¿Ã¿Ã¿Ã¿Ã¿Ã¿Ã¿Ã¿Ã¿";
					symbolValueMemoryOperand.setText(new X86MemoryOperand(DataType.INT32, ((LongValueOld) x1).getValue()),
							sInfo);
				}

			} else if (funcName.startsWith("GetStdHandle")) {
				// DWORD nStdHandle // input, output, or error device
				Value x1 = symbolStack.pop();

				System.out.println("Argument:" + x1 + " ");

			} else if (funcName.startsWith("GetVersion")) {
				// This function has no parameters.

				System.out.println("Argument:" + "null");

			} else if (funcName.startsWith("HeapAlloc")) {
				/*
				 * HANDLE hHeap, // handle to the private heap block DWORD
				 * dwFlags, // heap allocation control flags DWORD dwBytes //
				 * number of bytes to allocate
				 */
				Value x1 = symbolStack.pop();
				Value x2 = symbolStack.pop();
				Value x3 = symbolStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " " + x3);
			} else if (funcName.startsWith("HeapCreate")) {
				/*
				 * DWORD flOptions, // heap allocation flag DWORD dwInitialSize,
				 * // initial heap size DWORD dwMaximumSize // maximum heap size
				 */
				Value x1 = symbolStack.pop();
				Value x2 = symbolStack.pop();
				Value x3 = symbolStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " " + x3);

				if (x1 instanceof LongValueOld && x2 instanceof LongValueOld && x3 instanceof LongValueOld) {
					long t1 = ((LongValueOld) x1).getValue();
					long t2 = ((LongValueOld) x2).getValue();
					long t3 = ((LongValueOld) x3).getValue();

					// String fileNameOld = symbolValueMemoryOperand.getText(new
					// X86MemoryOperand(DataType.INT32, t1));
					// String fileNameNew = symbolValueMemoryOperand.getText(new
					// X86MemoryOperand(DataType.INT32, t2));
					System.out.println("fOption:" + t1 + ", Initial Size:" + t2 + ", Maximum Size:" + t3);

					symbolValueRegister.mov("%eax", new LongValueOld(system.getHeapHandle().creatHeap(t2, t3, t1)));

				}
			} else if (funcName.startsWith("HeapDestroy")) {
				// HANDLE hHeap // handle to the heap
				Value x1 = symbolStack.pop();

				System.out.println("Argument:" + x1);

			} else if (funcName.startsWith("HeapFree")) {
				/*
				 * HANDLE hHeap, // handle to the heap DWORD dwFlags, // heap
				 * freeing flags LPVOID lpMem // pointer to the memory to free
				 */
				Value x1 = symbolStack.pop();
				Value x2 = symbolStack.pop();
				Value x3 = symbolStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " " + x3);

			} else if (funcName.startsWith("HeapReAlloc")) {
				/*
				 * HANDLE hHeap, // handle to a heap block DWORD dwFlags, //
				 * heap reallocation flags LPVOID lpMem, // pointer to the
				 * memory to reallocate DWORD dwBytes // number of bytes to
				 * reallocate
				 */
				Value x1 = symbolStack.pop();
				Value x2 = symbolStack.pop();
				Value x3 = symbolStack.pop();
				Value x4 = symbolStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4);
			} else if (funcName.startsWith("lstrcpyA")) {
				// LPTSTR lpString1, // address of buffer
				// LPCTSTR lpString2 // address of string to copy
				Value x1 = symbolStack.pop();
				Value x2 = symbolStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " ");

				if (x1 instanceof LongValueOld && x2 instanceof LongValueOld) {
					/*
					 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
					 * x1).getValueOperand(), ((ValueLongExp)
					 * x2).getValueOperand(), program);
					 */
					long dest = ((LongValueOld) x1).getValue();
					String src = symbolValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, ((LongValueOld) x2)
							.getValue()));
					System.out.println("Destination Address:" + dest + ", Source String:" + src);
					symbolValueMemoryOperand.setText(new X86MemoryOperand(DataType.INT32, dest), src);
					symbolValueRegister.mov("%eax", new LongValueOld(1));
				}

			} else if (funcName.startsWith("MessageBoxA")) {
				/*
				 * HWND hWnd, // handle of owner window LPCTSTR lpText, //
				 * address of text in message box LPCTSTR lpCaption, // address
				 * of title of message box UINT uType // style of message box
				 */
				Value x1 = symbolStack.pop();
				Value x2 = symbolStack.pop();
				Value x3 = symbolStack.pop();
				Value x4 = symbolStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4);
				System.out.print("Handle:" + x1.toString());
				if (x2 instanceof LongValueOld) {
					System.out.print(", Address of Text:"
							+ x2.toString()
							+ ", Text:"
							+ symbolValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, ((LongValueOld) x2)
									.getValue())));
				}

				if (x3 instanceof LongValueOld) {
					System.out.print(", Address of Title Text:"
							+ x3.toString()
							+ ", Title Text:"
							+ symbolValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, ((LongValueOld) x3)
									.getValue())));
				}

				System.out.println(", Style:" + x4.toString());
			} else if (funcName.startsWith("PeekMessageA")) {
				/*
				 * LPMSG lpMsg, // pointer to structure for message HWND hWnd,
				 * // handle to window UINT wMsgFilterMin, // first message UINT
				 * wMsgFilterMax, // last message UINT wRemoveMsg // removal
				 * flags
				 */
				Value x1 = symbolStack.pop();
				Value x2 = symbolStack.pop();
				Value x3 = symbolStack.pop();
				Value x4 = symbolStack.pop();
				Value x5 = symbolStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4 + " " + x5);

			} else if (funcName.startsWith("SetHandleCount")) {
				// UINT uNumber // number of file handles needed
				Value x1 = symbolStack.pop();

				System.out.println("Argument:" + x1 + " ");

			} else if (funcName.startsWith("VirtualAlloc")) {
				/*
				 * LPVOID lpAddress, // address of region to reserve or commit
				 * DWORD dwSize, // size of region DWORD flAllocationType, //
				 * type of allocation DWORD flProtect // type of access
				 * protection
				 */
				Value x1 = symbolStack.pop();
				Value x2 = symbolStack.pop();
				Value x3 = symbolStack.pop();
				Value x4 = symbolStack.pop();
				// Exp x5 = symbolStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4);
				if (x1 instanceof LongValueOld && x2 instanceof LongValueOld && x3 instanceof LongValueOld
						&& x4 instanceof LongValueOld) {
					long t1 = ((LongValueOld) x1).getValue();
					long t2 = ((LongValueOld) x2).getValue();
					long t3 = ((LongValueOld) x3).getValue();
					long t4 = ((LongValueOld) x4).getValue();

					// String fileName = symbolValueMemoryOperand.getText(new
					// X86MemoryOperand(DataType.INT32, t1));
					System.out.println("Address:" + t1 + ", Size:" + t2 + ", Allocation Type:" + t3
							+ ", Protection Type:" + t4);

					long t = system.getVirtualHandle().virtualAllocate(t1, t2, t3, t4);
					symbolValueRegister.mov("%eax", new LongValueOld(t));
				}
			} else if (funcName.startsWith("VirtualFree")) {
				/*
				 * LPVOID lpAddress, // address of region of committed pages
				 * DWORD dwSize, // size of region DWORD dwFreeType // type of
				 * free operation
				 */
				Value x1 = symbolStack.pop();
				Value x2 = symbolStack.pop();
				Value x3 = symbolStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " " + x3);

				if (x1 instanceof LongValueOld && x2 instanceof LongValueOld && x3 instanceof LongValueOld) {
					long t1 = ((LongValueOld) x1).getValue();
					long t2 = ((LongValueOld) x2).getValue();
					long t3 = ((LongValueOld) x3).getValue();

					// String fileName = symbolValueMemoryOperand.getText(new
					// X86MemoryOperand(DataType.INT32, t1));
					System.out.println("Base Address:" + t1 + ", Size:" + t2 + ", Free Type:" + t3);

					long t = system.getVirtualHandle().freeVirtual(t1, t2, t3);
					symbolValueRegister.mov("%eax", new LongValueOld(t));
				}

			} else // Insert new API
			if (funcName.startsWith("FindFirstFileA")) {
				Value x1 = symbolStack.pop();
				Value x2 = symbolStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " ");

				if (x1 instanceof LongValueOld && x2 instanceof LongValueOld) {
					long t1 = ((LongValueOld) x1).getValue();
					long t2 = ((LongValueOld) x2).getValue();

					String searchName = symbolValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, t1));
					System.out.println("Search File:" + searchName + ", Memory Operand:" + t2);

					/*
					 * symbolValueRegister.mov( "%eax", new
					 * ValueLongExp(system.getFileHandle()
					 * .findFirstFile(searchName, symbolValueMemoryOperand,
					 * t2)));
					 */
					long t = system.getFileHandle().findFirstFile(searchName, symbolValueMemoryOperand, t2);
					System.out.println("Search Handle:" + t);
					symbolValueRegister.mov("%eax", new LongValueOld(t));
				}

			} else if (funcName.startsWith("SetCurrentDirectoryA")) {
				Value x1 = symbolStack.pop();
				// long x2 = concreteStack.pop();

				System.out.println("Argument:" + x1);
				if (x1 instanceof LongValueOld) {
					long t1 = ((LongValueOld) x1).getValue();

					String path = symbolValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, t1));
					System.out.println("Path File:" + path);

					symbolValueRegister.mov("%eax", new LongValueOld(system.setPath(path)));
				}

			} else if (funcName.startsWith("FindNextFileA")) {
				Value x1 = symbolStack.pop();
				Value x2 = symbolStack.pop();

				System.out.println("Argument:" + x1 + " " + x2);

				if (x1 instanceof LongValueOld && x2 instanceof LongValueOld) {
					long t1 = ((LongValueOld) x1).getValue();
					long t2 = ((LongValueOld) x2).getValue();

					symbolValueRegister.mov("%eax",
							new LongValueOld(system.getFileHandle().findNextFile(t1, symbolValueMemoryOperand, t2)));
				}

			} else if (funcName.startsWith("CloseHandle")) {
				Value x1 = symbolStack.pop();

				System.out.println("Argument:" + x1);
				if (x1 instanceof LongValueOld) {
					long x = ((LongValueOld) x1).getValue();
					System.out.println("Object Handle:" + x1);

					long t = system.closeHandle(x);
					symbolValueRegister.mov("%eax", new LongValueOld(t));

					System.out.println("Return Value:" + t);
				}

			} else if (funcName.startsWith("CreateFileMappingA")) {
				Value x1 = symbolStack.pop();
				Value x2 = symbolStack.pop();
				Value x3 = symbolStack.pop();
				Value x4 = symbolStack.pop();
				Value x5 = symbolStack.pop();
				Value x6 = symbolStack.pop();
				// Exp x7 = symbolStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4 + " " + x5 + " " + x6);

				if (x1 instanceof LongValueOld && x2 instanceof LongValueOld && x3 instanceof LongValueOld
						&& x4 instanceof LongValueOld && x5 instanceof LongValueOld && x6 instanceof LongValueOld) {
					/*
					 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
					 * x1).getValueOperand(), ((ValueLongExp)
					 * x2).getValueOperand(), program);
					 */
					long t1 = ((LongValueOld) x1).getValue();
					long t2 = ((LongValueOld) x2).getValue();
					long t3 = ((LongValueOld) x3).getValue();
					long t4 = ((LongValueOld) x4).getValue();
					long t5 = ((LongValueOld) x5).getValue();
					long t6 = ((LongValueOld) x6).getValue();

					/*
					 * String fileMapName = symbolValueMemoryOperand
					 * .getText(new X86MemoryOperand(DataType.INT32, t6));
					 */
					// String str = symbolValueMemoryOperand.getText(new
					// X86MemoryOperand(DataType.INT32, t2));
					System.out.println("Handle File:" + t1 + ", Security Attribute:" + t2 + ", Object Protection:" + t3
							+ ", High-Order:" + t4 + ", High-Order:" + t5 + ", File Mapping Name Address:" + t6);
					long t = system.getFileHandle().createFileMapping(t1, t2, t3, t4, t5, t6);
					symbolValueRegister.mov("%eax", new LongValueOld(t));
					System.out.println("Return Value:" + t);
				}

			} else if (funcName.startsWith("CreateFileA")) {
				Value x1 = symbolStack.pop();
				Value x2 = symbolStack.pop();
				Value x3 = symbolStack.pop();
				Value x4 = symbolStack.pop();
				Value x5 = symbolStack.pop();
				Value x6 = symbolStack.pop();
				Value x7 = symbolStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4 + " " + x5 + " " + x6 + " " + x7);

				if (x1 instanceof LongValueOld && x2 instanceof LongValueOld && x3 instanceof LongValueOld
						&& x4 instanceof LongValueOld && x5 instanceof LongValueOld && x6 instanceof LongValueOld
						&& x7 instanceof LongValueOld) {
					long t1 = ((LongValueOld) x1).getValue();
					long t2 = ((LongValueOld) x2).getValue();
					long t3 = ((LongValueOld) x3).getValue();
					long t4 = ((LongValueOld) x4).getValue();
					long t5 = ((LongValueOld) x5).getValue();
					long t6 = ((LongValueOld) x6).getValue();
					long t7 = ((LongValueOld) x7).getValue();
					String fileName = symbolValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, t1));
					System.out.println("FileName:" + fileName + ", Access:" + t2 + ", ShareMode:" + t3 + ", pSecurity:"
							+ t4 + ", Mode:" + t5 + ", Attributes:" + t6 + ", hTemplate:" + t7);

					long t = system.createFile(fileName, t2, t3, t4, t5, t6, t7);
					symbolValueRegister.mov("%eax", new LongValueOld(t));
					System.out.println("Return value:" + t);

				}

			} else if (funcName.startsWith("SetFileAttributesA")) {
				Value x1 = symbolStack.pop();
				Value x2 = symbolStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " ");
				if (x1 instanceof LongValueOld && x2 instanceof LongValueOld) {
					long t1 = ((LongValueOld) x1).getValue();
					long t2 = ((LongValueOld) x2).getValue();

					String fileName = symbolValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, t1));
					System.out.println("FileName:" + fileName + ", Attribute:" + t2);

					symbolValueRegister.mov("%eax",
							new LongValueOld(system.getFileHandle().setFileAttribute(fileName, t2)));
					long t = system.getFileHandle().setFileAttribute(fileName, t2);
					System.out.println("Return Value:" + t);
				}

			} else if (funcName.startsWith("GetCurrentDirectoryA")) {
				Value x1 = symbolStack.pop();
				Value x2 = symbolStack.pop();
				System.out.println("Argument: Length:" + x1 + ", Memory Operand:" + x2);
				if (x1 instanceof LongValueOld && x2 instanceof LongValueOld) {
					String curDir = "C:/Test";
					long size = symbolValueMemoryOperand.setText(
							new X86MemoryOperand(DataType.INT32, ((LongValueOld) x2).getValue()), curDir,
							((LongValueOld) x1).getValue());
					System.out.println("Current Directory:" + curDir);
					symbolValueRegister.mov("%eax", new LongValueOld(size));

					// System.out.println("Result " + funcName + ":" +
					// symbolValueMemoryOperand.getText(new
					// X86MemoryOperand(DataType.INT32,
					// ((ValueLongExp)x2).getValueOperand()), size) + "!");
				}

			} else if (funcName.startsWith("GetSystemDirectoryA")) {
				Value x2 = symbolStack.pop();
				Value x1 = symbolStack.pop();

				System.out.println("Argument: Length:" + x1 + ", Memory Operand:" + x2);
				if (x1 instanceof LongValueOld && x2 instanceof LongValueOld) {
					String curDir = "C:/Windows/system32";
					long size = symbolValueMemoryOperand.setText(
							new X86MemoryOperand(DataType.INT32, ((LongValueOld) x2).getValue()), curDir,
							((LongValueOld) x1).getValue());
					System.out.println("System Directory:" + curDir);
					symbolValueRegister.mov("%eax", new LongValueOld(size));

					// System.out.println("Result GetSystemDirectory:" +
					// symbolValueMemoryOperand.getText(new
					// X86MemoryOperand(DataType.INT32,
					// ((ValueLongExp)x2).getValueOperand()), size) + "!");
				}

			} else if (funcName.startsWith("GetWindowsDirectoryA")) {
				Value x2 = symbolStack.pop();
				Value x1 = symbolStack.pop();
				System.out.println("Argument: Length:" + x1 + ", Memory Operand:" + x2);
				if (x1 instanceof LongValueOld && x2 instanceof LongValueOld) {
					String curDir = "C:/Windows";
					System.out.println("WindowDirectory:" + curDir);
					long size = symbolValueMemoryOperand.setText(
							new X86MemoryOperand(DataType.INT32, ((LongValueOld) x2).getValue()), curDir,
							((LongValueOld) x1).getValue());
					symbolValueRegister.mov("%eax", new LongValueOld(size));

					// System.out.println("Result " + funcName + ":" +
					// symbolValueMemoryOperand.getText(new
					// X86MemoryOperand(DataType.INT32,
					// ((ValueLongExp)x2).getValueOperand()), size) + "!");
				}

			} else if (funcName.startsWith("GetModuleHandleA")) {
				Value lpModuleName = symbolStack.pop();

				if (lpModuleName instanceof LongValueOld) {
					if (((LongValueOld) lpModuleName).getValue() == 0) {
						returnValue = APIHandler.getModuleHandleA(0, program);
						symbolValueRegister.mov("%eax", new LongValueOld(returnValue));
						// symbolStack.pop();
					}
				}
			} else if (funcName.startsWith("GetModuleFileNameA")) {

				Value hModule = symbolStack.pop();
				Value lpFilename = symbolStack.pop();
				Value nSize = symbolStack.pop();
				System.out.println("Argument:" + hModule.toString() + " " + lpFilename.toString() + " "
						+ nSize.toString());

				if (hModule instanceof LongValueOld && lpFilename instanceof LongValueOld && nSize instanceof LongValueOld) {
					/*
					 * long returnValue = APIHandler.getModuleFileNameA(
					 * ((ValueLongExp) hModule).getValueOperand(),
					 * ((ValueLongExp) lpFilename).getValueOperand(),
					 * ((ValueLongExp) nSize).getValueOperand(), program);
					 */
					String s = "C:/Windows/" + program.getFileName();
					symbolValueMemoryOperand.setText(
							new X86MemoryOperand(DataType.INT32, ((LongValueOld) lpFilename).getValue()), s);
					symbolValueRegister.mov("%eax", new LongValueOld(s.length()));

					// System.out.println("Result " + funcName + ":" +
					// symbolValueMemoryOperand.getText(new
					// X86MemoryOperand(DataType.INT32,
					// ((ValueLongExp)lpFilename).getValueOperand()),
					// s.length()) +
					// "!");
					// symbolStack.pop();

				}

			}

		} else if (inst.getName().toString().equals("jmp")) {
			System.out.println("JMP Symbolic Execution of API:" + funcName);
			/*
			 * Exp retVal = symbolStack.pop(); if
			 * (funcName.startsWith("GetModuleHandleA")) { Exp lpModuleName =
			 * symbolStack.pop();
			 * 
			 * if (lpModuleName instanceof ValueLongExp) { if (((ValueLongExp)
			 * lpModuleName).getValueOperand() == 0) { long returnValue =
			 * APIHandler.getModuleHandleA(0, program);
			 * symbolValueRegister.mov("%eax", new ValueLongExp( returnValue));
			 * // symbolStack.pop(); } } } else if
			 * (funcName.startsWith("GetModuleFileNameA")) {
			 * 
			 * Exp hModule = symbolStack.pop(); Exp lpFilename =
			 * symbolStack.pop(); Exp nSize = symbolStack.pop();
			 * 
			 * if (hModule instanceof ValueLongExp && lpFilename instanceof
			 * ValueLongExp && nSize instanceof ValueLongExp) { long returnValue
			 * = APIHandler.getModuleFileNameA( ((ValueLongExp)
			 * hModule).getValueOperand(), ((ValueLongExp)
			 * lpFilename).getValueOperand(), ((ValueLongExp)
			 * nSize).getValueOperand(), program);
			 * symbolValueRegister.mov("%eax", new ValueLongExp( returnValue));
			 * // symbolStack.pop();
			 * 
			 * }
			 * 
			 * } symbolStack.push(retVal);
			 */
		}

		// Giai thuat Look Ahead
		Value v = symbolValueRegister.getRegVal("%eax");
		AddressList l = se.getAddrTraceList().clone();

		if (checkCondLookAhead(l, se.getAssemblyMap(), se.getNeg()) == 1 && v instanceof LongValueOld) {
			long v1 = ((LongValueOld) v).getValue();
			if (v1 == 0) {
				symbolValueRegister.mov("%eax", new LongValueOld(1));
				se.getPcValue().add("api_" + funcName, 0);
				se.getFormulas().add(new Formula(new SymbolExp("api_" + funcName), new LongValueOld(1), "="));
			}

		} else if (checkCondLookAhead(l, se.getAssemblyMap(), se.getNeg()) == 1 && v instanceof LongValueOld) {
			long v1 = ((LongValueOld) v).getValue();
			if (v1 != 0) {
				symbolValueRegister.mov("%eax", new LongValueOld(0));
				se.getPcValue().add("api_" + funcName, 0);
				se.getFormulas().add(new Formula(new SymbolExp("api_" + funcName), new LongValueOld(0), "="));
			}
		}

		return ret;
	}

	private static int checkCondLookAhead(AddressList l, Map<AbsoluteAddress, Instruction> map,
			Map<AbsoluteAddress, AbsoluteAddress> neg) {
		if (l.length() == 2) {
			AbsoluteAddress t1 = l.pop();
			AbsoluteAddress t2 = l.pop();
			// AbsoluteAddress t3 = l.pop();
			// AbsoluteAddress t4 = l.pop();
			Instruction i1 = map.get(t1);
			// Instruction i2 = map.get(t2);

			if (i1 instanceof X86CondJmpInstruction) {
				boolean reCond = false;

				if (neg != null && !neg.isEmpty() && neg.containsKey(t1) && neg.get(t1).getValue() == t2.getValue()) {
					// instName = reverseConditionJump(instName);
					reCond = true;
					// System.out.println("Reverse Conditional Jump:" +
					// instName);
				}

				if (i1.getName().equals("je")) {
					if (reCond) {
						return 0;
					} else {
						return 1;
					}
				}

				if (i1.getName().equals("jne")) {
					if (reCond) {
						return 1;
					} else {
						return 0;
					}
				}
			}
		} else if (l.length() >= 2) {
			AbsoluteAddress t1 = l.pop();
			AbsoluteAddress t2 = l.pop();
			AbsoluteAddress t3 = l.pop();
			// AbsoluteAddress t4 = l.pop();
			Instruction i1 = map.get(t1);
			Instruction i2 = map.get(t2);

			if (i2 instanceof X86CondJmpInstruction) {
				boolean reCond = false;

				if (neg != null && !neg.isEmpty() && neg.containsKey(t1) && neg.get(t2).getValue() == t3.getValue()) {
					// instName = reverseConditionJump(instName);
					reCond = true;
					// System.out.println("Reverse Conditional Jump:" +
					// instName);
				}

				if (i1.getName().equals("je")) {
					if (reCond) {
						return 0;
					} else {
						return 1;
					}
				}

				if (i1.getName().equals("jne")) {
					if (reCond) {
						return 1;
					} else {
						return 0;
					}
				}
			} else if (i1 instanceof X86CondJmpInstruction) {
				boolean reCond = false;

				if (neg != null && !neg.isEmpty() && neg.containsKey(t1) && neg.get(t1).getValue() == t2.getValue()) {
					// instName = reverseConditionJump(instName);
					reCond = true;
					// System.out.println("Reverse Conditional Jump:" +
					// instName);
				}

				if (i1.getName().equals("je")) {
					if (reCond) {
						return 0;
					} else {
						return 1;
					}
				}

				if (i1.getName().equals("jne")) {
					if (reCond) {
						return 1;
					} else {
						return 0;
					}
				}
			}
		}

		// TODO Auto-generated method stub
		return 3;
	}

	public static boolean executeConcrete(String funcName, ConcreteExecution ce, Instruction inst) {
		// TODO Auto-generated method stub
		// FormulaSet formulaList = ce.getFormulas();
		// ConcreteFlag concreteFlag = ce.getConcreteFlag();
		ConcreteStack concreteStack = ce.getConcreteStack();
		ConcreteValueMemoryOperand concreteValueMemoryOperand = ce.getConcreteValueMemoryOperand();
		ConcreteValueRegister concreteValueRegister = ce.getConcreteValueRegister();
		// ConcreteValueRegisterPart concreteValueRegisterPart =
		// ce.getConcreteValueRegisterPart();
		// ConcreteValueSegment concreteValueSegment =
		// ce.getConcreteValueSegment();
		Program program = ce.getProgram();
		SystemHandle system = ce.getSystem();

		boolean ret = true;
		long returnValue;

		if (inst.getName().toString().equals("call")) {
			System.out.println("Call Concrete Execution of API:" + funcName);
			if (funcName.startsWith("WinExec")) {
				// HMODULE hModule, handle to DLL module
				// LPCSTR lpProcName name of function
				long x1 = concreteStack.pop();
				long x2 = concreteStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " ");

				String commandLine = concreteValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, x1));
				System.out.println("Command Line:" + commandLine + ", Window Style:" + x2);
				concreteValueRegister.mov("%eax", system.getWindowHandle().createWindow(commandLine, x2));
			} else if (funcName.startsWith("CreateProcess")) {
				/*
				 * HANDLE hFile, // handle to file to write to LPCVOID lpBuffer,
				 * // pointer to data to write to file DWORD
				 * nNumberOfBytesToWrite, // number of bytes to write LPDWORD
				 * lpNumberOfBytesWritten, // pointer to number of bytes written
				 * LPOVERLAPPED lpOverlapped // pointer to structure needed for
				 * overlapped I/O
				 */
				long x1 = concreteStack.pop();
				long x2 = concreteStack.pop();
				long x3 = concreteStack.pop();
				long x4 = concreteStack.pop();
				long x5 = concreteStack.pop();
				long x6 = concreteStack.pop();
				long x7 = concreteStack.pop();
				long x8 = concreteStack.pop();
				long x9 = concreteStack.pop();
				long x10 = concreteStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4 + " " + x5 + " " + x6 + " " + x7
						+ " " + x8 + " " + x9 + " " + x10);

				String moduleFileName = concreteValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, x1));
				String commandLine = concreteValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, x2));
				String curDir = concreteValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, x8));
				String pStarupInfo = concreteValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, x9));
				System.out.println("Module File Name:" + moduleFileName + ", Command Line:" + commandLine
						+ ", Process Attribute Security:" + x3 + ", Thread Attribute Security:" + x4 + ", Handle Flag:"
						+ x5 + ", Creation Flag:" + x6 + ", Environemnt Block:" + x7 + ", Current Directory:" + curDir
						+ ", Process Starup Info:" + pStarupInfo + ", Process Information:" + x10);
				concreteValueRegister.mov(
						"%eax",
						system.getProcessHandle().createProcess(moduleFileName, commandLine, x3, x4, x5, x6, x7,
								curDir, pStarupInfo, x10));

			} else if (funcName.startsWith("GetFileAttributes")) {
				// HANDLE hFindFile // file search handle
				long x1 = concreteStack.pop();
				System.out.println("Argument:" + x1);

				String fName = concreteValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, x1));

				concreteValueRegister.mov("%eax", system.getFileAttribute(fName));

			} else if (funcName.startsWith("GetProcAddress")) {
				// HMODULE hModule, handle to DLL module
				// LPCSTR lpProcName name of function
				long x1 = concreteStack.pop();
				long x2 = concreteStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " ");

				String functionName = concreteValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, x2));
				System.out.println("Function Name:" + functionName + ", Library Handle:" + x1);

				concreteValueRegister.mov("%eax", system.getProcAddress(x1, functionName));

			} else if (funcName.startsWith("LoadLibraryA")) {
				// LPCTSTR lpLibFileName // address of filename of executable
				// module
				long x1 = concreteStack.pop();
				System.out.println("Argument:" + x1);

				String libraryName = concreteValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, x1));
				System.out.println(" Library Name:" + libraryName);

				concreteValueRegister.mov("%eax", system.getLibraryHandle(libraryName));

			} else if (funcName.startsWith("SetFilePointer")) {
				/*
				 * HANDLE hFile, // handle of file LONG lDistanceToMove, //
				 * number of bytes to move file pointer PLONG
				 * lpDistanceToMoveHigh, // address of high-order word of
				 * distance to move DWORD dwMoveMethod // how to move
				 */
				long x1 = concreteStack.pop();
				long x2 = concreteStack.pop();
				long x3 = concreteStack.pop();
				long x4 = concreteStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4 + " ");

				System.out.println("Handle File:" + x1 + ", Number of Bytes:" + x2 + ", Address of High-Order:" + x3
						+ ", Move Type:" + x4);
				concreteValueRegister.mov("%eax", system.getFileHandle().setFilePointer(x1, x2, x3, x4));

			} else if (funcName.startsWith("FindClose")) {
				// HANDLE hFindFile // file search handle
				long x1 = concreteStack.pop();
				System.out.println("Argument:" + x1);

				concreteValueRegister.mov("%eax", system.getFileHandle().closeFind(x1));

			} else if (funcName.startsWith("DeleteFileA")) {
				// LPCTSTR lpFileName // pointer to name of file to delete
				long x1 = concreteStack.pop();
				System.out.println("Argument:" + x1);

				String fName = concreteValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, x1));
				concreteValueRegister.mov("%eax", system.getFileHandle().deleteFile(fName));

			} else if (funcName.startsWith("GetSystemTime")) {
				// LPSYSTEMTIME lpSystemTime // address of system time structure
				long x1 = concreteStack.pop();
				System.out.println("MemoryOperand:" + x1);

				concreteValueMemoryOperand.setText(new X86MemoryOperand(DataType.INT32, x1), "AnyTime");
				// concreteValueMemoryOperand.setText(new
				// X86MemoryOperand(DataType.INT32, x1),
				// System.currentTimeMillis());

			} else if (funcName.startsWith("GetVersionExA")) {
				// This function has no parameters.
				long verNum = 498139398;
				System.out.println("Version Number:" + verNum);

				concreteValueRegister.mov("%eax", verNum);
			} else if (funcName.startsWith("MapViewOfFile")) {
				/*
				 * HANDLE hFileMappingObject, // file-mapping object to map into
				 * address space DWORD dwDesiredAccess, // access mode DWORD
				 * dwFileOffsetHigh, // high-order 32 bits of file offset DWORD
				 * dwFileOffsetLow, // low-order 32 bits of file offset DWORD
				 * dwNumberOfBytesToMap // number of bytes to map
				 */
				long x1 = concreteStack.pop();
				long x2 = concreteStack.pop();
				long x3 = concreteStack.pop();
				long x4 = concreteStack.pop();
				long x5 = concreteStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4 + " " + x5);

				// concreteValueRegister.mov("%eax", 1);
				System.out.println("Handle File:" + x1 + ", Access Mode:" + x2 + ", High-order 32 bits of file offset:"
						+ x3 + ", Low-order 32 bits of file offset :" + x4 + ", Number of bytes to map:" + x5);
				long v = system.getFileHandle().mapViewOfFile(x1, x2, x3, x4, x5);
				System.out.println("Base Address: " + v);
				concreteValueRegister.mov("%eax", v);

			} else if (funcName.startsWith("ReadFile")) {
				/*
				 * HANDLE hFile, // handle of file to read LPVOID lpBuffer, //
				 * address of buffer that receives data DWORD
				 * nNumberOfBytesToRead, // number of bytes to read LPDWORD
				 * lpNumberOfBytesRead, // address of number of bytes read
				 * LPOVERLAPPED lpOverlapped // address of structure for data
				 */
				long x1 = concreteStack.pop();
				long x2 = concreteStack.pop();
				long x3 = concreteStack.pop();
				long x4 = concreteStack.pop();
				long x5 = concreteStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4 + " " + x5);

				System.out.println("Handle File:" + x1 + ", Address of Buffer:" + x2 + ", Number of Byte:" + x3
						+ ", Number of Actual Read Bytes:" + x4 + ", Overlapped:" + x5);
				String str = system.getFileHandle().readFile(x1, x2, x3, x4, x5);
				if (str != null) {
					concreteValueMemoryOperand.setText(new X86MemoryOperand(DataType.INT32, x2), str);
					concreteValueRegister.mov("%eax", 1);
				} else {
					concreteValueRegister.mov("%eax", 0);
				}

			} else if (funcName.startsWith("UnMapViewOfFile")) {
				// LPCVOID lpBaseAddress // address where mapped view begins
				long x1 = concreteStack.pop();
				System.out.println("Argument:" + x1);

			} else if (funcName.startsWith("WriteFile")) {
				/*
				 * HANDLE hFile, // handle to file to write to LPCVOID lpBuffer,
				 * // pointer to data to write to file DWORD
				 * nNumberOfBytesToWrite, // number of bytes to write LPDWORD
				 * lpNumberOfBytesWritten, // pointer to number of bytes written
				 * LPOVERLAPPED lpOverlapped // pointer to structure needed for
				 * overlapped I/O
				 */
				long x1 = concreteStack.pop();
				long x2 = concreteStack.pop();
				long x3 = concreteStack.pop();
				long x4 = concreteStack.pop();
				long x5 = concreteStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4 + " " + x5);

				String str = concreteValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, x2));
				System.out.println("Handle File:" + x1 + ", String written:" + str + ", Number of Byte:" + x3
						+ ", Pointer:" + x4 + ", Overlapped:" + x5);
				concreteValueRegister.mov("%eax", system.getFileHandle().writeFile(x1, str, x3, x4, x5));

			} else if (funcName.startsWith("RegSetValueExA")) {
				/*
				 * HKEY hKey, // handle of key to set value for LPCTSTR
				 * lpValueName, // address of value to set DWORD Reserved, //
				 * reserved DWORD dwType, // flag for value type CONST BYTE
				 * *lpData, // address of value data DWORD cbData // size of
				 * value data
				 */

				long x1 = concreteStack.pop();
				long x2 = concreteStack.pop();
				long x3 = concreteStack.pop();
				long x4 = concreteStack.pop();
				long x5 = concreteStack.pop();
				long x6 = concreteStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4 + " " + x5 + " " + x6);

			} else if (funcName.startsWith("SetEndOfFile")) {
				// HANDLE hFile // handle of file whose EOF is to be set
				long x1 = concreteStack.pop();

				System.out.println("Argument:" + x1);
				System.out.println("Handle of file:" + x1);
				concreteValueRegister.mov("%eax", system.getFileHandle().setEndOfFile(x1));

			} else if (funcName.startsWith("WaitForSingleObject")) {
				/*
				 * HANDLE hHandle, // handle of object to wait for DWORD
				 * dwMilliseconds // time-out interval in milliseconds
				 */
				long x1 = concreteStack.pop();
				long x2 = concreteStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " ");

			} else if (funcName.startsWith("PostMessageA")) {
				/*
				 * HWND hWnd, // handle of destination window UINT Msg, //
				 * message to post WPARAM wParam, // first message parameter
				 * LPARAM lParam // second message parameter
				 */
				long x1 = concreteStack.pop();
				long x2 = concreteStack.pop();
				long x3 = concreteStack.pop();
				long x4 = concreteStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4);
				String str = concreteValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, x2));
				System.out.println("Handle Window:" + x1 + ", Post Message:" + str + ", First Param:" + x3
						+ ", Second Param:" + x4);
				concreteValueRegister.mov("%eax", system.getWindowHandle().postMessage(x1, str, x3, x4));

			} else if (funcName.startsWith("lstrcatA")) {
				// LPTSTR lpString1, // address of buffer for concatenated
				// strings
				// LPCTSTR lpString2 // address of string to add to string1
				long x1 = concreteStack.pop();
				long x2 = concreteStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " ");

				String dest = concreteValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, x1));
				String src = concreteValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, x2));
				dest = dest.concat(src);
				System.out.println("Destination Address:" + x1 + ", Source String:" + src);
				concreteValueMemoryOperand.setText(new X86MemoryOperand(DataType.INT32, x1), dest);
				concreteValueRegister.mov("%eax", 1);

			} else if (funcName.startsWith("lstrcmpA")) {
				// LPCTSTR lpString1, // address of first string
				// LPCTSTR lpString2 // address of second string
				long x1 = concreteStack.pop();
				long x2 = concreteStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " ");

				String dest = concreteValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, x1));
				String src = concreteValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, x2));
				// dest += src;
				System.out.println("Destination String:" + dest + ", Source String:" + src);
				// concreteValueMemoryOperand.setText(new
				// X86MemoryOperand(DataType.INT32, x1), dest);
				concreteValueRegister.mov("%eax", dest.compareTo(src));

			} else if (funcName.startsWith("lstrlenA")) {
				// LPCTSTR lpString // address of string to count
				long x1 = concreteStack.pop();
				// long x2 = concreteStack.pop();
				System.out.println("Argument:" + x1);

			} else if (funcName.startsWith("MoveFileA")) {
				// LPCTSTR lpExistingFileName, // address of name of the
				// existing file
				// LPCTSTR lpNewFileName // address of new name for the file
				long x1 = concreteStack.pop();
				long x2 = concreteStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " ");

				String fileNameOld = concreteValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, x1));
				String fileNameNew = concreteValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, x2));
				System.out.println("Old File:" + fileNameOld + ", New File:" + fileNameNew);

				concreteValueRegister.mov("%eax", system.getFileHandle().moveFile(fileNameOld, fileNameNew));

			} else if (funcName.startsWith("RegCloseKey")) {
				// HKEY hKey // handle of key to close
				long x1 = concreteStack.pop();
				System.out.println("Argument:" + x1 + " ");

			} else if (funcName.startsWith("RegOpenKeyExA")) {
				/*
				 * HKEY hKey, // handle of open key LPCTSTR lpSubKey, // address
				 * of name of subkey to open DWORD ulOptions, // reserved REGSAM
				 * samDesired, // security access mask PHKEY phkResult //
				 * address of handle of open key
				 */

				long x1 = concreteStack.pop();
				long x2 = concreteStack.pop();
				long x3 = concreteStack.pop();
				long x4 = concreteStack.pop();
				long x5 = concreteStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4 + " " + x5);

			} else if (funcName.startsWith("SendMessageA")) {
				/*
				 * HWND hWnd, // handle of destination window UINT Msg, //
				 * message to send WPARAM wParam, // first message parameter
				 * LPARAM lParam // second message parameter
				 */

				long x1 = concreteStack.pop();
				long x2 = concreteStack.pop();
				long x3 = concreteStack.pop();
				long x4 = concreteStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4);

				String msg = concreteValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, x2));

				System.out.println("Window Handle:" + x1 + ", Message Sent:" + msg + ", First Param:" + x3
						+ ", Second Param:" + x4);
				concreteValueRegister.mov("%eax", system.getWindowHandle().sendMessage(x1, msg, x3, x4));
			} else
			// insert API them
			if (funcName.startsWith("CopyFileA")) {
				/*
				 * LPCTSTR lpExistingFileName, // pointer to name of an existing
				 * file LPCTSTR lpNewFileName, // pointer to filename to copy to
				 * BOOL bFailIfExists // flag for operation if file exists
				 */
				long x1 = concreteStack.pop();
				long x2 = concreteStack.pop();
				long x3 = concreteStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " " + x3);

				String fileNameOld = concreteValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, x1));
				String fileNameNew = concreteValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, x2));
				System.out.println("Old File:" + fileNameOld + ", New File:" + fileNameNew + ", Flag:" + x3);

				concreteValueRegister.mov("%eax", system.getFileHandle().copyFile(fileNameOld, fileNameNew, x3));

			} else if (funcName.startsWith("CreateThread")) {
				/*
				 * LPSECURITY_ATTRIBUTES lpThreadAttributes, // pointer to
				 * thread security attributes DWORD dwStackSize, // initial
				 * thread stack size, in bytes LPTHREAD_START_ROUTINE
				 * lpStartAddress, // pointer to thread function LPVOID
				 * lpParameter, // argument for new thread DWORD
				 * dwCreationFlags, // creation flags LPDWORD lpThreadId //
				 * pointer to returned thread identifier
				 */
				long x1 = concreteStack.pop();
				long x2 = concreteStack.pop();
				long x3 = concreteStack.pop();
				long x4 = concreteStack.pop();
				long x5 = concreteStack.pop();
				long x6 = concreteStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4 + " " + x5 + " " + x6);

			} else if (funcName.startsWith("ExitProcess")) {
				// UINT uExitCode // exit code for all threads
				long x1 = concreteStack.pop();

				System.out.println("Argument:" + x1);

			} else if (funcName.startsWith("FindWindowA")) {
				// LPCTSTR lpClassName, // pointer to class name
				// LPCTSTR lpWindowName // pointer to window name
				long x1 = concreteStack.pop();
				long x2 = concreteStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " ");

				String className = concreteValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, x1));
				System.out.println("Command Line:" + className + ", Window Name:" + x2);
				concreteValueRegister.mov("%eax", system.getWindowHandle().findWindow(className, x2));

			} else if (funcName.startsWith("FreeEnvironmentStringsA")) {
				// LPTSTR lpszEnvironmentBlock // pointer to a block of
				// environment strings
				long x1 = concreteStack.pop();
				System.out.println("Argument:" + x1);

			} else if (funcName.startsWith("FreeEnvironmentStringsW")) {
				// LPTSTR lpszEnvironmentBlock // pointer to a block of
				// environment strings
				long x1 = concreteStack.pop();

				System.out.println("Argument:" + x1);

			} else if (funcName.startsWith("GetCommandLineA")) {
				// This function has no parameters.
				long disp = 4796200;
				String commandLine = "C:/Windows/" + program.getFileName();
				System.out.println("Argument MemoryOperand:" + disp + ", Command Line:" + commandLine);
				concreteValueMemoryOperand.setText(new X86MemoryOperand(DataType.INT32, disp), commandLine);

			} else if (funcName.startsWith("GetEnvironmentStrings")) {
				// This function has no parameters.

				System.out.println("Argument:" + "null");

			} else if (funcName.startsWith("GetEnvironmentStringsW")) {
				// This function has no parameters.

				System.out.println("Argument:" + "null");

			} else if (funcName.startsWith("GetFileType")) {
				// HANDLE hFile // file handle
				long x1 = concreteStack.pop();
				System.out.println("Argument:" + x1);

			} else if (funcName.startsWith("GetLastError")) {
				// This function has no parameters.

				long verNum = 14007;
				System.out.println("Last Error:" + verNum);

				concreteValueRegister.mov("%eax", verNum);

			} else if (funcName.startsWith("GetStartupInfoA")) {
				// LPSTARTUPINFO lpStartupInfo // address of STARTUPINFO
				// structure
				long x1 = concreteStack.pop();

				System.out.println("Argument:" + x1);
				String sInfo = "D...Â¨Â¡^.ÃˆÂ¡^.Ã°Â¡^.l).*.dll.Any file (*.*).*.*.Â�...........Ã¿Ã¿Ã¿Ã¿Ã¿Ã¿Ã¿Ã¿Ã¿Ã¿Ã¿Ã¿";
				concreteValueMemoryOperand.setText(new X86MemoryOperand(DataType.INT32, x1), sInfo);

			} else if (funcName.startsWith("GetStdHandle")) {
				// DWORD nStdHandle // input, output, or error device
				long x1 = concreteStack.pop();

				System.out.println("Argument:" + x1 + " ");

			} else if (funcName.startsWith("GetVersion")) {
				// This function has no parameters.

				System.out.println("Argument:" + "null");

			} else if (funcName.startsWith("HeapAlloc")) {
				/*
				 * HANDLE hHeap, // handle to the private heap block DWORD
				 * dwFlags, // heap allocation control flags DWORD dwBytes //
				 * number of bytes to allocate
				 */
				long x1 = concreteStack.pop();
				long x2 = concreteStack.pop();
				long x3 = concreteStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " " + x3);
			} else if (funcName.startsWith("HeapCreate")) {
				/*
				 * DWORD flOptions, // heap allocation flag DWORD dwInitialSize,
				 * // initial heap size DWORD dwMaximumSize // maximum heap size
				 */
				long x1 = concreteStack.pop();
				long x2 = concreteStack.pop();
				long x3 = concreteStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " " + x3);

				System.out.println("fOption:" + x1 + ", Initial Size:" + x2 + ", Maximum Size:" + x3);

				concreteValueRegister.mov("%eax", system.getHeapHandle().creatHeap(x2, x3, x1));
			} else if (funcName.startsWith("HeapDestroy")) {
				// HANDLE hHeap // handle to the heap
				long x1 = concreteStack.pop();

				System.out.println("Argument:" + x1);

			} else if (funcName.startsWith("HeapFree")) {
				/*
				 * HANDLE hHeap, // handle to the heap DWORD dwFlags, // heap
				 * freeing flags LPVOID lpMem // pointer to the memory to free
				 */
				long x1 = concreteStack.pop();
				long x2 = concreteStack.pop();
				long x3 = concreteStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " " + x3);

			} else if (funcName.startsWith("HeapReAlloc")) {
				/*
				 * HANDLE hHeap, // handle to a heap block DWORD dwFlags, //
				 * heap reallocation flags LPVOID lpMem, // pointer to the
				 * memory to reallocate DWORD dwBytes // number of bytes to
				 * reallocate
				 */
				long x1 = concreteStack.pop();
				long x2 = concreteStack.pop();
				long x3 = concreteStack.pop();
				long x4 = concreteStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4);
			} else if (funcName.startsWith("lstrcpyA")) {
				// LPTSTR lpString1, // address of buffer
				// LPCTSTR lpString2 // address of string to copy
				long x1 = concreteStack.pop();
				long x2 = concreteStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " ");

				// long dest = ((ValueLongExp) x1).getValueOperand();
				String src = concreteValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, x2));
				System.out.println("Destination Address:" + x1 + ", Source String:" + src);
				concreteValueMemoryOperand.setText(new X86MemoryOperand(DataType.INT32, x1), src);
				concreteValueRegister.mov("%eax", 1);

			} else if (funcName.startsWith("MessageBoxA")) {
				/*
				 * HWND hWnd, // handle of owner window LPCTSTR lpText, //
				 * address of text in message box LPCTSTR lpCaption, // address
				 * of title of message box UINT uType // style of message box
				 */
				long x1 = concreteStack.pop();
				long x2 = concreteStack.pop();
				long x3 = concreteStack.pop();
				long x4 = concreteStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4);

				System.out.print(", Address of Text:" + x2 + ", Text:"
						+ concreteValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, x2)));

				System.out.print(", Address of Title Text:" + x3 + ", Title Text:"
						+ concreteValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, x3)));

				System.out.println(", Style:" + x4);

			} else if (funcName.startsWith("PeekMessageA")) {
				/*
				 * LPMSG lpMsg, // pointer to structure for message HWND hWnd,
				 * // handle to window UINT wMsgFilterMin, // first message UINT
				 * wMsgFilterMax, // last message UINT wRemoveMsg // removal
				 * flags
				 */
				long x1 = concreteStack.pop();
				long x2 = concreteStack.pop();
				long x3 = concreteStack.pop();
				long x4 = concreteStack.pop();
				long x5 = concreteStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4 + " " + x5);

			} else if (funcName.startsWith("SetHandleCount")) {
				// UINT uNumber // number of file handles needed
				long x1 = concreteStack.pop();

				System.out.println("Argument:" + x1 + " ");

			} else if (funcName.startsWith("VirtualAlloc")) {
				/*
				 * LPVOID lpAddress, // address of region to reserve or commit
				 * DWORD dwSize, // size of region DWORD flAllocationType, //
				 * type of allocation DWORD flProtect // type of access
				 * protection
				 */
				long x1 = concreteStack.pop();
				long x2 = concreteStack.pop();
				long x3 = concreteStack.pop();
				long x4 = concreteStack.pop();
				// long x5 = concreteStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4);

				System.out.println("Address:" + x1 + ", Size:" + x2 + ", Allocation Type:" + x3 + ", Protection Type:"
						+ x4);
				long t = system.getVirtualHandle().virtualAllocate(x1, x2, x3, x4);
				concreteValueRegister.mov("%eax", t);

			} else if (funcName.startsWith("VirtualFree")) {
				/*
				 * LPVOID lpAddress, // address of region of committed pages
				 * DWORD dwSize, // size of region DWORD dwFreeType // type of
				 * free operation
				 */
				long x1 = concreteStack.pop();
				long x2 = concreteStack.pop();
				long x3 = concreteStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " " + x3);

				System.out.println("Base Address:" + x1 + ", Size:" + x2 + ", Free Type:" + x3);

				long t = system.getVirtualHandle().freeVirtual(x1, x2, x3);
				concreteValueRegister.mov("%eax", t);

			} else // Insert new API
			if (funcName.startsWith("FindFirstFileA")) {
				long x1 = concreteStack.pop();
				long x2 = concreteStack.pop();

				String fileName = concreteValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, x1));
				System.out.println("Search File:" + fileName + ", Memory Operand:" + x2);
				long t = system.getFileHandle().findFirstFile(fileName, concreteValueMemoryOperand, x2);
				concreteValueRegister.mov("%eax",
						system.getFileHandle().findFirstFile(fileName, concreteValueMemoryOperand, x2));

				System.out.println("Search Handle:" + t);

			} else if (funcName.startsWith("SetCurrentDirectoryA")) {
				long x1 = concreteStack.pop();
				// long x2 = concreteStack.pop();

				System.out.println("Argument:" + x1);

				String path = concreteValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, x1));
				System.out.println("Path File:" + path);

				concreteValueRegister.mov("%eax", system.setPath(path));

			} else if (funcName.startsWith("FindNextFileA")) {
				long x1 = concreteStack.pop();
				long x2 = concreteStack.pop();

				System.out.println("Argument, Search Handle:" + x1 + ", MemoryOperand:" + x2);
				concreteValueRegister.mov("%eax",
						system.getFileHandle().findNextFile(x1, concreteValueMemoryOperand, x2));

			} else if (funcName.startsWith("CloseHandle")) {
				long x1 = concreteStack.pop();

				System.out.println("Object Handle:" + x1);

				long t = system.closeHandle(x1);
				concreteValueRegister.mov("%eax", t);
				System.out.println("Return Value:" + t);

			} else if (funcName.startsWith("CreateFileMappingA")) {
				long x1 = concreteStack.pop();
				long x2 = concreteStack.pop();
				long x3 = concreteStack.pop();
				long x4 = concreteStack.pop();
				long x5 = concreteStack.pop();
				long x6 = concreteStack.pop();
				// long x7 = concreteStack.pop();
				// long x3 = concreteStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4 + " " + x5 + " " + x6);

				/*
				 * String fileMapName = concreteValueMemoryOperand .getText(new
				 * X86MemoryOperand(DataType.INT32, x6));
				 */
				// String str = symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32, t2));
				System.out.println("Handle File:" + x1 + ", Security Attribute:" + x2 + ", Object Protection:" + x3
						+ ", High-Order:" + x4 + ", High-Order:" + x5 + ", File Mapping Name Address:" + x6);
				long t = system.getFileHandle().createFileMapping(x1, x2, x3, x4, x5, x6);
				concreteValueRegister.mov("%eax", t);
				System.out.println("Return Value:" + t);

			} else if (funcName.startsWith("CreateFileA")) {
				long x1 = concreteStack.pop();
				long x2 = concreteStack.pop();
				long x3 = concreteStack.pop();
				long x4 = concreteStack.pop();
				long x5 = concreteStack.pop();
				long x6 = concreteStack.pop();
				long x7 = concreteStack.pop();
				// long x3 = concreteStack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4 + " " + x5 + " " + x6 + " " + x7);

				String fileName = concreteValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, x1));
				System.out.println("FileName:" + fileName + ", Access:" + x2 + ", ShareMode:" + x3 + ", pSecurity:"
						+ x4 + ", Mode:" + x5 + ", Attributes:" + x6 + ", hTemplate:" + x7);

				long t = system.createFile(fileName, x2, x3, x4, x5, x6, x7);
				concreteValueRegister.mov("%eax", t);

				System.out.println("Return value:" + t);

			} else if (funcName.startsWith("SetFileAttributesA")) {
				long x1 = concreteStack.pop();
				long x2 = concreteStack.pop();
				// long x3 = concreteStack.pop();
				System.out.println("Argument:" + x1 + " " + x2);

				String fileName = concreteValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, x1));
				System.out.println("FileName:" + fileName + ", Attribute:" + x2);

				concreteValueRegister.mov("%eax", system.getFileHandle().setFileAttribute(fileName, x2));
				long t = system.getFileHandle().setFileAttribute(fileName, x2);
				System.out.println("Return Value:" + t);

			} else if (funcName.startsWith("GetCurrentDirectoryA")) {
				long x1 = concreteStack.pop();
				long x2 = concreteStack.pop();
				// long x3 = concreteStack.pop();
				// System.out.println("Argument:" + x1 + " " + x2);

				System.out.println("Argument: Length:" + x1 + ", Memory Operand:" + x2);

				String curDir = "C:/Test";
				System.out.println("Current Directory:" + curDir);
				long size = concreteValueMemoryOperand.setText(new X86MemoryOperand(DataType.INT32, x2), curDir, x1);
				concreteValueRegister.mov("%eax", size);

			} else if (funcName.startsWith("GetSystemDirectoryA")) {
				long x2 = concreteStack.pop();
				long x1 = concreteStack.pop();

				String curDir = "C:/Windows/sytem32";
				System.out.println("System Directory:" + curDir);
				long size = concreteValueMemoryOperand.setText(new X86MemoryOperand(DataType.INT32, x2), curDir, x1);
				concreteValueRegister.mov("%eax", size);

				System.out.println("Result SystemDirectoryA:"
						+ concreteValueMemoryOperand.getText(new X86MemoryOperand(DataType.INT32, x2), size));

			} else if (funcName.startsWith("GetWindowsDirectoryA")) {
				long x2 = concreteStack.pop();
				long x1 = concreteStack.pop();
				// System.out.println("Argument:" + x1 + " " + x2);

				System.out.println("Argument: Length:" + x1 + ", Memory Operand:" + x2);

				String curDir = "C:/Windows";
				System.out.println("WindowDirectory:" + curDir);
				long size = concreteValueMemoryOperand.setText(new X86MemoryOperand(DataType.INT32, x2), curDir, x1);
				concreteValueRegister.mov("%eax", size);

			} else if (funcName.startsWith("GetModuleHandleA")) {
				long lpModuleName = concreteStack.pop();
				if (lpModuleName == 0) {
					returnValue = APIHandler.getModuleHandleA(0, program);
					concreteValueRegister.mov("%eax", returnValue);
				}
			} else if (funcName.startsWith("GetModuleFileNameA")) {
				long hModule = concreteStack.pop();
				long lpFilename = concreteStack.pop();
				long nSize = concreteStack.pop();

				System.out.println("Argument: " + hModule + " " + lpFilename + " " + nSize);
				String s = "C:/Windows/" + program.getFileName();
				concreteValueMemoryOperand.setText(new X86MemoryOperand(DataType.INT32, lpFilename), s);
				concreteValueRegister.mov("%eax", s.length());
			}

		} else if (inst.getName().toString().equals("jmp")) {
			System.out.println("JMP Concrete Execution of API:" + funcName);
			/*
			 * long retValue = concreteStack.pop();
			 * 
			 * if (funcName.startsWith("GetCurrentDirectoryA")) { long x1 =
			 * concreteStack.pop(); long x2 = concreteStack.pop(); long x3 =
			 * concreteStack.pop(); System.out.println("Argument:" + x1 + " " +
			 * x2 + " " + x3);
			 * 
			 * } else if (funcName.startsWith("GetSystemDirectoryA")) { long x1
			 * = concreteStack.pop(); long x2 = concreteStack.pop();
			 * System.out.println("Argument:" + x1 + " " + x2);
			 * 
			 * } else if (funcName.startsWith("GetWindowsDirectoryA")) { long x1
			 * = concreteStack.pop(); long x2 = concreteStack.pop();
			 * System.out.println("Argument:" + x1 + " " + x2);
			 * 
			 * } else if (funcName.startsWith("GetModuleHandleA")) { long
			 * lpModuleName = concreteStack.pop(); if (lpModuleName == 0) { long
			 * returnValue = APIHandler.getModuleHandleA(0, program);
			 * concreteValueRegister.mov("%eax", returnValue); } } else if
			 * (funcName.startsWith("GetModuleFileNameA")) { long hModule =
			 * concreteStack.pop(); long lpFilename = concreteStack.pop(); long
			 * nSize = concreteStack.pop();
			 * 
			 * long returnValue = APIHandler.getModuleFileNameA(hModule,
			 * lpFilename, nSize, program); concreteValueRegister.mov("%eax",
			 * returnValue); } concreteStack.push(retValue);
			 */
		}
		return ret;
	}
}
