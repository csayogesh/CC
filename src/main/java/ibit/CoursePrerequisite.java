package ibit;

import java.util.*;

public class CoursePrerequisite {
    public int solve(int a, List<Integer> b, List<Integer> c) {
        Map<Integer, Set<Integer>> adjList = new HashMap<>();
        for (int i = 0; i < b.size(); i++) {
            adjList.putIfAbsent(b.get(i), new HashSet<>());
            adjList.get(b.get(i)).add(c.get(i));
        }
        return isPossible(adjList, a);
    }

    private int isPossible(Map<Integer, Set<Integer>> adjList, int nodes) {
        Map<Integer, Integer> inCounts = new HashMap<>();
        for (int i = 1; i <= nodes; i++)
            inCounts.put(i, 0);
        for (Map.Entry<Integer, Set<Integer>> entry : adjList.entrySet()) {
            for (int adj : entry.getValue()) {
                inCounts.put(adj, inCounts.get(adj) + 1);
            }
        }
        Queue<Integer> queue = new ArrayDeque<>();
        for (Map.Entry<Integer, Integer> entry : inCounts.entrySet()) {
            if (entry.getValue() == 0)
                queue.add(entry.getKey());
        }
        while (queue.size() > 0) {
            int elem = queue.poll();
            if (adjList.containsKey(elem))
                for (int adj : adjList.get(elem)) {
                    inCounts.put(adj, inCounts.get(adj) - 1);
                    if (inCounts.get(adj) == 0)
                        queue.add(adj);
                }
            adjList.remove(elem);
        }
        return adjList.size() == 0 ? 1 : 0;
    }

    public static void main(String[] args) {
        int[] a = {1, 3, 4, 5};
        int[] b = {2, 1, 5, 3};
        int k = 5;

        List<Integer> A = new ArrayList<>();
        for (int x : a)
            A.add(x);

        List<Integer> B = new ArrayList<>();
        for (int x : b)
            B.add(x);
        int solve = new CoursePrerequisite().solve(k, A, B);
        System.out.println(solve);
    }
}
