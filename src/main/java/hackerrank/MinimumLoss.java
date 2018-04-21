package hackerrank;

import java.util.Scanner;
import java.util.TreeSet;

/**
 * Created by yogesh.bh on 22/04/18.
 */
public class MinimumLoss {
    static int minimumLoss(long[] price) {
        TreeSet<Long> set = new TreeSet<>();
        set.add(price[0]);
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < price.length; i++) {
            Long higher = set.ceiling(price[i]);
            if (higher != null)
                ans = (int) Math.min(higher - price[i], ans);
            set.add(price[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long[] price = new long[n];
        for (int price_i = 0; price_i < n; price_i++) {
            price[price_i] = in.nextLong();
        }
        int result = minimumLoss(price);
        System.out.println(result);
        in.close();
    }
}
