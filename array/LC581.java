package array;

/**
 * created by mercury on 2020-07-25
 * <p>
 * 最短无序连续子数组
 *
 * 给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 *
 * 你找到的子数组应是最短的，请输出它的长度。
 *
 * 示例 1:
 *
 * 输入: [2, 6, 4, 8, 10, 9, 15]
 * 输出: 5
 * 解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 * 说明 :
 *
 * 输入的数组长度范围在 [1, 10,000]。
 * 输入的数组可能包含重复元素 ，所以升序的意思是<=。
 *
 */

public class LC581 {

    /**
     * 双指针法寻找逆序对
     *
     * 1、指针left从头开始遍历，指针right从尾开始遍历
     * 2、对于left，如果有比当前最大数更小的数，说明存在逆序，更新左指针
     * 3、反之，对于right,如果有比当前最小数更大的数，说明存在逆序,更新右指针，一直到更新完毕
     */
    public static int findUnsortedSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int len = nums.length;
        int left = 0, right = -1;
        int max = nums[0], min = nums[len - 1];

        for (int i = 0; i < len; i++) {
            if (nums[i] < max) {
                right = i;
            } else {
                max = nums[i];
            }

            if (nums[len - i - 1] > min) {
                left = len - i - 1;
            } else {
                min = nums[len - i - 1];
            }
        }

        return right - left + 1;



    }


    public static void main(String[] args) {
        int[] arr = {2, 6, 4, 8, 10, 9, 15};
        System.out.println(findUnsortedSubArray(arr));

    }

}
