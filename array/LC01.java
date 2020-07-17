package array;

import java.util.Arrays;

/**
 * created by mercury on 2020-07-17
 *
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 *  
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 *
 */
public class LC01 {

    public static void main(String[] args) {
        int[] arr = {2, 4, 7, 5, 3, 1, 6, 9, 8};
        int[] result = twoSum1(arr, 10);
        System.out.println(Arrays.toString(result));
    }


    /**
     * 一遍哈希表，逆向思路，用元素的值做key，用对应的数组索引做value
     */
    public static int[] twoSum1(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {

                    result[0] = i;
                    result[1] = j;
                    return result;
                }

            }
        }
        return result;
    }
}
