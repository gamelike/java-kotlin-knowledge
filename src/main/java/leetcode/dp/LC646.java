package leetcode.dp;

import java.util.Arrays;

/**
 * @author gjd3
 */
public class LC646 {
  private static String addStr(String s) {
    s += "bbb";
    return s;
  }

  private static StringBuilder addStr(StringBuilder s) {
    s.append("bbb");
    return s;
  }

  public static void main(String[] args) {

    String str1 = "str";
    String str2 = "ing";
    String str3 = "str" + "ing";
    String str4 = str1 + str2;
    String str5 = "string";
    System.out.println(str3 == str4);//false
    System.out.println(str3 == str5);//true
    System.out.println(str4 == str5);//false
  }

  public int maxProfit(int[] prices) {
    // dp[i]
    //从左到右
    //dp[i] = max(dp[i-1],prices[i]-min(price[i-1]))
    int[] dp = new int[prices.length];
    int min = prices[0];
    int max = 0;
    for (int i = 1; i < prices.length; i++) {
      max = Math.max(max, prices[i] - min);
      min = Math.min(min, prices[i]);
    }
    return max;
  }

}
