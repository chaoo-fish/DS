package p1.接口;

public interface Queue<E> extends Iterable<E> {
    public void offer(E element);   //入队
    public E poll();    //出队
    public E element();    //查看队首元素
    public boolean isEmpty();
    public void clear();
    public int size();

}
