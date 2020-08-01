package string;

import java.util.ArrayList;
import java.util.List;

/**
 * created by mercury on 2020-08-
 *
 *数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 *  
 *
 * 示例：
 *
 * 输入：n = 3
 * 输出：[
 *        "((()))",
 *        "(()())",
 *        "(())()",
 *        "()(())",
 *        "()()()"
 *      ]
 *
 */

public class LC22 {

    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }

        dfs(res, "", n, n);

        return res;
    }

    /**
     * 深度优先遍历，递归+回溯
     *
     * 图解：https://leetcode-cn.com/problems/generate-parentheses/solution/hui-su-suan-fa-by-liweiwei1419/
     *
     * 1、当前左右括号都有大于0个可以使用时，才产生分支
     * 2、左分支，只看当前是否还有左分支可用
     * 3、右分支，还受左分支限制，如果左括号剩余数量严格大于右括号剩余数量，不符合要求，剪枝
     * 4、左右剩余括号都等于0时结算
     *
     * @param res    结果集
     * @param str    当前递归得到的结果
     * @param left   左括号还有几个可以使用
     * @param right  右括号还有几个可以使用
     */
    private static void dfs(List<String> res, String str, int left, int right) {
        if (left == 0 && right == 0) {
            res.add(str);
            return;
        }
        System.out.println(res);

        if (left > right) {
            return;
        }

        if (left > 0) {
            dfs(res, str + "(", left - 1, right);
        }
        if (right > 0) {
            dfs(res, str + ")", left, right - 1);
        }


    }

    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }
}
