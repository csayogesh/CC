package leet;

/**
 * Created by yogesh.bh on 04/07/18.
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        int i = 0;
        StringBuilder sb = new StringBuilder();
        out:
        while (true) {
            char ch;
            if (i >= strs[0].length())
                break;
            ch = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i >= strs[j].length() || ch != strs[j].charAt(i))
                    break out;
            }
            i++;
            sb.append(ch);
        }
        return sb.toString();
    }
}
