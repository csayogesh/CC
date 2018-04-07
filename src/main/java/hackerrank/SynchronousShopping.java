package hackerrank;

import java.util.*;

public class SynchronousShopping {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        int fishAtNode[] = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int types = sc.nextInt();
            int fishes = 0;
            for (int j = 0; j < types; j++)
                fishes |= 1 << (sc.nextInt() - 1);
            fishAtNode[i] = fishes;
        }
        Map<Integer, List<int[]>> adjList = new HashMap();
        for (int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int wt = sc.nextInt();
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