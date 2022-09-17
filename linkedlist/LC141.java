package linkedlist;

/**
 * created by mercury on 2020-07-20
 * <p>
 * 环形链表
 */
public class LC141 extends BaseNode {

    //快慢指针，如果无环，快指针肯定先到尾部
    private static boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 0, -4};
        ListNode circle = generateCircle(arr, -1);
        System.out.println(hasCycle(circle));
    }
}
