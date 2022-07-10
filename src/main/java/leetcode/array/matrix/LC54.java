package leetcode.array.matrix;

import java.util.LinkedList;
import java.util.List;
import org.junit.Test;

public class LC54 {

  public List<Integer> spiralOrder(int[][] matrix) {
//    if(matrix.length==0){
//      return new int[0];
//    }
    int m = matrix.length;
    int n = matrix[0].length;
    int loop = (Math.min(m, n)) / 2; //最大循环次数
    int startx = 0, starty = 0;
    int i = 0, j = 0;
    int offset = 0;
    List<Integer> result = new LinkedList<>();
//    int[] result2 = new int[m * n];
//    int point = 0;
    while (loop-- > 0) {
      for (j = starty; j < n - offset - 1; j++) {
        result.add(matrix[startx][j]);
//        result2[point++] = matrix[startx][j];
      }
      for (i = startx; i < m - offset - 1; i++) {
        result.add(matrix[i][j]);
//        result2[point++] = matrix[i][j];
      }
      for (; j > offset; j--) {
        result.add(matrix[i][j]);
//        result2[point++] = matrix[i][j];
      }
      for (; i > offset; i--) {
        result.add(matrix[i][j]);
//        result2[point++] = matrix[i][j];
      }
      offset++;
      startx++;
      starty++;
    }
    if ((Math.min(m, n)) % 2 == 1) {
      if (m >= n) { //中间列
        for (i = startx; i < m - offset; i++) {
          result.add(matrix[i][starty]);
//          result2[point++] = matrix[i][starty];
        }
      }
      if (n > m) { //中间行
        for (j = startx; j < n - offset; j++) {
          result.add(matrix[startx][j]);
//          result2[point++] = matrix[startx][j];
        }
      }
    }
    return result;
//    return result2;
  }

  @Test
  public void run() {
    System.out.println(spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
    System.out.println(spiralOrder(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}}));
  }
}
