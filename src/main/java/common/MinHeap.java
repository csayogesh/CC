package common;

import java.util.ArrayList;

/**
 * Created by yogesh.bh on 29/04/18.
 */
public class MinHeap {
    private ArrayList<Integer> ls = new ArrayList<>();
    private int size = 0;

    public MinHeap() {
    }

    public MinHeap(ArrayList<Integer> ls) {
        this.ls = ls;
        size = ls.size();
        heapify();
    }

    public MinHeap(int[] data) {
        for (int x : data)
            this.ls.add(x);
        size = data.length;
        heapify();
    }

    private void heapify() {
        for (int i = ls.size() / 2; i >= 0; i--)
            minHeapify(i);
    }

    private void minHeapify(int i) {
        int l = left(i);
        int r = right(i);
        int min;
        if (l < size && ls.get(l) < ls.get(i))
            min = l;
        else min = i;
        if (r < size && ls.get(r) < ls.get(min))
            min = r;
        if (min != i) {
            int tmp = ls.get(i);
            ls.set(i, ls.get(min));
            ls.set(min, tmp);
            minHeapify(min);
        }
    }

    public int findMin() {
        return ls.get(0);
    }

    public int removeMin() {
        int ans = findMin();
        ls.set(0, ls.get(size - 1));
        size = size - 1;
        minHeapify(0);
        return ans;
    }

    public void insert(int key) {
        if (ls.size() > size) ls.set(size, Integer.MAX_VALUE);
        else ls.add(Integer.MAX_VALUE);
        size++;
        decreaseKey(size - 1, key);
    }

    private void decreaseKey(int i, int key) {
        ls.set(i, key);
        while (i > 0 && ls.get(parent(i)) > ls.get(i)) {
            int tmp = ls.get(parent(i));
            ls.set(parent(i), ls.get(i));
            ls.set(i, tmp);
            i = parent(i);
        }
    }

    private int left(int i) {
        return 2 * i;
    }

    private int right(int i) {
        return 2 * i + 1;
    }

    private int parent(int i) {
        return i / 2;
    }

    public int getSize() {
        return size;
    }
}
