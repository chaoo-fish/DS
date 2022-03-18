package ph.作业.排序;

import p5.排序算法.ArrayData;

public class AATest {
    public static void main(String[] args) {
        ArrayData data = new ArrayData(1);
        int[] arr = data.makeData();
//        sort01(arr);
//        sort02(arr);
//        sort03(arr);
//        sort04(arr);
//        sort05(arr);
        sort06(arr);

    }

    private static void sort06(int[] arr) {
        QuickSort03 quickSort03 = new QuickSort03(arr);
        Long start = System.currentTimeMillis();
        quickSort03.sort();
        Long end = System.currentTimeMillis();
        System.out.println("三路快排:" + (end - start) + "ms");

    }

    private static void sort05(int[] arr) {
        MergeSort bs = new MergeSort(arr);
        bs.sort();
    }

    private static void sort04(int[] arr) {
        ShellSort bs = new ShellSort(arr);
        bs.sort();
    }

    private static void sort03(int[] arr) {
        InsertSort bs = new InsertSort(arr);
        bs.sort();
    }

    private static void sort01(int[] arr) {
        BubbleSort bs = new BubbleSort(arr);
        bs.sort();
    }

    private static void sort02(int[] arr) {
        SelectionSort bs = new SelectionSort(arr);
        bs.sort();
    }
}
