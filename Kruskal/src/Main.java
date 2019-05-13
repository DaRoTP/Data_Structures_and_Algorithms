public class Main {

    public static void main(String[] args) {

        Graph g = new Graph(8, 10);
        g.addEdge(new Edge(1, 2, 2));
        g.addEdge(new Edge(1, 3, 3));
        g.addEdge(new Edge(1, 4, 3));
        g.addEdge(new Edge(2, 5, 3));
        g.addEdge(new Edge(5, 6, 8));
        g.addEdge(new Edge(6, 7, 9));
        g.addEdge(new Edge(2, 3, 4));
        g.addEdge(new Edge(4, 3, 5));
        g.addEdge(new Edge(3, 5, 1));
        g.addEdge(new Edge(4, 6, 7));

        g.kruskal();
    }
}