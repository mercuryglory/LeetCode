package btree;

/**
 * created by mercury on 2020-09-13
 *
 * 二叉树的序列化与反序列化
 *
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 *
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 *
 * 示例: 
 *
 * 你可以将以下二叉树：
 *
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 *
 * 序列化为 "[1,2,3,null,null,4,5]"
 * 提示: 这与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 *
 * 说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。
 *
 */
public class LC297 extends BaseTreeNode {

    /**
     * 这里用了先序遍历
     * 官方示例是层序遍历生成的二叉树，只要序列化和反序列化用的同一种遍历方式就可以
     */
    public static String serialize(TreeNode root) {
        if (root == null) {
            return "null,";
        }

        String result = root.val + "," +
                serialize(root.left) +
                serialize(root.right);
        return result;

    }


    /**
     * 设置序号index，将字符串根据，分割为数组，根据index的值来设置树节点的val,如果节点的值为null,则返回空的树节点。
     */
    public static int index = -1;

    public static TreeNode deserialize(String str) {
        if (str == null) {
            return null;
        }
        String[] arr = str.split(",");
        return deserialize(arr);
    }

    public static TreeNode deserialize(String[] array) {
        index++;
        if (index >= array.length) {
            return null;
        }
        TreeNode node = null;
        if (!array[index].equals("null")) {
            node = new TreeNode(Integer.parseInt(array[index]));      //这里没有做对非法输入的校验
            node.left = deserialize(array);
            node.right = deserialize(array);
        }

        return node;
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, null, null, 4, 5};
        TreeNode input = generateTreeNode(arr);
        String res = serialize(input);
        System.out.println(res);

        TreeNode output = deserialize(res);
        System.out.println(123);
    }

}
