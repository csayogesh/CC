package hckrnk;

import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by yogesh.bh on 15/04/18.
 */
public class SubsetComponents {
    static int findConnectedComponents(long[] d) {
        int totalSubsets = 1 << d.length;
        int ans = 0;
        for (int i = 0; i < totalSubsets; i++) {
            Set<Long> set = new HashSet<>();
            for (int j = 0; j < 20; j++) {
                if (((i >> j) & 1) == 1)
                    set.add(d[j]);
            }
            ans += totalComponents(set);
        }
        return ans;
    }

    private static int totalComponents(Set<Long> set) {
        Set<Long> components = new HashSet<>();
        for (long elem : set) {
            Set<Long> connected = getConnected(components, elem);
            components.removeAll(connected);
            long newComp = elem;
            for (long cmp : connected)
                newComp |= cmp;
            components.add(newComp);
        }
        int toRemove = 0;
        for (long cmp : components)
            for (int i = 0; i < 64; i++)
                if (((cmp >> i) & 1) == 1)
                    toRemove++;
        return 64 + components.size() - toRemove;
    }

    private static Set<Long> getConnected(Set<Long> components, long elem) {
        Set<Long> res = new HashSet<>();
        for (long component : components)
            if ((component & elem) > 0)
                res.add(component);
        return res;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int dCount = Integer.parseInt(scanner.nextLine().trim());

        long[] d = new long[dCount];

        String[] dItems = scanner.nextLine().split(" ");

        for (int dItr = 0; dItr < dCount; dItr++) {
            long dItem = Long.parseLong(dItems[dItr].trim());
            d[dItr] = dItem;
        }

        int components = findConnectedComponents(d);
    }
}
