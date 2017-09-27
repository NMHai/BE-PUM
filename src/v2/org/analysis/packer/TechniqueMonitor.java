package v2.org.analysis.packer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.jakstab.Program;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import v2.org.analysis.packer.techniques.AntiDebugging;
import v2.org.analysis.packer.techniques.Checksumming;
import v2.org.analysis.packer.techniques.CodeChunking;
import v2.org.analysis.packer.techniques.HardwareBPX;
import v2.org.analysis.packer.techniques.IndirectJump;
import v2.org.analysis.packer.techniques.ObfuscatedConstant;
import v2.org.analysis.packer.techniques.OverlappingBlockV1;
import v2.org.analysis.packer.techniques.OverlappingBlockV2;
import v2.org.analysis.packer.techniques.OverlappingFunctionV1;
import v2.org.analysis.packer.techniques.OverlappingFunctionV2;
import v2.org.analysis.packer.techniques.OverwritingV2;
import v2.org.analysis.packer.techniques.PackerTechnique;
import v2.org.analysis.packer.techniques.PackingUnpackingV2;
import v2.org.analysis.packer.techniques.SEH;
import v2.org.analysis.packer.techniques.StolenBytes;
import v2.org.analysis.packer.techniques.TimingCheck;
import v2.org.analysis.packer.techniques.TwoSpecialAPIs;
import v2.org.analysis.path.BPState;
import v2.org.analysis.statistics.FileProcess;

public class TechniqueMonitor {
	class PackerCandidate {
		private String packerName;
		private int matchWeight;
		private double distance;
		private long addr;
		// public PackerCandidate(String packerName, int matchWeight, double
		// degree) {
		// this.packerName = packerName;
		// this.matchWeight = matchWeight;
		// this.distance = degree;
		// }

		public PackerCandidate(String packerName, int matchWeight, double degree, long addr) {
			this.packerName = packerName;
			this.matchWeight = matchWeight;
			this.distance = degree;
			this.addr = addr;
		}

		public double getDistance() {
			return distance;
		}

		public int getMatchWeight() {
			return matchWeight;
		}

		public String getPackerName() {
			// TODO Auto-generated method stub
			return packerName;
		}

		public void setDistance(double degree) {
			this.distance = degree;
		}

		public void setMatchWeight(int matchWeight) {
			this.matchWeight = matchWeight;
		}

		@Override
		public String toString() {
			return packerName + "_" + matchWeight + "_" + distance + "\t" + "0x" + Long.toHexString(addr);
		}

		public String getDetailed() {
			return packerName + "_" + matchWeight + "_" + distance;
		}

		public void setAddr(long addr) {
			// TODO Auto-generated method stub
			this.addr = addr;
		}

		public long getAddr() {
			// TODO Auto-generated method stub
			return this.addr;
		}
	}

	public static final String ENTRY_POINT_TAG = "IS_ENTRY_POINT";
	public static final String EPSILON_TAG = "EPSILON";

	public static final String FREQUENCY_TAG = "FREQUENCY_SIGNATURE";
	public static final String HEADER_SIGNATURE = "data/data/PackerSignature/header_signature.json";
	public static final String PACKER_NAME_TAG = "PACKER_NAME";
	public static final String POSITION_TAG = "POSITION";
	public static final String SIGNATURE_TAG = "SIGNATURE";
	public static final String TECHNIQUE_SIGNATURE = "data/data/PackerSignature/technique_signature.json";
	public static final String FREQUENCY_TEMP = "data/data/frequencyTemp.txt";
	public static final int THREADSHOLD = 5;
	public static final String THRESHOLD_TAG = "THRESHOLD";
	public static final String VERSION_TAG = "VERSION";
	public static final String WEIGHT_TAG = "WEIGHT";
	private String binaryDetec = "", semanticDetect = "", expectPackerByChiSquare = "";
	private ArrayList<HeaderSignature> pHeader;
	private ArrayList<PackerTechnique> techList;
	private String techOrder, posOrder; // Store the technique order and
										// position
	private ArrayList<TechniqueSignature> tSignature;

	private List<PackerCandidate> candiadateList;

	// Chi-square test
	private double distanceByCosin = 0;

