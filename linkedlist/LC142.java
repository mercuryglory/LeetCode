package linkedlist;

/**
 * created by mercury on 2020-08-10
 *
 * 环形链表II
 *
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 *
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 *
 * 说明：不允许修改给定的链表。
 *
 *
 *
 */
public class LC142 extends BaseNode {

    /**
     * 快慢指针，可以完成空间复杂度为O(1)，又称Floyd算法
     * 关于证明可以看题解下的高赞回答
     */
    public static ListNode detectCycle(ListNode head) {
        //因为只使用了指针，可以认为空间复杂度为O(1)
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                fast = head;
                while (fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return fast;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 0, -4};
        ListNode circle = generateCircle(arr, -1);
        ListNode entry = detectCycle(circle);
    }
}
