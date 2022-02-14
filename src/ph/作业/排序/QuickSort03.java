package ph.作业.排序;

import p5.排序算法.Sort;

import java.util.Arrays;

public class QuickSort03 extends Sort {

    public QuickSort03(int[] arr) {
        super(arr);
    }

    @Override
    public void sort() {
        quickSort(0, arr.length - 1);
//        System.out.println(Arrays.toString(arr));
    }

    private void quickSort(int l, int r) {
        if (l >= r) {
            return;
        }

        int v = arr[l]; // 中间值
        int lt = l;
        int gt = r + 1;
        int i = l + 1;
        while (i < gt) {
            if (arr[i] < v) {
                swap(i,lt + 1);
                i++;
                lt++;
            } else if (arr[i] > v) {
                swap(i, gt - 1);
                gt--;
            } else {
                i++;
            }
        }
        swap(l,lt);
        quickSort(l,lt - 1);
        quickSort(gt, r);
    }
}
