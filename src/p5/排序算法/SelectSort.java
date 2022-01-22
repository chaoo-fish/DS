package p5.排序算法;

// 选择排序  O(n^2) S(1) 不稳定
public class SelectSort extends Sort {

    public SelectSort(int[] arr) {
        super(arr);
    }

    @Override
    public void sort() {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    swap(i,j);
                }
            }

        }
    }
}
