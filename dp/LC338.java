package dp;

import java.util.Arrays;

/**
 * created by mercury on 2020-08-23
 *
 * 比特位计数
 *
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 *
 * 示例 1:
 * 输入: 2
 * 输出: [0,1,1]
 *
 * 示例 2:
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 *
 * 进阶:
 * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
 * 要求算法的空间复杂度为O(n)。
 * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
 *
 */
public class LC338 {

    /**
     * 按照汉明距离的解法{@link bit.LC461},当然可以求解，时间复杂度为O(nk),k是每个整数x的位数
     *
     * 进阶做法就是动态规划
     * 如果是奇数，一定比前面的偶数的二进制多一个1
     * 如果是偶数，最低位是0，除以2就是右移一位，也就是去掉那个0，所以偶数和它除以2后的1的个数一样多
     *
     */
    public static int[] countBis(int num) {
        int[] res = new int[num + 1];
        res[0] = 0;
        for (int i = 1; i <= num; i++) {
            if ((i & 1) == 1) {
                res[i] = res[i - 1] + 1;
            } else {
                res[i] = res[i >> 1];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] res = countBis(2);
        System.out.println(Arrays.toString(res));

    }
}
