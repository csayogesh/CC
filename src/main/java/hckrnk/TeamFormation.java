package hckrnk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by yogesh.bh on 22/04/18.
 */
public class TeamFormation {
    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer line = new StringTokenizer(sc.readLine(), " ");
        int t = Integer.parseInt(line.nextToken());
        for (int i = 0; i < t; i++) {
            line = new StringTokenizer(sc.readLine(), " ");
            int n = Integer.parseInt(line.nextToken());
            List<Integer> ls = new ArrayList<>();
            for (int j = 0; j < n; j++)
                ls.add(Integer.parseInt(line.nextToken()));
            int minSize = minTeamSize(ls);
            System.out.println(minSize);
        }
    }

    private static int minTeamSize(List<Integer> ls) {
        if (ls.size() == 0)
            return 0;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int elem : ls) {
            map.putIfAbsent(elem, 0);
            map.put(elem, map.get(elem) + 1);
        }
        int ans = Integer.MAX_VALUE;
        while (map.size() > 0) {
            Integer prev = null;
            Integer prevCnt = null;
            int cur = 0;
            LinkedHashSet<Integer> keys = new LinkedHashSet<>(map.keySet());
            for (int elem : keys) {
                if (prev != null && (elem - prev != 1 || map.get(elem) < prevCnt)) {
                    break;
                }
                prevCnt = map.get(elem);
                if (map.get(elem) > 1)
                    map.put(elem, map.get(elem) - 1);
                else map.remove(elem);
                cur++;
                prev = elem;

            }
            ans = Math.min(ans, cur);
            if (ans == 1)
                break;
        }
        return ans;
    }
}
