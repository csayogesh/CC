import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by yogesh.bh on 05/11/17 in CC.
 */
public class CorrectChargingTheBatteries {
    public static int ConvertToInt(String s) {
        int y = 0;
        for (char c : s.toCharArray()) {
            y = y * 10 + (c - '0');
        }
        return y;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int[] line1 = new int[n + 1]; // y = 0
        int[] line2 = new int[n + 1]; // x = n
        int[] line3 = new int[n + 1]; // y = n
        int[] line4 = new int[n + 1]; // x = 0
        Map<Integer, Map<Integer, Integer>> points = new HashMap<>();
        for (int a0 = 0; a0 < m; a0++) {
            int x = in.nextInt();
            int y = in.nextInt();
            if (y == 0)
                line1[x]++;
            else if (x == n)
                line2[y]++;
            else if (y == n)
                line3[x]++;
            else
                line4[y]++;
            //grid[x, y]++;
        }
        int[] dist = new int[m];
        int prevx = -1;
        int prevy = -1;
        int firstx = -1;
        int firsty = -1;
        int index = 0;
        for (int i = 0; i < n; i++) {
            int x = i;
            int y = 0;
            while (line1[i] > 0) {
                //Console.WriteLine(x + " " + y);
                if (prevx != -1 && prevy != -1) {
                    dist[index] = Math.abs(x - prevx) + Math.abs(y - prevy);
                    index++;
                } else {
                    firstx = x;
                    firsty = y;
                }
                prevx = x;
                prevy = y;
                line1[i]--;
            }
        }
        for (int i = 0; i < n; i++) {
            int x = n;
            int y = i;
            while (line2[i] > 0) {
                //Console.WriteLine(x + " " + y);
                if (prevx != -1 && prevy != -1) {
                    dist[index] = Math.abs(x - prevx) + Math.abs(y - prevy);
                    index++;
                } else {
                    firstx = x;
                    firsty = y;
                }
                prevx = x;
                prevy = y;
                line2[i]--;
            }
        }
        for (int i = n; i > 0; i--) {
            int x = i;
            int y = n;
            while (line3[i] > 0) {
                //Console.WriteLine(x + " " + y);
                if (prevx != -1 && prevy != -1) {
                    dist[index] = Math.abs(x - prevx) + Math.abs(y - prevy);
                    index++;
                } else {
                    firstx = x;
                    firsty = y;
                }
                prevx = x;
                prevy = y;
                line3[i]--;
            }
        }
        for (int i = n; i > 0; i--) {
            int x = 0;
            int y = i;
            while (line4[i] > 0) {
                //Console.WriteLine(x + " " + y);
                if (prevx != -1 && prevy != -1) {
                    dist[index] = Math.abs(x - prevx) + Math.abs(y - prevy);
                    index++;
                } else {
                    firstx = x;
                    firsty = y;
                }
                prevx = x;
                prevy = y;
                line4[i]--;
            }
        }
        dist[index] = Math.abs(firstx - prevx) + Math.abs(firsty - prevy);
        //Console.WriteLine(String.Join(" ", dist));

        int min = 0;

        for (int i = 0; i < k - 1; i++) {
            min += dist[i];
        }
        int total = min;
        for (int i = 1; i < m; i++) {
            int ind = i + k - 2;
            if (ind >= m)
                ind %= m;
            total = total - dist[i - 1] + dist[ind];
            min = Math.min(min, total);
        }

        /*for (int i = 0; i < m; i++){
            total = 0;
            for (int j = 0; j < k - 1; j++){
                int ind = i + j;
                if (ind >= m)
                    ind %= m;
                total += dist[ind];
                //Console.Write(dist[ind] + " ");
            }
            //Console.WriteLine();
            //Console.WriteLine(total);
            min = Math.Min(min, total);
        }*/
        System.out.println(min);
    }
}
