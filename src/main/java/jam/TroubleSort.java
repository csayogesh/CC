package jam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by yogesh.bh on 07/04/18.
 */
public class TroubleSort {
    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer line = new StringTokenizer(sc.readLine(), " ");
        int nt = Integer.parseInt(line.nextToken());
        for (int i = 0; i < nt; i++) {
            line = new StringTokenizer(sc.readLine(), " ");
            int n = Integer.parseInt(line.nextToken());
            line = new StringTokenizer(sc.readLine(), " ");
            List<Integer> ls = new ArrayList<>();
            for (int j = 0; j < n; j++)
                ls.add(Integer.parseInt(line.nextToken()));
            System.out.print("Case #");
            System.out.print(i + 1);
            System.out.print(": ");
            int ans = cal(ls);
            if (ans == -1)
                System.out.println("OK");
            else System.out.println(ans);
        }
    }

    private static int cal(List<Integer> ls) {
        List<Integer> ls1 = new ArrayList<>();
        List<Integer> ls2 = new ArrayList<>();
        for (int i = 0; i < ls.size(); i++) {
            if (i % 2 == 0)
                ls1.add(ls.get(i));
            else ls2.add(ls.get(i));
        }
        Collections.sort(ls1);
        Collections.sort(ls2);
        Iterator<Integer> it1 = ls1.iterator();
        int currentMax = it1.next();
        int maxInd = 0;
        Iterator<Integer> it2 = ls2.iterator();
        for (int i = 1; i < ls.size(); i++) {
            int elem;
            if (i % 2 == 0)
                elem = it1.next();
            else elem = it2.next();
            if (elem < currentMax)
                return maxInd;
            maxInd = i;
            currentMax = elem;
        }
        return -1;
    }
}
