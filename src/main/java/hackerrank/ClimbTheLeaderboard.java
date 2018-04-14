package hackerrank;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by yogesh.bh on 14/04/18.
 */
public class ClimbTheLeaderboard {
    static int[] climbingLeaderboard(int[] scores, int[] alice) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int score : scores)
            set.add(score);
        List<Integer> ls = new ArrayList<>(set);
        Collections.reverse(ls);
        int res[] = new int[alice.length];
        for (int i = 0; i < alice.length; i++) {
            int rank = bs(ls, alice[i], 0, ls.size() - 1);
            res[i] = rank + 2;
        }
        return res;
    }

    private static int bs(List<Integer> ls, int elem, int i, int j) {
        if (i == j)
            if (ls.get(i) <= elem)
                return i - 1;
            else return i;
        int mid = (i + j) / 2;
        if (ls.get(mid) > elem)
            return bs(ls, elem, mid + 1, j);
        return bs(ls, elem, i, mid);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int scoresCount = Integer.parseInt(scanner.nextLine().trim());

        int[] scores = new int[scoresCount];

        String[] scoresItems = scanner.nextLine().split(" ");

        for (int scoresItr = 0; scoresItr < scoresCount; scoresItr++) {
            int scoresItem = Integer.parseInt(scoresItems[scoresItr].trim());
            scores[scoresItr] = scoresItem;
        }

        int aliceCount = Integer.parseInt(scanner.nextLine().trim());

        int[] alice = new int[aliceCount];

        String[] aliceItems = scanner.nextLine().split(" ");

        for (int aliceItr = 0; aliceItr < aliceCount; aliceItr++) {
            int aliceItem = Integer.parseInt(aliceItems[aliceItr].trim());
            alice[aliceItr] = aliceItem;
        }

        int[] result = climbingLeaderboard(scores, alice);

        for (int resultItr = 0; resultItr < result.length; resultItr++) {
            bufferedWriter.write(String.valueOf(result[resultItr]));

            if (resultItr != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}
