def printHuffman(fqc):
    """Prints out char: frequency - code"""
    for k, v in fqc.items():
        print(k, ":", v[0], " - ", v[1])


def FrequencyCalc(sequence):
    """Creates a dictionary where stored all information about a given String sequence key = char : value[0] =
    frequency value[1] = binary code """
    fqc = {i: [0, None] for i in set(sequence)}
    for i in set(sequence):
        for j in sequence:
            if i == j:
                fqc[i][0] += 1
    return fqc

def ExtractSamllest(ListOfNodes):
    """Returns an index from a list of objects of class Node with the smallest Value"""
    temp = ListOfNodes[0]
    for i in ListOfNodes:
        if i.Value < temp.Value:
            temp = i
    return ListOfNodes.index(temp)

def CreateTree(ListOfNodes):
    """Returns a modified version of given ListOfNodes with One Node (Top node of the heap) which you can use to acces
    all of the children"""
    while len(ListOfNodes) != 1:
        x = ListOfNodes.pop(ExtractSamllest(ListOfNodes))
        y = ListOfNodes.pop(ExtractSamllest(ListOfNodes))
        if x.Value > y.Value:
            ListOfNodes.append(Node(x.Value + y.Value, LeftChiled=y, RightChiled=x))
        else:
            ListOfNodes.append(Node(x.Value + y.Value, LeftChiled=x, RightChiled=y))
        ListOfNodes[len(ListOfNodes) - 1].LeftChiled.Parent = ListOfNodes[len(ListOfNodes) - 1].RightChiled.Parent = ListOfNodes[len(ListOfNodes) - 1]
    return ListOfNodes

def SetCodes(MNode):
    """function sets codes to each Node of the tree if a chiled is left - gets code 0 if it's right - gets code 1"""
    if not MNode:
        pass
    else:
        if MNode.Parent:
            if MNode == MNode.Parent.LeftChiled:
                MNode.code = "0"
            else:
                MNode.code = "1"
        SetCodes(MNode.LeftChiled)
        SetCodes(MNode.RightChiled)

    return MNode

def getCodes(MNode):
    """function walks though the tree and ads up Parent codes to children"""
    if not MNode:
        pass
    else:
        if MNode.LeftChiled or MNode.RightChiled:
            MNode.LeftChiled.code += MNode.code

            MNode.RightChiled.code += MNode.code

            getCodes(MNode.LeftChiled)
            getCodes(MNode.RightChiled)
    return MNode

def toInfo_helper(MNode):
    """function returns a 2D list with a character in 1-st position and a code in second"""
    global info
    if not MNode:
        pass
    else:
        if MNode.id != "zw":
            info.append([MNode.id, MNode.code])
        toInfo_helper(MNode.LeftChiled)
        toInfo_helper(MNode.RightChiled)
    return info

def toInfo(info, MNode):
    """function takes the list from toInfo_helper() function and adds that information to the main info_sequence dictionary"""
    for i in toInfo_helper(MNode):
        info[i[0]][1] = i[1][::-1]
    return info

class Node:
    def __init__(self, value, id = "zw", LeftChiled = None, RightChiled = None):
        self.id = id
        self.Value = value
        self.Parent = None
        self.LeftChiled = LeftChiled
        self.RightChiled = RightChiled
        self.code = ""

def listOfNodes(info):
    """function returns a list of objects of class Node"""
    listNodes = list()
    for k, v in info.items():
        listNodes.append(Node(v[0], k))
    return listNodes

def CalcHuffman(info):
    sum = 0
    for k, v in info.items():
        sum += v[0] * len(v[1])
    return sum


sequence = ""
with open('dane.txt', 'r') as f:
    for line in f:
        sequence += line.strip()


sequence_info = FrequencyCalc(sequence) #write down basic information to the dictionary like character and frequency
listaWezlow = listOfNodes(sequence_info) #Create a list of Nodes
listaWezlow = CreateTree(listaWezlow) #Creates a Tree
listaWezlow[0] = SetCodes(listaWezlow[0]) #set codes 0 for left chiled 1 for right chiled
listaWezlow[0] = getCodes(listaWezlow[0]) #walk through the Tree and combine Parent and children codes
info = list()
sequence_info = toInfo(sequence_info, listaWezlow[0]) #and all colected information like codes to the main dictionary sequence_info
printHuffman(sequence_info)

print("Huffmana - ", CalcHuffman(sequence_info))
print("Normal - ", listaWezlow[0].Value * 3)
print("Compressed to: ", round(CalcHuffman(sequence_info)*100/(listaWezlow[0].Value * 3)), "%")