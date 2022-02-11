package p5.排序算法;

import java.util.Arrays;

// 三路快排
public class QuickSort03 extends Sort {
    public QuickSort03(int[] arr) {
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
        swap(L, (int) (Math.random() * (R - L + 1) + L));// 随机让后面的数字和第一个数字换一下
        int v = arr[L]; // 中间数
        int lt = L; // 左指针
        int gt = R + 1; // 右指针
        int i = L + 1;
        while (i < gt) {
            if (arr[i] < v) {
                swap(i, lt + 1);
                lt++;
                i++;
            } else if (arr[i] > v) {
                swap(i,gt - 1);
                gt--;
            } else {
                i++;
            }
        }
        swap(L,lt);
        quickSort(L,lt - 1);
        quickSort(gt, R);
    }
}