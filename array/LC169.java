package array;

import java.util.HashMap;
import java.util.Map;

/**
 * created by mercury on 2020-07-19
 * <p>
 * 多数元素
 *
 * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 */
public class LC169 {


    public static int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            Integer val = map.get(n);
            if (val != null) {
                map.put(n, val + 1);
            } else {
                map.put(n, 1);
            }
        }
        Map.Entry<Integer, Integer> maj = null;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (maj == null || entry.getValue() > maj.getValue()) {
                maj = entry;
            }
        }
        return maj.getKey();

    }


    /**
     * 投票算法，时间复杂度O(n)，空间复杂度O(1)
     *
     * 因为maj（众数）大于一半，且只会投给自己，即使其他所有人都反对最后也是maj当选
     * 如果候选人不是maj（众数），投票数-1，如果是，投票数+1
     * 当投票数为0时，则之前打平，重新换一个人选举
     */
    public static int majorityElement2(int[] nums) {
        Integer candidate = null;
        int count = 0;
        for(int n:nums){
            if (count == 0) {
                candidate = n;
            }
            if (candidate == n) {
                count++;
            } else {
                count--;
            }
        }
        return candidate;
    }




    public static void main(String[] args) {
        int[] nums = {2, 2, 1, 1, 1, 2, 2};
        System.out.println(majorityElement2(nums));

    }

}
