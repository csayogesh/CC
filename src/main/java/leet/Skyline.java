package leet;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Skyline {
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> ls = new ArrayList<>();
        for (int[] building : buildings) {
            ls.add(new int[]{building[0], building[2], 1});
            ls.add(new int[]{building[1], building[2], -1});
        }
        ls.sort((o1, o2) -> {
            int res = Integer.compare(o1[0], o2[0]);
            if (res != 0) return res;
            res = Integer.compare(o2[2],o1[2]);
            if(res!=0) return res;
            if(o1[2]==1) return Integer.compare(o2[1],o1[1]);
            return Integer.compare(o1[1],o2[1]);
        });
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int prevHeight = 0;
        List<int[]> ans = new ArrayList<>();
        for (int[] point : ls) {
            if (point[2] == 1) {
                map.putIfAbsent(point[1], 0);
                map.put(point[1], map.get(point[1]) + 1);
            } else {
                if (map.get(point[1]) == 1)
                    map.remove(point[1]);
                else map.put(point[1], map.get(point[1]) - 1);
            }
            Integer curHeight = null;
            if (map.size() > 0)
                curHeight = map.lastKey();
            else curHeight = 0;
            if (curHeight != prevHeight) {
                ans.add(new int[]{point[0], curHeight});
                prevHeight = curHeight;
            }
        }
        return ans;
    }
}
