from idaapi import *
from idc import *
from idautils import *

class MyNode():

    def __init__(self, ident, addr, instr, iscall):
        self.ident = ident
        self.addr = addr
        self.instr = instr
        self.iscall = iscall

    def GetDetails(self):
        return str(self.addr) + '\n' + str(self.instr)

    def GetInstruction(self):
        return str(self.instr)

    def GetAddress(self):
        return str(hex(self.addr))

    def GetAbsoluteAddress(self):
        return self.addr

    def is_call_node(self):
        return self.iscall

class MyBlockNode():

    def __init__(self, Ident, StartEA, EndEA, PrevNodes, NextNodes):
        self.Ident = Ident
        self.StartEA = StartEA
        self.EndEA = EndEA
        self.PrevNodes = PrevNodes
        self.NextNodes = NextNodes
        self.ListChildNodes = list()
        self.JumpNodeIdent = None
        self.JumpNodeAddr = None
        self.JumpTrap = False

    def GetDetails(self):
        return str(hex(self.StartEA)) + '\n' + str(hex(self.EndEA))

    def AddChildNodes(self):
        ident = 0
        for head in Heads(self.StartEA, self.EndEA + 1):
            if isCode(GetFlags(head)):
                ins = str(GetDisasm(head).split(';')[0]).rstrip(" ")
                if is_call_insn(head):
                    node = MyNode(ident, head, ins, True)
                    self.ListChildNodes.append(node)
                else:
                    if GetMnem(head) == 'jmp':
                        self.JumpTrap = True
                        self.JumpNodeIdent = 0
                        self.JumpNodeAddr = head
                    node = MyNode(ident, head, ins, False)
                    self.ListChildNodes.append(node)
                ident += 1

class CallerNode():

    def __init__(self, numNode, addr):
        self.numNode = numNode
        self.addr = addr

    def GetCalleeValue(self):
        return GetOperandValue(self.addr,0)

class CalleeNode():

    def __init__(self, numNode, addr):
        self.numNode = numNode
        self.addr = addr
    
