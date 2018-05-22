package hckrnk;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by yogesh.bh on 22/04/18.
 */
public class FindMedian {
    static int findMedian(int[] arr) {
        Arrays.sort(arr);
        return arr[(arr.length) / 2];
//        return find(arr, 0, arr.length - 1, (arr.length + 1) / 2);
    }

    private static int find(int[] arr, int i, int j, int n) {
        int pivot = arr[(int) (i + (j - i + 1) * Math.random())];
        int st = i;
        int en = j;
        for (; i < j; ) {
            while (arr[i] < pivot && i < j)
                i++;
            while (arr[j] > pivot && i < j)
                j--;
            if (i < j) {
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        int rank = j - st + 1;
        if (rank == n)
            return arr[j];
        if (rank > n)
            return find(arr, st, st + rank, n);
        return find(arr, st + rank, en, n - rank);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int arr_i = 0; arr_i < n; arr_i++) {
            arr[arr_i] = in.nextInt();
        }
        int result = findMedian(arr);
        System.out.println(result);
        in.close();
    }
}
