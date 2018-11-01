package hckrnk;

import java.util.HashMap;
import java.util.Map;

public class MostFrequentSubString {

    public static long getMaxOccurrences(String s, int minLength, int maxLength, int maxUnique) {
        // Write your code here
        Map<Long, int[]> map = new HashMap<>();
        int n = s.length();
        for (int l = minLength; l <= maxLength; l++) {
            for (int i = 0; i < s.length(); i++) {
                int[] ar = new int[26];
                for (int j = i; j < s.length() && j < (i + l); j++) {
                    ar[s.charAt(j) - 'a']++;
                }
                if (i + l - 1 < s.length()) {
                    long key = i * s.length() + i + l - 1;
                    map.put(key,ar);
                }
            }
        }
        Map<String, Long> cnts = new HashMap<>();
        long ans = 0;
        for (Map.Entry<Long, int[]> entry : map.entrySet()) {
            int cnt = 0;
            for (int x : entry.getValue())
                if (x > 0)
                    cnt++;
            long x = entry.getKey() / s.length();
            long y = entry.getKey() % s.length();
            if (cnt < maxUnique ) {
                String sub = s.substring((int)x, (int)y + 1);
                cnts.putIfAbsent(sub, 0L);
                cnts.put(sub, cnts.get(sub) + 1);
                ans = Math.max(ans, cnts.get(sub));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        long ans = getMaxOccurrences("ababab", 2, 3, 4);
        System.out.println(ans);
    }
}
