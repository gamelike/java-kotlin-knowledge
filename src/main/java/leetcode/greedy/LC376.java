package leetcode.greedy;

import org.junit.Test;

/**
 * @author gjd
 */
public class LC376 {
  public int wiggleMaxLength(int[] nums) {
    if (nums.length < 2) {
      return nums.length;
    }
    int preDiff = nums[1] - nums[0];
    int curDiff = 0;
    int ant = preDiff != 0 ? 2 : 1;  //默认认为第一组是峰值
    for (int i = 1; i < nums.length - 1; i++) {
      curDiff = nums[i + 1] - nums[i];
      if ((preDiff <= 0 && curDiff > 0) || (preDiff >= 0 && curDiff < 0)) {
        ant++;
        preDiff = curDiff;
      }
    }
    return ant;
  }

  @Test
  public void run(){
    wiggleMaxLength(new int[]{1,7,4,9,2,5});
  }
}
