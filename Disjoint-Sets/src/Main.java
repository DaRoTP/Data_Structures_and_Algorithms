public class Main {

    public static void main(String[] args) {

        Node tab[] = new Node[10];
        //
        DisjointSet disjoinedSET = new DisjointSet();
        for(int i = 0; i < tab.length; i++) {
            tab[i] = disjoinedSET.make(i);
        }

        disjoinedSET.union(disjoinedSET.find(tab[0]), disjoinedSET.find(tab[1]));
        disjoinedSET.union(disjoinedSET.find(tab[2]), disjoinedSET.find(tab[3]));
        disjoinedSET.union(disjoinedSET.find(tab[1]), disjoinedSET.find(tab[2]));
        disjoinedSET.union(disjoinedSET.find(tab[5]), disjoinedSET.find(tab[6]));
        disjoinedSET.union(disjoinedSET.find(tab[7]), disjoinedSET.find(tab[8]));
        disjoinedSET.union(disjoinedSET.find(tab[3]), disjoinedSET.find(tab[5]));
        disjoinedSET.union(disjoinedSET.find(tab[0]), disjoinedSET.find(tab[7]));

        for(int i = 0; i < tab.length; i++) {
            System.out.println("key = "+tab[i].key);
            disjoinedSET.path_to_root(tab[i]);
            System.out.println("\n");
        }

    }
}