package p0.测试;

import p6.树与哈希表.BinarySearchTree;

public class TestBST {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.add(1);
        bst.add(4);
        bst.add(3);
        bst.add(5);
        bst.add(2);
        bst.add(6);
        System.out.println(bst);

        System.out.println(bst.contains(3));
        System.out.println(bst.contains(7));
        System.out.println("==========");
        bst.preOrder();
        System.out.println("==========");
        bst.inOrder();
        System.out.println("==========");
        bst.postOrder();
        System.out.println("==========");
        bst.preOrderNR();
        System.out.println("==========");
        bst.inOrderNR();
        System.out.println("==========");
        bst.levelOrder();
        System.out.println("==========");
        System.out.println(bst.minimum());
        System.out.println(bst.maximum());
        System.out.println(bst.removeMin());
        System.out.println(bst.removeMax());
        System.out.println("==========");
        bst.inOrderNR();
        System.out.println("==========");
        bst.remove(4);
        bst.inOrderNR();
        System.out.println("==========");
        for (Integer num : bst) {
            System.out.println(num);
        }
        System.out.println(bst);
    }
}