package string;

import java.util.HashMap;
import java.util.Map;

/**
 * created by mercury on 2020-07-26
 *
 * 无重复字符的最长子串
 *
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 */
public class LC03 {


    /**
     * 滑动窗口
     * 1、定义一个map，key为字符，value为字符位置+1,表示从字符后一个位置开始才不重复
     * 2、定义不重复子串的开始位置start,结束位置end
     * 3、end不断向后，会遇到与[start,end]区间内字符相同的情况，获取该字符在map中对应的value，并更新start,
     * 此时[start,end]不存在重复字符
     * 4、每次循环都要更新结果res和map
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 1;
        }

        Map<Character, Integer> map = new HashMap<>();
        int res = 0;
        for (int start = 0, end = 0; end < s.length(); end++) {
            char i = s.charAt(end);
            if (map.containsKey(i)) {
                start = Math.max(start, map.get(i));
            }

            res = Math.max(res, end - start + 1);

            map.put(i, end + 1);
        }

        return res;


    }


    public static void main(String[] args) {
        String str = "pwwkew";
        System.out.println(lengthOfLongestSubstring(str));
    }

}
