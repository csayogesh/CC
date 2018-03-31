package hackerrank;

import java.io.*;
import java.util.*;

public class DijkstraAlgorithm {

    private static final BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));

    static int[] shortestReach(int n, Map<Integer, Map<Integer, Integer>> adjList, int s) {
        int dist[] = new int[n + 1];
        for (int i = 0; i < dist.length; i++) dist[i] = Integer.MAX_VALUE;
        dist[s] = 0;
        PriorityQueue<int[]> toVisit = new PriorityQueue<>((node1, node2) -> Integer.compare(node1[0], node2[0]));
        Set<Integer> selectedNodes = new HashSet();
        toVisit.add(new int[]{0, s});
        while (toVisit.size() > 0) {
            int[] minNode = toVisit.remove();
            int minDist = minNode[0];
            for (Map.Entry<Integer, Integer> adjNode : adjList.get(minNode[1]).entrySet()) {
                if (!selectedNodes.contains(adjNode.getKey())) {
                    if (dist[adjNode.getKey()] > minDist + adjNode.getValue()) {
                        toVisit.add(new int[]{minDist + adjNode.getValue(), adjNode.getKey()});
                        dist[adjNode.getKey()] = minDist + adjNode.getValue();
                    }
                }
            }
            selectedNodes.add(minNode[1]);
        }
        int ans[] = new int[n - 1];
        for (int i = 0; i < dist.length; i++)
            if (dist[i] == Integer.MAX_VALUE)
                dist[i] = -1;
        int i = 0;
        for (int j = 1; j <= n; j++)
            if (j == s) continue;
            else ans[i++] = dist[j];
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        StringTokenizer line = new StringTokenizer(scanner.readLine(), " ");
        int t = Integer.parseInt(line.nextToken());

        for (int tItr = 0; tItr < t; tItr++) {
            line = new StringTokenizer(scanner.readLine(), " ");

            int n = Integer.parseInt(line.nextToken());

            int m = Integer.parseInt(line.nextToken());

            int[][] edges = new int[m][3];

            Map<Integer, Map<Integer, Integer>> adjList = new HashMap();
            for (int edgesRowItr = 0; edgesRowItr < m; edgesRowItr++) {
                line = new StringTokenizer(scanner.readLine(), " ");
                int one = Integer.parseInt(line.nextToken());
                int two = Integer.parseInt(line.nextToken());
                int three = Integer.parseInt(line.nextToken());
                int[] edge = new int[]{one, two, three};
                adjList.putIfAbsent(edge[0], new HashMap());
                adjList.putIfAbsent(edge[1], new HashMap());
                int existingWt = adjList.get(edge[0]).getOrDefault(edge[1], Integer.MAX_VALUE);
                adjList.get(edge[0]).put(edge[1], Math.min(existingWt, edge[2]));
                adjList.get(edge[1]).put(edge[0], Math.min(existingWt, edge[2]));
            }

            line = new StringTokenizer(scanner.readLine(), " ");
            int s = Integer.parseInt(line.nextToken());

            int[] result = shortestReach(n, adjList, s);

            for (int resultItr = 0; resultItr < result.length; resultItr++) {
                bufferedWriter.write(String.valueOf(result[resultItr]));

                if (resultItr != result.length - 1) {
                    bufferedWriter.write(" ");
                }
            }

            bufferedWriter.newLine();
        }

        bufferedWriter.close();
        scanner.close();
    }
}
