package dp;

/**
 * created by mercury on 2020-09-08
 *
 * 编辑距离
 *
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 *
 * 你可以对一个单词进行如下三种操作：
 *
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *  
 *
 * 示例 1：
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 *
 *
 * 示例 2：
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 *
 */

public class LC72 {

    /**
     * 题解：https://leetcode-cn.com/problems/edit-distance/solution/bian-ji-ju-chi-by-leetcode-solution/
     *
     * 分类讨论
     * 在单词A中插入一个字符，比如horse到ro编辑距离为a，则horse到ros编辑距离不会超过a+1
     * 在单词B中插入一个字符，比如hors到ros编辑距离为b，则horse到ros编辑距离不会超过b+1
     * 修改单词A的一个字符，比如hors到ro编辑距离为c，那么horse到ros编辑距离不会超过c+1
     * 所以horse到ros编辑距离最小值为min{a+1,b+1,c+1}
     *
     * 将问题一直拆分下去，直到
     * 字符串A为空，如从""到ro,编辑距离为B的长度=2
     * 字符串B为空，如从horse到"",编辑距离为A的长度=5
     *
     * 因此用dp[i][j]表示A的前i个字母和B的前j个字母之间的编辑距离
     * 特别的，如果是修改字符的情况,若A的第i个字符和B的第j个字符相同，那么实际上不需要修改
     *
     * 状态转移方程
     * if(A[i]==B[j]) dp[i][j]=min{dp[i][j-1]+1,dp[i-1][j]+1,dp[i-1][j-1]}
     * else dp[i][j]=min{dp[i][j-1]+1,dp[i-1][j]+1,dp[i-1][j-1]+1}
     * 初始化dp[i][0]=i，相当于对word1执行i次删除
     * dp[0][j]=j,相当于对word1执行j次插入
     */
    public static int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        //如果有一个字符串为空的特殊情况
        if (m * n == 0) {
            return m + n;
        }
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m + 1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < n + 1; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                int addA = dp[i][j - 1] + 1;
                int addB = dp[i - 1][j] + 1;
                int modA = dp[i - 1][j - 1] + 1;
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    modA -= 1;
                }
                dp[i][j] = Math.min(Math.min(addA, addB), modA);
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        String word1 = "intention";
        String word2 = "execution";
        System.out.println(minDistance(word1, word2));

    }
}
