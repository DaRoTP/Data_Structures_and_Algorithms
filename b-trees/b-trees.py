import bisect

class _BTreeNode(object):
    def __init__(self, values=None, children=None):
        self.parent = None
        self.values = values or []
        self.children = children
        # update the parent node in children,
        # just in case it has changed
        if self.children:
            for i in self.children:
                i.parent = self

    def __str__(self):
        return 'Node( %r)' % (self.values,)

    def pretty_print(self, tab=''):
        print('%s%s' % (tab, self))
        if self.children:
            for i in self.children:
                i.pretty_print(tab + '   ')



    def search(self, val):
        i = bisect.bisect_left(self.values, val)
        if (i != len(self.values) and not val < self.values[i]):
            # a value was found
            assert (self.values[i] == val)
            return (True, self, i)

        if self.children is not None:
            assert (len(self.children) >= i and self.children[i])
            # recursively search down the appropriate child node
            return self.children[i].search(val)
        else:
            return (False, self, i)

    def _split_node(self, tree, val=None, slot=None, childNodes=None):
        assert (val is None or (slot is not None))

        midList = [] if val is None else [val]
        if slot is None:
            slot = 0


        splitValues = self.values[0:slot] + midList + self.values[slot:]
        medianIdx = len(splitValues) // 2

        lv = splitValues[0:medianIdx]
        medianVal = splitValues[medianIdx]
        rv = splitValues[medianIdx + 1:]

        innerNode = self.children is not None

        if innerNode:
            if childNodes is not None:
                splitChildren = (self.children[0:slot] +
                                 list(childNodes) +
                                 self.children[slot + 1:])
            else:
                splitChildren = self.children
            lc = splitChildren[0:len(lv) + 1]
            rc = splitChildren[len(lv) + 1:]
        else:
            lc = None
            rc = None

        leftNode = _BTreeNode(lv, lc)
        rightNode = _BTreeNode(rv, rc)

        if self.parent:
            self.parent.add(tree,
                            medianVal,
                            None,
                            (leftNode, rightNode))
        else:

            newRoot = _BTreeNode([medianVal], [leftNode, rightNode])
            leftNode.parent = newRoot
            rightNode.parent = newRoot
            tree.root = newRoot
            tree.height += 1
            tree.size += 1




    def add(self, tree, val, slot=None, childNodes=None):

        assert (self.children is None or childNodes)


        innerNode = self.children is not None
        if innerNode:
            assert (childNodes and len(childNodes) == 2)
        else:
            assert (childNodes is None)


        if slot is None:
            slot = bisect.bisect_left(self.values, val)

        if len(self.values) < tree.max_values:
            self.values.insert(slot, val)
            tree.size += 1
            if childNodes:

                for i in childNodes:
                    i.parent = self
                self.children[slot:slot + 1] = childNodes
            # we're done
            return True

        self._split_node(tree, val, slot, childNodes)
        return True

    def min_value(self, slot=0):
        if self.children:
            return self.children[slot].min_value()
        return self.values[0], self, 0

    def max_value(self, slot=None):
        if slot is None:
            slot = len(self.values) - 1
        if self.children:
            return self.children[slot + 1].max_value()
        return self.values[-1], self, len(self.values) - 1





class BTree(object):

    def __init__(self, order):
        if order <= 2:
            raise ValueError("B-tree order must be at least 3")
        self.root = _BTreeNode()
        self.order = order
        self.max_values = order - 1
        self.min_values = self.max_values // 2
        self.height = 1
        self.size = 0

    def __str__(self):
        return 'height: %d items: %d m: %d root: %x' % (
            self.height, self.size,
            self.max_values + 1,
            id(self.root))

    def add(self, val):
        # find the leaf node where the value should be added
        found, node, slot = self.root.search(val)
        if found:
            # the value already exists, can't add it twice
            return False
        return node.add(self, val, slot, None)



    def search(self, val):
        return self.root.search(val)[0]

    def min(self):
        return self.root.min_value()[0]

    def max(self):
        return self.root.max_value()[0]


if __name__ == '__main__':
    # mini test
    tree = BTree(3)
    for i in range(1, 8):
        tree.add(i)
        assert (tree.search(i))
    for i in range(1, 8):
        assert (tree.search(i))
    print(tree.search(6))
    print("B-tree containing values 1..7")
    tree.root.pretty_print()
    print("-------")