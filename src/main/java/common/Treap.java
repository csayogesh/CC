package common;

import com.google.gson.Gson;

/**
 * Created by yogesh.bh on 09/05/18.
 */
class TreapNode {
    public int data;
    public int rank;
    public double w;
    public transient TreapNode left, right, parent;
}

public class Treap {
    TreapNode root = null;

    public void insert(TreapNode cur, TreapNode node) {
        if (root == null) {
            root = node;
            return;
        }
        if (node.rank < cur.rank) {
            if (cur.left != null)
                insert(cur.left, node);
            else {
                cur.left = node;
                node.parent = cur;
            }
        } else {
            node.rank -= cur.rank;
            if (cur.right != null) {
                insert(cur.right, node);
            } else {
                cur.right = node;
                node.parent = cur;
            }
        }
    }

    public TreapNode[] split(int rank) {
        TreapNode node = new TreapNode();
        node.rank = rank;
        node.data = rank * 10 + rank;
        node.w = Integer.MAX_VALUE;
        insert(root, node);
        printDel(root);
        maintainMaxHeap(node, true);
        root = node;
//        printDel(root);
        return new TreapNode[]{node.left, node.right};
    }

    public TreapNode find(int rank) {
        return find(root, rank);
    }

    private TreapNode find(TreapNode root, int rank) {
        if (root == null || root.rank == rank) return root;
        if (rank > root.rank)
            return find(root.right, rank - root.rank);
        return find(root.left, rank);
    }

    private void insert(TreapNode node) {
        insert(root, node);
        maintainMaxHeap(node, false);
        if (node.parent == null)
            root = node;
    }

    private void maintainMaxHeap(TreapNode node, boolean mode) {
        while (node.parent != null && node.parent.w < node.w) {
            if (mode) printDel(node.parent);
            if (node.parent.left == node)
                rightRotate(node.parent);
            else leftRotate(node.parent);
            if (mode) printDel(node);
        }
    }

    private void rightRotate(TreapNode node) {
        TreapNode newParent = node.left;
        TreapNode nodeParent = node.parent;
        if (nodeParent != null)
            if (nodeParent.left == node)
                nodeParent.left = newParent;
            else nodeParent.right = newParent;
        node.left = newParent.right;
        newParent.right = node;
        node.parent = newParent;
        newParent.parent = nodeParent;
        node.rank = node.left == null ? 1 : node.left.rank + 1;
    }

    private void leftRotate(TreapNode node) {
        TreapNode newParent = node.right;
        TreapNode nodeParent = node.parent;
        if (nodeParent != null)
            if (nodeParent.left == node)
                nodeParent.left = newParent;
            else nodeParent.right = newParent;
        node.right = newParent.left;
        newParent.left = node;
        newParent.rank += node.rank;
        node.parent = newParent;
        newParent.parent = nodeParent;
    }

    public static void main(String[] args) {
        int[] ar = {1, 2, 3};
        Treap treap = new Treap();
        for (int i = 0; i < ar.length; i++) {
            int rank = i + 1;
            int data = ar[i];
            TreapNode node = new TreapNode();
            node.data = data;
            node.rank = rank;
            node.w = rank % 2 == 1 ? rank * 2 : rank;
            treap.insert(node);
            treap.printDel(treap.root);
        }

//        treap.printDel(treap.root);

//        TreapNode[] sp = treap.split(2);

//        treap.printDel(sp[0]);
//        treap.printDel(sp[1]);
    }

    private void printDel(TreapNode node) {
        printTree(node);
        System.out.println("\n\n\n");
    }

    private void printTree(TreapNode node) {
        if (node == null)
            return;
        System.out.print(new Gson().toJson(node));
        System.out.println(" -> " + new Gson().toJson(node.left) + " -> " + new Gson().toJson(node.right)
                + " -> " + new Gson().toJson(node.parent));
        printTree(node.left);
        printTree(node.right);
    }
}
