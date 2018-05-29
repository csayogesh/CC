package hckrnk;

import java.io.IOException;
import java.util.*;

/**
 * Created by yogesh.bh on 13/05/18.
 */
public class BalancedForest {
    static long balancedForest(int[] c, int[][] edges) {
        if (c.length < 3)
            return -1;
        Map<Integer, Set<Integer>> adjList = getAdjList(edges);
        int optRoot = getOptimalRoot(adjList);
        long[] nodeSums = new long[c.length];
        int[] heights = new int[c.length];
        List<Integer> ls = new ArrayList<>();
        subNodeSums(adjList, optRoot, -1, c, nodeSums, heights, ls);
        Map<Integer, Integer> firstOccurence = new HashMap<>();
        for (int i = 0; i < ls.size(); i++)
            firstOccurence.putIfAbsent(ls.get(i), i);
        int log = (int) Math.ceil(Math.log(ls.size()) / Math.log(2));
        int parentsParse[][] = new int[ls.size()][log + 1];
        for (int i = 0; i <= log; i++) {
            for (int j = 0; j < ls.size(); j++) {
                if (i == 0)
                    parentsParse[j][i] = ls.get(j);
                else {
                    int firstHalfIntervalMaxNode = parentsParse[j][i - 1];
                    int ans = firstHalfIntervalMaxNode;
                    int nextHalfStart = j + (1 << (i - 1));
                    if (nextHalfStart < ls.size()) {
                        int secondHalfIntervalMaxNode = parentsParse[nextHalfStart][i - 1];
                        if (heights[firstHalfIntervalMaxNode - 1] > heights[secondHalfIntervalMaxNode - 1])
                            ans = firstHalfIntervalMaxNode;
                        else ans = secondHalfIntervalMaxNode;
                    }
                    parentsParse[j][i] = ans;
                }
            }
        }
        List<Integer> candidates = new ArrayList<>();
        long total = nodeSums[optRoot - 1];
        for (int i = 0; i < nodeSums.length; i++)
            candidates.add(i + 1);
        long ans = Long.MAX_VALUE;
        long oneThird = (long) Math.ceil((total * 1.0) / 3.0);
        for (int i = 0; i < candidates.size() - 1; i++)
            for (int j = i + 1; j < candidates.size(); j++) {
                int node1 = candidates.get(i);
                int node2 = candidates.get(j);
                int index1 = firstOccurence.get(node1);
                int index2 = firstOccurence.get(node2);
                int start = Math.min(index1, index2);
                int end = Math.max(index1, index2);
                int size = end - start + 1;
                int logIndex = (int) Math.floor(Math.log(size) / Math.log(2));
                int firstHalf = parentsParse[start][logIndex];
                int lca;
                int secondHalf = parentsParse[end + 1 - (1 << logIndex)][logIndex];
                if (heights[firstHalf - 1] > heights[secondHalf - 1])
                    lca = firstHalf;
                else lca = secondHalf;
                long sum1;
                long sum2;
                long sum3;
                if (lca == node1) {
                    sum1 = total - nodeSums[node1 - 1];
                    sum2 = nodeSums[node2 - 1];
                    sum3 = total - sum1 - sum2;
                } else if (lca == node2) {
                    sum1 = total - nodeSums[node2 - 1];
                    sum2 = nodeSums[node1 - 1];
                    sum3 = total - sum1 - sum2;
                } else {
                    sum1 = nodeSums[node1 - 1];
                    sum2 = nodeSums[node2 - 1];
                    sum3 = total - sum1 - sum2;
                }
                if (sum1 == sum2 && sum1 >= oneThird)
                    ans = Math.min(ans, sum1 - sum3);
                else if (sum2 == sum3 && sum2 >= oneThird)
                    ans = Math.min(ans, sum2 - sum1);
                else if (sum1 == sum3 && sum1 >= oneThird)
                    ans = Math.min(ans, sum3 - sum2);
                if (ans < 0)
                    System.out.println();
            }
        if (ans == Long.MAX_VALUE)
            return -1;
        return ans;
    }

    private static long[] subNodeSums(Map<Integer, Set<Integer>> adjList,
                                      int root, int parent, int[] c, long[] nodeCnt, int[] heights, List<Integer> ls) {
        long curSum = c[root - 1];
        long height = 0;
        ls.add(root);
        for (int child : adjList.get(root))
            if (child != root && child != parent) {
                long[] childRes = subNodeSums(adjList, child, root, c, nodeCnt, heights, ls);
                curSum += childRes[0];
                height = Math.max(height, 1 + childRes[1]);
                ls.add(root);
            }
        ls.add(root);
        nodeCnt[root - 1] = curSum;
        heights[root - 1] = (int) height;
        return new long[]{curSum, height};
    }

    private static int getOptimalRoot(Map<Integer, Set<Integer>> adjList) {
        int optRoot = -1;
        Queue<Integer> queue = new ArrayDeque<>();
        for (Map.Entry<Integer, Set<Integer>> entry : adjList.entrySet()) {
            if (entry.getValue().size() == 1)
                queue.add(entry.getKey());
        }
        Set<Integer> visited = new HashSet<>();
        queue.add(-1);
        while (queue.size() > 1) {
            while (queue.peek() != -1) {
                optRoot = queue.poll();
                visited.add(optRoot);
                for (int adjNode : adjList.get(optRoot))
                    if (!visited.contains(adjNode))
                        queue.add(adjNode);
            }
            queue.poll();
            queue.add(-1);
        }
        return optRoot;
    }

    private static Map<Integer, Set<Integer>> getAdjList(int[][] edges) {
        Map<Integer, Set<Integer>> adjList = new LinkedHashMap<>();
        for (int[] edge : edges) {
            adjList.putIfAbsent(edge[0], new LinkedHashSet<>());
            adjList.putIfAbsent(edge[1], new LinkedHashSet<>());
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        return adjList;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        int q = Integer.parseInt(scanner.nextLine().trim());

        for (int qItr = 0; qItr < q; qItr++) {
            int cCount = Integer.parseInt(scanner.nextLine().trim());

            int[] c = new int[cCount];

            String[] cItems = scanner.nextLine().split(" ");

            for (int cItr = 0; cItr < cCount; cItr++) {
                int cItem = Integer.parseInt(cItems[cItr].trim());
                c[cItr] = cItem;
            }

            int[][] edges = new int[cCount - 1][2];

            for (int edgesRowItr = 0; edgesRowItr < cCount - 1; edgesRowItr++) {
                String[] edgesRowItems = scanner.nextLine().split(" ");

                for (int edgesColumnItr = 0; edgesColumnItr < 2; edgesColumnItr++) {
                    int edgesItem = Integer.parseInt(edgesRowItems[edgesColumnItr].trim());
                    edges[edgesRowItr][edgesColumnItr] = edgesItem;
                }
            }

            long result = balancedForest(c, edges);
            System.out.println(result);
        }
    }
}
