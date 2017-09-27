/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.msvcrt.functions
 * File name: strrchr.java
 * Created date: Sep 21, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.value.LongValue;

/**
 * Locate last occurrence of character in string
 * 
 * Returns a pointer to the last occurrence of character in the C string str.
 * 
 * The terminating null-character is considered part of the C string. Therefore,
 * it can also be located to retrieve a pointer to the end of a string.
 * 
 * @param str
 *            C string.
 * 
 * @param character
 *            Character to be located. It is passed as its int promotion, but it
 *            is internally converted back to char.
 * 
 * @return A pointer to the last occurrence of character in str. If the
 *         character is not found, the function returns a null pointer.
 * 
 * @author Yen Nguyen
 *
 */
public class strrchr extends MSVCRTAPI {

	public strrchr() {
		super();
		NUM_OF_PARMS = 2;
		IS_POP_STACK_VALUE = false;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		String str = memory.getText(this, t1);
		char character = (char) t2;
		
		System.out.println(String.format("str:%s|char:%c", str, character));
		
		int index = str.lastIndexOf(character);
		long ret = 0;
		
		if (index > -1) {
			ret = t1 + index;
		}
		
		register.mov("eax", new LongValue(ret));
	}

}
