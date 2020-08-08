package array;

import java.util.Arrays;

/**
 * created by mercury on 2020-08-08
 *
 * 颜色分类
 *
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 *
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 *
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 *
 * 示例:
 *
 * 输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]
 *
 * 进阶：
 *
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。
 * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 *
 */

public class LC75 {

    public static void sortColors(int[] nums) {
        int len = nums.length;
        int zeroCount = 0;
        int oneCount = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] == 0) {
                zeroCount++;
            } else if (nums[i] == 1) {
                oneCount++;
            }
        }

        for (int i = 0; i < len; i++) {
            if (i < zeroCount) {
                nums[i] = 0;
            } else if (i < zeroCount + oneCount) {
                nums[i] = 1;
            } else {
                nums[i] = 2;
            }
        }

    }

    /**
     * 进阶的算法用了双指针，重点在于设计循环不变量，类似快排
     * 参考题解：https://leetcode-cn.com/problems/sort-colors/solution/kuai-su-pai-xu-partition-guo-cheng-she-ji-xun-huan/
     */
    public static void sortColors2(int[] nums) {
        int zero = 0;
        int two = nums.length;

        int i = 0;
        while (i < two) {
            if (nums[i] == 0) {
                swap(nums, zero, i);
                zero++;
                i++;
            } else if (nums[i] == 1) {
                i++;
            } else {
                two--;
                swap(nums, two, i);
            }
        }
    }

    private static void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {2, 0, 2, 1, 1, 0};
        sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}
