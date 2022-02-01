package p5.排序算法;

import java.util.Arrays;

// 归并排序
public class MergeSort extends Sort {

    public MergeSort(int[] arr) {
        super(arr);
    }

    @Override
    public void sort() {
        mergeSort(0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    private void mergeSort(int L, int R) { // 5  0  4    02 34
        if (L >= R) {
            return;
        }
        int mid = (L + R) / 2;
        // 递归排序当前层级的左边
        mergeSort(L, mid);
        // 递归排序当前层级的右边
        mergeSort(mid + 1, R);

        // 左边排序完了 右边排序也完了
        // 将左右进行合并
        // 特殊 如果左边的最大值arr[mid]小于等于右边的最小值arr[mid+1] 则不需要合并
        if (arr[mid] > arr[mid + 1]) {
            merge(L, mid, R);
        }
    }

    private void merge(int L, int mid, int R) {
        int[] aux = new int[R - L + 1];
        for (int k = L; k <= R; k++) {
            aux[k - L] = arr[k];
        }
        int i = L;
        int j = mid + 1;
        for (int k = L; k <= R; k++) {
            if (i > mid) { // 左边完毕了
                arr[k] = aux[j - L];
                j++;
            } else if (j > R) { // 右边完毕了
                arr[k] = aux[i - L];
                i++;
            } else if (aux[i - L] < aux[j - L]) {
                arr[k] = aux[i - L];
                i++;
            } else {
                arr[k] = aux[j - L];
                j++;
            }
        }
    }
}
