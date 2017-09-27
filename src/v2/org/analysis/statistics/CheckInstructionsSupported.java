/**
 * This class checks which viruses supported by 52 instructions of BE-PUM
 * 
 * Method: 
 * S1: read 52 instructions which is stored in file inst.txt and create a array list of supported instructions.
 * S2: read generated results of BE-PUM in TEMP 
 * S3: check if all instructions of each virus is in the array list of supported instructions. 
 * S4: store the results, the number and name of supported viruses in supportedVirus.txt
 *     name of unSupported viruses in unSupportedVirus.txt
 * S5: calculate the interleave between supportedVirus and indirectResolveFile 
 * 
 * @author Nguyen Minh Hai
 */

package v2.org.analysis.statistics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class CheckInstructionsSupported {
	final String inst = "data/data/in/inst.txt"; // Name of supported instruction
	final String pathTarget = "data/asm/Temp"; // Store generated results
	final String supportVName = "data/data/out/supportedVirus.txt"; // Store name of
																// supported
																// Viruses
	final String unSupportVName = "data/data/out/unSupportedVirus.txt"; // Store name
																	// of
																	// unsupported
																	// Viruses
	final String indirectName = "data/data/in/indirectResolvedFile.txt"; // Store
																	// name of
																	// supported
																	// Viruses
																	// with
																	// indirect
																	// jump
	final String indirectUnName = "data/data/iout/indirectUnsupported.txt"; // Store
																		// name
																		// of
																		// unsupported
																		// Viruses
																		// with
																		// indirect
																		// jump

	public void execute() {
		StringArrayList n = new StringArrayList();
		FileProcess insF = new FileProcess(inst);
		// Initiate the list of instructions
		insF.readFile(n);

		FileProcess sF = new FileProcess(supportVName);
		sF.clearContentFile();
		FileProcess usF = new FileProcess(unSupportVName);
		usF.clearContentFile();

		// FileProcess indirectF = new FileProcess(indirectName);

		File folder = new File(pathTarget);
		System.out.println("Begin to check supported files.");
		int numF = 0;

		for (File fileEntry : folder.listFiles()) {
			if (!fileEntry.isDirectory()
			// && fileEntry.getName().endsWith("exe")
			) {
				// if (!check(fileEntry.getName())) {
				String t = fileEntry.getName();
				if (t.contains("_jak.asm"))
					if (checkFile(t, n)) {
						numF++;
						String s = t.replace("_jak.asm", "");
						sF.appendFile(s);
					} else
						usF.appendFile(t);
			}
		}
		System.out.println("Number of supported file: " + numF);

		checkIndirect(sF);
	}

	private void checkIndirect(FileProcess sF) {
		// TODO Auto-generated method stub
		int numFile = 0;
		FileProcess t = new FileProcess(indirectUnName);
		t.clearContentFile();

		try {
			// clearContentFile(unprocessedFile);
			BufferedReader br = new BufferedReader(new FileReader(indirectName));
			// StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				if (sF.contain(line)) {
					System.out.println(line);
					numFile++;
				} else
					t.appendFile(line);
				line = br.readLine();
				// if (numFile > 0) break;
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		System.out.println("Number of indirect File: " + numFile);
	}

	private boolean checkFile(String file, StringArrayList n) {
		// TODO Auto-generated method stub
		try {
			// clearContentFile(unprocessedFile);
			BufferedReader br = new BufferedReader(new FileReader(pathTarget + "/" + file));
			// StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				if (line.contains("0x")) {
					// String t[] = line.split(" ");
					if (!n.contain(line)) {
						System.out.println(file);
						System.out.println(line);
						return false;
					}
				}

				line = br.readLine();
				// if (numFile > 0) break;
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return true;
	}

	public static void main(String[] args) {
		new CheckInstructionsSupported().execute();
	}

}
