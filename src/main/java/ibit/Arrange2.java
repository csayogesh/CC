package ibit;

import java.util.HashMap;
import java.util.Map;

public class Arrange2 {
    int[] w;
    int[] b;

    public int arrange(String A, int B) {
        if (B > A.length()) return -1;
        w = new int[A.length()];
        b = new int[A.length()];
        for (int i = 0; i < A.length(); i++) {
            if (A.charAt(i) == 'W')
                w[i] = 1;
            else b[i] = 1;
        }
        for (int i = 1; i < A.length(); i++) {
            w[i] += w[i - 1];
            b[i] += b[i - 1];
        }
        return get(A, B, 0, A.length() - 1);
    }

    Map<Integer, Map<Integer, Integer>> map = new HashMap();

    private int get(String a, int B, int i, int j) {
        if (B == 1) {
            int x = w[j];
            int y = b[j];
            if (i != 0) {
                x -= w[i - 1];
                y -= b[i - 1];
            }
            return x * y;
        }
        int in = i * (a.length() + 1) + j;
        map.putIfAbsent(B, new HashMap());
        if (map.get(B).get(in) != null) return map.get(B).get(in);
        int res = Integer.MAX_VALUE;
        for (int k = i + 1; k <= j; k++) {
            int tmp = Math.min(res, get(a, 1, i, k - 1) + get(a, B - 1, k, j));
            if (tmp >= 0) res = tmp;
        }
        map.get(B).put(in, res);
        return res;
    }
}
