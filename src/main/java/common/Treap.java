package common;

import com.google.gson.Gson;

/**
 * Created by yogesh.bh on 09/05/18.
 */
class TreapNode {
    private int d;
    private int key;
    private double w;
    private transient TreapNode left, right, parent;

    private int lCnt = 1;
    private int tCnt = 1;

    public TreapNode(int data, int key, double w) {
        this.d = data;
        this.key = key;
        this.w = w;
    }

    public static void insertNodeInATree(TreapNode tree, TreapNode node) {
        if (node.key < tree.key)
            setLeft(tree, node);
        else setRight(tree, node);
    }

    private static void setRight(TreapNode tree, TreapNode node) {
        if (tree != null) {
            if (node != null)
                tree.tCnt += node.tCnt;
            if (tree.right != null)
                insertNodeInATree(tree.right, node);
            else {
                tree.right = node;
                node.parent = tree;
            }
        }
    }

    private static void setLeft(TreapNode tree, TreapNode node) {
        if (tree != null) {
            if (node != null) {
                tree.lCnt += node.tCnt;
                tree.tCnt += node.tCnt;
            }
            if (tree.left != null)
                insertNodeInATree(tree.left, node);
            else {
                tree.left = node;
                node.parent = tree;
            }
        }
    }

    public static void print(TreapNode root) {
        if (root == null)
            return;
        print(root.left);
        System.out.print(new Gson().toJson(root));
        System.out.print(" -> ");
        System.out.print(new Gson().toJson(root.left));
        System.out.print(" -> ");
        System.out.print(new Gson().toJson(root.right));
        System.out.print(" -> ");
        System.out.println(new Gson().toJson(root.parent));
        print(root.right);
    }
}

public class Treap {
    TreapNode root = null;

    public static void main(String[] args) {
        int[] ar = {1, 2, 3};

        Treap treap = new Treap();

        for (int i = 0; i < ar.length; i++) {
            treap.insertNode(new TreapNode(ar[i], i + 1, Math.random()));
        }

        treap.print();
    }

    private void print() {
        TreapNode.print(root);
        System.out.println("\n\n\n");
    }

    private void insertNode(TreapNode treapNode) {
        if (root == null)
            root = treapNode;
        else TreapNode.insertNodeInATree(root, treapNode);
    }
}
