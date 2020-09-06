package stack;

import java.util.Stack;

/**
 * created by mercury on 2020-09-06
 *
 * 最长有效括号
 *
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 *
 * 示例 1:
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 *
 *
 * 示例 2:
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 *
 */

public class LC32 {

    /**
     * 保持栈底元素为当前已经遍历过的元素中最后一个没有被匹配的右括号的下标，其他元素维护左括号的下标，方便边界处理
     * 因为子串的长度=j-i+1，每次遇到')'，都先弹出栈顶表示匹配，
     * 如果栈不为空，则当前右括号下标-栈顶元素=以该右括号结尾的最长有效括号的长度
     * 如果栈为空，当前右括号为没有被匹配的（因为空栈前的栈顶是先前没有被匹配的右括号，两个右括号肯定不匹配）
     * 则将下标入栈，更新"最后一个没有被匹配的右括号的下标"
     */
    public static int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.empty()) {
                    stack.push(i);
                } else {
                    res = Math.max(res, i - stack.peek());
                }
            }
        }

        return res;
    }


    public static void main(String[] args) {
        String s = ")()())";
        System.out.println(longestValidParentheses(s));

    }
}
