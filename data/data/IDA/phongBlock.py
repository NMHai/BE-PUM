from idaapi import *
from idc import *
from idautils import *
import itertools

from phongNode import *

class BasicBlockNode:
    
    def __init__(self, Ident, (StartEA, EndEA)):
        self.StartEA = StartEA
        self.EndEA = EndEA
        self.Ident = Ident
        self.NextNodes = set()
        self.PrevNodes = set()
        self.ListBlockNodes = list()

    def IsStart(self):
        return get_func(self.StartEA).startEA == self.StartEA

    def IsEnd(self):
        return len(self.NextNodes) == 0

    def FindNode(self, EA):
        def FindNode_(self, EA, Visited):
            if self in Visited:
                return None
            Visited.add(self)
            if self.StartEA <= EA <= self.EndEA:
                return self
            for x in self.NextNodes:
                n = FindNode_(x, EA, Visited)
                if n:
                    return n
                return None
        return FindNode_(self, EA, set())

    def FindPathToRoot(self):
        def FindPathToRoot_(self, Visited):
            if self in Visited:
                return None
            Visited.add(self)
            if self.IsStart():
                return [self]
            for x in self.PrevNodes:
                l = FindPathToRoot_(x, Visited)
                if l:
                    l.append(self)
                    return l
                return None
        return FindPathToRoot_(self, set())

    def FindEnd(self):
        def FindEnd_(self, Visited):
            if self in Visited:
                return None
            Visited.add(self)
            if self.IsEnd():
                return self
            for x in self.NextNodes:
                n = FindEnd_(x, Visited)
                if n:
                    return n
                return None
        return FindEnd_(self, set())

    def PrintNode(self):
        print "Node %d = (StartEA:0x%x, EndEA:0x%x)" % (self.Ident, self.StartEA, self.EndEA)
        print "  Previous Nodes:"
        pNodes = []    
        for p in self.PrevNodes:
            if p is not None:
                pNodes.append(p)
        for x in sorted(pNodes, cmp = lambda x,y: cmp(x.Ident, y.Ident)):
            print "    Node %d" % x.Ident
        print "  Next Nodes:"
        nNodes = []
        for n in self.NextNodes:
            if n is not None:
                nNodes.append(n)
        for x in sorted(nNodes, cmp = lambda x,y: cmp(x.Ident, y.Ident)):
            print "    Node %d" % x.Ident

    def Dump(self):
        def Dump_(self, Visited):
            if self in Visited or self == None:
                return
            Visited.add(self)
            self.PrintNode()
            for n in self.NextNodes:
                Dump_(n, Visited)
        Dump_(self, set())
        
    def GetInfoBlockNode(self):
        def CountNode(self, nodes):
            if self == None or self.Ident in nodes:
                return 
            nodes.add(self.Ident)
            for n in self.NextNodes:
                CountNode(n, nodes)
            return nodes
        def GetInfoBlockNode_(self, Visited, listBN):
            if self in Visited or self == None:
                return
            Visited.add(self)
            bNode = MyBlockNode(self.Ident, self.StartEA, self.EndEA, list(self.PrevNodes), list(self.NextNodes))
            bNode.AddChildNodes()
            listBN[self.Ident] = bNode   
            for n in self.NextNodes:
                GetInfoBlockNode_(n, Visited, listBN)
            return listBN
        listBN = (max(CountNode(self, set()))+1)*[None]
        self.ListBlockNodes = GetInfoBlockNode_(self, set(), listBN)
        return self.ListBlockNodes
    
def BuildBasicBlockInfo(EA):
    
    f_start = get_func(EA).startEA
    f_end = FindFuncEnd(f_start)
    edges = set()
    boundaries = set((f_start,))

    def FindFuncChunk(s_addr, listFuncChunk):
        funcChunkEP = NextFuncFchunk(s_addr,FirstFuncFchunk(s_addr))
        if funcChunkEP in listFuncChunk:
            return
        else:
           listFuncChunk.append(funcChunkEP)
        FindFuncChunk(funcChunkEP,listFuncChunk)
        return listFuncChunk
        
    def getEdgesAndBoundaries(start_addr, end_addr):
        bs = set()
        es = set()        

        listFuncChunk = FindFuncChunk(start_addr,list([start_addr]))
        heads_func = Heads(start_addr,end_addr)
        for fc in listFuncChunk:
            heads_func = itertools.chain(heads_func,Heads(fc,FindFuncEnd(fc)))
                
        for head in heads_func:
            if isCode(GetFlags(head)):
                refs_temp = CodeRefsFrom(head, 0)
                refs = list()
                for r in refs_temp:
                    valid = False
                    for fc in listFuncChunk:
                        if r >= fc and r < FindFuncEnd(fc):
                            valid = True
                            refs.append(r)
                            break
                        
                refs = set(refs)
                
            if refs:
                next_head = NextHead(head, end_addr)
                if isFlow(GetFlags(next_head)):
                    refs.add(next_head)
            
            bs.update(refs)
            
            for r in refs:
                if isFlow(GetFlags(r)):
                    es.add((PrevHead(r, start_addr), r))
                es.add((head, r))
            
        return bs,es

    bs, es = getEdgesAndBoundaries(f_start,f_end)
    edges.update(es)
    boundaries.update(bs)

    chunk_boundaries = set()
    sorted_boundaries_without_chunk = set()
    sorted_boundaries_with_chunk = sorted(boundaries, reverse = True)
    for addr in sorted_boundaries_with_chunk:
        if addr <= f_end:
            sorted_boundaries_without_chunk.add(addr)
        else:
            chunk_boundaries.add(addr)

    sorted_boundaries_without_chunk = sorted(sorted_boundaries_without_chunk, reverse = True)
    chunk_boundaries = sorted(chunk_boundaries, reverse = True)
    end_addr = PrevHead(f_end, f_start)
    bb_addr = []
    for begin_addr in sorted_boundaries_without_chunk:
        bb_addr.append((begin_addr, end_addr))
        end_addr = PrevHead(begin_addr, f_start)
        while not isCode(GetFlags(end_addr)):
            end_addr = PrevHead(end_addr, f_start)

    bb_addr.reverse()
    for begin_addr in chunk_boundaries:
        bb_addr.append((begin_addr, PrevHead(FindFuncEnd(begin_addr),begin_addr)))
    
    return bb_addr, sorted(edges)

def BuildBasicBlockGraph(EA):
    def find_node(EA, Nodes):
        for n in Nodes:
            if n.StartEA <= EA <= n.EndEA:
                return n
        return None

    boundaries, edges = BuildBasicBlockInfo(EA)
    nodes = set()
    first_node = BasicBlockNode(len(nodes), boundaries.pop(0))
    nodes.add(first_node)
    for x in boundaries:
        nodes.add(BasicBlockNode(len(nodes), x))
    for (src_ea, dest_ea) in edges:
        src_node = find_node(src_ea, nodes)
        dest_node = find_node(dest_ea, nodes)
        if src_node != None:
            src_node.NextNodes.add(dest_node)
        if dest_node != None:
            dest_node.PrevNodes.add(src_node)

    return first_node
