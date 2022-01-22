package p5.排序算法;

public class InsertionSort extends Sort{
    public InsertionSort() {
    }

    public InsertionSort(int[] arr) {
        super(arr);
    }

    @Override
    public void sort() {
        for (int i = 0; i < arr.length; i++) {
            int e = arr[i];
            int j = 0;
            for (j = i; j > 0 && arr[j - 1] > e ; j--) {
                arr[j] = arr[j -1]; // 第一次的时候 arr[j] 的值其实是 e ,
             }
            arr[j] = e; // e 最后插入的位置
        }
    }
}
