package dp;

/**
 * created by mercury on 2020-08-27
 *
 * 分割等和子集
 *
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 * 注意:
 *
 * 每个数组中的元素不会超过 100
 * 数组的大小不会超过 200
 *
 * 示例 1:
 * 输入: [1, 5, 11, 5]
 * 输出: true
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 *  
 *
 * 示例 2:
 * 输入: [1, 2, 3, 5]
 * 输出: false
 * 解释: 数组不能分割成两个元素和相等的子集.
 *
 */

public class LC416 {

    /**
     * 问题转化为:能否从数组中挑出一些正整数，其和刚好等于整个数组元素的和的一半。并且数组的和一定得是偶数
     * 画一个len行，target+1列的表格，这里target=sum/2
     *
     * dp[i][j]表示从数组的[0,i]子区间内选取一些正整数，每个数只能用一次，使其和刚好等于j
     * 对于每一个元素nums[i]，都只有选和不选两种状态
     * 不选nums[i]，那么dp[i][j]=true的条件就是[0,i-1]区间内有一部分元素和为j
     * 选nums[i]，那么dp[i][j]=true的条件就是[0,i-1]区间内有一部分元素和为j-nums[i]
     * dp[i][j]=dp[i-1][j] || dp[i-1][j-nums[i]]
     * 容易看出需要满足j-nums[i]>=0
     * j==nums[i]是符合的，说明单独的nums[i]刚好单独的分割为一组
     *
     * 空间优化，题解说的很好：
     * https://leetcode-cn.com/problems/partition-equal-subset-sum/solution/0-1-bei-bao-wen-ti-xiang-jie-zhen-dui-ben-ti-de-yo/
     */
    public static boolean canPartition1(int[] nums) {
        int len = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        //如果数组的和是奇数，不可能分割成相等的两组，直接返回false
        if ((sum & 1) == 1) {
            return false;
        }

        int target = sum / 2;
        boolean[][] dp = new boolean[len][target + 1];

        //填表格第0行，第1个数只能让容积为自己的背包装满
        if (nums[0] <= target) {
            dp[0][nums[0]] = true;
        }

        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= target; j++) {
                if (j == nums[i]) {
                    dp[i][j] = true;
                    continue;
                }
                if (nums[i] < j) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                } else {
                    dp[i][j] = false;
                }
            }
        }

        return dp[len - 1][target];

    }


    /**
     * 优化点：
     * 1、dp[0][0]=true，根据前面j==nums[i]的解释，其实dp[i][0]都可以设置为true
     * 2、可以观察到只要or的结果为真，表格这一列下面所有的值都为真。因此只要最后一列是true，就可以提前结束，相当于剪枝
     * 3、空间优化，压缩到一维，要采用逆序。如果不逆序，无法保证在外层循环i保持不变的情况下，dp[j-nums[i]]的值不会被
     *   当前所放入的nums[i]修改，也就是说避免前面对后面产生影响
     */
    public static boolean canPartition(int[] nums) {
        int len = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) == 1) {
            return false;
        }

        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        if (nums[0] <= target) {
            dp[nums[0]] = true;
        }
        for (int i = 1; i < len; i++) {
            for (int j = target; nums[i] <= j; j--) {
                if (dp[target]) {
                    return true;
                }
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }

        return dp[target];

    }

    public static void main(String[] args) {
        int[] nums = {1, 5, 11, 5};
        System.out.println(canPartition(nums));

    }
}
