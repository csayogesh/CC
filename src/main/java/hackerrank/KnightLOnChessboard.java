package hackerrank;

import java.io.IOException;
import java.util.*;

/**
 * Created by yogesh.bh on 15/04/18.
 */
public class KnightLOnChessboard {

    static int[][] knightlOnAChessboard(int n) {
        int[][] res = new int[n - 1][];
        for (int i = 0; i < res.length; i++) {
            res[i] = new int[n - 1];
            for (int j = 0; j < res.length; j++) {
                res[i][j] = bfs(n, i + 1, j + 1);
            }
        }
        return res;
    }

    private static int bfs(int n, int a, int b) {
        Queue<List<Integer>> q = new ArrayDeque<>();
        Set<List<Integer>> visited = new HashSet<>();
        q.add(new ArrayList<Integer>() {{
            add(0);
            add(0);
        }});
        q.add(new ArrayList<>());
        int curDist = 0;
        while (q.size() > 1) {
            while (q.peek().size() > 0) {
                List<Integer> elem = q.remove();
                int x = elem.get(0);
                int y = elem.get(1);
                if (x == n - 1 && y == n - 1)
                    return curDist;
                if (!visited.contains(elem)) {
                    addAdj(q, x, y, a, b, n);
                }
                visited.add(elem);
            }
            curDist++;
            q.remove();
            q.add(new ArrayList<>());
        }
        return -1;
    }

    private static void addAdj(Queue<List<Integer>> q, int x, int y, int a, int b, int n) {
        int[] mul = {-1, 1};
        int[][] L = {{a, b}, {b, a}};
        for (int[] l : L) {
            for (int m1 : mul) {
                for (int m2 : mul) {
                    int xNew = x + m1 * l[0];
                    int yNew = y + m2 * l[1];
                    if (xNew < 0 || yNew < 0 || xNew >= n || yNew >= n)
                        continue;
                    else q.add(new ArrayList<Integer>() {{
                        add(xNew);
                        add(yNew);
                    }});
                }
            }
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(scanner.nextLine().trim());

        int[][] result = knightlOnAChessboard(n);
    }
}
