package hckrnk;

import java.util.HashSet;
import java.util.Set;

public class DistinctPals {
    public static int palindrome(String s) {
        Set<String> ans = new HashSet<>();
        for (double st = 0.0; st < s.length(); st += 0.5) {
            int i = (int) Math.floor(st);
            int j = (int) Math.ceil(st);
            while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
                ans.add(s.substring(i, j + 1));
                i--;
                j++;
            }
        }
        return ans.size();
    }
}
