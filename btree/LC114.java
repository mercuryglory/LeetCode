package btree;

/**
 * created by mercury on 2020-08-03
 *
 * 二叉树展开为链表
 *
 * 给定一个二叉树，原地将它展开为一个单链表。
 *
 *  
 *
 * 例如，给定二叉树
 *
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * 将其展开为：
 *
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 *
 */
public class LC114 extends BaseTreeNode {

    /**
     * 寻找前驱节点，空间复杂度O(1)
     *
     * while 节点不为空
     * 1、如果左子节点为空，则直接移动到右子节点
     * 2、左子节点不为空，首先找到左子树的最右节点作为前驱节点，该前驱节点的右节点指向当前节点的右子树，
     *    然后当前节点左子树赋值给当前节点的右子树，并将当前节点的左子树置空。
     *    当前节点处理完毕后，继续移动到其右子节点
     */
    public static void flatten(TreeNode root) {
        //对于节点的修改，注意和LC98、LC94的区别
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
                cur = cur.right;
            } else {
                TreeNode pre = cur.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                pre.right = cur.right;
                cur.right = cur.left;
                cur.left = null;
                cur = cur.right;
            }
        }

    }


    public static void main(String[] args) {
        Integer[] arr = {1, 2, 5, 3, 4, null, 6};
        TreeNode root = generateTreeNode(arr);
        flatten(root);
        System.out.println(root.right.val);
    }

}
