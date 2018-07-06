package leet;

/**
 * Created by yogesh.bh on 04/07/18.
 */
public class EliminationGame {
    public static int lastRemaining(int n) {
        int step = 1, remaining = n;
        int ans = 1;
        boolean flg = true;
        while (remaining > 1) {
            if (flg || remaining % 2 == 1)
                ans += step;
            remaining /= 2;
            step *= 2;
            flg = !flg;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(lastRemaining(9));
    }
}
