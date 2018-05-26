package hckrnk;

import java.io.IOException;
import java.util.*;

/**
 * Created by yogesh.bh on 26/05/18.
 */
public class MInimumAvgWaitingTime {
    /*
     * Complete the minimumAverage function below.
     */
    static long minimumAverage(int[][] customers) {
        TreeSet<int[]> sorted = new TreeSet<>((c1, c2) -> {
            int res = Integer.compare(c1[0], c2[0]);
            if (res == 0)
                res = -1;
            return res;
        });
        for (int[] c : customers)
            sorted.add(c);
        List<int[]> cList = new ArrayList<>();
        for (int[] c : sorted)
            cList.add(c);
        long currentTime = cList.get(0)[0];
        int i = 0;
        TreeMap<Integer, LinkedList<Integer>> map = new TreeMap<>();
        long totalWaitTime = 0;
        while (i < cList.size() || map.size() > 0) {
            while (i < cList.size() && cList.get(i)[0] <= currentTime) {
                map.putIfAbsent(cList.get(i)[1], new LinkedList<>());
                map.get(cList.get(i)[1]).add(cList.get(i)[0]);
                i++;
            }
            if (map.size() > 0) {
                Map.Entry<Integer, LinkedList<Integer>> smallestJob = map.firstEntry();
                long completionTime = currentTime + smallestJob.getKey();
                long waitTime = completionTime - smallestJob.getValue().removeFirst();
                totalWaitTime += waitTime;
                currentTime = completionTime;
                while (i < cList.size() && cList.get(i)[0] <= currentTime) {
                    map.putIfAbsent(cList.get(i)[1], new LinkedList<>());
                    map.get(cList.get(i)[1]).add(cList.get(i)[0]);
                    i++;
                }
                if (smallestJob.getValue().size() == 0)
                    map.remove(smallestJob.getKey());
            }
            if (map.size() == 0 && i < cList.size())
                currentTime = cList.get(i)[0];
        }
        return totalWaitTime / customers.length;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int n = scanner.nextInt();

        int[][] customers = new int[n][];

        for (int customersRowItr = 0; customersRowItr < n; customersRowItr++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            customers[customersRowItr] = new int[]{x, y};
        }

        long result = minimumAverage(customers);
        System.out.println(result);
        scanner.close();
    }
}
