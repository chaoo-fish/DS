package p6.树与哈希表;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //元素自身的哈希值和元素自身有关 由元素内部的内容来计算的
        //计算的结果一般都是整数
        String s1 = new String("abc");  //['a','b','c']
        String s2 = new String("123");
        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());
        Integer i1 = new Integer(1);
        System.out.println(i1.hashCode());
        Integer i2 = new Integer(2);
        System.out.println(i2.hashCode());
        Integer i3 = new Integer(-10);
        System.out.println(i3.hashCode());
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list.hashCode());
    }
}