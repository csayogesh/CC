package leet;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yogesh.bh on 07/07/18.
 */
public class TwoSum {
    static public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i]) && i != map.get(target - nums[i]))
                return new int[]{i, map.get(target - nums[i])};
        }
        return null;
    }

    public static void main(String[] args) {
        twoSum(new int[]{2, 7, 11, 15}, 9);
    }
}
