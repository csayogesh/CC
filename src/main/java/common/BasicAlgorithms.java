package common;
/**
 * Created by yogesh.bh on 25/10/17 in CC.
 */
public class BasicAlgorithms {

    public static int binarySearchAtMostIndex(int[] a, int xi, int i, int j) {
        if (i == j && xi >= a[i])
            return i;
        if (i == j && xi < a[i])
            return -2;
        if (i > j)
            return -2;
        int mid = (i + j + 1) / 2;
        if (xi < a[mid])
            return binarySearchAtMostIndex(a, xi, i, mid - 1);
        else return binarySearchAtMostIndex(a, xi, mid, j);
    }
}
