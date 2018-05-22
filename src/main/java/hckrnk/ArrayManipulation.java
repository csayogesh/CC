package hckrnk;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by yogesh.bh on 12/05/18.
 */
public class ArrayManipulation {
    /*
     * Complete the arrayManipulation function below.
     */
    static int arrayManipulation(int n, int[][] queries) {
        int arr[] = new int[n + 1];
        for (int[] q : queries) {
            arr[q[0]] += q[2];
            if (q[1] < n)
                arr[q[1] + 1] -= q[2];
        }
        int ans = 0;
        int cur = 0;
        for (int i = 1; i <= n; i++) {
            cur += arr[i];
            ans = Math.max(ans, cur);
        }
        return ans;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0].trim());

        int m = Integer.parseInt(nm[1].trim());

        int[][] queries = new int[m][3];

        for (int queriesRowItr = 0; queriesRowItr < m; queriesRowItr++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");

            for (int queriesColumnItr = 0; queriesColumnItr < 3; queriesColumnItr++) {
                int queriesItem = Integer.parseInt(queriesRowItems[queriesColumnItr].trim());
                queries[queriesRowItr][queriesColumnItr] = queriesItem;
            }
        }

        int result = arrayManipulation(n, queries);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}
