package array;

import java.util.Arrays;
import java.util.Collections;

/**
 * created by mercury on 2020-08-01
 *
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 *
 * 你可以假设数组中不存在重复的元素。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 示例 1:
 *
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 示例 2:
 *
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 *
 */
public class LC33 {

    /**
     * 二分将数组分为两部分
     *
     * 如果[left,mid-1]是有序数组，即arr[left]<=arr[mid]，并且满足arr[left]<=target<arr[mid]，则应该将
     * 搜索范围缩小至[left,mid-1],否则在[mid+1,right]中寻找
     *
     * 如果[mid,right]是有序数组，且满足arr[mid]<target<=arr[right]，则将搜索范围缩小至[mid+1,right]，
     * 否则在[left,mid-1]中寻找
     *
     * 如果arr[mid]==target，找到了
     *
     * 这就是利用了有序数组可以确定左右取值范围的特点，并且nums[left]<=nums[mid]则左半边有序，反之右半边有序
     *
     */
    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }


        int left = 0;
        int right = nums.length - 1;

        //小于等于是因为（l+r）/2是把小数省去的结果，避免漏掉要查找的元素刚好落在边界上的情况
        while (left <= right) {
            int mid = (left + right) / 2;

            if (nums[mid] == target) {
                return mid;
            }
            System.out.println(nums[left] + "," + nums[right]);
            if (nums[left] <= nums[mid]) {
                //至于这里target和mid不用判断==,是因为前面已经判断过了
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;

                }
            }
        }

        return -1;

    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(search(nums, 0));
    }
}
