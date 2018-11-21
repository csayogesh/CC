package leet;

import java.util.PriorityQueue;

class node {
    int i, j;

    public node(int i, int j) {
        this.i = i;
        this.j = j;
    }
}

public class RisingWater {
    public int swimInWater(int[][] grid) {
        PriorityQueue<node> queue = new PriorityQueue<node>((a, b) ->
                Integer.compare(grid[a.i][a.j], grid[b.i][b.j]));
        queue.add(new node(0, 0));
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int ans = 0;
        while (queue.size() > 0) {
            node el = queue.poll();
            if (!visited[el.i][el.j]) {
                ans = Math.max(grid[el.i][el.j], ans);
                addElement(el.i + 1, el.j, grid, queue);
                addElement(el.i - 1, el.j, grid, queue);
                addElement(el.i, el.j + 1, grid, queue);
                addElement(el.i, el.j - 1, grid, queue);
            }
            visited[el.i][el.j] = true;
        }
        return ans;
    }

    private void addElement(int i, int j, int[][] grid, PriorityQueue<node> queue) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length)
            return;
        queue.add(new node(i, j));
    }
}
