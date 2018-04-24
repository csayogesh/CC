package hackerrank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Created by yogesh.bh on 24/04/18.
 */
public class Quartiles {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> ls = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ls.add(sc.nextInt());
        }
        Collections.sort(ls);
        double[] ar = median(ls, 0, ls.size() - 1);
        double q2 = ar[0];
        double q1 = median(ls, 0, (int) Math.floor(ar[1] - 0.1))[0];
        double q3 = median(ls, 0, (int) Math.ceil(ar[1] + 0.1))[0];
        System.out.println(q1);
        System.out.println(q2);
        System.out.println(q3);
    }

    private static double[] median(List<Integer> ls, int i, int j) {
        int n = j - i + 1;
        double[] median = new double[2];
        if (n % 2 == 1) {
            median[1] = i + n / 2;
            median[0] = ls.get((int) median[1]);
        } else {
            median[1] = i + n / 2;
            median[1] += i + (n - 1) / 2;
            median[0] += ls.get(i + n / 2);
            median[0] += ls.get(i + (n - 1) / 2);
            median[0] /= 2;
            median[1] /= 2;
        }
        return median;
    }
}
