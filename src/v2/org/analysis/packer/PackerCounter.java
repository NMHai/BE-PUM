package v2.org.analysis.packer;

import java.util.ArrayList;

import org.jakstab.Program;

import v2.org.analysis.packer.techniques.PackerTechnique;
import v2.org.analysis.path.BPState;

public class PackerCounter {
	
	private BPState m_state;
	private Program m_prog;
	
	private ArrayList<PackerTechnique> pTechs = new ArrayList<>();
	
//	private TechniqueAbstract antiDebuggingC;
//	private TechniqueAbstract checksummingC;
//	private TechniqueAbstract codeChunkingC;
//	private TechniqueAbstract indirectJumpC;
//	private TechniqueAbstract obfuscatedConstC;
//	private TechniqueAbstract overlappingBlockC;
//	private TechniqueAbstract overlappingFuncC;
//	private TechniqueAbstract overwrittingC;
//	private TechniqueAbstract packingUnpackingC;
//	private TechniqueAbstract sehC;
//	private TechniqueAbstract stolenBytesC;
//	private TechniqueAbstract timingCheckC;
//	private TechniqueAbstract twoAPIsC;
//	private TechniqueAbstract hardwareBpxC;
	
	public PackerCounter ()
	{	
//		antiDebuggingC		= new AntiDebugging();
//		checksummingC		= new Checksumming();
//		codeChunkingC		= new CodeChunking();
//		indirectJumpC 		= new IndirectJump();
//		obfuscatedConstC	= new ObfuscatedConst();
//		overlappingBlockC	= new OverlappingBlock();
//		overlappingFuncC	= new OverlappingFunction();
//		overwrittingC		= new Overwriting();
//		packingUnpackingC	= new PackingUnpacking();
//		sehC				= new SEH();
//		stolenBytesC		= new StolenBytes();
//		timingCheckC		= new TimingCheck();
//		twoAPIsC			= new TwoSpecialAPIs();
//		hardwareBpxC		= new HardwareBPX();
//		
//		pTechs.add(antiDebuggingC);
//		pTechs.add(checksummingC);
//		pTechs.add(codeChunkingC);
//		pTechs.add(indirectJumpC);
//		pTechs.add(obfuscatedConstC);
//		pTechs.add(overlappingBlockC);
//		pTechs.add(overlappingFuncC);
//		pTechs.add(overwrittingC);
//		pTechs.add(packingUnpackingC);
//		pTechs.add(sehC);
//		pTechs.add(stolenBytesC);
//		pTechs.add(timingCheckC);
//		pTechs.add(twoAPIsC);
//		pTechs.add(hardwareBpxC);
	}
	
	public void Execute (boolean run)
	{
		if (run)
		{
			for (PackerTechnique pTech : this.pTechs)
			{
				pTech.check(this.m_state, this.m_prog);
			}
		}
	}
	
	public void setCountingState (BPState curState, Program prog) {
		this.m_state 	= curState;
		this.m_prog		= prog;
	}
	
	public String getInfo () {
		String result = "";
		
		for (PackerTechnique pTech: pTechs) {
			result += String.valueOf(pTech.getFrequency());
			result += "\t";
		}
		
		return result;
	}
	
	public PackerTechnique getTechniques(int index)
	{
		return pTechs.get(index);
	}
}
