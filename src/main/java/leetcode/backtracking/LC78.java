package leetcode.backtracking;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author gjd3
 */
public class LC78 {
  List<List<Integer>> res = new ArrayList<>();
  Deque<Integer> subCollection = new ArrayDeque<>();

  public List<List<Integer>> subsets(int[] nums) {
    backTracking(nums, 0);
    return res;
  }

  private void backTracking(int[] nums, int startIndex) {
    res.add(new ArrayList<>(subCollection));
    if (startIndex >= nums.length) { //全部集合不是子集
      return;
    }
    for (int i = startIndex; i < nums.length; i++) {
      subCollection.addLast(nums[i]);
      backTracking(nums, i + 1);
      subCollection.removeLast();
    }
  }
}
