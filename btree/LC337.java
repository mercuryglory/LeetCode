package btree;

/**
 * created by mercury on 2020-08-23
 *
 * 打家劫舍III
 *
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。
 * 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 *
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 *
 * 示例 1:
 * 输入: [3,2,3,null,3,null,1]
 *
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 *
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 *
 *
 * 示例 2:
 * 输入: [3,4,5,1,3,null,1]
 *
 *      3
 *     / \
 *    4   5
 *   / \   \
 *  1   3   1
 *
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 *
 */
public class LC337 extends BaseTreeNode {

    /**
     * 这是一个树形dp的问题
     * 类似{@link dp.LC309}，问题是具有后效性的。这种问题在设计状态的时候，可以在后面加一维，保存需要的状态
     * 这里树的遍历用后序遍历，因为逻辑是子节点一层一层向上汇报，最后在根节点汇总值
     * <p>
     * 关键点在于当前节点偷还是不偷决定了子节点偷或者不偷，把这一点设计成状态，放在第2维，即消除后效性
     *
     * 定义dp[node][j]表示以node为根节点的树，并规定j=0表示node节点不偷，j=1表示node节点偷
     * 如果当前节点不偷，那么左右子节点偷或者不偷都行，取最大值
     * 如果当前节点偷，那么左右子节点都不能偷了，去左右子节点的0位置
     * node为空时递归结束，返回空数组
     * 遍历到根节点结束后，返回max{dp[0],dp[1]}
     */

    public static int rob(TreeNode root) {
        int[] res = dfs(root);
        return Math.max(res[0], res[1]);
    }


    private static int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }

        int[] left = dfs(node.left);
        int[] right = dfs(node.right);

        int[] dp = new int[2];
        dp[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        dp[1] = node.val + left[0] + right[0];
        return dp;
    }

    public static void main(String[] args) {
        Integer[] arr = {3, 4, 5, 1, 3, null, 1};
        TreeNode root = generateTreeNode(arr);
        System.out.println(rob(root));

    }
}
