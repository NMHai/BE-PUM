/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.generation
 * File name: TypeMapping.java
 * Created date: Oct 20, 2015
 * Description:
 */
package v2.org.analysis.apihandle.generation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import v2.org.analysis.apihandle.generation.stub.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Yen Nguyen
 *
 */
public class TypeMappingGeneration {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		String workingDir = System.getProperty("user.dir");
		String path = workingDir + "\\src\\" + TypeMappingGeneration.class.getPackage().getName().replace(".", "\\")
				+ "\\TypeMap.json";
		File jsonfile = new File(path);

		// if file doesn't exists, then create it
		if (!jsonfile.exists()) {
			jsonfile.createNewFile();
		}

		FileWriter fileWriter = new FileWriter(jsonfile.getAbsoluteFile());
		@SuppressWarnings("resource")
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		List<Type> typeList = new ArrayList<>();

		boolean isBasic = false;
		try (BufferedReader br = new BufferedReader(new FileReader("Supported_TypeStruct.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				int index = line.lastIndexOf('.');
				if (index > 1) {
					String typeName = line.substring(index + 1, line.length());
					Type t = new Type(typeName, typeName, line);

					typeList.add(t);
				} else {
					isBasic = true;
				}
			}
		}

		// System.out.println(gson.toJson(typeList));
		try (BufferedReader br = new BufferedReader(new FileReader("Supported_TypeBasic.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] subStr = line.split(",");
				if (subStr.length > 0) {
					Type t = new Type();
					t.from = subStr[0];
					t.to = subStr[1];
					if (subStr.length == 3) {
						t.fullClassName = subStr[2];
					}

					typeList.add(t);
				} else {
					isBasic = true;
				}
			}
		}

		bufferedWriter.write(gson.toJson(typeList));
		bufferedWriter.close();
		fileWriter.close();
	}
}
