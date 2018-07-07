package leet;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by yogesh.bh on 07/07/18.
 */
public class FourSum2 {
    static public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> cMap = new HashMap<>();
        for (int a : C) {
            cMap.putIfAbsent(a, 0);
            cMap.put(a, cMap.get(a) + 1);
        }
        Map<Integer, Integer> dMap = new HashMap<>();
        for (int a : D) {
            dMap.putIfAbsent(a, 0);
            dMap.put(a, dMap.get(a) + 1);
        }
        int ans = 0;
        Map<Integer, Integer> sMap = new HashMap<>();
        for (int aA : A)
            for (int aB : B) {
                sMap.putIfAbsent(aA + aB, 0);
                sMap.put(aA + aB, sMap.get(aA + aB) + 1);
            }

        for (int cC : cMap.keySet()) {
            for (int dD : dMap.keySet()) {
                if (sMap.containsKey((-cC - dD)))
                    ans += sMap.get(-cC - dD) * cMap.get(cC) * dMap.get(dD);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int A[] = new int[]{0, 1, -1};
        int B[] = new int[]{-1, 1, 0};
        int C[] = new int[]{0, 0, 1};
        int D[] = new int[]{-1, 1, 1};
        int ans = fourSumCount(A, B, C, D);
        System.out.println(ans);
    }
}
