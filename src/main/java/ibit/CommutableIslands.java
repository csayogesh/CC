package ibit;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class CommutableIslands {
    public int solve(int A, ArrayList<ArrayList<Integer>> B) {
        List<List<Integer>> getSelectedEdges = mst(B, A);
        int ans = 0;
        for (List<Integer> ls : getSelectedEdges)
            ans += ls.get(2);
        return ans;
    }

    private List<List<Integer>> mst(ArrayList<ArrayList<Integer>> b, int a) {
        PriorityQueue<List<Integer>> queue =
                new PriorityQueue<>((o1, o2) -> Integer.compare(o1.get(2), o2.get(2)));
        for (List<Integer> ls : b) {
            queue.add(ls);
        }
        List<List<Integer>> ans = new ArrayList<>();
        int parents[] = new int[a + 1];
        for (int i = 1; i <= a; i++)
            parents[i] = i;
        int heights[] = new int[a + 1];
        while (queue.size() > 0) {
            List<Integer> min = queue.poll();
            int src = min.get(0);
            int dest = min.get(1);
            if (find(src, parents) != find(dest, parents)) {
                ans.add(min);
                union(src, dest, parents, heights);
            }
        }
        return ans;
    }

    private void union(int src, int dest, int[] parents, int[] heights) {
        int par1 = find(src, parents);
        int par2 = find(dest, parents);
        int h1 = heights[par1];
        int h2 = heights[par2];
        if (h1 < h2) {
            parents[par1] = par2;
        } else if (h2 < h1) {
            parents[par2] = par1;
        } else {
            parents[par2] = par1;
            heights[par2]++;
        }
    }

    private int find(int src, int[] parents) {
        if (parents[src] != src)
            parents[src] = find(parents[src], parents);
        return parents[src];
    }
}
