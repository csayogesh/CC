package leet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yogesh.bh on 10/07/18.
 */
public class Subsets2 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> cur = new LinkedList<>();
        generate(nums, 0, cur, res);
        return res;
    }

    private void generate(int[] nums, int i, LinkedList<Integer> cur, List<List<Integer>> res) {
        if (i == nums.length) {
            res.add(new ArrayList<>(cur));
            return;
        }
        generate(nums, i + 1, cur, res);
        cur.addLast(nums[i]);
        generate(nums, i + 1, cur, res);
        cur.removeLast();
    }
}
