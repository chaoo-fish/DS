package p5.排序算法;

//冒泡排序 O(n^2) S(1) 稳定
public class BubbleSort extends Sort{
    public BubbleSort(int[] arr) {
        super(arr);
    }
    @Override
    public void sort() {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(j, j + 1);
                }
            }
        }
    }
}