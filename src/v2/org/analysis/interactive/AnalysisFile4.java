package v2.org.analysis.interactive;

/**
*
* @author NMHai

* Process the malware hostname_mew.exe
*/

import java.io.FileWriter;
import java.io.IOException;

public class AnalysisFile4 extends AnalysisFactory {
	private static FileWriter file = null;

	static {
		try {
			file = new FileWriter("strings.log");
		} catch (IOException ex) {

		}
	}	
}
