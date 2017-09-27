/**
 * 
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import org.jakstab.asm.AbsoluteAddress;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.value.SymbolValue;
import v2.org.analysis.value.Value;

/**
 * @author HaiNM
 *
 */
public class scanf extends MSVCRTAPI {

	public scanf() {
		super();
		NUM_OF_PARMS = 2;
		IS_POP_STACK_VALUE = false;
	}

	@Override
	public void execute() {
		long pFormat = this.params.get(0);
		long pArg = this.params.get(1);
		String format = memory.getText(this, pFormat);
		System.out.println(String.format("Format: %s, Argument: %s", format, new AbsoluteAddress(pArg)));		
		Value val = new SymbolValue("scanf_" + System.currentTimeMillis());
		
		if (format == "%d" || format == "%s") {			
			memory.setDoubleWordMemoryValue(pArg, val);					
		} else if (format == "%c") {			
			memory.setByteMemoryValue(pArg, val);					
		} else {
			memory.setDoubleWordMemoryValue(pArg, val);			
		}
		
		register.mov("eax", val);	
	}

}
