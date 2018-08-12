package leet;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Pattern132 {
    public boolean find132pattern(int[] nums) {
        if (nums.length < 3) return false;
        Stack<Integer> stack = new Stack<>();
        List<Integer> mins = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            if (mins.size() == 0) mins.add(cur);
            else if (mins.get(mins.size() - 1) > cur) mins.add(cur);
            else mins.add(mins.get(mins.size() - 1));
            while (stack.size() > 0 && nums[stack.peek()] <= cur)
                stack.pop();
            if (stack.size() > 0) {
                int index = stack.peek();
                int min = mins.get(index);
                if (min < cur) return true;
            }
            stack.push(i);
        }
        return false;
    }

    public static void main(String[] args) {
        boolean in = new Pattern132().find132pattern(new int[]{4, 1, 3, 2});
        System.out.println(in);
    }
}
