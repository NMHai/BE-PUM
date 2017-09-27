/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: IsProcessorFeaturePresent.java
 * Created date: Nov 11, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.platform.win32.WinDef.BOOL;

/**
 * Determines whether the specified processor feature is supported by the
 * current computer.
 * 
 * @param ProcessorFeature
 *            The processor feature to be tested. This parameter can be one of
 *            the following values.
 * 
 * @return If the feature is supported, the return value is a nonzero value. If
 *         the feature is not supported, the return value is zero. If the HAL
 *         does not support detection of the feature, whether or not the
 *         hardware supports the feature, the return value is also zero.
 * 
 * @author Yen Nguyen
 *
 */
public class IsProcessorFeaturePresent extends Kernel32API {

	public IsProcessorFeaturePresent() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		int ProcessorFeature = (int) t1;
		BOOL ret = Kernel32DLL.INSTANCE.IsProcessorFeaturePresent(ProcessorFeature);
		
		register.mov("eax", new LongValue(ret.longValue()));
	}

}
