package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.value.LongValue;

public class _mbsrchr extends MSVCRTAPI {

	public _mbsrchr() {
		super();
		NUM_OF_PARMS = 2;
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
