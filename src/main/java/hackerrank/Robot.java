package hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by yogesh.bh on 07/04/18.
 */
public class Robot {
    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer line = new StringTokenizer(sc.readLine(), " ");
        int n = Integer.parseInt(line.nextToken());
        for (int i = 0; i < n; i++) {
            line = new StringTokenizer(sc.readLine(), " ");
            int w = Integer.parseInt(line.nextToken());
            String str = line.nextToken();
            System.out.print("Case #");
            System.out.print(i + 1);
            System.out.print(": ");
            long ans = cal(w, str);
            if (ans >= 0)
                System.out.println(ans);
            else System.out.println("IMPOSSIBLE");
        }
    }

    private static long cal(int w, String str) {
        long ans = 0;
        long cur[] = new long[str.length()];
        long s = 1;
        long cShoot = 0;
        for (int i = 0; i < str.length(); i++)
            if (str.charAt(i) == 'C') {
                cur[i] = s * 2;
                s = cur[i];
            } else {
                cur[i] = s;
                cShoot += s;
            }
        if (cShoot < w)
            return 0;
        s = 0;
        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) == 'C' && cShoot > w) {
                long dec = Math.min(s, (2 * cShoot - 2 * w) / cur[i]);
                cShoot -= dec * cur[i] / 2;
                ans += dec;
            }
            if (str.charAt(i) == 'S')
                s++;
        }
        if (cShoot <= w)
            return ans;
        else return -1;
    }
}
