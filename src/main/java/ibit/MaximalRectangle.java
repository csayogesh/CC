package ibit;

import java.util.ArrayList;

/**
 * Created by yogesh.bh on 28/07/18.
 */
public class MaximalRectangle {
    public int maximalRectangle(ArrayList<ArrayList<Integer>> a) {
        if (a.size() == 0) return 0;
        ArrayList<Integer> ls = new ArrayList();
        int res = 0;
        for (int x : a.get(0)) ls.add(x);
        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; i != 0 && j < a.get(0).size(); j++)
                ls.set(j, ls.get(j) * (a.get(i).get(j)) + (a.get(i).get(j)));
            stk = new ArrayList();
            max = 0;
            res = Math.max(largestRectangleArea(ls), res);
        }
        return res;
    }

    ArrayList<ArrayList<Integer>> stk = new ArrayList<ArrayList<Integer>>();
    int max = 0;

    public int largestRectangleArea(ArrayList<Integer> a) {
        stk.add(new ArrayList<Integer>());
        stk.add(new ArrayList<Integer>());
        stk.add(new ArrayList<Integer>());
        for (int i = 0; i < a.size(); i++) {
            int x = a.get(i);
            if (empty()) {
                push(x, i, i);
            } else if (top() < x) {
                push(x, i, i);
            } else {
                int j = topj(), st;
                st = j;
                while (top() >= x) {
                    st = tops();
                    pop(j);
                }
                push(x, st, i);
            }
        }
        while (!empty()) pop(a.size() - 1);
        return max;
    }

    public boolean empty() {
        return stk.get(0).size() == 0;
    }

    public void push(int x, int i, int j) {
        stk.get(0).add(x);
        stk.get(1).add(i);
        stk.get(2).add(j);
    }

    public void pop(int i) {
        if (empty()) return;
        int tmp = (i - tops() + 1) * top();
        if (tmp > max) max = tmp;
        stk.get(2).remove(size() - 1);
        stk.get(1).remove(size() - 1);
        stk.get(0).remove(size() - 1);
    }

    public int size() {
        return stk.get(0).size();
    }

    public int top() {
        if (empty()) return -1;
        return stk.get(0).get(size() - 1);
    }

    public int topj() {
        if (empty()) return -1;
        return stk.get(2).get(size() - 1);
    }

    public int tops() {
        if (empty()) return -1;
        return stk.get(1).get(size() - 1);
    }
}
