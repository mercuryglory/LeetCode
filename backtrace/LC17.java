package backtrace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * created by mercury on 2020-07-30
 *
 * 电话号码的字母组合
 *
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 *
 * 给出数字到字母的映射如下。注意 1 不对应任何字母。
 *
 *
 * 示例:
 *
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 *
 *
 */

public class LC17 {

    private static List<String> result = new ArrayList<>();

    private static Map<String, String> map = new HashMap<String,String>(){{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};


    public static List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return result;
        }

        backTrace("", digits);

        return result;
    }

    /**
     * 循环+递归，回溯
     *
     */
    public static void backTrace(String combination, String nextDigits) {
        if (nextDigits.length() == 0) {
            result.add(combination);
        } else {
            String digit = nextDigits.substring(0, 1);
            String letters = map.get(digit);
            for (int i = 0; i < letters.length(); i++) {
                String letter = letters.substring(i, i + 1);
                //这里就是回溯，利用了String的不可变特性，每次循环递归操作都会生成一个新的字符串，不影响循环和递归每一层的结果
                backTrace(combination + letter, nextDigits.substring(1));
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(letterCombinations("23"));

    }
}
