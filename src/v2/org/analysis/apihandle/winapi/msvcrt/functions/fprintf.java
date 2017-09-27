/**
 * 
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

import com.sun.jna.Pointer;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTDLL;
import v2.org.analysis.apihandle.winapi.structures.Stdio.FILE;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * @author Yen Nguyen
 *
 */
public class fprintf extends MSVCRTAPI {

	public fprintf() {
		super();
		NUM_OF_PARMS = 0;
	}

	@Override
	public void execute() {
		this.params = new ArrayList<Long>();
		for (int i = 0; i < 2; i++) {
			Value value = stack.pop();

			if (value instanceof LongValue) {
				this.params.add(((LongValue) value).getValue());
			}
		}

		long pFILE = this.params.get(0);
		String format = memory.getText(this, this.params.get(1));

		System.out.println(String.format("FILE: %d, Format String: %s", pFILE, format));

		if (format != null && format.length() > 0) {
			int numOfAgrs = StringUtils.countMatches(format, "%");

			String argsList[] = new String[numOfAgrs + 1];
			argsList[0] = format;

			for (int i = 0; i < numOfAgrs; i++) {
				Value value = stack.pop();
				if (value instanceof LongValue) {
					long p = ((LongValue) value).getValue();

					this.params.add(p);
					argsList[i + 1] = memory.getText(this, p);
					System.out.println(String.format("[%d]:%s", i, argsList[i + 1]));
				}
			}
			
			FILE stream = new FILE(new Pointer(pFILE));
			int ret = MSVCRTDLL.INSTANCE.fprintf(stream , argsList);

//			MSVCRTDLL.INSTANCE.fprintf(MSVCRTDLL.INSTANCE.fopen("C:\\Workspace\\BE-PUMv2\\Storage\\C\\WINDOWS\\system32\\drivers\\etc\\hosts", "a") , argsList);
			
			
			
			register.mov("eax", new LongValue(ret));
		}
		
		//Re-push
		for (int i = this.params.size(); i > 0; i--) {
			stack.push(new LongValue(this.params.get(i - 1)));
		}
	}

}
