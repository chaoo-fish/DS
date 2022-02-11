package ph.作业.排序;

import p5.排序算法.Sort;

import java.util.Arrays;

public class BubbleSort extends Sort {

    public BubbleSort(int[] arr) {
        super(arr);
    }

    @Override
    public void sort() {
        // 大数上浮
        for (int i = 0; i < arr.length - 1; i++) {  //  6 5 4 只需要找两次
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(j, j + 1);
                }
            }
        }
//        System.out.println(Arrays.toString(arr));
    }
}
