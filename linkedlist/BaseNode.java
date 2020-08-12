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

    protected static ListNode generateCircle(int[] arr, int pos) {
        ListNode pre = new ListNode(-1);
        ListNode target = null;
        ListNode head = pre;
        for (int i = 0; i < arr.length; i++) {
            head.next = new ListNode(arr[i]);
            head = head.next;
            if (pos >= 0 && pos < arr.length - 1 && pos == i) {
                target = head;
            }
            if (i == arr.length - 1) {
                head.next = target;
            }
        }

        return pre.next;


    }

    protected static ListNode generateListNode(int[] arr) {
        ListNode pre = new ListNode(-1);
        ListNode head = pre;
        for (int i = 0; i < arr.length; i++) {
            head.next = new ListNode(arr[i]);
            head = head.next;
        }

        return pre.next;


    }
}