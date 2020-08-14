package dp;

/**
 * created by mercury on 2020-08-13
 *
 * 乘积最大子数组
 *
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 *
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 *
 *
 */

public class LC152 {

    /**
     * 不同于{@link LC53}，这里的fmax(i)=max{fmax(i-1)*a[i],a[i]}是错误的，因为乘积有负负得正的情况，
     * 比如{5,6,-3,4,-3}，当前位置的最优解不一定是由前一个位置的最优解转移得到
     *
     * 但可以通过正负情况分类讨论
     * 如果当前位置时负数，我们期望以它前一个位置结尾的某个段的乘积也是负数，并且负数越小，乘积越大
     * 反过来，希望它前一个位置结尾的某个段的乘积是正数，并且尽可能大
     *
     * fmax=max{fmax(i-1)*a[i],a[i],fmin(i-1)*a[i]}
     * fmin=min{fmax(i-1)*a[i],a[i],fmin(i-1)*a[i]}
     *
     * 由于第i个状态只和第i-1个状态有关，可以用两个变量维护i-1时刻的状态
     */
    public static int maxProduct(int[] nums) {
        int res = nums[0];
        int fmax = nums[0];
        int fmin = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int max = fmax;
            int min = fmin;
            fmax = Math.max(max * nums[i], Math.max(nums[i], min * nums[i]));
            fmin = Math.min(max * nums[i], Math.min(nums[i], min * nums[i]));
            res = Math.max(fmax, res);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {2, -5, -2, -4, 3};
        System.out.println(maxProduct(nums));

    }
}
