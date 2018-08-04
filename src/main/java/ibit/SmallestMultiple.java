package ibit;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class SmallestMultiple {
    public String multiple(int A) {
        LinkedList<Integer> q = new LinkedList<>();
        q.add(1);
        q.add(-1);
        Set<Integer> visited = new HashSet<>();
        while (q.size() > 1) {
            while (q.getFirst() > 0) {
                int val = q.removeFirst();
                if (val > 0 && val % A == 0) return String.valueOf(val);
                if (!visited.contains(val % A)) {
                    q.add(val * 10);
                    q.add(val * 10 + 1);
                }
                visited.add(val % A);
            }
            q.removeFirst();
            q.add(-1);
        }
        return null;
    }

    public static void main(String[] args) {
        String str = new SmallestMultiple().multiple(5);
        System.out.println(str);
    }
}
