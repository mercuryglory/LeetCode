package btree;

import java.util.HashMap;
import java.util.Map;
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


    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = inorder.length;
        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }

        return buildTree(preorder, inorder, 0, n - 1, 0, n - 1, map);

    }

    /**
     * 递归法，分治，用Map存储中序遍历中每个值出现的位置，不必每次递归都遍历
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder, int preLeft, int preRight,
                                     int inLeft, int inRight, Map<Integer, Integer> map) {
        if (preLeft > preRight) {
            return null;
        }
        int preRoot = preLeft;
        //确定中序遍历根节点的位置
        int inRoot = map.get(preorder[preRoot]);
        //左子树的节点数
        int leftSubSize = inRoot - inLeft;
        TreeNode root = new TreeNode(preorder[preRoot]);
        //前序遍历从左边界+1开始的leftSubSize个元素，对应了中序遍历从左边界到根节点位置-1的元素
        root.left = buildTree(preorder, inorder, preLeft + 1, preLeft + leftSubSize, inLeft, inRoot - 1, map);
        //前序遍历从左边界+左子树节点数+1到右边界的元素，对应了中序遍历从根节点位置+1到右边界的元素
        root.right = buildTree(preorder, inorder, preLeft + leftSubSize + 1, preRight, inRoot + 1, inRight, map);

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
        TreeNode node = buildTree(pre, in);
        System.out.println(123);

    }

}
