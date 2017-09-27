package v2.org.analysis.apihandle.winapi.advapi32.functions;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinNT.ACL;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;

/**
 * The IsValidAcl function validates an access control list (ACL).
 * 
 * @param pAcl
 *            A pointer to an ACL structure validated by this function. This
 *            value must not be NULL.
 * 
 * @return If the ACL is valid, the function returns nonzero. If the ACL is not
 *         valid, the function returns zero. There is no extended error
 *         information for this function; do not call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class IsValidAcl extends Advapi32API {

	public IsValidAcl() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		ACL pAcl = new ACL();
		pAcl.AclRevision = (byte) ((LongValue) memory.getByteMemoryValue(//
				new X86MemoryOperand(DataType.INT8, t1))).getValue();
		pAcl.Sbz1 = (byte) ((LongValue) memory.getByteMemoryValue(//
				new X86MemoryOperand(DataType.INT8, t1 += 1))).getValue();
		pAcl.AclSize = (short) ((LongValue) memory.getByteMemoryValue(//
				new X86MemoryOperand(DataType.INT8, t1 += 1))).getValue();
		pAcl.AceCount = (short) ((LongValue) memory.getByteMemoryValue(//
				new X86MemoryOperand(DataType.INT8, t1 += 2))).getValue();
		pAcl.Sbz2 = (short) ((LongValue) memory.getByteMemoryValue(//
				new X86MemoryOperand(DataType.INT8, t1 += 2))).getValue();

		BOOL ret = Advapi32DLL.INSTANCE.IsValidAcl(pAcl);

		register.mov("eax", new LongValue(ret.longValue()));
	}

}
