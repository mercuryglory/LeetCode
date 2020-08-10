package dp;

import java.util.*;

/**
 * created by mercury on 2020-08-10
 *
 * 单词拆分
 *
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 *
 * 说明：
 *
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 *
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * 示例 2：
 *
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 * 示例 3：
 *
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 *
 */
public class LC139 {

    /**
     * 其实也是动态规划，用dp[i]表示字符串s前i个字符组成的字符串s[0...i-1]能否被空格拆分为若干个字典中出现的单词
     * 题解：https://leetcode-cn.com/problems/word-break/solution/dan-ci-chai-fen-by-leetcode-solution/
     *
     * 这里有个技巧，用HashSet代替ArrayList判断是否包含字符串，可以将查找的时间复杂度降到O(1)
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>();
        int len = s.length();
        int maxLen = 0;
        for (String str : wordDict) {
            dict.add(str);
            maxLen = Math.max(maxLen, str.length());
        }
        //注意i从1开始，初始值dp[0]表示空串且合法。substring是左闭右开区间
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;

        for (int i = 1; i <= len; i++) {
            //这里可以做剪枝优化，如果分割点i到j的距离已经大于字典中最长的单词长度，就提前结束循环
            for (int j = i; j >= 0 && i - j <= maxLen; j--) {
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len];
    }


    public static void main(String[] args) {
        String s = "leetcode";
        String[] array = {"leet", "code"};
//        String s = "applepenapple";
//        String[] array = {"apple", "pen", "apple"};
//        String s = "catsandog";
//        String[] array = {"cats", "dog", "sand", "and", "cat"};

//        String s = "aaaaaaa";
//        String[] array = {"aaaa", "aaa"};
        List<String> dict = new ArrayList<>(Arrays.asList(array));
        System.out.println(wordBreak(s, dict));

    }

}
