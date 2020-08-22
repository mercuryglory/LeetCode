package dp;

/**
 * created by mercury on 2020-08-22
 *
 * 完全平方数
 *
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 *
 * 示例 1:
 *
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 * 示例 2:
 *
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 *
 */

public class LC279 {

    /**
     * 假设n=12,那么可以拆分为：1+11、4+8、9+3
     * 1+11，如果将11分解成平方和所需最小个数为n1，则总共需要n1+1个数
     * 同理4+8需要n2+1个，9+3需要n3+1个
     *
     * 将平方数的个数看为1，那么只要平方数小于等于目标值，则最小个数numSquares(n)=min{numSquares(n-k)+1} k=1,4,9...
     */
    public static int numSquares(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            //最差情况就是全都由1组成，需要i个数
            dp[i] = i;
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(numSquares(6));
    }
}
