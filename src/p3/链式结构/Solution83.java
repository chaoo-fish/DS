package p3.链式结构;

public class Solution83 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode left = head;
        ListNode right = left.next;
        while (right != null){
            if (left.val == right.val){
                left.next = right.next;
                right.next = null;
                right = left.next;
            } else {
                left = left.next;
                right = left.next;
            }
        }
        return head;
    }
}
