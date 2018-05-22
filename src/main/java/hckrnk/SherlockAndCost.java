package hckrnk;

import java.util.*;

/**
 * Created by yogesh.bh on 10/04/18.
 */
public class SherlockAndCost {
    static int cost1(int[] arr) {
        int temp1[] = new int[2];
        int temp2[] = new int[2];
        temp1[0] = 1;
        temp2[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            int sum1 = temp1[1] + Math.abs(temp1[0] - 1);
            int sum2 = temp1[1] + Math.abs(temp1[0] - arr[i]);
            if (temp1[1] < sum1) {
                temp1[0] = 1;
                temp1[1] = sum1;
            } else if (temp1[1] < sum2) {
                temp1[0] = arr[i];
                temp1[1] = sum2;
            }
            sum1 = temp2[1] + Math.abs(temp2[0] - 1);
            sum2 = temp2[1] + Math.abs(temp2[0] - arr[i]);
            if (temp2[1] < sum1) {
                temp2[0] = 1;
                temp2[1] = sum1;
            } else if (temp2[1] < sum2) {
                temp2[0] = arr[i];
                temp2[1] = sum2;
            }
        }
        return Math.max(temp1[1], temp2[1]);
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
            int result = cost(arr);
            System.out.println(result);
        }
        in.close();
    }

    private static int cost(int[] arr) {
        int hi, low;
        hi = low = 0;
        for (int i = 1; i < arr.length; i++) {
            int lowToHigh = Math.abs(1 - arr[i]);
            int highToLow = Math.abs(arr[i - 1] - 1);
            int highToHigh = Math.abs(arr[i - 1] - arr[i]);

            int lowNext = Math.max(low, hi + highToLow);
            int hiNext = Math.max(hi + highToHigh, low + lowToHigh);

            low = lowNext;
            hi = hiNext;
        }
        return Math.max(hi, low);
    }

    private static Map<Integer, Map<Integer, Integer>> dp = new HashMap<>();

    private static int cost2(int[] arr, int i, int end) {
        if (i == 0)
            return 0;
        if (dp.containsKey(i) && dp.get(i).containsKey(end))
            return dp.get(i).get(end);
        dp.putIfAbsent(i, new HashMap<>());
        int sub1 = cost2(arr, i - 1, 1);
        int sub2 = cost2(arr, i - 1, arr[i - 1]);
        List<Integer> sums = new ArrayList<>();
        sums.add(sub1 + Math.abs(end - 1));
        sums.add(sub2 + Math.abs(end - arr[i - 1]));
        int ans = Collections.max(sums);
        dp.get(i).put(end, ans);
        return ans;
    }
}
