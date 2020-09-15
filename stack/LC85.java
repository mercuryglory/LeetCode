package stack;

import java.util.Stack;

/**
 * created by mercury on 2020-09-11
 *
 * 最大矩形
 *
 *
 * 给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 * 示例:
 *
 * 输入:
 * [
 *   ["1","0","1","0","0"],
 *   ["1","0","1","1","1"],
 *   ["1","1","1","1","1"],
 *   ["1","0","0","1","0"]
 * ]
 * 输出: 6
 *
 */

public class LC85 {

    /**
     * 看图说话：https://leetcode-cn.com/problems/maximal-rectangle/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-1-8/
     * 求出每一层(行)的heights[i]，然后用{@link LC84#largestRectangleArea(int[])}求解
     */
    public static int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int[] height = new int[matrix[0].length];
        int res = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    height[j] += 1;
                } else {
                    height[j] = 0;
                }
            }
            //求出每一层的只包含1的最大矩形面积
            res = Math.max(res, largestRectangleArea(height));
        }

        return res;
    }

    public static int largestRectangleArea(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int[] arr = new int[height.length + 2];
        for (int i = 1; i < height.length + 1; i++) {
            arr[i] = height[i - 1];
        }
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
                int top = stack.pop();
                res = Math.max(res, (i - stack.peek() - 1) * arr[top]);
            }
            stack.push(i);
        }
        return res;

    }

    public static void main(String[] args) {
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '1', '1', '0'}};
        System.out.println(maximalRectangle(matrix));

    }
}
