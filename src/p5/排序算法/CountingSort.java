package p5.排序算法;

import java.util.Arrays;

//计数排序
public class CountingSort extends Sort{
    public CountingSort(int[] arr) {
        super(arr);
    }
    @Override
    public void sort() {
        //1.找最大值和最小值
        int max = arr[0];
        int min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max){
                max = arr[i];
            }
            if (arr[i] < min){
                min = arr[i];
            }
        }

        //2.确定桶的长度 确定 数据->桶角标的转换关系 和 桶角标->数据的转换关系
        //创建桶（桶专门用于统计数据出现的次数）
        int[] counts = new int[max - min + 1];

        //3.遍历原数组arr 将每个数据出现的次数统计在桶里
        for (int i = 0; i < arr.length; i++) {
            counts[arr[i] - min]++;
        }

        //4.遍历桶 按照从左到右的顺序 将每个数字按照出现的次数 依次回填给arr
        int k = 0;
        for (int index = 0; index < counts.length; index++) {
            for (int count = 0; count < counts[index]; count++) {
                int num = index + min;
                arr[k++] = num;
            }
        }

//        System.out.println(Arrays.toString(arr));
    }
}