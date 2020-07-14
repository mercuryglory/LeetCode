package array;

/**
 * created by mercury on 2020-06-14
 *
 * 给出一个用二维矩阵表示的图像
 * 返回该图像顺时针旋转90度的结果
 * 扩展：
 * 你能使用原地算法解决这个问题么？
 */
public class LC48 {

    /**
     * 最外圈到最里圈，一层一层的转
     * 关键点 a[i][j]这个点的下一个位置是 a[n-1-j][i]
     */
    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = i; j < n - i - 1; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = temp;
            }
        }
    }


    public static void main(String[] args) {
        int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        rotate(arr);
    }

}
