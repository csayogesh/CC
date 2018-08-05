package leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StickersToSpell {
    public int minStickers(String[] stickers, String target) {
        Map<List<Integer>, Integer> min = new HashMap<>();
        List<Integer> t = convert(target);
        List<List<Integer>> ls = new ArrayList<>();
        for (String stick : stickers)
            ls.add(convert(stick));
        int ans = minStickers(t, ls, min);
        if (ans == Integer.MAX_VALUE) return -1;
        return ans;
    }

    private int minStickers(List<Integer> target, List<List<Integer>> ls, Map<List<Integer>, Integer> min) {
        if (zeros(target))
            return 0;
        if (min.containsKey(target))
            return min.get(target);
        int ans = Integer.MAX_VALUE;
        for (List<Integer> word : ls) {
            List<Integer> newT = minus(target, word);
            if (!newT.equals(target)) {
                int imm = minStickers(newT, ls, min);
                if (imm != Integer.MAX_VALUE)
                    ans = Math.min(imm + 1, ans);
            }
        }
        min.put(target, ans);
        return ans;
    }

    private List<Integer> minus(List<Integer> target, List<Integer> word) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < 26; i++)
            res.add(Math.max(target.get(i) - word.get(i), 0));
        return res;
    }

    private boolean zeros(List<Integer> target) {
        for (int i = 0; i < 26; i++)
            if (target.get(i) != 0)
                return false;
        return true;
    }

    private List<Integer> convert(String target) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < 26; i++)
            res.add(0);
        for (int i = 0; i < target.length(); i++) {
            char ch = target.charAt(i);
            int index = ch - 'a';
            res.set(index, res.get(index) + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        StickersToSpell stickersToSpell = new StickersToSpell();
        String[] stickers = {"gone", "dont", "bell", "simple", "colony", "mine", "carry", "sleep", "village", "ready", "ground", "sell", "use", "lead", "doctor", "stretch", "less", "except", "long", "why", "indicate", "live", "animal", "blow", "inch", "got", "include", "hope", "real", "then", "string", "degree", "syllable", "blue", "stop", "job", "key", "class", "he", "valley", "did", "country", "space", "heat", "collect", "truck", "mother", "problem", "toward", "my"};
        String target = "bringmethod";
        System.out.println(stickersToSpell.minStickers(stickers,target));
    }
}
