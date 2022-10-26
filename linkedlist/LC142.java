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
     * 可以用哈希表
     *
     * 快慢指针，可以完成空间复杂度为O(1)，又称Floyd算法
     * 首先证明若成环，为什么两个指针跑不完一圈就会相遇
     * 假设环长L，slow进入环的时候，fast距离它n步（n<L）。每走一次，fast和slow的距离会缩短1，
     * 它们的距离会从n，n-1,n-2...1,0。最后一定会相遇
     * 这个过程中slow走了n步，而n<L，即走不了一圈就会追上
     *
     * 假设从头到入环节点的距离为a，入环后直到相遇慢指针走过的距离为b，相遇节点和入环节点的距离为c
     * 则2*（a+b)=a+b+c+b   =>   a=c
     * 即快慢指针相遇后，一个节点从链表头，另一个从相遇点出发，再次走过相同的路程一定会相遇，此时就是入环点
     *
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
