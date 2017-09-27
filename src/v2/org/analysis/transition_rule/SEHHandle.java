package v2.org.analysis.transition_rule;

import java.util.ArrayList;

import org.jakstab.asm.AbsoluteAddress;

import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class SEHHandle {
	
	private ArrayList<LongValue> exception_addrs;
	
	public SEHHandle () {
		exception_addrs = new ArrayList<LongValue>();
	}
	
	public void setExceptionAddr (Value addr)
	{
		if (addr instanceof LongValue)
		{
			LongValue laddr = (LongValue) addr;
			if (laddr.getValue() != 0x0)
			{
				exception_addrs.add(laddr);
			}
		}
	}
	
	public boolean causeException (AbsoluteAddress addr)
	{
		if (exception_addrs.size() == 0) {
			return false;
		}
		
		for (LongValue a: exception_addrs)
		{
			if (a.getValue() == addr.getValue())
			{
				return true;
			}
		}
		
		return false;
	}
	
}
