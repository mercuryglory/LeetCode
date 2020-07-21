package linkedlist;

public class BaseNode {

    protected static void printList(ListNode listNode) {
        if (listNode == null) {
            return;
        }
        while (listNode != null) {
            System.out.print(listNode.value + (listNode.next == null ? "" : " -> "));
            listNode = listNode.next;
        }
        System.out.println();

    }

    protected static ListNode generateListNode(int n) {
        ListNode head = new ListNode(0);
        ListNode point = head;
        int i = 0;
        while (i++ < n) {
            point.next = new ListNode(i);
            point = point.next;
        }

        return head;
    }
}