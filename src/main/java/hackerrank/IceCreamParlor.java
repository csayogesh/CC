package hackerrank;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by yogesh.bh on 02/05/18.
 */
public class IceCreamParlor {
    static void solve(int[] arr, int money) {
        // Complete this function
        Map<Integer, Integer> map = new HashMap<>();
        map.put(arr[0], 1);
        int[] ans = {0, 0};
        for (int i = 1; i < arr.length; i++) {
            if (map.containsKey(money - arr[i])) {
                ans[0] = map.get(money - arr[i]);
                ans[1] = i + 1;
                break;
            }
            map.putIfAbsent(arr[i], i + 1);
        }
        System.out.println(ans[0] + " " + ans[1]);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int a0 = 0; a0 < t; a0++) {
            int money = in.nextInt();
            int n = in.nextInt();
            int[] arr = new int[n];
            for (int arr_i = 0; arr_i < n; arr_i++) {
                arr[arr_i] = in.nextInt();
            }
            solve(arr, money);
        }
        in.close();
    }
}
