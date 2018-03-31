package common;// Test.java

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PriorityQueue {
    public static void main(String[] args) {
        java.util.PriorityQueue<List<Integer>> queue =
                new java.util.PriorityQueue<>((o1, o2) -> Integer.compare(o1.get(0), o2.get(0)));
        List<Integer> ar = new ArrayList<Integer>() {{
            add(1);
            add(2);
        }};
        List<Integer> ar2 = new ArrayList<Integer>() {{
            add(2);
            add(3);
        }};
        List<Integer> ar3 = new ArrayList<Integer>() {{
            add(3);
            add(4);
        }};
        queue.add(ar2);
        queue.add(ar3);
        queue.add(ar);
        queue.remove(ar2);
        ar2.set(0, -1);
        queue.add(ar2);
        while (queue.size() != 0) {
            System.out.println(queue.remove());
        }
    }
}

// StringLengthComparator.java

class StringLengthComparator implements Comparator<String> {
    @Override
    public int compare(String x, String y) {
        // Assume neither string is null. Real code should
        // probably be more robust
        // You could also just return x.length() - y.length(),
        // which would be more efficient.
        if (x.length() < y.length()) {
            return -1;
        }
        if (x.length() > y.length()) {
            return 1;
        }
        return 0;
    }
}