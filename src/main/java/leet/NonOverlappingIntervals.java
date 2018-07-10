package leet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by yogesh.bh on 10/07/18.
 */
public class NonOverlappingIntervals {

    public class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    class Solution {

        public int eraseOverlapIntervals(Interval[] intervals) {
            TreeMap<Integer, List<Interval>> map = new TreeMap<>();
            for (Interval interval : intervals) {
                map.putIfAbsent(interval.end, new ArrayList<>());
                map.get(interval.end).add(interval);
            }
            int curTime = Integer.MIN_VALUE, selected = 0;
            for (Map.Entry<Integer, List<Interval>> entry1 : map.entrySet()) {
                for (Interval interval : entry1.getValue()) {
                    if (interval.start >= curTime) {
                        curTime = interval.end;
                        selected++;
                    }
                }
            }
            return intervals.length - selected;
        }

    }
}