	public TechniqueMonitor() {
		techList = new ArrayList<>();
		techList.add(new AntiDebugging());
		techList.add(new Checksumming());
		techList.add(new CodeChunking());
		techList.add(new IndirectJump());
		techList.add(new ObfuscatedConstant());
		// techList.add(new OverlappingBlockV1());
		techList.add(new OverlappingBlockV2());
		// techList.add(new OverlappingFunctionV1());
		techList.add(new OverlappingFunctionV2());
		techList.add(new OverwritingV2());
		techList.add(new PackingUnpackingV2());
//		techList.add(new Overwriting());
//		techList.add(new PackingUnpacking());
		techList.add(new SEH());
		techList.add(new StolenBytes());
		techList.add(new TimingCheck());
		techList.add(new TwoSpecialAPIs());
		techList.add(new HardwareBPX());
		techOrder = "";
		posOrder = "";

		pHeader = getHeaderSignature();
		tSignature = getTechniqueSignature();
		candiadateList = new ArrayList();
	}

	private double calChiSquare(double expectedValue, int sampleValue) {
		// TODO Auto-generated method stub
		if (expectedValue == 0) {
			return Double.MAX_VALUE;
		}

		return ((expectedValue - sampleValue) * (expectedValue - sampleValue)) / expectedValue;
	}

	private int calculateMatchWeight(double[] weight, double[] expectFrequency) {
		// TODO Auto-generated method stub
		int ret = 0;
		for (int i = 0; i < weight.length; i++) {
			int observedFreq = getTechnique(i).getFrequency();

			if ((weight[i] == 0 && observedFreq > 0) || (weight[i] > 0.5 && observedFreq == 0)
					|| (expectFrequency[i] < 5 && Math.abs(expectFrequency[i] - observedFreq) > 1)) {
				ret++;
			}
		}

		return ret;
	}

	private double calMembershipDegree(double epsilon, double[] expectFrequency, double[] weights) {
		// TODO Auto-generated method stub

		// If this is the packer, it must apply 2APIs and Packing/unpacking
		if ((getTechnique(PackerConstants.PACKING_UNPACKING).getFrequency() == 0
				&& getTechnique(PackerConstants.STOLEN_BYTES).getFrequency() == 0)
				|| getTechnique(PackerConstants.TWO_APIS).getFrequency() == 0) {
			return -1;
		}

		if (epsilon > 0) {
			double memDegree = 0;
			int num = 0;
			// int diffNum = 0;
			for (int i = 0; i < expectFrequency.length; i++) {
				int observedFreq = getTechnique(i).getFrequency();
				// if (weights[i] > 0.9 && observedFreq == 0) {
				// return -1;
				// }
				//
				// if (expectFrequency[i] > 1 && observedFreq == 0) {
				// return -1;
				// }
				//
				// if (expectFrequency[i] < 5 && Math.abs(expectFrequency[i] -
				// observedFreq) > 2) {
				// return -1;
				// }

				if (expectFrequency[i] > 0) {
					num++;

					// Special Case: since the detection of OBFUSCATED_CONST may
					// have some problem
					// if (i == PackerConstants.OBFUSCATED_CONST) {
					// if (calChiSquare(expectFrequency[i], observedFreq) <=
					// epsilon || expectFrequency[i] < observedFreq) {
					// memDegree += weights[i];
					// }
					// } else
					if (calChiSquare(expectFrequency[i], observedFreq) <= epsilon
							|| expectFrequency[i] < observedFreq) {
						memDegree += weights[i];
					}
				}
			}

			return memDegree / num;
		}

		return -1;
	}

	public void checkAPIName(String api, long value) {
		// TODO Auto-generated method stub
		for (PackerTechnique pTech : techList) {
			if (pTech.checkAPIName(api, value)) {
				detectNewObfuscationTechnique(pTech.getID(), value);
				return;
			}
		}
	}

	private double cosineSimilarity(double[] vectorA, double[] vectorB) {
		double dotProduct = 0.0;
		double normA = 0.0;
		double normB = 0.0;
		for (int i = 0; i < vectorA.length; i++) {
			dotProduct += vectorA[i] * vectorB[i];
			normA += Math.pow(vectorA[i], 2);
			normB += Math.pow(vectorB[i], 2);
		}
		return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
	}

