package greedy;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * created by mercury on 2020-08-26
 *
 * 根据身高重建队列
 *
 * 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。
 *
 * 注意：
 * 总人数少于1100人。
 *
 * 示例
 *
 * 输入:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 *
 * 输出:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 *
 */

public class LC406 {

    /**
     * 先从最简单的情况考虑，假设队列中所有人的（h,k)都是相同高度h，只有k不同时，则每个人在队列的索引index=k
     * 如果不是同一高度，一个人的k和身高更矮的人的位置无关，所以如果两个身高不同的人k值一样，那么个子矮的应该在个子高的前边
     * 相同身高则k值较小的人在前边
     *
     * 因此，先排序后插入
     * 同h按照k大小升序，不同h按照h大小降序排列
     * 遍历排序后的数组，根据k将对应数组插入到k的位置上
     *
     * 核心思路就是高个子先排好序，矮个子后插入到k的位置上
     */
    public static int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);

        List<int[]> list = new LinkedList<>();
        for (int[] arr : people) {
            list.add(arr[1], arr);
        }

        return list.toArray(new int[list.size()][2]);
    }


    public static void main(String[] args) {
        int[][] people = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        int[][] res = reconstructQueue(people);

        for (int[] i : res) {
            System.out.println(Arrays.toString(i));
        }
    }
}
