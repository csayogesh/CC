package leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class UniqueBST {
    public List<TreeNode> generateTrees(int n) {
        Map<Integer, Map<Integer, List<TreeNode>>> dp = new HashMap<>();
        return getTrees(1, n, dp);
    }

    private List<TreeNode> getTrees(int i, int j, Map<Integer, Map<Integer, List<TreeNode>>> dp) {
        if (i > j)
            return new ArrayList<TreeNode>() {{
                add(null);
            }};
        if (i == j)
            return new ArrayList<TreeNode>() {{
                add(new TreeNode(i));
            }};
        if (dp.containsKey(i) && dp.get(i).containsKey(j))
            return dp.get(i).get(j);
        List<TreeNode> nodes = new ArrayList<>();
        for (int k = i; k <= j; k++) {
            List<TreeNode> leftTrees = getTrees(i, k - 1, dp);
            List<TreeNode> rightTrees = getTrees(k + 1, j, dp);
            for (TreeNode lc : leftTrees)
                for (TreeNode rc : rightTrees) {
                    TreeNode ch = new TreeNode(k);
                    ch.left = lc;
                    ch.right = rc;
                    nodes.add(ch);
                }
        }
        dp.putIfAbsent(i, new HashMap<>());
        dp.get(i).put(j, nodes);
        return nodes;
    }

    public static void main(String[] args) {

    }
}
