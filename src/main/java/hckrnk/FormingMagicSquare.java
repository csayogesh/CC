package hckrnk;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by yogesh.bh on 01/07/18.
 */
public class FormingMagicSquare {
    static int formingMagicSquare(int[][] s, List<int[][]> ls) {
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < ls.size(); i++) {
            int[][] sq = ls.get(i);
            int tmp = 0;
            for (int j = 0; j < sq.length; j++)
                for (int k = 0; k < sq.length; k++)
                    tmp += Math.abs(sq[j][k] - s[j][k]);
            ans = Math.min(ans, tmp);
        }
        return ans;
    }


    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        int[][] mag = new int[][]{
                {8, 3, 4},
                {1, 5, 9},
                {6, 7, 2}
        };
        List<int[][]> ls = new ArrayList<>();
        ls.add(transpose(mag));
        ls.add(mag);
        for (int i = 0; i < 3; i++) {
            mag = rotate(mag);
            ls.add(transpose(mag));
            ls.add(mag);
        }

        int[][] s = new int[3][3];

        for (int i = 0; i < 3; i++) {
            String[] sRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int sItem = Integer.parseInt(sRowItems[j]);
                s[i][j] = sItem;
            }
        }

        int result = formingMagicSquare(s, ls);

        System.out.println(result);
        scanner.close();
    }

    private static int[][] rotate(int[][] mag) {
        int[][] res = new int[mag.length][mag.length];
        for (int i = 0, j2 = 0; i < mag.length; i++, j2++)
            for (int j = 0, i2 = mag.length - 1; j < mag.length; j++, i2--)
                res[i][j] = mag[i2][j2];
        return res;
    }

    private static int[][] transpose(int[][] mag) {
        int[][] res = new int[mag.length][mag.length];
        for (int i = 0; i < mag.length; i++)
            for (int j = 0; j < mag.length; j++)
                res[i][j] = mag[i][j];
        for (int i = 0; i < mag.length; i++)
            for (int j = 0; j < i; j++) {
                int temp = res[i][j];
                res[i][j] = res[j][i];
                res[j][i] = temp;
            }
        return res;
    }
}
