package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * created by mercury on 2020-08-07
 *
 * 合并区间
 *
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1:
 *
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 *
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 */

public class LC56 {

    /**
     * https://leetcode-cn.com/problems/merge-intervals/solution/he-bing-qu-jian-by-leetcode-solution/
     * 官方题解里面用反证法证明了，按区间的左端点排序，在排完序的列表中，可以合并的区间一定是连续的
     *
     * 问题就转化为：先将第一个区间存到数组中，然后遍历区间，如果当前区间的左端点大于数组中最后一个区间的右端点，说明不重叠，将当前
     * 区间加到数组中。否则就将最后一个区间的右端点更新为它和当前区间右端点的最大值
     * @param intervals
     * @return
     */
    public static int[][] merge(int[][] intervals) {
        //矩阵大于一行才有计算的意义
        int len = intervals.length;
        if (len < 2) {
            return intervals;
        }

        //JDK 1.8特性
        Arrays.sort(intervals, Comparator.comparingInt(v -> v[0]));
        List<int[]> res = new ArrayList<>();
        res.add(intervals[0]);

        for (int i = 1; i < len; i++) {
            int[] current = intervals[i];
            int[] last = res.get(res.size() - 1);
            if (current[0] > last[1]) {
                res.add(current);
            }else {
                last[1] = Math.max(last[1], current[1]);
            }
        }

        return res.toArray(new int[res.size()][]);


    }


    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] merged = merge(intervals);

        for (int i = 0; i < merged.length; i++) {
            System.out.println(Arrays.toString(merged[i]));

        }

    }
}
