package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author gjd
 */
public class LC77 {
  List<List<Integer>> result = new LinkedList<>();
  Deque<Integer> path = new LinkedList<>();

  public List<List<Integer>> combine(int n, int k) {
    backTracking(n, k, 1);
    return result;
  }

  private void backTracking(int n, int k, int startIndex) {
    if (path.size() == k) {
      result.add(new ArrayList<>(path));
      return;
    }
    for (int i = startIndex; i <= n; i++) {
      //如果之后元素不满足，则剪纸
      // k - path.size-1 > n-i  前面-1 目的是因为，当前节点也已经被还未被计算上
      if (k - path.size() - 1 > n - i) {
        return;
      }
      path.offerLast(i); //当前节点被加入
      backTracking(n, k, i + 1);
      path.pollLast();
    }
  }
}
