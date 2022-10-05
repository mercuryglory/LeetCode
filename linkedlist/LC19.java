package linkedlist;

/**
 * created by mercury on 2020-07-31
 *
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 *
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 *
 * 给定的 n 保证是有效的。
 *
 */

public class LC19 extends BaseNode {

    /**
     * 快慢指针
     * 找到要删除的节点的前一个节点，将其next指向next的下一个（相当于快指针领先n+1个）
     * 但如果是删除头节点则不存在前一个节点，所以用哑节点解决
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode slow = dummy;
        ListNode fast = dummy;
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = generateListNode(5);

        printList(removeNthFromEnd(head, 2));
    }

}
