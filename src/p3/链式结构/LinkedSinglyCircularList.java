package p3.链式结构;

import p1.接口.List;

import java.util.Comparator;
import java.util.Iterator;

//单向循环链表
public class LinkedSinglyCircularList<E> implements List<E> {

    //定义结点对象
    private class Node {
        E data;     //数据域
        Node next;  //指针域

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

    private Node head;  //头指针
    private Node tail;  //尾指针
    private int size;   //元素的个数

    public LinkedSinglyCircularList() {
        head = null;
        tail = null;
        size = 0;
    }

    public LinkedSinglyCircularList(E[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("arr is null");
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
            throw new IllegalArgumentException("add index out of range");
        }
        Node n = new Node(element);
        if (size == 0) {
            head = n;
            tail = n;
            tail.next = head;   //new code
        } else if (index == 0) {
            n.next = head;
            head = n;
            tail.next = head;   //new code
        } else if (index == size) {
            n.next = tail.next; //new code
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
            throw new IllegalArgumentException("remove index out of range");
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
            tail.next = head; //new code
        } else if (index == size - 1) {
            Node p = head;
            while (p.next != tail) {
                p = p.next;
            }
            ret = tail.data;
            p.next = tail.next; //change code
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
            if (p == head) {    //change code
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

        if (fromIndex < 0 || toIndex >= size || fromIndex > toIndex) {
            throw new IllegalArgumentException("must 0 <= fromIndex <= toIndex <= size - 1");
        }

        LinkedSinglyList<E> list = new LinkedSinglyList<>();
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
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        if (isEmpty()) {
            sb.append(']');
        } else {
            Node p = head;
            while (true) {
                sb.append(p.data);
                if (p == tail) {
                    sb.append(']');
                    break;
                }
                sb.append(',');
                sb.append(' ');
                p = p.next;
            }
        }
        return sb.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedSinglyCircularListIterator();
    }

    class LinkedSinglyCircularListIterator implements Iterator<E> {
        private Node cur = head;
        private boolean flag = true; //是否在第一圈

        @Override
        public boolean hasNext() {
            if (isEmpty()) {
                return false;
            }
            return flag;
        }

        @Override
        public E next() {
            E ret = cur.data;
            cur = cur.next;
            if (cur == head) {
                flag = false;
            }
            return ret;
        }
    }

    //约瑟夫环问题
    public void josephusLoop() {
        if (size <= 2) {
            return;
        }
        Node p = head;
        while (size != 2) {
            p = p.next;
            Node del = p.next;
            if (del == head) {
                head = del.next;
            } else if (del == tail) {
                tail = p;
            }
            p.next = del.next;
            del.next = null;
            p = p.next;
            size--;
        }
    }

    //链表反转的问题
    public void reverse() {
        if (size == 0 || size == 1) {
            return;
        }
        Node dummpyHead = new Node();   //虚拟头结点
        Node p = head;
        for (int i = 0; i < size; i++) {
            Node n = new Node(p.data);
            if (dummpyHead.next == null) {
                tail = n;
            }
            n.next = dummpyHead.next;
            dummpyHead.next = n;
            p = p.next;
        }
        head = dummpyHead.next;
    }
}











