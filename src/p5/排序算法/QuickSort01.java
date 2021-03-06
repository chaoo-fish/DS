package p5.排序算法;

// 快排
public class QuickSort01 extends Sort{
    public QuickSort01(int[] arr) {
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
        quickSort(L,p - 1);
        quickSort(p + 1, R);
    }

    private int partition(int L, int R) {
        //优化一下 随机让后面的数字和第一个数字换一下
        //尽量避免极端情况
        swap(L, (int) (Math.random() * (R - L + 1) + L));
        int v = arr[L];

        //arr[l+1 ~ j] < v < arr[j+1 ~ i)
        int j = L; // 记录后面将要把中点换到哪个坐标
        for (int i = L + 1; i <= R; i++) {
            if (arr[i] < v) {
                swap(j + 1, i);
                j++;
            }
        }

        // 最后将 中点值 交换到中间
        swap(L, j);
        return j;
    }
}