package ibit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DistinctNumbersInWindow {
    public ArrayList<Integer> dNums(ArrayList<Integer> A, int B) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < B; i++) {
            map.putIfAbsent(A.get(i), 0);
            map.put(A.get(i), map.get(A.get(i)) + 1);
        }
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(map.size());
        for (int i = B; i < A.size(); i++) {
            int cur = A.get(i);
            int prev = A.get(i - B);
            map.putIfAbsent(cur, 0);
            map.put(cur, map.get(cur) + 1);
            int prevCnt = map.get(prev);
            if (prevCnt == 1)
                map.remove(prev);
            else map.put(prev, prevCnt - 1);
            ans.add(map.size());
        }
        return ans;
    }
}
