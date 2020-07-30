package array;

import linkedlist.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * created by mercury on 2020-07-29
 *
 * 三数之和
 *
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 *  
 *
 * 示例：
 *
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *
 */

public class LC15 {

    /**
     * 双指针
     *
     * 先对数组排序，然后每次固定一个数nums[i]，再使用左右指针指，左指针指向i+1,右指针指向数组右边界，判断三个数的和是否等于0，
     * 满足则添加进集合，不满足则判断如果和>0,右指针左移，否则左指针右移
     *
     * 如果nums[i]>0，则三数和必然大于0，结束循环
     * 如果nums[i]==nums[i-1]，该数字重复，跳过
     * sum==0时，nums[left]=nums[left+1]会导致结果重复，left++
     * sum==0时，nums[right]=nums[right-1]会导致结果重复，right--
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return res;
        }
        Arrays.sort(nums);
        int len = nums.length;

        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = len - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }

        }

        return res;
    }


    public static void main(String[] args) {
        int[] arr = {-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(arr));

    }

}
