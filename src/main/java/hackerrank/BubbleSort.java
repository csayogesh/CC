package hackerrank;

import java.util.*;

/**
 * Created by yogesh.bh on 01/05/18.
 */
public class BubbleSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        LinkedList<Integer> ls = new LinkedList<>();
        for (int i = 0; i < n; i++)
            ls.add(sc.nextInt());
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int swaps = 0;
        map.put(ls.removeLast(), 1);
        while (ls.size() > 0) {
            Integer last = ls.removeLast();
            NavigableMap<Integer, Integer> less = map.headMap(last, false);
            for (Map.Entry<Integer, Integer> entry : less.entrySet())
                swaps += entry.getValue();
            map.putIfAbsent(last, 0);
            map.put(last, map.get(last) + 1);
        }
        System.out.println("Array is sorted in " + swaps + " swaps.");
        System.out.println("First Element: " + map.firstKey());
        System.out.println("Last Element: " + map.lastKey());
    }
}
