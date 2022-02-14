package p6.树与哈希表;

import p1.接口.List;
import p1.接口.Map;
import p1.接口.Set;
import p2.线性结构.ArrayList;
import p3.链式结构.LinkedList;

//AVL平衡二分搜索树实现的映射
public class AVLTreeMap<K extends Comparable<K>,V> implements Map<K,V> {
    private class Node {
        public K key;   //键
        public V value; //值
        public int height;  //高度
        public Node left;
        public Node right;

        public Node (K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1; //新结点的高度默认都是1
        }
    }

    private Node root;
    private int size;

    public AVLTreeMap() {
        root = null;
        size = 0;
    }

    //以node为根的子树中 查找key所在的结点
    private Node getNode(Node node, K key) {
        if(node == null) {
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

    //获取某一个结点的高度 如果该结点为空 则高度为0
    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    //计算某一个结点的平衡因子(左右子树的高度差) >0左边高 ==0左右同高 <0右边高
    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    //验证是否是二分搜索树
    public boolean isBST() {
        ArrayList<K> list = new ArrayList<>();
        inOrderKeys(root, list);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1).compareTo(list.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }

    private void inOrderKeys(Node node, ArrayList<K> list) {
        if (node == null) {
            return;
        }
        inOrderKeys(node.left, list);
        list.add(node.key);
        inOrderKeys(node.right,list);
    }

    //验证得是一颗平衡树
    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }
        int balancedFactor = getBalanceFactor(node);
        if (Math.abs(balancedFactor) > 1) {
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }

    //左旋转(右侧的右侧) 将y结点进行左旋转 并返回新的根
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node T3 = x.left;
        x.left = y;
        y.right = T3;
        y.height = Math.max(getHeight(y.left),getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left),getHeight(x.right)) + 1;
        return x;
    }
    //右旋转(左侧的左侧) 将y结点进行右旋转 并返回新的根
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T3 = x.right;
        x.right = y;
        y.left = T3;
        y.height = Math.max(getHeight(y.left),getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left),getHeight(x.right)) + 1;
        return x;
    }

    @Override
    public void put(K key, V value) {
        root = put(root,key, value);
    }

    private Node put(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }
        if (key.compareTo(node.key) < 0) {
            node.left = put(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = put(node.right, key,value);
        } else {
            node.value = value;
        }

        //当前结点的高度需要更新
        node.height = Math.max(getHeight(node.left),getHeight(node.right)) + 1;
        //判断当前结点是否是平衡的
        int balanceFactor = getBalanceFactor(node);

        // >1说明当前结点左侧不平衡 node.left >= 0 左侧的左侧不平衡
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            return rightRotate(node);
        }
        // >1说明当前结点左侧不平衡 node.left < 0 左侧的右侧不平衡
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // <-1说明当前结点右侧不平衡 node.right >= 0 右侧的左侧不平衡
        if (balanceFactor < -1 && getBalanceFactor(node.right) >= 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        // <-1说明当前结点右侧不平衡 node.right < 0 右侧的右侧不平衡
        if (balanceFactor < -1 && getBalanceFactor(node.right) < 0) {
            return leftRotate(node);
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
        Node retNode = null;
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            retNode = node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            retNode = node;
        } else {    //找到了要删除的结点
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            } else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                retNode = leftNode;
            } else {
                Node successor = minmum(node.right);
                successor.right = remove(node.right,successor.key);
                successor.left = node.left;
                node.left = node.right = null;
                retNode = successor;
            }
        }
        if (retNode == null) {
            return retNode;
        }
        //更新高度
        retNode.height = Math.max(getHeight(retNode.left), getHeight(retNode.right)) + 1;
        //获取平衡因子判断是否需要自平衡
        int balanceFactor = getBalanceFactor(retNode);

        // >1说明当前结点左侧不平衡 node.left >= 0 左侧的左侧不平衡
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
            return rightRotate(retNode);
        }
        // >1说明当前结点左侧不平衡 node.left < 0 左侧的右侧不平衡
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }
        // <-1说明当前结点右侧不平衡 node.right >= 0 右侧的左侧不平衡
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) >= 0) {
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }
        // <-1说明当前结点右侧不平衡 node.right < 0 右侧的右侧不平衡
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) < 0) {
            return leftRotate(retNode);
        }
        return retNode;
    }

    private Node minmum(Node node) {
        if (node.left == null) {
            return node;
        } else {
            return minmum(node.left);
        }
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

    @Override
    public Set<K> keySet() {
        TreeSet<K> set = new TreeSet<>();
        inOrderKeySet(root, set);
        return set;
    }
    private void inOrderKeySet(Node node, TreeSet<K> set) {
        if (node == null) {
            return;
        }
        inOrderKeySet(node.left,set);
        set.add(node.key);
        inOrderKeySet(node.right,set);
    }

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
        inOrderEntrys(node.left, entries);
        entries.add(new BSTEntry<>(node.key, node.value));

        inOrderEntrys(node.right, entries);
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

    //前序遍历
    public void preOrder() {
        preOrder(root);
    }
    //前序遍历-递归方式 以node为根节点 前序遍历DLR
    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.key);
        preOrder(node.left);
        preOrder(node.right);
    }
}