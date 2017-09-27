/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.log
 * File name: BPLogger.java
 * Created date: Sep 29, 2015
 * Description:
 */
package v2.org.analysis.log;

import org.apache.log4j.Logger;

/**
 * @author Yen Nguyen
 *
 */
public class BPLogger {
	
	public final static Logger reportLogger = Logger.getLogger("reportsLogger");
	public final static Logger debugLogger = Logger.getLogger("debugLogger");

	public BPLogger() {
		// TODO Auto-generated constructor stub
	}

}
