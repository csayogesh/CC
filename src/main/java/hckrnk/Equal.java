package hckrnk;

import java.util.Scanner;

public class Equal {
    static int equal(int[] arr) {
        int min = Integer.MAX_VALUE;
        for (int val : arr)
            min = Math.min(min, val);
        int moves = 0;
        for (int i = 0; i < arr.length; i++) {
            int cMoves = (arr[i] - min) / 5;
            moves += cMoves;
            arr[i] -= 5 * cMoves;
        }
        int freq[] = new int[5];
        for (int val : arr)
            freq[val - min]++;
        int extraMoves = (freq[1] + freq[2]) + 2 * (freq[3] + freq[4]);
        extraMoves = Math.min(extraMoves,
                (freq[0] + freq[1] + freq[4]) + 2 * (freq[2] + freq[3]));
        extraMoves = Math.min(extraMoves,
                (freq[0] + freq[3]) + 2 * (freq[1] + freq[2] + freq[4]));
        return moves + extraMoves;
    }

    private static int getMinMoves(int[] target, int[] pMoves) {
        int minMoves = 0;
        for (int tVal : target)
            for (int div : pMoves)
                if (tVal > 0) {
                    minMoves += tVal / div;
                    tVal %= div;
                }
        return minMoves;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int a0 = 0; a0 < t; a0++) {
            int n = in.nextInt();
            int[] arr = new int[n];
            for (int arr_i = 0; arr_i < n; arr_i++) {
                arr[arr_i] = in.nextInt();
            }
            int result = equal(arr);
            System.out.println(result);
        }
        in.close();
    }
}
