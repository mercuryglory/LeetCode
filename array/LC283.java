package array;

import java.util.Arrays;

/**
 * created by mercury on 2020-07-22
 * <p>
 * 移动零
 * <p>
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 * <p>
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 */

public class LC283 {

    //两次遍历，先把所有不为0的数字全填到最前面，后面的就都是0了
    public static void moveZeros(int[] nums) {

        int nonZeroBegin = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[nonZeroBegin++] = nums[i];
            }
        }

        for (int i = nonZeroBegin; i < nums.length; i++) {
            nums[i] = 0;
        }

    }

    /**
     * 更巧妙的一种做法，利用两个指针，只需要遍历一次，一般不容易想到
     * 当前元素!=0，就把其换到左边，等于0的交换到右边
     */
    public static void moveZeros1(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j++] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        moveZeros1(nums);
        System.out.println(Arrays.toString(nums));

    }

}
