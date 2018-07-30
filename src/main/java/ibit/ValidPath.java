package ibit;

import java.util.*;

public class ValidPath {
    public String solve(int A, int B, int C, int D, ArrayList<Integer> E, ArrayList<Integer> F) {
        A++;
        B++;
        int[][] matrix = new int[A][B];
        for (int i = 0; i < A; i++)
            for (int j = 0; j < B; j++)
                if (matrix[i][j] == 0)
                    for (int k = 0; k < C; k++) {
                        int rsquare = D * D;
                        int x = i - (E.get(k));
                        int y = j - (F.get(k));
                        int dist = x * x + y * y;
                        if (dist <= rsquare) {
                            matrix[i][j] = 1;
                            break;
                        }
                    }
        boolean possible = possible(0, 0, A - 1, B - 1, matrix);
        return possible ? "YES" : "NO";
    }

    private boolean possible(int sx, int sy, int dx, int dy, int[][] matrix) {
        Set<List<Integer>> visited = new HashSet<>();
        Queue<List<Integer>> q = new ArrayDeque<>();
        if (matrix[0][0] == 0)
            q.add(new ArrayList<Integer>() {{
                add(0);
                add(0);
            }});
        q.add(new ArrayList<>());
        int[][] ds = {
                {0, 1}, {0, -1},
                {1, 0}, {-1, 0},
                {1, 1}, {-1, -1},
                {-1, 1}, {1, -1}
        };
        while (q.size() > 1) {
            while (q.peek().size() > 1) {
                List<Integer> ls = q.poll();
                int cx = ls.get(0);
                int cy = ls.get(1);
                if (cx == dx && cy == dy)
                    return true;
                for (int k = 0; k < ds.length; k++) {
                    int i = cx + ds[k][0], j = cy + ds[k][1];
                    List<Integer> adj = new ArrayList<>();
                    adj.add(i);
                    adj.add(j);
                    if (i >= 0 && i < matrix.length && j >= 0 && j < matrix[0].length && matrix[i][j] == 0 && !visited.contains(adj)) {
                        q.add(adj);
                        visited.add(adj);
                    }
                }
                visited.add(ls);
            }
            q.poll();
            q.add(new ArrayList<>());
        }
        return false;
    }

    public static void main(String[] args) {
        int[] xs = {17, 16, 12, 0, 40};
        int[] ys = {52, 61, 61, 25, 31};
        ArrayList<Integer> ls1 = new ArrayList<>();
        ArrayList<Integer> ls2 = new ArrayList<>();
        for (int x : xs)
            ls1.add(x);
        for (int y : ys)
            ls2.add(y);
        String out = new ValidPath().solve(41, 67, xs.length, 0, ls1, ls2);
        System.out.println(out);
    }
}
