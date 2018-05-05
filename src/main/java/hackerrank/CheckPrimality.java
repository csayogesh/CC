package hackerrank;

import java.util.Scanner;

/**
 * Created by yogesh.bh on 05/05/18.
 */
public class CheckPrimality {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int p = in.nextInt();
        for (int a0 = 0; a0 < p; a0++) {
            int n = in.nextInt();
            boolean res = checkPrime(n);
            if (res)
                System.out.println("Prime");
            else System.out.println("Not prime");
        }
    }

    private static boolean checkPrime(int n) {
        if (n < 2)
            return false;
        int max = (int) Math.sqrt(n);
        for (int i = 2; i <= max; i++)
            if (n % i == 0)
                return false;
        return true;
    }
}
