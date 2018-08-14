package leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LevelOrderTraversal {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        traverse(root, 1, map);
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 1; map.containsKey(i); i++)
            res.add(map.get(i));
        return res;
    }

    private void traverse(TreeNode root, int lev, Map<Integer, List<Integer>> map) {
        if (root == null) return;
        map.putIfAbsent(lev, new ArrayList<>());
        map.get(lev).add(root.val);
        traverse(root.left, lev + 1, map);
        traverse(root.right, lev + 1, map);
    }
}
