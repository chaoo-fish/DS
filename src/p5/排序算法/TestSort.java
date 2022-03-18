package p5.排序算法;

import java.util.Arrays;
import java.util.Random;

/*
算法的执行时间
除了跟算法的策略有关系之外
还跟数据分布情况有关系
数据分布情况:
        完全随机    大致有序    大致平稳
选择      5           5          4
冒泡      4           4          5
插入      3           1          3
希尔      2           3          2
归并      1           2          1
单快      1+          3+         1+
双快      1+          1+         1+
三快      n           n          n
基排      3-          4          3-
桶排序    4+          3-          4+
计数排序   4+          3-          4+
*/
public class TestSort {
    public static void main(String[] args) {

        ArrayData data = new ArrayData(0);
        int[] arr = data.makeData();
        test01(arr);
        test02(arr);
        test03(arr);
        test04(arr);
        test05(arr);
        test06(arr);
        test07(arr);
        test08(arr);
        test09(arr);
        test10(arr);
        test11(arr);
        test12(arr);
    }

    private static void test12(int[] arr) {
        HeapSort heapSort = new HeapSort(arr);
        Long start = System.currentTimeMillis();
        heapSort.sort();
        Long end = System.currentTimeMillis();
        System.out.println("最大堆排序:" + (end - start) + "ms");
    }

    private static void test11(int[] arr) {
        CountingSort countingSort = new CountingSort(arr);
        Long start = System.currentTimeMillis();
        countingSort.sort();
        Long end = System.currentTimeMillis();
        System.out.println("计数排序:" + (end - start) + "ms");
    }

    private static void test10(int[] arr) {
        BucketSort bucketSort = new BucketSort(arr);
        Long start = System.currentTimeMillis();
        bucketSort.sort();
        Long end = System.currentTimeMillis();
        System.out.println("桶排序:" + (end - start) + "ms");
    }

    private static void test09(int[] arr) {
        RadixSort radixSort = new RadixSort(arr);
        Long start = System.currentTimeMillis();
        radixSort.sort();
        Long end = System.currentTimeMillis();
        System.out.println("基数排序:" + (end - start) + "ms");
    }

    private static void test08(int[] arr) {
        QuickSort03 quickSort03 = new QuickSort03(arr);
        Long start = System.currentTimeMillis();
        quickSort03.sort();
        Long end = System.currentTimeMillis();
        System.out.println("三路快排:" + (end - start) + "ms");
    }

    private static void test07(int[] arr) {
        QuickSort02 quickSort02 = new QuickSort02(arr);
        Long start = System.currentTimeMillis();
        quickSort02.sort();
        Long end = System.currentTimeMillis();
        System.out.println("双路快排:" + (end - start) + "ms");
    }

    private static void test06(int[] arr) {
        QuickSort01 quickSort01 = new QuickSort01(arr);
        Long start = System.currentTimeMillis();
        quickSort01.sort();
        Long end = System.currentTimeMillis();
        System.out.println("单路快排:" + (end - start) + "ms");
    }

    private static void test05(int[] arr) {
        MergeSort mergeSort = new MergeSort(arr);
        Long start = System.currentTimeMillis();
        mergeSort.sort();
        Long end = System.currentTimeMillis();
        System.out.println("归并排序:" + (end - start) + "ms");
    }

    private static void test04(int[] arr) {
        ShellSort shellSort = new ShellSort(arr);
        Long start = System.currentTimeMillis();
        shellSort.sort();
        Long end = System.currentTimeMillis();
        System.out.println("希尔排序:" + (end - start) + "ms");
    }

    private static void test03(int[] arr) {
        InsertionSort insertionSort = new InsertionSort(arr);
        Long start = System.currentTimeMillis();
        insertionSort.sort();
        Long end = System.currentTimeMillis();
        System.out.println("插入排序:" + (end - start) + "ms");
    }

    private static void test02(int[] arr) {
        BubbleSort bubbleSort = new BubbleSort(arr);
        Long start = System.currentTimeMillis();
        bubbleSort.sort();
        Long end = System.currentTimeMillis();
        System.out.println("冒泡排序:" + (end - start) + "ms");
    }

    private static void test01(int[] arr) {
        SelectionSort selectionSort = new SelectionSort(arr);
        Long start = System.currentTimeMillis();
        selectionSort.sort();
        Long end = System.currentTimeMillis();
        System.out.println("选择排序:" + (end - start) + "ms");
    }
}