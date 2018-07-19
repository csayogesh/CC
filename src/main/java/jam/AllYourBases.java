package jam;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by yogesh.bh on 18/07/18.
 */
public class AllYourBases {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.nextLine());
        for (int i = 1; i <= t; i++)
            System.out.println("Case #" + i + ": " + getMinTime(sc.nextLine()));
    }

    private static long getMinTime(String input) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < input.length(); i++) {
            map.putIfAbsent(input.charAt(i), 0);
            map.put(input.charAt(i), map.get(input.charAt(i)) + 1);
        }
        int base = Math.max(map.size(), 2);
        Map<Character, Integer> digitsMapping = new HashMap<>();
        LinkedList<Integer> ls = new LinkedList<>();
        for (int i = 2; i < base; i++)
            ls.addLast(i);
        ls.addFirst(0);
        ls.addFirst(1);
        long power = (long) Math.pow(base, input.length() - 1);
        long ans = 0;
        for (int i = 0; i < input.length(); i++) {
            if (!digitsMapping.containsKey(input.charAt(i)))
                digitsMapping.putIfAbsent(input.charAt(i), ls.removeFirst());
            int digit = digitsMapping.get(input.charAt(i));
            ans += digit * power;
            power /= base;
        }
        return ans;
    }
}
