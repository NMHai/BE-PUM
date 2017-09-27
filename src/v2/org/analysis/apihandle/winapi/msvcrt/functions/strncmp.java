/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.msvcrt.functions
 * File name: strncmp.java
 * Created date: Nov 10, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTDLL;
import v2.org.analysis.value.LongValue;

/**
 * Compare characters of two strings
 * 
 * Compares up to num characters of the C string str1 to those of the C string
 * str2. This function starts comparing the first character of each string. If
 * they are equal to each other, it continues with the following pairs until the
 * characters differ, until a terminating null-character is reached, or until
 * num characters match in both strings, whichever happens first.
 * 
 * @param str1
 *            C string to be compared.
 * 
 * @param str2
 *            C string to be compared.
 * 
 * @param num
 *            Maximum number of characters to compare. size_t is an unsigned
 *            integral type.
 * 
 * @return Returns an integral value indicating the relationship between the
 *         strings:
 * 
 *         return value indicates
 * 
 *         <0 the first character that does not match has a lower value in str1
 *         than in str2
 * 
 *         0 the contents of both strings are equal
 * 
 *         >0 the first character that does not match has a greater value in
 *         str1 than in str2
 * 
 * @author Yen Nguyen
 *
 */
public class strncmp extends MSVCRTAPI {

	public strncmp() {
		super();
		NUM_OF_PARMS = 3;
		IS_POP_STACK_VALUE = false;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);

		String str1 = memory.getText(this, t1);
		String str2 = memory.getText(this, t2);
		int num = (int) t3;
		int ret = MSVCRTDLL.INSTANCE.strncmp(str1, str2, num);
		
		register.mov("eax", new LongValue(ret));
	}

}
