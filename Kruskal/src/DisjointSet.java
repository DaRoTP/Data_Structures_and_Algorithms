import java.util.ArrayList;
import java.util.List;

public class DisjointSet {
    List<Node> sets = new ArrayList<>();

    public Node make() {
        Node node = new Node();
        node.parent = node;
        node.rank = 0;
        sets.add(node);
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
}