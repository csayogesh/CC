package ibit;

import java.util.ArrayList;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class GenerateTrees {

    public ArrayList<TreeNode> generateTrees(int a) {
        return allTrees(1, a);
    }

    public ArrayList<TreeNode> allTrees(int m, int n) {
        if (m > n) {
            return null;
        }
        ArrayList<TreeNode> res = new ArrayList<TreeNode>();
        for (int i = m; i <= n; i++) {
            ArrayList<TreeNode> left = allTrees(m, i - 1);
            ArrayList<TreeNode> right = allTrees(i + 1, n);
            if (left != null && right != null)
                for (TreeNode x : left)
                    for (TreeNode y : right) {
                        TreeNode c = new TreeNode(i);
                        c.left = x;
                        c.right = y;
                        res.add(c);
                    }
            else if (left == null && right != null) for (TreeNode y : right) {
                TreeNode c = new TreeNode(i);
                c.right = y;
                res.add(c);
            }
            else if (left != null && right == null) for (TreeNode x : left) {
                TreeNode c = new TreeNode(i);
                c.left = x;
                res.add(c);
            }
            else if (left == null && right == null) res.add(new TreeNode(i));
        }
        return res;
    }
}
