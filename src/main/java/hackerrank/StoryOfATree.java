package hackerrank;

import java.io.IOException;
import java.util.*;

/**
 * Created by yogesh.bh on 16/04/18.
 */
public class StoryOfATree {

    static class Node {
        public int val;
        public List<Node> children = new ArrayList<>();
        public Node parent;

        public Node(int i) {
            val = i;
        }
    }

    static String storyOfATree(int n, int[][] edges, int k, int[][] guesses) {
        Map<Integer, Set<Integer>> adj = new HashMap<>();
        for (int[] edge : edges) {
            adj.putIfAbsent(edge[0], new HashSet<>());
            adj.putIfAbsent(edge[1], new HashSet<>());
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        Map<Integer, Node> nodeMap = new HashMap<>();
        makeTree(adj, 1, nodeMap);
        int success = 0;
        for (int hid = 1; hid <= n; hid++) {
            if (hid != 1) {
                makeRoot(nodeMap.get(hid));
                nodeMap.get(hid).parent = null;
            }
            int correct = 0;
            for (int[] guess : guesses)
                try {
                    if (nodeMap.get(guess[1]).parent != null && nodeMap.get(guess[1]).parent.val == guess[0])
                        correct++;
                } catch (Exception e) {
                    System.out.println(1);
                }
            if (correct >= k)
                success++;
        }
        int gcd = gcdF(success, n);
        success /= gcd;
        n /= gcd;
        return success + "/" + n;
    }

    private static int gcdF(int a, int b) {
        if (b == 0)
            return a;
        if (b > a)
            return gcdF(b, a);
        return gcdF(b, a % b);
    }

    private static void makeRoot(Node root) {
        if (root == null)
            return;
        Node oldParent = root.parent;
        makeRoot(oldParent);
        if (oldParent != null)
            oldParent.parent = root;
    }

    private static Node makeTree(Map<Integer, Set<Integer>> adj, int root, Map<Integer, Node> map) {
        if (adj.get(root).size() == 0) {
            Node node = new Node(root);
            map.put(root, node);
            return node;
        }
        Node rNode = new Node(root);

        List<Integer> ls = new ArrayList<>(adj.get(root));
        for (int child : ls) {
            adj.get(child).remove(root);
            Node node = makeTree(adj, child, map);
            rNode.children.add(node);
            node.parent = rNode;
        }
        map.put(root, rNode);
        return rNode;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int q = Integer.parseInt(scanner.nextLine().trim());

        for (int qItr = 0; qItr < q; qItr++) {
            int n = Integer.parseInt(scanner.nextLine().trim());

            int[][] edges = new int[n - 1][2];

            for (int edgesRowItr = 0; edgesRowItr < n - 1; edgesRowItr++) {
                String[] edgesRowItems = scanner.nextLine().split(" ");

                for (int edgesColumnItr = 0; edgesColumnItr < 2; edgesColumnItr++) {
                    int edgesItem = Integer.parseInt(edgesRowItems[edgesColumnItr].trim());
                    edges[edgesRowItr][edgesColumnItr] = edgesItem;
                }
            }

            String[] gk = scanner.nextLine().split(" ");

            int g = Integer.parseInt(gk[0].trim());

            int k = Integer.parseInt(gk[1].trim());

            int[][] guesses = new int[g][2];

            for (int guessesRowItr = 0; guessesRowItr < g; guessesRowItr++) {
                String[] guessesRowItems = scanner.nextLine().split(" ");

                for (int guessesColumnItr = 0; guessesColumnItr < 2; guessesColumnItr++) {
                    int guessesItem = Integer.parseInt(guessesRowItems[guessesColumnItr].trim());
                    guesses[guessesRowItr][guessesColumnItr] = guessesItem;
                }
            }

            String result = storyOfATree(n, edges, k, guesses);
            System.out.println(result);
        }

    }
}
