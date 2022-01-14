package p2.线性结构;

import java.util.Iterator;

// 双端栈
public class ArrayDoubleEndStack<E> implements Iterable<E> {
    // 左端栈的栈顶
    private int ltop;
    // 右边栈的栈顶
    private int rtop;
    // 存储元素的容器
    private E[] data;
    // 数组容器的默认容量
    private static int DEFAULT_CAPACITY = 10;

    public ArrayDoubleEndStack() {
        data = (E[]) new Object[DEFAULT_CAPACITY];
        ltop = -1;
        rtop = data.length;
    }

    public void pushLeft(E element) {
        if (ltop + 1 == rtop) {
            resize(data.length * 2);
        }
        data[++ltop] = element;
    }

    public void pushRight(E element) {
        if (ltop + 1 == rtop) {
            resize(data.length * 2);
        }
        data[--rtop] = element;
    }

    private void resize(int newLen) {
        E[] newData = (E[]) new Object[newLen];
        // 复制左端栈的元素
        for (int i = 0; i <= ltop; i++) {
            newData[i] = data[i];
        }
        // 复制右端栈的元素
        int index = rtop;
        for (int i = newLen - sizeRight(); i < newLen; i++) {
            newData[i] = data[index++];
        }
        rtop = newLen - sizeRight();
        data = newData;
    }

    public E popLeft() {
        if (isLeftEmpty()) {
            throw new IllegalArgumentException("left stack is null");
        }
        E ret = data[ltop--];
        if (sizeLeft() + sizeRight() <= data.length / 4 && data.length > DEFAULT_CAPACITY) {
            resize(data.length / 2);
        }
        return ret;
    }

    public E popRight() {
        if (isRightEmpty()) {
            throw new IllegalArgumentException("right stack is null");
        }
        E ret = data[rtop++];
        if (sizeLeft() + sizeRight() <= data.length / 4 && data.length > DEFAULT_CAPACITY) {
            resize(data.length / 2);
        }
        return ret;
    }

    public E peekLeft() {
        if (isLeftEmpty()) {
            throw new IllegalArgumentException("left stack is null");
        }
        return data[ltop];
    }

    public E peekRight() {
        if (isRightEmpty()) {
            throw new IllegalArgumentException("right stack is null");
        }
        return data[rtop];
    }

    public boolean isLeftEmpty() {
        return ltop == -1;
    }

    public boolean isRightEmpty() {
        return rtop == data.length;
    }

    public int sizeLeft() {
        return ltop + 1;
    }

    public int sizeRight() {
        return data.length - rtop;
    }

    @Override
    public String toString() {
        // 1 2 3       7 8 9
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        if (isLeftEmpty() && isRightEmpty()) {
            sb.append(']');
            return sb.toString();
        }
        //先搞左边
        for (int i = 0; i <= ltop; i++) {
            sb.append(data[i]);
            if (i == ltop && isRightEmpty()) {
                sb.append(']');
                return sb.toString();
            } else {
                sb.append(',');
            }
        }
        //后搞右边
        for (int i = rtop; i < data.length; i++) {
            sb.append(data[i]);
            if (i == data.length - 1) {
                sb.append(']');
            } else {
                sb.append(',');
            }
        }
        return sb.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayDoubleEndStackIterator();
    }

    class ArrayDoubleEndStackIterator implements Iterator<E> {
        private ArrayList<E> list;
        private Iterator<E> it;

        public ArrayDoubleEndStackIterator() {
            list = new ArrayList<>();
            for (int i = 0; i <= ltop; i++) {
                list.add(data[i]);
            }
            for (int i = rtop; i < data.length; i++) {
                list.add(data[i]);
            }
            it = list.iterator();
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public E next() {
            return it.next();
        }
    }
}
