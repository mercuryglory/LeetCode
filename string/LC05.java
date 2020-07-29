package string;

import java.util.HashMap;
import java.util.Map;

/**
 * created by mercury on 2020-07-27
 *
 * 最长回文子串
 *
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 *
 * 输入: "cbbd"
 * 输出: "bb"
 *
 */

public class LC05 {

    /**
     * 动态规划，时间复杂度O(N2),空间复杂度O(N2)
     *
     * 1、如果一个字符串的头尾两个字符不相等，那它一定不是回文串
     * 2、如果相等，还要分情况讨论：
     *    2.1 如果里面的子串是回文，整体就是回文
     *    2.2 如果里面的子串不是回文，整体就不是回文串
     *
     * 思路
     * 1、定义状态，二维数组dp[i][j]表示子串s[i..j]是否为回文子串
     * 2、状态转移方程 dp[i][j]=(s[i]==s[j])&& dp[i+1][j-1]
     *    i<=j，边界条件是[i+1,j-1]不构成区间，即长度小于2，得出
     *    j-1-(i+1)<2 即j-i<3.可以得出在s[i]==s[j]和j-i<3时，
     *    dp[i][j]=true，否则就执行状态转移
     *
     * 3、内层循环完成一次后，比较并记录值
     */
    public static String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int begin = 0;
        int maxLen = 1;

        boolean[][] dp = new boolean[len][len];
        char[] arr = s.toCharArray();

        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if (arr[i] != arr[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                //只要dp[i][j]==true成立，就表示子串s[i..j]是回文，此时比较并记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    begin = i;
                    maxLen = j - i + 1;
                }
            }
        }

        return s.substring(begin, begin + maxLen);

    }


    /**
     * 中心扩散法，时间复杂度O(N2),空间复杂度O(1)
     *
     * 遍历每一个索引，以该索引为中心，利用回文串中心对称的特点，往两边扩散，看最多能扩散多远
     * 注意回文串长度为奇数或偶数，回文中心的形式不一样：
     * 1、奇数回文串的中心是一个具体字符，比如"aba"的中心是"b"
     * 2、偶数回文串的中心是位于两个字符之间的空字符串，比如"abba"的中心是""
     *
     */
    public static String longestPalindrome2(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int begin = 0;
        int maxLen = 1;
        for (int i = 0; i < len; i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len3 = Math.max(len1, len2);
            if (len3 > maxLen) {
                begin = i - (len3 - 1) / 2;
                maxLen = len3;
            }
        }
        return s.substring(begin, begin + maxLen);
    }


    private static int expandAroundCenter(String s, int left, int right) {
        int i = left;
        int j = right;
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }
        System.out.println(j - i - 1);
        return j - i - 1;
    }



    //暴力穷举，时间复杂度O（N3)
    public static String longestPalindrome1(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int begin = 0;
        int maxLen = 1;
        char[] arr = s.toCharArray();

        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (j - i + 1 > maxLen && isValidate(arr, i, j)) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }

        return s.substring(begin, begin + maxLen);

    }

    private static boolean isValidate(char[] arr, int i, int j) {
        while (i < j) {
            if (arr[i] != arr[j]) {
                return false;
            }
            i++;
            j--;
        }

        return true;
    }

    public static void main(String[] args) {
        String s = "ccc";
        System.out.println(longestPalindrome2(s));

    }
}
