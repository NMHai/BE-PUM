/**
 * 
 */
package v2.org.analysis.apihandle.winapi;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;

import com.google.gson.Gson;

/**
 * @author yennlh
 *
 */
public class APIMapGenerator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int count = 0;
		String workingDir = System.getProperty("user.dir");
		String path = workingDir + "\\src\\" + APIMapGenerator.class.getPackage().getName().replace(".", "\\");
		HashMap<String, HashMap<String, String>> map = new HashMap<>();

		try {
			File jsonFile = new File(path + "\\APIMap.json");

			// if file doesn't exists, then create it
			if (!jsonFile.exists()) {
				jsonFile.createNewFile();
			}

			FileWriter fw = new FileWriter(jsonFile.getAbsoluteFile());
			BufferedWriter bufferedWriter = new BufferedWriter(fw);

			File directory = new File(path);
			for (File file : directory.listFiles()) {
				if (file.isDirectory() && !file.getName().equals("structures")) {
					String DLLName = file.getName().toLowerCase();
					HashMap<String, String> subMap = new HashMap<>();

					File subDir = new File(file.getAbsolutePath() + "\\functions");
					for (File api : subDir.listFiles()) {
						String packageName = APIMapGenerator.class.getPackage().getName() + "." + file.getName()
								+ ".functions.";
						String funcName = api.getName().replace(".java", "");

						subMap.put(funcName.toLowerCase(), packageName + funcName );
						count++;
					}

					map.put(DLLName, subMap);
				}
			}

			Gson gson = new Gson();
			bufferedWriter.write(gson.toJson(map));
			bufferedWriter.close();
			System.out.println("Total: " + count);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
