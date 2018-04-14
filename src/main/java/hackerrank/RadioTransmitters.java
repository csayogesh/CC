package hackerrank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Created by yogesh.bh on 13/04/18.
 */
public class RadioTransmitters {
    static int bsCeil(List<Integer> ls, int elem, int i, int j) {
        if (i == j)
            if (ls.get(i) > elem)
                return i;
            else return -2;
        if (i > j)
            return -2;
        int mid = (i + j) / 2;
        if (ls.get(mid) > elem)
            return bsCeil(ls, elem, i, mid);
        return bsCeil(ls, elem, mid + 1, j);
    }

    static int hackerlandRadioTransmitters(int[] x, int k) {
        List<Integer> ls = new ArrayList<>();
        for (int a : x) ls.add(a);
        Collections.sort(ls);
        int ans = 0;
        int inital = 0;
        int start = 0;
        while ((start = bsCeil(ls, inital, start, ls.size() - 1)) >= 0) {
            ans += 1;
            int select = bsFloor(ls, ls.get(start) + k, start, ls.size() - 1);
            start = select;
            inital = ls.get(select) + k;
        }
        return ans;
    }

    private static int bsFloor(List<Integer> ls, int elem, int i, int j) {
        if (i == j)
            if (ls.get(i) > elem)
                return -2;
            else return i;
        if (i > j)
            return -2;
        int mid = (1 + i + j) / 2;
        if (ls.get(mid) > elem)
            return bsFloor(ls, elem, i, mid - 1);
        return bsFloor(ls, elem, mid, j);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] x = new int[n];
        for (int x_i = 0; x_i < n; x_i++) {
            x[x_i] = in.nextInt();
        }
        int result = hackerlandRadioTransmitters(x, k);
        System.out.println(result);
        in.close();
    }
}
