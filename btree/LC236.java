package btree;

import javafx.scene.Parent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * created by mercury on 2020-08-20
 *
 * 二叉树的最近公共祖先
 *
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 示例 1:
 *
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出: 3
 * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
 * 示例 2:
 *
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出: 5
 * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
 *  
 *
 * 说明:
 *
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉树中。
 *
 */

public class LC236 extends BaseTreeNode {

    private static Map<Integer, TreeNode> parent = new HashMap<>();
    private static Set<Integer> visited = new HashSet<>();



    private static void dfs(TreeNode root){
        if (root.left != null) {
            parent.put(root.left.val, root);
            dfs(root.left);
        }
        if (root.right != null) {
            parent.put(root.right.val, root);
            dfs(root.right);
        }
    }

    /**
     * 1、从根节点开始遍历，用map记录每个节点的父节点指针，根节点没有父节点
     * 2、从p节点开始往它的祖先移动，并记录已经访问过的祖先节点的值，直到根节点
     * 3、再从q节点不断往其祖先移动，如果有访问过的祖先节点，即返回
     */
    public static TreeNode lowestCommonAncestor(TreeNode root,TreeNode p,TreeNode q){
        dfs(root);

        while (p != null) {
            visited.add(p.val);
            p = parent.get(p.val);
        }
        while (q != null) {
            if (visited.contains(q.val)) {
                return q;
            }
            q = parent.get(q.val);
        }
        return null;

    }

    public static void main(String[] args) {
        Integer[] arr = {3, 5, 1, 6, 2, 0, 8, null, null, 7, 4};
        TreeNode root = generateTreeNode(arr);
        TreeNode lca = lowestCommonAncestor(root, root.left, root.left.right.right);
        System.out.println(123);

    }


}
