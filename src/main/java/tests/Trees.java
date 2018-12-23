package tests;

import java.util.LinkedHashSet;
import java.util.Set;

public class Trees {
    public static void main(String[] args) {
        int[] input = {2};
        int ans = calculate(input);
        System.out.println(ans);
    }

    private static int calculate(int[] input) {
        Set<Integer> set = new LinkedHashSet<>();
        for (int i = 1; i < input.length; i++) {
            if (input[i - 1] > input[i]) {
                set.add(i - 1);
                set.add(i);
            }
        }
        if (set.size() > 2) return 0;
        if (set.size() == 0) return input.length;
        int ans = 0;
        for (int index : set) {
            ans += verify(input, index);
        }
        return ans;
    }

    private static int verify(int[] input, int index) {
        int[] ar = new int[input.length - 1];
        for (int i = 0, j = 0; i < input.length; i++) {
            if (i != index)
                ar[j++] = input[i];
        }
        for (int i = 1; i < ar.length; i++) {
            if (ar[i - 1] > ar[i])
                return 0;
        }
        return 1;
    }
}
