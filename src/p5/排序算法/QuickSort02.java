package p5.排序算法;

// 双路快排
public class QuickSort02 extends Sort {
    public QuickSort02(int[] arr) {
        super(arr);
    }

    @Override
    public void sort() {
        quickSort(0, arr.length - 1);
    }

    private void quickSort(int L, int R) {
        if (L >= R) {
            return;
        }
        //先对数组进行划分 并返回划分后的中点
        int p = partition(L, R);
        quickSort(L, p - 1);
        quickSort(p + 1, R);
    }

    private int partition(int L, int R) {
        //优化一下 随机让后面的数字和第一个数字换一下
        //尽量避免极端情况
        swap(L, (int) (Math.random() * (R - L + 1) + L));
        int v = arr[L];
        int i = L + 1; // 左指针
        int j = R; // 右指针
        while (true) {
            while (i <= R && arr[i] < v) {
                i++;
            }
            while (j >= L + 1 && arr[j] > v) {
                j--;
            }
            if (i > j) {
                break;
            }
            swap(i, j);
            i++;
            j--;
        }
        swap(L, j);
        return j;
    }
}