package p1.接口;

public interface Dequeue<E> extends Queue<E> {
    public void addFirst(E element);
    public void addLast(E element);
    public E removeFirst();
    public E reomveLast();
    public E getFirst();
    public E getLast();
}