package ph.作业;

import p1.接口.List;
import p1.接口.Map;
import p1.接口.Set;
import p2.线性结构.ArrayList;
import p3.链式结构.LinkedList;
import p6.树与哈希表.TreeSet;

// 平衡二分搜索数
public class HWAVLTreeMap<K extends Comparable<K>, V> implements Map<K, V> {

    private class Node {
        public K key;
        public V value;
        public int height;
        public Node left;
        public Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            height = 1;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public HWAVLTreeMap() {
        root = null;
        size = 0;
    }

    // 根据root根  找值为key的节点
    private Node getNode(Node node, K key) {
        if (node == null) return null;
        if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            return getNode(node.right, key);
        } else {
            return node;
        }
    }

    // 高度
    private int getHeight(Node node) {
        if (node == null) return 0;
        return node.height;
    }

    // 平衡因子
    private int getBalanceFactor(Node node) {
        if (node == null) return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    // is二分搜索树
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
        if (node == null) return;
        inOrderKeys(node.left, list);
        list.add(node.key);
        inOrderKeys(node.right, list);
    }

    // is平衡树
    public  boolean isBalanced() {
        return isBalanced(root);
    }
    private boolean isBalanced(Node node) {
        if (node == null) return true;
        int balanceFactor = getBalanceFactor(node);
        if (balanceFactor > 1) {
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }

    // 左旋转
    private Node leftRoate(Node y) {
        Node x = y.right;
        Node T3 = x.left;
        x.left = y;
        y.right = T3;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    //右旋转
    private Node rightRoate(Node y) {
        Node x = y.left;
        Node T3 = x.right;
        x.right = y;
        y.left = T3;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node put(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }
        if (key.compareTo(node.key) < 0) {
            node.left = put(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }

        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        int balanceFactor = getBalanceFactor(node);

        // > 1 左左
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            return rightRoate(node);
        }
        // > 1 左右
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRoate(node.left);
            return rightRoate(node);
        }
        // < 1 右右
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            return leftRoate(node);
        }
        // < 1 右左
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            node.right = rightRoate(node.right);
            return leftRoate(node);
        }
        return node;
    }

    @Override
    public V remove(K key) {
        Node delNode = getNode(root, key);
        if (delNode != null) {
            root = remove(root, key);
            return delNode.value;
        }
        return null;
    }

    private Node remove(Node node, K key) {
        if (node == null) return null;
        Node retNode = null;
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            retNode = node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            retNode = node;
        } else {
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            } else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            } else {
                Node successor = minimum(node.right);
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;
                node.left = node.right = null;
                size--;
                retNode = successor;
            }
        }
        if (retNode == null) {
            return retNode;
        }
        retNode.height = Math.max(getHeight(retNode.left), getHeight(retNode.right)) + 1;
        int balanceFactor = getBalanceFactor(retNode);

        // > 1 左左
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
            return rightRoate(retNode);
        }
        // > 1 左右
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = leftRoate(retNode.left);
            return rightRoate(retNode);
        }
        // < 1 右右
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            return leftRoate(retNode);
        }
        // < 1 右左
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
            retNode.right = rightRoate(retNode.right);
            return leftRoate(retNode);
        }
        return retNode;
    }

    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        } else {
            return minimum(node.left);
        }
    }

    @Override
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    @Override
    public V get(K key) {
        return getNode(root, key).value;
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException("cuo");
        }
        node.value = value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return root == null && size == 0;
    }

    @Override
    public Set<K> keySet() {
        TreeSet<K> set = new TreeSet<>();
        inOrderSet(root, set);
        return set;
    }

    private void inOrderSet(Node node, TreeSet<K> set) {
        if (node == null) return;
        inOrderSet(node.left, set);
        set.add(node.key);
        inOrderSet(node.right, set);
    }

    @Override
    public List<V> values() {
        LinkedList<V> list = new LinkedList<>();
        inOrderValues(root, list);
        return list;
    }

    private void inOrderValues(Node node, LinkedList<V> list) {
        if (node == null) return;
        inOrderValues(node.left, list);
        list.add(node.value);
        inOrderValues(node.right, list);
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        TreeSet<Entry<K, V>> entries = new TreeSet<>();
        inOrderEntrys(root, entries);
        return entries;
    }

    private void inOrderEntrys(Node node, TreeSet<Entry<K, V>> entries) {
        if (node == null) return;
        inOrderEntrys(node.left, entries);
        entries.add(new BSTEntry<>(node.key, node.value));
        inOrderEntrys(node.right, entries);
    }

    private class BSTEntry<K extends Comparable<K>, V> implements Entry<K, V> {
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
        public int compareTo(Entry<K, V> o) {
            return this.getKey().compareTo(o.getKey());
        }

        @Override
        public String toString() {
            return key + ":" + value;
        }
    }
}