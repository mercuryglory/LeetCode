package btree;

/**
 * created by mercury on 2020-07-18
 * <p>
 * 二叉树的最大深度
 */
public class LC104 extends BaseTreeNode {

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }


    public static void main(String[] args) {
        Integer[] arr = {3, 9, 20, null, null, 15, 7};
        TreeNode root = generateTreeNode(arr);
        System.out.println(maxDepth(root));
    }
}
