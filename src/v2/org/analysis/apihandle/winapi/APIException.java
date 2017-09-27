/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi
 * File name: APIException.java
 * Created date: Jul 25, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * @author Yen Nguyen
 *
 */
public class APIException extends Exception {

	private static final long serialVersionUID = -4195129803051214178L;
	private static Logger logger = Logger.getLogger(APIException.class.getName());

	static {
		FileHandler fh;
		try {

			// This block configure the logger with handler and formatter
			fh = new FileHandler("APIException.log", true);
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private API api = null;

	public APIException(API winAPI) {
		this.api = winAPI;
	}

	public APIException(String message) {
		super(message);
	}

	public APIException(Throwable cause) {
		super(cause);
	}

	public APIException(String message, Throwable cause) {
		super(message, cause);
	}

	public APIException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public void writeLog() {
		logger.log(Level.WARNING, "Call Windows API with symbolic arguments: " + this.api.getFullName());
	}
}
