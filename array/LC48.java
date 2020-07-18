package array;


/**
 * created by mercury on 2020-06-14
 *
 * 旋转图像
 *
 * 给出一个用n * n二维矩阵表示的图像
 * 返回该图像顺时针旋转90度的结果
 * 扩展：
 * 你能使用原地算法解决这个问题么？
 */
public class LC48 {

    /**
     * 图解 https://uploadfiles.nowcoder.com/images/20160901/536462_1472719350724_E927FB24B1D99A05356A331B88A30942
     * 分步骤求解，会简单一些
     * 做两次翻转，先沿左下-右上的对角线翻转，再沿水平中线上下翻转
     */
    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][n - i - 1];
                matrix[n - j - 1][n - i - 1] = temp;
            }
        }

        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = temp;
            }
        }
    }



    public static void main(String[] args) {
        int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        rotate(arr);
        System.out.println(123);
    }

}
