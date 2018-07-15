package proofs;

/**
 * Created by yogesh.bh on 07/07/18.
 */
public class DiagonalsExistence {
    public static void main(String[] args) {
        int[][] solution = getSolution(5, 16);
        for (int i = 0; i < solution.length; i++) {
            for (int j = 0; j < solution.length; j++) {
                System.out.printf("%4d ", solution[i][j]);
            }
            System.out.println();
        }
//        System.out.println();
    }

    private static int[][] getSolution(int n, int k) {
        int[][] res = new int[n][n];
        boolean st = putDiagonals(res, 0, 0, k);
        System.out.println(st);
        return res;
    }

    private static boolean putDiagonals(int[][] grid, int i, int j, int k) {
        if (k == 0)
            return true;
        if (i < 0 || j < 0 || i >= grid.length || j >= grid.length)
            return false;
        int[] vs = new int[]{1, -1, 0};
        for (int v : vs) {
            if (valid(grid, i, j, v)) {
                int old = grid[i][j];
                grid[i][j] = v;
                for (int x = i; x < grid.length; x++) {
                    for (int y = x == i ? j + 1 : 0; y < grid.length; y++) {
                        if (grid[x][y] == 0 && putDiagonals(grid, x, y, v != 0 ? k - 1 : k)) {

                            return true;
                        }
                    }
                }
                grid[i][j] = old;
            }
        }
        return false;
    }

    private static boolean valid(int[][] grid, int i, int j, int dt) {
        if (dt == 0)
            return true;
        int anti = dt * -1;
        if (i < grid.length - 1 && anti == grid[i + 1][j]) return false;
        if (i > 0 && anti == grid[i - 1][j]) return false;
        if (j < grid.length - 1 && anti == grid[i][j + 1]) return false;
        if (j > 0 && anti == grid[i][j - 1]) return false;
        if (i < grid.length - 1 && j < grid.length - 1 && grid[i + 1][j + 1] == 1 && dt == 1) return false;
        if (i > 0 && j > 0 && grid[i - 1][j - 1] == 1 && dt == 1) return false;
        if (j < grid.length - 1 && i > 0 && grid[i - 1][j + 1] == -1 && dt == -1) return false;
        if (j > 0 && i < grid.length - 1 && grid[i + 1][j - 1] == -1 && dt == -1) return false;
        return true;
    }
}
