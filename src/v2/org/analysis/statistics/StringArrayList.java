package v2.org.analysis.statistics;

import java.util.ArrayList;

public class StringArrayList {
	private ArrayList<String> list;

	public StringArrayList(String fileName) {
		list = new ArrayList<String>();
		list.add(fileName);
	}

	public StringArrayList() {
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

	// Check if the string s is stored in array list
	public boolean contain(String s) {
		for (String t : list)
			if (t.equals(s) || s.contains(t))
				return true;

		return false;
	}

}
