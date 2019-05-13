import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Graph {
    int v;
    int e;
    List <Edge> edges = new ArrayList<>();


    public Graph(int v, int e) {
        this.v = v;
        this.e = e;
    }

    public void addEdge(Edge edge){
        edges.add(edge);
    }


    Comparator<Edge> compareByWeight = Comparator.comparingInt(Edge::getW);
    public void kruskal(){
        DisjointSet dj = new DisjointSet();
        Node rs;
        Node rd;

        for(int i = 0; i < this.v; i++){
            dj.make();
        }
        Collections.sort(edges, compareByWeight);

        for(int i = 0; i < this.e; i++){
            rs = dj.find(dj.sets.get(this.edges.get(i).s));
            rd = dj.find(dj.sets.get(this.edges.get(i).d));
            if(rs != rd){
                System.out.println(this.edges.get(i));
                dj.union(rs, rd);
            }
        }

    }
}
