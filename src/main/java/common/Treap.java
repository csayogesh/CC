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
                if (node != null)
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
                if (node != null)
                    node.parent = tree;
            }
        }
    }

    public static void print(TreapNode root) {
        if (root == null)
            return;
        System.out.print(new Gson().toJson(root));
        System.out.print(" -> ");
        System.out.print(new Gson().toJson(root.left));
        System.out.print(" -> ");
        System.out.print(new Gson().toJson(root.right));
        System.out.print(" -> ");
        System.out.println(new Gson().toJson(root.parent));
        print(root.left);
        print(root.right);
    }

    public static void maintainMaxHeap(TreapNode treapNode) {
        while (treapNode.parent != null && treapNode.parent.w < treapNode.w) {
            if (treapNode.parent.left == treapNode)
                doRightRotation(treapNode.parent);
            else doLeftRotation(treapNode.parent);
        }
    }

    private static void doLeftRotation(TreapNode node) {
        if (node == null) return;
        TreapNode nodeParent = node.parent;
        boolean nodeParentLeft = false;
        if (nodeParent != null)
            if (nodeParent.left == node) {
                stripLeft(nodeParent);
                nodeParentLeft = true;
            } else stripRight(nodeParent);
        TreapNode right = stripRight(node);
        TreapNode left = stripRight(right);
        setRight(node, left);
        setLeft(right, node);
        if (nodeParent != null)
            if (nodeParentLeft) setLeft(nodeParent, right);
            else setRight(nodeParent, right);
    }

    private static void doRightRotation(TreapNode node) {
        if (node == null) return;
        TreapNode nodeParent = node.parent;
        boolean nodeParentLeft = false;
        if (nodeParent != null)
            if (nodeParent.left == node) {
                stripLeft(nodeParent);
                nodeParentLeft = true;
            } else stripRight(nodeParent);
        TreapNode left = stripLeft(node);
        TreapNode right = stripRight(left);
        setLeft(node, right);
        setRight(left, node);
        if (nodeParent != null)
            if (nodeParentLeft) setLeft(nodeParent, left);
            else setRight(nodeParent, left);
    }

    private static TreapNode stripRight(TreapNode node) {
        TreapNode res = null;
        if (node != null && node.right != null) {
            res = node.right;
            node.right = null;
            res.parent = null;
            node.tCnt -= res.tCnt;
        }
        return res;
    }

    private static TreapNode stripLeft(TreapNode node) {
        TreapNode res = null;
        if (node != null && node.left != null) {
            res = node.left;
            node.left = null;
            res.parent = null;
            node.tCnt -= res.tCnt;
            node.lCnt -= res.tCnt;
        }
        return res;
    }

    public boolean isRoot() {
        return parent == null;
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
        TreapNode.maintainMaxHeap(treapNode);
        if (treapNode.isRoot())
            root = treapNode;
    }
}
