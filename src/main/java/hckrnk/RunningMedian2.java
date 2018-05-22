package hckrnk;

import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Created by yogesh.bh on 05/05/18.
 */
public class RunningMedian2 {
    static double[] runningMedian(int[] a) {
        TreeSet<Integer> half1 = new TreeSet<>((i1, i2) -> {
            int res = Integer.compare(i1, i2);
            if (res == 0)
                return -1;
            return res;
        });
        TreeSet<Integer> half2 = new TreeSet<>((i1, i2) -> {
            int res = Integer.compare(i1, i2);
            if (res == 0)
                return -1;
            return res;
        });
        double[] med = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            if (half1.size() > 0 && half1.last() <= a[i])
                half2.add(a[i]);
            else half1.add(a[i]);
            while (half2.size() > half1.size())
                half1.add(half2.pollFirst());
            while ((half1.size() - half2.size()) > 1)
                half2.add(half1.pollLast());
            med[i] = half1.last();
            if (half2.size() == half1.size()) {
                med[i] += half2.first();
                med[i] /= 2.0;
            }
        }
        return med;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int aCount = Integer.parseInt(scanner.nextLine().trim());

        int[] a = new int[aCount];

        for (int aItr = 0; aItr < aCount; aItr++) {
            int aItem = Integer.parseInt(scanner.nextLine().trim());
            a[aItr] = aItem;
        }

        double[] result = runningMedian(a);
    }
}
