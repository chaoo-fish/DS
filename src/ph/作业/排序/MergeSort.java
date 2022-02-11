package ph.作业.排序;

import p5.排序算法.Sort;

import java.util.Arrays;

public class MergeSort extends Sort {
    public MergeSort(int[] arr) {
        super(arr);
    }

    @Override
    public void sort() {
        mergeSort(0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    private void mergeSort(int L, int R) {
        if (L >= R) {
            return;
        }
        int mid = (L + R) / 2;
        mergeSort(L, mid);
        mergeSort(mid + 1, R);

        // 一直递归到最后面就是需要去合并 两个有序数组
        // 如果左边数组最大值小于右边数组最小值,就不需要进行合并
        if (arr[mid] > arr[mid + 1]) {
            merge(L, mid, R);
        }
    }

    // 两个有序数组的合并
    private void merge(int L, int mid, int R) {
        // 先接受原数组
        int[] aux = new int[R - L + 1];
        for (int k = L; k <= R; k++) {
            aux[k - L] = arr[k];
        }

        int i = L; // 左边数组起始位置
        int j = mid + 1;// 右边数组起始位置
        for (int k = L; k <= R; k++) {
            if (i > mid) { // 如果左边的排完了
                arr[k] = aux[j - L];
                j++;
            } else if (j > R) { // 如果右边的排完了
                arr[k] = aux[i - L];
                i++;
            } else if (aux[i - L] < aux[j - L]) { // 如果左边的数字小
                arr[k] = aux[i - L];
                i++;
            } else {
                arr[k] = aux[j - L];
                j++;
            }


        }
    }
}
