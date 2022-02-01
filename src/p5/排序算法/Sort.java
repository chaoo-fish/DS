package p5.排序算法;

// 排序类,其它排序方法需要继承该类
public abstract class Sort {

    public int[] arr;

    public Sort() {
    }

    public Sort(int[] arr) {
        this.arr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            this.arr[i] = arr[i];
        }
    }

    public abstract void sort();

    public void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
