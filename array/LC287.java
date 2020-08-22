package array;

/**
 * created by mercury on 2020-08-22
 *
 * 寻找重复数
 *
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
 *
 * 示例 1:
 *
 * 输入: [1,3,4,2,2]
 * 输出: 2
 * 示例 2:
 *
 * 输入: [3,1,3,4,2]
 * 输出: 3
 * 说明：
 *
 * 不能更改原数组（假设数组是只读的）。
 * 只能使用额外的 O(1) 的空间。
 * 时间复杂度小于 O(n2) 。
 * 数组中只有一个重复的数字，但它可能不止重复出现一次。
 *
 */

public class LC287 {

    /**
     * 如果数组中没有重复的数，那么从下标0出发，以0上的值作为新的下标，再访问下个数组中的节点，以此类推，直到索引越界
     * 如果由重复的数，就会一直循环，类似环形链表
     *
     * 已知该数组确定存在一个重复的整数，问题就转化为求环形链表的入口
     * 这里快慢指针就设置为slow=nums[slow]，fast=nums[nums[fast]]
     */
    public static int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        int pre = 0;
        while (pre != slow) {
            pre = nums[pre];
            slow = nums[slow];
        }
        return pre;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1};
        System.out.println(findDuplicate(nums));

    }


}
