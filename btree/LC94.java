package btree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * created by mercury on 2020-08-02
 *
 * 二叉树的中序遍历
 *
 * 给定一个二叉树，返回它的中序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 */

public class LC94 extends BaseTreeNode {

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();

        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                res.add(node.val);
                node = node.right;
            }

        }

        return res;
    }


    /**
     * 还有一个Morris算法，可以做到原地修改，空间复杂度O(1)
     *
     *
     * tip:注意这里和{@link LC98}的区别，这里不光有指针移动，还有指针左右节点重新赋值的操作，所以是会改变输入参数root的
     */
    public static List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode cur = root;
        TreeNode pre;

        while (cur != null) {
            if (cur.left == null) {
                res.add(cur.val);
                cur = cur.right;
            } else {
                pre = cur.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                pre.right = cur;
                TreeNode temp = cur;
                cur = cur.left;
                temp.left = null;
            }
        }


        return res;
    }


    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 4, 5, 6, null};
        TreeNode root = generateTreeNode(arr);
        System.out.println(inorderTraversal2(root));
        System.out.println(123);

    }
}
