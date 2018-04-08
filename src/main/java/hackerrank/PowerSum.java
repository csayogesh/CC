package hackerrank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by yogesh.bh on 08/04/18.
 */
public class PowerSum {
    static int powerSum(int X, int N) {
        List<Integer> powers = new ArrayList();
        int i = 1;
        while ((int) Math.pow(i, N) <= X) {
            powers.add((int) Math.pow(i, N));
            i++;
        }
        int ans = 0;
        for (i = 0; i < (1 << powers.size()); i++) {
            int sum = check(powers, i);
            if (sum == X)
                ans++;
        }
        return ans;
    }

    static int check(List<Integer> ls, int select) {
        int sum = 0;
        for (int i = 0; i < ls.size(); i++) {
            if ((select & (1 << i)) == (1 << i))
                sum += ls.get(i);
        }
        return sum;
    }

    static int totnum(int X, int N, int num) {
        if (Math.pow(num, N) < X)
            return totnum(X, N, num + 1) + totnum(X - (int) Math.pow(num, N), N, num + 1);
        else if (Math.pow(num, N) == X)
            return 1;
        else
            return 0;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int X = in.nextInt();
        int N = in.nextInt();
        long start = System.currentTimeMillis();
        int result = totnum(X, N, 1);
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(result);
        in.close();
    }
}
