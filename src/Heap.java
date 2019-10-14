/**
 * 自己实现的小顶堆，暂时忽略扩容的部分
 */
public class Heap {
    private int[] queue;
    private int size = 0;

    public Heap(int initialCapacity) {
        if (initialCapacity < 1)
            throw new IllegalArgumentException();
        this.queue = new int[initialCapacity];
    }

    public Heap(int i, int[] arr) {
        this.size = arr.length;
        this.queue = arr;
    }

    private void siftDown(int cur) {
        int key = queue[cur];
        for (int child = 2 * cur + 1; child <= size - 1; child = 2 * child + 1) {
            if (child + 1 <= size - 1 && queue[child + 1] < queue[child]) {
                child++;
            }
            if (queue[child] < key) {
                queue[cur] = queue[child];
                cur = child;
            } else {
                break;
            }
        }
        queue[cur] = key;
    }

    private void siftUp(int cur) {
        int key = queue[cur];
        while (cur > 0) {
            int parent = (cur - 1) / 2;
            if (key < queue[parent]) {
                queue[cur] = queue[parent];
                cur = parent;
            } else {
                break;
            }
        }
        queue[cur] = key;
    }

    /**
     * 将数组建成堆
     */
    public void heapify() {
        for (int i = size / 2 - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    public void offer(int num) {
        queue[size++] = num;
        siftUp(size - 1);
    }

    public int poll() {
        int value = queue[0];
        queue[0] = queue[--size];
        siftDown(0);
        return value;
    }

    public int peek() {
        return queue[0];
    }

    public boolean isEmpty() {
        return this.size == 0;
    }
}
