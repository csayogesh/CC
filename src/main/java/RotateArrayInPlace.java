public class RotateArrayInPlace {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8}; // 3, 4, 1, 2
        int m = 6;
        m = (arr.length - m) % arr.length;
        if (m != 0) {
            int lastNum = 0;
            int numOfDisp = 0;
            int i = 0;
            while (numOfDisp < arr.length) {
                int j = (i + m) % arr.length;
                lastNum = arr[i];
                while (true) {
                    int temp = arr[j];
                    arr[j] = lastNum;
                    lastNum = temp;
                    numOfDisp++;
                    if (j == i)
                        break;
                    j = (j + m) % arr.length;
                }
                i++;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
