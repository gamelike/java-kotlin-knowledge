package leetcode.backtracking;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gjd3
 */
public class LC39 {
  //由于candidates大于0.所以必然存在sum>target情况，也就是可以退出的情况
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    backTracking(candidates, target, 0, 0);
    return result;
  }

  List<List<Integer>> result = new ArrayList<>();
  Deque<Integer> path = new ArrayDeque<>();

  public void backTracking(int[] candidates, int target, int sum, int startIndex) {
    if (sum > target) {
      return;
    }
    if (sum == target) {
      result.add(path.stream().collect(Collectors.toList()));
      return;
    }
    for (int i = startIndex; i < candidates.length && sum + candidates[i] <= target; i++) {
      sum += candidates[i];
      path.offerLast(candidates[i]);
      backTracking(candidates, target, sum, i);
      sum -= candidates[i];
      path.pollLast();
    }
  }
}
