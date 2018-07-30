package ibit;

public class ArrayMedian {
    public static void main(String[] args) {
        int[] arr = {10, 2, 15, 1, 3, 14, 4, 9, 8, 7, 5, 6, 11, 12, 13};
        double median = findMedian(arr);
        System.out.println(median);
    }

    private static double findMedian(int[] arr) {
        return find(arr, 0, arr.length - 1);
    }

    private static double find(int[] arr, int i, int j) {
        int len = j - i + 1;
        if (len % 2 == 0) {
            int kThElem = findKthElem(arr, i, j, len / 2);
            int kPlusThElem = findKthElem(arr, i, j, len / 2 + 1);
            double ans = kThElem + kPlusThElem;
            return ans / 2;
        }
        return findKthElem(arr, i, j, len / 2 + 1);
    }

    private static int findKthElem(int[] arr, int i, int j, int k) {
        int len = j - i + 1;
        if (len == 1)
            return arr[i];
        int pivote = arr[i];
        int x = i;
        for (int ind = i + 1; ind <= j; ind++) {
            if (arr[ind] < pivote) {
                x++;
                int tmp = arr[x];
                arr[x] = arr[ind];
                arr[ind] = tmp;
            }
        }
        int tmp = arr[x];
        arr[x] = pivote;
        arr[i] = tmp;
        int ans = x + 1 - i;
        if (ans < k)
            return findKthElem(arr, i + ans, j, k - ans);
        else if (ans > k)
            return findKthElem(arr, i, ans - 2, k);
        else return pivote;
    }
}
