package leetcode.competition;

import org.junit.Test;
/**
 * @author gjd3
 */
public class DouleContent85 {
  public int minimumRecolors(String blocks, int k) {
    int[] nums = new int[blocks.length() + 1];
    char[] chs = blocks.toCharArray();
    int ans = 0;
    for (int i = 0; i < chs.length; i++) {
      if (chs[i] == 'W') {
        ans++;
      }
      nums[i + 1] = ans;
    }
    for (int i = 0; i < blocks.length() - k + 1; i++) {
      ans = Math.min(ans, nums[i + k] - nums[i]);
    }
    return ans;
  }

  @Test
  public void run1() {
    minimumRecolors("BWWWBB", 6);
  }

  public int secondsToRemoveOccurrences(String s) {
    int count = 0;
    while (s.contains("01")) {
      s = s.replace("01", "10");
      count++;
    }
    return count;
  }

  //差分数组
  public String shiftingLetters(String s, int[][] shifts) {
    char[] chars = s.toCharArray();
    int[] difference = new int[s.length()];
    difference[0] = chars[0] - 'a';
    for (int i = 1; i < chars.length; i++) {
      difference[i] = chars[i] - chars[i - 1];
    }

    //维护差分数组的区间
    for (int i = 0; i < shifts.length; i++) {
      int operation = shifts[i][2] == 1 ? 1 : -1;
      difference[shifts[i][0]] += operation;
      if (shifts[i][1] + 1 < difference.length) {
        difference[shifts[i][1] + 1] -= operation;
      }
    }

    //根据差分数组恢复  偏离值
    int[] original = new int[s.length()];
    original[0] = difference[0];
    for (int i = 1; i < difference.length; i++) {
      original[i] = difference[i] + original[i - 1];
    }

    //根据偏离值，计算最终结果
    for (int i = 0; i < original.length; i++) {
      if (original[i] < 0) {
        original[i] = (original[i] % 26 + 26) % 26;
      } else {
        original[i] = original[i] % 26;
      }
      char ch = (char) (original[i] + 'a');
      chars[i] = ch;
    }
    return String.valueOf(chars);
  }


  @Test
  public void run() {
    System.out.println(shiftingLetters("dztz", new int[][]{
        {0, 0, 0}, {1, 1, 1}
    }));
  }
}
