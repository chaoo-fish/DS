package p5.排序算法;

// 堆排序
public class HeapSort extends Sort {
    public HeapSort(int[] arr) {
        super(arr);
    }

    @Override
    public void sort() {
        // 1.对原数组进行最大堆化
        heapfiy(arr);
        int len = arr.length;
        // 2.将每一个范围的最大值放到该范围的后面即可
        for (int i = arr.length - 1; i >= 0; i--) {
            swap(0,i);
            len--;
            siftDown(0,len);
        }
    }

    private void heapfiy(int[] arr) {
        // 从最后一个元素开始 将每一个元素做下沉处理
        for (int i = arr.length - 1; i >= 0; i--) {
            siftDown(i, arr.length);
        }
    }

    //元素下沉的操作
    private void siftDown(int k, int len) {
        //如果没有左孩子 同时也就没有右孩子 就不用下沉
        //如果有左孩子 右孩子不一定存在 判断右孩子的存在性
        //如果右孩子存在 取左右两个孩子的最大值和k对应的值比较
        //如果右孩子不存在 只能取左孩子的值和k对应的值比较
        //如果k对应的值比左右两个孩子都大 则不用下沉 否则下沉即可
        while (leftChild(k) < len) {
            // 因为是先左后右,所以先默认j为/左孩子
            int j = leftChild(k);
            // 存在右孩子的情况下,将左右孩子进行比较,取最大的那个
            if (j + 1 < len && arr[j + 1] > arr[j]) {
                j = rightChild(k);
            }
            // 如果当前值小于 j角标的值 则进行交换,交换完成后 k 的值更新为 j 的值
            if (arr[k] < arr[j]) {
                swap(k, j);
                k = j;
            } else {
                break;  //当前比左右两个孩子都大 不用下沉
            }
        }
    }

    //获取左孩子的角标
    private int leftChild(int index) {
        return 2 * index + 1;
    }

    //获取右孩子的角标
    private int rightChild(int index) {
        return 2 * index + 2;
    }
}
