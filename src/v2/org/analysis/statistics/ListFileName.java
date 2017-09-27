package v2.org.analysis.statistics;

import java.util.ArrayList;

public class ListFileName {
	private ArrayList<String> list;

	public ListFileName(String fileName) {
		list = new ArrayList<String>();
		list.add(fileName);
	}

	public ListFileName() {
		list = new ArrayList<String>();
		// list.add(fileName);
	}

	public int size() {
		return list.size();
	}

	public String get(int i) {
		return list.get(i);
	}

	public boolean insert(String fileName) {
		// TODO Auto-generated method stub
		if (!list.contains(fileName)) {
			list.add(fileName);
			return true;
		}

		return false;
	}

}
