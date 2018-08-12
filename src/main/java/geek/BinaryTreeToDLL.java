package geek;


public class BinaryTreeToDLL {
    class Node {
        Node left, right;
        int data;

        Node(int d) {
            data = d;
            left = right = null;
        }
    }

    class DLLNode {
        Node head;
        Node tail;
    }

    Node head;

    Node BToDLL(Node root) {
        DLLNode dll = convert(root);
        head = dll.head;
        return dll.head;
    }

    private DLLNode convert(Node root) {
        if (root == null)
            return null;
        DLLNode left = convert(root.left);
        DLLNode right = convert(root.right);
        DLLNode res = new DLLNode();
        if (left != null) {
            res.head = left.head;
            joinNodes(left.tail, root);
        } else res.head = root;
        if (right != null) {
            res.tail = right.tail;
            joinNodes(root, right.head);
        } else res.tail = root;
        return res;
    }

    private void joinNodes(Node node1, Node node2) {
        node1.right = node2;
        node2.left = node1;
    }
}
