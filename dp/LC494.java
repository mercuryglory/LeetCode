package dp;

/**
 * created by mercury on 2020-08-29
 *
 * 目标和
 *
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
 *
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 *
 *
 * 示例：
 * 输入：nums: [1, 1, 1, 1, 1], S: 3
 * 输出：5
 * 解释：
 *
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 *
 * 一共有5种方法让最终目标和为3。
 *
 * 提示：
 *
 * 数组非空，且长度不会超过 20 。
 * 初始的数组的和不会超过 1000 。
 * 保证返回的最终结果能被 32 位整数存下。
 *
 */

public class LC494 {

    /**
     * 将数组看成两部分，P和N，P都使用正号，N都使用负号，因为数组中的元素都>=0，有以下推导
     * sum(P)-sum(N)=target，并且sum(P)+sum(N)=数组的和
     * sum(P)+sum(N)+sum(P)-sum(N)=2*sum(P)=target+sum(nums)
     * 因此只要找到一个子集，都取正号，并且和等于(target+sum(nums))/2，就证明存在解
     * 将问题转化为类似{@link LC416}
     *
     * 状态方程 dp[i][j]=dp[i-1][j]+dp[i-1][j-nums[i]]
     * 压缩状态
     */
    public static int findTargetSumWays(int[] nums, int target) {
        int len = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        //剪枝，如果都按正号加的和必须大于等于target，或者根据前面推导target+sum肯定是偶数。有一项不符合，直接返回0
        if (sum < target || (target + sum) % 2 == 1) {
            return 0;
        }

        target = (target + sum) / 2;
        int[] dp = new int[target + 1];
        dp[0] = 1;

        /**
         * 注意这里和{@link LC416}的不同，外层循环从0开始
         */
        for (int i = 0; i < len; i++) {
            for (int j = target; nums[i] <= j; j--) {
                dp[j] = dp[j] + dp[j - nums[i]];
            }
        }
        return dp[target];

    }


    public static void main(String[] args) {
        int[] nums = {0, 0, 0, 0, 0, 0, 0, 0, 1};
        System.out.println(findTargetSumWays(nums, 1));
    }


}
