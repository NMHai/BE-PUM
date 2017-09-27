/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.winspool.functions
 * File name: EnumPrinters.java
 * Created date: Aug 2, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.winspool.functions;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.apihandle.winapi.winspool.WinspoolAPI;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Winspool;
import com.sun.jna.ptr.IntByReference;

/**
 * The EnumPrinters function enumerates available printers, print servers,
 * domains, or print providers.
 * 
 * @param Flags
 *            The types of print objects that the function should enumerate.
 * 
 * @param Name
 *            If Level is 1, Flags contains PRINTER_ENUM_NAME, and Name is
 *            non-NULL, then Name is a pointer to a null-terminated string that
 *            specifies the name of the object to enumerate. This string can be
 *            the name of a server, a domain, or a print provider. If Level is
 *            1, Flags contains PRINTER_ENUM_NAME, and Name is NULL, then the
 *            function enumerates the available print providers. If Level is 1,
 *            Flags contains PRINTER_ENUM_REMOTE, and Name is NULL, then the
 *            function enumerates the printers in the user's domain. If Level is
 *            2 or 5,Name is a pointer to a null-terminated string that
 *            specifies the name of a server whose printers are to be
 *            enumerated. If this string is NULL, then the function enumerates
 *            the printers installed on the local computer. If Level is 4, Name
 *            should be NULL. The function always queries on the local computer.
 *            When Name is NULL, setting Flags to PRINTER_ENUM_LOCAL |
 *            PRINTER_ENUM_CONNECTIONS enumerates printers that are installed on
 *            the local machine. These printers include those that are
 *            physically attached to the local machine as well as remote
 *            printers to which it has a network connection. When Name is not
 *            NULL, setting Flags to PRINTER_ENUM_LOCAL | PRINTER_ENUM_NAME
 *            enumerates the local printers that are installed on the server
 *            Name.
 * 
 * @param Level
 *            The type of data structures pointed to by pPrinterEnum. Valid
 *            values are 1, 2, 4, and 5, which correspond to the PRINTER_INFO_1,
 *            PRINTER_INFO_2 , PRINTER_INFO_4, and PRINTER_INFO_5 data
 *            structures.
 * 
 * @param pPrinterEnum
 *            A pointer to a buffer that receives an array of PRINTER_INFO_1,
 *            PRINTER_INFO_2, PRINTER_INFO_4, or PRINTER_INFO_5 structures. Each
 *            structure contains data that describes an available print object.
 *            If Level is 1, the array contains PRINTER_INFO_1 structures. If
 *            Level is 2, the array contains PRINTER_INFO_2 structures. If Level
 *            is 4, the array contains PRINTER_INFO_4 structures. If Level is 5,
 *            the array contains PRINTER_INFO_5 structures. The buffer must be
 *            large enough to receive the array of data structures and any
 *            strings or other data to which the structure members point. If the
 *            buffer is too small, the pcbNeeded parameter returns the required
 *            buffer size.
 * 
 * @param cbBuf
 *            The size, in bytes, of the buffer pointed to by pPrinterEnum.
 * 
 * @param pcbNeeded
 *            A pointer to a value that receives the number of bytes copied if
 *            the function succeeds or the number of bytes required if cbBuf is
 *            too small.
 * 
 * @param pcReturned
 *            A pointer to a value that receives the number of PRINTER_INFO_1,
 *            PRINTER_INFO_2 , PRINTER_INFO_4, or PRINTER_INFO_5 structures that
 *            the function returns in the array to which pPrinterEnum points.
 * 
 * @return If the function succeeds, the return value is a nonzero value. If the
 *         function fails, the return value is zero.
 * 
 * @author Yen Nguyen
 *
 */
public class EnumPrinters extends WinspoolAPI {
	public EnumPrinters() {
		super();
		NUM_OF_PARMS = 7;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);
		long t5 = this.params.get(4);
		long t6 = this.params.get(5);
		long t7 = this.params.get(6);

		int Flags = (int) t1;
		String Name = memory.getText(this, t2);
		int Level = (int) t3;
		Pointer pPrinterEnum = new Memory(Pointer.SIZE);
		int cbBuf = (int) t5;
		IntByReference pcbNeeded = new IntByReference();
		IntByReference pcReturned = new IntByReference();

		boolean ret = Winspool.INSTANCE.EnumPrinters(Flags, Name, Level, pPrinterEnum, cbBuf, pcbNeeded, pcReturned);

		register.mov("eax", new LongValue(ret ? 1 : 0));
		System.out.println("Return Value: " + ret);

		if (t4 != 0L) {
			memory.setByteMemoryValue(new X86MemoryOperand(DataType.INT8, t4), new LongValue(pPrinterEnum.getByte(0)));
		}

		if (t6 != 0L) {
			memory.setByteMemoryValue(new X86MemoryOperand(DataType.INT8, t6), new LongValue(pcbNeeded.getValue()));
		}

		if (t7 != 0L) {
			memory.setByteMemoryValue(new X86MemoryOperand(DataType.INT8, t7), new LongValue(pcReturned.getValue()));
		}
	}

}
