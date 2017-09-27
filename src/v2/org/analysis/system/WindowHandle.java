package v2.org.analysis.system;

import java.util.ArrayList;
import java.util.List;

public class WindowHandle {
	List<Window> wList;

	public long createWindow(String commandLine, long style) {
		return 1;
	}

	public WindowHandle() {
		wList = new ArrayList<Window>();
	}

	public long findWindow(String className, long value) {
		// TODO Auto-generated method stub
		/*
		 * Window w = new Window(className, value); long handle =
		 * Math.round(Math.random() * Math.pow(10, 8));
		 * w.setWindowHandle(handle); return handle;
		 */
		for (Window w : wList) {
			if (w != null && w.getClassName().equals(className))
				return w.getWindowHandle();
		}

		return 0;
	}

	public long sendMessage(long t1, String msg, long t3, long t4) {
		// TODO Auto-generated method stub
		for (Window w : wList) {
			if (w.getWindowHandle() == t1)
				return w.sendMessage(msg, t3, t4);
		}

		return 0;
	}

	public long postMessage(long t1, String str, long t3, long t4) {
		// TODO Auto-generated method stub
		for (Window w : wList) {
			if (w.getWindowHandle() == t1)
				return w.postMessage(str, t3, t4);
		}

		return 0;
	}
}
