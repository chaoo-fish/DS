package p6.树与哈希表;

import p1.接口.Set;

import java.util.Iterator;

//底层由二分搜索树BinarySearchTree(BST)实现的集合
public class TreeSet<E extends Comparable<E>> implements Set<E> {
    private BinarySearchTree<E> bst;

    public TreeSet() {
        bst = new BinarySearchTree<>();
    }


    @Override
    public void add(E e) {  //O(logn)
        bst.add(e);
    }

    @Override
    public void remove(E e) { //O(logn)
        bst.remove(e);
    }

    @Override
    public boolean contains(E e) { //O(logn)
        return bst.contains(e);
    }

    @Override
    public int size() {
        return bst.size();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    @Override
    public String toString() {
        return bst.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return bst.iterator();
    }
}