package v2.org.analysis.statistics;

import java.io.BufferedReader;
import java.io.FileReader;

public class ExtractData {
	// This class extracts the processing time, number of node, number of edges
	// from Result_Jakstab and Result_Final
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String rJakstab = "data/data/in/Result-Jakstab.txt";
		String name = "data/data/name.txt";
		String timesJ = "data/data/times_J.txt";
		String timesC = "data/data/times_B.txt";
		String nodesJ = "data/data/nodes_J.txt";
		String nodesC = "data/data/nodes_B.txt";
		String edgesJ = "data/data/edges_J.txt";
		String edgesC = "data/data/edges_B.txt";
		String rFinal = "data/data/in/Result-Final.txt";
		FileProcess fJ = new FileProcess(rJakstab);
		// fList.clearContentFile();
		// fList.listFileInDir(pathVirus);
		// FileProcess fFinal = new FileProcess(rFinal);
		FileProcess fName = new FileProcess(name);
		FileProcess tJ = new FileProcess(timesJ);
		FileProcess tC = new FileProcess(timesC);
		FileProcess nJ = new FileProcess(nodesJ);
		FileProcess nC = new FileProcess(nodesC);
		FileProcess eJ = new FileProcess(edgesJ);
		FileProcess eC = new FileProcess(edgesC);

		try {
			// clearContentFile(unprocessedFile);
			BufferedReader br = new BufferedReader(new FileReader(rFinal));
			// StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				String t[] = line.split(",");
				fName.appendFile(t[0]);
				if (fJ.containFinalExtract(line, nJ, nC, eJ, eC, tJ, tC)) {
					// fFinal.appendFile(line);
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
