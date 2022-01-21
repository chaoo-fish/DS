package p0.测试;

import p3.链式结构.LinkedList;

import java.util.Comparator;

public class TestLinkedList {
    public static void main(String[] args) {
        //线性表
        LinkedList<Integer> list = new LinkedList<>();
        System.out.println(list);

        for (int i = 1; i <= 6; i++) {
            list.add((int) (Math.random() * 100));
        }
        System.out.println(list);
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        System.out.println(list);

        //栈
        list.push(90);
        System.out.println(list);
        System.out.println(list.pop());

    }
}