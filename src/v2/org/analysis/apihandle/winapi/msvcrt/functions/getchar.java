/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.msvcrt.functions
 * File name: strrchr.java
 * Created date: Sep 21, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.value.SymbolValue;

/**
 * Read a character from a stream (getc, getwc), or get a character from stdin (getchar, getwchar).
 * 
 * int getchar( void );
 * 
 * Each of these functions returns the character read. To indicate an read error or end-of-file condition, 
 * etc and getchar return EOF, and getwc and getwchar return WEOF. For getc and getchar, use ferror or feof to 
 * check for an error or for end of file.
 * 
 * @author HaiNM
 *
 */
public class getchar extends MSVCRTAPI {

	public getchar() {
		super();
		NUM_OF_PARMS = 0;
		IS_POP_STACK_VALUE = false;
	}

	@Override
	public void execute() {
		System.out.println("No param");
		register.mov("eax", new SymbolValue(apiName + "_" + System.currentTimeMillis()));
	}

}
