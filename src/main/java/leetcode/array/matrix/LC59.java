package leetcode.array.matrix;

import java.util.Arrays;
import org.junit.Test;

/**
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 */
public class LC59 {

  public int[][] generateMatrix(int n) {
    int i = 0;
    int j = 0;
    int point = 1;
    int loop = 1; //循环次数 每次都会向中间逼近
    int offset = 1;
    int startx = 0, starty = 0;
    int[][] result = new int[n][n];
    while (n / 2 >= loop++) {
      //从左向右
      for (j = starty; j < n - offset; j++) {
        result[startx][j] = point++;
      }
      for (i = startx; i < n - offset; i++) {
        result[i][j] = point++;
      }
      for (; j >= offset; j--) {
        result[i][j] = point++;
      }
      for (; i >= offset; i--) {
        result[i][j] = point++;
      }
      offset++;
      startx++;
      starty++;
    }
    if (n % 2 == 1) {
      result[n / 2][n / 2] = point;
    }
    for (i = 0; i < n; i++) {
      System.out.println(Arrays.toString(result[i]));
    }
    return result;
  }

  @Test
  public void run() {
    generateMatrix(10);
  }
}
