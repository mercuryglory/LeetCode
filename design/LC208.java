package design;

/**
 * created by mercury on 2020-08-16
 *
 * 实现Trie（前缀树）
 *
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 *
 * 示例:
 *
 * Trie trie = new Trie();
 *
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 * 说明:
 *
 * 你可以假设所有的输入都是由小写字母 a-z 构成的。
 * 保证所有输入均为非空字符串。
 *
 */

public class LC208 {

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.startsWith("app"));
        trie.insert("app");
        System.out.println(trie.search("app"));

    }

}


/**
 * Trie树的一个有根的树，每个节点有以下字段：
 * 1、最多R个指向子节点的链接，每个链接对应字母表数据集中的一个字母，这里都是小写，R=26
 * 2、指定该节点是键的结尾，还是只是键的前缀
 *
 */
class Trie {

    private Trie[] next = new Trie[26];
    private boolean isEnd;

    public Trie() {
    }

    public void insert(String word) {
        Trie root = this;
        char[] arr = word.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (root.next[arr[i] - 'a'] == null) {
                root.next[arr[i] - 'a'] = new Trie();
            }
            root = root.next[arr[i] - 'a'];
        }
        //将当前节点标记为结束节点
        root.isEnd = true;
    }

    /**
     * 注意查找键和查找键前缀的区别，键的话需要当前节点需要标记为isEnd
     */
    public boolean search(String word) {
        Trie root = this;
        char[] arr = word.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (root.next[arr[i] - 'a'] == null) {
                return false;
            }
            root = root.next[arr[i] - 'a'];
        }
        return root.isEnd;
    }

    public boolean startsWith(String prefix) {
        Trie root = this;
        char[] arr = prefix.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (root.next[arr[i] - 'a'] == null) {
                return false;
            }
            root = root.next[arr[i] - 'a'];
        }
        return true;
    }
}