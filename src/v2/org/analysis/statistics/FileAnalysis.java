/**
 * This class gets the result which is interleaved between Jastab and CRHM
 * and stores the results in Final
 * 
 * @author Nguyen Minh Hai
 */

package v2.org.analysis.statistics;

import java.io.BufferedReader;
import java.io.FileReader;

public class FileAnalysis {

	// final String indirectFile = "indirectFile.txt";

	public static void main(String[] args) {
		// String pathVirus = "asm/vx.netlux.org/";
		String rJakstab = "data/data/in/Result-Jakstab.txt";
		String rCHRM = "data/data/in/Result-BE-PUM.txt";
		String rFinal = "data/data/in/Result-Final.txt";

		FileProcess fJ = new FileProcess(rJakstab);
		// fList.clearContentFile();
		// fList.listFileInDir(pathVirus);
		FileProcess fFinal = new FileProcess(rFinal);
		// FileProcess fC = new FileProcess(rCHRM);

		try {
			// clearContentFile(unprocessedFile);
			BufferedReader br = new BufferedReader(new FileReader(rCHRM));
			// StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				if (fJ.containFinal(line)) {
					fFinal.appendFile(line);
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
