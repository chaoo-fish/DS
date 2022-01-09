package p2.线性结构;

import p1.接口.List;

import java.util.Comparator;
import java.util.Iterator;


// 自定义的线性表的顺序存储方式
public class ArrayList<E> implements List<E> {
    // 数组容器
    private E[] data;

    // 元素的个数    size == 0 线性表为空 size == data.length 线性表满了
    // size 新元素默认尾部添加时要去的角标
    private int size;

    // 默认容量
    private static int DEFAULT_CAPACITY = 10;

    // 默认构造函数:创建一个默认容量为10的线性表
    public ArrayList() {
        data = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    // 指定默认容量的构造函数:创建一个指定容量的线性表
    public ArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must > 10");// 非法参数异常
        }
        DEFAULT_CAPACITY = capacity;
        data = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    // 指定数组的构造函数:传入一个数组 将该数组封装成一个线性表
    public ArrayList(E[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("arr can not be null");
        }
        data = (E[]) new Object[DEFAULT_CAPACITY];
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

        // 判读线性表是否满状态
        if (size == data.length) {
            resize(2 * data.length);
        }

        // 向后移动元素
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }

        // 将新元素插入指定位置
        data[index] = element;
        size++;
    }

    // 扩容/缩容 操作 不向外界开放提供  是私有private
    private void resize(int newLen) {
        E[] newData = (E[]) new Object[newLen];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    @Override
    public void remove(E element) { // 删除元素 只删除一次
        int index = indexOf(element);
        if (index != -1) {
            remove(index);
        } else {
            throw new IllegalArgumentException("要删除的元素不存在");
        }
    }


    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("remopve index out of range");
        }
        // 先保存要删除的值
        E ret = data[index];

        // 移动元素
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;

        // 什么时候缩容
        // 1.有效元素是容量的1/4
        // 2.当前容量不得小于的等于默认容量
        if (size == data.length / 4 && data.length > DEFAULT_CAPACITY) {
            resize(data.length / 2);
        }

        return ret;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("get index out of range");
        }
        return data[index];
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("set index out of range");
        }
        E set = data[index];
        data[index] = element;
        return set;
    }

    @Override
    public int size() {
        return size;
    }

    // 额外添加一个元素 获取线性表的容量
    private int getCapacity() {
        return data.length;
    }

    // 元素第一次出现的位置
    @Override
    public int indexOf(E element) {
        /*
           ==  比的啥 主要看等号两边是啥
           == 两边是基本数据类型的话 比的是值
           == 两边是引用类型的话 比较的是地址
         */
        for (int i = 0; i < size; i++) {
            if (data[i].equals(element)) {
                return i;
            }
        }
        return -1; // -1 代表没找到
    }

    @Override
    public boolean contains(E element) { // 查看元素在线性表中从左到右出现的位置
        return indexOf(element) != -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        data = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void sort(Comparator<E> c) {
        if (c == null) {
            throw new IllegalArgumentException("comparator can not be null");
        }
        for (int i = 1; i < size; i++) {
            E e = data[i];
            int j = 0;
            for (j = i; j > 0 && c.compare(data[j - 1], e) > 0; j--) { // compare > 0 代表第一个值比第二个值大
                data[j] = data[j - 1];
            }
            data[j] = e;
        }
    }

    @Override
    public List<E> sublist(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex >= size || fromIndex > toIndex) {
            throw new IllegalArgumentException("");
        }
        ArrayList<E> list = new ArrayList<>();
        for (int i = fromIndex; i <= toIndex; i++) {
            list.add(data[i]);
        }
        return list;
    }


    /*
        [1,2,3,4,5]
        Arrays.toString(arr);
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        if (isEmpty()) {
            sb.append(']');
        } else {
            for (int i = 0; i < size; i++) {
                sb.append(data[i]);
                if (i == size - 1) {
                    sb.append(']');
                } else {
                    sb.append(',');
                    sb.append(' ');
                }
            }
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) { // 比较的是两个 ArrayList 是否相等
        // 1.判空
        if (o == null) {
            return false;
        }
        // 2.判自己
        if (this == o) {
            return true;
        }
        // 3.判类型
        if (o instanceof ArrayList) {
            // 4.按照自己的逻辑比较
            ArrayList<E> other = (ArrayList<E>) o;
            // 5.先比较元素的个数
            if (this.size != other.size) {
                return false;
            }
            // 6.有效元素个数相等的情况下 逐个比较元素
            for (int i = 0; i < size; i++) {
                if (!data[i].equals(other.data[i])) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    //获取当前这个数据结构/容器 的 迭代器
    //通过迭代器对象 更方便挨个取出每一个元素
    //同时 实现了Iterable 可以让当前的数据结构/容器 被foreach循环遍历
    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    //创建一个属于ArrayList的迭代器
    class ArrayListIterator implements Iterator<E> {
        private int cur = 0;

        @Override
        public boolean hasNext() {//判断是否有下一个元素
            return cur < size;
        }

        @Override
        public E next() {//如果有下一个元素 则把当前元素返回 并移至到下一个元素
            return data[cur++]; // 先用后加
        }
    }
}
