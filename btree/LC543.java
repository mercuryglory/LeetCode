package btree;

/**
 * created by mercury on 2020-07-25
 *
 * 二叉树的直径
 *
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 *
 * 示例 :
 * 给定二叉树
 *
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 *
 *
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 *
 */

public class LC543 extends BaseTreeNode {


    private static int res = 1;


    /**
     * 一条路径的长度为该路径经过的节点数-1，所以求直径等效于求路径经过节点数的最大值-1
     * 而任意一条路径都可以看做由某个节点为起点，从其左儿子和右儿子向下遍历的路径拼接得到
     *
     * 假设对于一个节点，从左儿子向下遍历经过的最多节点数为L.右儿子为R,那么以该节点为起点（穿过）的节点数
     * 最大值为L+R+1
     * 而以每个节点为根的子树深度为max(L,R)+1，这个值返回给上级
     *
     * 用一个全局变量记录以每个节点为起点的路径最大值d，二叉树的直径就是所有节点d里面的最大值
     */
    public static int diameterOfBinaryTree(TreeNode root) {
        getDepth(root);
        return res - 1;
    }


    public static int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = getDepth(root.left);
        int right = getDepth(root.right);

        res = Math.max(res, left + right + 1);

        return Math.max(left, right) + 1;

    }


    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 4, 5};

        TreeNode root = generateTreeNode(arr);
        System.out.println(diameterOfBinaryTree(root));
    }

}
