package hckrnk;

import java.util.ArrayList;
import java.util.List;

public class Route {

    public static int gcd(int a, int b) {
        if (b > a)
            return gcd(b, a);
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    public static List<Integer> connectedCities(int n, int g, List<Integer> originCities, List<Integer> destinationCities) {
        // Write your code here
        int[] group = new int[n + 1];
        int[] heights = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            group[i] = i;
            heights[i] = 0;
        }
        for (int i = g + 1; i <= n; i++) {
            for (int j = i * 2; j <= n; j += i) {
                {
                    int g1 = find(group, i);
                    int g2 = find(group, j);
                    if (g1 != g2) {
                        join(group, heights, g1, g2);
                    }
                }
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < originCities.size(); i++) {
            int src = originCities.get(i);
            int dst = destinationCities.get(i);
            if (find(group, src) == find(group, dst))
                res.add(1);
            else res.add(0);
        }
        return res;
    }

    private static void join(int[] group, int[] heights, int g1, int g2) {
        if (heights[g1] < heights[g2]) {
            group[g1] = g2;
        } else if (heights[g1] > heights[g2])
            group[g2] = g1;
        else {
            group[g1] = g2;
            heights[g2]++;
        }
    }

    private static int find(int[] group, int j) {
        if (group[j] != j)
            group[j] = find(group, group[j]);
        return group[j];
    }

    public static void main(String[] args) {
        System.out.println(gcd(13, 122));
    }
}
