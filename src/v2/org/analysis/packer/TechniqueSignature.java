package v2.org.analysis.packer;

public class TechniqueSignature {

	private String[] techniqueSignature;
	private String[] techniquePos;
	private String packerName;
	private String packerVersion;
	private double epsilon = 0, threshold = 0; 
	private double[] frequencies = null;
	private double[] weights = null;
	
	public String getPackerVersion() {
		return packerVersion;
	}

	public void setPackerVersion(String packerVersion) {
		this.packerVersion = packerVersion;
	}

//	public TechniqueSignature (String pName, String pTechSign)	{
//		packerName 		= pName;
//		techniqueSignature	= pTechSign;
//		packerVersion = "";
//	}
//	
//	public TechniqueSignature (String pName, String version, String pTechSign) {
//		packerName 		= pName;
//		techniqueSignature	= pTechSign;
//		packerVersion = version;
//	}
	
	public TechniqueSignature (String pName, String version, String[] pTechSign, 
			String[] pos){
		// TODO Auto-generated constructor stub
		packerName 		= pName;
		techniqueSignature	= pTechSign;
		packerVersion = version;
		techniquePos = pos;
		
		if (techniquePos.length != techniqueSignature.length) {
			System.out.println("Signature " + packerName + " is wrong");
		}
	}

	public TechniqueSignature(String pName, String version, String[] pTechSign, 
			String[] pos, String eps, String thres, String[] freq, String[] wei) {
		// TODO Auto-generated constructor stub
		packerName 		= pName;
		techniqueSignature	= pTechSign;
		packerVersion = version;
		techniquePos = pos;
		
		if (techniquePos.length != techniqueSignature.length) {
			System.out.println("Signature " + packerName + " is wrong");
		}	
		
		if (eps != "" && eps != null) {
			epsilon = Double.parseDouble(eps);
		}
		
		if (thres != "" && thres != null) {
			threshold = Double.parseDouble(thres);
		}
		
		if (freq != null) {
			frequencies = new double[freq.length];
			for (int i=0; i<freq.length; i++) {
				frequencies[i] = Double.parseDouble(freq[i]);
			}
		}
		
		if (wei != null) {
			weights = new double[wei.length];
			for (int i=0; i<wei.length; i++) {
				weights[i] = Double.parseDouble(wei[i]);
			}
		}
	}

	public String getPackerName ()
	{
		return packerName;
	}
	
	public String[] getTechiqueSignature() {
		return techniqueSignature;
	}

	public String[] getTechniquePosition() {
		return techniquePos;
	}

	public void setTechniquePosition(String[] techniquePos) {
		this.techniquePos = techniquePos;
	}

	public double getEpsilon() {
		return epsilon;
	}

	public void setEpsilon(long epsilon) {
		this.epsilon = epsilon;
	}

	public double getThreshold() {
		return threshold;
	}

	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}

	public double[] getFrequencySet() {
		return frequencies;
	}

	public void setFrequencySet(double[] frequencies) {
		this.frequencies = frequencies;
	}

	public double[] getWeights() {
		return weights;
	}

	public void setWeights(double[] weights) {
		this.weights = weights;
	}
	
}
