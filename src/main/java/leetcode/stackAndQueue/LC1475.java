package leetcode.stackAndQueue;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author gjd3
 */
public class LC1475 {
  public int[] finalPrices(int[] prices) {
    Stack<Integer> stack = new Stack<>();
    Map<Integer, Integer> map = new HashMap<>();
    int res[] = new int[prices.length];
    for (int i = prices.length - 1; i >= 0; i--) {
      //单调递减
      while (!stack.isEmpty() && prices[i] < stack.peek()) {
        stack.pop();
      }
      res[i] = stack.isEmpty() ? prices[i] : prices[i] - stack.peek();
      stack.push(prices[i]);
    }
    return res;
  }
}
