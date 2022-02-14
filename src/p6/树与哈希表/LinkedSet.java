package p6.树与哈希表;

import p1.接口.Set;
import p3.链式结构.LinkedList;

import java.util.Iterator;


//底层由链表来实现的集合
public class LinkedSet<E> implements Set<E> {
    private LinkedList<E> list;

    public LinkedSet() {
        list = new LinkedList<>();
    }

    @Override
    public void add(E e) {      //O(n)
        if (!list.contains(e)) {
            list.add(e);
        }
    }

    @Override
    public void remove(E e) {   //O(n)
        list.remove(e);
    }

    @Override
    public boolean contains(E e) { //O(n)
        return list.contains(e);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        return list.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }
}