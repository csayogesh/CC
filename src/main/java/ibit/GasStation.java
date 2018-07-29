package ibit;

import java.util.List;

public class GasStation {
    public int canCompleteCircuit(final List<Integer> gas, final List<Integer> cost) {
        int q = -1, cur = 0, cnt = 0;
        for (int i = 0; q < gas.size(); i = (i + 1) % gas.size()) {
            if (q == -1) q++;
            cur += gas.get(i) - cost.get(i);
            cnt++;
            while (q < gas.size() && cur < 0) {
                cur += cost.get(q) - gas.get(q);
                q++;
                cnt--;
            }
            if (cnt == gas.size()) break;
        }
        if (q == gas.size()) return -1;
        return q;
    }
}
