package hackerrank;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by yogesh.bh on 28/04/18.
 */
public class RansomNote {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        Map<String, Integer> mag = new HashMap<>();
        for (int magazine_i = 0; magazine_i < m; magazine_i++) {
            String word = in.next();
            mag.put(word, mag.getOrDefault(word, 0) + 1);
        }

        Map<String, Integer> ran = new HashMap<>();
        for (int ransom_i = 0; ransom_i < n; ransom_i++) {
            String word = in.next();
            ran.put(word, ran.getOrDefault(word, 0) + 1);
        }

        boolean res = true;
        for (Map.Entry<String, Integer> e : ran.entrySet())
            if (e.getValue() > mag.getOrDefault(e.getKey(), 0)) {
                res = false;
                break;
            }
        if (res)
            System.out.println("Yes");
        else System.out.println("No");
    }
}
