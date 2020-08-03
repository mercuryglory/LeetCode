package btree;

import java.util.Arrays;
import java.util.Stack;

/**
 * created by mercury on 2020-08-03
 *
 * 从前序与中序遍历序列构造二叉树
 *
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 */
public class LC105 {

    /**
     * 递归法，分治
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }


        int rootVal = preorder[0];
        if (preorder.length == 1) {
            return new TreeNode(rootVal);
        }

        TreeNode root = new TreeNode(rootVal);
        int rootIndex = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == rootVal) {
                rootIndex = i;
                break;
            }
        }
        root.left = buildTree(Arrays.copyOfRange(preorder, 1, rootIndex + 1), Arrays.copyOfRange(inorder, 0, rootIndex));
        root.right = buildTree(Arrays.copyOfRange(preorder, rootIndex + 1, preorder.length), Arrays.copyOfRange(inorder, rootIndex + 1, inorder.length));

        return root;

    }


    //还有一种栈的解法比较难想到，了解即可
    private static TreeNode getTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }

        int pre = 0, in = 0;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curRoot = new TreeNode(preorder[pre]);
        TreeNode root = curRoot;
        stack.push(curRoot);
        pre++;

        while (pre < preorder.length) {
            if (curRoot.val == inorder[in]) {
                while (!stack.isEmpty() && stack.peek().val == inorder[in]) {
                    curRoot = stack.peek();
                    stack.pop();
                    in++;
                }
                curRoot.right = new TreeNode(preorder[pre]);
                curRoot = curRoot.right;
                stack.push(curRoot);
                pre++;
            } else {
                curRoot.left = new TreeNode(preorder[pre]);
                curRoot = curRoot.left;
                stack.push(curRoot);
                pre++;
            }
        }

        return root;

    }


    public static void main(String[] args) {
        int[] pre = {3, 9, 20, 15, 7};
        int[] in = {9, 3, 15, 20, 7};
        TreeNode node = getTree(pre, in);
        System.out.println(123);

    }

}
