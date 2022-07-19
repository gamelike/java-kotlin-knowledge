package leetcode.day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;

public class LC1260 {

  public List<List<Integer>> shiftGrid(int[][] grid, int k) {
    // m and  n ！=0
    int m = grid.length;
    int n = grid[0].length;
    List<Integer> nums = new ArrayList<>();
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        nums.add(grid[i][j]);
      }
    }

    int start = m * n - 1 - (k % (m * n)) + 1;
    List<List<Integer>> result = new LinkedList<>();
    for (int i = 1; i <= m; i++) { //m行
      List<Integer> sunResult = new LinkedList<>();
      //每次向后遍历k个元素
      for (int index = 1; index <= n; index++) { //n次
        if (start == nums.size()) { //走到了最后一位
          start = 0;
        }
        sunResult.add(nums.get(start++));
      }
      result.add(sunResult);
    }
//    for (int i = 0; i < m; i++) {
//      System.out.println(Arrays.toString(result.get(i).toArray()));
//    }
    return result;
  }

  @Test
  public void run() {
    shiftGrid(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, 1);
  }
}
