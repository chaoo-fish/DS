package p6.树与哈希表;

import p1.接口.List;
import p1.接口.Map;
import p1.接口.Set;
import p3.链式结构.LinkedList;

import java.util.Iterator;

public class TreeMap<K extends Comparable<K>,V> implements Map<K,V>,Iterable<Map.Entry<K,V>> {


    //定义二分搜索树的结点信息
    private class Node {
        public K key;   //键
        public V value; //值
        public Node left;
        public Node right;
        public Node() {}
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    private Node root;
    private int size;

    public TreeMap() {
        root = null;
        size = 0;
    }

    //辅助函数 获取指定key所在的结点
    //以node为根的二分搜索树中 查找key所在的结点 - 递归
    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            return getNode(node.right, key);
        } else {
            return node;
        }
    }

    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node put(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key,value);
        }
        if (key.compareTo(node.key) < 0) {
            node.left = put(node.left, key,value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = put(node.right, key,value);
        } else {    //如果已经存在 则put为修改
            node.value = value;
        }
        return node;
    }

    @Override
    public V remove(K key) {
        Node delNode = getNode(root,key);
        if (delNode != null) {
            root = remove(root,key);
            return delNode.value;
        }
        return null;
    }

    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else {
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;
            return successor;
        }
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    private Node minimum(Node node) {
        if (isEmpty()) {
            return null;
        }
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }



    @Override
    public boolean contains(K key) {
        return getNode(root,key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(root,key);
        if (node != null) {
            return node.value;
        }
        return null;
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(root,key);
        if (node == null) {
            throw new IllegalArgumentException("key-value is not exist");
        }
        node.value = value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0 && root == null;
    }

    //获取所有键的Set
    @Override
    public Set<K> keySet() {
        TreeSet<K> set = new TreeSet<>();
        inOrderKetSet(root, set);
        return set;
    }

    // 中序遍历
    private void inOrderKetSet(Node node, TreeSet<K> set) {
        if (node == null) {
            return;
        }
        inOrderKetSet(node.left,set);
        set.add(node.key);
        inOrderKetSet(node.right,set);
    }

    //获取所有值的List
    @Override
    public List<V> values() {
        LinkedList<V> list = new LinkedList<>();
        inOrderValues(root, list);
        return list;
    }

    private void inOrderValues(Node node, LinkedList<V> list) {
        if (node == null) {
            return;
        }
        inOrderValues(node.left, list);
        list.add(node.value);
        inOrderValues(node.right, list);
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        TreeSet<Entry<K,V>> entries = new TreeSet<>();
        inOrderEntrys(root,entries);
        return entries;
    }

    private void inOrderEntrys(Node node, TreeSet<Entry<K,V>> entries) {
        if (node == null) {
            return;
        }
        inOrderEntrys(node.left,entries);
        entries.add(new BSTEntry<>(node.key, node.value));

        inOrderEntrys(node.right,entries);
    }


    //自己实现
    @Override
    public String toString() {
        return null;
    }
    //自己实现
    @Override
    public Iterator<Entry<K, V>> iterator() {
        return null;
    }

    //键值对对象
    private class BSTEntry<K extends Comparable<K>,V> implements Entry<K,V> {

        private K key;
        private V value;
        public BSTEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return key + ":" + value;
        }

        @Override
        public int compareTo(Entry<K, V> o) {
            return this.getKey().compareTo(o.getKey()) ;
        }
    }
}

