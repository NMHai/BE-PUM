package v2.org.analysis.comparison;

public abstract class Comparison {
	private String toolName = "";

	/**
	 * @return the toolName
	 */
	public String getToolName() {
		return toolName;
	}

	/**
	 * @param toolName
	 *            the toolName to set
	 */
	public void setToolName(String toolName) {
		this.toolName = toolName;
	}

	public abstract void compare(String fileName);
}
