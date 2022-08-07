package leetcode.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author gjd
 */
public class LC216 {
  List<List<Integer>> result = new LinkedList<>();
  LinkedList<Integer> path = new LinkedList<>();

  public List<List<Integer>> combinationSum3(int k, int n) {
    backTracking(k, n, 0, 1);
    return result;
  }

  private void backTracking(int k, int targetSum, int sum, int startIndex) {
    if (sum > targetSum) {
      return;
    }
    if (path.size() == k) {
      if (sum == targetSum) {
        result.add(new ArrayList<>(path));
      }
      return;
    }
    for (int i = startIndex; i < 10; i++) {
      if (k - path.size() - 1 > 9 - i) {
        return;
      }
      path.add(i);
      backTracking(k, targetSum, sum + i, i + 1);
      path.removeLast();
    }
  }
}
