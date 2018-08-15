package leet;

import java.util.HashMap;
import java.util.Map;

public class KthSmallestInBST {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int kthSmallest(TreeNode root, int k) {
        nodeCnts(root);
        return method(root, k);
    }

    private int method(TreeNode root, int k) {
        if (root == null) return -1;
        int rank = 1 + getNodeCnts(root.left);
        if (rank > k)
            return method(root.left, k);
        else if (rank < k)
            return method(root.right, k - rank);
        return root.val;
    }

    private int getNodeCnts(TreeNode left) {
        if(left==null)return 0;
        return dp.get(left.val);
    }

    Map<Integer, Integer> dp = new HashMap<>();

    private int nodeCnts(TreeNode left) {
        if (left == null) return 0;
        int ans = 1;
        ans += nodeCnts(left.left);
        ans += nodeCnts(left.right);
        dp.put(left.val, ans);
        return ans;
    }
}
