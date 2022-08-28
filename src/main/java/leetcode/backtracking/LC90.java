package leetcode.backtracking;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * @author gjd3
 */
public class LC90 {
  List<List<Integer>> res = new ArrayList<>();
  Deque<Integer> subCollection = new ArrayDeque<>();

  public List<List<Integer>> subsetsWithDup(int[] nums) {
    Arrays.sort(nums);
    boolean[] flag = new boolean[nums.length];
    backTracking(nums, 0, flag);
    return res;
  }

  private void backTracking(int[] nums, int startIndex, boolean[] used) {
    res.add(new ArrayList<>(subCollection));
    if (startIndex >= nums.length) { //全部集合不是子集
      return;
    }
    for (int i = startIndex; i < nums.length; i++) {
      // used[i - 1] == true，说明同一树枝candidates[i - 1]使用过
      // used[i - 1] == false，说明同一树层candidates[i - 1]使用过  不支持同一个数层使用
      if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == false) {
        continue;
      } else {
        subCollection.addLast(nums[i]);
        used[i] = true;
        backTracking(nums, i + 1, used);
        used[i] = false;
        subCollection.removeLast();
      }
    }
  }
}
