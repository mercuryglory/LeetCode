package dp;

/**
 * created by mercury on 2020-08-09
 *
 * 不同的二叉搜索树
 *
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 *
 * 示例:
 *
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 */

public class LC96 {

    /**
     * 假设n个节点存在二叉搜索树的个数是G(n),f(i)为以i为根的的二叉搜索树个数
     * G(n)=f(1)+f(2)+...+f(n)
     *
     * 当i为根节点时，其左子树节点个数为i-1个，右子树节点个数为n-i个，而G(n)和序列长度有关，和内容无关
     * 因此f(i)=G(i-1)*G(n-i)
     *
     * 综合二者得到卡特兰公式：
     * G(n)=G(0)*G(n-1)+G(1)*G(n-2)+...+G(n-1)*G(0)
     */
    public static int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(numTrees(3));

    }

}
