package p2.线性结构;

import p1.接口.Queue;

import java.util.Iterator;

//栈实现队列
public class StackToQueue {
    public static void main(String[] args) {
        QueueImplByStack<Integer> queue = new QueueImplByStack<>();
        for (int i = 1; i <= 5; i++) {
            queue.offer(i);
        }
        System.out.println(queue);
        System.out.println(queue.poll());
        System.out.println(queue);
    }
}

class QueueImplByStack<E> implements Queue<E> {
    private ArrayStack<E> stackA;
    private ArrayStack<E> stackB;
    public QueueImplByStack() {
        stackA = new ArrayStack<>();
        stackB = new ArrayStack<>();
    }

    @Override
    public void offer(E element) {
        stackA.push(element);
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            throw new IllegalArgumentException("queue is null");
        }
        while (stackA.size() != 1) {
            stackB.push(stackA.pop());
        }
        E ret = stackA.pop();

        while (!stackB.isEmpty()) {
            stackA.push(stackB.pop());
        }
        return ret;
    }

    @Override
    public E element() {
        if (isEmpty()) {
            throw new IllegalArgumentException("queue is null");
        }
        while (stackA.size() != 1) {
            stackB.push(stackA.pop());
        }
        E ret = stackA.peek();

        while (!stackB.isEmpty()) {
            stackA.push(stackB.pop());
        }
        return ret;
    }

    @Override
    public boolean isEmpty() {
        return stackA.isEmpty();
    }

    @Override
    public void clear() {
        stackA.clear();
    }

    @Override
    public int size() {
        return stackA.size();
    }

    @Override
    public Iterator<E> iterator() {
        return stackA.iterator();
    }

    @Override
    public String toString() {
        return stackA.toString();
    }
}