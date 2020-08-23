package dp;

import java.util.Arrays;

/**
 * created by mercury on 2020-08-23
 *
 * 零钱兑换
 *
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 *
 * 示例 1:
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 *
 * 示例 2:
 * 输入: coins = [2], amount = 3
 * 输出: -1
 *  
 *
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 *
 */

public class LC322 {

    /**
     * 以11为例，凑成面值11的最少硬币个数由以下三者中最小值得到：
     * 凑成面值为10的最小数+面值为1的这一枚
     * 凑成面值为9的最小数+面值为2的这一枚
     * 凑成面值为6的最小数+面值为5的这一枚
     *
     * 状态转移方程 dp[i]=min{dp[i],dp[i-coins[j]]+1} for [0,coins.length) if coins[j]<=i
     * 初始值dp[0]=0，因为金额为0不能由硬币凑成
     *
     * @param coins  不同面额的硬币
     * @param amount    总金额
     * @return
     */
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        //要比较的是最小值，给不可能凑出来的值，预先定义一个能区分开的就行。比如Integer.MAX_VALUE也是可以的
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }

        if (dp[amount] > amount) {
            return -1;
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        System.out.println(coinChange(coins, 11));
    }
}
