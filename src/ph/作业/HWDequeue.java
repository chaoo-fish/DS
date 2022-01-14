package ph.作业;

import p1.接口.Queue;

public interface HWDequeue<E> extends Queue<E> {
    public void  addFirst(E element);
    public void  addLast(E element);
    public E removeFirst();
    public E removeLast();
    public E getFirst();
    public E getLast();
}
