package p3.链式结构;

public class Solution82 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0); // 虚拟头结点
        dummy.next = head;

        ListNode pre = dummy; // 用来接受不重复节点的
        ListNode cur = head; // 用来判断节点相等的指针

        while (cur != null && cur.next != null) {
            if (cur.val != cur.next.val) {
                 pre = cur;
                 cur = cur.next;
            } else {
                ListNode next = cur.next.next;
                while (next != null && next.val == cur.val) {
                    next = next.next;
                }
                pre.next = next;
                cur = next;
            }
        }
        return dummy.next;
    }
}
