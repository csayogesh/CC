package hckrnk;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class NonDivisibleSubset {

    static int nonDivisibleSubset(int k, int[] S) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int x : S) {
            int r = x % k;
            map.putIfAbsent(r, 0);
            map.put(r, map.get(r) + 1);
        }
        int ans = 0;
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < k; i++) {
            if ((i == 0 || k - i == i) && map.containsKey(i) && !visited.contains(i)) {
                ans += 1;
            } else if (!visited.contains(i)) {
                int first = map.getOrDefault(i, 0);
                int second = map.getOrDefault(k - i, 0);
                ans += Math.max(first, second);
            }
            visited.add(i);
            visited.add(k - i);
        }
        return ans;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[] S = new int[n];

        String[] SItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int SItem = Integer.parseInt(SItems[i]);
            S[i] = SItem;
        }

        int result = nonDivisibleSubset(k, S);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
