package p5.排序算法;

import p3.链式结构.LinkedList;
//基数排序
public class RadixSort extends Sort{
    public RadixSort(int[] arr) {
        super(arr);
    }

    @Override
    public void sort() {
        //1.找 分类-收集 的轮数(最大值的长度)
        int radix = getRadix();
        //2.创建桶 list所有桶的集合 每一个桶是LinkedList当成队列来用
        LinkedList<Integer>[] list = new LinkedList[10];
        for (int i = 0; i < list.length; i++) {
            list[i] = new LinkedList<>();
        }
        //3.开始 分类-收集
        for (int r = 1; r <= radix; r++) {
            //分类过程
            for (int i = 0; i < arr.length; i++) {
                list[getIndex(arr[i], r)].offer(arr[i]);
            }
            int index = 0; //遍历arr原数组
            //收集的过程
            for (int i = 0; i < list.length; i++) {
                while (!list[i].isEmpty()) {
                    arr[index++] = list[i].poll();
                }
            }
        }
    }

    private int getIndex(int num, int r) {
        int ret = 0;
        for (int i = 1; i <= r; i++) {
            ret = num % 10;
            num /= 10;
        }
        return ret;
    }

    private int getRadix() {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return (max + "").length();
    }
}