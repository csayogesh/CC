import common.BasicAlgorithms;

import java.util.*;

/**
 * Created by yogesh.bh on 04/11/17 in CC.
 */
public class ChargingTheBatteries {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        Map<Integer, Map<Integer, Integer>> points = new HashMap<>();
        for (int a0 = 0; a0 < m; a0++) {
            int x = in.nextInt();
            int y = in.nextInt();
            points.putIfAbsent(x, new HashMap<>());
            points.get(x).putIfAbsent(y, 0);
            points.get(x).put(y, points.get(x).get(y) + 1);
        }
        int traverse[][] = {
                {1, 0, n, 0},
                {0, 1, n, n},
                {-1, 0, 0, n},
                {0, -1, 0, 0}
        };
        int x = 0, y = 0;
        int px = -1, py = -1;
        int fx = -1, fy = -1;
        boolean pointChosen = false;
        List<Integer> distances = new ArrayList<>();
        for (int i = 0; i < traverse.length; i++) {
            int dx = traverse[i][0];
            int dy = traverse[i][1];
            in:
            while (true) {
                while (points.containsKey(x) && points.get(x).containsKey(y) && points.get(x).get(y) > 0) {
                    if (!pointChosen) {
                        px = fx = x;
                        py = fy = y;
                        pointChosen = true;
                    } else {
                        distances.add(Math.abs(px - x) + Math.abs(py - y));
                        px = x;
                        py = y;
                    }
                    points.get(x).put(y, points.get(x).get(y) - 1);
                }
                if (x == traverse[i][2] && y == traverse[i][3])
                    break in;
                x += dx;
                y += dy;
            }
        }
        distances.add(Math.abs(px - fx) + Math.abs(py - fy));
        System.out.println(BasicAlgorithms.circularMinSumSlidingWindow(distances, k - 1));
        in.close();
    }
}
