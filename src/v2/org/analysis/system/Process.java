/**
 * 
 */
package v2.org.analysis.system;

/**
 * @author MinhHai
 * 
 */
public class Process {
	private String moduleFileName, commanLine, curDir, pStarupInfo;
	private long pAttribute, tAttribute, handleFlag, creationFlag, envBlock, pInformation;

	public Process(String moduleFileName, String commandLine, long t3, long t4, long t5, long t6, long t7,
			String curDir, String pStarupInfo, long t10) {
		// TODO Auto-generated constructor stub
		this.setModuleFileName(moduleFileName);
		this.setCommanLine(commandLine);
		this.setpAttribute(t3);
		this.settAttribute(t4);
		this.setHandleFlag(t5);
		this.setCreationFlag(t6);
		this.setEnvBlock(t7);
		this.setCurDir(curDir);
		this.setpStarupInfo(pStarupInfo);
		this.setpInformation(t10);
	}

	/**
	 * @return the moduleFileName
	 */
	public String getModuleFileName() {
		return moduleFileName;
	}

	/**
	 * @param moduleFileName
	 *            the moduleFileName to set
	 */
	public void setModuleFileName(String moduleFileName) {
		this.moduleFileName = moduleFileName;
	}

	/**
	 * @return the commanLine
	 */
	public String getCommanLine() {
		return commanLine;
	}

	/**
	 * @param commanLine
	 *            the commanLine to set
	 */
	public void setCommanLine(String commanLine) {
		this.commanLine = commanLine;
	}

	/**
	 * @return the curDir
	 */
	public String getCurDir() {
		return curDir;
	}

	/**
	 * @param curDir
	 *            the curDir to set
	 */
	public void setCurDir(String curDir) {
		this.curDir = curDir;
	}

	/**
	 * @return the pStarupInfo
	 */
	public String getpStarupInfo() {
		return pStarupInfo;
	}

	/**
	 * @param pStarupInfo
	 *            the pStarupInfo to set
	 */
	public void setpStarupInfo(String pStarupInfo) {
		this.pStarupInfo = pStarupInfo;
	}

	/**
	 * @return the pAttribute
	 */
	public long getpAttribute() {
		return pAttribute;
	}

	/**
	 * @param pAttribute
	 *            the pAttribute to set
	 */
	public void setpAttribute(long pAttribute) {
		this.pAttribute = pAttribute;
	}

	/**
	 * @return the tAttribute
	 */
	public long gettAttribute() {
		return tAttribute;
	}

	/**
	 * @param tAttribute
	 *            the tAttribute to set
	 */
	public void settAttribute(long tAttribute) {
		this.tAttribute = tAttribute;
	}

	/**
	 * @return the handleFlag
	 */
	public long getHandleFlag() {
		return handleFlag;
	}

	/**
	 * @param handleFlag
	 *            the handleFlag to set
	 */
	public void setHandleFlag(long handleFlag) {
		this.handleFlag = handleFlag;
	}

	/**
	 * @return the creationFlag
	 */
	public long getCreationFlag() {
		return creationFlag;
	}

	/**
	 * @param creationFlag
	 *            the creationFlag to set
	 */
	public void setCreationFlag(long creationFlag) {
		this.creationFlag = creationFlag;
	}

	/**
	 * @return the envBlock
	 */
	public long getEnvBlock() {
		return envBlock;
	}

	/**
	 * @param envBlock
	 *            the envBlock to set
	 */
	public void setEnvBlock(long envBlock) {
		this.envBlock = envBlock;
	}

	/**
	 * @return the pInformation
	 */
	public long getpInformation() {
		return pInformation;
	}

	/**
	 * @param pInformation
	 *            the pInformation to set
	 */
	public void setpInformation(long pInformation) {
		this.pInformation = pInformation;
	}

}
