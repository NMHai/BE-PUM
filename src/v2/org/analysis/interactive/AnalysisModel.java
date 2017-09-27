/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v2.org.analysis.interactive;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author zunc
 */
public class AnalysisModel {
	private Map<String, String> hashValue = null;
	private static AnalysisModel Instance = null;
	private static final String FILE_HASH = "data/data/hashValue.json";
	private static final Object FILE_NAME_TAG = "FILE_NAME";
	private static final Object HASH_TAG = "HASH";
	private boolean isLogInstr = false, isLogAPI = false, isAutoGenerateSymbolic = false;
	private IInteractive globalAnalysis = null;
	
	public static AnalysisModel getInstance() {
		if (Instance == null) {
			Instance = new AnalysisModel();			
		}
		return Instance;
	}
	
	public void setAnalysis(IInteractive analysis) {
		this.globalAnalysis = analysis;
	}
	
	public IInteractive getAnalysis() {
		return this.globalAnalysis;
	}
	
	public IInteractive createAnalysis(String hashFile) {
		// TODO Auto-generated method stub
		if (hashValue == null) {
			hashValue = readJSON();
		}
		
		String temp = hashValue.get(hashFile);
		if (temp != null) {
			switch (temp) {
				case "EMDIVI":
					return new AnalysisEmdivi();	
				case "7ee26210bd9ef92fa1e522e46922df29":
					return new AnalysisFile1();
				case "99255fdbf1add2caad0f0de226d3f441":
					return new AnalysisFile2();
				case "api_test_morphnah.exe":
					return new AnalysisFile3();
				case "hostname_mew.exe":
					return new AnalysisFile4();
				case "multiThread7.exe":
					return new AnalysisFile5();
				case "api_test_telock.exe":
					return new AnalysisTelock();
				case "Petite_grpconv.test":
					return new AnalysisFile6();
				case "se01.exe":
					return new AnalysisDemoSE01();
				case "se02.exe":
					return new AnalysisDemoSE02();
				case "maze.exe":
					return new SENMaze();
				case "se03.exe":
					return new SEN03();
				case "flareon_challenge_2015_re1.exe":
					//return new FlareOn2015re1();
					return new AnalysisBase(true, true);
				case "flareon_challenge_2015_re2.exe":
					return new FlareOn2015re2();
				case "Petite_2.2_demo1.test":
					return new PetiteDemo1();
				case "helloworld.exe":
					return new AnalysisHelloWorld();
				case "ws2_32.exe":
					return new AnalysisWS2_32();
				case "0ca027edda0e005c91c378e2f2fd3d51855d901e9c173a4a79698b987b2cf5e6":
					return new AnalysisFile7();
				case "wannacryv1.bin":
					//return new WannaCryAnalysis();
					return new AnalysisBase(true, isLogInstr, isLogAPI);
			}
		}
		
		return new AnalysisBase(true, isLogInstr, isLogAPI);
	}
	
	private Map<String, String> readJSON() {
		Map<String, String> ret = new HashMap<> ();

		JSONParser jParser = new JSONParser();
		try {
			JSONArray jArray = (JSONArray) jParser.parse(new FileReader(FILE_HASH));
			for (Object o : jArray) {

				JSONObject pSignature = (JSONObject) o;

				String fileName = (String) pSignature.get(FILE_NAME_TAG);
				String hashValue = (String) pSignature.get(HASH_TAG);
				ret.put(hashValue, fileName);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ret;

	}

	public void setLogInstr(boolean b) {
		// TODO Auto-generated method stub
		isLogInstr = b;
	}

	public void setLogAPI(boolean b) {
		// TODO Auto-generated method stub
		isLogAPI = b;
	}
	
	public void setAutoGenerateSymbolic(boolean b) {
		// TODO Auto-generated method stub
		isAutoGenerateSymbolic = b;
	}
	
	public boolean getIsLogInstr() {
		return isLogInstr;
	}
	
	public boolean getIsLogAPI() {
		return isLogAPI;
	}
	
	public boolean getIsAutoGenerateSymbolic() {
		return isAutoGenerateSymbolic;
	}
	
}
