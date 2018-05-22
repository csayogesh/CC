package hckrnk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SynchronousShopping {
    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer line = new StringTokenizer(sc.readLine(), " ");
        int n = Integer.parseInt(line.nextToken());
        int m = Integer.parseInt(line.nextToken());
        int k = Integer.parseInt(line.nextToken());
        int fishAtNode[] = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            line = new StringTokenizer(sc.readLine(), " ");
            int types = Integer.parseInt(line.nextToken());
            int fishes = 0;
            for (int j = 0; j < types; j++)
                fishes |= 1 << (Integer.parseInt(line.nextToken()) - 1);
            fishAtNode[i] = fishes;
        }
        Map<Integer, List<int[]>> adjList = new HashMap();
        for (int i = 0; i < m; i++) {
            line = new StringTokenizer(sc.readLine(), " ");
            int from = Integer.parseInt(line.nextToken());
            int to = Integer.parseInt(line.nextToken());
            int wt = Integer.parseInt(line.nextToken());
            adjList.putIfAbsent(from, new ArrayList());
            adjList.putIfAbsent(to, new ArrayList());
            adjList.get(from).add(new int[]{to, wt});
            adjList.get(to).add(new int[]{from, wt});
        }
        PriorityQueue<int[]> q = new PriorityQueue<>((ar1, ar2) -> Integer.compare(ar1[2], ar2[2]));
        int ar[] = new int[]{1, fishAtNode[1], 0};
        q.add(ar);
        Map<List<Integer>, Integer> distances = new HashMap();
        Set<List<Integer>> visited = new HashSet();
        while (q.size() > 0) {
            int[] min = q.remove();
            List<Integer> eNode = new ArrayList<Integer>() {{
                add(min[0]);
                add(min[1]);
            }};
            if (visited.contains(eNode)) continue;
            for (int[] adjNode : adjList.get(eNode.get(0))) {
                List<Integer> adjEnode = new ArrayList<Integer>() {{
                    add(adjNode[0]);
                    add(eNode.get(1) | fishAtNode[adjNode[0]]);
                }};
                if (!visited.contains(adjEnode)) {
                    distances.putIfAbsent(adjEnode, Integer.MAX_VALUE);
                    int viaDist = min[2] + adjNode[1];
                    if (distances.get(adjEnode) > viaDist) {
                        q.add(new int[]{adjEnode.get(0), adjEnode.get(1), viaDist});
                        distances.put(adjEnode, viaDist);
                    }
                }
            }
            visited.add(eNode);
            distances.put(eNode, min[2]);
        }
        List<Map.Entry<List<Integer>, Integer>> ls = new ArrayList();
        for (Map.Entry<List<Integer>, Integer> entry : distances.entrySet())
            if (entry.getKey().get(0) == n)
                ls.add(entry);
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < ls.size(); i++)
            for (int j = i; j < ls.size(); j++)
                if ((ls.get(i).getKey().get(1) | ls.get(j).getKey().get(1)) == ((1 << k) - 1))
                    ans = Math.min(ans, Math.max(ls.get(i).getValue(), ls.get(j).getValue()));

        System.out.println(ans);
    }
}