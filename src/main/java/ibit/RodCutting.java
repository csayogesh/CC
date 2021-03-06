package ibit;

import java.util.*;

/**
 * Created by yogesh.bh on 27/07/18.
 */
public class RodCutting {
    ArrayList<Integer> result;
    int[] cuts;
    int[][] parent;
    public ArrayList<Integer> rodCut(int rod, ArrayList<Integer> scores) {
        int n = scores.size() + 2;
        cuts = new int[n];
        cuts[0] = 0;
        for (int i = 0; i < scores.size(); i++) {
            cuts[i + 1] = scores.get(i);
        }
        cuts[n - 1] = rod;

        long[][] dp = new long[n][n];
        parent = new int[n][n];
        for (int len = 1; len <= n; len++) {
            for (int s = 0; s < n - len; s++) {
                int e = s + len;
                for (int k = s + 1; k < e; k++) {
                    long sum = cuts[e] - cuts[s] + dp[s][k] + dp[k][e];
                    if (dp[s][e] == 0 || sum < dp[s][e]) {
                        dp[s][e] = sum;
                        parent[s][e] = k;
                    }
                }
            }
        }

        result = new ArrayList<>();
        backTrack(0, n - 1);

        return result;
    }

    private void backTrack(int s, int e) {
        if (s + 1 >= e) {
            return;
        }

        result.add(cuts[parent[s][e]]);
        backTrack(s, parent[s][e]);
        backTrack(parent[s][e], e);
    }
}
