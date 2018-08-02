package geek;

public class InterleavedStrings {
    public boolean isInterLeave(String a, String b, String c) {
        return isPossible(a, b, c, 0, 0, 0);
    }

    public boolean isPossible(String a, String b, String c, int i,
                              int j, int k) {

        if (i == a.length() && j == b.length() && k == c.length())
            return true;
        if (k >= c.length()) return false;

        char target = c.charAt(k);
        boolean res = false;
        if (i < a.length() && a.charAt(i) == target)
            res = res || isPossible(a, b, c, i + 1, j, k + 1);
        if (res) return res;
        if (j < b.length() && b.charAt(j) == target)
            res = res || isPossible(a, b, c, i, j + 1, k + 1);
        return res;
    }
}
