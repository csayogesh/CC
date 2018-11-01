package hckrnk;

import java.util.*;

public class Sort {
    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : arr) {
            map.putIfAbsent(a, 0);
            map.put(a, map.get(a) + 1);
        }
        ArrayList<Map.Entry<Integer, Integer>> ls = new ArrayList<>(map.entrySet());
        Collections.sort(ls, (a, b) -> {
            int c = Integer.compare(a.getValue(), b.getValue());
            if (c != 0)
                return c;
            c = Integer.compare(a.getKey(), b.getKey());
            return c;
        });
        for (Map.Entry<Integer, Integer> a : ls)
            for (int i = 0; i < a.getValue(); i++)
                System.out.println(a.getKey());
    }
}
