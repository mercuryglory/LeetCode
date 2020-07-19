package dp;

/**
 * created by mercury on 2020-07-19
 * <p>
 * 打家劫舍
 */

public class LC198 {

    /**
     * 递归不行，会超时，还是考虑动态规划
     *
     * 如果房屋数量大于两间，用dp[i]表示前i间房屋能偷到的最高金额，得到状态转移方程
     * dp[i] = max{ dp[i-2] + nums[i], dp[i-1] }
     * 排除掉边界条件，因为一定满足dp[i]>=dp[i-1]，所以dp[n-1]就是要求的结果
     *
     * 该方法空间复杂度为O(n)，再优化一下，考虑到每间房屋的最高总金额只和该房屋的前两间房屋的最高总金额有关，
     * 可以用两个数，在每次循环时只存储这两个值
     *
     */
    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }

        int first = nums[0];
        int second = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }

        return second;



    }



    public static void main(String[] args) {
        int[] arr = {114, 117, 207, 117, 235, 82, 90, 67, 143, 146, 53, 108, 200, 91, 80, 223, 58, 170, 110, 236, 81, 90, 222, 160, 165, 195, 187, 199, 114, 235, 197, 187, 69, 129, 64, 214, 228, 78, 188, 67, 205, 94, 205, 169, 241, 202, 144, 240};

        System.out.println(rob(arr));

    }

}
