package hckrnk;

import java.util.*;

/**
 * Created by yogesh.bh on 22/04/18.
 */
public class MissingNumbers {
    static int[] missingNumbers(int[] arr, int[] brr) {
        TreeSet<Integer> set = new TreeSet<>();
        Map<Integer, Integer> map1 = new HashMap();
        for (int elem : arr) {
            map1.putIfAbsent(elem, 0);
            map1.put(elem, map1.get(elem) + 1);
            set.add(elem);
        }
        Map<Integer, Integer> map2 = new HashMap();
        for (int elem : brr) {
            map2.putIfAbsent(elem, 0);
            map2.put(elem, map2.get(elem) + 1);
            set.add(elem);
        }
        Set<Integer>set2=new HashSet(set);
        for (int elem : set2) {
            if (map1.containsKey(elem) && map2.containsKey(elem) && map1.get(elem).equals(map2.get(elem)))
                set.remove(elem);
        }
        int res[] = new int[set.size()];
        Iterator<Integer> it = set.iterator();
        for (int i = 0; i < set.size(); i++) {
            res[i] = it.next();
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int arr_i = 0; arr_i < n; arr_i++) {
            arr[arr_i] = in.nextInt();
        }
        int m = in.nextInt();
        int[] brr = new int[m];
        for (int brr_i = 0; brr_i < m; brr_i++) {
            brr[brr_i] = in.nextInt();
        }
        int[] result = missingNumbers(arr, brr);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + (i != result.length - 1 ? " " : ""));
        }
        System.out.println("");


        in.close();
    }
}
