package hckrnk;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by yogesh.bh on 17/06/18.
 */
public class PoisonousPlants {

    static int poisonousPlants(int[] p) {
        LinkedList<LinkedList<Integer>> staks = new LinkedList<>();
        for (int i = 0; i < p.length; i++) {
            if (i == 0) {
                staks.add(new LinkedList<>());
                staks.getLast().add(p[i]);
            } else if (staks.getLast().getLast() >= p[i])
                staks.getLast().add(p[i]);
            else {
                staks.add(new LinkedList<>());
                staks.getLast().add(p[i]);
            }
        }
        int ans = 0;
        while (staks.size() > 1) {
            LinkedList<Integer> prev = null;
            LinkedList<Integer> cur = null;
            LinkedList<LinkedList<Integer>> newStack = new LinkedList<>();
            for (LinkedList<Integer> ls : staks) {
                cur = ls;
                if (prev != null) {
                    cur.removeFirst();
                    if (cur.size() > 0 && prev.size() > 0 && prev.getLast() >= cur.getFirst())
                        prev.addAll(cur);
                    else if (cur.size() > 0) {
                        newStack.add(cur);
                        prev = cur;
                    }
                }
                if (prev == null && cur.size() > 0) {
                    prev = cur;
                    newStack.add(cur);
                }
            }
            staks = newStack;
            ans++;
        }
        return ans;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int n = scanner.nextInt();

        int[] p = new int[n];


        for (int i = 0; i < n; i++) {
            p[i] = scanner.nextInt();
        }

        int result = poisonousPlants(p);

        System.out.println(result);
        scanner.close();
    }
}
