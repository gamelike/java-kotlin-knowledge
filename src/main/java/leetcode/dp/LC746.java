package leetcode.dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。
 * <p>
 * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
 * <p>
 * 请你计算并返回达到楼梯顶部的最低花费。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/min-cost-climbing-stairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author gjd
 */
public class LC746 {
  public int minCostClimbingStairs(int[] cost) {
    // dp
    // dp[i] = Math.min(dp[i-1],dp[i-2])+cost[i];
    // 初始化
    // 遍历
    int dp[] = new int[cost.length];
    dp[0] = cost[0];
    dp[1] = cost[1];
    for (int i = 2; i < cost.length; i++) {
      dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
    }
    System.out.println(Arrays.toString(dp));
    return Math.min(dp[cost.length - 2], dp[cost.length - 1]);
  }

  @Test
  public void run() {
    minCostClimbingStairs(new int[]{10, 15, 20});
  }
}
