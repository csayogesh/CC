package hckrnk;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class RoadsInHackerland {

    static String roadsInHackerland(int n, int[][] roads) {
        List<int[]> spanTree = getSpanTree(roads, n);
        Map<Integer, List<int[]>> adjList = new HashMap<>();
        for (int[] edge : spanTree) {
            adjList.putIfAbsent(edge[0], new ArrayList<>());
            adjList.putIfAbsent(edge[1], new ArrayList<>());
            adjList.get(edge[0]).add(new int[]{edge[1], edge[2]});
            adjList.get(edge[1]).add(new int[]{edge[0], edge[2]});
        }
        int nodeCnts[] = new int[n + 1];
        generateCnts(1, -1, adjList, nodeCnts);
        BigInteger number = new BigInteger("0");
        for (int[] edge : spanTree) {
            int cnt1 = Math.min(nodeCnts[edge[0]], nodeCnts[edge[1]]);
            int weight = 1 << edge[2];
            String tmp = String.valueOf((n - cnt1) * cnt1 * weight);
            BigInteger tmpNum = new BigInteger(tmp);
            number = number.add(tmpNum);
        }
        return number.toString(2);
    }

    private static int generateCnts(int cur, int parent, Map<Integer, List<int[]>> adjList, int[] nodeCnts) {
        int cnt = 1;
        for (int[] child : adjList.get(cur)) {
            if (child[0] != cur && child[0] != parent) {
                cnt += generateCnts(child[0], cur, adjList, nodeCnts);
            }
        }
        nodeCnts[cur] = cnt;
        return cnt;
    }

    private static List<int[]> getSpanTree(int[][] roads, int n) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
        for (int[] edge : roads)
            queue.add(edge);
        int[] parents = new int[n + 1];
        int[] heights = new int[n + 1];
        for (int i = 1; i <= n; i++)
            parents[i] = i;
        List<int[]> res = new ArrayList<>();
        while (queue.size() > 0) {
            int[] smallest = queue.poll();
            if (find(smallest[0], parents) != find(smallest[1], parents)) {
                union(smallest[0], smallest[1], parents, heights);
                res.add(smallest);
            }
        }
        return res;
    }

    private static void union(int a, int b, int[] parents, int[] heights) {
        int parent1 = find(a, parents);
        int parent2 = find(b, parents);
        int h1 = heights[parent1];
        int h2 = heights[parent2];
        if (h1 < h2)
            parents[parent1] = parent2;
        else if (h2 < h1)
            parents[parent2] = parent1;
        else {
            parents[parent2] = parent1;
            heights[parent2]++;
        }
    }

    private static int find(int e, int[] parents) {
        if (parents[e] != e)
            parents[e] = find(parents[e], parents);
        return parents[e];
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0].trim());

        int m = Integer.parseInt(nm[1].trim());

        int[][] roads = new int[m][3];

        for (int roadsRowItr = 0; roadsRowItr < m; roadsRowItr++) {
            String[] roadsRowItems = scanner.nextLine().split(" ");

            for (int roadsColumnItr = 0; roadsColumnItr < 3; roadsColumnItr++) {
                int roadsItem = Integer.parseInt(roadsRowItems[roadsColumnItr].trim());
                roads[roadsRowItr][roadsColumnItr] = roadsItem;
            }
        }

        String result = roadsInHackerland(n, roads);
        System.out.println(result.length());
        System.out.println(result);
    }
}
