/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: UnhandledExceptionFilter.java
 * Created date: Jul 26, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.value.LongValue;

/**
 * @author Yen Nguyen
 *
 */
public class UnhandledExceptionFilter extends Kernel32API {

	public UnhandledExceptionFilter() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		// When an exception occurs, with Windows XP SP>=2, Windows 2003, and
		// Windows Vista, the usual way the OS processes the exception is:
		//
		// - If any, pass control to the per-process Vectored Exception
		// Handlers.
		// - If the exception is not processed, pass the control to the
		// per-thread top SEH handler, pointed by FS:[0] in the thread that
		// generated the exception. SEH are chained and called in turn if the
		// exception is not processed by the previous in the chain.
		// - If the exception has not been processed by any of the previous
		// handlers, the final SEH handler (set by the system), will call
		// kernel32!UnhandledExceptionFilter. This function will decide what it
		// should do depending if the process is debugged or not.

		/**
		 * - IF IT IS NOT DEBUGGED, IT WILL CALL THE USER-DEFINED FILTER
		 * FUNCTION (SET VIA KERNEL32!SETUNHANDLEDEXCEPTIONFILTER).
		 */

		// - If it debugged, the program will be terminated.
		//
		// The debugger detection in UnhandledExceptionFilter is made with
		// ntdll!NtQueryInformationProcess.
		//
		// Example:
		// push @not_debugged
		// call SetUnhandledExceptionFilter
		// xor eax, eax
		// mov eax, dword [eax] ; trigger exception
		// ;program terminated if debugged
		// ;...
		// @not_debugged:
		// ;process the exception
		// ;continue the execution
		// ;...

		// http://www.codeproject.com/Articles/30815/An-Anti-Reverse-Engineering-Guide
		/**
		 * LONG WINAPI UnhandledExcepFilter(PEXCEPTION_POINTERS pExcepPointers)
		 * {
		 * 
		 * // Restore old UnhandledExceptionFilter
		 * SetUnhandledExceptionFilter((LPTOP_LEVEL_EXCEPTION_FILTER)
		 * pExcepPointers->ContextRecord->Eax);
		 * 
		 * // Skip the exception code pExcepPointers->ContextRecord->Eip += 2;
		 * 
		 * cout << "Catch the exception and continue execution!" << endl;
		 * 
		 * return EXCEPTION_CONTINUE_EXECUTION;
		 * 
		 * }
		 * 
		 * 
		 * int main() {
		 * 
		 * SetUnhandledExceptionFilter(UnhandledExcepFilter);
		 * 
		 * __asm{xor eax, eax} __asm{div eax}
		 * 
		 * // Execution resumes here if there is no debugger or if there is a
		 * debugger it will never reach this point of execution
		 * 
		 * getchar(); return 0;
		 * 
		 * }
		 */

		System.out.println("Return EXCEPTION_EXECUTE_HANDLER 0x1");
		System.out
				.println("The function returns control to the exception handler, which is free to take any appropriate action.");
		long value = 1;
		register.mov("eax", new LongValue(value));
	}

}
