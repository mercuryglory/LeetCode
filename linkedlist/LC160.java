package linkedlist;

/**
 * created by mercury on 2020-07-19
 * <p>
 * 相交链表
 */
public class LC160 {

    /**
     * 一种很巧妙的思路，时间复杂度O(n)，空间复杂度O(1)
     *
     * 两个指针pA、pB，pA指向链表A头节点，pB指向链表B头节点
     * 假设链表A的长度为6，链表B的长度为4，那么pB比pA少经过2个节点，会先到达尾部。
     * 将pB重定向到A的头节点，这时候pA必然再经过2个节点就会到达尾部，那么pA继续向后
     * 到达尾部后重定向到B的头节点。这样pB又比pA多经过2个节点，这时候两个节点所在的位置
     * 距离链表末尾就是同等距离了
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode nodeA = headA;
        ListNode nodeB = headB;
        while (nodeA != nodeB) {
            nodeA = nodeA == null ? headB : nodeA.next;
            nodeB = nodeB == null ? headA : nodeB.next;
        }

        return nodeA;
    }

    public static void main(String[] args) {
        ListNode head1= new ListNode(0);
        ListNode head2= new ListNode(-1);
        ListNode point1 = head1;
        ListNode point2 = head2;
        int i = 0;
        while (i++ < 5) {
            if (i >= 3) {
                ListNode newNode = new ListNode(i * 4);
                point1.next = point2.next = newNode;
                point1 = point2 = point2.next;

            } else {
                point1.next = new ListNode(i * 2);
                point1 = point1.next;

                point2.next = new ListNode(i * 3);
                point2 = point2.next;
            }

        }

        ListNode node = getIntersectionNode(head1, head2);
        System.out.println(123);
    }
}
