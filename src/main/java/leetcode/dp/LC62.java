package leetcode.dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * <p>
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * <p>
 * 问总共有多少条不同的路径？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/unique-paths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author gjd
 */
public class LC62 {
  public int uniquePaths(int m, int n) {
    //dp[i][j]
    //dp[i][j] = dp[i-1][j]+dp[i][j-1]
    //dp[1][1] = 0  dp[1][0] = 1
    int dp[][] = new int[m + 1][n + 1];
    for (int i = 1; i <= m; i++) {
      dp[i][1] = 1;
    }
    for (int j = 1; j <= n; j++) {
      dp[1][j] = 1;
    }
    for (int i = 2; i <= m; i++) {
      for (int j = 2; j <= n; j++) {
        dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
      }
    }
    for (int i = 0; i <= m; i++) {
      System.out.println(Arrays.toString(dp[i]));
    }
    return dp[m][n];
  }

  @Test
  public void run() {
    uniquePaths(3, 7);
  }

  public int uniquePaths2(int m, int n) {
    return dfs(0, 0, m, n);
  }

  public int dfs(int i, int j, int m, int n) {
    if (i > m || j > n) {
      return 0;
    }
    if (i == m && j == n) {
      return 1;
    }
    return dfs(i + 1, j, m, n) + dfs(i, j + 1, m, n);
  }
}
