package array;

/**
 * created by mercury on 2020-09-04
 *
 * 寻找两个正序数组的中位数
 *
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
 *
 * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 示例 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * 则中位数是 2.0
 *
 *
 * 示例 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * 则中位数是 (2 + 3)/2 = 2.5
 *
 */

public class LC04 {

    /**
     * 二分法，题解：https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/xun-zhao-liang-ge-you-xu-shu-zu-de-zhong-wei-s-114/
     *
     * 两个有序数组长度分别为m和n
     * 当m+n为奇数时，中位数是第(m+n)/2个元素
     * 当m+n为偶数时，中位数是第(m+n)/2-1和(m+n)/2元素的平均值
     *
     * 问题转化为寻找两个有序数组中第k小的数，k为(m+n)/2或(m+n)/2+1。注意和下标的区别，因为下标一般从0开始，而
     * 第几个数一般从1开始，就是表达形式
     *
     */
    public static double findMedianSortedArrays(int[] num1, int[] num2) {
        int len1 = num1.length;
        int len2 = num2.length;
        int totalLen = len1 + len2;

        if (totalLen % 2 == 1) {
            int midIndex = totalLen / 2;
            return getKThElement(num1, num2, midIndex + 1);
        } else {
            int midIndex1 = totalLen / 2 - 1;
            int midIndex2 = totalLen / 2;
            return (getKThElement(num1, num2, midIndex1 + 1) + getKThElement(num1, num2, midIndex2 + 1)) / 2.0;
        }


    }


    public static double getKThElement(int[] num1, int[] num2, int k) {
        int len1 = num1.length;
        int len2 = num2.length;
        //表示数组1下标偏移量，用来和数组2比较的
        int index1 = 0;
        int index2 = 0;

        while (true) {
            //边界，一个数组为空，说明该数组中所有元素都被排除，返回另一个数组中第k小的元素
            if (index1 == len1) {
                return num2[index2 - k + 1];
            }
            if (index2 == len2) {
                return num1[index1 - k + 1];
            }

            //返回较小值，自己品
            if (k == 1) {
                return Math.min(num1[index1], num2[index2]);
            }

            int half = k / 2;
            //无法简单的k=k-k/2，因为index+k/2可能数组越界，这时我们可以选取数组中最后一个元素
            //这种情况下要根据实际排除数的个数，
            int newIndex1 = Math.min(index1 + half, len1) - 1;
            int newIndex2 = Math.min(index2 + half, len2) - 1;
            if (num1[newIndex1] <= num2[newIndex2]) {
                //+1,是因为k/2-1自己也要被排除（不考虑边界）
                k = k - (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                k = k - (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }


        }
    }


    public static void main(String[] args) {
        int[] num1 = {1, 3, 4, 9};
        int[] num2 = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        System.out.println(findMedianSortedArrays(num1,num2));
    }

}
