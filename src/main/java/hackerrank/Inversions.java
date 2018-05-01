package hackerrank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by yogesh.bh on 01/05/18.
 * Merge Sort
 */
public class Inversions {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int j = 0; j < t; j++) {
            int n = sc.nextInt();
            List<Integer> ls = new ArrayList<>();
            for (int i = 0; i < n; i++)
                ls.add(sc.nextInt());
            long swaps = inversions(ls, 0, n - 1);
            System.out.println(swaps);
        }
    }

    private static long inversions(List<Integer> ls, int i, int j) {
        if (i >= j)
            return 0;
        int mid = (i + j) / 2;
        long inv1 = inversions(ls, i, mid);
        long inv2 = inversions(ls, mid + 1, j);
        long inv3 = merge(ls, i, mid, mid + 1, j);
        return inv1 + inv2 + inv3;
    }

    private static long merge(List<Integer> ls, int i, int j, int k, int l) {
        int[] tmp = new int[l - i + 1];
        long ans = 0;
        int oldI = i;
        for (int ind = 0; ind < tmp.length; ind++) {
            if (k > l || (i <= j && ls.get(i) <= ls.get(k)))
                tmp[ind] = ls.get(i++);
            else if (k <= l) {
                tmp[ind] = ls.get(k);
                if (i <= j && k <= l && ls.get(i) > ls.get(k))
                    ans += j - i + 1;
                k++;
            }
        }
        for (int ind = 0; ind < tmp.length; ind++) {
            ls.set(oldI++, tmp[ind]);
        }
        return ans;
    }
}
