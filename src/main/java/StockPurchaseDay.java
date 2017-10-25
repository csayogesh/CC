import java.util.Scanner;

/**
 * Created by yogesh.bh on 25/10/17 in CC.
 */
public class StockPurchaseDay {
    static int stockPurchaseDay(int[] A, int xi) {
        // Complete this function
        return CommonAlgorithms.binarySearchAtMostIndex(A, xi, 0, A.length - 1) + 1;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] A = new int[n];
        for (int A_i = 0; A_i < n; A_i++) {
            A[A_i] = in.nextInt();
        }
        int[] min = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            if (i == n - 1)
                min[i] = A[i];
            else min[i] = Math.min(min[i + 1], A[i]);
        }
        int q = in.nextInt();
        for (int a0 = 0; a0 < q; a0++) {
            int xi = in.nextInt();
            int result = stockPurchaseDay(min, xi);
            System.out.println(result);
        }
        in.close();
    }
}
