package leet;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * Created by yogesh.bh on 04/07/18.
 */
public class CarFleet {
    public int carFleet(int target, int[] position, int[] speed) {
        TreeSet<int[]> ls = new TreeSet<>((o1, o2) -> {
            return Integer.compare(o2[0], o1[0]);
        });
        for (int i = 0; i < position.length; i++) {
            ls.add(new int[]{position[i], speed[i]});
        }
        LinkedList<Double> stack = new LinkedList<>();
        Iterator<int[]> it = ls.descendingIterator();
        while (it.hasNext()) {
            int[] ar = it.next();
            double time = target - ar[0];
            time /= ar[1];
            if (stack.size() == 0 || stack.getFirst() > time)
                stack.addFirst(time);
            else {
                while (stack.size() > 0 && stack.getFirst() <= time)
                    stack.removeFirst();
                stack.addFirst(time);
            }
        }
        return stack.size();
    }
}
