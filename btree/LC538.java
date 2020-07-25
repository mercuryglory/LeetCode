package btree;

/**
 * created by mercury on 2020-07-25
 * <p>
 * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
 * <p>
 *  
 * <p>
 * 例如：
 * <p>
 * 输入: 原始二叉搜索树:
 * 5
 * /   \
 * 2     13
 * <p>
 * 输出: 转换为累加树:
 * 18
 * /   \
 * 20     13
 */

public class LC538 extends BaseTreeNode {

    private static int sum = 0;


    /**
     * 从右到左的中序遍历  递归+回溯
     * 将累加值作为全局变量存起来
     * 1、判断当前访问节点是否存在，存在就递归右子树
     * 2、右子树递归完，更新累加值和当前节点的值
     * 3、递归左子树
     */
    public static TreeNode convertBST(TreeNode root) {
        if (root != null) {
            convertBST(root.right);
            sum += root.val;
            root.val = sum;
            convertBST(root.left);
        }

        return root;

    }


    public static void main(String[] args) {

        Integer[] arr = {10, 5, -3, 3, 2, null, 11, 3, -2, null, 1};
        TreeNode root = generateTreeNode(arr);
        convertBST(root);
        System.out.println();
    }

}
