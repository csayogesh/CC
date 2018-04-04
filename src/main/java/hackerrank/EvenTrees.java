package hackerrank;

import java.util.*;

public class EvenTrees {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] treeNodesEdges = scanner.nextLine().split(" ");
        int treeEdges = Integer.parseInt(treeNodesEdges[1].trim());

        Map<Integer, Node> map = new HashMap();
        Set<Integer> set = new HashSet();
        for (int treeItr = 0; treeItr < treeEdges; treeItr++) {
            String[] treeFromTo = scanner.nextLine().split(" ");
            int to = Integer.parseInt(treeFromTo[0].trim());
            int from = Integer.parseInt(treeFromTo[1].trim());
            set.add(to);
            map.putIfAbsent(from, new Node(from));
            map.putIfAbsent(to, new Node(to));
            map.get(from).sub.add(map.get(to));
        }

        Node root = null;
        Set<Integer> set2 = new HashSet(map.keySet());
        set2.removeAll(set);
        for (int origin : set2)
            root = map.get(origin);
        int ans = compute(root);
        System.out.println(ans);
    }

    public static int compute(Node root) {
        if (root.sub.size() == 0) {
            root.subSum = 1;
            return 0;
        }
        int ans = 0;
        for (Node n : root.sub) {
            int subAns = compute(n);
            if (n.subSum % 2 == 0)
                ans++;
            else root.subSum += n.subSum;
            ans += subAns;
        }
        root.subSum++;
        return ans;
    }

    static class Node {
        public int val;
        public int subSum;
        public List<Node> sub = new ArrayList();

        public Node(int v) {
            val = v;
        }
    }
}
