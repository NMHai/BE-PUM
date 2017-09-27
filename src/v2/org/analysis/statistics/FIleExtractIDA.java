/**
 * This class extract the result in file1 which is in file2 
 * and stores the results in file3
 * 
 * @author Nguyen Minh Hai
 */

package v2.org.analysis.statistics;

import java.io.BufferedReader;
import java.io.FileReader;

public class FIleExtractIDA {

	// final String indirectFile = "indirectFile.txt";

	public static void main(String[] args) {
		// String pathVirus = "asm/vx.netlux.org/";
		String file = "result_IDA.txt";
		String fileN = "ida.txt";
		// fList.clearContentFile();
		// fList.listFileInDir(pathVirus);
		FileProcess f = new FileProcess(fileN);
		// FileProcess fC = new FileProcess(rCHRM);

		try {
			// clearContentFile(unprocessedFile);
			BufferedReader br = new BufferedReader(new FileReader(file));
			// StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				String t[] = line.split(" ");
				String t1 = t[0].substring(40);
				String t2 = t1.replace(".asm", "");

				f.appendFile(t2 + "\t" + t[1]);

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
