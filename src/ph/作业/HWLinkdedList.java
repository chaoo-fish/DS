package ph.作业;

import p1.接口.List;
import p1.接口.Queue;
import p1.接口.Stack;

import java.util.Comparator;
import java.util.Iterator;

// 循环双端链表
public class HWLinkdedList<E> implements List<E>, Stack<E>, Queue<E> {
    public class Node {
        E data;
        Node pre;
        Node next;

        public Node() {
            this(null, null, null);
        }

        public Node(E data) {
            this(data, null, null);
        }

        public Node(E data, Node pre, Node next) {
            this.data = data;
            this.pre = pre;
            this.next = next;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public HWLinkdedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public HWLinkdedList(E[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("arr is null");
        }
        for (E e : arr) {
            add(e);
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
            tail.next = head;
            head.pre = tail;
        } else if (index == 0) {
            n.pre = head.pre;
            n.next = head;
            head.pre = n;
            head = n;
            tail.next = head;
        } else if (index == size) {
            n.next = tail.next;
            tail.next = n;
            n.pre = tail;
            tail = n;
            head.pre = tail;
        } else {
            Node p, q;
            if (index <= size / 2) {
                p = head;
                for (int i = 0; i < index - 1; i++) {
                    p = p.next;
                }
                q = p.next;
                p.next = n;
                n.pre = p;

                q.pre = n;
                n.next = q;
            } else {
                p = tail; //  7  8  9   0 1 2   idx1 l3
                for (int i = size - 1; i > index; i--) {
                    p = p.pre;
                }
                q = p.pre;
                q.next = n;
                n.pre = q;
                ;

                n.next = p;
                p.pre = n;
            }
        }
        size++;
    }

    @Override
    public void remove(E element) {
        int index = indexOf(element);
        if (index != -1) {
            remove(index);
        } else {
            throw new IllegalArgumentException("remove index out of range");
        }
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("remove index out of range");
        }
        E ret = null;
        Node node;
        if (size == 1) {
            ret = head.data;
            head = null;
            tail = null;
        } else if (index == 0) {
            ret = head.data;
            node = head.next;
            head.next = null;
            node.pre = head.pre;
            head.pre = null;
            head = node;
            tail.next = head;
        } else if (index == size - 1) {
            ret = tail.data;
            node = tail.pre;
            tail.pre = null;
            node.next = tail.next;
            tail.next = null;
            tail = node;
            head.pre = tail;
        } else {
            Node p, q, r;
            if (index <= size / 2) {
                p = head;
                for (int i = 0; i < index - 1; i++) {
                    p = p.next;
                }
                q = p.next;
                ret = q.data;
                r = q.next;
                p.next = r;
                r.pre = p;
                q.next = null;
                q.pre = null;
            } else {
                p = tail;
                for (int i = size - 1; i > index + 1; i--) {
                    p = p.pre;
                }
                q = p.pre;
                r = q.pre;
                ret = q.data;
                r.next = p;
                p.pre = r;
                q.next = null;
                q.pre = null;
            }
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
            throw new IllegalArgumentException("set index out of range");
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
            if (p == head) {
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
    public void clear() {
        size = 0;
        head = null;
        tail = null;
    }

    @Override
    public void sort(Comparator<E> c) {
        if (c == null) {
            throw new IllegalArgumentException("null");
        }
        // 插入排序
        if (size == 0 || size == 1) {
            return;
        }
        for (Node nodeA = head.next; nodeA != head; nodeA = nodeA.next) {
            E e = nodeA.data;
            Node nodeB;
            Node nodeC;
            for (nodeB = nodeA, nodeC = nodeB.pre; nodeC != tail && c.compare(nodeC.data, e) > 0; nodeB = nodeB.next, nodeC = nodeC.next) {
                nodeB.data = nodeC.data;
            }
            nodeB.data = e;
        }
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex >= size || fromIndex > toIndex) {
            throw new IllegalArgumentException("sublist");
        }
        Node nodeA = head;
        for (int i = 0; i < fromIndex; i++) {
            nodeA = nodeA.next;
        }

        Node nodeB = head;
        for (int i = 0; i < fromIndex; i++) {
            nodeB = nodeB.next;
        }

        Node p = nodeA;
        HWLinkdedList<E> list = new HWLinkdedList<>();
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
                } else {
                    sb.append(',');
                    sb.append(' ');
                    p = p.next;
                }
            }
        }
        return sb.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
    class HWLinkedListIterator implements Iterator<E> {
        private  Node cur = head;
        private  boolean flag = true;

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

    @Override
    public void offer(E element) {
        add(element);
    }

    @Override
    public E poll() {
        return remove(0);
    }

    @Override
    public E element() {
        return get(0);
    }

    @Override
    public boolean isEmpty() {
        return size == 0 && head == null && tail == null;
    }

    @Override
    public void push(E element) {
        add(element);
    }

    @Override
    public E pop() {
        return remove(size-1);
    }

    @Override
    public E peek() {
        return get(size - 1);
    }

}
