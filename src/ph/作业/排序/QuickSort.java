package ph.作业.排序;

import p5.排序算法.Sort;

public class QuickSort extends Sort {
    public QuickSort(int[] arr) {
        super(arr);
    }

    @Override
    public void sort() {
        quickSort(0, arr.length - 1);
    }

    private void quickSort(int L, int R) {
        if (L >= R) {
            return;
        }
        // 先对数组进行划分 并返回划分后的中点
        int p = partition(L, R);
        quickSort(L, p - 1);
        quickSort(p + 1, R);
    }

    private int partition(int L, int R) {
        // 优化 随机让后面的数字和第一个数字交换一下
        swap(L, (int) (Math.random() * (R - L + 1) + L));
        int v = arr[L];

        int j = L;
        for (int i = L + 1; i <= R; i++) {
            if (arr[i] < v) {
                swap(j + 1, i);
                j++;
            }
        }
        swap(L, j);
        return j;
    }
}
