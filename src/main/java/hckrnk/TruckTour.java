package hckrnk;

import java.io.IOException;
import java.util.Scanner;

public class TruckTour {

    /*
     * Complete the truckTour function below.
     */
    static int truckTour(int[][] petrolpumps) {
        /*
         * Write your code here.
         */
        long sum = 0;
        int i = 0;
        int start = 0;
        while (sum >= 0 && start < petrolpumps.length) {
            sum += (petrolpumps[i][0] - petrolpumps[i][1]);
            i = (i + 1) % petrolpumps.length;
            if (i == start)
                break;
            if (sum < 0) {
                start = i;
                sum = 0;
            }
        }
        return start;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(scanner.nextLine().trim());

        int[][] petrolpumps = new int[n][2];

        for (int petrolpumpsRowItr = 0; petrolpumpsRowItr < n; petrolpumpsRowItr++) {
            String[] petrolpumpsRowItems = scanner.nextLine().split(" ");

            for (int petrolpumpsColumnItr = 0; petrolpumpsColumnItr < 2; petrolpumpsColumnItr++) {
                int petrolpumpsItem = Integer.parseInt(petrolpumpsRowItems[petrolpumpsColumnItr].trim());
                petrolpumps[petrolpumpsRowItr][petrolpumpsColumnItr] = petrolpumpsItem;
            }
        }

        int result = truckTour(petrolpumps);

        System.out.println(result);
    }
}
