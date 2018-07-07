package leet;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by yogesh.bh on 07/07/18.
 */
public class ThreeSum {
    public static List<List<Integer>> threeSum(int[] nums) {
        ArrayList<Integer> ls = new ArrayList<>();
        for (int num : nums)
            ls.add(num);
        Set<List<Integer>> ans = new LinkedHashSet<>();
        ls.sort(Integer::compare);
        for (int i = 0; i < ls.size() - 2; i++) {
            int target2 = 0 - ls.get(i);
            for (int k = i + 1, l = ls.size() - 1; k < l; ) {
                if ((ls.get(k) + ls.get(l)) < target2) k++;
                else if ((ls.get(k) + ls.get(l)) > target2) l--;
                else {
                    List<Integer> set = new ArrayList<>();
                    set.add(ls.get(i));
                    set.add(ls.get(k));
                    set.add(ls.get(l));
                    ans.add(set);
                    k++;
                    l--;
                }
            }
        }
        return new ArrayList<>(ans);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{-1, 0, 1, 2, -1, -4};
        threeSum(arr);
    }
}
