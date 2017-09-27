/**
 * 
 */
package v2.org.analysis.system;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MinhHai
 * 
 */
public class ProcessHandle {
	private List<Process> pList;

	public ProcessHandle() {
		pList = new ArrayList<Process>();
	}

	/**
	 * @return the pList
	 */
	public List<Process> getProcessList() {
		return pList;
	}

	/**
	 * @param pList
	 *            the pList to set
	 */
	public void setProcessList(List<Process> pList) {
		this.pList = pList;
	}

	public long createProcess(String moduleFileName, String commandLine, long t3, long t4, long t5, long t6, long t7,
			String curDir, String pStarupInfo, long t10) {
		// TODO Auto-generated method stub
		Process p = new Process(moduleFileName, commandLine, t3, t4, t5, t6, t7, curDir, pStarupInfo, t10);
		pList.add(p);

		return 1;
	}
}
