package v2.org.analysis.olly;

import org.jakstab.asm.AbsoluteAddress;

public class MainOlly {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long memoryStartAddr = 0x401000;
		long memoryEndAddr = 0x401210;
		long stackIndex = 0x8c;
		AbsoluteAddress checkedAddr = new AbsoluteAddress(0);
		AbsoluteAddress endAddr = new AbsoluteAddress(0);
		String fileName = "out_themida_";
		int num = 12;

		OllyCompare ollyCompare = new OllyCompare("asm/olly/" + fileName + "" + num + ".txt", memoryStartAddr,
				memoryEndAddr, stackIndex);
		ollyCompare.importOllyData(checkedAddr, endAddr);
		System.out.println("Finished import Olly");
	}

}
