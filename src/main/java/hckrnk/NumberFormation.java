package hckrnk;

import java.util.*;

public class NumberFormation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> ls = new ArrayList<>();
        for (int i = 0; i < n; i++)
            ls.add(sc.nextInt());
        int k = sc.nextInt();
        if (k == 0 || n == 0) {
            System.out.println(0);
            return;
        }
        long dp[][] = new long[2][n];
        int cur = 0;
        int prev = 1;
        long cnt = 0;
        for (int i = 0; i < n; i++) {
            if (ls.get(i) != 0)
                cnt++;
            dp[cur][i] = cnt;
        }
        for (int i = 1; i < k; i++) {
            cur = (cur + 1) % 2;
            prev = (prev + 1) % 2;
            dp[cur][0] = 0;
            for (int j = 1; j < n; j++) {
                dp[cur][j] = dp[prev][j - 1] + dp[cur][j - 1];
            }
        }
        System.out.println(dp[cur][n - 1]);
    }


}
