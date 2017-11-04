import common.BasicAlgorithms;
import common.Edge;
import common.Point;

import java.util.*;
import java.util.Scanner;

/**
 * Created by yogesh.bh on 04/11/17 in CC.
 */
public class ChargingTheBatteries {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        List<Point> points = new ArrayList<>();
        int bias = Integer.MAX_VALUE;
        for (int a0 = 0; a0 < m; a0++) {
            int x = in.nextInt();
            int y = in.nextInt();
            Point p = new Point();
            p.x = x;
            p.y = y;
            points.add(p);
            int xMin = Math.min(Math.abs(x - n), x);
            int yMin = Math.min(Math.abs(y - n), y);
            int min = Math.min(xMin, yMin);
            bias = Math.min(min, bias);
        }
        in.close();
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < m - 1; i++)
            for (int j = i + 1; j < m; j++) {
                int weight = dist(points.get(i), points.get(j));
                Edge e = new Edge();
                e.src = i;
                e.dst = j;
                e.w = weight;
                edges.add(e);
            }
        int res = bias + BasicAlgorithms.kruskalMinSpanTree(m, edges);
        System.out.println(res);
    }

    private static int dist(Point v1, Point v2) {
        return Math.abs(v1.x - v2.x) + Math.abs(v1.y - v2.y);
    }
}
