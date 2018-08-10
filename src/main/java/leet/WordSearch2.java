package leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordSearch2 {
    class TrieNode {
        String word;
        Map<Character, TrieNode> children = new HashMap();
    }

    int[][] ds = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = buildTrie(words);
        List<String> foundWords = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                populateWords(board, i, j, root, foundWords);
            }
        }
        return foundWords;
    }

    private void populateWords(char[][] board, int i, int j, TrieNode root, List<String> foundWords) {
        if (root.word != null) {
            foundWords.add(root.word);
            root.word = null;
        }
        if (outOfBounds(board, i, j) || !root.children.containsKey(board[i][j]))
            return;
        char old = board[i][j];
        board[i][j] = '*';
        TrieNode child = root.children.get(old);
        for (int[] d : ds) {
            int x = i + d[0];
            int y = j + d[1];
            populateWords(board, x, y, child, foundWords);
        }
        board[i][j] = old;
    }

    private boolean outOfBounds(char[][] board, int i, int j) {
        return i < 0 || j < 0 || i >= board.length || j >= board[0].length;
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                node.children.putIfAbsent(word.charAt(i), new TrieNode());
                node = node.children.get(word.charAt(i));
            }
            node.word = word;
        }
        return root;
    }
}
