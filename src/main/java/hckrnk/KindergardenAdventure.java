package hckrnk;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by yogesh.bh on 01/06/18.
 */
public class KindergardenAdventure {
    /*
     * Complete the solve function below.
     */
    static int solve(int[] t) {
        int cnt[] = new int[t.length];
        for (int i = 0; i < t.length; i++) {
            if (t[i] == 0)
                cnt[0]++;
            else if (t[i] < t.length) {
                int size = t.length - t[i];
                int end = i - t[i];
                if (end >= 0) {
                    cnt[0]++;
                    int e1 = end + 1;
                    if (e1 < t.length)
                        cnt[e1]--;
                    if (i < t.length - 1) {
                        int s2 = i + 1;
                        cnt[s2]++;
                    }
                } else {
                    int s1 = (i + 1 + t.length) % t.length;
                    int e1 = s1 + size;
                    cnt[s1]++;
                    if (e1 < t.length)
                        cnt[e1]--;
                }
            }
        }
        int max = Integer.MIN_VALUE;
        int index = Integer.MAX_VALUE;
        int cur = 0;
        for (int i = 0; i < t.length; i++) {
            cur += cnt[i];
            if (cur > max) {
                max = cur;
                index = i + 1;
            }
        }
        return index;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int tCount = Integer.parseInt(scanner.nextLine().trim());

        int[] t = new int[tCount];

        String[] tItems = scanner.nextLine().split(" ");

        for (int tItr = 0; tItr < tCount; tItr++) {
            int tItem = Integer.parseInt(tItems[tItr].trim());
            t[tItr] = tItem;
        }

        int id = solve(t);
        System.out.println(id);
    }
}
