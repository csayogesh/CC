package hckrnk;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GameOfStones {

    static String gameOfStones(int n) {
        Map<Integer, Map<Integer, Integer>> dp = new HashMap();
        int ans = max(n, 1, dp);
        if (ans == 1)
            return "First";
        return "Second";
    }

    public static int max(int n, int turn, Map<Integer, Map<Integer, Integer>> dp) {
        if (n < 2)
            return turn * -1;
        if (dp.containsKey(n) && dp.get(n).containsKey(turn))
            return dp.get(n).get(turn);
        int x = max(n - 2, turn * -1, dp);
        int y = max(n - 3, turn * -1, dp);
        int z = max(n - 5, turn * -1, dp);
        dp.putIfAbsent(n, new HashMap());
        if (x == turn || y == turn || z == turn) {
            dp.get(n).put(turn, turn);
            return turn;
        }
        dp.get(n).put(turn, x);
        return x;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int a0 = 0; a0 < t; a0++) {
            int n = in.nextInt();
            String result = gameOfStones(n);
            System.out.println(result);
        }
        in.close();
    }
}
