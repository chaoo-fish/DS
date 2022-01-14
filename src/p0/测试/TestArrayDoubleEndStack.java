package p0.测试;

import p2.线性结构.ArrayDoubleEndStack;

public class TestArrayDoubleEndStack {
    public static void main(String[] args) {
        ArrayDoubleEndStack<Integer> ad1 = new ArrayDoubleEndStack<>();
        System.out.println(ad1);
        for (int i = 0; i < 10; i++) {
            ad1.pushLeft(i);
        }
        System.out.println(ad1);
        for (int i = 0; i < 10; i++) {
            ad1.pushRight(i);
        }
        System.out.println(ad1);
    }
}
