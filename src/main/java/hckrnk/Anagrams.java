package hckrnk;

import java.util.*;

/**
 * Created by yogesh.bh on 24/04/18.
 */
public class Anagrams {
    public static int numberNeeded(String first, String second) {
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        for (int i = 0; i < first.length(); i++) {
            map1.putIfAbsent(first.charAt(i), 0);
            map1.put(first.charAt(i), map1.get(first.charAt(i)) + 1);
        }
        for (int i = 0; i < second.length(); i++) {
            map2.putIfAbsent(second.charAt(i), 0);
            map2.put(second.charAt(i), map2.get(second.charAt(i)) + 1);
        }
        Set<Character> allChars = new HashSet<>();
        allChars.addAll(map1.keySet());
        allChars.addAll(map2.keySet());
        int ans = 0;
        for (char ch : allChars) {
            int cnt1 = map1.getOrDefault(ch, 0);
            int cnt2 = map2.getOrDefault(ch, 0);
            ans += Math.abs(cnt1 - cnt2);
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String a = in.next();
        String b = in.next();
        System.out.println(numberNeeded(a, b));
    }
}
