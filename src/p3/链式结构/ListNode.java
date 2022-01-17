package p3.链式结构;

import p1.接口.List;

// LeetCode 中 关于链表节点的定义
public class ListNode {
    int val;
    ListNode next;

    ListNode(){}
    ListNode(int val) {
        this.val = val;
    }
    ListNode(int val ,ListNode next){
        this.val = val;
        this.next = next;
    }
}
