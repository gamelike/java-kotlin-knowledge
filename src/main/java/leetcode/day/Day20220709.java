package leetcode.day;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Day20220709 {

    final int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 9}};

    public int lenLongestFibSubseq(int[] arr) {
        int ans = 0, n = arr.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(arr[i], i);
        }
        int[][] f = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= 0 && j + 2 > ans; j--) {
                if (arr[i] - arr[j] >= arr[j]) break;
                int t = map.getOrDefault(arr[i] - arr[j], -1);
                if (t == -1) continue;
                f[i][j] = Math.max(3, f[j][t] + 1);
                ans = Math.max(ans, f[i][j]);
            }
        }
        return ans;
    }

    /**
     * 7月10日每日一题
     * <hr/>
     * <p>状态转移往返判断路径能拿到最大点数.</p>
     * <p>可以看做两个人从起点到终点.</p>
     * mark 因为两人所在的每个点都会在同一条边上, x1 + y1 = x2 + y2 = k,所以进行拆分<br/>
     * y可以由k进行推论出,所以状态转移仅需要记录点 [k] [x1] [x2] <br/>
     * 公式 ： dp[k][x1][y1] = dp[k - 1][x1][x2] (右移) | dp[k - 1][x1 - 1][x2] (x1下，x2右移) | dp[k - 1][x1][x2 - 1] | dp[k - 1][x1 - 1][x2 - 1]
     */
    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int[][][] dp = new int[n * 2 - 1][n][n];
        // 初始化状态转移
        for (int[][] ints : dp) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(ints[j], Integer.MIN_VALUE);
            }
        }
        dp[0][0][0] = grid[0][0]; // 开始点
        for (int i = 1; i < dp.length; i++) {
            for (int x1 = Math.max(i - n + 1, 0); x1 <= Math.min(i, n - 1); ++x1) {
                int y1 = i - x1;
                if (grid[x1][y1] == -1) {
                    continue;
                }
                for (int x2 = x1; x2 <= Math.min(i, n - 1); ++x2) {
                    int y2 = i - x2;
                    if (grid[x2][y2] == -1) {
                        continue;
                    }
                    int res = dp[i - 1][x1][x2];
                    if (x1 > 0) {
                        res = Math.max(res, dp[i - 1][x1 - 1][x2]);
                    }
                    if (x2 > 0) {
                        res = Math.max(res, dp[i - 1][x1][x2 - 1]);
                    }
                    if (x1 > 0 && x2 > 0) {
                        res = Math.max(res, dp[i - 1][x1 - 1][x2 - 1]);
                    }
                    res += grid[x1][y1];
                    if (x2 != x1) { // 避免重复摘取
                        res += grid[x2][y2];
                    }
                    dp[i][x1][x2] = res;
                }
            }
        }
        return Math.max(dp[n * 2 - 2][n - 1][n - 1], 0);
    }

    @Test
    public void test() {
        System.out.println(lenLongestFibSubseq(new int[]{2, 4, 7, 8, 9, 10, 14, 15, 18, 23, 32, 50}));
    }
}
