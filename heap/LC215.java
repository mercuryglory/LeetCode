package heap;


/**
 * created by mercury on 2020-08-20
 *
 * 数组中的第K个最大元素
 *
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 *
 * 示例 2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 *
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 *
 */

public class LC215 {

    /**
     * 建立一个大根堆，做k-1次删除并调整后，堆顶元素就是结果
     */
    public static int findKthLargest(int[] nums, int k) {
        int heapSize = nums.length;
        /**
         * 构建大顶堆，从最后一个非叶子节点len/2-1开始，如果该节点小于其左/右子树的值就交换。然后继续下一个非叶子节点，即i--
         * 从底向上构建大顶堆是因为，如果自顶向下的话当第i层和i+1层比较后交换，交换后无法保证i层小于i-1层，又要向上验证
         *
         */
        for (int i = heapSize / 2 - 1; i >= 0; i--) {
            adjustMaxHeap(nums, i, heapSize);
        }

        //这里可以从顶向下调整是因为，只有堆顶不满足最大堆性质，其它分支都是满足的
        for (int i = nums.length - 1; i >= nums.length - k + 1; i--) {
            swap(nums, 0, i);
            heapSize--;
            adjustMaxHeap(nums, 0, heapSize);
        }

        return nums[0];
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }


    public static void adjustMaxHeap(int[] arr, int i, int len) {
        int temp = arr[i];
        for (int j = i * 2 + 1; j < len; j = j * 2 + 1) {
            if (j < len - 1 && arr[j] < arr[j + 1]) {
                j++;
            }
            if (arr[j] > temp) {
                arr[i] = arr[j];
                i = j;
            } else {
                break;
            }
        }
        arr[i] = temp;

    }


    public static void main(String[] args) {
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        System.out.println(findKthLargest(nums, 4));
    }
}
