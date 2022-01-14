package p2.线性结构;

import p1.接口.Queue;

import java.util.Iterator;

public class ArrayQueue<E> implements Queue<E> {
    private ArrayList<E> list;

    public ArrayQueue() {
        list = new ArrayList<>();
    }

    @Override
    public void offer(E element) {
        list.add(list.size(), element);
    }

    @Override
    public E poll() {
        return list.remove(0);
    }

    @Override
    public E element() {
        return list.get(0);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    @Override
    public String toString() {
        return list.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (o instanceof ArrayQueue) {
            ArrayQueue other = (ArrayQueue) o;
            return list.equals(other.list);
        }
        return false;
    }
}
