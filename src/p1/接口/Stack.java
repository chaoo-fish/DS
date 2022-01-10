package p1.接口;

public interface Stack<E> extends Iterable<E> {
    public int size();

    public boolean isEmpty();

    // 入栈 进栈一个元素 在线性表的表尾添加一个元素
    public void  push(E element);

    // 出栈 弹出一个元素 在线性表的表尾删除一个元素
    public E pop();

    // 查看当前栈顶元素 并不是移除 查看线性表中最后一个元素
    public E peek();

    public void clear();
}
