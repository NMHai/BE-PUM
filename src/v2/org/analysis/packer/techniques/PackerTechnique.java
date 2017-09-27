package v2.org.analysis.packer.techniques;

import java.util.HashSet;
import java.util.Set;

import org.jakstab.Program;

import v2.org.analysis.path.BPState;

public abstract class PackerTechnique {
	protected String name = ""; 
//	protected int num = 0;
	protected int id; 
	protected Set<Long> resultList = new HashSet<Long> (); 
	
	public abstract boolean check(BPState curState, Program prog);
	public abstract boolean checkAPIName(String apiName, long location);
	
	public int getFrequency() {
		return resultList.size();
	}
	public boolean hasTechnique() {
		return resultList.size() > 0;
	}
	
	public String getName() {
		return name;
	}
	
	public int getID() {
		return id;
	}
	
	public boolean contain(long location) {
		return resultList.contains(location);
	}
	
	@Override
	public String toString() {
		String ret = "Name:" + name + ", Frequency:" + getFrequency() 
		+ ", Location:";
		for (Long location: resultList) {
			ret += "0x" + Long.toHexString(location) + ", ";
		}
		
		return ret;
	}
	
	public void insertLocation(long location) {
		resultList.add(location);
	}
}
