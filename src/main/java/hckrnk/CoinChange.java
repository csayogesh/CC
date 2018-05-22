package hckrnk;

import java.util.Scanner;

/**
 * Created by yogesh.bh on 05/05/18.
 */
public class CoinChange {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int coins[] = new int[m];
        for (int coins_i = 0; coins_i < m; coins_i++) {
            coins[coins_i] = in.nextInt();
        }
        long[][] dp = new long[n + 1][];
        for (int i = 0; i < dp.length; i++)
            dp[i] = new long[m + 1];
        for (int i = 0; i <= m; i++)
            dp[0][i] = 1;
        for (int sum = 1; sum <= n; sum++) {
            for (int j = 1; j <= m; j++) {
                if (sum >= coins[j - 1])
                    dp[sum][j] += dp[sum - coins[j - 1]][j];
                dp[sum][j] += dp[sum][j - 1];
            }
        }
        System.out.println(dp[n][m]);
    }


}
