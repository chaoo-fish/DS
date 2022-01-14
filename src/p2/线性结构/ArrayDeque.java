package p2.线性结构;

import p1.接口.Dequeue;
import p1.接口.Stack;

import java.util.Arrays;
import java.util.Iterator;

// 双端队列
public class ArrayDeque<E> implements Dequeue<E>, Stack<E> {
    private E[] data;
    private int front;
    private int rear;
    private int size;
    private static int DEFAULT_CAPACITY = 10;

    public ArrayDeque() {
        data = (E[]) new Object[DEFAULT_CAPACITY + 1];
        front = 0;
        rear = 0;
        size = 0;
    }

    @Override
    public void addFirst(E element) {
        if ((rear + 1) % data.length == front) {
            resize(data.length * 2 - 1);
        }
        front = (front - 1 + data.length) % data.length;
        data[front] = element;
        size++;
    }

    private void resize(int newLen) {
        E[] newData = (E[]) new Object[newLen];
        int index = 0;
        for (int i = front; i != rear; i = (i + 1) % data.length) {
            newData[index++] = data[i];
        }
        data = newData;
        front = 0;
        rear = index;
    }

    @Override
    public void addLast(E element) {
        if ((rear + 1) % data.length == front) {
            resize(data.length * 2 - 1);
        }
        data[rear] = element;
        rear = (rear + 1) % data.length;
        size++;
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            throw new IllegalArgumentException("queue is null");
        }
        E ret = data[front];
        front = (front + 1) % data.length;
        size--;
        if (size <= (data.length - 1) / 4 && data.length - 1 > DEFAULT_CAPACITY) {
            resize(data.length / 2 + 1);
        }
        return ret;
    }

    @Override
    public E reomveLast() {
        if (isEmpty()) {
            throw new IllegalArgumentException("queue is null");
        }
        rear = (rear - 1 + data.length) % data.length;
        E ret = data[rear];
        size--;
        if (size <= (data.length - 1) / 4 && data.length - 1 > DEFAULT_CAPACITY) {
            resize(data.length / 2 + 1);
        }
        return ret;
    }

    @Override
    public E getFirst() {
        if (isEmpty()) {
            throw new IllegalArgumentException("queue is null");
        }
        return data[front];
    }

    @Override
    public E getLast() {
        if (isEmpty()) {
            throw new IllegalArgumentException("queue is null");
        }
        return data[(rear - 1 + data.length) % data.length];
    }

    @Override
    public void offer(E element) {
        addLast(element);
    }

    @Override
    public E poll() {
        return removeFirst();
    }

    @Override
    public E element() {
        return getFirst();
    }

    @Override
    public E peek() {
        return getLast();
    }

    @Override
    public boolean isEmpty() {
        return size == 0 && front == rear;
    }

    @Override
    public void push(E element) {
        addLast(element);
    }

    @Override
    public E pop() {
        return reomveLast();
    }

    @Override
    public void clear() {
        E[] data = (E[]) new Object[DEFAULT_CAPACITY];
        front = 0;
        rear = 0;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        if (isEmpty()) {
            sb.append(']');
            return sb.toString();
        }
        for (int i = front; i != rear; i = (i + 1) % data.length) {
            sb.append(data[i]);
            if ((i + 1) % data.length == rear) {
                sb.append(']');
            } else {
                sb.append(',');
                sb.append(' ');
            }
        }
        return sb.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayDequeIterator();
    }

    class ArrayDequeIterator implements Iterator<E> {
        private int cur = front;

        @Override
        public boolean hasNext() {
            return cur != rear;
        }

        @Override
        public E next() {
            E ret = data[cur];
            cur = (cur + 1) % data.length;
            return ret;
        }
    }
}