package hackerrank;

import java.util.Scanner;

/**
 * Created by yogesh.bh on 28/04/18.
 */
public class NimGame {
    static String nimGame(int[] pile) {
        int ans = 0;
        for (int n : pile)
            ans = ans ^ n;
        if (ans == 0)
            return "First";
        return "Second";
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int g = in.nextInt();
        for (int a0 = 0; a0 < g; a0++) {
            int n = in.nextInt();
            int[] pile = new int[n];
            for (int pile_i = 0; pile_i < n; pile_i++) {
                pile[pile_i] = in.nextInt();
            }
            String result = nimGame(pile);
            System.out.println(result);
        }
        in.close();
    }
}
