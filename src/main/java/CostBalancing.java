import java.util.Scanner;

/**
 * Created by yogesh.bh on 25/10/17 in CC.
 */
public class CostBalancing {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int total = 0;
        int[] paid = new int[m];
        for (int a0 = 0; a0 < n; a0++) {
            int id_number = in.nextInt();
            int amount = in.nextInt();
            total += amount;
            paid[id_number - 1] += amount;
        }
        int owe = total / m;
        System.out.println("1 " + (paid[0] - (owe + total - owe * m)));
        for (int i = 1; i < m; i++)
            System.out.println((i + 1) + " " + (paid[i] - owe));
        in.close();
    }
}
