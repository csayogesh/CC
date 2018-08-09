package leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PalindromePairs {
    class TrieNode {
        String word;
        int index;
        Map<Character, TrieNode> children = new HashMap<>();
    }

    TrieNode root = new TrieNode();

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        List<TrieNode> defaults = buildTrie(words);
        for (int i = 0; i < words.length; i++) {
            TrieNode node = root;
            int j = 0;
            for (; j < words[i].length(); j++) {
                if (node.word != null && isPalindrome(words[i], node.word.length(), words[i].length() - 1)) {
                    int finalI1 = i;
                    TrieNode finalNode = node;
                    res.add(new ArrayList<Integer>() {{
                        add(finalI1);
                        add(finalNode.index);
                    }});
                }
                if (node.children.containsKey(words[i].charAt(j)))
                    node = node.children.get(words[i].charAt(j));
                else break;
            }
            if (j == words[i].length()) {
                List<TrieNode> matchingWords = getWords(node, new ArrayList<>());

                for (TrieNode sub : matchingWords) {
                    if (sub.index != i && isPalindrome(sub.word, 0, sub.word.length() - words[i].length() - 1)) {
                        int finalI = i;
                        res.add(new ArrayList<Integer>() {{
                            add(finalI);
                            add(sub.index);
                        }});
                    }
                }
            }
        }

        return res;
    }

    private boolean isPalindrome(String word, int st, int en) {
        for (int i = st, j = en; i < j; i++, j--)
            if (word.charAt(i) != word.charAt(j))
                return false;
        return true;
    }

    private List<TrieNode> getWords(TrieNode node, List<TrieNode> nodes) {
        if (node.word != null)
            nodes.add(node);
        for (TrieNode child : node.children.values())
            getWords(child, nodes);
        return nodes;
    }

    private List<TrieNode> buildTrie(String[] words) {
        List<TrieNode> res = new ArrayList<>();
        int index = 0;
        for (String word : words) {
            TrieNode node = root;
            for (int i = word.length() - 1; i >= 0; i--) {
                node.children.putIfAbsent(word.charAt(i), new TrieNode());
                node = node.children.get(word.charAt(i));
            }
            if (word.equals("")) {
                res.clear();
                res.add(node);
            }
            node.word = word;
            node.index = index;
            index++;
        }
        return res;
    }

    public static void main(String[] args) {
        PalindromePairs pair = new PalindromePairs();
        String[] ar = {"abcd", "dcba", "lls", "s", "sssll", ""};
        System.out.println(pair.palindromePairs(ar));
    }
}