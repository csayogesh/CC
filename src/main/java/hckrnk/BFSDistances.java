package hckrnk;

import java.util.*;

/**
 * Created by yogesh.bh on 05/05/18.
 */
public class BFSDistances {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int tin = 0; tin < t; tin++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            Map<Integer, Set<Integer>> adj = new HashMap<>();
            for (int i = 0; i < m; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                adj.putIfAbsent(u, new HashSet<>());
                adj.putIfAbsent(v, new HashSet<>());
                adj.get(u).add(v);
                adj.get(v).add(u);
            }
            int st = sc.nextInt();
            int[] dist = dist(adj, n, st);
            for (int i = 1; i <= n; i++)
                if (dist[i] == -1)
                    System.out.print("-1 ");
                else if (i != st)
                    System.out.print(dist[i] * 6 + " ");
            System.out.println();
        }
    }

    private static int[] dist(Map<Integer, Set<Integer>> adj, int n, int st) {
        int dist[] = new int[n + 1];
        for (int i = 0; i < dist.length; i++)
            dist[i] = -1;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(st);
        queue.add(-1);
        int cur = 0;
        while (queue.size() > 1) {
            while (queue.peek() != -1) {
                int node = queue.remove();
                if (dist[node] == -1) {
                    dist[node] = cur;
                    for (int adjNode : adj.getOrDefault(node, new HashSet<>())) {
                        queue.add(adjNode);
                    }
                }
            }
            cur++;
            queue.remove();
            queue.add(-1);
        }
        return dist;
    }
}
