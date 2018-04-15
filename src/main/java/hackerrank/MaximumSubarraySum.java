package hackerrank;

import java.util.Scanner;
import java.util.TreeSet;

/**
 * Created by yogesh.bh on 15/04/18.
 */
public class MaximumSubarraySum {
    static long maximumSum(long[] a, long m) {
        long cur = 0;
        for (int i = 0; i < a.length; i++) {
            a[i] = (cur + a[i] % m) % m;
            cur = a[i];
        }
        TreeSet<Long> set = new TreeSet<>();
        long ans = 0;
        for (int i = 0; i < a.length; i++) {
            Long high = set.higher(a[i]);
            if (high != null)
                ans = Math.max(ans, (a[i] - high + m) % m);
            ans = Math.max(ans, a[i]);
            if (ans == m - 1)
                break;
            set.add(a[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for (int a0 = 0; a0 < q; a0++) {
            int n = in.nextInt();
            long m = in.nextLong();
            long[] a = new long[n];
            for (int a_i = 0; a_i < n; a_i++) {
                a[a_i] = in.nextLong();
            }
            long result = maximumSum(a, m);
            System.out.println(result);
        }
        in.close();
    }
}
