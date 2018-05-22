package hckrnk;

import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Created by yogesh.bh on 22/04/18.
 */
public class GreedyFloryist {
    static int getMinimumCost(int n, int k, int[] c) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int elem : c)
            set.add(elem);
        Iterator<Integer> it = set.iterator();
        for (int i = 0; i < n; i++)
            c[i] = it.next();
        set.clear();
        int ans = 0;
        int i, j;
        int mul = 1;
        for (i = n - 1, j = 0; i >= 0; i--) {
            ans += c[i] * mul;
            j++;
            if (j % k == 0)
                mul++;
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] c = new int[n];
        for (int c_i = 0; c_i < n; c_i++) {
            c[c_i] = in.nextInt();
        }
        int minimumCost = getMinimumCost(n, k, c);
        System.out.println(minimumCost);
    }
}
