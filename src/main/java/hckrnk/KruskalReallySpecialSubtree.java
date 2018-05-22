package hckrnk;

import java.util.PriorityQueue;
import java.util.Scanner;

public class KruskalReallySpecialSubtree {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] gNodesEdges = scanner.nextLine().split(" ");
        int gNodes = Integer.parseInt(gNodesEdges[0].trim());
        int gEdges = Integer.parseInt(gNodesEdges[1].trim());

        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> {
            int res = Integer.compare(o1[2], o2[2]);
            if (res == 0)
                Integer.compare(o1[0] + o1[1], o2[0] + o2[1]);
            return res;
        });

        for (int gItr = 0; gItr < gEdges; gItr++) {
            String[] gFromToWeight = scanner.nextLine().split(" ");
            int edge[] = new int[3];
            edge[0] = Integer.parseInt(gFromToWeight[0].trim());
            edge[1] = Integer.parseInt(gFromToWeight[1].trim());
            edge[2] = Integer.parseInt(gFromToWeight[2].trim());
            queue.add(edge);
        }

        int ranks[] = new int[gNodes + 1];
        int parents[] = new int[gNodes + 1];
        for (int i = 0; i < parents.length; i++)
            parents[i] = i;
        int res = 0;
        while (queue.size() > 0) {
            int[] small = queue.remove();
            int iRoot = find(parents, small[0]);
            int jRoot = find(parents, small[1]);
            if (iRoot != jRoot) {
                res += small[2];
                union(parents, ranks, small[0], small[1]);
            }
        }
        System.out.println(res);
    }

    public static int find(int[] parents, int elem) {
        if (parents[elem] != elem)
            parents[elem] = find(parents, parents[elem]);
        return parents[elem];
    }

    public static void union(int[] parents, int[] ranks, int i, int j) {
        int iRoot = find(parents, i);
        int jRoot = find(parents, j);

        if (ranks[iRoot] < ranks[jRoot]) {
            parents[iRoot] = jRoot;
        } else if (ranks[iRoot] > ranks[jRoot]) {
            parents[jRoot] = iRoot;
        } else {
            parents[iRoot] = jRoot;
            ranks[jRoot]++;
        }
    }
}
