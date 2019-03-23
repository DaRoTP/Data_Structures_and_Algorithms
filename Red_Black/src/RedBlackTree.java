/*
Napisz program (= struktury danych + procedury), który umożliwia wstawianie elementów do drzewa czerwono-czarnego.
*/
public class RedBlackTree {
    //KOLORY
    public  enum COLOR{RED, BLACK}
    //NODE
    public static class Node
    {
        //ZMIENNE
        public COLOR color;
        public int value;
        public Node left;
        public Node right;
        public Node parent;
        //KONSTRUKTOR
        public Node(int value, COLOR color)
        {
            this.value = value;
            this.color = color;
        }
        //DEFAULT - jezeli kolor nie jest podany to wezel jest czerwony
        public Node(int val){
            this(val, COLOR.RED);
        }
    }
//====================================================

    //ZMIENNE "GLOBALNE"
    public static final String ANSI_BLACK = "\u001B[30m"; //kolor tekstu CZARNY
    public static final String ANSI_RED = "\u001B[31m";// --|--| CZERWONY
    public static final String ANSI_RESET = "\u001B[0m";// --|--| RESET

    private Node root = null;


    //
    private static boolean isLeftChild(Node node) { return node.parent.left == node; }
    //
    private static boolean isUncleTheRightChild(Node node) { return node.parent == node.parent.parent.left; }
    //
    private static boolean isRedUncle(Node uncle) { return uncle != null && uncle.color == COLOR.RED; }
    //
    private static Node getUncle(Node node)
    {
        Node parent = node.parent; //wstawiamy ojca wezla"node"
        Node grandParent = parent.parent; //wtawiamy dziadka wezla "node"

        if (node.parent == null || node.parent.parent == null) //sprawdzamy czy jest ojciec jest pusty badz czy dziadek jest pusty
            return null;

        if (parent == grandParent.left)
            return grandParent.right;
        else
            return grandParent.left;
    }
//====================================================

    //WYPISYWANIE
    public void Drukuj_Node_A(Node node, int Spacje) {
        if(node != null)
        {
            Spacje += 5;
            int Licznik = Spacje;

            Drukuj_Node_A(node.right, Spacje); //dochodzimy do najbarziej prawego liscia i drukujemy go
            while( Licznik > 0)
            {
                System.out.print(" ");
                Licznik--;
            }

            if(node.color  == COLOR.RED)
                System.out.println(ANSI_RED + node.value + ANSI_RESET);
            else
                System.out.println(ANSI_BLACK + node.value + ANSI_RESET);
            Drukuj_Node_A(node.left, Spacje);
        }
    }
    public void Drukuj_Node_B(int Spacje) {
        if(root != null)
        {
            Spacje += 5;
            int Licznik = Spacje;

            Drukuj_Node_A(root.right, Spacje);
            while( Licznik > 0)
            {
                System.out.print(" ");
                Licznik--;
            }

            if(root.color  == COLOR.RED)
                System.out.println(ANSI_RED + root.value + ANSI_RESET);
            else
                System.out.println(ANSI_BLACK + root.value + ANSI_RESET);
            Drukuj_Node_A(root.left, Spacje);
        }

        System.out.println("\nmaksymalna glebokosc : "+maxDepth(root));
        System.out.println("minimalna glebokosc : "+minDepth(root));
        System.out.println("red: "+isRed(root));
    }
//====================================================

    //WSTAWIANIE
    private void insert(int x)
    {
        Node parent = root;
        Node current = root;

        //Jezeli korzen jest pusty, to wstawiamy wezel i zmieniamy kolor na czarny (KONIEC)
        if (root == null)
        {
            root = new Node(x, COLOR.BLACK);
            return;
        }

        //  WSTAWIANIE DO DRZEWA POSZUKIWAN BINARNYCH
        while (current != null)
        {
            parent = current;
            if(x < current.value)
                current = current.left;
            else
                current = current.right;
        }

        Node Node_1 = new Node(x); //Node pomocniczy
        Node_1.parent = parent;

        if(x < parent.value)
            parent.left = Node_1;
        else
            parent.right = Node_1;

        fixTree(Node_1);
    }
    //POPRAWIANIE DRZEWA, Malowanie  wezlow w sposob by odpowiadalo DEF. drzewa Czerwono-Czarnego
    private void fixTree(Node node) {
        if(node.parent == null || node.parent.color == COLOR.BLACK)
            return;

        Node uncle = getUncle(node);
        if(isRedUncle(uncle))//spawdzamy czy wojek jest czerwony - jezeli tak to WAR. 1 (zmieniamy kolor ojca, dziadka i wujka)
        {
            node.parent.color = COLOR.BLACK;
            node.parent.parent.color = COLOR.RED;
            uncle.color = COLOR.BLACK;
            fixTree(node.parent.parent);
        }//JEZELI WOJEK JEST CZARNY
        else if(isUncleTheRightChild(node)) //czy wojek jest dzieckiem
        {
            if(node.parent.right == node) //czy wezel jest prawym dzieckiem lewego dziecka
                node.parent.color = COLOR.BLACK;
            node.parent.parent.color = COLOR.RED;
            rotateRight(node.parent); //rotacja prawostronna
        }
        else
        {
            if(node.parent.left == node)
                rotateRight(node);

            node.parent.color = COLOR.BLACK;
            node.parent.parent.color = COLOR.RED;
            rotateLeft(node.parent);
        }

        root.color = COLOR.BLACK;
    }

    //ROTACJA PRAWOSTRONNA
    private void rotateRight(Node node)
    {
        Node x = node;
        Node y = x.parent;
        Node g = y.parent;
        Node beta = x.right;

        x.parent = g;
        if(g != null){
            if(isLeftChild(y))
                g.left = x;
            else
                g.right = x;
        }else {this.root = x;}

        x.right = y;

        y.parent = x;
        y.left = beta;

        //fix beta
        if(beta != null)
            beta.parent = y;
    }

    //ROTACJA LEWOSTRONNA
    private void rotateLeft(Node node)
    {
        Node newNode = node;
        Node oldNode = node.parent;
        Node grandparent = oldNode.parent;
        Node newNodeLeftChild = newNode.left;

        newNode.parent = grandparent;

        if (grandparent != null)
        {
            if(isLeftChild(oldNode))
                grandparent.left = newNode;
            else
                grandparent.right = newNode;
        }
        else
            this.root = newNode;

        newNode.left = oldNode;
        oldNode.parent = newNode;

        oldNode.right = newNodeLeftChild;
        if(newNodeLeftChild != null)
            newNodeLeftChild.parent = oldNode;
    }
    //MAKSYMALNA GLEBOKOSC
    public int maxDepth(Node node)
    {
        if (node == null)
            return 0;

        return Math.max(maxDepth(node.left), maxDepth(node.right)) + 1;
    }
    //MINIMALA GLEBOKOSC
    public int minDepth(Node node) {
        if (node == null)
            return 0;

        return Math.min(minDepth(node.left), minDepth(node.right)) + 1;
    }
    //ISRED
    public int isRed(Node node)
    {
        if (node == null)
            return 0;
        if(node.color == COLOR.BLACK)
            return isRed(node.left)+isRed(node.right);
        else
            return (isRed(node.left)+isRed(node.right))+1;
    }



    public static void main(String[] args) {


        RedBlackTree tree = new RedBlackTree();
        tree.insert(6);
        tree.insert(2);
        tree.insert(12);
        tree.insert(1);
        tree.insert(3);
        tree.insert(11);
        tree.insert(13);
        tree.insert(14);
        tree.insert(15);




        tree.Drukuj_Node_B(0);
    }
}