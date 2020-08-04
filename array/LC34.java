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

        //target在数组中的开始位置
        int firstPosition = getLower(nums, target);
        if (firstPosition == -1) {
            return new int[]{-1, -1};
        }
        //target在数组中的结束位置
        int lastPosition = getHigher(nums, target);

        return new int[]{firstPosition, lastPosition};
    }


    /**
     * 考虑边界情况，判断条件和常见的二分有一些差别
     * 参考 https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/solution/si-lu-hen-jian-dan-xi-jie-fei-mo-gui-de-er-fen-cha/
     * 测试用例 [1],0   [1],2   [2,2],3
     *
     * 这里将等于的情况合并了，所以不是严格的mid+1和mid-1
     */
    private static int getLower(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        //这种循环条件不包括边界重合的情况，可以将数组中只有一个数的情况排除
        while (start < end) {
            int mid = (start + end) / 2;
            if (nums[mid] < target) {
                //下一轮搜索区间是[mid+1,right]
                start = mid + 1;
            } else {
                //下一轮搜索区间是[start,mid]
                end = mid;
            }
        }
        if (nums[start] == target) {
            return start;
        }
        return -1;
    }


    private static int getHigher(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        //注意这里，因为判断条件是start<end，如果mid不向上取的话，假设start=4,end=5，那么start=(4+5)/2=4,这一位上不去，
        //一直会陷入死循环。因为这里左边界收缩的条件是start=mid、不同于取开始位置时start=mid+1。所以这里mid会向上取
        while (start < end) {
            int mid = (start + end + 1) / 2;
            if (nums[mid] <= target) {
                //下一轮搜索区间是[mid,end]
                start = mid;
            } else {
                //下一轮搜索区间是[start,mid-1]
                end = mid - 1;
            }
        }

        //在取开始位置的方法里已经判断了该数组中是否存在该数，直接返回就好
        return start;
    }


    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int[] res = searchRange(nums, 8);
        System.out.println(Arrays.toString(res));
    }

}
