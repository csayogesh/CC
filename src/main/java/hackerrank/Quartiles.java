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
        double q2 = median(ls, 0, ls.size() - 1);
        double q1, q3;
        if (n % 2 == 1) {
            q1 = median(ls, 0, -1 + n / 2);
            q3 = median(ls, 1 + n / 2, n - 1);
        } else {
            q1 = median(ls, 0, (n-1) / 2);
            q3 = median(ls, n / 2, n - 1);
        }
        System.out.println(q1);
        System.out.println(q2);
        System.out.println(q3);
    }

    private static double median(List<Integer> ls, int i, int j) {
        int n = j - i + 1;
        double median = 0;
        if (n % 2 == 1)
            median = ls.get(i + n / 2);
        else {
            median += ls.get(i + n / 2);
            median += ls.get(i + (n - 1) / 2);
            median /= 2;
        }
        return median;
    }
}
