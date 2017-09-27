package v2.org.analysis.packer;

public class PackerPatterns {
	
	private PackerCounter pCounter;
	
	public PackerPatterns()
	{
		pCounter = new PackerCounter();
	}
	
	public void setCheckingState(PackerCounter pCounter)
	{
		this.pCounter = pCounter;
	}
	
	/*
	 * Check if it is encryption
	 */
	public boolean PackAndUnpack()
	{
		return pCounter.getTechniques(PackerConstants.PACKING_UNPACKING).getFrequency() > 0;
	}
	
	/*
	 * Check if it is SMC
	 */
	public boolean Overwriting()
	{
		return pCounter.getTechniques(PackerConstants.OVERWRITING).getFrequency() > 0;
	}
	
	/*
	 * Check if it is indirect jump
	 */
	public boolean IndirectJump()
	{
		return pCounter.getTechniques(PackerConstants.INDIRECT_JUMP).getFrequency() > 0;
	}
	
	/*
	 * Check if it is obfuscated constant.
	 */
	public boolean ObfuscatedConst()
	{
		return pCounter.getTechniques(PackerConstants.OBFUSCATED_CONST).getFrequency() > 0;
	}
	
	/*
	 * Check if it is overlapping
	 */
	public boolean OverlappingFunction()
	{
		return pCounter.getTechniques(PackerConstants.OVERLAPPING_FUNC).getFrequency() > 0;
	}
	
	public boolean OverlappingBlock()
	{
		return pCounter.getTechniques(PackerConstants.OVERLAPPING_BLOCK).getFrequency() > 0;
	}
	
	/*
	 * Check if it is chunk code
	 */
	public boolean CodeChunking()
	{
		return pCounter.getTechniques(PackerConstants.CODE_CHUNKING).getFrequency() > 0;
	}
	
	/*
	 * Check if it is VirtualAlloc
	 */
	public boolean StolenBytes()
	{
		return pCounter.getTechniques(PackerConstants.STOLEN_BYTES).getFrequency() > 0;
	}
	
	/*
	 * Check if it is checksum calculate
	 */
	public boolean Checksumming()
	{
		return pCounter.getTechniques(PackerConstants.CHECKSUMMING).getFrequency() > 0;
	}
	
	/*
	 * Check if it is structure exception handling
	 */
	public boolean SEHs()
	{
		return pCounter.getTechniques(PackerConstants.SEH).getFrequency() > 0;
	}
	
	/*
	 * Check if it is LoadLibrary and GetProcAddress
	 */
	public boolean TwoAPIs()
	{
		return pCounter.getTechniques(PackerConstants.TWO_APIS).getFrequency() > 0;
	}
	
	/*
	 * Check if it is IsDebuggerPresent 
	 */
	public boolean AntiDebugging()
	{
		return pCounter.getTechniques(PackerConstants.ANTI_DEBUGGING).getFrequency() > 0;
	}

	/*
	 * Check if it is Timing check
	 */
	public boolean TimingCheck()
	{
		return pCounter.getTechniques(PackerConstants.TIMING_CHECK).getFrequency() > 0;
	}
	
	/*
	 * Check if it is Hardware breakpoints
	 */
	public boolean HardwareBPX()
	{
		return pCounter.getTechniques(PackerConstants.HARDWARE_BPX).getFrequency() > 0;
	}
}
