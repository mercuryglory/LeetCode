package dp;

/**
 * created by mercury on 2020-09-07
 *
 * 接雨水
 *
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 *
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
 * 感谢 Marcos 贡献此图。
 *
 * 示例:
 *
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 *
 */
public class LC42 {


    /**
     * 用数组提前存储每个元素的leftMax和rightMax
     * 示意图：https://leetcode-cn.com/problems/trapping-rain-water/solution/jie-yu-shui-by-leetcode/
     *
     * 找到数组中从下标i到最左端最高的条形块高度leftMax[i]
     * 找到数组中从下标i到最右端最高的条形块高度rightMax[i]
     * 扫描数组，累加min{leftMax[i],rightMax[i]}-height[i]到结果上
     */
    public static int trap(int[] height) {
        int res = 0;
        int len = height.length;
        int[] leftMax = new int[len];
        int[] rightMax = new int[len];
        leftMax[0] = height[0];
        for (int i = 1; i < len; i++) {
            leftMax[i] = Math.max(height[i], leftMax[i - 1]);
        }
        rightMax[len - 1] = height[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            rightMax[i] = Math.max(height[i], rightMax[i + 1]);
        }

        for (int i = 0; i < len; i++) {
            res += Math.min(leftMax[i], rightMax[i]) - height[i];
        }

        return res;
    }


    //暴力解法，遍历数组每个元素时，从当前元素向左扫描得到maxLeft，向右扫描得到maxRight。时间复杂度O(n^2)，勉强AC
    public static int trap2(int[] height) {
        int res = 0;
        int len = height.length;
        for (int i = 1; i < len - 1; i++) {
            int maxLeft = 0;
            int maxRight = 0;
            for (int j = i; j >= 0; j--) {
                maxLeft = Math.max(maxLeft, height[j]);
            }
            for (int j = i; j < len; j++) {
                maxRight = Math.max(maxRight, height[j]);
            }
            res += Math.min(maxLeft, maxRight) - height[i];
        }
        
        return res;

    }


    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap(height));
    }
}
