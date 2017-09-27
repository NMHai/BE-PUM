package v2.org.analysis.cfg;

import org.jakstab.asm.AbsoluteAddress;

import java.util.ArrayList;
import java.util.List;

public class AddressList {
	private List<AbsoluteAddress> addrList;

	public AddressList() {
		this.addrList = new ArrayList<AbsoluteAddress>();
	}

	public boolean isEmpty() {
		return this.addrList.isEmpty();
	}

	public boolean add(AbsoluteAddress addr) {
		if (contain(addr))
			// addrList.add(addr);
			return false;

		addrList.add(addr);
		return true;
	}

	public boolean addT(AbsoluteAddress addr) {
		// if (contain(addr))
		// return false;
		addrList.add(addr);
		return true;
	}

	public boolean contain(AbsoluteAddress addr) {
		for (AbsoluteAddress a : addrList)
			if (a.getValue() == addr.getValue())
				return true;
		return false;
	}

	public AbsoluteAddress pop() {
		if (this.isEmpty())
			return null;
		return this.addrList.remove(0);
	}

	public AbsoluteAddress get(int index) {
		if (index >= addrList.size())
			return null;
		return addrList.get(index);
	}

	public AddressList clone() {
		AddressList list = new AddressList();
		for (AbsoluteAddress addr : this.addrList)
			list.add(addr);
		return list;
	}

	public void printInfo() {
		for (AbsoluteAddress addr : this.addrList)
			System.out.print(addr.toString() + " ");
		System.out.println();
	}

	public AbsoluteAddress getNext(AbsoluteAddress ad) {
		int i = 0;
		for (AbsoluteAddress a : addrList) {
			if (a.getValue() == ad.getValue()) {
				if (i < addrList.size() - 1)
					return addrList.get(i + 1);
				else
					break;
			}
			i++;
		}
		return null;
	}

	public AbsoluteAddress getNextLast() {
		int i = length();
		if (i > 0)
			return this.addrList.get(i - 2);

		return null;
	}

	public int length() {
		return this.addrList.size();
	}

	public AddressList cut(AbsoluteAddress ad) {
		// TODO Auto-generated method stub
		AddressList l = this.clone();
		int length = l.length();
		for (int i = 0; i < length; i++) {
			AbsoluteAddress a = l.get(0);
			if (a.getValue() != ad.getValue())
				l.pop();
			else
				return l;
		}
		return null;
	}

	public int getPos(AbsoluteAddress indirectTarget) {
		int i = 0;
		for (; i < this.addrList.size(); i++) {
			if (this.addrList.get(i).getValue() == indirectTarget.getValue())
				return i;
		}
		return -1;
	}

	public void remove(int i) {
		if (i < this.addrList.size())
			this.addrList.remove(i);
	}

	public AbsoluteAddress popAddr(AbsoluteAddress result) {
		// TODO Auto-generated method stub
		if (result == null || !contain(result))
			return null;
		else {
			while (true) {
				AbsoluteAddress temp = this.pop();
				if (temp.getValue() == result.getValue())
					return temp;
			}
		}
	}

	public void pushList(AddressList listLoop) {
		// TODO Auto-generated method stub
		AddressList l = listLoop.clone();
		List<AbsoluteAddress> t = new ArrayList<AbsoluteAddress>();

		while (!l.isEmpty())
			t.add(l.pop());

		while (!this.addrList.isEmpty())
			t.add(this.pop());

		this.addrList = t;
	}
}
