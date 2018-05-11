package common;

import com.google.gson.Gson;

/**
 * Created by yogesh.bh on 09/05/18.
 */
public class TreapNode {
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

    public static void setRight(TreapNode tree, TreapNode node) {
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

    public static void setLeft(TreapNode tree, TreapNode node) {
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
        TreapNode left = stripLeft(right);
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

    public static TreapNode stripRight(TreapNode node) {
        TreapNode res = null;
        if (node != null && node.right != null) {
            res = node.right;
            node.right = null;
            res.parent = null;
            node.tCnt -= res.tCnt;
        }
        return res;
    }

    public static TreapNode stripLeft(TreapNode node) {
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

    public static void insertSplitter(TreapNode tree, TreapNode splitter) {
        if (splitter.key < tree.lCnt)
            setLeftSplitter(tree, splitter);
        else {
            splitter.key -= tree.lCnt;
            setRightSplitter(tree, splitter);
        }
    }

    private static void setLeftSplitter(TreapNode tree, TreapNode node) {
        if (tree != null) {
            if (node != null) {
                tree.lCnt += node.tCnt;
                tree.tCnt += node.tCnt;
            }
            if (tree.left != null)
                insertSplitter(tree.left, node);
            else {
                tree.left = node;
                if (node != null)
                    node.parent = tree;
            }
        }
    }

    private static void setRightSplitter(TreapNode tree, TreapNode node) {
        if (tree != null) {
            if (node != null)
                tree.tCnt += node.tCnt;
            if (tree.right != null)
                insertSplitter(tree.right, node);
            else {
                tree.right = node;
                if (node != null)
                    node.parent = tree;
            }
        }
    }

    public static TreapNode join(TreapNode tree1, TreapNode tree2) {
        if (tree1 == null) return tree2;
        if (tree2 == null) return tree1;
        TreapNode res = null;
        TreapNode concilator = new TreapNode(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
        concilator.lCnt = 0;
        concilator.tCnt = 0;
        TreapNode.setLeft(concilator, tree1);
        TreapNode.setRight(concilator, tree2);
        while (concilator.left != null || concilator.right != null) {
            boolean leftMax = false;
            double max = concilator.w;
            if (concilator.left != null && concilator.left.w >= max) {
                max = concilator.left.w;
                leftMax = true;
            }
            if (concilator.right != null && concilator.right.w >= max) {
                max = concilator.right.w;
                leftMax = false;
            }
            if (leftMax)
                doRightRotation(concilator);
            else doLeftRotation(concilator);
            if (concilator.parent != null && concilator.parent.isRoot())
                res = concilator.parent;
        }
        if (concilator.parent != null)
            if (concilator.parent.left == concilator)
                stripLeft(concilator.parent);
            else stripRight(concilator.parent);
        return res;
    }

    public int getD() {
        return d;
    }

    public static TreapNode search(TreapNode root, int key) {
        if (root == null)
            return null;
        if (root.lCnt == key)
            return root;
        if (root.left != null && root.lCnt > key)
            return search(root.left, key);
        return search(root.right, key - root.lCnt);
    }

    public static void printDs(TreapNode root) {
        if (root == null)
            return;
        printDs(root.left);
        System.out.print(root.d + " ");
        printDs(root.right);
    }
}
