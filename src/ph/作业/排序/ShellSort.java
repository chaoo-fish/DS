package ph.作业.排序;

import p5.排序算法.Sort;

import java.util.Arrays;

public class ShellSort extends Sort {
    public ShellSort(int[] arr) {
        super(arr);
    }

    @Override
    public void sort() {
        int len = arr.length;
        for (int gap = len / 2; gap > 0; gap = gap / 2) {
            for (int i = gap; i < len; i++) {
                // 使用插入排序,但是数组间隔为gap
                int e = arr[i];
                int j = i;
                while (j - gap >= 0 && arr[j - gap] > e) {
                    arr[j] = arr[j - gap];
                    j = j - gap;
                }
                arr[j] = e;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
