package hckrnk;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by yogesh.bh on 20/05/18.
 */
public class GameOfTwoStacks {
    static int twoStacks(int x, int[] a, int[] b) {
        int ans = 0;
        int sum = 0;
        int i = 0;
        for (; i < a.length; i++) {
            if (sum + a[i] > x)
                break;
            sum += a[i];
        }
        ans = Math.max(ans, i);
        for (int j = 0; j < b.length; j++) {
            sum += b[j];
            while (i > 0 && sum > x) {
                sum -= a[--i];
            }
            if (sum > x) break;
            ans = Math.max(ans, i + j + 1);
        }
        return ans;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int g = Integer.parseInt(scanner.nextLine().trim());

        for (int gItr = 0; gItr < g; gItr++) {
            String[] nmx = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nmx[0].trim());

            int m = Integer.parseInt(nmx[1].trim());

            int x = Integer.parseInt(nmx[2].trim());

            int[] a = new int[n];

            String[] aItems = scanner.nextLine().split(" ");

            for (int aItr = 0; aItr < n; aItr++) {
                int aItem = Integer.parseInt(aItems[aItr].trim());
                a[aItr] = aItem;
            }

            int[] b = new int[m];

            String[] bItems = scanner.nextLine().split(" ");

            for (int bItr = 0; bItr < m; bItr++) {
                int bItem = Integer.parseInt(bItems[bItr].trim());
                b[bItr] = bItem;
            }

            int result = twoStacks(x, a, b);
            System.out.println(result);
        }
    }
}
