package p2.线性结构;

import p1.接口.Stack;

import java.util.Iterator;
import java.util.Objects;

public class ArrayStack<E> implements Stack<E> {
    private ArrayList<E> list;

    public ArrayStack() {
        list = new ArrayList<>();
    }

    public ArrayStack(int capacity) {
        list = new ArrayList<>(capacity);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(E element) {
        list.add(element);
    }

    @Override
    public E pop() {
        return list.remove(list.size() - 1);
    }

    @Override
    public E peek() {
        return list.get(list.size() - 1);
    }

    @Override
    public void clear() {
        list.clear();
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
        if (o instanceof ArrayStack) {
            ArrayStack other = (ArrayStack) o;
            return this.list.equals(other.list);
        }
        return false;
    }


}
