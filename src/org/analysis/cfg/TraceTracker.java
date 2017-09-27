package org.analysis.cfg;

import org.jakstab.asm.AbsoluteAddress;
import v2.org.analysis.cfg.AddressList;

import java.util.ArrayList;
import java.util.List;

public class TraceTracker {
	private List<AbsoluteAddress> startAddress;
	private List<AddressList> processedList;
	private List<Condition> conds;

	public TraceTracker() {
		this.startAddress = new ArrayList<AbsoluteAddress>();
		this.processedList = new ArrayList<AddressList>();
		conds = new ArrayList<Condition>();
	}

	public boolean add(AbsoluteAddress addr, AddressList list, Condition cond) {
		if (addr == null && list == null && cond == null)
			return false;
		this.startAddress.add(addr);
		this.processedList.add(list);
		this.conds.add(cond);
		return true;
	}

	public boolean isEmpty() {
		return this.startAddress.isEmpty() || this.processedList.isEmpty();
	}

	public AbsoluteAddress getStartAddress() {
		if (this.isEmpty())
			return null;
		return this.startAddress.get(0);
	}

	public AddressList getProcessedList() {
		if (this.isEmpty())
			return null;
		return this.processedList.get(0);
	}

	public AbsoluteAddress removeFirstStartAddress() {
		if (this.isEmpty())
			return null;
		return this.startAddress.remove(0);
	}

	public AddressList removeFirstProcessedList() {
		if (this.processedList.isEmpty())
			return null;
		return this.processedList.remove(0);
	}

	public Condition removeFirstCondition() {
		if (this.conds.isEmpty())
			return null;
		return this.conds.remove(0);
	}

}
