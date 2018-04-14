package hackerrank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Created by yogesh.bh on 14/04/18.
 */
public class Pairs {
    static long pairs(int k, int[] arr) {
        List<Integer> ls = new ArrayList<>();
        for (int x : arr)
            ls.add(x);
        Collections.sort(ls);
        int i = 0, j = 1;
        long cnt = 0;
        while (i <= j && j < arr.length) {
            int diff = ls.get(j) - ls.get(i);
            if (diff == k) {
                cnt++;
                j++;
            } else if (diff < k)
                j++;
            else if (diff > k)
                i++;
        }
        return cnt;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] arr = new int[n];
        for (int arr_i = 0; arr_i < n; arr_i++) {
            arr[arr_i] = in.nextInt();
        }
        long result = pairs(k, arr);
        System.out.println(result);
        in.close();
    }
}
