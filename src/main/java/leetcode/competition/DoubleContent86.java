package leetcode.competition;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * @author gjd3
 */
public class DoubleContent86 {
  public boolean findSubarrays(int[] nums) {
    Set<Integer> set = new HashSet<>();
    for (int i = 0; i < nums.length - 1; i++) {
      int temp = nums[i] + nums[i + 1];
      if (!set.add(temp)) {
        return true;
      }
    }
    return false;
  }

  public boolean isStrictlyPalindromic(int n) {
    for (int i = 2; i <= n - 2; i++) {
      String s = Integer.toString(n, i);
      if (!isPalindrome(s)) {
        return false;
      }
    }
    return true;
  }

  private boolean isPalindrome(String str) {
    if (null == str || "".equals(str)) {
      return false;
    }
    int i = 0;
    int j = str.length() - 1;
    String[] strings = str.split("");
    for (; i <= j; i++, j--) {
      if (!strings[i].equals(strings[j])) {
        return false;
      }
    }
    return true;
  }

  public int numSpecial(int[][] mat) {
    int rows = mat.length;
    int cols = mat[0].length;
    int[] rowNum = new int[rows];
    int[] colNum = new int[cols];
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        rowNum[i] += mat[i][j];
        colNum[j] += mat[i][j];
      }
    }
    int res = 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (mat[i][j] == 1 && rowNum[i] == 1 && colNum[j] == 1) {
          res++;
        }
      }
    }
    return res;
  }
}
