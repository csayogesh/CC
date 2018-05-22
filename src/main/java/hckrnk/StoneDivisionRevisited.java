package hckrnk;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by yogesh.bh on 21/04/18.
 */
public class StoneDivisionRevisited {

    static long stoneDivision(long n, long[] s) {
        Map<Long, Long> maxMovesRequired = new HashMap<>();
        return max(n, s, maxMovesRequired);
    }

    private static long max(long n, long[] s, Map<Long, Long> max) {
        if (max.containsKey(n))
            return max.get(n);
        long cnt = 0;
        for (long div : s) {
            if (n % div == 0 && n > div) {
                long subCnt = 0;
                long k = n / div;
                subCnt += 1 + k * max(div, s, max);
                cnt = Math.max(cnt, subCnt);
            }
        }
        max.put(n, cnt);
        return cnt;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for (int a0 = 0; a0 < q; a0++) {
            long n = in.nextLong();
            int m = in.nextInt();
            long[] s = new long[m];
            for (int s_i = 0; s_i < m; s_i++) {
                s[s_i] = in.nextLong();
            }
            long result = stoneDivision(n, s);
            System.out.println(result);
        }
        in.close();
    }
}
