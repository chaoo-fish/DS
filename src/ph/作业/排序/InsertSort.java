package ph.作业.排序;

import p5.排序算法.Sort;

import java.util.Arrays;

public class InsertSort extends Sort {

    public InsertSort(int[] arr) {
        super(arr);
    }

    @Override
    public void sort() {
        // 类似于小朋友排队
        for (int i = 0; i < arr.length; i++) {
            int e = arr[i];
            int j = 0;
            for (j = i; j > 0 && arr[j - 1] > e; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = e;
        }
        System.out.println(Arrays.toString(arr));
    }
}
