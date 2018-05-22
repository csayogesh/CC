package hckrnk;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RoadsAndLibraries {

    static long roadsAndLibraries(int n, long c_lib, long c_road, int[][] cities) {
        // Complete this function
        int parents[] = new int[n + 1];
        int ranks[] = new int[n + 1];

        for (int i = 0; i < parents.length; i++)
            parents[i] = i;

        for (int[] city : cities) {
            union(parents, ranks, city);
        }

        Map<Long, Long> map = new HashMap();
        for (int i = 1; i <= n; i++) {
            long cmp = find(parents, i);
            if (!map.containsKey(cmp))
                map.put(cmp, 0l);
            map.put(cmp, map.get(cmp) + 1);
        }
        long ans = 0;
        for (Map.Entry<Long, Long> e : map.entrySet()) {
            ans += Math.min(c_lib + (e.getValue() - 1) * c_road, e.getValue() * c_lib);
        }

        return ans;
    }

    static void union(int[] parents, int[] ranks, int[] city) {
        int r1 = find(parents, city[0]);
        int r2 = find(parents, city[1]);

        if (ranks[r1] < ranks[r2]) {
            parents[r1] = r2;
        } else if (ranks[r1] > ranks[r2]) {
            parents[r2] = r1;
        } else {
            parents[r1] = r2;
            ranks[r2]++;
        }
    }

    static int find(int parents[], int node) {
        if (parents[node] != node)
            parents[node] = find(parents, parents[node]);
        return parents[node];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for (int a0 = 0; a0 < q; a0++) {
            int n = in.nextInt();
            int m = in.nextInt();
            int c_lib = in.nextInt();
            int c_road = in.nextInt();
            int[][] cities = new int[m][2];
            for (int cities_i = 0; cities_i < m; cities_i++) {
                for (int cities_j = 0; cities_j < 2; cities_j++) {
                    cities[cities_i][cities_j] = in.nextInt();
                }
            }
            long result = roadsAndLibraries(n, c_lib, c_road, cities);
            System.out.println(result);
        }
        in.close();
    }
}
