package design;


import java.util.HashMap;
import java.util.Map;

/**
 * created by mercury on 2020-08-11
 *
 * LRU缓存机制
 *
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 *
 * 获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *
 *  
 *
 * 进阶:
 *
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 *
 *  
 *
 * 示例:
 *
 * LRUCache cache = new LRUCache( 2 );  //缓存容量
 *
    *cache.put(1,1);
    *cache.put(2,2);
    *cache.get(1);       // 返回  1
    *cache.put(3,3);    // 该操作会使得关键字 2 作废
    *cache.get(2);       // 返回 -1 (未找到)
    *cache.put(4,4);    // 该操作会使得关键字 1 作废
    *cache.get(1);       // 返回 -1 (未找到)
    *cache.get(3);       // 返回  3
    *cache.get(4);       // 返回  4
 *
 *
 */
public class LC146 {

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1,1);
        cache.put(2,2);
        System.out.println(cache.get(1));       // 返回  1
        cache.put(3,3);    // 该操作会使得关键字 2 作废
        System.out.println(cache.get(2));       // 返回 -1 (未找到)
        cache.put(4,4);    // 该操作会使得关键字 1 作废
        System.out.println(cache.get(1));       // 返回 -1 (未找到)
        System.out.println(cache.get(3));       // 返回  3
        System.out.println(cache.get(4));       // 返回  4
    }
}


/**
 * 如果直接利用JDK中的LinkedHashMap可以实现，但不是此题的要求
 * 思路是哈希表+双向链表
 *
 * 1、双向链表按照最近使用顺序存储键值对，靠近头部是最近使用，靠近尾部是最久未使用
 * 2、哈希表，即通过缓存数据的键映射到其在双向链表中的位置
 */
class LRUCache {

    class LinkedNode {
        int key;
        int value;
        LinkedNode prev;
        LinkedNode next;

        public LinkedNode() {

        }

        public LinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Map<Integer, LinkedNode> cached = new HashMap<>();
    private int size;
    private int capacity;
    private LinkedNode head, tail;


    public LRUCache(int capacity) {
        this.capacity = capacity;
        //设置head和tail两个哑节点，意义在于添加和删除节点时不用检查相邻节点是否存在
        head = new LinkedNode();
        tail = new LinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        //先查看是否在哈希表中有缓存
        LinkedNode node = cached.get(key);
        if (node == null) {
            return -1;
        }
        //存在的话返回value，并将其移动到双向链表的头部
        moveToHead(node);
        return node.value;
    }



    public void put(int key, int value) {
        LinkedNode node = cached.get(key);
        //在哈希表中不存在缓存，则新建
        if (node == null) {
            LinkedNode newNode = new LinkedNode(key, value);
            //将新增的node分别添加到哈希表和链表中，这里是最新的，要添加到头部
            cached.put(key, newNode);
            addToHead(newNode);
            size++;
            //超过最大容量，删除尾节点，并且从哈希表中也删除
            if (size > capacity) {
                LinkedNode removed = removeTail();
                cached.remove(removed.key);
                //最后容量要恢复
                size--;
            }
        } else {
            //已经存在则更新，并且最新被使用了
            node.value = value;
            moveToHead(node);
        }
    }


    //移动就是先删除当前，再添加到头部
    private void moveToHead(LinkedNode node) {
        removeNode(node);
        addToHead(node);

    }


    private void addToHead(LinkedNode node) {
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
    }

    private void removeNode(LinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private LinkedNode removeTail() {
        LinkedNode node = tail.prev;
        removeNode(node);
        return node;
    }

}
