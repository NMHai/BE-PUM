package v2.org.analysis.system;

public class Window {
	private String className;
	private long windowHandle;
	private long windowName;

	public Window(String className, long windowName) {
		super();
		this.className = className;
		this.windowName = windowName;
	}

	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @param className
	 *            the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * @return the windowHandle
	 */
	public long getWindowHandle() {
		return windowHandle;
	}

	/**
	 * @param windowHandle
	 *            the windowHandle to set
	 */
	public void setWindowHandle(long windowHandle) {
		this.windowHandle = windowHandle;
	}

	/**
	 * @return the windowName
	 */
	public long getWindowName() {
		return windowName;
	}

	/**
	 * @param windowName
	 *            the windowName to set
	 */
	public void setWindowName(long windowName) {
		this.windowName = windowName;
	}

	public long sendMessage(String msg, long firstParam, long secondParam) {
		// TODO Auto-generated method stub
		return 1;
	}

	public long postMessage(String str, long t3, long t4) {
		// TODO Auto-generated method stub
		return 1;
	}
}
