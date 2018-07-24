import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    class KeyVal {
        int key, val;
        KeyVal prev = null, next = null;

        public KeyVal(int k, int v) {
            key = k;
            val = v;
        }
    }

    class DList {
        KeyVal head, tail;
        int size, cap;

        public DList(int c) {
            cap = c;
            size = 0;
        }

        public int size() {
            return size;
        }

        public void addFirst(KeyVal obj) {
            if (obj == null) return;
            if (head == null) head = tail = obj;
            else {
                obj.next = head;
                obj.prev = null;
                head.prev = obj;
                head = obj;
            }
            size++;
        }

        public void removeLast() {
            if (tail == null) return;
            map.remove(tail.key);
            if (head == tail) head = tail = null;
            else if (tail != null) {
                KeyVal t = tail.prev;
                tail.prev = tail.next = null;
                if (t != null) {
                    t.next = null;
                    tail = t;
                }
            } else return;
            size--;
        }

        public void remove(KeyVal obj) {
            if (obj == null) return;
            if (head == tail && obj == head) {
                head = tail = null;
            } else if (obj == head) {
                head = head.next;
                head.prev = null;
            } else if (obj == tail) {
                tail = tail.prev;
                tail.next = null;
            } else {
                obj.prev.next = obj.next;
                obj.next.prev = obj.prev;
            }
            obj.prev = obj.next = null;
            size--;
        }
    }

    DList list = null;
    Map<Integer, KeyVal> map = new HashMap<Integer, KeyVal>();
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        list = new DList(capacity);
    }

    public int get(int key) {
        KeyVal res = map.get(key);
        if (res == null) return -1;
        list.remove(res);
        list.addFirst(res);
        return res.val;
    }

    public void set(int key, int value) {
        KeyVal res = map.get(key);
        if (res == null) {
            res = new KeyVal(key, value);
            if (list.size() == capacity) list.removeLast();
            map.put(key, res);
            list.addFirst(res);
        } else {
            res.val = value;
            list.remove(res);
            list.addFirst(res);
        }
    }
}
