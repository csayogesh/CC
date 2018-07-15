package leet;

/**
 * Created by yogesh.bh on 15/07/18.
 */
public class SmallestRotationWithHighestScore {
    static public int bestRotation(int[] arr) {

        int inter[] = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < arr.length) {
                if (arr[i] > i) {
                    int st = i + 1;
                    int end = arr.length - arr[i] + i + 1;
                    inter[st]++;
                    inter[end]--;
//                    System.out.println(st + " " + end);
                } else if (arr[i] == i) {
                    int st = i + 1;
                    int end = arr.length;
//                    System.out.println(st + " " + end);
                    inter[st]++;
                    inter[end]--;
                } else if (arr[i] < i) {
                    int st1 = 0, end1 = i - arr[i] + 1;
                    int end2 = arr.length, st2 = i + 1;
//                    System.out.println(st1 + " " + end1 + " " + st2 + " " + end2);
                    inter[st1]++;
                    inter[end1]--;
                    inter[st2]++;
                    inter[end2]--;
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;
        int cur = 0;
        for (int i = 0; i < arr.length; i++) {
            cur += inter[i];
            if (cur > high) {
                ans = i;
                high = cur;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 1, 1, 4, 0};
        int ans = bestRotation(arr);
        System.out.println(ans);
    }
}
