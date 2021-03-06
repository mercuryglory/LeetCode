package dp;

/**
 * created by mercury on 2020-09-13
 *
 * 戳气球
 *
 * 有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 *
 * 现在要求你戳破所有的气球。如果你戳破气球 i ，就可以获得 nums[left] * nums[i] * nums[right] 个硬币。 这里的 left 和 right 代表和 i 相邻的两个气球的序号。注意当你戳破了气球 i 后，气球 left 和气球 right 就变成了相邻的气球。
 *
 * 求所能获得硬币的最大数量。
 *
 * 说明:
 *
 * 你可以假设 nums[-1] = nums[n] = 1，但注意它们不是真实存在的所以并不能被戳破。
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * 示例:
 *
 * 输入: [3,1,5,8]
 * 输出: 167
 * 解释: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 *      coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 *
 */

public class LC312 {

    /**
     * 最清楚的题解：https://leetcode-cn.com/problems/burst-balloons/solution/zhe-ge-cai-pu-zi-ji-zai-jia-ye-neng-zuo-guan-jian-/
     * 创建新数组arr，相当于在原数组nums的头尾各加了一个元素，值为1，方便计算并且避免数组越界，
     * 相似的处理方法还有{@link stack.LC84}
     *
     * dp[i][j]表示开区间(i,j)内能拿到的最多金币
     * 开区间的长度至少为3，从3开始一直扩大到n+2
     * 每一个开区间，左端点从0开始，一直到n+2-len
     * 在每一个开区间内，选择戳爆气球k是多个，枚举这几个k，从中选择最大的total来更新dp[i][j]
     * dp[i][j]=max{dp[i][k]+dp[k][j]+arr[i]*arr[k]*arr[j]}，
     * 这里j=i+len-1 （因为是开区间）
     */
    public static int maxCoins(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n + 2];
        arr[0] = 1;
        arr[n + 1] = 1;
        for (int i = 1; i <= n; i++) {
            arr[i] = nums[i - 1];
        }
        int[][] dp = new int[n + 2][n + 2];

        //开区间的长度，从3开始
        for (int len = 3; len <= n + 2; len++) {
            for (int i = 0; i <= n + 2 - len; i++) {
                int res = 0;
                for (int k = i + 1; k < i + len - 1; k++) {
                    int left = dp[i][k];
                    int right = dp[k][i + len - 1];
                    res = Math.max(res, left + right + arr[i] * arr[k] * arr[i + len - 1]);
                }
                dp[i][i + len - 1] = res;
            }
        }
        return dp[0][n + 1];
    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 5, 8};
        System.out.println(maxCoins(nums));

    }
}
