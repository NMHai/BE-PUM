from idaapi import *
from idc import *
from idautils import *

from phongNode import *

class MyGraph(GraphViewer):

    def __init__(self, GraphIdent, FuncName, ListBlockNode, ListCallerNode, ListCalleeNode, FileToWrite):
        GraphViewer.__init__(self, "Graph of " + FuncName)
        self.GraphIdent = GraphIdent
        self.FuncName = FuncName
        self.ListBlockNode = ListBlockNode
        self.ListCallerNode = ListCallerNode
        self.ListCalleeNode = ListCalleeNode
        self.FileToWrite = FileToWrite
        # Count node and edge:
        self.nodes = 0
        self.edges = 0

    def GetGraphIdent(self):
        return self.GraphIdent

    def GetNodes(self):
        return self.nodes

    def GetEdges(self):
        return self.edges
    
    def OnRefresh(self):
        self.Clear()
        def LinkInBlock(self,children):
            for i in range(0,len(children)-1):
                self.AddEdge(children[i],children[i+1])
        block = list(list())
        for b in self.ListBlockNode:
            children = []
            if b is not None:
                for n in b.ListChildNodes:
                    child = self.AddNode(n.GetDetails())
                    children.append(child)
                LinkInBlock(self,children)
                block.append(children)
            else:
                block.append([None])
        for b in self.ListBlockNode:
            if b is not None:
                for nex in b.NextNodes:
                    if nex != None:
                        self.AddEdge(block[b.Ident][len(block[b.Ident])-1], block[nex.Ident][0])
                for pre in b.PrevNodes:
                    if pre != None:
                        self.AddEdge(block[pre.Ident][len(block[pre.Ident])-1], block[b.Ident][0])
        return True
    
    def OnGetText(self, node_id):
        return str(self[node_id])

    def OnCommand(self, cmd_id):
        if self.cmd_close == cmd_id:
            self.Close()
            return
        print "Command:", cmd_id

    def Show(self):
        if not GraphViewer.Show(self):
            return False
        self.cmd_close = self.AddCommand("Close", "F2")
        if self.cmd_close == 0:
            print "Failed to add pop-up menu item !"
        return True
    
    def WriteDOT(self):
        def WriteNode(f, ident, addr, ins):
            f.write(str(ident))
            f.write("[" + "label=\"")
            f.write(addr)
            f.write("\\"+"n")
            f.write(ins)
            f.write("\"" + "];" + "\n")

        def WriteNodeStart(f, ident, addr, ins):
            f.write(str(ident))
            f.write("[" + "label=\"")
            f.write("Start")
            f.write("\\"+"n")
            f.write(addr)
            f.write("\\"+"n")
            f.write(ins)
            f.write("\"" + ",fillcolor=\"" + "orange\"" + "];" + "\n")
        
        def WriteEdge(f, src, dest):
            f.write(str(src))
            f.write("->")
            f.write(str(dest))
            f.write("[color=\"#000000\"];\n")

        def Edge_Existed(edge, listEdges):
            if edge in listEdges:
                return True
            return False

        def WriteContent(g, f):
            def LinkInBlock(self,children):
                for i in range(0,len(children)-1):
                    WriteEdge(f,children[i],children[i+1])
                    self.edges += 1
        
            block = list(list())
            ident = g.GraphIdent
            for b in g.ListBlockNode:
                children = []
                if b is not None:
                    for n in b.ListChildNodes:
                        # Add callee and caller node
                        if ident == self.GraphIdent:
                            calleeNode = CalleeNode(ident,n.GetAbsoluteAddress())
                            g.ListCalleeNode.append(calleeNode)
                        if n.is_call_node():
                            callerNode = CallerNode(ident,n.GetAbsoluteAddress())
                            g.ListCallerNode.append(callerNode)
                        # Write start node
                        if ident == 0:
                            WriteNodeStart(f,ident,n.GetAddress(),n.GetInstruction())
                            self.nodes += 1
                        else:
                            # If block has jump node
                            if b.JumpTrap == True:
                                b.JumpNodeIdent = ident
                                b.JumpTrap = False
                            WriteNode(f,ident,n.GetAddress(),n.GetInstruction())
                            self.nodes += 1
                        children.append(ident)
                        ident += 1
                    LinkInBlock(g,children)
                    block.append(children)
                else:
                    block.append([None])
            g.GraphIdent = ident
            
            listEdges = []
            for b in g.ListBlockNode:
                if b is not None:
                    for nex in b.NextNodes:
                        if nex != None:
                            src = block[b.Ident][len(block[b.Ident])-1]
                            dest = block[nex.Ident][0]
                            if b.JumpNodeIdent is not None and GetOperandValue(b.JumpNodeAddr,0) == nex.StartEA:
                                src = b.JumpNodeIdent
                                dest = block[nex.Ident][0]
                                src_trivial = block[b.Ident][len(block[b.Ident])-1]
                                dest_trivial = block[nex.Ident][0]
                                listEdges.append(str(src_trivial)+str(dest_trivial))
                            if not Edge_Existed(str(src)+str(dest),listEdges) and src != None and dest != None:
                                WriteEdge(f,src,dest)
                                g.edges += 1
                            listEdges.append(str(src)+str(dest))
                    for pre in b.PrevNodes:
                        if pre != None:
                            src = block[pre.Ident][len(block[pre.Ident])-1]
                            dest = block[b.Ident][0]
                            if not Edge_Existed(str(src)+str(dest),listEdges) and src != None and dest != None:
                                WriteEdge(f,src,dest)
                                g.edges += 1
                            listEdges.append(str(src)+str(dest))
        WriteContent(self, self.FileToWrite)

def WriteHeader(f):
    f.write("digraph G{\n")
    f.write("node[shape=rectangle,style=filled,fillcolor=lightsteelblue,color=lightsteelblue]\n")
    f.write("bgcolor=\"transparent\"\n")
            
def WriteFooter(f):
    f.write("}")

def WriteCallTree(f, ListCallerNode, ListCalleeNode):
    def WriteEdge(f, src, dest):
        f.write(str(src))
        f.write("->")
        f.write(str(dest))
        f.write("[color=\"#ff0000\"];\n")

    for cr in ListCallerNode:
        for ce in ListCalleeNode:
            if cr.GetCalleeValue() == ce.addr:
                WriteEdge(f,cr.numNode,ce.numNode)
                break
            
                            
