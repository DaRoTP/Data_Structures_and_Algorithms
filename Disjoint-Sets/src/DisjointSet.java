public class DisjointSet {


    public Node make(int key) {
        Node node = new Node();
        node.key = key;
        node.parent = node;
        node.rank = 0;
        return node;
    }

    public Node find(Node node) {
        if(node != node.parent) {
            node.parent = find(node.parent);
        }
        return node.parent;
    }

    public void union(Node node1, Node node2) {
        if(node1.rank > node2.rank) {
            node2.parent = node1;
        } else {
            node1.parent = node2;
            if(node1.rank == node2.rank) {
                node2.rank = node2.rank + 1;
            }
        }
    }


    public void path_to_root(Node node) {
        System.out.print(node.key);
        if(node != node.parent) {
            System.out.print("->");
            path_to_root(node.parent);
        }

    }

}