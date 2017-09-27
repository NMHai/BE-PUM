/**
 * This class extract the result in file1 which is in file2 
 * and stores the results in file3
 * 
 * @author Nguyen Minh Hai
 */

package v2.org.analysis.statistics;

import java.io.BufferedReader;
import java.io.FileReader;

public class FIleExtract {

	// final String indirectFile = "indirectFile.txt";

	public static void main(String[] args) {
		// String pathVirus = "asm/vx.netlux.org/";
		String file1 = "be-pum.txt";
		String file2 = "ida.txt";
		String file3 = "r.txt";

		FileProcess f1 = new FileProcess(file1);
		// fList.clearContentFile();
		// fList.listFileInDir(pathVirus);
		FileProcess f3 = new FileProcess(file3);
		// FileProcess fC = new FileProcess(rCHRM);

		try {
			// clearContentFile(unprocessedFile);
			BufferedReader br = new BufferedReader(new FileReader(file2));
			// StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				String t[] = line.split("\t");

				if (f1.contain(t[0])) {
					f3.appendFile(line);
					// Main.process("-m " + pathVirus + line);
				}

				line = br.readLine();
				// if (numFile > 0) break;
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		System.exit(0);
	}
}
