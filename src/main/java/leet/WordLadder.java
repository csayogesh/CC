package leet;

import java.util.*;

public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int level = 1;
        while (queue.size() > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.remove();
                if (cur.equals(endWord)) return level;
                else if (visited.contains(cur)) continue;
                else {
                    for (int j = 0; j < cur.length(); j++) {
                        char[] ar = cur.toCharArray();
                        for (char ch = 'a'; ch <= 'z'; ch++) {
                            ar[j] = ch;
                            String s = new String(ar);
                            if (!visited.contains(s) && dict.contains(s))
                                queue.add(s);
                        }
                    }
                }
                visited.add(cur);
            }
            level++;
        }
        return 0;
    }

    public static void main(String[] args) {

    }
}
