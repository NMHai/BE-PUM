package v2.org.analysis.json;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.jakstab.Program;
import org.jakstab.loader.pe.SectionHeader;
import org.json.simple.JSONArray;

import v2.org.analysis.statistics.FileProcess;
import v2.org.analysis.structure.TripleData;
import v2.org.analysis.structure.TripleList;

public class JSONManager {
	private static final String JSON_PATH = "";
	private JSONObj quickOverview, staticAnalysis, networkAnalysis, dropFile;
	private JSONArray behaviourAnalysis;
	private Program program;
	private DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private boolean exportJSON = false;
	
	public boolean isExportJSON() {
		return exportJSON;
	}

	public void setExportJSON(boolean exportJSON) {
		this.exportJSON = exportJSON;
	}

	/**
	 * Singleton instance of {@link JSONManager} class
	 */
	private static volatile JSONManager mInstance = null;

	/**
	 * Get singleton instance of this class
	 * 
	 * @return {@link JSONManager} instance
	 */
	public static synchronized JSONManager getInstance() {
		if (mInstance == null) {
			try {
				mInstance = new JSONManager();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mInstance;
	}

	public JSONManager() {
		quickOverview = new JSONObj();
		staticAnalysis = new JSONObj();
		behaviourAnalysis = new JSONArray();
		networkAnalysis = new JSONObj();
		dropFile = new JSONObj();
	}

	@SuppressWarnings("unchecked")
	public void importData(Program program) {
		this.program = program;
		quickOverview = getQuickOverviewJSON(program);
		staticAnalysis = getStaticAnalysisJSON(program);
	}

	private String getDropFileJSON(Program program) {
		// TODO Auto-generated method stub
		return "";
	}

	private String getNetworkAnalysisJSON(Program program) {
		// TODO Auto-generated method stub
		return "";
	}

	private String getBehaviourAnalysisJSON(Program program) {
		// TODO Auto-generated method stub
		return "";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JSONObj getStaticAnalysisJSON(Program program) {
		// TODO Auto-generated method stub
		JSONObj staticAnalysis = new JSONObj();

		// Dung helps me with this part
		staticAnalysis.add("strings", getStringProgram(program));

		JSONObj staticPart = new JSONObj();
		staticPart.add("pe inphash", program.getPEHash());

		staticPart.add("resource", getResourcePart(program));
		staticPart.add("imports", getImportPart(program));
		staticPart.add("section", getSectionPart(program));

		staticAnalysis.add("static analysis", staticPart);

		return staticAnalysis;
	}

	@SuppressWarnings("unchecked")
	private Object getSectionPart(Program program) {
		// TODO Auto-generated method stub
		JSONArray ja = new JSONArray();
		for (int i = 0; i < program.getSectionNum(); i++) {
			JSONObj temp = new JSONObj();
			SectionHeader section = program.getSection(i);
			temp.add("name", section.getName());
			temp.add("virtual address", section.getVirtualAddr());
			temp.add("virtual size", section.getVirtualSize());
			temp.add("size of raw data", section.getSizeOfRawData());
			temp.add("entropy", section.getEntropy());

			ja.add(temp);
		}

		return ja;
	}

	@SuppressWarnings("unchecked")
	private JSONArray getImportPart(Program program) {
		JSONArray result = new JSONArray();
		TripleList<String, String, Long> l = program.getImportTableTriple();

		if (l == null) {
			return result;
		}

		List<TripleData<String, String, Long>> temp = l.getList();

		for (TripleData<String, String, Long> t : temp) {
			JSONObj main = new JSONObj();
			main.add("name dll", t.getKey());

			JSONArray content = new JSONArray();

			Iterator<Entry<String, Long>> it = t.getValue().entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, Long> entry = it.next();
				JSONObj x = new JSONObj();
				x.add("address", "0x" + Long.toHexString(entry.getValue()));
				x.add("name", entry.getKey());
				content.add(x);
			}
			main.add("content", content);
			result.add(main);
		}

		return result;
	}

	private JSONArray getResourcePart(Program program) {
		// TODO Auto-generated method stub
		JSONArray result = new JSONArray();
		List<JSONObj> resourceList = program.getResourceJSONObj();
		if (resourceList != null && resourceList.size() > 0) {
			for (JSONObj resource: resourceList) {
				result.add(resource);
			}
		}
		
		return result;
	}

	private JSONArray getStringProgram(Program program) {
		// TODO Auto-generated method stub
		JSONArray result = new JSONArray();
		return result;
	}

	@SuppressWarnings("rawtypes")
	private JSONObj getQuickOverviewJSON(Program program) {
		// TODO Auto-generated method stub
		// insert Analysis part
		JSONObj<String, String> analysis = new JSONObj<String, String>();
		analysis.add("category", program.getCategory());
		analysis.add("started", "" + program.getStartDate());
		analysis.add("completed", "" + program.getEndDate());
		analysis.add("duration", "" + program.getDuration() + " seconds");

		JSONArray temp = new JSONArray();
		temp.add(analysis);

		// insert file detail part
		JSONObj<String, String> file_detail = new JSONObj<String, String>();
		file_detail.add("file name", program.getFileName());
		file_detail.add("file size", "" + program.getFileSize());
		file_detail.add("file type", program.getFileType());
		file_detail.add("md5", program.getFileMD5());
		file_detail.add("sha1", program.getFileSHA1());
		file_detail.add("crc32", program.getFileCRC32());
		file_detail.add("ssdeep", program.getFileSSDeep());
		file_detail.add("yara", program.getFileYara());

		// insert quick overview part
		JSONObj<String, String> quick_overview = new JSONObj<String, String>();
		quick_overview.add("analysis", temp);
		quick_overview.add("file detail", file_detail);
		return quick_overview;
	}

	@SuppressWarnings("unused")
	private JSONObj getSectionJSON(Program program) {
		// TODO Auto-generated method stub
		return null;
	}

	public void exportData(String fileName) {
		// insert behaviour analysis part
		if (!exportJSON) {
			return;
		}
		
		JSONObj mainObj = new JSONObj();

		// insert quick overview part
		mainObj.add("quick overview", getQuickOverviewJSON(program));

		// insert static analysis part
		mainObj.add("static analysis", staticAnalysis);

		mainObj.add("behavioral analysis", behaviourAnalysis);

		// insert network analysis part
		mainObj.add("network analysis", networkAnalysis);

		// insert drop files part
		mainObj.add("dropped files", dropFile);

		FileProcess file = new FileProcess(JSON_PATH + fileName + ".json");
		file.clearContentFile();
		file.writeFile(mainObj.toString());
	}

	@SuppressWarnings("unchecked")
	public void addAPIBehaviour(String api, long ret, List paramList) {
		if (!exportJSON) {
			return;
		}
		
		JSONObj apiObj = new JSONObj();
		apiObj.add("time", sdf.format(new Date().getTime()));
		apiObj.add("api", api);
		apiObj.add("return", ret);

		if (paramList != null && paramList.size() > 0) {
			int size = paramList.size();
			apiObj.add("param_count", size);
			for (int i = 0; i < size; i++) {
				apiObj.add("param" + (i + 1), paramList.get(i));
			}
		} else {
			apiObj.add("param_count", 0);
		}

		behaviourAnalysis.add(apiObj);
	}
}
