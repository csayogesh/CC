package hckrnk;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by yogesh.bh on 16/06/18.
 */
public class QueriesWithFixedLength {
    // Complete the solve function below.
    static int[] solve(int[] arr, int[] queries) {
        int[] ans = new int[queries.length];
        int ind = 0;
        int log = (int) Math.ceil(Math.log(arr.length) / Math.log(2));
        int[][] parseTable = new int[arr.length][log + 1];
        for (int i = 0; i <= log; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (i == 0)
                    parseTable[j][i] = arr[j];
                else {
                    if (j + (1 << (i - 1)) < arr.length)
                        parseTable[j][i] = Math.max(parseTable[j][i - 1], parseTable[j + (1 << (i - 1))][i - 1]);
                    else parseTable[j][i] = parseTable[j][i - 1];
                }
            }
        }
        for (int q : queries) {
            ans[ind] = Integer.MAX_VALUE;
            for (int i = 0; i <= arr.length - q; i++) {
                int lg = (int) (Math.log(q) / Math.log(2));
                int end = i + q - 1;
                int upto = i + (1 << lg) - 1;
                int max = parseTable[i][lg];
                if (upto < end) {
                    max = Math.max(max, parseTable[end + 1 - (1 << lg)][lg]);
                }
                ans[ind] = Math.min(ans[ind], max);
            }
            ind++;
        }
        return ans;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        String[] nq = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nq[0]);

        int q = Integer.parseInt(nq[1]);

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int arrItr = 0; arrItr < n; arrItr++) {
            int arrItem = Integer.parseInt(arrItems[arrItr]);
            arr[arrItr] = arrItem;
        }

        int[] queries = new int[q];

        for (int queriesItr = 0; queriesItr < q; queriesItr++) {
            int queriesItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            queries[queriesItr] = queriesItem;
        }

        int[] result = solve(arr, queries);
        System.out.println(result);

        scanner.close();
    }
}
