package dp;

import java.util.Arrays;

/**
 * created by mercury on 2020-08-22
 *
 * 最长上升子序列
 *
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 *
 * 示例:
 *
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 *
 * 说明:
 *
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 *
 */

public class LC300 {

    /**
     * dp[i]代表前i个数字的最长子序列长度
     * 设0<=j<i
     * 当nums[i]>nums[j]时，nums[i]可以连接在nums[j]之后，即dp[i]=dp[j]+1
     * 反之，此情况上升子序列不成立，跳过
     *
     * 转移方程 dp[i]=max{dp[i],dp[j]+1} for j in [0,i]
     * 初始化dp[i]所有值为1，因为每个元素自己也构成上升子序列，长度为1
     * 最后返回dp[i]中的最大值
     */
    public static int lengthOfLIS(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        int[] dp = new int[len];
        dp[0] = 1;
        int res = 0;
        Arrays.fill(dp, 1);
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(lengthOfLIS(nums));
    }

}
