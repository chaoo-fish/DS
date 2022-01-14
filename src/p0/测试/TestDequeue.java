package p0.测试;

import p2.线性结构.ArrayDeque;
import ph.作业.HWArrayDequeue;

import java.util.Iterator;

public class TestDequeue {
    public static void main(String[] args) {
        HWArrayDequeue<Integer> deque = new HWArrayDequeue<Integer>();
//        ArrayDeque<Integer> deque = new ArrayDeque<Integer>();1
        System.out.println(deque);
        for (int i = 0; i < 5; i++) {
            deque.addFirst(i);
            deque.addLast(i);
        }
        System.out.println(deque);

        Iterator<Integer> iterator = deque.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
