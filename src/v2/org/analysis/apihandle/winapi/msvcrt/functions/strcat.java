/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.msvcrt.functions
 * File name: strncat.java
 * Created date: Nov 10, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.value.LongValue;

/**
 * Append characters from string
 * 
 * Appends the first num characters of source to destination, plus a terminating
 * null-character.
 * 
 * @param destination
 *            Pointer to the destination array, which should contain a C string,
 *            and be large enough to contain the concatenated resulting string,
 *            including the additional null-character.
 * 
 * @param source
 *            C string to be appended.
 * 
 * @param num
 *            Maximum number of characters to be appended. size_t is an unsigned
 *            integral type.
 * 
 *            If the length of the C string in source is less than num, only the
 *            content up to the terminating null-character is copied.
 * 
 * @return destination is returned.
 * 
 * @author Yen Nguyen
 *
 */
public class strcat extends MSVCRTAPI {

	public strcat() {
		super();
		NUM_OF_PARMS = 2;
		IS_POP_STACK_VALUE = false;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		
		String destination = memory.getText(this, t1);
		String source = memory.getText(this, t2);
		System.out.println("Source:" + source + ", Dest:" + destination);
		if (source.length() > 0) {			
			destination = destination.concat(source);

			memory.setText(this, t1, destination, destination.length());
			// Null-terminated
//			memory.setByteMemoryValue(t1 + destination.length(), new LongValue(0));
		}
		
		System.out.println("New String:" + memory.getText(this, t1));
		register.mov("eax", new LongValue(t1));
	}

}
