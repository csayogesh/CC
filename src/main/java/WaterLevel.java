public class WaterLevel {
    //
    //  1,2,2,1,2,1  ==> 2
    //
    public static void main(String[] args) {
        int[] input = {4, 6, 2, 1, 5}; // 4+3 = 7
        //    4,5,1,2,6
        int[] left = new int[input.length];
        int max = 0;
        for (int i = 0; i < input.length; i++) {
            left[i] = max;
            max = Math.max(max, input[i]);
        }
        max = 0;
        for (int i = input.length - 1; i >= 0; i--) {
            left[i] = Math.min(left[i], max);
            max = Math.max(max, input[i]);
        }
        int ans = 0;
        for (int i = 0; i < input.length; i++) {
            System.out.println("left " + left[i]);
            int temp = left[i] - input[i];
            if (temp > 0)
                ans += temp;
        }
        System.out.println(ans);
    }
}