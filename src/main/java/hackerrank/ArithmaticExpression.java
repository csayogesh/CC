package hackerrank;

import java.util.*;

/**
 * Created by yogesh.bh on 21/04/18.
 */
public class ArithmaticExpression {

    private static List<String> ops = new ArrayList<String>() {{
        add("+");
        add("-");
        add("*");
    }};

    static Map<Integer, Map<Integer, String>> dp = new HashMap<>();

    static String arithmeticExpressions(int[] arr, int i, LinkedList<String> strings, long cur) {
        dp.putIfAbsent(i, new HashMap<>());
        if (dp.get(i).containsKey(cur))
            return dp.get(i).get(cur);
        if (i == arr.length) {
            if (cur % 101 == 0) {
                StringBuilder sb = new StringBuilder();
                for (String s : strings)
                    sb.append(s);
                String res = sb.toString();
                dp.get(i).put((int) cur, res);
            } else {
                dp.get(i).put((int) cur, "");
                return "";
            }
        }
        if (cur % 101 == 0) {
            for (int j = i; j < arr.length; j++) {
                strings.add("*");
                strings.add(String.valueOf(arr[j]));
            }
            StringBuilder sb = new StringBuilder();
            for (String s : strings)
                sb.append(s);
            String res = sb.toString();
            dp.get(i).put((int) cur, res);
            return res;
        }
        for (String op : ops) {
            strings.addLast(op);
            strings.addLast(String.valueOf(arr[i]));
            String res = arithmeticExpressions(arr, i + 1, strings, compute(cur, arr[i], op));
            if (res.length() > 0) {
                return res;
            }
            strings.removeLast();
            strings.removeLast();
        }
        dp.get(i).put((int) cur, "");
        return "";
    }

    static long compute(long x, long y, String op) {
        switch (op) {
            case "+":
                return (x % 101 + y % 101 + 101) % 101;
            case "-":
                return (x % 101 - y % 101 + 101) % 101;
            case "*":
                return (x % 101 * y % 101 + 101) % 101;
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int arr_i = 0; arr_i < n; arr_i++) {
            arr[arr_i] = in.nextInt();
        }
        String result = arithmeticExpressions(arr, 1, new LinkedList<String>() {{
            add(String.valueOf(arr[0] % 101));
        }}, arr[0]);
        System.out.println(result);
        in.close();
    }
}
