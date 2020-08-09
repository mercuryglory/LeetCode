package backtrace;

import java.util.ArrayList;
import java.util.List;

/**
 * created by mercury on 2020-08-09
 *
 * 单词搜索
 *
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 *  
 *
 * 示例:
 *
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 *
 * 提示：
 *
 * board 和 word 中只包含大写和小写英文字母。
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * 1 <= word.length <= 10^3
 *
 */

public class LC79 {

    private String word;
    private char[][] board;
    private boolean[][] flag;
    private int row;
    private int col;
    private int[][] direction = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    /**
     * 二维平面中偏移量数组是常用的技巧，该问题中4个偏移顺序的次序无关紧要
     * 这里的回溯指判断不匹配时，二维平面上的指针后退，如何重置状态很重要
     * 题解配图：https://leetcode-cn.com/problems/word-search/solution/zai-er-wei-ping-mian-shang-shi-yong-hui-su-fa-pyth/
     */
    public boolean exist(char[][] board, String word) {
        row = board.length;
        if (row == 0) {
            return false;
        }

        col = board[0].length;
        flag = new boolean[row][col];
        this.word = word;
        this.board = board;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (dfs(i, j, 0)) {
                    return true;
                }
            }
        }

        return false;

    }

    private boolean dfs(int i, int j, int start) {
        if (start == word.length() - 1) {
            return word.charAt(start) == board[i][j];
        }
        if (word.charAt(start) == board[i][j]) {
            flag[i][j] = true;
            //开始四个方向dfs
            for (int k = 0; k <4 ; k++) {
                int newX = i + direction[k][0];
                int newY = j + direction[k][1];
                if (isInArea(newX, newY) && !flag[newX][newY]) {
                    if (dfs(newX, newY, start + 1)) {
                        return true;
                    }
                }
            }


            //不是一条dfs路径上的节点可以重复访问
            flag[i][j] = false;

        }
        return false;
    }

    private boolean isInArea(int x, int y) {
        return x >= 0 && x < row && y >= 0 && y < col;
    }


    public static void main(String[] args) {
        char[][] board = {{'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}};
        LC79 obj = new LC79();
        String word = "ABCCED";
        System.out.println(obj.exist(board, word));

    }
}
