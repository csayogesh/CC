package hackerrank;

import java.util.*;

/**
 * Created by yogesh.bh on 24/04/18.
 */
public class MeanMedianMode {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> ls = new ArrayList<>();
        double mean = 0;
        Map<Integer, Integer> counts = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            ls.add(sc.nextInt());
            mean += ls.get(i);
            counts.putIfAbsent(ls.get(i), 0);
            counts.put(ls.get(i), counts.get(ls.get(i))+1);
        }
        mean /= n;
        Collections.sort(ls);
        double median = 0;
        if (n % 2 == 1)
            median = ls.get(n / 2);
        else {
            median += ls.get(n / 2);
            median += ls.get((n - 1) / 2);
            median /= 2;
        }
        int mode = 0, mcnt = 0;
        for (Map.Entry<Integer, Integer> e : counts.entrySet()) {
            if (e.getValue() > mcnt) {
                mode = e.getKey();
                mcnt = e.getValue();
            }
        }

        System.out.println(mean);
        System.out.println(median);
        System.out.println(mode);
    }
}
