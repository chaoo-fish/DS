package p3.链式结构;

public class Solution141 {
    public boolean hasCycle(ListNode head){
        if (head == null || head.next == null) {
            return false;
        }
        ListNode p = head;
        ListNode q = head;
        // 快慢指针 p一倍速向下走  q以二倍速向下走  终究q会追上p
        while (q.next != null && q.next.next != null){
            p = p.next;
            q = q.next.next;
            if (p == q) {
                return true;
            }
        }

        return false;
    }
}
