package leet;

public class NumArray {
    class TreeNode {
        int st, en;
        int sum;
        TreeNode left, right;
    }

    TreeNode root = null;

    public NumArray(int[] nums) {
        root = buildTree(nums, 0, nums.length - 1);
    }

    private TreeNode buildTree(int[] nums, int i, int j) {
        if (i > j) return null;
        if (i == j) {
            TreeNode node = new TreeNode();
            node.st = i;
            node.en = j;
            node.sum = nums[i];
            return node;
        }
        int m = (i + j) / 2;
        TreeNode left = buildTree(nums, i, m);
        TreeNode right = buildTree(nums, m + 1, j);
        TreeNode root = new TreeNode();
        root.left = left;
        root.right = right;
        root.st = i;
        root.en = j;
        if (left != null)
            root.sum += left.sum;
        if (right != null)
            root.sum += right.sum;
        return root;
    }

    public void update(int i, int val) {
        updateValue(root, val, i);
    }

    private int updateValue(TreeNode root, int val, int i) {
        if (root.st == root.en && root.st == i) {
            int d = val - root.sum;
            root.sum = val;
            return d;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        int diff = 0;
        if (left != null && i >= left.st && i <= left.en) diff = updateValue(left, val, i);
        else if (right != null && i >= right.st && i <= right.en) diff = updateValue(right, val, i);
        root.sum += diff;
        return diff;
    }

    public int sumRange(int i, int j) {
        return getSum(root, i, j);
    }

    private int getSum(TreeNode root, int i, int j) {
        if (root.st == i && root.en == j)
            return root.sum;
        TreeNode left = root.left;
        TreeNode right = root.right;
        int ans = 0;
        if (left != null && left.en >= i)
            ans += getSum(left, i, Math.min(left.en, j));
        if (right != null && right.st <= j)
            ans += getSum(right, Math.max(i, right.st), j);
        return ans;
    }

    public static void main(String[] args) {
        NumArray num = new NumArray(new int[]{9, -8});
        num.update(0, 3);
        System.out.println(num.sumRange(1, 1));
        System.out.println(num.sumRange(0, 1));
        num.update(1, -1);
        System.out.println(num.sumRange(0, 1));
    }
}
