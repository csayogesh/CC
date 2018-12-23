package hckrearth;

import java.util.HashMap;
import java.util.Scanner;

public class LargestZeroRectangle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int arr[][] = new int[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                arr[i][j] = sc.nextInt();

        int colPrefSum[][] = new int[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                if (i > 0)
                    colPrefSum[i][j] += colPrefSum[i - 1][j];
                colPrefSum[i][j] += arr[i][j];
            }

        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = i; j < m; j++) {
                int width = j - i + 1;
                HashMap<Integer, Integer> dp = new HashMap<>();
                dp.put(0, -1);
                int runSum = 0;
                for (int k = 0; k < n; k++) {
                    int cur = 0;
                    if (i > 0)
                        cur -= colPrefSum[i - 1][k];
                    cur += colPrefSum[j][k];
                    runSum += cur;
                    if (dp.containsKey(runSum))
                        ans = Math.max(ans, (k-dp.get(runSum)) * width);
                    else dp.put(runSum, k);
                }

            }
        }
        System.out.println(ans);
    }
}
