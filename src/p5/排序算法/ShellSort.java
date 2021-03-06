package p5.排序算法;

// 希尔排序
public class ShellSort extends Sort {
    public ShellSort(int[] arr) {
        super(arr);
    }

    @Override
    public void sort() {
        int len = arr.length;
        // O(n^1.3)
        for (int gap = len / 2; gap > 0; gap = gap / 2) { // 每次取间隔为一半
            for (int i = gap; i < len; i++) {
                int e = arr[i];
                int j = i;
                while (j - gap >= 0 && arr[j - gap] > e) { // 插入排序的体现
                    arr[j] = arr[j - gap];
                    j = j - gap;
                }
                arr[j] = e;
            }
        }
    }
}
