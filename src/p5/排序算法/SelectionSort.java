package p5.排序算法;

import java.util.Arrays;

//选择排序 O(n^2) S(1) 不稳定
public class SelectionSort extends Sort {
    public SelectionSort(int[] arr) {
        super(arr);
    }

    @Override
    public void sort() {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    swap(i, j);
                }
            }
        }
    }
}