package common;

import java.util.Comparator;
import java.util.List;

/**
 * Created by yogesh.bh on 25/10/17 in CC.
 */
public class BasicAlgorithms {
    public static int binarySearchAtMostIndex(int[] a, int xi, int i, int j) {
        if (i == j && xi >= a[i])
            return i;
        if (i == j && xi < a[i])
            return -2;
        if (i > j)
            return -2;
        int mid = (i + j + 1) / 2;
        if (xi < a[mid])
            return binarySearchAtMostIndex(a, xi, i, mid - 1);
        else return binarySearchAtMostIndex(a, xi, mid, j);
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

    public static void makeSet(int[] parents) {
        for (int i = 0; i < parents.length; i++)
            parents[i] = i;
    }

    public static int kruskalMinSpanTree(int noOfVertices, List<Edge> edgesWithWeight) {
        int[] parents = new int[noOfVertices];
        int[] ranks = new int[noOfVertices];
        makeSet(parents);
        edgesWithWeight.sort(Comparator.comparing(e -> e.w));
        int spaningTreeWeight = 0;
        for (Edge e : edgesWithWeight) {
            int src = e.src;
            int dst = e.dst;
            if (find(parents, src) != find(parents, dst)) {
                union(parents, ranks, src, dst);
                spaningTreeWeight += e.w;
            }
        }
        return spaningTreeWeight;
    }

    public static int circularMinSumSlidingWindow(List<Integer> array, int window) {
        int sum = 0;
        for (int i = 0; i < window && i < array.size(); i++) sum += array.get(i);
        int ans = sum;
        for (int i = window; i < array.size() + window; i++) {
            sum += array.get(i % array.size());
            sum -= array.get((i % array.size() - window + array.size()) % array.size());
            ans = Math.min(sum, ans);
        }
        return ans;
    }
}
