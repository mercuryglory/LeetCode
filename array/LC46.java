package array;

import java.util.ArrayList;
import java.util.Arrays;
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

        backTrack(res, new ArrayList<>(), nums, 0);
        return res;

    }

    private static void backTrack(List<List<Integer>> res, List<Integer> list, int[] nums, int start) {
        if (nums == null || start > nums.length - 1) {
            return;
        }
        if (start == nums.length - 1) {
            for (int num : nums) {
                list.add(num);
            }
            //回溯的问题，一定要记得需要对引用进行拷贝，否则引用回溯后最后得到的都是空
            res.add(new ArrayList<>(list));
            list.clear();
        } else {
            for (int i = start; i <= nums.length - 1; i++) {
                swap(nums, i, start);
                backTrack(res, list, nums, start + 1);
                swap(nums, i, start);
            }
        }

    }


    private static void swap(int[] arr, int a, int b) {
        if (a == b) {
            return;
        }
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(permute(nums));
    }
}
