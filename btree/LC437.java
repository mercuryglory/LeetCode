package btree;

/**
 * created by mercury on 2020-07-22
 *
 * 路径总和III
 */

public class LC437 {

    /**
     * 把问题转化为求三部分的和：
     * 1、以当前节点作为头节点的路径数量
     * 2、当前节点的左子树中满足条件的路径数量
     * 3、当前节点的右子树中满足条件的路径数量
     */
    public static int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }

        int rootCount = pathCount(root, sum);
        int leftCount = pathSum(root.left, sum);
        int rightCount = pathSum(root.right, sum);
        return rootCount + leftCount + rightCount;

    }

    public static int pathCount(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }

        sum -= root.val;
        int result = sum == 0 ? 1 : 0;
        return result + pathCount(root.left, sum) + pathCount(root.right, sum);

    }

    public static void main(String[] args) {
//        TreeNode root = new TreeNode(10);
//        root.left = new TreeNode(5);
//        root.left.left = new TreeNode(3);
//        root.left.right = new TreeNode(2);
//        root.left.left.left = new TreeNode(3);
//        root.left.left.right = new TreeNode(-2);
//        root.left.right.right = new TreeNode(1);
//        root.right = new TreeNode(-3);
//        root.right.right = new TreeNode(11);

        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);

        System.out.println(pathSum(root, 3));

    }

}
