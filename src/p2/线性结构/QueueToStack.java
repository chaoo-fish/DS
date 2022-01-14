package p2.线性结构;

import p1.接口.Stack;

import java.util.Iterator;

//队列实现栈
public class QueueToStack {
    public static void main(String[] args) {
        StackImplByQueue<Integer> stack = new StackImplByQueue<>();
        System.out.println(stack);
        for (int i = 0; i <= 5; i++) {
            stack.push(i); // 队列A
        }
        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack);
    }
}

class StackImplByQueue<E> implements Stack<E> {
    private ArrayQueue<E> queueA;
    private ArrayQueue<E> queueB;

    public StackImplByQueue() {
        queueA = new ArrayQueue();
        queueB = new ArrayQueue();
    }

    @Override
    public int size() {
        if (queueA.isEmpty() && queueB.isEmpty()) {
            return 0;
        } else if (!queueA.isEmpty()) {
            return queueA.size();
        } else {
            return queueB.size();
        }
//        return queueA.size() + queueB.size();
    }

    @Override
    public boolean isEmpty() {
        return queueA.isEmpty() && queueB.isEmpty();
    }

    @Override
    public void push(E element) {
        if (isEmpty()) {
            queueA.offer(element);
        } else if (!queueA.isEmpty()) {
            queueA.offer(element);
        } else {
            queueB.offer(element);
        }
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E ret = null;
        if (!queueA.isEmpty()) {
            while (queueA.size() != 1) {
                queueB.offer(queueA.poll());
            }
            ret = queueA.poll();
        } else {
            while (queueB.size() != 1) {
                queueA.offer(queueB.poll());
            }
            ret = queueB.poll();
        }
        return ret;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        E ret = null;
        if (!queueA.isEmpty()) {
            while (queueA.size() != 1) {
                queueB.offer(queueA.poll());
            }
            ret = queueA.poll();
            queueB.offer(ret);
        } else {
            while (queueB.size() != 1) {
                queueA.offer(queueB.poll());
            }
            ret = queueB.poll();
            queueA.offer(ret);
        }
        return ret;
    }

    @Override
    public void clear() {
        queueA.clear();
        queueB.clear();
    }

    @Override
    public Iterator<E> iterator() {
        if (isEmpty()) {
            return queueA.iterator();
        } else if (!queueA.isEmpty()) {
            return queueA.iterator();
        } else {
            return queueB.iterator();

        }
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        } else if (!queueA.isEmpty()) {
            return queueA.toString();
        } else {
            return queueB.toString();
        }
    }
}