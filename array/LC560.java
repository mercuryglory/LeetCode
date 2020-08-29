package array;

import java.util.HashMap;
import java.util.Map;

/**
 * created by mercury on 2020-08-29
 *
 * 和为K的子数组
 *
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 *
 * 示例 1 :
 *
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 * 说明 :
 *
 * 数组的长度为 [1, 20,000]。
 * 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
 *
 */
public class LC560 {

    /**
     * 暴力遍历很容易
     *
     * 前缀和+哈希表
     * 前缀和，假设有数x0,x1,.. 则y0=x0,y1=x0+x1,yn=x0+x1+..+xn
     * 即有数组pre,pre[i]=pre[i-1]+nums[i]，pre就是前缀和
     * 因为前缀和需要前一个元素，因此一般设置前缀和数组的大小为数组大小+1，第0个位置上为0
     *
     * 只关心次数，不关心具体解，可以用哈希表提高效率
     * 计算完包括当前数的前缀和之后，查一下在当前数之前，有多少个前缀和等于preSum-k
     * 因为满足preSum-(preSum-k)==k的区间个数是我们关心的
     * 开始时，下标0之前没有元素，可以认为前缀和为0的个数为1
     */
    public static int subArraySum(int[] nums, int k) {
        //key:前缀和  value:key对应的前缀和个数
        Map<Integer, Integer> preSumFreq = new HashMap<>();
        preSumFreq.put(0, 1);
        int count = 0;
        int preSum = 0;     //定义前缀和
        for (int num : nums) {
            preSum += num;
            if (preSumFreq.containsKey(preSum - k)) {
                count += preSumFreq.get(preSum - k);
            }
            //更新前缀和个数
            preSumFreq.put(preSum, preSumFreq.getOrDefault(preSum, 0) + 1);

        }

        return count;

    }

    public static void main(String[] args) {
        int[] nums = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        System.out.println(subArraySum(nums, 0));
    }
}
