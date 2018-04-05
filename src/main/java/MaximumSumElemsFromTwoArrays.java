import java.util.*;

/**
 * Created by yogesh.bh on 29/03/18.
 */
public class MaximumSumElemsFromTwoArrays {
    public ArrayList<Integer> solve(ArrayList<Integer> A, ArrayList<Integer> B) {
        Collections.sort(A);
        Collections.sort(B);
        int i = A.size() - 1;
        int j = B.size() - 1;
        TreeMap<Integer, LinkedList<List<Integer>>> heap = new TreeMap<>();
        List<Integer> ls = new ArrayList<Integer>();
        ls.add(i);
        ls.add(j);
        LinkedList<List<Integer>> contain = new LinkedList();
        contain.add(ls);
        heap.put(A.get(i) + B.get(j), contain);
        ArrayList<Integer> res = new ArrayList();
        Map<Integer, Map<Integer, Boolean>> map = new HashMap();
        map.putIfAbsent(i, new HashMap());
        map.get(i).put(j, true);
        for (i = 0; i < A.size(); i++) {
            Map.Entry<Integer, LinkedList<List<Integer>>> max = heap.lastEntry();
            List<Integer> indices = max.getValue().removeFirst();
            if (max.getValue().size() == 0)
                heap.remove(max.getKey());
            res.add(max.getKey());
            int k = indices.get(0);
            int l = indices.get(1);
            map.putIfAbsent(k, new HashMap());
            map.putIfAbsent(l, new HashMap());
            map.putIfAbsent(k - 1, new HashMap());
            map.putIfAbsent(l - 1, new HashMap());
            if (l - 1 >= 0 && !map.get(k).containsKey(l - 1)) {
                heap.putIfAbsent(A.get(k) + B.get(l - 1), new LinkedList());
                heap.get(A.get(k) + B.get(l - 1)).add(new ArrayList<Integer>() {{
                    add(k);
                    add(l - 1);
                }});
                map.get(k).put(l - 1, true);
            }
            if (k - 1 >= 0 && !map.get(k - 1).containsKey(l)) {
                heap.putIfAbsent(A.get(k - 1) + B.get(l), new LinkedList());
                heap.get(A.get(k - 1) + B.get(l)).add(new ArrayList<Integer>() {{
                    add(k - 1);
                    add(l);
                }});
                map.get(k - 1).put(l, true);
            }
        }
        return res;
    }
}
