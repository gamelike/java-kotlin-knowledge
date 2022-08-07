package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gjd
 */
public class LC19 {
  String[] numString = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
  List<String> result = new ArrayList<>();
  StringBuilder temp = new StringBuilder();

  public List<String> letterCombinations(String digits) {
    if (digits == null || digits.length() == 0) {
      return result;
    }
    backTracking(0, digits);
    return result;
  }

  private void backTracking(int index, String digits) {
    if (index == digits.length()) {
      result.add(new String(temp));
      return;
    }
    String s = numString[digits.charAt(index) - '0'];
    for (int i = 0; i < s.length(); i++) {
      temp.append(s.charAt(i));
      backTracking(index + 1, digits);
      temp.deleteCharAt(temp.length() - 1);
    }
  }
}
