package array;

import java.util.Arrays;

/**
 * created by mercury on 2020-08-04
 *
 * 在排序数组中查找元素的第一个和最后一个位置
 *
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 如果数组中不存在目标值，返回 [-1, -1]。
 *
 * 示例 1:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * 示例 2:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 *
 */
public class LC34 {

    public static int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length < 1) {
            return new int[]{-1, -1};
        }

        int first = find(nums, target, true);
        int last = find(nums, target, false);
        return new int[]{first, last};
    }


    /**
     * 考虑边界情况，判断条件和常见的二分有一些差别
     * 测试用例 [1],0   [1],2   [2,2],3
     *
     */
    private static int find(int[] nums, int target, boolean lower) {
        int left = 0;
        int right = nums.length - 1;
        int ans = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                ans = mid;
                if (lower) {
                    //找第一个出现的位置，不断左移右指针，减小mid
                    right = mid - 1;
                } else {
                    //找最后一个出现的位置，不断左移右指针，增加mid
                    left = mid + 1;
                }
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int[] res = searchRange(nums, 8);
        System.out.println(Arrays.toString(res));
    }

}
