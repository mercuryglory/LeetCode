package array;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * created by mercury on 2020-08-04
 *
 * 组合总和
 *
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 *
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1：
 *
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * 示例 2：
 *
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 *
 */

public class LC39 {

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, new LinkedList<>(), candidates, target, 0);

        return res;
    }

    /**
     * 递归+回溯
     * @param res
     * @param list
     * @param candidates
     * @param target
     * @param start 记录遍历开始的位置，否则每次都从0开始，会出现重复的组合，数字只能和自己及后面的数字组合，不应该再去选择前面的，
     *              比如[2,3,5]，选择3之后，只能和3、5组合
     */
    private static void backtrack(List<List<Integer>> res, List<Integer> list, int[] candidates, int target,
                                  int start) {
        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        //因为都是正整数，一旦减到负数了，这一层的下次循环不可能再加回来，直接返回
        if (target < 0) {
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            list.add(candidates[i]);
            //因为每个数字都可以使用无数次，所以递归可以从当前元素开始
            backtrack(res, list, candidates, target - candidates[i], i);
            //这一层递归完成，要进行一次回溯
            list.remove(list.size() - 1);
        }


    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 5};
        System.out.println(combinationSum(arr, 8));
    }


}
