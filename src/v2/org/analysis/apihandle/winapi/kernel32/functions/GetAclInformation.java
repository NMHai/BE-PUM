/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetAclInformation.java
 * Created date: Sep 10, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.ACL;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.apihandle.winapi.structures.WinNTn.*;
import v2.org.analysis.value.LongValue;

/**
 * The GetAclInformation function retrieves information about an access control
 * list (ACL).
 * 
 * @param pAcl
 *            A pointer to an ACL. The function retrieves information about this
 *            ACL. If a null value is passed, the function causes an access
 *            violation.
 * 
 * @param pAclInformation
 *            A pointer to a buffer to receive the requested information. The
 *            structure that is placed into the buffer depends on the
 *            information class requested in the dwAclInformationClass
 *            parameter.
 * 
 * @param nAclInformationLength
 *            The size, in bytes, of the buffer pointed to by the
 *            pAclInformation parameter.
 * 
 * @param dwAclInformationClass
 *            A value of the ACL_INFORMATION_CLASS enumeration that indicates
 *            the class of information requested.
 * 
 *            This parameter can be one of two values from this enumeration:
 * 
 *            If the value is AclRevisionInformation, the function fills the
 *            buffer pointed to by the pAclInformation parameter with an
 *            ACL_REVISION_INFORMATION structure.
 * 
 *            If the value is AclSizeInformation, the function fills the buffer
 *            pointed to by the pAclInformation parameter with an
 *            ACL_SIZE_INFORMATION structure.
 * 
 * @return If the function succeeds, the function returns nonzero. If the
 *         function fails, it returns zero. To get extended error information,
 *         call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class GetAclInformation extends Kernel32API {

	public GetAclInformation() {
		super();
		NUM_OF_PARMS = 4;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);

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

		int dwAclInformationClass = (int) t4;
		Structure pAclInformation = null;
		if (dwAclInformationClass == 1) {
			// ACL_REVISION_INFORMATION
			pAclInformation = new ACL_REVISION_INFORMATION();
		} else if (dwAclInformationClass == 2) {
			// ACL_SIZE_INFORMATION
			pAclInformation = new ACL_SIZE_INFORMATION();
		}

		DWORD nAclInformationLength = new DWORD(pAclInformation.size());
		BOOL ret = Kernel32DLL.INSTANCE.GetAclInformation(pAcl, pAclInformation, nAclInformationLength,
				dwAclInformationClass);

		register.mov("eax", new LongValue(ret.longValue()));

		if (dwAclInformationClass == 1) {
			// ACL_REVISION_INFORMATION
			memory.setDoubleWordMemoryValue( //
					new X86MemoryOperand(DataType.INT32, t2), //
					new LongValue(((ACL_REVISION_INFORMATION) pAclInformation).AclRevision.longValue()));
		} else if (dwAclInformationClass == 2) {
			// ACL_SIZE_INFORMATION
			ACL_SIZE_INFORMATION p = (ACL_SIZE_INFORMATION) pAclInformation;
			memory.setDoubleWordMemoryValue( //
					new X86MemoryOperand(DataType.INT32, t2), //
					new LongValue(p.AceCount.longValue()));
			memory.setDoubleWordMemoryValue( //
					new X86MemoryOperand(DataType.INT32, t2 += 4), //
					new LongValue(p.AclBytesInUse.longValue()));
			memory.setDoubleWordMemoryValue( //
					new X86MemoryOperand(DataType.INT32, t2 += 4), //
					new LongValue(p.AclBytesFree.longValue()));
		}
	}
}
