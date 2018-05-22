package hckrnk;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by yogesh.bh on 22/04/18.
 */
public class MinMax {
    static int angryChildren(int k, int[] arr) {
        Arrays.sort(arr);
        int ans = Integer.MAX_VALUE;
        for (int i = 0, j = k - 1; j < arr.length; i++, j++)
            ans = Math.min(arr[j] - arr[i], ans);
        return ans;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] arr = new int[n];
        for (int arr_i = 0; arr_i < n; arr_i++) {
            arr[arr_i] = in.nextInt();
        }
        int result = angryChildren(k, arr);
        System.out.println(result);
        in.close();
    }
}
