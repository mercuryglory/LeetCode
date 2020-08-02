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

    public static void main(String[] args) {
        Integer[] arr = {1, null, 2, null, null, 3};
        TreeNode root = generateTreeNode(arr);
        System.out.println(inorderTraversal(root));

    }
}
