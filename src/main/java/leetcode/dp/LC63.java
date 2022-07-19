package leetcode.dp;

import java.util.Arrays;
import org.junit.Test;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * <p>
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish”）。
 * <p>
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 * <p>
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode.cn/problems/unique-paths-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC63 {

  public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    //dp[i][j]
    //dp[i][j] = dp[i][j-1]+dp[i-1][j]
    //dp[1][1] = 0   dp[i][1] = 1 dp[1][j] = 1
    //if(ob[i][j]==1)  dp[i][j] = 0
    int m = obstacleGrid.length;
    int n = obstacleGrid[0].length;
    int[][] dp = new int[m][n];
    //出现障碍 提前结束
    for (int i = 0; i < m; i++) {
      if (obstacleGrid[i][0] == 1) {
        break;
      }
      dp[i][0] = 1;
    }
    for (int j = 0; j < n; j++) {
      if (obstacleGrid[0][j] == 1) {
        break;
      }
      dp[0][j] = 1;
    }
    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        if (obstacleGrid[i][j] == 1) {
          dp[i][j] = 0;
        } else {
          dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
        }
      }
    }
    for (int i = 0; i < m; i++) {
      System.out.println(Arrays.toString(dp[i]));
    }
    return dp[m - 1][n - 1];
  }

}
