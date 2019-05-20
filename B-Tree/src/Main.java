public class Main {
    public static void main(String[] args) {
        Tree tree = new Tree(3);
        tree.insert(22);
        tree.insert(3);
        tree.insert(11);
        tree.insert(32);
        tree.insert(2);
        tree.insert(45);
        tree.insert(8);
        tree.insert(90);
        tree.insert(5);
        tree.insert(55);

        tree.print();
        System.out.println("\n" + tree.search(3));
        System.out.println(tree.search(5));
        System.out.println(tree.search(90));
    }
}