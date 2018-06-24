package hckrnk.cmpt;

import java.io.IOException;
import java.util.*;

/**
 * Created by yogesh.bh on 24/06/18.
 */
public class TimeSavingAffair {
    // Complete the leastTimeToInterview function below.
    static long leastTimeToInterview(int n, int k, int m) {
        // Return the least amount of time needed to reach the interview location in seconds.
        Map<Integer, Map<Integer, Integer>> adjList = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int wt = scanner.nextInt();
            adjList.putIfAbsent(x, new HashMap<>());
            adjList.putIfAbsent(y, new HashMap<>());
            adjList.get(x).put(y, wt);
            adjList.get(y).put(x, wt);
        }
        PriorityQueue<long[]> q = new PriorityQueue<>((ar1, ar2) -> Long.compare(ar1[1], ar2[1]));
        long ar[] = new long[]{1, 0};
        q.add(ar);
        Map<Integer, Long> distances = new HashMap();
        Set<Integer> visited = new HashSet();
        while (q.size() > 0) {
            long[] min = q.remove();
            if (visited.contains((int) min[0])) continue;
            for (Map.Entry<Integer, Integer> adjNode : adjList.get((int) min[0]).entrySet()) {
                if (!visited.contains(adjNode.getKey())) {
                    distances.putIfAbsent(adjNode.getKey(), Long.MAX_VALUE);
                    long viaDist = min[1] + adjNode.getValue();
                    long signal = viaDist / k;
                    if (signal % 2 == 1 && adjNode.getKey() != n)
                        viaDist = (signal + 1) * k;
                    if (distances.get(adjNode.getKey()) > viaDist) {
                        q.add(new long[]{adjNode.getKey(), viaDist});
                        distances.put(adjNode.getKey(), viaDist);
                    }
                }
            }
            visited.add((int) min[0]);
            distances.put((int) min[0], min[1]);
        }
        return distances.get(n);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int k = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int m = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        long result = leastTimeToInterview(n, k, m);

        System.out.println(result);
        scanner.close();
    }
}
