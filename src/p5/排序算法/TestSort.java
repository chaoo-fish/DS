package p5.排序算法;

import java.util.Arrays;

/*
算法的执行时间
除了跟算法的策略有关系之外
还跟数据分布情况有关系
数据分布情况：
        完全随机    大致有序    大致平稳
选择      5           5          4
冒泡      4           4          5
插入      3           1          3
希尔      2           3          2
归并      1           2          1
单快      1+          3+         1+
*/
public class TestSort {
    public static void main(String[] args) {

        ArrayData data = new ArrayData(2);
        int[] arr = data.makeData();
        test01(arr);
        test02(arr);
        test03(arr);
        test04(arr);
        test05(arr);
        test06(arr);
    }

    private static void test06(int[] arr) {
        QuickSort01 quickSort01 = new QuickSort01(arr);
        Long start = System.currentTimeMillis();
        quickSort01.sort();
        Long end = System.currentTimeMillis();
        System.out.println("单路快排：" + (end - start) + "ms");
    }

    private static void test05(int[] arr) {
        MergeSort mergeSort = new MergeSort(arr);
        Long start = System.currentTimeMillis();
        mergeSort.sort();
        Long end = System.currentTimeMillis();
        System.out.println("归并排序：" + (end - start) + "ms");
    }

    private static void test04(int[] arr) {
        ShellSort shellSort = new ShellSort(arr);
        Long start = System.currentTimeMillis();
        shellSort.sort();
        Long end = System.currentTimeMillis();
        System.out.println("希尔排序：" + (end - start) + "ms");
    }

    private static void test03(int[] arr) {
        InsertionSort insertionSort = new InsertionSort(arr);
        Long start = System.currentTimeMillis();
        insertionSort.sort();
        Long end = System.currentTimeMillis();
        System.out.println("插入排序：" + (end - start) + "ms");
    }

    private static void test02(int[] arr) {
        BubbleSort bubbleSort = new BubbleSort(arr);
        Long start = System.currentTimeMillis();
        bubbleSort.sort();
        Long end = System.currentTimeMillis();
        System.out.println("冒泡排序：" + (end - start) + "ms");
    }

    private static void test01(int[] arr) {
        SelectionSort selectionSort = new SelectionSort(arr);
        Long start = System.currentTimeMillis();
        selectionSort.sort();
        Long end = System.currentTimeMillis();
        System.out.println("选择排序：" + (end - start) + "ms");
    }
}