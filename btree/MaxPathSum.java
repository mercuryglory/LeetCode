package btree;


/**
 * created by mercury on 2020-04-22
 *
 * 给出一棵二叉树，寻找一条路径使其路径和最大，路径可以在任一节点中开始和结束
 * 比如
 *      1
 *   /     \
 *  2       3
 *
 *  return 6
 */
public class MaxPathSum {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);

        System.out.println(maxPathSum(root));

    }


    public static int maxValue;

    public static int maxPathSum(TreeNode root) {
        maxValue = Integer.MIN_VALUE;
        dfs(root);

        return maxValue;
    }


    /**
     * 给定一个非空节点，最终路径经过这个节点有4种情况：
     * 1、左子树的路径加上当前节点
     * 2、右子树的路径加上当前节点
     * 3、只有该节点本身（左右子树的路径和都是负数）
     * 4、左右子树的路径加上当前节点
     * 其中1，2，3都可以作为子树路径和向上延伸，而4则不行。因为沿着路径，每个节点只会经过一次
     */
    public static int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = dfs(node.left);
        int right = dfs(node.right);

        //比较这三种情况的最大值，都是经过node且可以向上组合的，返回给上层使用
        int temp = Math.max(Math.max(left + node.val, right + node.val), node.val);
        //不能向上组合的情况只用于更新结果，不用向上返回
        maxValue = Math.max(maxValue, Math.max(temp, left + right + node.val));

        return temp;

    }


    /**
     * 对算法做了优化，如果返回给上层的值为负数就取0，要取就取正数，减少了做比较的运算次数
     */
    public static int getPath(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = Math.max(0, getPath(node.left));
        int right = Math.max(0, getPath(node.right));

        maxValue = Math.max(maxValue, left + right + node.val);
        return Math.max(left, right) + node.val;

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
