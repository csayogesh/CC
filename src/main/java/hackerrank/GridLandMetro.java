package hackerrank;

import java.util.*;

/**
 * Created by yogesh.bh on 14/04/18.
 */
public class GridLandMetro {
    static long gridlandMetro(int n, int m, int k, int[][] track) {
        Map<Integer, List<int[]>> map = new TreeMap<>();
        for (int[] arr : track) {
            map.putIfAbsent(arr[0], new ArrayList<>());
            map.get(arr[0]).add(new int[]{arr[1], arr[2]});
        }
        for (Map.Entry<Integer, List<int[]>> entry : map.entrySet()) {
            entry.setValue(mergeIntervals(entry.getValue()));
        }
        long ans = 0;
        int lastProcessedRow = 0;
        for (Map.Entry<Integer, List<int[]>> entry : map.entrySet()) {
            ans += (long) (entry.getKey() - lastProcessedRow - 1) * m;
            ans += m;
            for (int[] ar : entry.getValue())
                ans -= (ar[1] - ar[0] + 1);
            lastProcessedRow = entry.getKey();
        }
        ans += (long) (n - lastProcessedRow) * m;
        return ans;
    }

    private static List<int[]> mergeIntervals(List<int[]> value) {
        value.sort(((o1, o2) -> Integer.compare(o1[0], o2[0])));
        Stack<int[]> stack = new Stack<>();
        for (int[] elem : value) {
            if (stack.size() > 0) {
                int[] top = stack.peek();
                if (top[1] >= elem[0])
                    top[1] = Math.max(elem[1], top[1]);
                else stack.push(new int[]{elem[0], elem[1]});
            } else stack.push(new int[]{elem[0], elem[1]});
        }
        return new ArrayList<>(stack);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int[][] track = new int[k][3];
        for (int track_i = 0; track_i < k; track_i++) {
            for (int track_j = 0; track_j < 3; track_j++) {
                track[track_i][track_j] = in.nextInt();
            }
        }
        long result = gridlandMetro(n, m, k, track);
        System.out.println(result);
        in.close();
    }
}
