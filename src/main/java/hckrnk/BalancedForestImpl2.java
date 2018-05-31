package hckrnk;

import java.io.IOException;
import java.util.*;

public class BalancedForestImpl2 {

    static class Node {
        int id;
        long sum;
        int st;
        int end;
    }

    public static int time;
    public static long ans;

    static long balancedForest(int[] c, int[][] edges) {
        if (c.length < 2)
            return -1;
        Map<Integer, Set<Integer>> adjList = new LinkedHashMap<>();
        for (int[] edge : edges) {
            adjList.putIfAbsent(edge[0], new LinkedHashSet<>());
            adjList.putIfAbsent(edge[1], new LinkedHashSet<>());
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        Map<Integer, Node> nodes = new HashMap<>();
        time = 0;
        ans = Long.MAX_VALUE;
        computeSums(1, 0, c, adjList, nodes);
        Map<Long, Node> earliestNode = new HashMap<>();
        Map<Long, Node> latestNode = new HashMap<>();
        for (Map.Entry<Integer, Node> entry : nodes.entrySet()) {
            Node node = entry.getValue();
            earliestNode.putIfAbsent(node.sum, node);
            latestNode.putIfAbsent(node.sum, node);
            if (node.st < earliestNode.get(node.sum).st)
                earliestNode.put(node.sum, node);
            if (node.st > latestNode.get(node.sum).st)
                latestNode.put(node.sum, node);
        }
        long total = nodes.get(1).sum;
        if (total % 2 == 0 && earliestNode.containsKey(total / 2))
            ans = total / 2;
        for (Map.Entry<Integer, Node> entry : nodes.entrySet()) {
            Node node = entry.getValue();
            if (node.sum * 3 <= total) {
                if ((total - node.sum) % 2 == 0) {
                    long p = (total - node.sum) / 2;
                    Node candidates[] = {earliestNode.get(p), latestNode.get(p)};
                    for (Node candidate : candidates)
                        if (candidate != null && (candidate.st > node.end || candidate.end < node.st)) {
                            ans = Math.min(ans, p - node.sum);
                            continue;
                        }
                    long cp = p + node.sum;
                    candidates = new Node[]{earliestNode.get(cp), latestNode.get(cp)};
                    for (Node candidate : candidates)
                        if (candidate != null && (candidate.st < node.st && candidate.end > node.st)) {
                            ans = Math.min(ans, p - node.sum);
                            continue;
                        }
                }
            } else if (2 * node.sum < total) {
                long extraNeed = 3 * node.sum - total;
                if (earliestNode.containsKey(node.sum) && earliestNode.get(node.sum).id != latestNode.get(node.sum).id) {
                    ans = Math.min(ans, extraNeed);
                    continue;
                }
                Node candidates[] = {earliestNode.get(node.sum - extraNeed), latestNode.get(node.sum - extraNeed)};
                for (Node candidate : candidates)
                    if (candidate != null && (candidate.st > node.end || candidate.end < node.st)) {
                        ans = Math.min(ans, extraNeed);
                        continue;
                    }
                if (earliestNode.containsKey(2 * node.sum - extraNeed) || earliestNode.containsKey(2 * node.sum)) {
                    ans = Math.min(ans, extraNeed);
                    continue;
                }
            }
        }
        if (ans == Long.MAX_VALUE) return -1;
        return ans;
    }

    private static long computeSums(int cur, int parent, int[] c, Map<Integer, Set<Integer>> adjList, Map<Integer, Node> nodes) {
        time++;
        Node node = new Node();
        nodes.put(cur, node);
        node.id = cur;
        node.st = time;
        node.sum = c[cur - 1];
        for (int child : adjList.get(cur))
            if (child != parent)
                node.sum += computeSums(child, cur, c, adjList, nodes);
        time++;
        node.end = time;
        return node.sum;
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
