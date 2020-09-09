package dp;

/**
 * created by mercury on 2020-09-05
 *
 * 正则表达式匹配
 *
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 *
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *
 * 说明:
 *
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 *
 *
 * 示例 1:
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 *
 *
 * 示例 2:
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 *
 *
 * 示例 3:
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 *
 *
 * 示例 4:
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 *
 *
 * 示例 5:
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 *
 */

public class LC10 {

    /**
     * 直观用递归，然后可过渡成动态规划
     *
     * dp[i][j]表示字符串s中前i个字符与p中前j个字符是否匹配
     * 两个空字符是匹配的，因此初始化dp[0][0]=true
     *
     * 如果我们已经得出了前i-1个和前j-1个字符的匹配情况dp[i-1][j-1]，只要对p中特殊的'.'和'*'多加注意
     * 因为下标从0开始，前i个其实是[0,i-1]的元素，因此遍历判断匹配当前字符串时都以i-1比较
     *
     * 匹配逻辑是，如果p[j-1]=='.'，代表任意字符，相当于s[i-1]==p[j-1]。否则比较s[i-1]和p[j-1]
     * 然后分情况讨论：
     * 1、如果p[j-1]不等于'*'，那么如果匹配上了，则dp[i][j]取决于dp[i-1][j-1]
     * 2、如果p[j-1]等于'*'，又分两种情况，比较p[j-1]前面的字符p[j-2]
     *    若p[j-2]能与当前s[i-1]匹配，则dp[i][j]取决于dp[i-1][j]。同时若dp[i-1][j]=false,我们
     *    还可以跳过p[j-1]不用他了，直接看s的前i个字符能否与p的前j-2个字符匹配，即dp[i][j-2]
     *
     *    若不匹配，就是p[j-1]使用0次，还是看dp[i][j-2]
     *
     */
    public static boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        for (int i = 0; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) != '*') {
                    if (match(s, p, i, j)) {
                        //到这里时一定能保证i>=1
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                } else {
                    //这个地方没做校验，认为题目能保证p的合法性，因为j=1时p[0]!='*'，否则*前面没有可以匹配的字符
                    //所以到这里j>=2，不会造成数组越界
                    dp[i][j] = match(s, p, i, j - 1) ? dp[i - 1][j] || dp[i][j - 2] : dp[i][j - 2];
                }
            }
        }


        return dp[m][n];
    }

    public static boolean match(String s, String p, int i, int j) {
        //字符串s中0个字符肯定和p中的j(j>=1)个字符不匹配
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }


    public static void main(String[] args) {
        String s = "aab";
        String p = "a*b";
        System.out.println(isMatch(s, p));
    }
}
