package p0.测试;

import p2.线性结构.ArrayList;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;

public class TestArrayList {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        System.out.println(list);
        Random random = new Random();

        // 插入数据
        for (int i = 0; i < 10; i++) {
            list.add(random.nextInt(100));
        }
        System.out.println(list);

        // 在 0 位置插入元素
//        for (int i = 0; i < 10; i++) {
//            list.add(0, i);
//        }
//        System.out.println(list);

        // 排序
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2; // 从小到大
            }
        });
        System.out.println(list);

        // for each
//        for (Integer num :
//                list) {
//            System.out.println(num);
//        }

//        Iterator<Integer> iterator = list.iterator();
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next() + " ");
//        }
    }
}