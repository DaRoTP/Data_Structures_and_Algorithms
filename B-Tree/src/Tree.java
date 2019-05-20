public class Tree {
    private Node root;
    int t; // minimum degree

    public Tree(int t) {
        this.root = null;
        this.t = t;
    }

    public void print() {
        if (root != null) {
            root.print(0);
        }
    }

    public Node search(int key) {
        if (root == null) {
            return null;
        }
        return root.search(key);
    }

    public void insert(int key) {
        if (root == null) { // if root is null, key becomes root
            root = new Node(t, true);
            root.keys[0] = key;
            root.setN(root.getN() + 1);
        } else {
            if (root.getN() == 2 * t - 1) { // if root is full then split
                Node s = new Node(t, false);
                s.child[0] = root; // make old root as a child of new root
                s.split(0, root); // split old root and move 1 key to the new root
                int i = 0;
                if (s.keys[0] < key) {
                    i++;
                }
                s.child[i].insertNonFull(key);
                root = s;
            } else {
                root.insertNonFull(key);
            }
        }
    }
}