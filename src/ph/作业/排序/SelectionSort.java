package ph.作业.排序;

import p5.排序算法.Sort;

import java.util.Arrays;

public class SelectionSort extends Sort {
    public SelectionSort(int[] arr) {
        super(arr);
    }

    @Override
    public void sort() {
        // 每次选择最小的那个放在前面
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length - 1; j++) {
                if (arr[i] > arr[j]) {
                    swap(i, j);
                }
            }
        }
        System.out.println(Arrays.toString(arr));

    }
}
