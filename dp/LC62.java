package dp;

/**
 * created by mercury on 2020-08-07
 *
 * 不同路径
 *
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 *
 * 问总共有多少条不同的路径？
 *
 * 示例 1:
 *
 * 输入: m = 3, n = 2
 * 输出: 3
 * 解释:
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向右 -> 向下
 * 2. 向右 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向右
 * 示例 2:
 *
 * 输入: m = 7, n = 3
 * 输出: 28
 *  
 *
 * 提示：
 *
 * 1 <= m, n <= 100
 * 题目数据保证答案小于等于 2 * 10 ^ 9
 *
 */

public class LC62 {

    /**
     * 动态规划
     *
     * 以dp[i][j]作为到达[i][j]的最多路径
     * 对于第一行或者第一列，因为都在边界，要么一直向右，要么一直向下，dp[i][j]=1
     * 对于其他行列的，到达它上面的元素和左边的元素最多有多少条路径，加起来就是到达它这里的结果
     *
     * 动态方程：dp[i][j]=dp[i-1]+dp[i][j-1]
     */
    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }

    //还有一种直接找规律的，走到最后需要(m-1+n-1)=m+n-2步，其中m-1步是向右的，从m+n-2步中选出m-1个，总共有C(m+n-2,m-1)中组合方式


    public static void main(String[] args) {
        System.out.println(uniquePaths(6, 4));
    }
}
