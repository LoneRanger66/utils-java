import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 两种LRU Cache的实现，参考https://leetcode.com/problems/lru-cache/
 */
public class LRUCache<K, V> {
    private Map<K, V> map;
    private final int cacheSize;

    public LRUCache(int initialCapacity) {
        map = new LinkedHashMap<K, V>(initialCapacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > cacheSize;
            }
        };
        this.cacheSize = initialCapacity;
    }
}

/**
 * 在链头放最久未被使用的元素，链尾放刚刚添加或访问的元素
 */
class LRUCache2 {
    class Node {
        int key, value;
        Node pre, next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
            pre = this;
            next = this;
        }
    }

    private final int capacity;// LRU Cache的容量
    private Node dummy;// dummy的next是第一个节点，dummy的pre是最后一个节点
    private Map<Integer, Node> cache;//保存key-Node对，Node是双向链表节点

    public LRUCache2(int capacity) {
        this.capacity = capacity;
        dummy = new Node(0, 0);
        cache = new ConcurrentHashMap<>();
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) return -1;
        remove(node);
        add(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        if (node == null) {
            if (cache.size() >= capacity) {
                cache.remove(dummy.next.key);
                remove(dummy.next);
            }
            node = new Node(key, value);
            cache.put(key, node);
            add(node);
        } else {
            cache.remove(node.key);
            remove(node);
            node = new Node(key, value);
            cache.put(key, node);
            add(node);
        }
    }

    /**
     * 在链表尾部添加新节点
     *
     * @param node 新节点
     */
    private void add(Node node) {
        dummy.pre.next = node;
        node.pre = dummy.pre;
        node.next = dummy;
        dummy.pre = node;
    }

    /**
     * 从双向链表中删除该节点
     *
     * @param node 要删除的节点
     */
    private void remove(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }
}