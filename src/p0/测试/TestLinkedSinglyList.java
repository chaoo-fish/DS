package p0.测试;


import p3.链式结构.LinkedSinglyCircularList;

import java.util.Comparator;

public class TestLinkedSinglyList {
    public static void main(String[] args) {
//        LinkedSinglyList<Integer> list = new LinkedSinglyList<>();
        LinkedSinglyCircularList<Integer> list = new LinkedSinglyCircularList<>();
        for (int i = 1; i <= 10; i++) {
            list.add((int) (Math.random() * 100));
        }
        System.out.println(list);
        list.reverse();
        System.out.println(list);
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        System.out.println(list);

        list.clear();
        for (int i = 1; i <= 41; i++) {
            list.add(i);
        }
        //从单向循环链表的内部来处理 处理结点与结点之间的关系
        //list.josephusLoop();

        //从单向循环链表的外部来处理 处理就是角标之间的关系
        /*
        1 2 5
        0 1 2
          i
        */
        int index = 0;
        while (list.size() != 2) {
            index = (index + 2) % list.size();
            list.remove(index);
        }
        System.out.println(list);
        //拉丁方阵的问题 ???
    }
}
