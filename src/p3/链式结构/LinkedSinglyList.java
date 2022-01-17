package p3.链式结构;

import p1.接口.List;

import java.util.Comparator;
import java.util.Iterator;

// 单向链表
public class LinkedSinglyList<E> implements List<E> {

    // 定义节点对象
    private class Node {
        E data;
        Node next;

        public Node() {
            this(null, null);
        }

        public Node(E data) {
            this(data, null);
        }

        public Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    private Node head; // 头指针
    private Node tail; // 尾指针
    private int size; // 元素的个数

    public LinkedSinglyList() {
        head = null;
        tail = null;
        size = 0;
    }

    public LinkedSinglyList(E[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("arr is null!");
        }
        for (int i = 0; i < arr.length; i++) {
            add(arr[i]);
        }

    }

    @Override
    public void add(E element) {
        add(size, element);
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException();
        }
        Node n = new Node(element);

        if (size == 0) {
            head = n;
            tail = n;
        } else if (index == 0) {
            n.next = head;
            head = n;
        } else if (index == size) {
            tail.next = n;
            tail = n;
        } else {
            Node p = head;
            for (int i = 0; i < index - 1; i++) {
                p = p.next;
            }
            n.next = p.next;
            p.next = n;
        }
        size++;
    }

    @Override
    public void remove(E element) {
        int index = indexOf(element);
        if (index != -1) {
            remove(index);
        }
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        }
        E ret = null;
        if (size == 1) {
            ret = head.data;
            head = null;
            tail = null;
        } else if (index == 0) {
            Node n = head;
            ret = n.data;
            head = n.next;
            n.next = null;
        } else if (index == size - 1) {
            Node p = head;
            while (p.next != tail) {
                p = p.next;
            }
            ret = tail.data;
            p.next = null;
            tail = p;
        } else {
            Node p = head;
            for (int i = 0; i < index - 1; i++) {
                p = p.next;
            }
            Node n = p.next;
            ret = n.data;
            p.next = n.next;
            n.next = null;
        }
        size--;
        return ret;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("get index out of range");
        }
        if (index == 0) {
            return head.data;
        } else if (index == size - 1) {
            return tail.data;
        } else {
            Node p = head;
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
            return p.data;
        }
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("get index out of range");
        }
        E ret = null;
        if (index == 0) {
            ret = head.data;
            head.data = element;
        } else if (index == size - 1) {
            ret = tail.data;
            tail.data = element;
        } else {
            Node p = head;
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
            ret = p.data;
            p.data = element;
        }
        return ret;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int indexOf(E element) {
        Node p = head;
        int index = 0;
        while (!p.data.equals(element)) {
            p = p.next;
            index++;
            if (p == null) {
                return -1;
            }
        }
        return index;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0 && head == null && tail == null;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void sort(Comparator<E> c) {
        if (c == null) {
            throw new IllegalArgumentException("comparator can not be null");
        }
        //此处的插入排序O(n^3)
        /*
        for (int i = 1; i < size; i++) {
            E e = get(i);
            int j = 0;
            for (j = i; j > 0 && c.compare(get(j - 1), e) > 0; j--) {
                set(j, get(j - 1));
            }
            set(j, e);
        }
        */
        if (size == 0 || size == 1) {
            return;
        }

        Node nodeA = head;
        Node nodeB = nodeA.next;
        while (true) {
            while (true) {
                if (c.compare(nodeA.data, nodeB.data) > 0) {
                    swap(nodeA, nodeB);
                }
                if (nodeB == tail) {
                    break;
                }
                nodeB = nodeB.next;
            }
            if (nodeA.next == tail) {
                break;
            }
            nodeA = nodeA.next;
            nodeB = nodeA.next;
        }
    }

    private void swap(Node nodeA, Node nodeB) {
        E temp = nodeA.data;
        nodeA.data = nodeB.data;
        nodeB.data = temp;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        //0 <= fromIndex <= toIndex <= size - 1    [fromIndex,toIndex]
        if (fromIndex < 0 || toIndex >= size || fromIndex > toIndex) {
            throw new IllegalArgumentException("must 0 <= fromIndex <= toIndex <= size - 1");
        }

        LinkedSinglyList<E> list = new LinkedSinglyList<>();
        /*
        for (int i = fromIndex; i <= toIndex; i++) {    //O(M)
            list.add(get(i));   //O(N) * O(M) = O(NM) ~ O(N^2)
        }
        */
        Node nodeA = head;
        for (int i = 0; i < fromIndex; i++) {
            nodeA = nodeA.next;
        }
        Node nodeB = head;
        for (int i = 0; i < toIndex; i++) {
            nodeB = nodeB.next;
        }
        Node p = nodeA;
        while (true) {
            list.add(p.data);
            if (p == nodeB) {
                break;
            }
            p = p.next;
        }
        return list;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedSinglyListIterator();
    }

    class LinkedSinglyListIterator implements Iterator<E> {
        private Node cur = head;

        @Override
        public boolean hasNext() {
            return cur != null;
        }
        @Override
        public E next() {
            E ret = cur.data;
            cur = cur.next;
            return ret;
        }
    }
}
