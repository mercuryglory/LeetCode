package stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * created by mercury on 2020-08-30
 *
 * 每日温度
 *
 * 请根据每日气温列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 *
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 *
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 *
 */

public class LC739 {

    /**
     * 定义一个栈，存放的是数组元素的下标。定义res存放结果
     * 遍历数组，在下标入栈前，如果当前栈不为空，并且当前元素大于栈顶下标对应的元素，则栈顶下标出栈，表示已经找到了比该下标
     * 对应气温更高的下标。并且将res对应出栈下标的元素更新为i和出栈下标之间的差值，表示出栈下标后多少位置能找到更高的气温
     */
    public static int[] dailyTemperatures(int[] temp) {
        int[] res = new int[temp.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < temp.length; i++) {
            while (!stack.isEmpty() && temp[i] > temp[stack.peek()]) {
                int index = stack.pop();
                res[index] = i - index;
            }
            stack.push(i);
        }
        return res;

    }

    public static void main(String[] args) {
        int[] temp = {73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println(Arrays.toString(dailyTemperatures(temp)));

    }
}
