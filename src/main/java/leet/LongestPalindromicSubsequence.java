package leet;

import java.util.HashMap;
import java.util.Map;

public class LongestPalindromicSubsequence {
    public int longestPalindromeSubseq(String s) {
        Map<Integer, Map<Integer, Integer>> dp = new HashMap<>();
        return getMaxPalSubseq(s, 0, s.length() - 1, dp);
    }

    private int getMaxPalSubseq(String s, int i, int j, Map<Integer, Map<Integer, Integer>> dp) {
        if (i > j) return 0;
        if (i == j) return 1;
        dp.putIfAbsent(i, new HashMap<>());
        if (dp.get(i).containsKey(j))
            return dp.get(i).get(j);
        char ch1 = s.charAt(i);
        char ch2 = s.charAt(j);
        int ans = 0;
        if (ch1 == ch2)
            ans = 2 + getMaxPalSubseq(s, i + 1, j - 1, dp);
        else {
            int ans1 = getMaxPalSubseq(s, i + 1, j, dp);
            int ans2 = getMaxPalSubseq(s, i, j - 1, dp);
            ans = Math.max(ans1, ans2);
        }
        dp.get(i).put(j, ans);
        return ans;
    }

    public static void main(String[] args) {
        new LongestPalindromicSubsequence().longestPalindromeSubseq("gphyvqruxjmwhonjjrgumxjhfyupajxbjgthzdvrdqmdouuukeaxhjumkmmhdglqrrohydrmbvtuwstgkobyzjjtdtjroqpyusfsbjlusekghtfbdctvgmqzeybnwzlhdnhwzptgkzmujfldoiejmvxnorvbiubfflygrkedyirienybosqzrkbpcfidvkkafftgzwrcitqizelhfsruwmtrgaocjcyxdkovtdennrkmxwpdsxpxuarhgusizmwakrmhdwcgvfljhzcskclgrvvbrkesojyhofwqiwhiupujmkcvlywjtmbncurxxmpdskupyvvweuhbsnanzfioirecfxvmgcpwrpmbhmkdtckhvbxnsbcifhqwjjczfokovpqyjmbywtpaqcfjowxnmtirdsfeujyogbzjnjcmqyzciwjqxxgrxblvqbutqittroqadqlsdzihngpfpjovbkpeveidjpfjktavvwurqrgqdomiibfgqxwybcyovysydxyyymmiuwovnevzsjisdwgkcbsookbarezbhnwyqthcvzyodbcwjptvigcphawzxouixhbpezzirbhvomqhxkfdbokblqmrhhioyqubpyqhjrnwhjxsrodtblqxkhezubprqftrqcyrzwywqrgockioqdmzuqjkpmsyohtlcnesbgzqhkalwixfcgyeqdzhnnlzawrdgskurcxfbekbspupbduxqxjeczpmdvssikbivjhinaopbabrmvscthvoqqbkgekcgyrelxkwoawpbrcbszelnxlyikbulgmlwyffurimlfxurjsbzgddxbgqpcdsuutfiivjbyqzhprdqhahpgenjkbiukurvdwapuewrbehczrtswubthodv");
    }
}
