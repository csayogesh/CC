package jam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by yogesh.bh on 29/04/18.
 */
public class Mysterious {
    public static void main(String[] args) throws IOException {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer line = new StringTokenizer(scanner.readLine(), " ");
        int t = Integer.parseInt(line.nextToken());
        for (int test = 0; test < t; test++) {
            line = new StringTokenizer(scanner.readLine(), " ");
            int N = Integer.parseInt(line.nextToken());
            List<int[]> roads = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                line = new StringTokenizer(scanner.readLine(), " ");
                int d = Integer.parseInt(line.nextToken());
                int a = Integer.parseInt(line.nextToken());
                int b = Integer.parseInt(line.nextToken());
                roads.add(new int[]{d, a, b});
            }
            int[] maxPerc = compute(roads);
            System.out.print("Case #");
            System.out.print(test + 1);
            System.out.print(": ");
            System.out.println(maxPerc);
        }
    }

    private static int[] compute(List<int[]> roads) {
        for (int[] road : roads) {
            road[1] = road[0] + road[1];
            road[2] = road[0] - road[2];
        }
        List<int[]> dp = new ArrayList<>();
        dp.add(new int[]{1, 1, 1});
        for (int i = 1; i < roads.size(); i++) {
            int[] prev = roads.get(i - 1);
            int[] cur = roads.get(i);
            int[] prevAns = dp.get(i - 1);
            int[] curAns = new int[]{1, 0, 1};
            if (prev[1] == cur[1] || prev[2] == cur[2]) {
                curAns[0] += prevAns[0];
                curAns[1] = prevAns[1];
            } else {

            }
            if (prev[1] == cur[1] && prev[2] == cur[2])
                curAns[1] = 2;
            else curAns[1] = 1;
            dp.add(curAns);
        }
        return new int[0];
    }
}
