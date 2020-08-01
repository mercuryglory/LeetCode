package array;

import java.util.Arrays;
import java.util.Collections;

/**
 * created by mercury on 2020-08-01
 *
 * 下一个排列
 *
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 *
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 *
 * 必须原地修改，只允许使用额外常数空间。
 *
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 *
 */
public class LC31 {

    /**
     * 降序的数组显然不可能有下一个排列
     *
     * 下一个更大的序列，一定是在接近低位获得
     *
     * 从右边开始找到第一对数字，满足arr[i+1]>arr[i],那么i+1右边的都是降序排列
     * 这时候重新从右边开始，找到第一个大于arr[i]的索引j，和arr[i]交换
     * 注意这时候从arr[i+1]到最右边都是降序排列，因此要翻转这一部分，将其变为该区间最小的值
     */
    public static void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 1) {
            return;
        }

        int i = nums.length - 2;
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= i && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }

        reverse(nums, i + 1, nums.length - 1);

    }

    private static void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private static void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }


    public static void main(String[] args) {
        int[] nums = {1, 3, 2};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

}
