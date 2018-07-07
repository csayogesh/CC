package leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by yogesh.bh on 07/07/18.
 */
public class FourSum2 {
    static public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Arrays.sort(C);
        Arrays.sort(D);
        int ans = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                int target2 = 0 - A[i] - B[j];
                for (int k = 0, l = D.length - 1; k < C.length && l >= 0; ) {
                    if ((C[k] + D[l]) < target2) k++;
                    else if ((C[k] + D[l]) > target2) l--;
                    else {
                        ans++;
                        k++;
                        l--;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int A[] = new int[]{1, 2};
        int B[] = new int[]{-2, -1};
        int C[] = new int[]{-1, 2};
        int D[] = new int[]{0, 2};
        int ans = fourSumCount(A, B, C, D);
        System.out.println(ans);
    }
}
