package hckrnk;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by yogesh.bh on 29/04/18.
 */
class Trie {
    public char ch;
    public int cnt = 0;
    public Map<Character, Trie> nodes = new HashMap<>();

    public Trie(char ch) {
        this.ch = ch;
    }
}

public class Contacts {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Map<Character, Trie> starts = new HashMap<>();
        for (int a0 = 0; a0 < n; a0++) {
            String op = in.next();
            String contact = in.next();
            if (op.equals("find")) {
                Trie cur = null;
                int ans = 0;
                for (int i = 0; (cur != null && i < contact.length()) || i == 0; i++) {
                    if (i == 0) cur = starts.get(contact.charAt(i));
                    else cur = cur.nodes.get(contact.charAt(i));
                    if (i == contact.length() - 1 && cur != null)
                        ans = cur.cnt;
                }
                System.out.println(ans);
            } else if (op.equals("add")) {
                Trie cur = null;
                for (int i = 0; i < contact.length(); i++) {
                    if (cur == null) {
                        starts.putIfAbsent(contact.charAt(i), new Trie(contact.charAt(i)));
                        cur = starts.get(contact.charAt(i));
                    } else {
                        cur.nodes.putIfAbsent(contact.charAt(i), new Trie(contact.charAt(i)));
                        cur = cur.nodes.get(contact.charAt(i));
                    }
                    cur.cnt++;
                }
            }
        }
    }
}
