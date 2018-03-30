import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by yogesh.bh on 15/10/17 in CC.
 */
public class Palindrom {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        String str = sc.nextLine();
        long n = getCount(str, k, new HashMap());
        System.out.println(n);
    }

    private static long getCount(String str, int len, Map<Integer, Map<Integer, Boolean>> dp) {
        str = str.toLowerCase();
//        Set<String> unique = new HashSet<>();
        int res = 0;
        for (int i = 0; i + len <= str.length(); i++) {
            String sub = str.substring(i, i + len);
            if (isPalindromNormal(sub))
//                unique.add(sub);
                res++;
        }
//        return unique.size();
        return res;
    }

    private static boolean isPalindromNormal(String sub) {
        for (int i = 0, j = sub.length() - 1; i <= j; i++, j--)
            if (sub.charAt(i) != sub.charAt(j))
                return false;
        return true;
    }

    private static boolean isPalindrom(String str, int i, int j, Map<Integer, Map<Integer, Boolean>> dp) {
        if (i >= j)
            return true;
        boolean temp = str.charAt(i) == str.charAt(j);
        if (i + 1 == j)
            return temp;
        if (dp.containsKey(i) && dp.get(i).containsKey(j))
            return dp.get(i).get(j);
        boolean res = isPalindrom(str, i + 1, j - 1, dp) && temp;
        dp.get(i).put(j, res);
        return res;
    }
}
