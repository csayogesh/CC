package tests;

import java.util.regex.Pattern;

public class ConsequetiveNumbers {
    public static void main(String[] args) {
        int[][] input = {
                {0, 0},
                {0, 1},
                {1, 0},
                {2, 0},
                {1, 1},
                {2, 1},
                {10, 9},
                {50, 50},
                {1, 4},
                {2, 4},
                {98, 99},
                {200, 200},
        };
        for (int[] ar : input) {
            String str = generate(ar[0], ar[1]);
            System.out.print(verify(str, ar[0], ar[1]) + " : ");
            System.out.print(ar[0] + " " + ar[1] + " => ");
            System.out.print(str);
            System.out.println();
            System.out.println();
        }
    }

    private static Pattern p1 = Pattern.compile("(aaa+|bbb+)");

    private static String verify(String str, int as, int bs) {
        String right = "Right";
        if (str.length() != (as + bs))
            return "String length mismatch";
        if (p1.matcher(str).find())
            return "Continuous as, bs";
        int acnt = 0, bcnt = 0;
        for (int i = 0; i < str.length(); i++)
            if (str.charAt(i) == 'a')
                acnt++;
            else if (str.charAt(i) == 'b')
                bcnt++;
        if (as != acnt || bs != bcnt)
            return "a , b counts mismatch";
        return right;
    }

    private static String generate(int as, int bs) {
        int[] cnts = {as, bs};
        char[] chs = {'a', 'b'};
        int noOfSlots = Math.min(as, bs) + 1;
        if (bs > as) {
            cnts = new int[]{bs, as};
            chs = new char[]{'b', 'a'};
        }
        StringBuilder sb = new StringBuilder();
        while (cnts[0] > 0 || cnts[1] > 0) {
            for (int i = 0; i < 2; i++) {
                if (cnts[i] > 0) {
                    sb.append(chs[i]);
                    cnts[i] -= 1;
                }
                if (cnts[i] > noOfSlots && cnts[i] > 0) {
                    sb.append(chs[i]);
                    cnts[i] -= 1;
                }
            }
            noOfSlots--;
        }
        return sb.toString();
    }
}
