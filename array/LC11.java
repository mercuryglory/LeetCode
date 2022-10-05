package array;

/**
 * created by mercury on 2020-07-29
 *
 * 盛最多水的容器
 *
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 *
 */

public class LC11 {

    /**
     * 双指针法
     * 向内移动较短边
     * 考虑两点：
     * 1、相同情况下两边距离越远越好
     * 2、区域受限于较短边
     *
     * 开始的时候左指针指向数组左边界，右指针指向数组右边界
     * 每次都用当前两个指针较小的值和之间的距离算出面积，和当前最大值比较
     *
     * 关键是每次循环过后，指针的移动。如果当前左指针的值小于右指针，那么一定是左指针向右移动一位。假设是右指针向左移动一位，那么
     * 左右边界的距离变小了，而接下来选取的较短边肯定还是<=left,得出的面积只会比当前更小。反之则是右指针向左移动一位
     */
    public static int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        while (left < right) {
            maxArea = Math.max(maxArea, Math.min(height[left], height[right]) * (right - left));
            System.out.println(maxArea);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }


    public static void main(String[] args) {
        int[] arr = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea(arr));

    }

}
