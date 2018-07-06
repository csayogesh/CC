package leet;

/**
 * Created by yogesh.bh on 06/07/18.
 */
public class PushDominoes {
    public static String pushDominoes(String dominoes) {
        int[] lefts = new int[dominoes.length()];
        int[] rights = new int[dominoes.length()];
        int nearestLeft = Integer.MAX_VALUE, nearestRight = Integer.MAX_VALUE;
        for (int i = dominoes.length() - 1; i >= 0; i--) {
            char ch = dominoes.charAt(i);
            lefts[i] = nearestLeft;
            rights[i] = nearestRight;
            if (ch == 'L')
                nearestLeft = i;
            else if (ch == 'R')
                nearestRight = i;
        }
        char[] ans = new char[dominoes.length()];
        for (int i = 0; i < dominoes.length(); i++) {
            ans[i] = '.';
        }
        for (int i = dominoes.length() - 1; i >= 0; i--) {
            char ch = dominoes.charAt(i);
            if (ch == 'R') {
                if (lefts[i] != Integer.MAX_VALUE && rights[i] > lefts[i]) {
                    int j = lefts[i];
                    if ((i + j) % 2 == 0) ans[(i + j) / 2] = 'S';
                    else ans[(i + j) / 2] = 'R';
                    ans[j] = 'L';
                    ans[i] = 'R';
                } else ans[i] = 'R';
            } else if (ch == 'L')
                ans[i] = 'L';
        }
        char cur = ans[dominoes.length() - 1];
        for (int i = dominoes.length() - 2; i >= 0; i--) {
            if (cur == 'L' && ans[i] == '.')
                ans[i] = 'L';
            else cur = ans[i];
        }
        cur = ans[0];
        for (int i = 1; i < dominoes.length(); i++) {
            if (cur == 'R' && ans[i] == '.')
                ans[i] = 'R';
            else cur = ans[i];
        }
        StringBuilder sb = new StringBuilder();
        for (char ch : ans)
            if (ch == 'S')
                sb.append('.');
            else
                sb.append(ch);
        return sb.toString();
    }

    public static void main(String[] args) {
        String ans = pushDominoes("R.R.L");
        System.out.println(ans);
    }
}
