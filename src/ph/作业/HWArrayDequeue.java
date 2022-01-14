package ph.作业;

import p1.接口.Stack;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

// 双端队列
public class HWArrayDequeue<E> implements HWDequeue<E>, Stack<E> {
    E[] data;
    private int front;
    private int rear;
    private int size;
    private int DEFAULT_CAPACITY = 10;

    public HWArrayDequeue() {
        data = (E[]) new Object[DEFAULT_CAPACITY + 1];
        front = 0;
        rear = 0;
        size = 0;
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
    public boolean isEmpty() {
        return rear == front;
    }

    @Override
    public void push(E element) {
        addLast(element);
    }

    @Override
    public E pop() {
        return removeLast();
    }

    @Override
    public E peek() {
        return getLast();
    }

    @Override
    public void clear() {
        data = (E[]) new Object[DEFAULT_CAPACITY + 1];
        front = 0;
        rear = 0;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void addFirst(E element) {
        // 判断是否满了
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
        // 判断是否满了
        if ((rear + 1) % data.length == front) {
            resize(data.length * 2 - 1);
        }
        data[rear] = element;
        rear = (rear + 1) % data.length;
        size++;
    }

    @Override
    public E removeFirst() {
        // 判断空
        if (isEmpty()) {
            throw new IllegalArgumentException("dequeue is null");
        }
        E rep = data[front];
        front = (front + 1) % data.length;
        size--;
        if (size < (data.length -1) / 4 && size > DEFAULT_CAPACITY ) {
            resize(data.length/2 + 1);
        }
        return rep;
    }

    @Override
    public E removeLast() {
        // 判断空
        if (isEmpty()) {
            throw new IllegalArgumentException("dequeue is null");
        }
        rear = (rear - 1 + data.length) % data.length;
        E rep = data[rear];
        size--;
        if (size < (data.length -1) / 4 && data.length - 1 > DEFAULT_CAPACITY ) {
            resize(data.length/2 + 1);
        }
        return rep;
    }

    @Override
    public E getFirst() {
        // 判断空
        if (isEmpty()) {
            throw new IllegalArgumentException("dequeue is null");
        }
        E rep = data[front];
        return rep;
    }

    @Override
    public E getLast() {
        // 判断空
        if (isEmpty()) {
            throw new IllegalArgumentException("dequeue is null");
        }
        E rep = data[(rear - 1 + data.length) % data.length];
        return rep;
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
        return new ArrayDequeueIterable();
    }

    class ArrayDequeueIterable implements Iterator<E> {
        private int cur = front;

        @Override
        public boolean hasNext() {
            return cur != rear;
        }

        @Override
        public E next() {
            E req = data[cur];
            cur = (cur + 1) % data.length;
            return req;
        }
    }
}
