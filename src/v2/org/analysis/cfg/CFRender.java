/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v2.org.analysis.cfg;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.jakstab.Options;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.BranchInstruction;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.ReturnInstruction;
import org.jakstab.util.GraphMLWriter;
import org.jakstab.util.GraphWriter;
import org.jakstab.util.GraphvizWriter;

/**
 *
 * @author zunc
 */
public class CFRender {

	private TreeMap<Long, CFBlock> _block = new TreeMap<>();
	private BPCFG _cfg;
	private List<BPVertex> _vertexs;
	private List<BPEdge> _edges;
	private boolean _reduceCall = true;
	private long _rootAddr = 0x401500;

	public CFRender(BPCFG cfg) {
		_cfg = cfg;
		_vertexs = _cfg.getVertecesList();
		_edges = _cfg.getEdgesList();
	}

	public void setReduceCall(boolean reduce) {
		this._reduceCall = reduce;
	}

	public void setRootAddress(long addr) {
		this._rootAddr = addr;
	}

	private TreeMap<Long, CFBlock> renderBasicBlock_dev() {
		TreeMap<Long, CFBlock> blocks = new TreeMap<>();
		CFBlock curBlock = new CFBlock();
		long addrNext = 0;
		TreeMap<Long, BPVertex> sortedInst = new TreeMap<>();
		for (BPVertex vertex : _vertexs) {
			AbsoluteAddress pc = vertex.getAddress();
			if (pc != null) {
				sortedInst.put(pc.getValue(), vertex);
			}
		}
		
		for (BPVertex vertex : sortedInst.values()) {
			AbsoluteAddress pc = vertex.getAddress();
			Instruction instr = vertex.getInstruction();
			String strInst = instr.getName();
			// newblock by far address
			long addr = pc.getValue();
			if (addr == 0x0040150c) {
				System.out.println("DEBUG");
			}
			
			if (addrNext > 0 && addrNext != addr) {
				blocks.put(curBlock.getLoc(), curBlock);
				curBlock = new CFBlock();
			}
			curBlock.push(pc.getValue(), instr);
			addrNext = addr + instr.getSize();
			if (instr instanceof ReturnInstruction) {
				blocks.put(curBlock.getLoc(), curBlock);
				// init new block
				curBlock = new CFBlock();
				addrNext = 0;
				continue;
			}
		}
		if (!curBlock.isEmpty()) {
			blocks.put(curBlock.getLoc(), curBlock);
		}
		return blocks;
	}

	private TreeMap<Long, CFBlock> renderBasicBlock() {
		TreeMap<Long, CFBlock> blocks = new TreeMap<>();
		CFBlock curBlock = new CFBlock();
		long addrNext = 0;
		TreeMap<Long, BPVertex> sortedInst = new TreeMap<>();
		for (BPVertex vertex : _vertexs) {
			AbsoluteAddress pc = vertex.getAddress();
			if (pc != null) {
				sortedInst.put(pc.getValue(), vertex);
			}
		}
		
		for (BPVertex vertex : sortedInst.values()) {
			AbsoluteAddress pc = vertex.getAddress();
			Instruction instr = vertex.getInstruction();
			curBlock.push(pc.getValue(), instr);
		}
		if (!curBlock.isEmpty()) {
			blocks.put(curBlock.getLoc(), curBlock);
		}
		return blocks;
	}
	
	private boolean _isNearby(long addr1, long addr2) {
		long pAddr1 = Math.min(addr1, addr2);
		long pAddr2 = Math.max(addr1, addr2);
		Map.Entry<Long, CFBlock> entry1 = _block.floorEntry(pAddr1);
		if (entry1 == null) {
//			System.out.println("Debug");
		}
		CFBlock block1 = _block.floorEntry(pAddr1).getValue();
		CFBlock block2 = _block.floorEntry(pAddr2).getValue();
		if (block1 != block2) {
			return false;
		}
		boolean isRange1 = block1.isRange(pAddr1);
		boolean isRange2 = block1.isRange(pAddr2);
		if (!isRange1 || !isRange2) {
			System.out.println("DEBUG");
		}
		return block1.getNext(pAddr1, pAddr2);
	}

