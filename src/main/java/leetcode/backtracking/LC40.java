package leetcode.backtracking;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gjd3
 */
public class LC40 {
  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    Arrays.sort(candidates);
    boolean used[] = new boolean[candidates.length];
    backTracking(candidates, target, 0, 0, used);
    return result;
  }

  List<List<Integer>> result = new ArrayList<>();
  Deque<Integer> path = new ArrayDeque<>();

  public void backTracking(int[] candidates, int target, int sum, int startIndex, boolean used[]) {
    if (sum == target) {
      result.add(path.stream().collect(Collectors.toList()));
      return;
    }
    for (int i = startIndex; i < candidates.length && sum + candidates[i] <= target; i++) {
      if (i > 0 && candidates[i] == candidates[i - 1] && used[i - 1] == false) { //used[i - 1] == false 为false说明之前相同元素被其他分支选择过，所以此分支不再向下考虑
        continue;
      }
      sum += candidates[i];
      path.offerLast(candidates[i]);
      used[i] = true;
      backTracking(candidates, target, sum, i+1, used);
      used[i] = false;
      int temp = path.pollLast();
      sum -= temp;
    }
  }
}
