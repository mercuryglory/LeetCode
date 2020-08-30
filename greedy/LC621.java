package greedy;

/**
 * created by mercury on 2020-08-30
 *
 * 任务调度器
 *
 * 给定一个用字符数组表示的 CPU 需要执行的任务列表。其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。
 * 任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。CPU 在任何一个单位时间内都可以执行一个任务，
 * 或者在待命状态。
 *
 * 然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
 * 你需要计算完成所有任务所需要的最短时间。
 *
 *
 * 示例 ：
 * 输入：tasks = ["A","A","A","B","B","B"], n = 2
 * 输出：8
 * 解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B.
 *      在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。
 *  
 *
 * 提示：
 * 任务的总个数为 [1, 10000]。
 * n 的取值范围为 [0, 100]。
 *
 */

public class LC621 {

    /**
     * 首先算出每个任务的个数，并排序
     * 贪心的将出现次数较多的任务排在前边是合理的，由于冷却时间的存在，如果不尽早安排出现次数较多的任务，会导致大量空闲时间的出现
     * 以n+1个任务为一轮，将当前任务按照它们剩余次数降序排序，可以证明这一轮的第K个任务距离上一次执行至少有n的冷却时间
     *
     * 类似填桶的过程：
     * https://leetcode-cn.com/problems/task-scheduler/solution/tian-tong-si-lu-you-tu-kan-wan-jiu-dong-by-mei-jia/
     */
    public static int leastInterval(char[] tasks, int n) {
        //记录每个任务出现的次数
        int[] counts = new int[26];
        for (char c : tasks) {
            counts[c - 'A']++;
        }
        //记录最多的任务出现的次数
        int max = 0;
        for (int count : counts) {
            max = Math.max(max, count);
        }

        //填不满桶时，最后一行的个数，就是具有最多次数的任务的个数
        int maxCount = 0;
        for (int count : counts) {
            if (max == count) {
                maxCount++;
            }
        }

        //有两种情况，填不满桶和桶放不下，要取最大值
        return Math.max((max - 1) * (n + 1) + maxCount, tasks.length);
    }


    public static void main(String[] args) {
        char[] tasks = {'A', 'B', 'A', 'B', 'A', 'B'};
        System.out.println(leastInterval(tasks, 2));

    }

}
