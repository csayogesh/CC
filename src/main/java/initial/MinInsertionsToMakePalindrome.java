package initial;

import java.util.HashMap;
import java.util.Map;


public class MinInsertionsToMakePalindrome {
    private Map<Integer, Map<Integer, Integer>> dp = new HashMap();

    public int solve(String A) {
        return recurse(A, 0, A.length() - 1);
    }

    public int recurse(String str, int i, int j) {
        if (i >= j)
            return 0;
        if (dp.containsKey(i) && dp.get(i).containsKey(j))
            return dp.get(i).get(j);
        char ch1 = str.charAt(i), ch2 = str.charAt(j);
        int ans;
        if (ch1 == ch2)
            ans = recurse(str, i + 1, j - 1);
        else {
            int temp1 = recurse(str, i + 1, j);
            int temp2 = recurse(str, i, j - 1);
            ans = Math.min(temp1, temp2) + 1;
        }
        dp.putIfAbsent(i, new HashMap());
        dp.get(i).put(j, ans);
        //System.out.println(dp);
        return ans;
    }
}
