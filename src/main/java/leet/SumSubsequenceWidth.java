package leet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class SumSubsequenceWidth {
    public int sumSubseqWidths(int[] A) {
        Arrays.sort(A);
        int mod = (int) (Math.pow(10, 9) + 7);
        int ans = 0;
        for (int i = 0; i < A.length - 1; i++) {
            for (int j = i + 1; j < A.length; j++) {
                int d = j - i;
                int cur = A[j] - A[i];
                cur = (cur * d) % mod;
                ans = (ans + cur) % mod;
            }
        }
        return ans;
    }

    public static long teamFormation(List<Integer> score, int team, int m) {
        // Write your code here
        long ans = 0;
        int cnt = 0;
        TreeMap<Integer, TreeSet<Integer>> map1 = new TreeMap(), map2 = new TreeMap();
        int i = 0, j = score.size() - 1;
        for (; i < m && i <= j; i++) {
            map1.putIfAbsent(score.get(i), new TreeSet<>());
            map1.get(score.get(i)).add(i);
        }
        for (; j > i && j > score.size() - m - 1; j--) {
            map2.putIfAbsent(score.get(j), new TreeSet<>());
            map2.get(score.get(j)).add(j);
        }
        while (cnt < team) {
            Integer max1 = map1.size()==0?null:map1.lastKey();
            Integer max2 = map2.size()==0?null:map2.lastKey();
            if(max1==null&&max2==null)break;
            if(max2==null) {
                ans += max1;
                TreeSet<Integer> t = map1.lastEntry().getValue();
                t.pollFirst();
                if (t.size() == 0)
                    map1.pollLastEntry();
                if (i <= j) {
                    int s = score.get(i);
                    map1.putIfAbsent(s, new TreeSet<>());
                    map1.get(s).add(i);
                    i++;
                }
                cnt++;
                continue;
            }
            if(max1==null) {
                ans += max2;
                TreeSet<Integer> t = map2.lastEntry().getValue();
                t.pollFirst();
                if (t.size() == 0)
                    map2.pollLastEntry();
                if (i <= j) {
                    int s = score.get(j);
                    map2.putIfAbsent(s, new TreeSet<>());
                    map2.get(s).add(j);
                    j--;
                }
                cnt++;
                continue;
            }
            if (!Objects.equals(max1, max2)) {
                ans += Math.max(max1, max2);
                if (max1 > max2) {
                    TreeSet<Integer> t = map1.lastEntry().getValue();
                    t.pollFirst();
                    if (t.size() == 0)
                        map1.pollLastEntry();
                    if (i <= j) {
                        int s = score.get(i);
                        map1.putIfAbsent(s, new TreeSet<>());
                        map1.get(s).add(i);
                        i++;
                    }
                } else {
                    TreeSet<Integer> t = map2.lastEntry().getValue();
                    t.pollFirst();
                    if (t.size() == 0)
                        map2.pollLastEntry();
                    if (i <= j) {
                        int s = score.get(j);
                        map2.putIfAbsent(s, new TreeSet<>());
                        map2.get(s).add(j);
                        j--;
                    }
                }
            } else {
                ans += Math.max(max1, max2);
                TreeSet<Integer> t = map1.lastEntry().getValue();
                t.pollFirst();
                if (t.size() == 0)
                    map1.pollLastEntry();
                if (i <= j) {
                    int s = score.get(i);
                    map1.putIfAbsent(s, new TreeSet<>());
                    map1.get(s).add(i);
                    i++;
                }
            }
            cnt++;
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int scoreCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> score = IntStream.range(0, scoreCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int team = Integer.parseInt(bufferedReader.readLine().trim());

        int m = Integer.parseInt(bufferedReader.readLine().trim());

        long result = teamFormation(score, team, m);
        System.out.println(result);

        bufferedReader.close();
    }
}
