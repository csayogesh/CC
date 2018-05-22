package hckrnk;

import java.util.Scanner;
import java.util.TreeSet;

/**
 * Created by yogesh.bh on 22/04/18.
 */
public class MinimumAbsDiff {
    static int minimumAbsoluteDifference(int n, int[] arr) {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(arr[0]);
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < arr.length; i++) {
            Integer high = set.ceiling(arr[i]);
            Integer low = set.floor(arr[i]);
            if (high != null)
                ans = Math.min(ans, Math.abs(arr[i] - high));
            if (low != null)
                ans = Math.min(ans, Math.abs(arr[i] - low));
            set.add(arr[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int arr_i = 0; arr_i < n; arr_i++) {
            arr[arr_i] = in.nextInt();
        }
        int result = minimumAbsoluteDifference(n, arr);
        System.out.println(result);
        in.close();
    }
}
