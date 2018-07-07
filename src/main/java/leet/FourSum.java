package leet;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by yogesh.bh on 07/07/18.
 */
public class FourSum {
    static public List<List<Integer>> fourSum(int[] nums, int target) {
        ArrayList<Integer> ls = new ArrayList<>();
        for (int num : nums)
            ls.add(num);
        Set<List<Integer>> ans = new LinkedHashSet<>();
        ls.sort(Integer::compare);
        for (int i = 0; i < ls.size() - 3; i++) {
            for (int j = i + 1; j < ls.size() - 2; j++) {
                int target2 = target - ls.get(i) - ls.get(j);
                for (int k = j + 1, l = ls.size() - 1; k < l; ) {
                    if ((ls.get(k) + ls.get(l)) < target2) k++;
                    else if ((ls.get(k) + ls.get(l)) > target2) l--;
                    else {
                        List<Integer> set = new ArrayList<>();
                        set.add(ls.get(i));
                        set.add(ls.get(j));
                        set.add(ls.get(k));
                        set.add(ls.get(l));
                        ans.add(set);
                        k++;
                        l--;
                    }
                }
            }
        }
        return new ArrayList<>(ans);
    }

    public static void main(String[] args) {
        int input[] = new int[]{1, 0, -1, 0, -2, 2}, target = 0;
        List<List<Integer>> ans = fourSum(input, target);
        System.out.println(ans);
    }
}
