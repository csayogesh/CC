
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by yogesh.bh on 15/10/17 in CC.
 */
public class Danger {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) try {
            String line = sc.nextLine();
            if ("00e0".equals(line))
                break;
            String[] input = line.split("e");
            int n = Integer.parseInt(input[0]);
            n *= Math.pow(10, Integer.parseInt(input[1]));
            System.out.println(getSafePlace(n));
        } catch (Exception e) {

        }
    }

    private static int getSafePlace(int n) {
        int base = 1;
        int up = 2;
        while (n >= up) {
            base <<= 1;
            up <<= 1;
        }
        n -= base;
        return 1 + 2 * n;
    }

    private static int getSafePlaceBrute(int n) {
        List<Integer> res = new ArrayList<Integer>();
        for (int i = 1; i <= n; i++)
            res.add(i);
        int victim = 0;
        while (res.size() > 1) {
            victim += 1;
            victim %= res.size();
            res.remove(victim);
        }
        return res.get(0);
    }
}
