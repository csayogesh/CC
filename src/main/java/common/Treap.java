package common;

/**
 * Created by yogesh.bh on 09/05/18.
 */
class TreapNode {
    public int data;
    public int rank;
    public double w;
    public TreapNode left, right, parent;
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
        node.w = 2.0;
        insert(node);
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
        maintainMaxHeap(node);
        if (node.parent == null)
            root = node;
    }

    private void maintainMaxHeap(TreapNode node) {
        while (node.parent != null && node.parent.w < node.w) {
            if (node.parent.left == node)
                rightRotate(node.parent);
            else leftRotate(node.parent);
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
        int[] ar = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        Treap treap = new Treap();
        for (int i = 0; i < ar.length; i++) {
            int rank = i + 1;
            int data = ar[i];
            TreapNode node = new TreapNode();
            node.data = data;
            node.rank = rank;
            node.w = Math.random();
            treap.insert(node);
        }

        for (int i = 1; i <= 10; i++)
            System.out.println(treap.find(i).data);
    }
}
