package initial;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by yogesh.bh on 25/10/17 in CC.
 */
public class TileStacking {
    static int mod = (int) (Math.pow(10, 9) + 7);

    static int tileStackingProblem(int m, int n, int k) {
        ArrayList<Integer> prev = new ArrayList<>();
        ArrayList<Integer> cur = new ArrayList<>();
        for (int i = 0; i <= k; i++)
            prev.add(1);
        cur = prev;
        for (int i = 2; i <= m; i++) {
            cur = new ArrayList<>();
            for (int j = 0; j < (prev.size() + k); j++) {
                int sum = 0;
                for (int l = 0; l <= k; l++)
                    if ((j - l) >= 0 && (j - l) < prev.size())
                        sum = (sum + prev.get(j - l)) % mod;
                cur.add(sum);
            }
            prev = cur;
        }
        return cur.get(n);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int result = tileStackingProblem(m, n, k);
        System.out.println(result);
        in.close();
    }
}
