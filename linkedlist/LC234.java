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
    public static boolean isPalindrome(ListNode head) {
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

    public static void main(String[] args) {
//        ListNode head = generateListNode(5);
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(1);
        System.out.println(isPalindrome(head));

    }

}
