package jam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by yogesh.bh on 29/04/18.
 */
public class RoundingError {
    public static void main(String[] args) throws IOException {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer line = new StringTokenizer(scanner.readLine(), " ");
        int t = Integer.parseInt(line.nextToken());
        for (int test = 0; test < t; test++) {
            line = new StringTokenizer(scanner.readLine(), " ");
            int N = Integer.parseInt(line.nextToken());
            int L = Integer.parseInt(line.nextToken());
            List<Integer> votes = new ArrayList<>();
            line = new StringTokenizer(scanner.readLine(), " ");
            for (int i = 0; i < L; i++)
                votes.add(Integer.parseInt(line.nextToken()));
            int maxPerc = maxPercentage(N, L, votes);
            System.out.print("Case #");
            System.out.print(test+1);
            System.out.print(": ");
            System.out.println(maxPerc);
        }
    }

    private static int maxPercentage(int n, int l, List<Integer> votes) {
        int totalVotes = 0;
        for (int v : votes) totalVotes += v;
        int ans = 0;
        int d = (1000 / n) % 10;
        Map<Integer, int[]> map = new HashMap<>();
        for (int v : votes) {
            int r = ((v * 1000) / n) % 10;
            map.putIfAbsent(r, new int[]{0, 0});
            int[] pair = map.get(r);
            pair[0]++;
            pair[1] += rounded(v, n);
        }
        int diff = n - totalVotes;
        for (int i = 4; i >= 0 && diff > 0; i--) {
            int[] ar = map.get(i);
            if (ar != null && d > 0) {
                int req = (int) Math.ceil((5.0 - i) / d);
                int select = Math.min(ar[0], diff / req);
                diff -= select * req;
                ar[1] += select;
                ar[1] += select * roundedF(req, n);
            }
        }
        if (diff > 0 && d > 0) {
            int opt = (int) Math.ceil(5.0 / d);
            int perc = (diff / opt) * rounded(opt, n);
            perc += rounded(diff % opt, n);
            ans += perc;
        } else if (diff > 0) {
            ans += rounded(diff, n);
        }
        for (Map.Entry<Integer, int[]> entry : map.entrySet())
            ans += entry.getValue()[1];
        return ans;
    }

    private static int rounded(int v, int n) {
        double perc;
        perc = v * 100;
        perc /= n;
        perc += 0.5;
        return (int) perc;
    }

    private static int roundedF(int v, int n) {
        double perc;
        perc = v * 100;
        perc /= n;
        return (int) perc;
    }
}
