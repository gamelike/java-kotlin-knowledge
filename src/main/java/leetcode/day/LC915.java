package leetcode.day;

/**
 * @author gjd3
 */
public class LC915 {
  public int partitionDisjoint(int[] nums) {
    //遍历数组 求最大值
    int max = nums[0];
    int leftMax = max;
    int index = 0;  //下标
    for (int i = 0; i < nums.length; i++) {
      max = Math.max(nums[i], max);
      if (nums[i] < leftMax) {
        leftMax = max;
        index = i;
      }
    }
    return index + 1;
  }
}
