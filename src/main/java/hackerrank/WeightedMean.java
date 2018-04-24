package hackerrank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by yogesh.bh on 24/04/18.
 */
public class WeightedMean {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> ls = new ArrayList<>();
        for (int i = 0; i < n; i++)
            ls.add(sc.nextInt());
        double num = 0, den = 0;
        for (int i = 0; i < n; i++) {
            int nu = sc.nextInt();
            num += nu * ls.get(i);
            den += nu;
        }
        System.out.println(Math.round(num * 10 / den) / 10.0);
    }
}
