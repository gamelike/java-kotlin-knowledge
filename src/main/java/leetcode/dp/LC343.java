package leetcode.dp;

import java.util.Arrays;

/**
 * 给定一个正整数 n ，将其拆分为 k 个 正整数 的和（ k >= 2 ），并使这些整数的乘积最大化。
 * <p>
 * 返回 你可以获得的最大乘积 。
 */
public class LC343 {

  public int integerBreak2(int n) {
    //dp[n]
    //dp[n-3]*3
    //从左到右

    int dp[] = new int[n + 1];
    dp[2] = 1;
    //j*(i-j)
    //j * dp[i - j]
//    dp[i] = Math.max(dp[i - j] * j, i * j)
    for (int i = 3; i <= n; i++) {
      for (int j = 1; j < i - 1; j++) {
        dp[i] = Math.max(dp[i], Math.max(dp[i - j] * j, (i - j) * j));
      }
    }
    System.out.println(Arrays.toString(dp));
    return dp[n];
  }


  public int integerBreak(int n) {
    //dp[n]
    //dp[n-3]*3
    //从左到右
    if (n <= 6) {
      return (n - n / 2) * (n / 2);
    }
    int dp[] = new int[n + 1];
    dp[2] = 1;
    dp[3] = 2;
    dp[4] = 4;
    dp[5] = 6;
    dp[6] = 9;
    for (int i = 7; i <= n; i++) {
      dp[i] = dp[i - 3] * 3;
    }
    System.out.println(Arrays.toString(dp));
    return dp[n];
  }
}
