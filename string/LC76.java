package string;

import java.util.HashMap;
import java.util.Map;

/**
 * created by mercury on 2020-09-09
 *
 * 最小覆盖子串
 *
 * 给你一个字符串 S、一个字符串 T 。请你设计一种算法，可以在 O(n) 的时间复杂度内，从字符串 S 里面找出：包含 T 所有字符的最小子串。
 *
 *  
 * 示例：
 *
 * 输入：S = "ADOBECODEBANC", T = "ABC"
 * 输出："BANC"
 *  
 *
 * 提示：
 *
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 *
 */

public class LC76 {

    /**
     * 滑动窗口
     * 用双指针left和right表示一个窗口
     *
     */
    public static String minWindow(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        //初始化每个字母出现次数
        for (int i = 0; i < t.length(); i++) {
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1);
        }

        int left = 0;
        int right = 0;
        int start = 0;
        //符合条件的最小窗口初始化值为-1的原因是，end<=s.len-1，而substring是左闭右开
        int end = -1;
        //初始值要保证大于第一次出现符合条件的窗口大小
        int curLen = Integer.MAX_VALUE;

        while (right < s.length()) {
            char rightStr = s.charAt(right);
            //map中是否含有当前字母，包含了才有意义
            if (map.containsKey(rightStr)) {
                //这样做的意义是如何判断当前窗口包含了所有字母
                map.put(rightStr, map.get(rightStr) - 1);
                while (match(map)) {
                    int tempLen = right - left + 1;
                    if (tempLen < curLen) {
                        start = left;
                        end = right;
                        curLen = tempLen;
                    }
                    char key = s.charAt(left);
                    if (map.containsKey(key)) {
                        //因为下一次循环左指针后移了，缩小窗口，前面—1了，那么相应次数要+1，有点像回溯
                        map.put(key, map.get(key) + 1);
                    }
                    left++;
                }
            }


            right++;
        }

        return s.substring(start, end + 1);
    }

    /**
     * 如果当前窗口包含了所有字母，那么根据之前遍历s，遇到相应的字母就将相应字母的次数-1，则窗口内每个字母出现的次数都
     * 大于等于map中的，减了之后，如果map中有一个次数大于0则窗口不符合
     */
    private static boolean match(Map<Character, Integer> map) {
        for (int val : map.values()) {
            if (val > 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(minWindow(s, t));
    }

}
