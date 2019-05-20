public class Node {
    public int[] keys;
    private int t; // minimum degree
    public Node[] child;
    private int n; // number of keys
    private boolean leaf;

    public Node(int t, boolean leaf) {
        this.t = t;
        this.keys = new int[t*2 - 1];
        this.child = new Node[t*2];
        this.leaf = leaf;
        n = 0;
    }

    public Node search(int key) {
        int i;
        for (i = 0; i < n; i++) {
            if (keys[i] >= key) {
                break;
            }
        }
        if (i < n && keys[i] == key) {
            return this;
        }
        if (leaf) {
            return null;
        }
        return child[i].search(key);
    }

    public void print(int p) {
        if (leaf) {
            for (int i = 0; i < p; i++) {
                System.out.print(" ");
            }
            for (int i = 0; i < n; i++) {
                System.out.print(keys[i] + " ");
            }
            System.out.println();
        } else {
            child[n].print(p+4);
            for (int i = n-1; i >= 0; i--) {
                for (int j = 0; j < p; j++) {
                    System.out.print(" ");
                }
                System.out.println(keys[i]);
                child[i].print(p+4);
            }
        }
    }

    public void split(int i, Node y) {
        Node z = new Node(y.getT(), y.isLeaf()); // new node which contains t-1 keys of y
        z.setN(t-1);
        for (int j = 0; j < t-1; j++) { // copy last t-1 keys of y to z
            z.keys[j] = y.keys[j+t];
        }
        if (!y.isLeaf()) { // copy last t-1 children of y to z
            for (int j = 0; j < t; j++) {
                z.child[j] = y.child[j+t];
            }
        }
        y.setN(t-1);
        for (int j = n; j >= i+1; j--) { // create space for new child
            child[j+1] = child[j];
        }
        child[i+1] = z;
        for (int j = n-1; j >= i; j--) { // move greater keys
            keys[j+1] = keys[j];
        }
        keys[i] = y.keys[t-1]; // middle key
        n++; // increment number of keys
    }

    public void insertNonFull(int key) {
        int i = n-1;
        if (leaf) {
            while (i >= 0 && keys[i] > key) { // find and move all greater keys
                keys[i+1] = keys[i];
                i--;
            }
            keys[i+1] = key;
            n++;
        } else {
            while (i >= 0 && keys[i] > key) { // find child where key is going to be inserted
                i--;
            }
            if (child[i+1].n == 2*t-1) { // check if found child is full
                split(i+1, child[i+1]); // if it is full then split
                if (keys[i+1] < key) {
                    i++;
                }
            }
            child[i+1].insertNonFull(key);
        }
    }

    public int getT() {
        return t;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public boolean isLeaf() {
        return leaf;
    }
}