package p1.接口;
//集合接口- 底层链表实现 底层BST实现 底层哈希表
public interface Set<E> extends Iterable<E> {
    public void add(E e);
    public void remove(E e);
    public boolean contains(E e);
    public int size();
    public boolean isEmpty();
}
