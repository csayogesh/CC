package hackerrank;

/**
 * Created by yogesh.bh on 12/05/18.
 */


class Node {
    public int frequency; // the frequency of this tree
    public char data;
    public Node left, right;
}


public class HuffmanDecoding {
    void decode(String S, Node root) {
        StringBuilder sb = new StringBuilder();
        Node cur = root;
        for (int i = 0; i < S.length(); i++) {
            if (cur.left == null && cur.right == null) {
                sb.append(cur.data);
                cur = root;
            }
            char ch = S.charAt(i);
            if (ch == '0')
                cur = cur.left;
            else cur = cur.right;
        }
        if (cur.left == null && cur.right == null) {
            sb.append(cur.data);
            cur = root;
        }
        System.out.println(sb.toString());
    }
}
