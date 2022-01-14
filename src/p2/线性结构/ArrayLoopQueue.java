package p2.线性结构;

import p1.接口.Queue;

import java.util.Iterator;
import java.util.Objects;

// 循环队列
public class ArrayLoopQueue<E> implements Queue<E> {
    // 存储数据的容器
    private E[] data;
    // 队首指针
    private int front;
    // 队尾指针
    private int rear;
    // 元素个数 (f < r  r-f ; r < f  r+l-f)
    private int size;
    // 默认容量
    private static int DEEFAULT_CAPACITY = 10;

    public ArrayLoopQueue() {
        data = (E[]) new Object[DEEFAULT_CAPACITY + 1];
        front = 0;
        rear = 0;
        size = 0;
    }


    @Override
    public void offer(E element) {
        // 满没满
        if ((rear + 1) % data.length == front) {
            resize(data.length * 2 - 1);
        }
        data[rear] = element;
        rear = (rear + 1) % data.length;
        size++;
    }

    @Override
    public E poll() {
        // 空不空
        if (isEmpty()) {
            throw new IllegalArgumentException("queue is null");
        }
        E ret = data[front];
        front = (front + 1) % data.length;
        size--;
        if (size <= (data.length - 1) / 4 && (data.length - 1) > DEEFAULT_CAPACITY) {
            resize(data.length / 2 + 1);
        }
        return ret;
    }

    private void resize(int newLen) {
        E[] newdata = (E[]) new Object[newLen];
        int index = 0;
        for (int i = front; i != rear; i = (i + 1) % data.length) {
            newdata[index++] = data[i];
        }
        data = newdata;
        front = 0;
        rear = index;

    }

    @Override
    public E element() {
        if (isEmpty()) {
            throw new IllegalArgumentException("queue is null");
        }
        return data[front];
    }

    @Override
    public boolean isEmpty() {
        return front == rear;

    }

    @Override
    public void clear() {
        data = (E[]) new Object[DEEFAULT_CAPACITY];
        size = 0;
        front = 0;
        rear = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        if (isEmpty()){
            sb.append(']');
            return sb.toString();
        }
        for (int i = front; i != rear; i = (i + 1) % data.length) {
            {
                sb.append(data[i]);
                if ((i + 1) % data.length == rear) {
                    sb.append(']');
                } else {
                    sb.append(',');
                    sb.append(' ');
                }
            }
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (o instanceof ArrayLoopQueue) {
            ArrayLoopQueue<E> other = (ArrayLoopQueue<E>) o;
            if (size != other.size) {
                return false;
            }
            int i = front;
            int j = other.front;
            while (i != rear) {
                if (!data[i].equals(other.data[j])) {
                    return false;
                }
                i = (i + 1) % data.length;
                j = (j + 1) % other.data.length;
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size);
    }

    @Override
    public Iterator iterator() {
        return null;
    }
    class ArrayLoopQueueIterator implements Iterator<E> {
        private int cur = front;

        @Override
        public boolean hasNext() {
            return cur != rear;
        }

        @Override
        public E next() {
            E ret = data[cur];
            cur = (cur + 1) % data.length;
            return data[cur];
        }
    }
}
