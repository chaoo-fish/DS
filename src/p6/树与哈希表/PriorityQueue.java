package p6.树与哈希表;

import p1.接口.Queue;

import java.util.Iterator;
//优先队列 - 最大堆实现 - 线性表实现 - 数组实现
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {
    private MaxHeap<E> heap;

    public PriorityQueue() {
        heap = new MaxHeap<>();
    }

    @Override
    public void offer(E element) {
        heap.add(element);
    }

    @Override
    public E poll() {
        return heap.extractMax();
    }

    @Override
    public E element() {
        return heap.findMax();
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public void clear() {
        heap.clear();
    }

    @Override
    public int size() {
        return heap.size();
    }

    @Override
    public Iterator<E> iterator() {
        return heap.iterator();
    }

    @Override
    public String toString() {
        return heap.toString();
    }
}