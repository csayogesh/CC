package leet;

public class ArrayLoop {
    public boolean circularArrayLoop(int[] nums) {
        int st = 0;
        while (st < nums.length) {
            int i = next(st, nums);
            int sign = 1;
            if (nums[st] != 0)
                sign = nums[st] / Math.abs(nums[st]);
            if (i == st) {
                nums[st++] = 0;
                continue;
            }
            while (i != st && nums[i] != 0 && nums[i] * sign > 0) {
                int j = next(i, nums);
                nums[i] = 0;
                i = j;
            }
            if (i == st) return true;
            nums[st] = 0;
            while (st < nums.length - 1 && nums[++st] != 0) ;
        }
        return false;
    }

    private int next(int st, int[] nums) {
        int ans = st + nums[st] + nums.length;
        return ans % nums.length;
    }
}
