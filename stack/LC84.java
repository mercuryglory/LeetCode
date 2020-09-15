package stack;

import java.util.Stack;

/**
 * created by mercury on 2020-09-10
 *
 * 柱状图中最大的矩形
 *
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 *  
 * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
 *
 * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
 *
 */

public class LC84 {

    /**
     * 基础的解法是，对于每一个柱子，以当前柱子的高作为矩形的高，我们要找到它的左右边界，即向左找到第一个高度小于
     * 当前柱体高度，向右找到第一个高度小于当前柱体高度
     * <p>
     * 优化思路是用单调栈，栈中下一个柱体就是左边第一个高度小于自身的柱体
     * 用栈遍历每个柱体，若当前柱体高度>=栈顶柱体的高度，就直接将当前柱体入栈
     *
     */
    public static int largestRectangleArea(int[] heights) {
        //在柱体数组的头和尾加了两个高度为0的柱体，很妙，可以简化逻辑。因为开始的时候栈为空，而遍历到最后如果栈不为空需要
        //弹出栈中所有元素。或者理解为，头的左边没有值，怎么找左边界？尾的右边没有值，怎么找右边界？
        int[] arr = new int[heights.length + 2];
        for (int i = 1; i < heights.length + 1; i++) {
            arr[i] = heights[i - 1];
        }

        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            //找到了右边第一个高度小于栈顶柱体高度的柱体，右边界确定，再向左找到所有符合条件的柱体。因为左边界已经确定，
            //只要是大于当前柱体高度的柱体都要计算，然后比较面积
            while (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
                int top = stack.pop();
                res = Math.max(res, (i - stack.peek() - 1) * arr[top]);
            }
            System.out.println(res);
            stack.push(i);
        }

        return res;

    }


    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println(largestRectangleArea(heights));

    }
}
