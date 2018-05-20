package hackerrank;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by yogesh.bh on 20/05/18.
 */
public class EqualStack {
    static class Stack {
        LinkedList<Integer> elem = new LinkedList<>();
        LinkedList<Long> sum = new LinkedList<>();

        public Stack() {
            elem.addLast(0);
            sum.addLast(0l);
        }

        public void push(int val) {
            if (elem.size() == 0)
                sum.addLast((long) val);
            else
                sum.addLast(val + sum.getLast());
            elem.addLast(val);
        }

        public long getHeight() {
            return sum.getLast();
        }

        public int pop() {
            sum.removeLast();
            return elem.removeLast();
        }
    }

    static long equalStacks(int[] h1, int[] h2, int[] h3) {
        Stack st1 = new Stack();
        Stack st2 = new Stack();
        Stack st3 = new Stack();
        for (int i = h1.length - 1; i >= 0; i--)
            st1.push(h1[i]);
        for (int i = h2.length - 1; i >= 0; i--)
            st2.push(h2[i]);
        for (int i = h3.length - 1; i >= 0; i--)
            st3.push(h3[i]);
        while (st1.getHeight() != st2.getHeight() || st1.getHeight() != st3.getHeight()) {
            Stack max = null;
            if (st1.getHeight() > st2.getHeight())
                max = st1;
            else max = st2;
            if (st3.getHeight() > max.getHeight())
                max = st3;
            max.pop();
        }
        return st1.getHeight();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        String[] n1N2N3 = scanner.nextLine().split(" ");

        int n1 = Integer.parseInt(n1N2N3[0].trim());

        int n2 = Integer.parseInt(n1N2N3[1].trim());

        int n3 = Integer.parseInt(n1N2N3[2].trim());

        int[] h1 = new int[n1];

        String[] h1Items = scanner.nextLine().split(" ");

        for (int h1Itr = 0; h1Itr < n1; h1Itr++) {
            int h1Item = Integer.parseInt(h1Items[h1Itr].trim());
            h1[h1Itr] = h1Item;
        }

        int[] h2 = new int[n2];

        String[] h2Items = scanner.nextLine().split(" ");

        for (int h2Itr = 0; h2Itr < n2; h2Itr++) {
            int h2Item = Integer.parseInt(h2Items[h2Itr].trim());
            h2[h2Itr] = h2Item;
        }

        int[] h3 = new int[n3];

        String[] h3Items = scanner.nextLine().split(" ");

        for (int h3Itr = 0; h3Itr < n3; h3Itr++) {
            int h3Item = Integer.parseInt(h3Items[h3Itr].trim());
            h3[h3Itr] = h3Item;
        }

        long result = equalStacks(h1, h2, h3);

    }

}
