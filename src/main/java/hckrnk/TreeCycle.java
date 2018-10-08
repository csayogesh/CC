package hckrnk;

import java.util.*;

public class TreeCycle {
    private static int[] getAns(Map<Integer, List<Integer>> tree, int cur, int parent) {
        List<Integer> childs = tree.get(cur);
        List<int[]> hs = new ArrayList<>();
        List<int[]> ds = new ArrayList<>();
        int[] ans = new int[3];
        ans[0] = 1;
        ans[1] = cur;
        ans[2] = cur;
        for (int c : childs) {
            if (c != parent) {
                hs.add(height(tree, c, cur));
                ds.add(getAns(tree, c, cur));
            }
        }
        if (hs.size() == 0)
            return ans;
        Collections.sort(hs, (o1, o2) -> Integer.compare(o2[0], o1[0]));
        Collections.sort(ds, (o1, o2) -> Integer.compare(o2[0], o1[0]));
        int[] d1 = null;
        int[] h1 = null;
        int[] h2 = null;
        if (ds.size() > 0)
            d1 = ds.get(0);
        else d1 = ans;
        if (hs.size() > 0)
            h1 = hs.get(0);
        else h1 = new int[]{0, cur};
        if (hs.size() > 1)
            h2 = hs.get(1);
        else h2 = new int[]{0, cur};
        if (d1[0] > h1[0] + h2[0] + 1)
            return d1;
        return new int[]{h1[0] + h2[0] + 1, h1[1], h2[1]};
    }

    private static int[] height(Map<Integer, List<Integer>> tree, int cur, int parent) {
        int ans[] = new int[2];
        ans[0] = 0;
        ans[1] = cur;
        List<Integer> childs = tree.get(cur);
        for (int c : childs) {
            if (parent != c) {
                int[] h = height(tree, c, cur);
                if (h[0] >= ans[0]) {
                    ans[0] = h[0];
                    ans[1] = h[1];
                }
            }
        }
        ans[0]++;
        return ans;
    }

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        Map<Integer, List<Integer>> map = new HashMap();
        int n = sc.nextInt();
        int startNode = -1;
        for (int i = 0; i < n - 1; i++) {
            int n1 = sc.nextInt();
            int n2 = sc.nextInt();
            startNode = n1;
            map.putIfAbsent(n1, new ArrayList());
            map.putIfAbsent(n2, new ArrayList());
            map.get(n1).add(n2);
            map.get(n2).add(n1);
        }
        int[] ans = getAns(map, startNode, -1);
        System.out.println(ans[1] + " " + ans[2]);
    }
}
