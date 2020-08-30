package string;

/**
 * created by mercury on 2020-08-30
 *
 * 回文子串
 *
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 *
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 *
 * 示例 1：
 * 输入："abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 *
 * 示例 2：
 * 输入："aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 *  
 * 提示：
 *
 * 输入的字符串长度不会超过 1000 。
 *
 */
public class LC647 {

    /**
     * 类似{@link LC05}，也可以用Manacher(马拉车算法）求解，时间复杂度能降为O(n)，不太好理解
     *
     * 中心扩散法，枚举回文中心时，为了将回文长度是奇数和偶数两种情况统一，假设n=4，把可能的回文中心列出来
     * 得出长度为n的字符串会生成2n-1组回文中心[li,ri],其中li=i/2,ri=li+(i%2)
     * 只要从0到2n-2挨个遍历，就能得到所有可能的回文中心，然后判断是否为回文
     *
     * https://leetcode-cn.com/problems/palindromic-substrings/solution/hui-wen-zi-chuan-by-leetcode-solution/
     */
    public static int countSubStrings(String s) {
        int len = s.length();
        int ans = 0;
        for (int i = 0; i < 2 * len - 1; i++) {
            int left = i / 2;
            int right = i / 2 + i % 2;
            while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
                ans++;
            }
        }

        return ans;

    }


    public static void main(String[] args) {
        System.out.println(countSubStrings("aaa"));
    }
}