	private void detectNewObfuscationTechnique(int techID, long addr) {
		techOrder += techID + "_";
		posOrder += "0x" + Long.toHexString(addr) + "_";
		String ret = detectViaSemanticSignature(addr);

		if (ret != "" && ret != "NONE") {
			// System.out.println("Detect Packer " + ret + " at " + "0x" +
			// Long.toHexString(addr));
			FileProcess frequency = new FileProcess(FREQUENCY_TEMP);
			frequency.appendFile(Program.getProgram().getFileName() + "\t" + ret + "\t" + getFrequencyTechniques()
					+ "\t" + "0x" + Long.toHexString(addr));
		}
	}

	private String detectViaBinarySignature() {
		// TODO Auto-generated method stub
		Program prog = Program.getProgram();
		long EP = Long.parseLong(Integer.toBinaryString(prog.getDoubleWordValueMemory(prog.getEntryPoint())), 2);

		// Read file
		String file = prog.getAbsolutePathFile();
		Path path = Paths.get(file);
		String[] dataString = null;
		try {
			byte[] dataByte = Files.readAllBytes(path);
			dataString = new String[dataByte.length];
			for (int i = 0; i < dataByte.length; i++) {
				dataString[i] = String.format("%02x", dataByte[i]);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (HeaderSignature hP : pHeader) {
			if (detectWithHeaderSignature(dataString, hP, EP)) {
				return hP.getPackerName();
			}
		}

		return "NONE";

	}

	private String detectViaChiSquare(long addr) {
		if (binaryDetec == "") {
			binaryDetec = detectViaBinarySignature();
		}

		String candidate = "\t";

		for (TechniqueSignature technique : tSignature) {
			double degree = calMembershipDegree(technique.getEpsilon(), technique.getFrequencySet(),
					technique.getWeights());
			if (degree >= technique.getThreshold()) {
				int matchWeight = calculateMatchWeight(technique.getWeights(), technique.getFrequencySet());
				double distance = cosineSimilarity(technique.getFrequencySet(), getFrequencySet());
				if (matchWeight == 0 // ||
										// binaryDetec.toLowerCase().contains(technique.getPackerName().toLowerCase())
				) {
					candidate += technique.getPackerName() + "v" + technique.getPackerVersion() + "_" + distance + ";";

					// if (compareString(binaryDetec, technique.getPackerName(),
					// 3)) {
					// expectPackerByChiSquare = technique.getPackerName() + "v"
					// + technique.getPackerVersion() + '\t' + "0x" +
					// Long.toHexString(addr);
					// distanceByCosin = 1;
					// } else

					if (expectPackerByChiSquare == "" || distanceByCosin < distance) {
						expectPackerByChiSquare = technique.getPackerName() + "v" + technique.getPackerVersion() + '\t'
								+ "0x" + Long.toHexString(addr);
						distanceByCosin = distance;
						// return technique.getPackerName();
					}
				} else {
					updateCandidate(technique.getPackerName() + "v" + technique.getPackerVersion(), matchWeight,
							distance, addr);
				}
			}
		}

		if (expectPackerByChiSquare != "NONE" && expectPackerByChiSquare != "") {
			expectPackerByChiSquare = expectPackerByChiSquare + candidate;
		}

		return expectPackerByChiSquare != "" ? expectPackerByChiSquare : "NONE";
	}

	private boolean compareString(String str1, String str2, int num) {
		// TODO Auto-generated method stub
		if (str1.length() < num || str2.length() < num) {
			return false;
		}
		
		String t1 = str1.substring(0, num).toLowerCase();
		String t2 = str2.substring(0, num).toLowerCase();
		return t1.equals(t2);
	}

	private String detectViaSemanticOrder() {
		// TODO Auto-generated method stub
		String[] tech = techOrder.split("_");
		String[] pos = posOrder.split("_");

		if (tech.length != pos.length) {
			return "Length technique is not equal";
		}

		for (TechniqueSignature tP : tSignature) {
			String result = tP.getPackerName() + " v" + tP.getPackerVersion() + "\t";
			String[] sig = tP.getTechiqueSignature();
			String[] po = tP.getTechniquePosition();
			for (int i = 0; i < sig.length && i < tech.length; i++) {
				if (tech[i] != null && !tech[i].equals(sig[i])) {
					if (i < THREADSHOLD) {
						result = "NONE \t" + i + ":" + sig[i] + "_" + po[i] + " vs " + tech[i] + "_" + pos[i] + "\t";
						break;
					} else {
						result += i + ":" + sig[i] + "_" + po[i] + " vs " + tech[i] + "_" + pos[i] + "\t";
					}
				}
			}
			if (result != "") {
				return result;
			}
		}
		return "NONE";
	}

	private String detectViaSemanticSignature(long addr) {
		// TODO Auto-generated method stub
		String ret = detectViaChiSquare(addr);
		if (expectPackerByChiSquare != "") {
			semanticDetect = expectPackerByChiSquare;
		} else {
			semanticDetect = "NONE";
		}

		return ret;
	}

	private boolean detectWithHeaderSignature(String[] dataString, HeaderSignature hPacker, long EP) {
		if (dataString == null) {
			return false;
		}

		int beginTracing = 0;
		int endTracing = 0;
		int pivot = 0;
		if (EP != 0x0) {
			for (int i = 0; i < dataString.length - 3; i++) {
				String dword = dataString[i + 3] + dataString[i + 2] + dataString[i + 1] + dataString[i];
				long dwordL = Long.parseLong(dword, 16);
				if (dwordL == EP) {
					pivot = i;
					break;
				}
			}
		}
		if (hPacker.isEntryPoint()) {
			beginTracing = pivot;
			endTracing = pivot + hPacker.getPackerSignature().length + 1;
		} else {
			beginTracing = 0;
			endTracing = pivot;
		}

		boolean trace = true;
		String[] sArr = hPacker.getPackerSignature();
		for (int i = beginTracing; i < endTracing && trace; i++) {
			if (dataString[i].equals(sArr[0].toLowerCase())
					&& (dataString[i + 1].equals(sArr[1].toLowerCase()) || sArr[1].equals("??"))) {
				for (int j = 0; j < sArr.length; j++) {
					if (i + j < dataString.length) {
						if (!sArr[j].toLowerCase().equals(dataString[i + j]) && !sArr[j].equals("??")) {
							break;
						} else if (j == sArr.length - 1) {
							trace = false;
						}
					} else {
						return false;
					}
				}
			} else {
				if (hPacker.isEntryPoint() || i == endTracing - 2) {
					return false;
				}
			}
		}
		return true;
	}

	public String getBinaryDetectionResult() {
		if (binaryDetec == "") {
			binaryDetec = detectViaBinarySignature();
		}

		return binaryDetec;
	}

	public String getDetectionDetail() {
		// TODO Auto-generated method stub
		String ret = "";

		for (PackerTechnique techSignature : techList) {
			ret += techSignature.toString() + "\n";
		}

		return ret;
	}

	public String getDetectionResult() {
		return getBinaryDetectionResult() + "\t" + getSemanticDetectionResult();
	}

	private double[] getFrequencySet() {
		// TODO Auto-generated method stub
		double[] set = new double[techList.size()];
		for (int i = 0; i < techList.size(); i++) {
			set[i] = getTechnique(i).getFrequency();
		}

		return set;
	}

	public String getFrequencyTechniques() {
		String techniques = "";

		techniques += getTechnique(PackerConstants.ANTI_DEBUGGING).getFrequency() + "\t";
		techniques += getTechnique(PackerConstants.CHECKSUMMING).getFrequency() + "\t";
		techniques += getTechnique(PackerConstants.CODE_CHUNKING).getFrequency() + "\t";
		techniques += getTechnique(PackerConstants.INDIRECT_JUMP).getFrequency() + "\t";
		techniques += getTechnique(PackerConstants.OBFUSCATED_CONST).getFrequency() + "\t";
		techniques += getTechnique(PackerConstants.OVERLAPPING_BLOCK).getFrequency() + "\t";
		techniques += getTechnique(PackerConstants.OVERLAPPING_FUNC).getFrequency() + "\t";
		techniques += getTechnique(PackerConstants.OVERWRITING).getFrequency() + "\t";
		techniques += getTechnique(PackerConstants.PACKING_UNPACKING).getFrequency() + "\t";
		techniques += getTechnique(PackerConstants.SEH).getFrequency() + "\t";
		techniques += getTechnique(PackerConstants.STOLEN_BYTES).getFrequency() + "\t";
		techniques += getTechnique(PackerConstants.TIMING_CHECK).getFrequency() + "\t";
		techniques += getTechnique(PackerConstants.TWO_APIS).getFrequency() + "\t";
		techniques += getTechnique(PackerConstants.HARDWARE_BPX).getFrequency();

		return techniques;
	}

	private ArrayList<HeaderSignature> getHeaderSignature() {
		ArrayList<HeaderSignature> hPacker = new ArrayList<>();

		JSONParser jParser = new JSONParser();
		try {
			JSONArray jArray = (JSONArray) jParser.parse(new FileReader(HEADER_SIGNATURE));
			for (Object o : jArray) {

				JSONObject pSignature = (JSONObject) o;

				String packerName = (String) pSignature.get(PACKER_NAME_TAG);
				String packerVersion = (String) pSignature.get(VERSION_TAG);
				boolean isEntryPoint = ((String) pSignature.get(ENTRY_POINT_TAG)).contains("YES");
				String[] sArr = parseHeaderSignature((String) pSignature.get(SIGNATURE_TAG));

				HeaderSignature pHeader = new HeaderSignature(packerName, packerVersion, isEntryPoint, sArr);
				hPacker.add(pHeader);
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

		return hPacker;
	}

	public String getPackerTechniques() {
		String techniques = "";

		techniques += ((getTechnique(PackerConstants.ANTI_DEBUGGING).hasTechnique()) ? "1" : "0") + "\t";
		techniques += ((getTechnique(PackerConstants.CHECKSUMMING).hasTechnique()) ? "1" : "0") + "\t";
		techniques += ((getTechnique(PackerConstants.CODE_CHUNKING).hasTechnique()) ? "1" : "0") + "\t";
		techniques += ((getTechnique(PackerConstants.INDIRECT_JUMP).hasTechnique()) ? "1" : "0") + "\t";
		techniques += ((getTechnique(PackerConstants.OBFUSCATED_CONST).hasTechnique()) ? "1" : "0") + "\t";
		techniques += ((getTechnique(PackerConstants.OVERLAPPING_BLOCK).hasTechnique()) ? "1" : "0") + "\t";
		techniques += ((getTechnique(PackerConstants.OVERLAPPING_FUNC).hasTechnique()) ? "1" : "0") + "\t";
		techniques += ((getTechnique(PackerConstants.OVERWRITING).hasTechnique()) ? "1" : "0") + "\t";
		techniques += ((getTechnique(PackerConstants.PACKING_UNPACKING).hasTechnique()) ? "1" : "0") + "\t";
		techniques += ((getTechnique(PackerConstants.SEH).hasTechnique()) ? "1" : "0") + "\t";
		techniques += ((getTechnique(PackerConstants.STOLEN_BYTES).hasTechnique()) ? "1" : "0") + "\t";
		techniques += ((getTechnique(PackerConstants.TIMING_CHECK).hasTechnique()) ? "1" : "0") + "\t";
		techniques += ((getTechnique(PackerConstants.TWO_APIS).hasTechnique()) ? "1" : "0") + "\t";
		techniques += (getTechnique(PackerConstants.HARDWARE_BPX).hasTechnique()) ? "1" : "0";

		return techniques;
	}

	public String getPosOrder() {
		return posOrder;
	}

	public String getSemanticDetectionResult() {
		if (semanticDetect != "" && semanticDetect != "NONE") {
			return semanticDetect;
		}

		String ret = "NONE";
		int matchWeight = techList.size();
		double expectDistance = -1;
		String candidate = "\t";
		for (PackerCandidate packer : candiadateList) {
			candidate += packer.getDetailed() + ";";

			// Special case: Binary produces the same results with Semantic
			if (compareString(binaryDetec, packer.getPackerName(), 3)) {
				if (matchWeight > 0 || (matchWeight == 0 && packer.getDistance() > expectDistance)) {
					matchWeight = 0;
					expectDistance = packer.getDistance();
					if (((TwoSpecialAPIs) getTechnique(PackerConstants.TWO_APIS)).isFirstCall()) {
						ret = "CustomPacked_" + packer.toString();
					} else {
						ret = "Near_" + packer.toString();
					}
					// Stop the checking
					semanticDetect = ret; 
				}
			} else if (packer.getMatchWeight() < matchWeight
					|| (packer.getMatchWeight() == matchWeight && packer.getDistance() > expectDistance)) {
				// if (packer.getDistance() > expectDistance) {

				matchWeight = packer.getMatchWeight();
				expectDistance = packer.getDistance();
				if (((TwoSpecialAPIs) getTechnique(PackerConstants.TWO_APIS)).isFirstCall()) {
					ret = "CustomPacked_" + packer.toString();
				} else {
					ret = "Near_" + packer.toString();
				}
			}
		}

		if (ret == "NONE") {
			if (((TwoSpecialAPIs) getTechnique(PackerConstants.TWO_APIS)).isFirstCall()) {
				ret = "CustomPacked_Unknown";
			}

			ret += "\t" + "0x" + Long.toHexString(0);
		}

		return ret + candidate;
	}

	public PackerTechnique getTechnique(int id) {
		for (PackerTechnique pTech : techList) {
			if (pTech.getID() == id) {
				return pTech;
			}
		}
		return null;
	}

	public String getTechniqueOrder() {
		return techOrder;
	}

	private ArrayList<TechniqueSignature> getTechniqueSignature() {
		ArrayList<TechniqueSignature> tPacker = new ArrayList<>();

		JSONParser jParser = new JSONParser();
		try {
			JSONArray jArray = (JSONArray) jParser.parse(new FileReader(TECHNIQUE_SIGNATURE));
			for (Object o : jArray) {

				JSONObject pSignature = (JSONObject) o;

				String packerName = (String) pSignature.get(PACKER_NAME_TAG);
				String packerVersion = (String) pSignature.get(VERSION_TAG);
				String epsilon = (String) pSignature.get(EPSILON_TAG);
				String threshold = (String) pSignature.get(THRESHOLD_TAG);
				// boolean isEntryPoint = ((String)
				// pSignature.get(ENTRY_POINT_TAG)).contains("YES");
				String[] sArr = parseTechniqueSignature((String) pSignature.get(SIGNATURE_TAG));
				String[] pos = parseTechniqueSignature((String) pSignature.get(POSITION_TAG));
				String[] freq = parseTechniqueSignature((String) pSignature.get(FREQUENCY_TAG));
				String[] wei = parseTechniqueSignature((String) pSignature.get(WEIGHT_TAG));

				// StringBuilder str = new StringBuilder();
				// str.
				TechniqueSignature pHeader = new TechniqueSignature(packerName, packerVersion, sArr, pos, epsilon,
						threshold, freq, wei);
				tPacker.add(pHeader);
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

		return tPacker;
	}

	public boolean isPacked() {
		return semanticDetect != "NONE" && semanticDetect != "";
	}

	private String[] parseHeaderSignature(String signature) {
		String[] sArr = {};
		ArrayList<String> sList = new ArrayList<>();
		if (signature.length() % 2 != 0) {
			signature += "?";
		}
		for (int i = 0; i < signature.length(); i++) {
			if (i % 2 == 0) {
				sList.add(signature.substring(i, i + 2));
			}
		}
		return sList.toArray(sArr);
	}

	private String[] parseTechniqueSignature(String signature) {
		// TODO Auto-generated method stub
		if (signature == null) {
			return null;
		}

		if (signature.contains("_")) {
			return signature.split("_");
		}

		return signature.split(" ");
	}

	public void setHardwareBreakpointTechnique(long value) {
		PackerTechnique p = getTechnique(PackerConstants.HARDWARE_BPX);
		if (p != null) {
			if (!p.contain(value)) {
				((HardwareBPX) p).insertLocation(value);
				detectNewObfuscationTechnique(PackerConstants.HARDWARE_BPX, value);
			}
		}
	}

	public void setIndirectJumpTechnique(long value) {
		// TODO Auto-generated method stub
		PackerTechnique p = getTechnique(PackerConstants.INDIRECT_JUMP);
		// if (value == 4194688) {
		// System.out.println("Debug");
		// }

		if (p != null) {
			if (!p.contain(value)) {
				((IndirectJump) p).insertLocation(value);
				// techOrder += PackerConstants.INDIRECT_JUMP + "_";
				// setPosOrder(getPosOrder() + "0x" + Long.toHexString(value) +
				// "_");
				// posOrder += "0x" + Long.toHexString(value) + "_";
				detectNewObfuscationTechnique(PackerConstants.INDIRECT_JUMP, value);
			}
		}
	}

	public void setOverlapping(String instName, long curAddr, long nextAddr) {
		PackerTechnique p;
		if (instName.equals("call")) {
			p = getTechnique(PackerConstants.OVERLAPPING_FUNC);
			if (p != null && p instanceof OverlappingFunctionV1) {
				if (!p.contain(nextAddr)) {
					// if (((OverlappingFunction) p).checkCallAddr(nextAddr)) {
					// // techOrder += PackerConstants.OVERLAPPING_FUNC + "_";
					// // setPosOrder(getPosOrder() + "0x" +
					// // Long.toHexString(curAddr) + "_");
					// // posOrder += "0x" + Long.toHexString(curAddr) + "_";
					// detectNewOT(PackerConstants.OVERLAPPING_FUNC, curAddr);
					// ((OverlappingFunction) p).insertLocation(nextAddr);
					// }
					((OverlappingFunctionV1) p).insertCallAddr(nextAddr);
				}
			}
		} else if (instName.equals("ret")) {
			p = getTechnique(PackerConstants.OVERLAPPING_FUNC);
			if (p != null && p instanceof OverlappingFunctionV1) {
				if (!p.contain(curAddr)) {
					if (((OverlappingFunctionV1) p).checkRetAddr(curAddr)) {
						// techOrder += PackerConstants.OVERLAPPING_FUNC + "_";
						// setPosOrder(getPosOrder() + "0x" +
						// Long.toHexString(curAddr) + "_");
						// posOrder += "0x" + Long.toHexString(curAddr) + "_";
						detectNewObfuscationTechnique(PackerConstants.OVERLAPPING_FUNC, curAddr);
						((OverlappingFunctionV1) p).insertLocation(curAddr);
					}
					// ((OverlappingFunction) p).insertRetAddr(curAddr);
				}
			}
		} else if (instName.equals("jmp")) {
			p = getTechnique(PackerConstants.OVERLAPPING_BLOCK);
			if (p != null && p instanceof OverlappingFunctionV1) {
				if (!p.contain(curAddr)) {
					if (((OverlappingBlockV1) p).checkJmpAddr(curAddr)) {
						// techOrder += PackerConstants.OVERLAPPING_BLOCK + "_";
						// setPosOrder(getPosOrder() + "0x" +
						// Long.toHexString(curAddr) + "_");
						// posOrder += "0x" + Long.toHexString(curAddr) + "_";
						detectNewObfuscationTechnique(PackerConstants.OVERLAPPING_BLOCK, curAddr);
						((OverlappingBlockV1) p).insertLocation(curAddr);
					}
					((OverlappingBlockV1) p).insertJmpAddr(curAddr, nextAddr);
				}
			}
		}
	}

	public void setPosOrder(String posOrder) {
		this.posOrder = posOrder;
	}

	public void setSEHTechnique(long value) {
		// TODO Auto-generated method stub
		PackerTechnique p = getTechnique(PackerConstants.SEH);
		if (p != null) {
			if (!p.contain(value)) {
				((SEH) p).insertLocation(value);
				// techOrder += PackerConstants.SEH + "_";
				// setPosOrder(getPosOrder() + "0x" + Long.toHexString(value) +
				// "_");
				// posOrder += "0x" + Long.toHexString(value) + "_";
				detectNewObfuscationTechnique(PackerConstants.SEH, value);
			}
		}
	}

	public void setTechniqueOrder(String techOrder) {
		this.techOrder = techOrder;
	}

	@Override
	public String toString() {
		String ret = "";
		for (PackerTechnique p : techList) {
			ret += p.toString() + "\n";
		}
		return ret;
	}

	private void updateCandidate(String packerName, int matchWeight, double distance, long addr) {
		// TODO Auto-generated method stub
		for (PackerCandidate p : candiadateList) {
			if (p.getPackerName().equals(packerName)) {
				if (matchWeight < p.getMatchWeight()
						|| (matchWeight == p.getMatchWeight() && distance > p.getDistance())) {
					p.setMatchWeight(matchWeight);
					p.setDistance(distance);
					p.setAddr(addr);
				}

				return;
			}
		}

		candiadateList.add(new PackerCandidate(packerName, matchWeight, distance, addr));
	}

	public void updateChecking(BPState curState, Program program) {
		if (curState != null) {
			// BEGIN TO STATISTIC
			// pCounter.setCountingState(curState, program);
			// pCounter.Execute(this.USING_COUNT);
			// pPattern.setCheckingState(pCounter);
			// this.checkingState();
			for (PackerTechnique pTech : techList) {
				if (pTech.check(curState, program)) {
					// techOrder += pTech.getID() + "_";
					// posOrder += curState.getLocation() + "_";
					detectNewObfuscationTechnique(pTech.getID(), curState.getLocation().getValue());
				}
			}
		}
	}
}
