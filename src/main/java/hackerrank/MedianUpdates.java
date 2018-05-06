package hackerrank;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by yogesh.bh on 06/05/18.
 */
public class MedianUpdates {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(scanner.nextLine().trim());
        TreeMap<Integer, Integer> left = new TreeMap<>();
        TreeMap<Integer, Integer> right = new TreeMap<>();
        int leftCnt = 0;
        int rightCnt = 0;
        for (int i = 0; i < n; i++) {
            String op = scanner.next();
            int num = scanner.nextInt();
            if (op.equals("a")) {
                if (left.size() > 0 && num <= left.lastKey()) {
                    left.putIfAbsent(num, 0);
                    left.put(num, left.get(num) + 1);
                    leftCnt++;
                } else {
                    right.putIfAbsent(num, 0);
                    right.put(num, right.get(num) + 1);
                    rightCnt++;
                }
            } else {
                if (left.size() > 0 && num <= left.lastKey() && left.containsKey(num)) {
                    left.put(num, left.get(num) - 1);
                    if (left.get(num) == 0)
                        left.remove(num);
                    leftCnt--;
                } else if (right.size() > 0 && num >= right.firstKey() && right.containsKey(num)) {
                    right.put(num, right.get(num) - 1);
                    if (right.get(num) == 0)
                        right.remove(num);
                    rightCnt--;
                } else {
                    System.out.println("Wrong!");
                    continue;
                }
            }
            while (rightCnt > leftCnt) {
                Map.Entry<Integer, Integer> mid = right.firstEntry();
                if (mid.getValue() > 1)
                    right.put(mid.getKey(), mid.getValue() - 1);
                else right.remove(mid.getKey());
                left.putIfAbsent(mid.getKey(), 0);
                left.put(mid.getKey(), left.get(mid.getKey()) + 1);
                rightCnt--;
                leftCnt++;
            }

            while ((leftCnt - rightCnt) > 1) {
                Map.Entry<Integer, Integer> mid = left.lastEntry();
                if (mid.getValue() > 1)
                    left.put(mid.getKey(), mid.getValue() - 1);
                else left.remove(mid.getKey());
                right.putIfAbsent(mid.getKey(), 0);
                right.put(mid.getKey(), right.get(mid.getKey()) + 1);
                rightCnt++;
                leftCnt--;
            }
            if (rightCnt == leftCnt && rightCnt > 0) {
                long med = left.lastKey();
                med += right.firstKey();
                if (med % 2 != 0) {
                    double tmp = med;
                    tmp /= 2.0;
                    System.out.printf("%.1f\n", tmp);
                } else System.out.println(med / 2);
            } else if (leftCnt > 0) {
                System.out.println(left.lastKey());
            } else System.out.println("Wrong!");
        }
    }
}
