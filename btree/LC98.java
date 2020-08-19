package btree;

import java.util.Stack;

/**
 * created by mercury on 2020-08-02
 *
 * 验证二叉搜索树
 *
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *
 * 假设一个二叉搜索树具有如下特征：
 *
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 *
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 * 示例 2:
 *
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 *
 */

public class LC98 extends BaseTreeNode {

    private static long pre = Long.MIN_VALUE;

    /**
     * 一种很简洁的递归做法
     *
     * 利用了二叉搜索树的中序遍历，结果一定是升序的特点，用pre保存每一次中序遍历的节点的值，若下一次的值小于等于pre则
     * 说明不是二叉搜索树
     */
    public static boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!isValidBST(root.left)) {
            return false;
        }

        if (root.val <= pre) {
            return false;
        }
        pre = root.val;

        return isValidBST(root.right);

    }


    /**
     * 拓展，迭代的做法最好也能掌握，利用栈，但同样需要一个变量记录每次遍历的节点值
     */
    public static boolean isValidBST2(TreeNode root) {
        long pre = Long.MIN_VALUE;

        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                if (root.val <= pre) {
                    return false;
                }
                pre = root.val;
                root = root.right;
            }
        }

        return true;


    }

    public static void main(String[] args) {
        Integer[] arr = {10, 5, 15, null,null, 12, 20};
        TreeNode root = generateTreeNode(arr);
        System.out.println(isValidBST2(root));
        System.out.println(123);
    }
}
