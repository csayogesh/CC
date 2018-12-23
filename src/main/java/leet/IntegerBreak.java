package leet;

import java.util.HashMap;

public class IntegerBreak {
    HashMap<Integer, Integer> dp = new HashMap<>();

    public int integerBreak(int n) {
        int ans = 0;
        for (int i = 1; i < n; i++) {
            int temp = i * doBreak(n - i);
            if (temp > ans) {
                ans = Math.max(ans, temp);
            }
        }
        return ans;
    }

    public int doBreak(int n) {
        if (n <= 1)
            return 1;
        if (dp.containsKey(n))
            return dp.get(n);
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            int temp = i * doBreak(n - i);
            if (temp > ans) {
                ans = Math.max(ans, temp);
            }

        }
        dp.put(n, ans);
        return ans;
    }

    public static void main(String[] args) {
        IntegerBreak ans = new IntegerBreak();
        int s = ans.integerBreak(10);
        System.out.println(s);
        System.out.println(ans.dp);
    }
}
