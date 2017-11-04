package common;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

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

    public static int kruskalMinSpanTree(int noOfVertices, List<Map.Entry<Map.Entry<Integer, Integer>, Integer>> edgesWithWeight) {
        int[] parents = new int[noOfVertices];
        int[] ranks = new int[noOfVertices];
        makeSet(parents);
        edgesWithWeight.sort(Comparator.comparing(Map.Entry::getValue));
        int spaningTreeWeight = 0;
        for (Map.Entry<Map.Entry<Integer, Integer>, Integer> vertice : edgesWithWeight) {
            int x = vertice.getKey().getKey();
            int y = vertice.getKey().getValue();
            if (find(parents, x) != find(parents, y)) {
                union(parents, ranks, x, y);
                spaningTreeWeight += vertice.getValue();
            }
        }
        return spaningTreeWeight;
    }

    public static void main(String[] args) {
        int[] parents = new int[20];
        int[] ranks = new int[20];

        makeSet(parents);
        System.out.println(find(parents, 0));
        System.out.println(find(parents, 1));
        union(parents, ranks, 0, 1);
        union(parents, ranks, 7, 9);
        union(parents, ranks, 0, 9);
        System.out.println(find(parents, 5));
        System.out.println(find(parents, 9));
        System.out.println(find(parents, 7));
        System.out.println(find(parents, 0));
        System.out.println(find(parents, 1));
    }
}
