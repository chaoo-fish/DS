package p5.排序算法;

import p2.线性结构.ArrayList;

import java.util.Comparator;

// 桶排序
public class BucketSort extends Sort{
    public BucketSort(int[] arr) {
        super(arr);
    }

    @Override
    public void sort() {
        //1.找到最大值和最小值
        int max = arr[0];
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        //2.确定桶的个数并创建桶
        int bucketNum = (max - min) / arr.length + 1;
        ArrayList<Integer> list[] = new ArrayList[bucketNum];
        for (int i = 0; i < list.length; i++) {
            list[i] = new ArrayList<>();
        }
        //3.遍历源数据 将数据进行分类处理
        for (int i = 0; i < arr.length; i++) {
            list[(arr[i] - min) / arr.length].add(arr[i]);
        }
        //4.对每一个桶进行排序
        for (int i = 0; i < list.length; i++) {
            list[i].sort(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            });
//            System.out.println("第" + (i+1) + "个桶:" + list[i].toString());
        }
        //5.将所有桶中的数据依次返回到原数组中即可
        int index = 0;  //原数组的角标
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list[i].size(); j++) {
                arr[index++] = list[i].get(j);
            }
        }
//        System.out.println(Arrays.toString(arr));
    }
}