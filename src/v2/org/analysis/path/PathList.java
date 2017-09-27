package v2.org.analysis.path;

import java.util.ArrayList;
import java.util.List;

import org.jakstab.asm.AbsoluteAddress;

public class PathList {
	private List<Long> addrList;

	public List<Long> extract(long v) {
		List<Long> ret = new ArrayList<Long>();
		boolean add = false;
		for (Long l : addrList) {
			if (l.longValue() == v) {
				add = true;
			}

			if (add) {
				ret.add(l);
			}
		}

		return ret;
	}

	@Override
	public String toString() {
		String ret = "Trace: ";
		for (Long t : addrList) {
			ret += new AbsoluteAddress(t.longValue()).toString() + ", ";
		}
		return ret;
	}

	public PathList() {
		this.addrList = new ArrayList<Long>();
	}

	public boolean isEmpty() {
		return this.addrList.isEmpty();
	}

	public boolean add(Long addr) {
		if (contain(addr)) {
			return false;
		}

		addrList.add(addr);
		return true;
	}

	public boolean addT(Long addr) {
		if (contain(addr)) {
			return false;
		}
		
		addrList.add(addr);
		return true;
	}

	public boolean contain(Long addr) {
		if (addr == null) {
			return false;
		}

		for (Long a : addrList) {
			if (a != null && a.longValue() == addr.longValue()) {
				return true;
			}
		}
		return false;
	}

	public Long pop() {
		if (this.isEmpty()) {
			return null;
		}
		return this.addrList.remove(0);
	}

	public Long get(int index) {
		if (index >= addrList.size()) {
			return null;
		}
		return addrList.get(index);
	}

	@Override
	public PathList clone() {
		PathList list = new PathList();
		for (Long addr : this.addrList) {
			list.add(addr);
		}
		return list;
	}

	public void printInfo() {
		for (Long addr : this.addrList) {
			System.out.print(addr.toString() + " ");
		}
		System.out.println();
	}

	public Long getNext(Long ad) {
		int i = 0;
		for (Long a : addrList) {
			if (a.longValue() == ad.longValue()) {
				if (i < addrList.size() - 1) {
					return addrList.get(i + 1);
				} else {
					break;
				}
			}
			i++;
		}
		return null;
	}

	public Long getNextLast() {
		int i = length();
		if (i > 0) {
			return this.addrList.get(i - 2);
		}

		return null;
	}

	public int length() {
		return this.addrList.size();
	}

	public PathList cut(Long ad) {
		// TODO Auto-generated method stub
		PathList l = this.clone();
		int length = l.length();
		for (int i = 0; i < length; i++) {
			Long a = l.get(0);
			if (a.longValue() != ad.longValue()) {
				l.pop();
			} else {
				return l;
			}
		}
		return null;
	}

	public int getPos(Long indirectTarget) {
		int i = 0;
		for (; i < this.addrList.size(); i++) {
			if (this.addrList.get(i).longValue() == indirectTarget.longValue()) {
				return i;
			}
		}
		return -1;
	}

	public void remove(int i) {
		if (i < this.addrList.size()) {
			this.addrList.remove(i);
		}
	}

	public Long popAddr(Long result) {
		// TODO Auto-generated method stub
		if (result == null || !contain(result)) {
			return null;
		} else {
			while (true) {
				Long temp = this.pop();
				if (temp == result) {
					return temp;
				}
			}
		}
	}

	public void pushList(PathList listLoop) {
		// TODO Auto-generated method stub
		PathList l = listLoop.clone();
		List<Long> t = new ArrayList<Long>();

		while (!l.isEmpty()) {
			t.add(l.pop());
		}

		while (!this.addrList.isEmpty()) {
			t.add(this.pop());
		}

		this.addrList = t;
	}
}
