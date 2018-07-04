package initial;

import java.util.TreeSet;

/**
 * Created by yogesh.bh on 01/05/18.
 */
public class TreeSetWithDuplicates {
    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<>((i1, i2) -> {
            int res = Integer.compare(i1, i2);
            if (res == 0)
                res = -1;
            return res;
        });
        set.add(4);
        set.add(5);
        set.add(1);
        set.add(3);
        set.add(1);
        set.add(1);
        set.add(2);
        set.add(1);
        set.add(1);
        set.add(2);
        set.add(2);
        System.out.println(set);
    }
}
