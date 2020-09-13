package string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * created by mercury on 2020-09-13
 *
 * 删除无效的括号
 *
 * 删除最小数量的无效括号，使得输入的字符串有效，返回所有可能的结果。
 *
 * 说明: 输入可能包含了除 ( 和 ) 以外的字符。
 *
 * 示例 1:
 * 输入: "()())()"
 * 输出: ["()()()", "(())()"]
 *
 *
 * 示例 2:
 * 输入: "(a)())()"
 * 输出: ["(a)()()", "(a())()"]
 *
 *
 * 示例 3:
 * 输入: ")("
 * 输出: [""]
 *
 */

public class LC301 {

    /**
     * 标准的dfs
     * https://leetcode-cn.com/problems/remove-invalid-parentheses/solution/biao-zhun-de-dfs-fei-chang-biao-zhun-by-xiaoweixia/
     */
    public static List<String> removeInvalidParentheses(String s) {
        //需要去重的原因是处理连续左括号或连续右括号的情况，比如(a)())()生成(a)()()，既可以删除第二个括号也可以删除
        //第三个括号，删除哪个产生的结果都是一样的
        Set<String> set = new HashSet<>();
        int index = 0;
        int leftCount = 0;
        int leftToDelete = 0;
        int rightToDelete = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                leftToDelete++;
            } else if (c == ')') {
                if (leftToDelete > 0) {
                    leftToDelete--;
                } else {
                    rightToDelete++;
                }
            }
        }
        dfs(s, index, leftCount, leftToDelete, rightToDelete, set, new StringBuilder());

        return new ArrayList<>(set);


    }

    /**
     *
     * @param s
     * @param index
     * @param leftCount     没有配对被保留下来的左括号数量
     * @param leftToDelete      要删除的左括号数量
     * @param rightToDelete     要删除的右括号数量
     * @param set
     * @param sb
     */
    private static void dfs(String s, int index, int leftCount, int leftToDelete, int rightToDelete,
                            Set<String> set, StringBuilder sb) {
        if (index == s.length()) {
            //最后找到符合条件时，一定是没有要删除的左右括号，并且没有多余的左括号
            if (leftCount == 0 && leftToDelete == 0 && rightToDelete == 0) {
                System.out.println(sb.toString());
                set.add(sb.toString());
            }
            return;
        }
        char c = s.charAt(index);
        if (c == '(') {
            if (leftToDelete > 0) {
                StringBuilder temp = new StringBuilder(sb);
                dfs(s, index + 1, leftCount, leftToDelete - 1,
                        rightToDelete, set, temp);
            }
            //左括号不删，剩余的左括号数量+1，等着配对
            StringBuilder temp = new StringBuilder(sb);
            temp.append(c);
            dfs(s, index + 1, leftCount + 1, leftToDelete, rightToDelete, set, temp);

        } else if (c == ')') {
            if (rightToDelete > 0) {
                StringBuilder temp = new StringBuilder(sb);
                dfs(s, index + 1, leftCount, leftToDelete, rightToDelete - 1, set, temp);
            }
            //右括号不删并且前面有'('，配对成功后剩余左括号数量-1
            if (leftCount > 0) {
                StringBuilder temp = new StringBuilder(sb);
                temp.append(c);
                dfs(s, index + 1, leftCount - 1, leftToDelete, rightToDelete, set, temp);
            }
        } else {
            //其他普通字符，无条件加入到结果中
            StringBuilder temp = new StringBuilder(sb);
            temp.append(c);
            dfs(s, index + 1, leftCount, leftToDelete, rightToDelete, set, temp);
        }
    }


    public static void main(String[] args) {
        String s = "(a)())()";
        System.out.println(removeInvalidParentheses(s));
    }

}
