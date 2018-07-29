package ibit;

import java.util.ArrayList;
import java.util.List;

public class MaxProduct {
    public int maxProduct(final List<Integer> a) {
        ArrayList<Integer> max = new ArrayList();
        ArrayList<Integer> min = new ArrayList();
        max.add(a.get(0));
        min.add(a.get(0));
        int res = a.get(0);
        for (int i = 1; i < a.size(); i++) {
            int x = a.get(i);
            min.add(Math.min(Math.min(x, x * min.get(i - 1)), x * max.get(i - 1)));
            int tmp = Math.max(Math.max(x, x * min.get(i - 1)), x * max.get(i - 1));
            if (tmp > res) res = tmp;
            max.add(tmp);
        }
        return res;
    }
}
