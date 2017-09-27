from idaapi import *
from idc import *
from idautils import *

from phongCallTree import *
from phongBlock import *
from phongGraph import *
		    
def construct_graph(staticFile, startAddr):
    # Open file
    fl = open(GetInputFile() + ".dot","w")
    WriteHeader(fl)
    # Set Timer
    t0 = time.clock()
    # Get function to analyze
    function = get_func(startAddr)
    Ident = 0
    ListCallerNode = list()
    ListCalleeNode = list()
    NumOfNodes = 0
    NumOfEdges = 0
    while function is not None:
        blockNode = BuildBasicBlockGraph(function.startEA)
        ListBlockNodes = blockNode.GetInfoBlockNode()
        g = MyGraph(Ident, GetFunctionName(function.startEA), ListBlockNodes, ListCallerNode, ListCalleeNode, fl)    
        g.WriteDOT()
        Ident += g.GetGraphIdent()
        NumOfNodes += g.GetNodes()
        NumOfEdges += g.GetEdges()
        function = get_next_func(function.startEA)
    # Build call tree
    WriteCallTree(fl,ListCallerNode,ListCalleeNode)
    # End timer
    t1 = time.clock()
    NumOfEdges += len(ListCallerNode)
    # Count nodes and edges:
    staticFile.write(str(GetInputFile()))
    staticFile.write("\t" + str(t1 - t0) +"\t" + str(NumOfNodes) + "\t" + str(NumOfEdges))
    staticFile.write("\n")
    
    WriteFooter(fl)
    fl.close()

staticFile = open("static" + ".txt","a")
autoWait()
g = construct_graph(staticFile,BeginEA())
Exit(0)
