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
public class LC148 extends BaseNode {

    /**
     * 链表因为不具备数组随机访问这一特性，很多数组里设计的遍历和交换的排序方法不适用于链表排序，只有归并排序合适一点
     * 因为要求O（1）的空间复杂度，递归不符合
     *
     * 这块配合题解看:https://leetcode-cn.com/problems/sort-list/solution/sort-list-gui-bing-pai-xu-lian-biao-by-jyd/
     *
     * 二分法确定合并次数 logN，每一层都需要遍历整个链表，每轮合并单元次数和单元总数累计为 n，因此时间复杂度为O(NLogN)
     * 迭代法是从底向上，初始单元长度为1
     *
     */

    public static ListNode sortList(ListNode head) {
        //先记录链表的长度，作为合并次数的结束条件
        ListNode temp = head;
        int len = 0;
        while (temp != null) {
            len++;
            temp = temp.next;
        }

        //定义哑节点，存储和搜索排序链表的头部
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        //step表示单元长度（步长），h1表示待合并链表的前半部分，h2后半部分，pre用于合并链表时的迭代
        int step = 1;
        ListNode h1, h2, pre;

        while (step < len) {
            //每一轮结束，复原temp和pre
            pre = dummy;
            temp = dummy.next;
            while (temp != null) {
                //设置计数是为了判断是否需要合并，以及h2的单元长度，因为链表长度不一定是2的次幂
                int count = step;
                h1 = temp;
                while (count > 0 && temp != null) {
                    count--;
                    temp = temp.next;
                }
                //h1连步长都没走完或者已经到尾部了，说明后面没有待排序节点，结束本次迭代
                if (count > 0 || temp == null) {
                    break;
                }

                h2 = temp;
                count = step;
                while (count > 0 && temp != null) {
                    count--;
                    temp = temp.next;
                }
                int len1 = step;
                int len2 = step - count;
                //h1,h2都走完，开始合并
                while (len1 > 0 && len2 > 0) {
                    if (h1.value <= h2.value) {
                        pre.next = h1;
                        h1 = h1.next;
                        len1--;
                    } else {
                        pre.next = h2;
                        h2 = h2.next;
                        len2--;
                    }
                    pre = pre.next;
                }

                //两个待排序链表如果有剩下的部分，直接拼到pre后面
                while (len1 > 0) {
                    pre.next=h1;
                    h1 = h1.next;
                    pre = pre.next;
                    len1--;
                }
                while (len2 > 0) {
                    pre.next = h2;
                    h2 = h2.next;
                    pre = pre.next;
                    len2--;
                }
                //很关键，合并完后需要修改新的合并单元尾部pre指向下一个合并单元头部h，否则会进入死循环
                pre.next = temp;

            }

            step *= 2;
        }

        return dummy.next;

    }


    public static void main(String[] args) {
        int[] arr = {4, 3, 2, 6, 5, 7, 1};
        ListNode node = generateListNode(arr);
        printList(node);
        ListNode res = sortList(node);
        printList(res);

    }

}
