package hackerrank;

import java.util.Scanner;

/**
 * Created by yogesh.bh on 24/04/18.
 */
public class LeftRotation {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int a[] = new int[n];
        for (int a_i = 0; a_i < n; a_i++) {
            a[a_i] = in.nextInt();
        }
        int i = k % n;
        do {
            System.out.print(a[i] + " ");
            i = (i + 1 + n) % n;
        } while (i > k % n || i < k % n);
    }
}
