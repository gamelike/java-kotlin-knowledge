package leetcode.backtracking;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;

/**
 * @author gjd3
 */
public class LC491 {
  private List<List<Integer>> result = new ArrayList<>();
  private Deque<Integer> path = new ArrayDeque<>();

  public List<List<Integer>> findSubsequences(int[] nums) {
    backTracking(nums, 0);
    return result;
  }

  private void backTracking(int[] nums, int startIndex) {
    if (path.size() > 1) {
      result.add(new ArrayList<>(path));
    }
    HashMap<Integer, Integer> map = new HashMap<>();
    for (int i = startIndex; i < nums.length; i++) {
      if (!path.isEmpty() && nums[i] < path.getLast()) {
        continue;
      }
      if (map.getOrDefault(nums[i], 0) >= 1) {
        continue;
      }
      map.put(nums[i], map.getOrDefault(nums[i], 0) + 1); //使用过的后面不需要再清空，记录下来即可
      path.add(nums[i]);
      backTracking(nums, i + 1);
      path.removeLast();
    }
  }
}
