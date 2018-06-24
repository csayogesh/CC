package hckrnk.cmpt;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by yogesh.bh on 23/06/18.
 */
public class MinuteToWin {

    static int minuteToWinIt(int[] a, int k) {
        int dp[] = new int[a.length];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < a.length; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if ((a[j] + (i - j) * k) == a[i]) {
                    dp[i] = dp[j] + 1;
                    break;
                }
            }
            max = Math.max(max, dp[i]);
        }
        return a.length - max;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[] a = new int[n];

        String[] aItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int aItem = Integer.parseInt(aItems[i]);
            a[i] = aItem;
        }

        int result = minuteToWinIt(a, k);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
