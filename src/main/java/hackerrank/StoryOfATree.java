package hackerrank;

import java.io.IOException;
import java.util.*;

/**
 * Created by yogesh.bh on 16/04/18.
 */
public class StoryOfATree {
    static String storyOfATree(int n, int[][] edges, int k, int[][] guesses) {
        Map<Integer, Set<Integer>> adj = new HashMap<>();
        for (int[] edge : edges) {
            adj.putIfAbsent(edge[0], new HashSet<>());
            adj.putIfAbsent(edge[1], new HashSet<>());
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        Map<Integer, Set<Integer>> guessAdj = new HashMap<>();
        for (int[] guess : guesses) {
            guessAdj.putIfAbsent(guess[0], new LinkedHashSet<>());
            guessAdj.get(guess[0]).add(guess[1]);
        }
        Map<Integer, Integer> counts = new HashMap<>();
        dfsSubTree(1, 1, adj, guessAdj, counts);
        dfsFull(1, 1, adj, guessAdj, counts);

        int success = 0;
        for (int hid = 1; hid <= n; hid++) {
            int correct = counts.getOrDefault(hid, 0);
            if (correct >= k)
                success++;
        }
        int gcd = gcdF(success, n);
        success /= gcd;
        n /= gcd;
        return success + "/" + n;
    }

    private static void dfsFull(int node, int parent, Map<Integer, Set<Integer>> adjList, Map<Integer, Set<Integer>> guesses, Map<Integer, Integer> counts) {
        counts.put(node, counts.getOrDefault(parent, 0));
        if (guesses.containsKey(parent) && guesses.get(parent).contains(node))
            counts.put(node, counts.get(node) - 1);
        if (guesses.containsKey(node) && guesses.get(node).contains(parent))
            counts.put(node, counts.get(node) + 1);
        for (int child : adjList.get(node))
            if (child != parent) {
                dfsFull(child, node, adjList, guesses, counts);
            }
    }

    private static void dfsSubTree(int node, int parent, Map<Integer, Set<Integer>> adjList, Map<Integer, Set<Integer>> guesses, Map<Integer, Integer> counts) {
        for (int child : adjList.get(node))
            if (child != parent) {
                if (guesses.containsKey(node) && guesses.get(node).contains(child))
                    counts.put(node, counts.getOrDefault(node, 0) + 1);
                dfsSubTree(child, node, adjList, guesses, counts);
                counts.put(node, counts.getOrDefault(node, 0) + counts.getOrDefault(child, 0));
            }
    }

    private static int gcdF(int a, int b) {
        if (b == 0)
            return a;
        if (b > a)
            return gcdF(b, a);
        return gcdF(b, a % b);
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
