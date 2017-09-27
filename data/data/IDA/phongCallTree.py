from idaapi import *
from idc import *
from idautils import *

class CallTreeNode:

    def __init__(self, Name, EA, NextCallEA, Parent = None):
        self.Children = []
        self.Parent = Parent
        self.Name = Name
        self.EA = EA
        self.NextCallEA = NextCallEA
        self.NextCallSPD = 0
        if NextCallEA:
            self.NextCallSPD = get_spd(get_func(NextCallEA), NextCallEA)

    def Exists(self, Name, NextCallEA):
        NextCallSPD = 0
        if NextCallEA:
            NextCallSPD = get_spd(get_func(NextCallEA), NextCallEA)    
        if self.Name == Name and self.NextCallSPD == NextCallSPD:
            return True
        for c in self.Children:
            if c.Exists(Name, NextCallEA):
                return True
        return False

    def FindAllPaths(self):
        info = (self.EA, self.Name, self.NextCallEA, self.NextCallSPD)
        if len(self.Children) == 0:
            return [[info]]
        paths = []
        for n in self.Children:
            subpaths = n.FindAllPaths()
            map(lambda p: p.append(info), subpaths)
            paths.extend(subpaths)
        return paths

    def Dump(self, Space = 0, Threshold = 42):
        if Space > Threshold:
            return
        print (" " * Space + self.Name + "(" + hex(self.EA) + ")")
        for c in self.Children:
            c.Dump(Space + 1)

def BuildCallTree(EA, NextCallEA = BADADDR, P = None):
    name = GetFunctionName(EA)
    if not name:
        name = "loc_%x" % EA
    if P and P.Exists(name, NextCallEA):
        return None
    node = CallTreeNode(name, EA, NextCallEA, Parent = P)
    for xref in CodeRefsTo(EA, 1):
        try:
            xref_f = get_func(xref).startEA
            subtree = BuildCallTree(xref_f, xref, P = node)
            if subtree:
                node.Children.append(subtree)
        except:
            print ""
    return node
