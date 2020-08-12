package linkedlist;

/**
 * created by mercury on 2020-08-12
 *
 * 排序链表
 *
 * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 *
 * 示例 1:
 *
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2:
 *
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 *
 */
public class LC148 {

    /**
     * 链表因为不具备数组随机访问这一特性，很多数组里设计的遍历和交换的排序方法不适用于链表排序，只有归并排序合适一点
     * 因为要求O（1）的空间复杂度，递归不符合
     */

    public ListNode sortList(ListNode head) {
        while(head)
    }

}
