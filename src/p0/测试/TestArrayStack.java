package p0.测试;

import p2.线性结构.ArrayStack;

public class TestArrayStack {
    public static void main(String[] args) {
        ArrayStack a1 = new ArrayStack();
        ArrayStack a2 = new ArrayStack();
        for (int i = 0; i < 10; i++) {
            a1.push(i);
            a2.push(i);
        }
        System.out.println(a1);
        System.out.println(a2);
        System.out.println(a1.equals(a2));

        System.out.println(a1.pop());
        System.out.println(a1);
    }
}
