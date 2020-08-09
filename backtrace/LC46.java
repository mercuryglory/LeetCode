package backtrace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * created by mercury on 2020-08-05
 *
 * 全排列
 *
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 *
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 */
public class LC46 {

    /**
     * 没有重复数字，用List存储就可以
     * @param nums
     * @return
     */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }

        List<Integer> list = new ArrayList<>();
        //优化一下，在开始递归前遍历数组到集合，在循环递归中只对集合做交换操作
        for (int num : nums) {
            list.add(num);
        }
        backTrack(res, list, list.size(), 0);
        return res;

    }

    private static void backTrack(List<List<Integer>> res, List<Integer> list, int len, int start) {
        if (start > len - 1) {
            return;
        }
        if (start == len - 1) {
            //回溯的问题，一定要记得需要对引用进行拷贝，否则引用回溯后最后得到的都是空
            res.add(new ArrayList<>(list));
        } else {
            for (int i = start; i <= len - 1; i++) {
                Collections.swap(list, i, start);
                backTrack(res, list, len, start + 1);
                Collections.swap(list, i, start);
            }
        }

    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(permute(nums));
    }
}
