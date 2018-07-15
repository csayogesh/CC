package leet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yogesh.bh on 15/07/18.
 */
public class MaxSlidingWindow {
    static public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length < k || k == 0 || nums.length == 0)
            return new int[0];
        int[] ans = new int[nums.length - k + 1];
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < k && i < nums.length; i++) {
            pushElem(queue, nums, i);
        }
        int j = 0;
        for (int i = k; i < nums.length; i++) {
            ans[j++] = nums[queue.getFirst()];
            while (queue.size() > 0 && queue.getFirst() <= (i - k))
                queue.removeFirst();
            pushElem(queue, nums, i);
        }
        if (queue.size() > 0)
            ans[j] = nums[queue.removeFirst()];
        return ans;
    }

    private static void pushElem(LinkedList<Integer> queue, int[] nums, int i) {
        if (queue.size() == 0) {
            queue.add(i);
            return;
        }
        while (queue.size() > 0 && nums[queue.getLast()] < nums[i]) {
            queue.removeLast();
        }
        queue.addLast(i);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, -1};
        int[] ans = maxSlidingWindow(arr, 1);
        List<Integer> ls = new ArrayList<>();
        for (int x : ans)
            ls.add(x);
        System.out.println(ls);
    }
}
