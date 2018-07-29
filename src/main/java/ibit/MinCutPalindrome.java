package ibit;

public class MinCutPalindrome {
    public int minCut(String a) {
        String s = a;
        boolean[][] palin = new boolean[s.length()][s.length()];
        int[] res = new int[s.length()];
        for (int i = 0; i < s.length(); i++)
            palin[i][i] = true;
        int k = 1;
        while (k <= s.length()) {
            for (int i = 0; i < s.length() - k; i++) {
                int j = i + k;
                if (k == 1) palin[i][j] = s.charAt(i) == s.charAt(j);
                else
                    palin[i][j] = s.charAt(i) == s.charAt(j) && palin[i + 1][j - 1];
            }
            k++;
        }
        for (int i = 0; i < res.length; i++) res[i] = Integer.MAX_VALUE;
        for (int i = 0; i < s.length(); i++) {
            if (palin[0][i]) res[i] = 0;
            else {
                for (int j = 0; j < i; j++)
                    if (palin[j + 1][i] && res[j] + 1 < res[i])
                        res[i] = res[j] + 1;
            }
        }
        return res[s.length() - 1];
    }
}
