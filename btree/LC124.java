package btree;


/**
 * created by mercury on 2020-04-22
 *
 * 给定一个非空二叉树，返回其最大路径和。
 *
 * 本题中，路径被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。
 * 该路径至少包含一个节点，且不一定经过根节点。
 *
 * 示例 1：
 * 输入：[1,2,3]
 *
 *        1
 *       / \
 *      2   3
 *
 * 输出：6
 *
 *
 * 示例 2：
 *
 * 输入：[-10,9,20,null,null,15,7]
 *
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 输出：42
 *
 */
public class LC124 extends BaseTreeNode {


    public static int maxValue = Integer.MIN_VALUE;

    //题目中的限制条件：路径上的点不能重复使用
    public static int maxPathSum(TreeNode root) {
        dfs(root);

        return maxValue;
    }


    /**
     * 给定一个非空节点，最终路径经过这个节点有4种情况：
     * 1、左子树的路径加上当前节点
     * 2、右子树的路径加上当前节点
     * 3、只有该节点本身（左右子树的路径和都是负数）
     * 4、左右子树的路径加上当前节点
     * 其中1，2，3都可以作为树的一个子树再计算。而4则不行，因为沿着路径，每个节点只会经过一次，只能用于比较更新最大值
     */
    public static int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = dfs(root.left);
        int right = dfs(root.right);

        //比较这三种情况的最大值，都是经过node且可以向上组合的，返回给上层使用
        int res = Math.max(Math.max(left + root.val, right + root.val), root.val);
        //不能向上组合的情况只用于更新结果，不用向上返回
        maxValue = Math.max(maxValue, Math.max(res, left + right + root.val));

        return res;

    }


    /**
     * 对算法做了优化，如果返回给上层的值为负数就取0，要取就取正数，减少了做比较的运算次数
     */
    public static int getPath(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = Math.max(0, getPath(root.left));
        int right = Math.max(0, getPath(root.right));

        maxValue = Math.max(maxValue, left + right + root.val);
        return Math.max(left, right) + root.val;

    }

    public static void main(String[] args) {
        Integer[] arr = {-10, 9, 20, null, null, 15, 7};
        TreeNode root = generateTreeNode(arr);

        System.out.println(maxPathSum(root));

    }


    //这种思路没有理解题目，算出的是必须从根节点出发的值
//    public static int maxPathAnother(TreeNode node) {
//        if (node == null) {
//            return 0;
//        }
//        int left = Math.max(0, maxPathAnother(node.left));
//        int right = Math.max(0, maxPathAnother(node.right));
//        return Math.max(left, right) + node.val;
//
//    }
}
