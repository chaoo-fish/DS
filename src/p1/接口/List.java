package p1.接口;

import java.util.Comparator;

// 线性表接口定义
public interface List<E> extends Iterable<E> {
    public void add(E element);

    // 在指定角标添加元素
    public void add(int index, E element);

    // 删除指定元素
    public void remove(E element);

    // 删除指定角标处的元素 并返回原先的值
    public E remove(int index);

    // 获取指定角标处的元素
    public E get(int index);

    // 修改角标index处的值为element 并返回原先的值
    public E set(int index, E element);

    // 获取线性表中的元素个数
    public int size();

    // 查看元素第一次出现的角标位置 (从左到右)
    public int indexOf(E element);

    // 判断是否包含元素
    public boolean contains(E element);

    // 判断线性表是否为空
    public boolean isEmpty();

    // 清空线性表
    public void clear();

    // 排序
    public void sort(Comparator<E> c);

    // 获取自线性表 原线性表中[fromIndex,toIndex]
    public List<E> subList(int fromIndex, int toIndex);
}
