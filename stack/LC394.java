package stack;

import java.util.Stack;

/**
 * created by mercury on 2020-08-24
 *
 * 字符串解码
 *
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 *
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 *
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 *
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 *
 * 示例 1：
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 *
 * 示例 2：
 * 输入：s = "3[a2[c]]"
 * 输出："accaccacc"
 *
 * 示例 3：
 * 输入：s = "2[abc]3[cd]ef"
 * 输出："abcabccdcdcdef"
 *
 * 示例 4：
 * 输入：s = "abc3[cd]xyz"
 * 输出："abccdcdcdxyz"
 *
 */

public class LC394 {

    /**
     * 辅助栈法，用一个栈保存数字，一个栈保存字符串
     *
     * 用res保存结果，遍历字符串的每个字符
     * 当c为数字时，将字符转为数字multi，用于后续倍数计算
     * 当c为字母时，将c添加到res尾部
     * 当c为 [ 时，将当前multi和res入栈，并分别重置
     *      记录res，用于发现对应 ]后的拼接操作
     *      记录multi,用于发现对应] 后，获取multi x [...]字符串
     * 当c为 ] 时，stack出栈，res=last_res+cur_multi*res
     *      last_res是上个[到当前[的字符串，也就是字符栈的栈顶
     *      cur_multi是当前 [到 ]内字符串重复的倍数，也就是数字栈的栈顶
     *      res就是当前从前面的[到这里的]内存储的字符串
     */
    public static String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        Stack<Integer> numStack = new Stack<>();
        Stack<String> strStack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '[') {
                numStack.push(multi);
                strStack.push(res.toString());
                multi = 0;
                res = new StringBuilder();
            } else if (c == ']') {
                StringBuilder temp = new StringBuilder();
                int curMulti = numStack.pop();
                for (int i = 0; i < curMulti; i++) {
                    temp.append(res);
                }
                res = new StringBuilder(strStack.pop() + temp);
            } else if (c >= '0' && c <= '9') {
                //因为数字有可能大于等于10，所以如果下一个字符还是数字要累加 比如15[a]
                multi = 10 * multi + Integer.parseInt(c + "");
            } else {
                res.append(c);
            }
        }

        return res.toString();

    }


    public static void main(String[] args) {
        System.out.println(decodeString("3[a2[c]]"));

    }

}
