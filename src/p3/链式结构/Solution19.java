package p3.链式结构;

public class Solution19 {
    public ListNode removeNthFromEnd(ListNode head,int n) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode right = head;
        ListNode left = head;
        for (int i = 0; i < n; i++) {
            right = right.next;
        }
        if (right == null){
            return head.next;
        }

        while (right.next != null) {
            right = right.next;
            left = left.next;
        }
        ListNode del = left.next;
        left.next = del.next;
        del.next = null;
        return head;
    }
}
