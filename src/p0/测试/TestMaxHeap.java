package p0.测试;

import p6.树与哈希表.MaxHeap;

public class TestMaxHeap {
    public static void main(String[] args) {
        MaxHeap<Integer> heap = new MaxHeap<>();
        heap.add(3);
        heap.add(5);
        heap.add(1);
        heap.add(2);
        heap.add(6);
        heap.add(4);
        heap.add(7);
        System.out.println(heap);
        System.out.println(heap.extractMax());
        System.out.println(heap);
    }
}