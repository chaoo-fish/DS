package p5.排序算法;


import java.util.Arrays;

/*
算法的执行时间 除了跟算法的策略有关系外 还跟数据分别情况有关系
数据分布情况:完全随机 大致有序 大致平稳
 */
public class TestSort {
    public static void main(String[] args) {
        ArrayData data = new ArrayData(1);
        int[] arr = data.makeData();
//        test01(arr);
//        test02(arr);
//        test03(arr);
//        test04(arr);
        test05(arr);
    }

    private static void test05(int[] arr) {
        long start = System.currentTimeMillis();
        MergeSort mergeSort = new MergeSort(arr);
        mergeSort.sort();
        long end = System.currentTimeMillis();
        System.out.println("归并排序: " + (end - start) + "ms");
    }


    private static void test04(int[] arr) {
        ShellSort shellSort = new ShellSort(arr);
        long start = System.currentTimeMillis();
        shellSort.sort();
        long end = System.currentTimeMillis();
        System.out.println("希尔排序: " + (end - start) + "ms");
    }

    private static void test03(int[] arr) {
        InsertionSort sort = new InsertionSort(arr);
        long start = System.currentTimeMillis();
        sort.sort();
        long end = System.currentTimeMillis();
        System.out.println("插入排序: " + (end - start) + "ms");
    }

    private static void test02(int[] arr) {
    }

    private static void test01(int[] arr) {
    }
}
