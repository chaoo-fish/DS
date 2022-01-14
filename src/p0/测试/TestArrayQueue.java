package p0.测试;

import p2.线性结构.ArrayQueue;

import java.util.Iterator;

public class TestArrayQueue {
    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.offer(i);
        }
        System.out.println(queue);
        for (int i = 0; i < 3; i++) {
            System.out.println(queue.poll());
        }
        System.out.println(queue);

        Iterator<Integer> iterator = queue.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
