package stack;

import java.util.Stack;

/**
 * created by mercury on 2020-09-10
 *
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 *  
 * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
 *
 * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
 *
 */

public class LC84 {

    public static int largestRectangleArea(int[] heights) {
        int[] arr = new int[heights.length + 2];
        for (int i = 1; i < heights.length + 1; i++) {
            arr[i] = heights[i - 1];
        }

        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            System.out.println(stack);
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
