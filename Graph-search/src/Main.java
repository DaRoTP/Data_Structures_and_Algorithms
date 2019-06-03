import java.io.FileNotFoundException;

public class Main {
    public static void main(String args[]) throws FileNotFoundException {
        FileGetter fs = new FileGetter();

        Graph g = fs.getEdges(FileGetter.getElemFromFile());

        g.printTree();
        System.out.println("-------------------");
        System.out.println("DFS:");
        g.DFS(3);

        System.out.println("BFS:");
        g.BFS(5);





    }
}