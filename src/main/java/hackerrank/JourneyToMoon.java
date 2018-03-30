package hackerrank;

import java.util.*;

public class JourneyToMoon {

    static long journeyToMoon(int n, int[][] as) {
        // Complete this function
        int groups[] = new int[n];
        int ranks[] = new int[n];
        for (int i = 0; i < n; i++)
            groups[i] = i;
        for (int i = 0; i < as.length; i++)
            union(groups, ranks, as[i][0], as[i][1]);
        Map<Integer, List<Integer>> map = new HashMap();
        for (int i = 0; i < n; i++) {
            int root = find(groups, i);
            map.putIfAbsent(root, new ArrayList());
            map.get(root).add(i);
        }
        List<Integer> ls = new ArrayList();
        for (List<Integer> list : map.values())
            ls.add(list.size());
        long res = 0;
        long sum = ls.get(0);
        for (int j = 1; j < ls.size(); j++) {
            res += sum * ls.get(j);
            sum += ls.get(j);
        }
        return res;
    }

    public static void union(int[] parents, int[] rank, int x, int y) {
        int xRoot = find(parents, x);
        int yRoot = find(parents, y);

        if (rank[xRoot] < rank[yRoot])
            parents[xRoot] = yRoot;
        else if (rank[xRoot] > rank[yRoot])
            parents[yRoot] = xRoot;
        else {
            parents[yRoot] = xRoot;
            rank[xRoot]++;
        }
    }

    public static int find(int[] parent, int x) {
        if (parent[x] != x)
            parent[x] = find(parent, parent[x]);
        return parent[x];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int p = in.nextInt();
        int[][] astronaut = new int[p][2];
        for (int astronaut_i = 0; astronaut_i < p; astronaut_i++) {
            for (int astronaut_j = 0; astronaut_j < 2; astronaut_j++) {
                astronaut[astronaut_i][astronaut_j] = in.nextInt();
            }
        }
        long result = journeyToMoon(n, astronaut);
        System.out.println(result);
        in.close();
    }
}
