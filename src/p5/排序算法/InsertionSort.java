package p5.排序算法;

//插入排序 O(n^2) S(1) 稳定
public class InsertionSort extends Sort {
    public InsertionSort(int[] arr) {
        super(arr);
    }

    @Override
    public void sort() {
        for (int i = 1; i < arr.length; i++) {
            int e = arr[i];
            int j = 0;
            for (j = i; j > 0 && arr[j - 1] > e; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = e;
        }
    }
}