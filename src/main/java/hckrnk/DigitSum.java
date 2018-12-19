package hckrnk;

import java.util.*;

public class DigitSum {
    public static List<Long> waysToChooseSum(long a, long b) {
        Map<Long, Set<Long>> map = new HashMap<>();
        map.put(0L, new HashSet<>());

            map.get(0L).add(0L);
        long curSum = 1;
        long max = 0;
        Set<Long> maxSum = new HashSet<>();
        while (map.size() > 0) {
            for (long i = curSum; i >= 0 && i > curSum - 10; i--) {
                long sum = getSum(i);
                map.putIfAbsent(curSum, new HashSet<>());
                if (sum == curSum && sum >= a && sum <= b)
                    map.get(curSum).add(i);
                Set<Long> prev = map.getOrDefault(i, new HashSet<>());
                Set<Long> curSet = map.get(curSum);
                for (long elem : prev) {
                    Set<Long> formNew = formNewElements(elem, curSum - i, a, b);
                    curSet.addAll(formNew);
                }
            }
            if (map.get(curSum).size() > max) {
                max = map.get(curSum).size();
                maxSum = new HashSet<>();
                maxSum.add(curSum);
            }
            if (map.get(curSum).size() == max) {
                maxSum.add(curSum);
            }

            if (map.containsKey(curSum - 10))
                map.remove(curSum - 10);
            for (long i = curSum; i >= 0 && i > curSum - 10; i--)
                if (map.containsKey(i) && map.get(i).size() == 0)
                    map.remove(i);
            System.out.println(map);
            curSum++;
        }
        List<Long> ans = new ArrayList<>();
        ans.add((long) maxSum.size());
        ans.add(max);
        return ans;
    }

    private static Set<Long> formNewElements(long elem, long digit, long a, long b) {
        if (digit == 0)
            return new HashSet<>();
        Set<Long> ans = new HashSet<>();
        long first = elem * 10 + digit;
        while (digit < elem)
            digit *= 10;
        long second = digit + elem;
        if (first >= a && first <= b)
            ans.add(first);
        if (second >= a && second <= b)
            ans.add(second);
        while (first * 10 <= b)
            if (first * 10 >= a) {
                ans.add(first * 10);
                first *= 10;
            }
        while (second * 10 <= b)
            if (second * 10 >= a) {
                ans.add(second * 10);
                second *= 10;
            }
        return ans;
    }

    private static long getSum(long num) {
        long ans = 0;
        while (num > 0) {
            ans += num % 10;
            num /= 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        List<Long> ls = waysToChooseSum(3, 12);
        System.out.println(ls);
    }
}
