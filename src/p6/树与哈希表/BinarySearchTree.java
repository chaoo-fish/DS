package p6.树与哈希表;

import p3.链式结构.LinkedList;

import java.util.Iterator;

//二分搜索树 - 不能存储重复的元素 - 容易产生极端情况(斜树) 非平衡树
//平衡树 在二分搜索树中 任意一个结点的左子树的高度和右子树的高度差 <= 1
public class BinarySearchTree<E extends Comparable<E>> implements Iterable<E>{

    //定义二分搜索树的结点信息
    private class Node {
        public E e; //数据域
        public Node left;   //左孩子(当前Node结点左子树的根)
        public Node right;  //右孩子(当前Node结点右子树的根)
        //public int count = 1;   //记录元素出现的次数 默认1
        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
        @Override
        public String toString() {
            return e.toString();
        }
    }
    private Node root;  //根节点的指针 根指针 采用真实根节点 如果二分搜索树为空 root == null
    private int size;   //二分搜索树中元素的个数(结点的个数)

    public BinarySearchTree(){
        root = null;
        size = 0;
    }

    //添加元素的方法
    public void add(E e) {
        /*
        //添加的迭代思路
        Node node = new Node(e);
        if (isEmpty()) {
            root = node;
            size++;
        }
        Node cur = root;
        while (true) {
            //新元素比当前大 往右走
            if (node.e.compareTo(cur.e) > 0) {
                if (cur.right == null) {
                    cur.right = node;
                    size++;
                    break;
                } else {
                    cur = cur.right;
                }
            //新元素比当前小 往左走
            } else if (node.e.compareTo(cur.e) < 0) {
                if (cur.left == null) {
                    cur.left = node;
                    size++;
                    break;
                } else {
                    cur = cur.left;
                }
            } else {
                break;
            }
        }
        */
        //递归的思路
        root = add(root, e);
    }

    //在以node为根的树中 插入元素e 并返回新树的根
    private Node add(Node node, E e) {
        //从下一层向上一层
        if (node == null) {
            size++;
            return new Node(e);
        }
        //当前层向下一层
        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }
        //等于的时候 想重复
        /*
        else {
            node.count++;
        }
        */
        //当前层向上一层
        return node;
    }

    public boolean contains(E e) {
        //迭代思路
        /*
        Node cur = root;
        while (true) {
            if (e.compareTo(cur.e) < 0) {
                if (cur.left == null) {
                    return false;
                }
                cur = cur.left;
            } else if (e.compareTo(cur.e) > 0) {
                if (cur.right == null) {
                    return false;
                }
                cur = cur.right;
            } else {
                return true;
            }
        }
        */
        return contains(root,e);
    }

    //查看以node为根的二分搜索树中是否包含元素e
    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }
        if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            return contains(node.right, e);
        } else {
            return true;
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
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    //前序遍历-迭代方式
    public void preOrderNR() {
        LinkedList<Node> stack = new LinkedList<>();
        if (isEmpty()) {
            return;
        }
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.e);
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }

    //中序遍历
    public void inOrder() {
        inOrder(root);
    }
    //中序遍历-递归方式 以node为根节点 中序遍历LDR
    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }
    //中序遍历-迭代方式
    public void inOrderNR() {
        LinkedList<Node> stack = new LinkedList<>();
        Node p = root;
        while (p != null) {
            stack.push(p);
            p = p.left;
        }
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.e);
            if (cur.right != null) {
                Node n = cur.right;
                while (n != null) {
                    stack.push(n);
                    n = n.left;
                }
            }
        }
    }

    //后序遍历
    public void postOrder() {
        postOrder(root);
    }
    //后序遍历-递归方式 以node为根节点 后序遍历LRD
    private void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    //层序遍历-迭代方式
    public void levelOrder() {
        LinkedList<Node> queue = new LinkedList<>();
        if (isEmpty()) {
            return;
        }
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.e);
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return root == null && size == 0;
    }

    //寻找二分搜索树中的最小值
    //自己尝试迭代方式实现
    public E minimum() {
        if (isEmpty()) {
            throw new IllegalArgumentException("bst is empty");
        }
        return minimum(root).e;
    }

    //以node为根节点 查找最小值所在的结点 - 递归
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    //寻找二分搜索树中的最大值
    //自己尝试迭代方式实现
    public E maximum() {
        if (isEmpty()) {
            throw new IllegalArgumentException("bst is empty");
        }
        return maximum(root).e;
    }

    //以node为根节点 查找最大值所在的结点 - 递归
    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }

    //删除最小值
    public E removeMin() {
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }

    //以node为根 删除其中的最小值结点 返回删除后新树的根
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

    //删除最大值
    public E removeMax() {
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }

    //以node为根 删除其中的最大值结点 返回删除后新树的根
    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    public void remove(E e) {
        root = remove(root,e);
    }

    //以node为根 删除元素e 并返回删除后新树的根
    //如果存在重复 删除的时候就要考虑当前节点出现的次数 node.count--
    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        }
        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right,e);
            return node;
        } else {    //找到了
            /*
            if (node.count > 1) {
                node.count--;
            }
            */
            //如果待删除的结点左边为空 则右子树直接上
            if (node.left == null) {
                Node rightNode = node.right;    //此时右边可能空 可能非空
                node.right = null;
                size--;
                return rightNode;
            }
            if (node.right == null) {
                Node leftNode = node.left;      //此时左边非空的
                node.left = null;
                size--;
                return leftNode;
            }
            //左右都不为空 把右子树的最小值当成新树的根返回
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;
            return successor;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        if (isEmpty()) {
            sb.append(']');
        } else {
            Iterator<E> it = iterator();
            for (int i = 0; i < size; i++) {
                sb.append(it.next());
                if (i == size - 1) {
                    sb.append(']');
                } else {
                    sb.append(',');
                }
            }

//            while (it.hasNext()) {
//                sb.append(it.next());
//                sb.append(',');
//            }
//            sb.deleteCharAt(sb.length() - 1);
//            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new BinarySearchTreeIterator();
    }
    private class BinarySearchTreeIterator implements Iterator<E> {
        private Iterator<E> it;
        public BinarySearchTreeIterator() {
            LinkedList<E> list = new LinkedList<>();
            LinkedList<Node> stack = new LinkedList<>();
            Node p = root;
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
            while (!stack.isEmpty()) {
                Node cur = stack.pop();
                list.offer(cur.e);
                if (cur.right != null) {
                    Node n = cur.right;
                    while (n != null) {
                        stack.push(n);
                        n = n.left;
                    }
                }
            }
            it = list.iterator();
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public E next() {
            return it.next();
        }
    }
}