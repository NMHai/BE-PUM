package org.analysis;

import java.util.ArrayList;

/**
 * track visiting information of all program points
 * 
 * @author Binh Ngo
 * 
 */
public class TraceTracker {

	/**
	 * program point to be tracked
	 * 
	 * @author Binh Ngo
	 * 
	 */
	public class Location {
		private long address;
		private boolean visited;
		private SymbolicValue nextAddress;
		private String statement;

		/**
		 * clone a new location with the same structure
		 */
		public Location clone() {
			Location res = new Location();
			res.address = address;
			res.visited = visited;
			res.statement = statement;
			if (nextAddress != null) {
				res.nextAddress = nextAddress.clone();
			} else {
				res.nextAddress = null;
			}
			return res;
		}

		/**
		 * @return program point address
		 */
		public long getAddress() {
			return address;
		}

		/**
		 * 
		 * @return next address of current trace
		 */
		public SymbolicValue getNextAddress() {
			return nextAddress;
		}
	}

	private ArrayList<Location> addressTracker = new ArrayList<Location>();

	/**
	 * initiate a new trace tracker
	 */
	public TraceTracker() {
		addressTracker = new ArrayList<Location>();
	}

	/**
	 * Add address to tracking location
	 * 
	 * @param address
	 *            address to be added
	 */
	public void addTraceAddress(long address) {
		Location loc = new Location();
		loc.address = address;
		loc.visited = false;
		loc.nextAddress = null;
		loc.statement = "";
		addressTracker.add(loc);
	}

	/**
	 * Set an address visited by analyzer
	 * 
	 * @param address
	 *            address to check
	 * @return true if address visited, false otherwise (mark it)
	 */
	public boolean setVisited(long address, SymbolicValue nextAddress, String statement) {
		for (int i = 0; i < addressTracker.size(); i++) {
			if (addressTracker.get(i).address == address) {
				if (addressTracker.get(i).visited) {
					return true;
				} else {
					addressTracker.get(i).visited = true;
					addressTracker.get(i).nextAddress = nextAddress;
					addressTracker.get(i).statement = statement.replaceAll("\t", " ");
				}
			}
		}
		return false;
	}

	/**
	 * update location information with given information
	 * 
	 * @param address
	 *            program point address
	 * @param isVisited
	 *            true if address is visited before
	 * @param nextAddress
	 *            next address after the instruction is executed
	 * @param statement
	 *            instruction stored in program point
	 */
	public void setLocationInfo(long address, boolean isVisited, SymbolicValue nextAddress, String statement) {
		for (int i = 0; i < addressTracker.size(); i++) {
			if (addressTracker.get(i).address == address) {
				addressTracker.get(i).nextAddress = nextAddress;
				addressTracker.get(i).visited = isVisited;
				addressTracker.get(i).statement = statement;
				return;
			}
		}
	}

	/**
	 * Check if an address visited by analyzer
	 * 
	 * @param address
	 *            address to check
	 * @return true if address was visited, false otherwise
	 */
	public boolean checkVisited(long address) {
		for (int i = 0; i < addressTracker.size(); i++) {
			if (addressTracker.get(i).address == address) {
				if (addressTracker.get(i).visited) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * get location information
	 * 
	 * @param address
	 *            program address
	 * @return program point information
	 */
	public Location getLocationInfo(long address) {
		for (int i = 0; i < addressTracker.size(); i++) {
			if (addressTracker.get(i).address == address) {
				return addressTracker.get(i);
			}
		}
		return null;
	}

	/**
	 * resolve tracker to a string
	 */
	public String toString() {
		String res = "";
		Long retAddress = null;
		for (int i = 0; i < addressTracker.size(); i++) {
			res += "[" + addressTracker.get(i).statement + "]" + "$0x"
					+ Long.toHexString(addressTracker.get(i).address) + " -- ";
			retAddress = addressTracker.get(i).nextAddress.calculateExprIntVal();
			if (retAddress == null) {
				System.out.println("UNRESOLVED");
			} else {
				System.out.println(retAddress.toString());
			}
		}
		return res;
	}

	/**
	 * get trace information of a given address
	 * 
	 * @param address
	 *            address to be checked
	 */
	public void getTraceInfo(long address) {
		String res = "";
		int remainingSpace = 0;
		for (int i = 0; i < addressTracker.size(); i++) {
			if (addressTracker.get(i).address == address) {
				String addr = "$0x" + Long.toHexString(address).toUpperCase();
				if (!addressTracker.get(i).statement.equals("")) {
					res = "[" + addressTracker.get(i).statement + "]";
					if (addressTracker.get(i).visited) {
						if (addressTracker.get(i).nextAddress != null) {
							addr = "$0x" + Long.toHexString(addressTracker.get(i).nextAddress.getIntValue())
									+ " <----- [" + addr;
						} else {
							addr = "UNRESOLVED" + " <----- [" + addr;
						}
					}
				} else {
					addr = "[" + addr;
				}
				remainingSpace = res.length() + addr.length() + 1;
				for (int j = 0; j < (80 - remainingSpace); j++) {
					res += " ";
				}
				res += addr + "]";
				System.out.println(res);
			}
		}
	}

	/**
	 * @return tracker information
	 */
	public ArrayList<Location> trackerInfo() {
		return addressTracker;
	}

	/**
	 * clone a new trace tracker with the same information
	 */
	public TraceTracker clone() {
		TraceTracker res = new TraceTracker();
		for (int i = 0; i < addressTracker.size(); i++) {
			res.addTraceAddress(addressTracker.get(i).address);
			res.setLocationInfo(addressTracker.get(i).address, addressTracker.get(i).visited,
					addressTracker.get(i).nextAddress, addressTracker.get(i).statement);
		}
		return res;
	}
}
