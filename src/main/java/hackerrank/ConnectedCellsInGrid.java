package hackerrank;

import java.util.Scanner;

/**
 * Created by yogesh.bh on 22/04/18.
 */
public class ConnectedCellsInGrid {
    static int connectedCell(int[][] matrix) {
        int ans = 0;
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    int max = cmp(matrix, i, j, 0);
                    ans = Math.max(ans, max);
                }
            }
        return ans;
    }

    private static int cmp(int[][] matrix, int i, int j, int cnt) {
        if (matrix[i][j] != 1)
            return 0;
        matrix[i][j] = 2;
        int x = i, y = j;
        int[][] dx = {{-1, -1}, {-1, 0}, {0, -1}, {-1, 1}};
        int count = 1;
        for (int diff[] : dx) {
            int[] m = {1, -1};
            for (int mul : m) {
                int nx = x + diff[0] * mul;
                int ny = y + diff[1] * mul;
                if (nx < 0 || ny < 0 || nx >= matrix.length || ny >= matrix[0].length) continue;
                if (matrix[nx][ny] == 1)
                    count += cmp(matrix, nx, ny, cnt + 1);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] matrix = new int[n][m];
        for (int matrix_i = 0; matrix_i < n; matrix_i++) {
            for (int matrix_j = 0; matrix_j < m; matrix_j++) {
                matrix[matrix_i][matrix_j] = in.nextInt();
            }
        }
        int result = connectedCell(matrix);
        System.out.println(result);
        in.close();
    }
}
