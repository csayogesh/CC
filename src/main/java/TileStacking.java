import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by yogesh.bh on 25/10/17 in CC.
 */
public class TileStacking {
    static int mod = 1000000007;
    static Map<Integer, Map<Integer, Map<Integer, Boolean>>> dp = new HashMap<>();

    static int tileStackingProblem(int top, int top_left, int n, int k) {
        if (dp.containsKey(top) && dp.get(top).containsKey(top_left) && dp.get(top).get(top_left).containsKey(n))
            return 0;
        if (top < 0 || n < 0)
            return 0;
        if (n == 0)
            return 1;
        int cnt = 0;
        if (top_left > 0)
            cnt += tileStackingProblem(top, top_left - 1, n - 1, k) % mod;
        cnt += tileStackingProblem(top - 1, k, n - 1, k) % mod;
        dp.putIfAbsent(top, new HashMap<>());
        dp.get(top).putIfAbsent(top_left, new HashMap<>());
        dp.get(top).get(top_left).putIfAbsent(n, true);
        return cnt;

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int result = tileStackingProblem(m, k, n, k);
        System.out.println(result);
        in.close();
    }
}
