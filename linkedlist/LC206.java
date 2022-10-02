package linkedlist;


/**
 * created by mercury on 2020-07-17
 * 反转链表
 */
public class LC206 extends BaseNode {

    public static ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }


    public static void main(String[] args) {
        ListNode node = generateListNode(5);
        ListNode reverse = reverseList(node);
        printList(reverse);
    }

}
