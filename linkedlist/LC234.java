package linkedlist;

import com.sun.xml.internal.rngom.parse.host.Base;

import java.util.ArrayList;
import java.util.List;

/**
 * created by mercury on 2020-07-21
 * <p>
 * 回文链表
 * <p>
 * 请判断一个链表是否为回文链表。
 * <p>
 * 示例 1:
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 * <p>
 * 示例 2:
 * 输入: 1->2->2->1
 * 输出: true
 */

public class LC234 extends BaseNode {

    /**
     * 用一个数组动态的存储链表每个节点的值，然后左右加逼去比较
     */
    public static boolean isPalindrome2(ListNode head) {
        if (head == null) {
            return true;
        }

        List<Integer> list = new ArrayList<>();

        while (head != null) {
            list.add(head.value);
            head = head.next;
        }

        int i = 0, j = list.size() - 1;
        while (i < j) {
            if (!list.get(i).equals(list.get(j))) {
                return false;
            }
            i++;
            j--;
        }

        return true;
    }


    /**
     * 进阶做法，空间复杂度O(1)
     * 1、找到前半部分链表的尾节点，快指针每次2步，慢指针每次1步
     * 2、反转后半部分链表
     * 3、遍历判断是否为回文，保存判断结果
     * 4、恢复之前反转的部分
     */
    public static boolean isPalindrome(ListNode head) {

        if (head == null) {
            return true;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode secondHalf = reverse(slow.next);
        ListNode p1 = head;
        ListNode p2 = secondHalf;
        boolean result = true;
        while (result && p2 != null) {
            if (p1.value != p2.value) {
                result = false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        slow.next = reverse(secondHalf);

        return result;

    }

    public static ListNode reverse(ListNode head) {
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
//        ListNode head = generateListNode(5);
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(1);
        System.out.println(isPalindrome(head));

    }

}
