package hckrnk;

import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by yogesh.bh on 22/05/18.
 */
public class LargestRectangle {

    static long largestRectangle(int[] h) {
        Stack<int[]> stack = new Stack<>();
        long[] right = new long[h.length];
        stack.push(new int[]{h.length - 1, h[h.length - 1]});
        for (int i = h.length - 2; i >= 0; i--) {
            int index = i;
            while (!stack.isEmpty() && stack.peek()[1] >= h[i])
                index = stack.pop()[0];
            stack.push(new int[]{index, h[i]});
            right[i] = Math.abs(index - i) * h[i];
        }
        long[] left = new long[h.length];
        stack.clear();
        stack.push(new int[]{0, h[0]});
        for (int i = 1; i < h.length; i++) {
            int index = i;
            while (!stack.isEmpty() && stack.peek()[1] >= h[i])
                index = stack.pop()[0];
            stack.push(new int[]{index, h[i]});
            left[i] = Math.abs(index - i) * h[i];
        }
        long ans = 0;
        for (int i = 0; i < h.length; i++) {
            ans = Math.max(ans, h[i] + left[i] + right[i]);
        }
        return ans;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int n = scanner.nextInt();

        int[] h = new int[n];

        for (int i = 0; i < n; i++) {
            int hItem = scanner.nextInt();
            h[i] = hItem;
        }

        long result = largestRectangle(h);
        System.out.println(result);
        scanner.close();
    }
}
