import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SMSSplitter {
    public static void main(String args[]) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        String input = sc.readLine();
        StringTokenizer tokenizer = new StringTokenizer(input, " ");
        ArrayList<Integer> ls = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            int len = token.length();
            ls.add(len);
        }
        int tRange = 1;
        int ans = 0;
        int maxTot = (int) Math.pow(10, tRange);
        out:
        while (ans < maxTot) {
            ans = 0;
            for (int i = 0; i < ls.size(); ) {
                ans++;
                if (ans >= maxTot)
                    break;
                int m = String.valueOf(ans).length() + 4 + tRange;
                int init = m;
                while (i < ls.size() && (m + ls.get(i) + (init == m ? 0 : 1)) <= 30) {
                    m += ls.get(i) + (init == m ? 0 : 1);
                    i++;
                }
                if (i == ls.size())
                    break out;
            }
            tRange++;
            maxTot = (int) Math.pow(10, tRange);
        }
        System.out.println(ans);
    }

}