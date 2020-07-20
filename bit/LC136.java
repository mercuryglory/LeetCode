package bit;

/**
 * created by mercury on 2020-07-20
 * <p>
 * 只出现一次的数字
 */

public class LC136 {

    public static int singleNumber(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result = result ^ nums[i];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {2, 2, 1, 3, 5, 7, 5, 7, 3};
        System.out.println(singleNumber(arr));
    }
}
