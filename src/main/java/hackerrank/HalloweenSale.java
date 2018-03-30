package hackerrank;

import java.util.Scanner;

/**
 * Created by yogesh.bh on 27/10/17 in CC.
 */
public class HalloweenSale {
    static int howManyGames(int p, int d, int m, int s) {
        int n1 = (m - d - p) / -d;
        int an1 = p - (n1 - 1) * d;
        int sum1 = n1 * (p + an1) / 2;
        if (sum1 > s)
            return 2 * s / (p + an1);
        else if (sum1 == s)
            return n1;
        s -= sum1;
        return n1 + s / m;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int p = in.nextInt();
        int d = in.nextInt();
        int m = in.nextInt();
        int s = in.nextInt();
        int answer = howManyGames(p, d, m, s);
        System.out.println(answer);
        in.close();
    }
}
