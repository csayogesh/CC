package hckrnk;

import java.util.Scanner;

/**
 * Created by yogesh.bh on 22/04/18.
 */
public class RecursiveSumDigit {
    static int digitSum(String n, int k) {
        long sum = 0;
        for (int i = 0; i < n.length(); i++) {
            sum += n.charAt(i) - '0';
        }
        String val = String.valueOf(k * sum);
        if (val.length() > 1)
            return digitSum(val, 1);
        return (int) (k * sum);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String n = in.next();
        int k = in.nextInt();
        int result = digitSum(n, k);
        System.out.println(result);
        in.close();
    }
}
