package leet;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yogesh.bh on 20/07/18.
 */
public class LongestConsecutiveSequence {
    static public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> rev = new HashMap<>();
        for (int num : nums)
            insert(map, rev, num);
        int ans = 0;
        for (Map.Entry<Integer, Integer> en : map.entrySet())
            ans = Math.max(en.getValue() - en.getKey() + 1, ans);
        return ans;
    }

    private static void insert(Map<Integer, Integer> map, Map<Integer, Integer> rev, int num) {
        if (map.containsKey(num) || rev.containsKey(num))
            return;
        if (rev.containsKey(num - 1) && map.containsKey(num + 1)) {
            int st = rev.remove(num - 1);
            int en = map.remove(num + 1);
            map.put(st, en);
            rev.put(en, st);
        } else if (rev.containsKey(num - 1)) {
            int st = rev.remove(num - 1);
            map.put(st, num);
            rev.put(num, st);
        } else if (map.containsKey(num + 1)) {
            int en = map.remove(num + 1);
            map.put(num, en);
            rev.put(en, num);
        } else {
            map.put(num, num);
            rev.put(num, num);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{100, 4, 200, 1, 3, 2};
        int s = longestConsecutive(arr);
        System.out.println(s);
    }
}
