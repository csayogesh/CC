package hckrnk;

import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by yogesh.bh on 16/06/18.
 */
public class QueriesWithFixedLength {
    // Complete the solve function below.
    static int[] solve(int[] arr, int[] queries) {
        int[] ans = new int[queries.length];
        int ind = 0;
        for (int q : queries) {
            TreeMap<Integer, Integer> treeMap = new TreeMap<>();
            int curSize = 0;
            ans[ind] = Integer.MAX_VALUE;
            for (int i = 0; i < arr.length; i++) {
                treeMap.putIfAbsent(arr[i], 0);
                treeMap.put(arr[i], treeMap.get(arr[i]) + 1);
                curSize++;
                if (curSize > q) {
                    int cnt = treeMap.get(arr[i - q]);
                    if (cnt == 1)
                        treeMap.remove(arr[i-q]);
                    else treeMap.put(arr[i-q], treeMap.get(arr[i-q]) - 1);
                    curSize--;
                }
                if (curSize == q) {
                    ans[ind] = Math.min(ans[ind], treeMap.lastKey());
                }
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
