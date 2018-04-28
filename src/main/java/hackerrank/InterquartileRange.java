package hackerrank;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by yogesh.bh on 26/04/18.
 */
public class InterquartileRange {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new TreeMap<>();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int num[] = new int[n];
        int freq[] = new int[n];
        for (int i = 0; i < n; i++)
            num[i] = sc.nextInt();
        for (int i = 0; i < n; i++)
            freq[i] = sc.nextInt();
        int tot = 0;
        for (int i = 0; i < n; i++) {
            map.put(num[i], freq[i]);
            tot += freq[i];
        }
        for (int i = 1; i < n; i++)
            freq[i] += freq[i - 1];
        double q1 = median(0, (int) Math.floor(median(0, tot - 1) - 0.1));
        double q3 = median((int) Math.ceil(median(0, tot - 1) + 0.1), tot - 1);

    }

    private static double median(int i, int j) {
        int n = j - i + 1;
        double[] median = new double[2];
        if (n % 2 == 1) {
            return i + n / 2;
        } else {
            double ans = i + n / 2;
            ans += i + (n - 1) / 2;
            ans /= 2;
            return ans;
        }
    }
}
