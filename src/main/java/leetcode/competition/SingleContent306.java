package leetcode.competition;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gjd
 */
public class SingleContent306 {
  public int[][] largestLocal(int[][] grid) {
    int n = grid.length;
    int[][] result = new int[n - 2][n - 2];
    for (int i = 1; i <= n - 2; i++) {
      for (int j = 1; j <= n - 2; j++) {
        //上下左右扩展 求值
        int temp = maxGrid(grid, i + 1, j + 1);
        result[i - 1][j - 1] = temp;
      }
    }
//    System.out.println(Arrays.asList(result[1]));
    return result;
  }

  public int maxGrid(int[][] grid, int endX, int endY) {
    int max = Integer.MIN_VALUE;
    for (int i = endX - 2; i <= endX; i++) {
      for (int j = endY - 2; j <= endY; j++) {
        max = Math.max(max, grid[i][j]);
      }
    }
    return max;
  }

  @Test
  public void run() {
    smallestNumber("IIIDIDDD");
  }

  public int edgeScore(int[] edges) {
    Map<Integer, Long> map = new HashMap<>();
    for (int i = 0; i < edges.length; i++) {
      map.put(edges[i], map.getOrDefault(edges[i], 0L) + i);
    }
    int i = -1;
    Long max = Long.MIN_VALUE;
    for (Integer numKey : map.keySet()) {
      if (max < map.get(numKey)) {
        max = map.get(numKey);
        i = numKey;
      }
    }
    return i;
  }


  String res = "";

  public String smallestNumber(String pattern) {
    boolean[] flag = new boolean[10];

    for (int i = 1; i <= 9; i++) {
//      res = "";
      flag[i] = true;
      dfs(0, i, new StringBuilder(String.valueOf(i)), pattern.toCharArray(), flag);
      flag[i] = false;
    }
    return res;
  }

  private void dfs(int index, int pre, StringBuilder sb, char[] pattern, boolean[] flag) {

    if (index == pattern.length) {
      if (res == "" || res.compareTo(sb.toString()) > 0) {
        res = sb.toString();
      }
      return;
    }
    for (int i = (pattern[index] == 'I' ? pre + 1 : 1); i < (pattern[index] == 'I' ? 10 : pre); i++) {
      if (!flag[i]) {
        sb.append(i);
        flag[i] = true;
        dfs(index + 1, i, sb, pattern, flag);
        flag[i] = false;
        sb.deleteCharAt(sb.length() - 1);
      }
    }
  }
}
