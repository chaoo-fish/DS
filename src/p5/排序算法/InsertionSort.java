package p5.排序算法;

//插入排序 O(n^2) S(1) 稳定
public class InsertionSort extends Sort {
    public InsertionSort(int[] arr) {
        super(arr);
    }
    /*
        这个可以类似于小朋友排队，我们先默认第一个是有序的
        然后开始将第二个小朋友依次和前面的小朋友进行比较，如果小于前面的就交换，大于就不动
     */
    @Override
    public void sort() {
        for (int i = 1; i < arr.length; i++) {
            int e = arr[i]; // 选中要和前面比较的小盆友
            int j = 0;
            for (j = i; j > 0 && arr[j - 1] > e; j--) { // 从有序的队伍后面向前比较
                arr[j] = arr[j - 1]; // 如果前面的比他大,就把前面的向后移动一位
            }
            arr[j] = e;// 最终插入该插入的位置

        }
    }
}