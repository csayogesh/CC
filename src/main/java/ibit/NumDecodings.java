package ibit;

public class NumDecodings {
    public int numDecodings(String s) {
        if (s.length() == 0 || s.startsWith("0")) return 0;
        int dp[] = new int[s.length() + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= s.length(); i++) {
            int x = Integer.parseInt(s.substring(i - 2, i));
            int y = Integer.parseInt(s.substring(i - 1, i));
            if (x > 9 && x <= 26) dp[i] += dp[i - 2];
            if (y > 0) dp[i] += dp[i - 1];
        }
        return dp[s.length()];
    }
}
