/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: RtlUnwind.java
 * Created date: Aug 11, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.value.SymbolValue;

/**
 * Initiates an unwind of procedure call frames.
 * 
 * @param TargetFrame
 *            A pointer to the call frame that is the target of the unwind.
 *            If this parameter is NULL, the function performs an exit
 *            unwind.
 * 
 * @param TargetIp
 *            The continuation address of the unwind. This parameter is
 *            ignored if TargetFrame is NULL.
 * 
 * @param ExceptionRecord
 *            A pointer to an EXCEPTION_RECORD structure.
 * 
 * @param ReturnValue
 *            A value to be placed in the integer function return register
 *            before continuing execution.
 *            
 * @author Yen Nguyen
 *
 */
public class RtlUnwind extends Kernel32API {
	public RtlUnwind() {
		super();
		NUM_OF_PARMS = 4;
	}

	/* (non-Javadoc)
	 * @see v2.org.analysis.apihandle.winapi.API#execute()
	 */
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		register.setRegisterValue("eax", new SymbolValue("api_eax_" + getFullName()));
	}

}
