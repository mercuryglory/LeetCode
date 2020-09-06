package linkedlist;

import java.util.*;

/**
 * created by mercury on 2020-09-06
 *
 * 合并K个升序链表
 *
 * 给你一个链表数组，每个链表都已经按升序排列。
 *
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 *
 * 示例 1：
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 *
 *
 * 示例 2：
 * 输入：lists = []
 * 输出：[]
 *
 *
 * 示例 3：
 * 输入：lists = [[]]
 * 输出：[]
 *  
 *
 * 提示：
 *
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] 按 升序 排列
 * lists[i].length 的总和不超过 10^4
 *
 */

public class LC23 extends BaseNode {

    /**
     * 使用小根堆，时间复杂度O(NlogK)
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        Queue<ListNode> queue = new PriorityQueue<>((v1, v2) -> (v1.val - v2.val));
        for (ListNode node : lists) {
            queue.offer(node);
        }

        ListNode pre = new ListNode(-1);
        ListNode cur = pre;
        while (!queue.isEmpty()) {
            ListNode minNode = queue.poll();
            cur.next = minNode;
            cur = cur.next;
            if (minNode.next != null) {
                queue.offer(minNode.next);
            }

        }
        return pre.next;

    }

    //比较容易的思路，先将各个链表的数都存放到一个集合中，对集合排序后再输出为链表，时间复杂度O(NK)
    public static ListNode mergeKLists2(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        List<Integer> list = new ArrayList<>();
        for (ListNode node : lists) {
            while (node != null) {
                list.add(node.val);
                node = node.next;
            }
        }

        Collections.sort(list);

        ListNode pre = new ListNode(-1);
        ListNode cur = pre;
        for (int i : list) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }
        return pre.next;

    }

    public static void main(String[] args) {
        int[] arr1 = {1, 4, 5};
        int[] arr2 = {1, 3, 4};
        int[] arr3 = {2, 6};
        ListNode[] list = new ListNode[3];
        list[0] = generateListNode(arr1);
        list[1] = generateListNode(arr2);
        list[2] = generateListNode(arr3);

        ListNode node = mergeKLists(list);
        printList(node);

    }
}
