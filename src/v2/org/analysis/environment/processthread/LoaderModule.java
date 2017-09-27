package v2.org.analysis.environment.processthread;

import v2.org.analysis.environment.memory.MemoryV2;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;

public class LoaderModule {
	
	private long base_address;
	private ListEntry InLoadOrderModule;
	private ListEntry InMemOrderModule;
	private ListEntry InInitOrderModule;
	private long dll_base;
	private long entry_point;
	private long reserved;
	private UnicodeString fullDLLName;
	private UnicodeString baseDLLName;
	
	public LoaderModule (long baseAddress, ListEntry lOrderModule, ListEntry mOrderModule
			, ListEntry iOrderModule, long base, long eP, long reserved
			, UnicodeString fDLLName, UnicodeString bDLLName)
	{
		this.base_address 		= baseAddress;
		this.InLoadOrderModule 	= lOrderModule;
		this.InMemOrderModule	= mOrderModule;
		this.InInitOrderModule	= iOrderModule;
		this.dll_base			= base;
		this.entry_point		= eP;
		this.reserved			= reserved;
		this.fullDLLName		= fDLLName;
		this.baseDLLName		= bDLLName;
	}
	
	public long getBaseAddress ()
	{
		return this.base_address;
	}
	
	public void Update (BPState curState)
	{	
		MemoryV2 mem = curState.getEnvironement().getMemory();
		mem.createMemory(base_address, 0x50, "Initialization of LoaderModule");
		mem.setDoubleWordMemoryValue(base_address, new LongValue(InLoadOrderModule.getNext()));
		mem.setDoubleWordMemoryValue(base_address + 0x4, new LongValue(InLoadOrderModule.getBack()));
		mem.setDoubleWordMemoryValue(base_address + 0x8, new LongValue(InMemOrderModule.getNext()));
		mem.setDoubleWordMemoryValue(base_address + 0xC, new LongValue(InMemOrderModule.getBack()));
		mem.setDoubleWordMemoryValue(base_address + 0x10, new LongValue(InInitOrderModule.getNext()));
		mem.setDoubleWordMemoryValue(base_address + 0x14, new LongValue(InInitOrderModule.getBack()));
		mem.setDoubleWordMemoryValue(base_address + 0x18, new LongValue(dll_base));
		mem.setDoubleWordMemoryValue(base_address + 0x1C, new LongValue(entry_point));
		mem.setDoubleWordMemoryValue(base_address + 0x20, new LongValue(reserved));
		mem.setWordMemoryValue(base_address + 0x24, new LongValue(fullDLLName.getLength()));
		mem.setWordMemoryValue(base_address + 0x26, new LongValue(fullDLLName.getMaxLength()));
		mem.setDoubleWordMemoryValue(base_address + 0x28, new LongValue(fullDLLName.getPointerStr()));
		mem.setWordMemoryValue(base_address + 0x2C, new LongValue(baseDLLName.getLength()));
		mem.setWordMemoryValue(base_address + 0x2E, new LongValue(baseDLLName.getMaxLength()));
		mem.setDoubleWordMemoryValue(base_address + 0x30, new LongValue(baseDLLName.getPointerStr()));
	}
}
