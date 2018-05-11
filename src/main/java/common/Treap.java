package common;


public class Treap {
    TreapNode root = null;

    public Treap(TreapNode root) {
        this.root = root;
    }

    public Treap() {
    }

    public Treap(TreapNode tree1, TreapNode tree2) {
        root = TreapNode.join(tree1, tree2);
    }

    public static void main(String[] args) {
        int[] ar = {1, 2, 3};

        Treap treap = new Treap();

        for (int i = 0; i < ar.length; i++) {
            treap.insertNode(new TreapNode(ar[i], i + 1, Math.random()));
        }

        treap.print();
        TreapNode[] split = treap.split(2);
        new Treap(split[0]).print();
        new Treap(split[1]).print();
        Treap joined = new Treap(split[1], split[0]);
        joined.print();
    }

    public TreapNode[] split(int rank) {
        TreapNode dummy = new TreapNode(Integer.MAX_VALUE, rank, Integer.MAX_VALUE);
        if (root == null)
            root = dummy;
        else TreapNode.insertSplitter(root, dummy);
        TreapNode.maintainMaxHeap(dummy);
        root = dummy;
        TreapNode[] ans = new TreapNode[]{TreapNode.stripLeft(dummy), TreapNode.stripRight(dummy)};
        root = null;
        return ans;
    }

    public void print() {
        TreapNode.print(root);
        System.out.println("\n\n\n");
    }


    public TreapNode getRoot() {
        return root;
    }

    public void insertNode(TreapNode treapNode) {
        if (root == null)
            root = treapNode;
        else TreapNode.insertNodeInATree(root, treapNode);
        TreapNode.maintainMaxHeap(treapNode);
        if (treapNode.isRoot())
            root = treapNode;
    }

    public int find(int key) {
        TreapNode node = TreapNode.search(root, key);
        return node.getD();
    }

    public void printData() {
        TreapNode.printDs(root);
        System.out.println();
    }
}
