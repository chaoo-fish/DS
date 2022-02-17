package p6.树与哈希表;

import p2.线性结构.ArrayList;

import java.util.Iterator;

// 最大堆
public class MaxHeap<E extends Comparable<E>> implements Iterable<E> {
    //最大堆底层使用线性表实现
    private ArrayList<E> data;

    public MaxHeap() {
        data = new ArrayList<>();
    }

    //获取父节点的角标
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("no parent");
        }
        return (index - 1) / 2;
    }

    //获取左孩子的角标
    private int leftChild(int index) {
        return 2 * index + 1;
    }

    //获取右孩子的角标
    private int rightChild(int index) {
        return 2 * index + 2;
    }

    //元素上浮的操作
    private void siftUp(int k) {
        while (k > 0 && data.get(k).compareTo(data.get(parent(k))) > 0) {
            data.swap(k, parent(k));
            k = parent(k);
        }
    }
    //元素下沉的操作
    private void siftDown(int k) {
        //如果没有左孩子 同时也就没有右孩子 就不用下沉
        //如果有左孩子 右孩子不一定存在 判断右孩子的存在性
        //如果右孩子存在 取左右两个孩子的最大值和k对应的值比较
        //如果右孩子不存在 只能取左孩子的值和k对应的值比较
        //如果k对应的值比左右两个孩子都大 则不用下沉 否则下沉即可
        while (leftChild(k) < data.size()) {
            // 因为是先左后右,所以先默认j为/左孩子
            int j = leftChild(k);
            // 存在右孩子的情况下,将左右孩子进行比较,取最大的那个
            if (j + 1 < data.size() && data.get(j + 1).compareTo(data.get(j)) > 0) {
                j = rightChild(k);
            }
            // 如果当前值小于 j角标的值 则进行交换,交换完成后 k 的值更新为 j 的值
            if (data.get(k).compareTo(data.get(j)) < 0) {
                data.swap(k,j);
                k = j;
            } else {
                break;  //当前比左右两个孩子都大 不用下沉
            }
        }
    }

    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public void clear() {
        data.clear();
    }

    public void add(E e) {
        data.add(e);
        siftUp(data.size() - 1);
    }

    public E findMax() {
        if (data.isEmpty()) {
            throw new IllegalArgumentException("maxheap is empty");
        }
        return data.get(0);
    }

    public E findMin() {
        if (data.isEmpty()) {
            throw new IllegalArgumentException("maxheap is empty");
        }
        E min = data.get(0);
        for (int i = 1; i < data.size(); i++) {
            if (data.get(i).compareTo(min) < 0) {
                min = data.get(i);
            }
        }
        return min;
    }

    public E extractMax() {
        if (data.isEmpty()) {
            throw new IllegalArgumentException("maxheap is empty");
        }
        E max = findMax();
        data.swap(0, data.size() - 1);
        data.remove(data.size() - 1);
        siftDown(0);
        return max;
    }

    //替换最大值 并返回原先的最大值
    public E replace(E e) {
        E res = findMax();
        data.set(0, e);
        siftDown(0);
        return res;
    }

    @Override
    public Iterator<E> iterator() {
        return data.iterator();
    }

    @Override
    public String toString() {
        return data.toString();
    }
}