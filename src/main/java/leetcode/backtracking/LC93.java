package leetcode.backtracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gjd3
 */
public class LC93 {

  @Test
  public void run() {
    restoreIpAddresses("0000");
  }

  List<String> result = new ArrayList<>();

  public List<String> restoreIpAddresses(String s) {
    backTrackint(s, 0, 0);
    return result;
  }

  private void backTrackint(String s, int startIndex, int pointNum) {
    if (pointNum == 3) {
      if (isSubIpField(s.substring(startIndex))) {
        result.add(s);
      }
      return;
    }
    for (int i = startIndex; i < s.length(); i++) {
      if (isSubIpField(s.substring(startIndex, i + 1))) {
        s = s.substring(0, i + 1) + "." + s.substring(i + 1); //添加逗号
        pointNum++;
        backTrackint(s, i + 2, pointNum); //多加了一个逗号
        pointNum--;
        s = s.substring(0, i + 1) + s.substring(i + 2); //去掉逗号
      } else {
        continue;
      }
    }
  }

  private boolean isSubIpField(String sub) {
    //如果不是0多个位且不是0开头
    if (sub.length() > 1 && sub.startsWith("0")) {
      return false;
    }
    try {
      if (Integer.parseInt(sub) > 255) {
        return false;
      }
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }
}
