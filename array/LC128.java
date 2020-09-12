package array;

import java.util.HashSet;
import java.util.Set;

/**
 * created by mercury on 2020-09-12
 *
 * 最长连续子序列
 *
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 *
 * 要求算法的时间复杂度为 O(n)。
 *
 * 示例:
 *
 * 输入: [100, 4, 200, 1, 3, 2]
 * 输出: 4
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 *
 */

public class LC128 {

    /**
     * 考虑枚举数组中的每个数x，以其为起点，不断尝试x+1,x+2..是否存在。假设一直匹配到x+y，则其长度为y+1
     * 匹配的过程比较好的是用哈希表存储数组中的数，仅仅查看是否存在用HashSet就可以，并且时间复杂度O(1)
     *
     * 对于如何进一步优化，其实外层循环有很多不必要的枚举。比如已经存在以x为起点，x+1、x+2、x+y是连续序列，而在数组中
     * 碰到x+1或x+2..x+y又尝试匹配，但得到的结果不会优于枚举x为起点的答案。
     * 因此，我们要枚举的数x，一定是在数组中不存在前驱数x-1的。所以遍历时判断是否包含x-1，如果包含则跳过进行下一次循环
     */
    public static int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int maxLen = 0;
        for (int num : nums) {
            if (!set.contains(num - 1)) {
                int curLen = 0;
                int target = num;
                while (set.contains(target)) {
                    target++;
                    curLen++;
                }
                maxLen = Math.max(maxLen, curLen);
            }
        }
        return maxLen;

    }


    public static void main(String[] args) {
        int[] nums = {100, 4, 200, 1, 3, 2};
        System.out.println(longestConsecutive(nums));

    }
}
