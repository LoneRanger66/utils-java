import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 各种排序算法
 */
public abstract class Sort {
    public abstract void sort(int[] arr);

    private static int[] generateRandomArray(int length) {
        List<Integer> list = new ArrayList<>(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            list.add(random.nextInt(length));
        }
        Collections.shuffle(list);
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    private static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        final int N = 20;
        int[] arr = generateRandomArray(N);
        Sort sort = new QuickSort();
        sort.sort(arr);
        if (isSorted(arr)) {
            System.out.println(true);
        } else {
            System.err.println(false);
        }
    }
}

// 快速排序算法
class QuickSort extends Sort {
    @Override
    public void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(int[] arr, int left, int right) {
        if (left >= right) return;
        int pos = partiton(arr, left, right);
        quickSort(arr, left, pos - 1);
        quickSort(arr, pos + 1, right);
    }

    private int partiton(int[] arr, int left, int right) {
        int key = arr[left];
        while (left < right) {
            while (left < right && arr[right] >= key) right--;
            if (left < right) arr[left] = arr[right];
            while (left < right && arr[left] <= key) left++;
            if (left < right) arr[right] = arr[left];
        }
        arr[left] = key;
        return left;
    }
}

// 归并排序
class MergeSort extends Sort {
    @Override
    public void sort(int[] arr) {
        int[] tmp = new int[arr.length];
        mergeSort(arr, tmp, 0, arr.length - 1);
    }

    private void mergeSort(int[] arr, int[] tmp, int left, int right) {
        if (left >= right) return;
        int mid = (left + right) / 2;
        mergeSort(arr, tmp, left, mid);
        mergeSort(arr, tmp, mid + 1, right);
        int pos = left;
        int i = left, j = mid + 1;
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                tmp[pos++] = arr[i++];
            } else {
                tmp[pos++] = arr[j++];
            }
        }
        while (i <= mid) {
            tmp[pos++] = arr[i++];
        }
        while (j <= right) {
            tmp[pos++] = arr[j++];
        }
        for (i = left; i <= right; i++) {
            arr[i] = tmp[i];
        }
    }
}

// 堆排序。升序的话，用大顶堆
class HeapSort extends Sort {
    @Override
    public void sort(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length - 1);// O(n) 同一层中个数多的需要遍历的层级浅；个数少的需要遍历的层级深
        }
        for (int i = arr.length - 1; i > 0; i--) {
            int tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;
            adjustHeap(arr, 0, i - 1);
        }
    }

    private void adjustHeap(int[] arr, int cur, int end) {
        int key = arr[cur];
        for (int child = 2 * cur + 1; child <= end; child = 2 * child + 1) {
            if (child + 1 <= end && arr[child + 1] > arr[child]) {
                child++;// 如果右子树存在并且比左子树大，指向右子树
            }
            if (arr[child] > key) {
                arr[cur] = arr[child];
                cur = child;
            } else {
                break;
            }
        }
        arr[cur] = key;
    }
}

// 冒泡排序
class BubbleSort extends Sort {
    @Override
    public void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }
}

// 插入排序
class InsertionSort extends Sort {
    @Override
    public void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}

// 选择排序
class SelectionSort extends Sort {
    @Override
    public void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int tmp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = tmp;
        }

    }
}