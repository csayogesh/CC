import java.util.ArrayList;

public class GreyCodes {
    public ArrayList<Integer> grayCode(int a) {
        ArrayList<Integer> ls = new ArrayList<Integer>();
        ls.add(0);
        int m = 1;
        for (int i = 1; i <= a; i++) {
            for (int j = ls.size() - 1; j >= 0; j--) {
                ls.add(ls.get(j) + m);
            }
            m *= 2;
        }
        return ls;
    }
}
