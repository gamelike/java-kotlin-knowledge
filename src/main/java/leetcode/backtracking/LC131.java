package leetcode.backtracking;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author gjd3
 */
public class LC131 {
  List<List<String>> reslut = new ArrayList<>();
  Deque<String> path = new ArrayDeque<>();

  public List<List<String>> partition(String s) {
    backtracking(s, 0);
    return reslut;
  }

  //递归
  private void backtracking(String s, int startIndex) {
    if (startIndex >= s.length()) {
      reslut.add(new ArrayList<>(path));
      return;
    }
    for (int i = startIndex; i < s.length(); i++) {
      if (isPalindrome(s, startIndex, i)) {
        path.addLast(s.substring(startIndex, i + 1));
      } else {
        continue;
      }
      backtracking(s, i + 1);
      path.removeLast();
    }
  }

  //判断是不是回文串
  private boolean isPalindrome(String s, int start, int end) {
    for (int i = start, j = end; i < j; i++, j--) {
      if (s.charAt(i) != s.charAt(j)) {
        return false;
      }
    }
    return true;
  }
}
