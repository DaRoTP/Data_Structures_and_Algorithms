public class Edge {
    int s;
    int d;
    int w;

    public int getW() {
        return w;
    }

    public Edge(int s, int d, int w) {
        this.s = s;
        this.d = d;
        this.w = w;
    }

    @Override
    public String toString() {
        return this.s + " -(" + this. w+")- " + this.d ;
    }
}
