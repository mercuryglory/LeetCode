package btree;

/**
 * created by mercury on 2020-07-25
 */
public class BaseTreeNode {

    protected static TreeNode generateTreeNode(Integer[] arr) {
        if (arr == null) {
            return null;
        }
        return generateTreeNode(arr, 0);
    }

    private static TreeNode generateTreeNode(Integer[] arr, int index) {
        TreeNode node = null;
        if (index < arr.length) {
            if (arr[index] != null) {
                node = new TreeNode(arr[index]);
                node.left = generateTreeNode(arr, 2 * index + 1);
                node.right = generateTreeNode(arr, 2 * index + 2);
            }
        }

        return node;
    }

    public static void main(String[] args) {
        Integer[] arr = {10, 5, -3, 3, 2, null, 11, 3, -2, null, 1};
        TreeNode root = generateTreeNode(arr);
        System.out.println();
    }
}