	private void reduceNearbyEgde() {
		System.err.println(" - edges.size()==" + _edges.size());
		for (BPEdge e : _edges) {
//			System.err.println(e);
			AbsoluteAddress src = e.getSourceVertex().getAddress();
			AbsoluteAddress dst = e.getDestVertex().getAddress();
			if (src == null || dst == null) {
				// API
				_edges.remove(e);
//				System.err.println("  -> api.remove");
				continue;
			}
			long srcAddr = src.getValue();
			long dstAddr = dst.getValue();
			Instruction srcInst = e.getSourceVertex().getInstruction();

			String instSrcName = srcInst.getName();
			if (_reduceCall) {
				if (instSrcName.equals("call")) {
//					System.err.println("  -> call.remove");
					_edges.remove(e);
					continue;
				}
			}
			
			long nextAddr = srcAddr + srcInst.getSize();
			if (!(srcInst instanceof BranchInstruction)
					&& !(srcInst instanceof ReturnInstruction)
					&& (nextAddr == dstAddr)) {
//				System.err.println("  -> nearby.remove");
				_edges.remove(e);
			}
		}
		System.err.println(" - reduce edges.size()==" + _edges.size());
	}

	private void _putBlock(CFBlock block) {
		_block.put(block.getLoc(), block);
	}

	private void splitBlock() {
		System.err.println(" - _block.size()==" + _block.size());
		for (BPEdge e : _edges) {
//			System.err.println(e);
			AbsoluteAddress src = e.getSourceVertex().getAddress();
			AbsoluteAddress dst = e.getDestVertex().getAddress();
			if (src == null || dst == null) {
				// API
//				System.err.println("DEBUG");
				continue;
			}

			{
//				String srcInst = e.getSourceVertex().getInstruction().getName();
//				if (srcInst.equals("ret")) {
//					long srcAddr = src.getValue();
//					CFBlock srcBlock = _block.floorEntry(srcAddr).getValue();
//					if (srcBlock.getLoc() != srcAddr) {
//						long nextAddr = srcBlock.getNextAddr(srcAddr);
//						if (nextAddr > 0) {
//							CFBlock newBlock = srcBlock.splitBlock(nextAddr);
//							if (newBlock != null) {
//								_putBlock(newBlock);
//							}
//						}
//					}
//				}
			}

			{
				long dstAddr = dst.getValue();
				CFBlock dstBlock = _block.floorEntry(dstAddr).getValue();
				if (dstBlock.getLoc() != dstAddr) {
					CFBlock newBlock = dstBlock.splitBlock(dstAddr);
					if (newBlock != null) {
						_putBlock(newBlock);
					}
				}
			}
		}
		System.err.println(" - split _block.size()==" + _block.size());
	}

	private GraphWriter createGraphWriter(String filename) {
		try {
			if (Options.graphML.getValue()) {
				return new GraphMLWriter(filename);
			} else {
				return new GraphvizWriter(filename);
			}
		} catch (IOException e) {
			return null;
		}
	}

	private void _render(String filePath) {
		GraphWriter gwriter = createGraphWriter(filePath);
		if (gwriter == null) {
			return;
		}

		// System.out.println("Writing assembly CFG to " +
		// gwriter.getFilename());
		try {
			for (Map.Entry<Long, CFBlock> entry : _block.entrySet()) {
				CFBlock blk = entry.getValue();
				gwriter.writeNode(blk.getLocName(), blk.toString());
			}

			for (BPEdge e : _edges) {
				AbsoluteAddress sourceAddr = e.getSourceVertex().getAddress();
				long srcAddr = sourceAddr.getValue();
				Map.Entry<Long, CFBlock> srcBlk = _block.floorEntry(srcAddr);
				String sNode = srcBlk.getValue().getLocName();
				AbsoluteAddress targetAddr = e.getDestVertex().getAddress();
				String dNode = String.format("0x00%s", Long.toHexString(targetAddr.getValue()));

				String label = null;
				Instruction instr = e.getSourceVertex().getInstruction();
				if (instr instanceof BranchInstruction) {
					BranchInstruction bi = (BranchInstruction) instr;
					if (bi.isConditional()) {
						String t = e.getSourceVertex().getProperty();
						label = t.startsWith(dNode) ? "T" : "F";
					}
				}

				if (label != null) {
					gwriter.writeLabeledEdge(sNode, dNode, label,
							label.equals("T") ? Color.GREEN : Color.RED);
				} else {
					gwriter.writeEdge(sNode, dNode, Color.BLACK);
				}
			}

			gwriter.close();
		} catch (IOException e) {

		}
	}

	public void render(String path) {
		_block = renderBasicBlock();
		reduceNearbyEgde();
		splitBlock();
		_render(path);
	}

}
